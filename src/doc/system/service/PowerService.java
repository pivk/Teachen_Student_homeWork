package doc.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import doc.common.BaseService;
import doc.home.view.Menu;
import doc.system.entity.Power;
import doc.system.entity.RolePower;
import doc.system.mapper.PowerMapper;
import doc.system.view.PowerTree;
import doc.system.view.PowerV;
import pushunsoft.database.MyBatis;

/**
 * 权限业务�?
 * 
 * @author jerry
 *
 */
@Service
public class PowerService extends BaseService {
	/**
	 * 获取�?有权�?
	 * 
	 * @return
	 */
	public List<PowerV> getAll() {
		// 查询前准�?
		Map<String, Object> params = new HashMap<String, Object>();
		// 数据库执�?
		List<PowerV> list = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			PowerMapper mapper = session.getMapper(PowerMapper.class);
			list = mapper.selectAll(params);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回结果
		return list;
	}

	/**
	 * 查询用户权限
	 * 
	 * @param roleId
	 * @return
	 */
	public List<PowerV> selectUserPower(String userId) {
		// �?始查询数据库
		List<PowerV> list = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			PowerMapper mapper = session.getMapper(PowerMapper.class);
			list = mapper.selectUserPower(userId);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回结果
		return list;
	}

	/**
	 * 查询角色权限
	 * 
	 * @param roleId
	 * @return
	 */
	public List<PowerV> getRolePower(String roleId) {
		// �?始查询数据库
		List<PowerV> list = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			PowerMapper mapper = session.getMapper(PowerMapper.class);
			list = mapper.selectRolePower(roleId);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回结果
		return list;
	}
	public List<PowerV> getOffice(String userId) {
		// �?始查询数据库
		List<PowerV> list = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			PowerMapper mapper = session.getMapper(PowerMapper.class);
			list = mapper.selectOffice(userId);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回结果
		return list;
	}

	/**
	 * 保存角色权限
	 * 
	 * @param roleId
	 * @param powerList
	 * @return
	 */
	public boolean saveRolePower(String roleId, List<Power> powerList) {
		// �?始查询数据库
		boolean result = false;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			PowerMapper mapper = session.getMapper(PowerMapper.class);
			List<RolePower> toDoList = new ArrayList<RolePower>();
			if (powerList != null && powerList.size() > 0) {
				for (Power power : powerList) {
					RolePower rolePower = new RolePower();
					rolePower.setRoleId(roleId);
					rolePower.setPowerId(power.getId());
					toDoList.add(rolePower);
				}
			}
			result = mapper.deleteRolePower(roleId);
			for (RolePower power : toDoList) {
				result = mapper.insertRolePower(power);
			}
			if (result) {
				session.commit();
			}
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回结果
		return result;
	}
	/*public boolean saveOffice(String userId, List<Power> powerList) {
		// �?始查询数据库
		boolean result = false;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			PowerMapper mapper = session.getMapper(PowerMapper.class);
			List<RolePower> toDoList = new ArrayList<RolePower>();
			if (powerList != null && powerList.size() > 0) {
				for (Power power : powerList) {
					RolePower rolePower = new RolePower();
					rolePower.setRoleId(userId);
					rolePower.setPowerId(power.getId());
					toDoList.add(rolePower);
				}
			}
			result = mapper.deleteOffice(userId);
			for (RolePower power : toDoList) {
				result = mapper.insertOffice(power);
			}
			if (result) {
				session.commit();
			}
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回结果
		return result;
	}*/
	/**
	 * 查看用户权限
	 * 
	 * @param roleId
	 * @return
	 */
	public List<PowerV> getUserPower(String userId) {
		// 执行数据�?
		List<PowerV> list = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			PowerMapper mapper = session.getMapper(PowerMapper.class);
			list = mapper.selectUserPower(userId);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回结果
		return list;
	}

	/**
	 * 创建多级�?
	 * 
	 * @param powerList
	 * @param node
	 */
	private void CreateTree(List<PowerV> powerList, PowerTree node) {
		for (Power power : powerList) {
			if (power.getUpId().equals(node.getId())) {
				if (node.getNodes() == null) {
					node.setNodes(new ArrayList<PowerTree>());
				}
				PowerTree subNode = new PowerTree();
				subNode.setId(power.getId());
				subNode.setMingCheng(power.getMingCheng());
				subNode.setLink(power.getLink());
				node.getNodes().add(subNode);
				CreateTree(powerList, subNode);
			}
		}
	}

	/**
	 * 获取权限�?
	 * 
	 * @return
	 */
	public PowerTree getTree() {
		List<PowerV> list = getAll();
		PowerTree tree = new PowerTree();
		tree.setId("0");
		tree.setMingCheng("权限");
		CreateTree(list, tree);
		return tree;
	}

	/**
	 * 递归生成权限�?
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
				menu.getNodes().add(subMenu);
				CreateMenu(powerList, subMenu);
			}
		}
	}

	/**
	 * 创建菜单树，适用treeview控件
	 * 
	 * @return
	 */
	public List<Menu> MakeMenu() {
		List<PowerV> powerList = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			PowerMapper mapper = session.getMapper(PowerMapper.class);

			powerList = mapper.selectAll(null);

		} catch (Exception ex) {
			this.setMessage("读取权限异常");
		} finally {
			database.closeSession();
		}

		if (powerList == null || powerList.size() == 0)
			return null;

		// �?始构建树
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
	 * 创建菜单树，适用treeview控件
	 * 
	 * @return
	 */
	public List<Menu> MakeUserMenu(String userId) {
		List<PowerV> powerList = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			PowerMapper mapper = session.getMapper(PowerMapper.class);
			powerList = mapper.selectUserPower(userId);
		} catch (Exception ex) {
			this.setMessage("读取权限异常");
		} finally {
			database.closeSession();
		}

		if (powerList == null || powerList.size() == 0)
			return null;

		// �?始构建树
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
	public List<Menu> MakeOfficeMenu(String userId) {
		List<PowerV> powerList = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			PowerMapper mapper = session.getMapper(PowerMapper.class);
			powerList = mapper.selectOffice(userId);
		} catch (Exception ex) {
			this.setMessage("读取权限异常");
		} finally {
			database.closeSession();
		}

		if (powerList == null || powerList.size() == 0)
			return null;

		// �?始构建树
		List<Menu> menuList = new ArrayList<Menu>();
		Menu menum = new Menu();
		menum.setId("rcbg");
		menum.setText("日常办公");
		menum.setUrl("#");
		menum.setIcon("oa-icon oa-icon-gongzuotai");
		List<Menu> nodes = new ArrayList<Menu>();
		Menu menu1 = new Menu();
		menu1.setId("index");
		menu1.setText("首页");
		menu1.setUrl("/");
		menu1.setIcon("oa-icon oa-icon-home");
		nodes.add(menu1);
		Menu menu2 = new Menu();
		menu2.setId("powerOffice");
		menu2.setText("自定�?");
		menu2.setUrl("/system/power/power.action");
		menu2.setIcon("oa-icon oa-icon-flow-todo");
		nodes.add(menu2);
		for (PowerV power : powerList) {
			Menu menu = new Menu();
			menu.setId(power.getId());
			menu.setText(power.getMingCheng());
			menu.setUrl(power.getLink());
/*			menu.setIcon(power.getLei());
*/			nodes.add(menu);
		}
		menum.setNodes(nodes);
		menuList.add(menum);
		return menuList;
	}
}