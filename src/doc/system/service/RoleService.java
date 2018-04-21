package doc.system.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import doc.common.BaseService;
import pushunsoft.common.PageData;
import doc.system.entity.Role;
import doc.system.mapper.RoleMapper;
import doc.system.view.RoleV;
import pushunsoft.database.MyBatis;
/**
 * 角色业务类
 * 
 * @author jerry
 *
 */
@Service
public class RoleService extends BaseService {
	/**
	 * 生成ID
	 * 
	 * @return
	 */
	private String getId() {
		doc.util.SeqUtil seq = new doc.util.SeqUtil();
		return seq.getId();
	}
	/**
	 * 获取角色列表
	 * 
	 * @return
	 */
	public List<Role> getList() {
		// 查询前准备
		Map<String, Object> params = new HashMap<String, Object>();
		// 开始查询数据库
		List<Role> list = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			RoleMapper mapper = session.getMapper(RoleMapper.class);
			list = mapper.selectAll(params);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return list;
	}
	/**
	 * 分页查询
	 * 
	 * @param page
	 *            页码，从1开始
	 * @param v
	 *            查询 条件
	 * @return
	 */
	public PageData<Role> getPage(Integer page, RoleV v) {
		// 查询前准备
		Map<String, Object> params = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		int begin = (page - 1) * this.getPageSize();
		params.put("begin", begin);
		params.put("PageSize", this.getPageSize());
		// 开始查询数据库
		PageData<Role> pageData = new PageData<Role>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			RoleMapper mapper = session.getMapper(RoleMapper.class);
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
	public Role get(String id) {
		// 开始查询数据库
		Role entity = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			RoleMapper mapper = session.getMapper(RoleMapper.class);
			entity = mapper.get(id);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return entity;
	}
	/**
	 * 添加角色
	 * 
	 * @return
	 */
	public boolean add(Role entity) {
		if (entity == null) {
			this.setMessage("数据为空");
			return false;
		}
		entity.setId(getId());
		boolean result = false;
		// 开始查询数据库
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			RoleMapper mapper = session.getMapper(RoleMapper.class);
			result = mapper.insert(entity);
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
	public boolean edit(Role entity) {
		if (entity == null) {
			this.setMessage("数据为空");
			return false;
		}
		boolean result = false;
		// 开始执行数据库
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			RoleMapper mapper = session.getMapper(RoleMapper.class);
			result = mapper.update(entity);
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
		boolean result = false;
		// 开始执行数据库
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			RoleMapper mapper = session.getMapper(RoleMapper.class);
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
}