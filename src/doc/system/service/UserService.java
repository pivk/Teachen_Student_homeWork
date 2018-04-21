package doc.system.service;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import doc.common.AppData;
import doc.common.BaseService;
import pushunsoft.common.PageData;
import doc.system.entity.User;
import doc.system.mapper.UserMapper;
import doc.system.view.ImportUser;
import doc.system.view.UserV;
import doc.util.SecurityUtil;
import pushunsoft.database.MyBatis;
/**
 * 用户业务类
 * 
 * @author jerry
 *
 */
@Service
public class UserService extends BaseService {
	/**
	 * 分页查询
	 * 
	 * @param page
	 *            页码，从1开始
	 * @param v
	 *            查询 条件
	 * @return
	 */
	public PageData<UserV> getPage(Integer page, UserV v) {
		// 查询前准备
		Map<String, Object> params = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		int begin = (page - 1) * this.getPageSize();
		params.put("begin", begin);
		params.put("PageSize", this.getPageSize());
		if (v != null) {
			if (v.getUnitId() != null && !"".equals(v.getUnitId())) {
				params.put("unitId", v.getUnitId());
			}
			if (v.getXingMing() != null && !"".equals(v.getXingMing())) {
				params.put("xingMing", "%" + v.getXingMing().trim() + "%");
			}
			if (v.getStatus() != null && !"".equals(v.getStatus())) {
				params.put("status", v.getStatus());
			}
		}
		// 内置账号不能被查询
		StringBuilder idNotIn = new StringBuilder();
		if (AppData.User_Nei.length > 0) {
			idNotIn.append("'0'");
			for (String user : AppData.User_Nei) {
				idNotIn.append(",'").append(user).append("'");
			}
		}
		if (idNotIn.length() > 0) {
			params.put("id_not_in", idNotIn.toString());
		}
		// 开始查询数据库
		PageData<UserV> pageData = new PageData<UserV>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			pageData.setTotal(mapper.selectCount(params));
			pageData.setData(mapper.selectPage(params));
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return pageData;
	}
	/**
	 * 获取一个
	 * 
	 * @param id
	 * @return
	 */
	public User get(String id) {
		if (Arrays.asList(AppData.User_Nei).contains(id)) {
			this.setMessage("越权查询");
			return null;
		}
		// 开始查询数据库
		User user = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			user = mapper.get(id);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return user;
	}
	/**
	 * 获取一个(审核显示)
	 * 
	 * @param id
	 * @return
	 */
	public User getUser(String id) {
		// 开始查询数据库
		User user = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			user = mapper.get(id);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return user;
	}
	/**
	 * 获取一个
	 * 
	 * @param id
	 * @return
	 */
	public UserV show(String id) {
		if (Arrays.asList(AppData.User_Nei).contains(id)) {
			this.setMessage("越权查询");
			return null;
		}
		// 开始查询数据库
		UserV user = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			user = mapper.show(id);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return user;
	}
	/**
	 * 添加
	 * 
	 * @param user
	 * @return
	 */
	public boolean add(User user) {
		if (user == null) {
			this.setMessage("数据为空");
			return false;
		}
		if (Arrays.asList(AppData.User_Nei).contains(user.getId())) {
			this.setMessage("账号已存在");
			return false;
		}
		// 密码加密
		user.setMima(SecurityUtil.encrypt(AppData.User_Password));
		boolean result = false;
		// 开始执行数据库
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			result = mapper.insert(user);
			if (result) {
				// 真正入库
				session.commit();
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("操作失败");
			result = false;
		} finally {
			database.closeSession();
		}
		// 返回处理
		return result;
	}
	/**
	 * 修改
	 * 
	 * @param user
	 * @return
	 */
	public boolean edit(User user) {
		if (user == null) {
			this.setMessage("数据为空");
			return false;
		}
		if (Arrays.asList(AppData.User_Nei).contains(user.getId())) {
			this.setMessage("账号不可修改");
			return false;
		}
		boolean result = false;
		// 开始执行数据库
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			result = mapper.update(user);
			if (result) {
				// 真正入库
				session.commit();
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("操作失败");
			result = false;
		} finally {
			database.closeSession();
		}
		// 返回处理
		return result;
	}
	/**
	 * 修改/重置 密码
	 * 
	 * @param user
	 * @return
	 */
	public boolean editMima(User user) {
		if (user == null) {
			this.setMessage("数据为空");
			return false;
		} else {
			// 重置密码
			if (user.getMima() == null) {
				user.setMima(SecurityUtil.encrypt(AppData.User_Password));
			} else {
				// 修改密码
				user.setMima(SecurityUtil.encrypt(user.getMima()));
			}
		}
		if (Arrays.asList(AppData.User_Nei).contains(user.getId())) {
			this.setMessage("账号不可修改");
			return false;
		}
		boolean result = false;
		// 开始执行数据库
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			result = mapper.updateMima(user);
			if (result) {
				// 真正入库
				session.commit();
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("操作失败");
			result = false;
		} finally {
			database.closeSession();
		}
		// 返回处理
		return result;
	}
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean remove(String id) {
		if (id == null) {
			this.setMessage("数据为空");
			return false;
		}
		if (Arrays.asList(AppData.User_Nei).contains(id)) {
			this.setMessage("账号不可删除");
			return false;
		}
		boolean result = false;
		// 开始执行数据库
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			result = mapper.delete(id);
			if (result) {
				// 真正入库
				session.commit();
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("操作失败");
			result = false;
		} finally {
			database.closeSession();
		}
		// 返回处理
		return result;
	}
	/**
	 * 批量删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteUsers(String id) {
		if (id == null) {
			this.setMessage("数据为空");
			return false;
		}
		id = id.substring(0, id.length() - 1);
		String[] uId = id.split(",");
		List<String> list = Arrays.asList(uId);
		List<String> nei = Arrays.asList(AppData.User_Nei);
		// 内置账号不能删除
		for (int i = 0; i < list.size(); i++) {
			if (nei.contains(list.get(i))) {
				this.setMessage("账号不可删除");
				return false;
			}
		}
		boolean result = false;
		// 开始执行数据库
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			// 执行删除
			for (int i = 0; i < uId.length; i++) {
				result = mapper.delete(uId[i]);
			}
			if (result) {
				// 真正入库
				session.commit();
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("操作失败");
			result = false;
		} finally {
			database.closeSession();
		}
		// 返回处理
		return result;
	}
	/**
	 * 统计数量
	 * 
	 * @param v
	 * @return
	 */
	public int count(UserV v) {
		// 查询前准备
		Map<String, Object> params = new HashMap<String, Object>();
		if (v != null) {
			if (v.getId() != null && !"".equals(v.getId())) {
				params.put("id", v.getId());
			}
		}
		// 内置账号不能被查询
		StringBuilder idNotIn = new StringBuilder();
		if (AppData.User_Nei.length > 0) {
			idNotIn.append("'0'");
			for (String user : AppData.User_Nei) {
				idNotIn.append(",'").append(user).append("'");
			}
		}
		if (idNotIn.length() > 0) {
			params.put("id_not_in", idNotIn.toString());
		}
		// 开始查询数据库
		int count = 0;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			count = mapper.selectCount(params);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return count;
	}
	/**
	 * 获取用户角色
	 * 
	 * @param id
	 * @return
	 */
	public UserV getUserRole(String id) {
		if (Arrays.asList(AppData.User_Nei).contains(id)) {
			this.setMessage("越权查询");
			return null;
		}
		// 开始查询数据库
		UserV user = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			user = mapper.getUserRole(id);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return user;
	}
	/**
	 * 用户关联角色
	 * 
	 * @param uId,用户id
	 *            rIds,角色idS
	 * @return
	 */
	public boolean userRole(String uId, String rIds) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uId", uId);
		if (uId == null) {
			this.setMessage("数据为空");
			return false;
		}
		if (Arrays.asList(AppData.User_Nei).contains(uId)) {
			this.setMessage("账号不可修改");
			return false;
		}
		boolean result = false;
		// 开始执行数据库
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			// 角色为空，删除即可
			if (rIds == null || rIds.equals("")) {
				result = mapper.emptyUserRole(uId);
			} else {
				// 角色不为空，删除后进行添加
				mapper.emptyUserRole(uId);
				// 去掉最后的逗号
				rIds = rIds.substring(0, rIds.length() - 1);
				// 拆分字符串
				String[] rId = rIds.split(",");
				for (int i = 0; i < rId.length; i++) {
					params.put("rId", rId[i]);
					result = mapper.changeUserRole(params);
				}
			}
			if (result) {
				// 真正入库
				session.commit();
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("操作失败");
			result = false;
		} finally {
			database.closeSession();
		}
		// 返回处理
		return result;
	}
	/**
	 * 导入用户
	 * 
	 * @param path
	 * @return
	 */
	public boolean importUser(String path) {
		ImportUser util = new ImportUser();
		List<User> list = util.ReadXls(path);
		List<String> nei = Arrays.asList(AppData.User_Nei);
		// 去掉excel中与内置用户冲突的
		for (int i = 0; i < list.size(); i++) {
			if (nei.contains(list.get(i).getId())) {
				list.remove(i);
				i--;
			}
		}
		// 开始执行数据库
		boolean result = false;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			for (int i = 0; i < list.size(); i++) {
				User user = mapper.get(list.get(i).getId());
				if (user != null) {
					result = mapper.update(list.get(i));
				} else {
					result = mapper.insert(list.get(i));
				}
			}
			if (result) {
				// 真正入库
				session.commit();
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("操作失败");
			result = false;
		} finally {
			database.closeSession();
		}
		// 返回处理
		return result;
	}
}