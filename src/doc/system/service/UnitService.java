package doc.system.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import doc.common.AppData;
import doc.common.BaseService;
import doc.home.view.Menu;
import doc.system.entity.Unit;
import doc.system.mapper.UnitMapper;
import doc.system.view.ImportUnit;
import doc.system.view.UnitV;
import doc.system.view.UserV;
import pushunsoft.common.PageData;
import pushunsoft.database.MyBatis;
/**
 * 机构业务类
 * 
 * @author jerry
 *
 */
@Service
public class UnitService extends BaseService {
	@Resource
	private UserService userService;
	
	/**
	 * 获取部门列表
	 * 
	 * @param v
	 * @return
	 */
	public List<Menu> getListAll(String str) {
		// 查询前准备
		Map<String, Object> params = new HashMap<String, Object>();
		// 开始查询数据库
		List<UnitV> list = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UnitMapper mapper = session.getMapper(UnitMapper.class);
			list = mapper.selectAll(params);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		if (list == null || list.size() == 0)
			return null;

		List<Menu> menuList = new ArrayList<Menu>();
		for (UnitV unit : list) {
			if (!unit.getParentId().equals("0"))
				continue;
			Menu menu = new Menu();
			menu.setId(unit.getId());
			menu.setText(unit.getMingCheng());
			menuList.add(menu);
			if(str == null) CreateMenu(list, menu);
			else CreateUserMenu(list, menu);
			
		}
		// 返回处理
		return menuList;

	}
	/**
	 * 创建组织菜单
	 * 
	 * @param powerList
	 * @param menu
	 */
	private void CreateMenu(List<UnitV> unitList, Menu menu) {
		for (Unit unit : unitList) {
			if (unit.getParentId() != null && unit.getParentId().equals(menu.getId())) {
				if (menu.getNodes() == null) {
					menu.setNodes(new ArrayList<Menu>());
				}
				Menu subMenu = new Menu();
				subMenu.setId(unit.getId());
				subMenu.setText(unit.getMingCheng());
				menu.getNodes().add(subMenu);
				CreateMenu(unitList, subMenu);
			}
		}
	}
	/**
	 * 创建组织人员菜单
	 * 
	 * @param powerList
	 * @param menu
	 */
	private void CreateUserMenu(List<UnitV> unitList, Menu menu) {
		UserV u = new UserV();
		u.setUnitId(menu.getId());
		List<UserV> userList = userService.getList(u);
		if(userList != null && userList.size()>0){
			if (menu.getNodes() == null) {
				menu.setNodes(new ArrayList<Menu>());
			}
			for(UserV uv : userList ){
				Menu subUserMenu = new Menu();
				subUserMenu.setId(uv.getId());
				subUserMenu.setText(uv.getXingMing());
				subUserMenu.setFlag(true);
				subUserMenu.setUnitName(uv.getUnitName());
				menu.getNodes().add(subUserMenu);
			}
		}
		for (Unit unit : unitList) {
			if (unit.getParentId() != null && unit.getParentId().equals(menu.getId())) {
				if (menu.getNodes() == null) {
					menu.setNodes(new ArrayList<Menu>());
				}
				Menu subMenu = new Menu();
				subMenu.setId(unit.getId());
				subMenu.setText(unit.getMingCheng());
				menu.getNodes().add(subMenu);
				CreateUserMenu(unitList, subMenu);
			}
		}
	}
	
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
	 * 获取部门列表
	 * @param v
	 * @return
	 */
	public List<UnitV> getList(UnitV v) {
		// 查询前准备
		Map<String, Object> params = new HashMap<String, Object>();
		// 开始查询数据库
		List<UnitV> list= null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UnitMapper mapper = session.getMapper(UnitMapper.class);
			list=mapper.selectAll(params);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return list;
	}
	public PageData<UnitV> getPage(Integer page, UnitV v) {
		// 查询前准备
		Map<String, Object> params = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		int begin = (page - 1) * this.getPageSize();
		params.put("begin", begin);
		params.put("PageSize", this.getPageSize());
		if (v != null) {
			if (v.getMingCheng() != null && !"".equals(v.getMingCheng())) {
				params.put("mingCheng", "%" + v.getMingCheng().trim() + "%");
			}
			if (v.getParentId() != null && !"".equals(v.getParentId())) {
				params.put("parentId",  v.getParentId().trim() );
			}
		}
		// 开始查询数据库
		PageData<UnitV> pageData = new PageData<UnitV>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UnitMapper mapper = session.getMapper(UnitMapper.class);
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
	public Unit get(String id) {
		// 开始查询数据库
		Unit unit = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UnitMapper mapper = session.getMapper(UnitMapper.class);
			unit = mapper.show(id);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return unit;
	}
	/**
	 * 获取一个
	 * 
	 * @param id
	 * @return
	 */
	public UnitV show(String id) {
		if (Arrays.asList(AppData.User_Nei).contains(id)) {
			this.setMessage("越权查询");
			return null;
		}
		// 开始查询数据库
		UnitV unit = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UnitMapper mapper = session.getMapper(UnitMapper.class);
			unit = mapper.show(id);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return unit;
	}
	/**
	 * 导入机构
	 * 
	 * @param path
	 * @return
	 */
	public boolean importUnit(String path) {
		ImportUnit util = new doc.system.view.ImportUnit();
		List<Unit> list = util.ReadXls(path);
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
			UnitMapper mapper = session.getMapper(UnitMapper.class);
			for (int i = 0; i < list.size(); i++) {
				Unit unit = mapper.get(list.get(i).getId());
				if (unit != null) {
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
	/**
	 * 添加
	 * 
	 * @param user
	 * @return
	 */
	public boolean add(Unit unit) {
		if (unit == null) {
			this.setMessage("数据为空");
			return false;
		}
		unit.setId(getId());
		boolean result=false;
		// 开始执行数据库
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UnitMapper mapper = session.getMapper(UnitMapper.class);
			result = mapper.insert(unit);
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
	public boolean edit(Unit entity) {
		if (entity == null) {
			this.setMessage("数据为空");
			return false;
		}
		boolean result = false;
		// 开始执行数据库
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UnitMapper mapper = session.getMapper(UnitMapper.class);
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
		boolean result=delUnit(id);
		// 返回处理
		return result;
	}
	/**
	 * 将此id的下级全部删除 
	 * @param id
	 * @return
	 */
	public boolean delUnit(String id){
		boolean result = false;
		// 开始执行数据库
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UnitMapper mapper = session.getMapper(UnitMapper.class);
			String chilId=mapper.getChilId(id);
			if(chilId!=null){
				delUnit(chilId);
			}
			result=mapper.delete(id);
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