package doc.home.service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import doc.common.AppData;
import doc.common.BaseService;
import doc.home.view.Menu;
import doc.system.entity.Parameter;
import doc.system.entity.Power;
import doc.system.entity.User;
import doc.system.mapper.ParameterMapper;
import doc.system.mapper.PowerMapper;
import doc.system.mapper.UserMapper;
import doc.system.view.PowerV;
import doc.util.SecurityUtil;
import pushunsoft.database.MyBatis;
/**
 * 首页类
 * 
 * @author jerry
 *
 */
@Service
public class HomeService extends BaseService {
	/**
	 * 登陆
	 * 
	 * @param id
	 * @return
	 */
	public User login(String uid, String pwd) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", uid);
		params.put("mima", SecurityUtil.encrypt(pwd));
		// 开始查询数据库
		User user = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			user = mapper.login(params);
		} catch (Exception ex) {
			this.setMessage("操作失败");
			System.out.println(ex);
		} finally {
			database.closeSession();
		}
		// 返回处理
		return user;
	}
	/**
	 * 创建菜单
	 * 
	 * @param powerList
	 * @param menu
	 */
	private void CreateMenu(List<PowerV> powerList, Menu menu) {
		for (Power power : powerList) {
			if (power.getUpId().equals(menu.getId())) {
				if (menu.getNodes() == null) {
					menu.setNodes(new ArrayList<Menu>());
				}
				Menu subMenu = new Menu();
				subMenu.setId(power.getId());
				subMenu.setText(power.getMingCheng());
				subMenu.setUrl(power.getLink());
				subMenu.setIcon(power.getIcon());
				menu.getNodes().add(subMenu);
				CreateMenu(powerList, subMenu);
			}
		}
	}
	/**
	 * 生成菜单
	 * 
	 * @param uid
	 * @return
	 */
	public List<Menu> MakeMenu(String uid) {
		if (uid == null || uid.isEmpty())
			return null;
		List<PowerV> powerList = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			PowerMapper mapper = session.getMapper(PowerMapper.class);
			if (!uid.equals(AppData.User_Admin)) {
				powerList = mapper.selectUserPower(uid);
			} else {
				powerList = mapper.selectAll(null);
			}
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		if (powerList == null || powerList.size() == 0)
			return null;
		// 返回处理
		List<Menu> menuList = new ArrayList<Menu>();
		for (PowerV power : powerList) {
			if (!power.getUpId().equals("0"))
				continue;
			Menu menu = new Menu();
			menu.setId(power.getId());
			menu.setText(power.getMingCheng());
			menu.setUrl(power.getLink());
			menuList.add(menu);
			CreateMenu(powerList, menu);
		}
		return menuList;
	}
	/**
	 * 获取一个
	 * 
	 * @param id
	 * @return
	 */
	public User get(String id) {
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
			// 修改密码
			user.setMima(SecurityUtil.encrypt(user.getMima()));
			// 密码加密
			user.setMima(SecurityUtil.encrypt(AppData.User_Password));
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
	 * 更换皮肤
	 * 
	 * @param userId
	 *            用户ID
	 * @param skin
	 *            皮肤ID
	 * @return
	 */
	public boolean changeSkin(String id, String skin) {
		//update cr_user set skin=skin where ID=userId
		User user=new User();
		if (id == null) {
			this.setMessage("用户数据为空");
			return false;
		}
		if (skin == null) {
			this.setMessage("皮肤数据为空");
			return false;
		}
		boolean result = false;
		if (id != null && !"".equals(id)) {
			user.setId(id);
		}
		if (skin != null && !"".equals(skin)) {
			user.setSkin(skin);
		}
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
	public List<Parameter> getList(Parameter v) {
		// 查询前准备
		Map<String, Object> params = new HashMap<String, Object>();
		// 开始查询数据库
		List<Parameter> list= null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			ParameterMapper mapper = session.getMapper(ParameterMapper.class);
			list=mapper.selectAll(params);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return list;
	}
}