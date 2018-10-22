package doc.information.service;

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
import doc.information.entity.Door;
import doc.information.mappper.DoorMapper;
import doc.information.view.DoorV;
import pushunsoft.common.PageData;
import pushunsoft.database.MyBatis;

/**
 * 通知业务�?
 * 
 * @author jerry
 *
 */
@Service
public class DoorService extends BaseService {
	
	private String getId() {
		return System.currentTimeMillis() + "";
	}
	
	/**
	 * 分页查询
	 * 
	 * @param page
	 *            页码�?1�?�?
	 * @param v
	 *            查询条件
	 * @return
	 */
	public PageData<Door> getDoorPage(Integer page, Door n) {
		// 查询前准�?
		Map<String, Object> params = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		int begin = (page - 1) * this.getPageSize();
		params.put("begin", begin);
		params.put("end", begin + this.getPageSize());
		if (n != null) {
			if (n.getBiaoTi() != null && !"".equals(n.getBiaoTi())) {
				params.put("biaoti", "%"+n.getBiaoTi()+"%");
			}
		}
		// 数据库执�?
		PageData<Door> pageData = new PageData<Door>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DoorMapper mapper = session.getMapper(DoorMapper.class);
			
			pageData.setTotal(mapper.selectCount(params));
			pageData.setData(mapper.selectPage(params));
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回结果
		return pageData;
	}
	
	
	
	/**
	 * 分页查询通知关联user
	 * 
	 * @param page
	 *            页码�?1�?�?
	 * @param v
	 *            查询条件
	 * @return
	 */
	public PageData<DoorV> getDoorVPage(DoorV n) {
		// 查询前准�?
		Map<String, Object> params = new HashMap<String, Object>();
		if (n != null) {
			if(n.getUserId() != null && !"".equals(n.getUserId())){
				params.put("userId", n.getUserId());
			}
			if (n.getBiaoTi() != null && !"".equals(n.getBiaoTi())) {
				params.put("biaoTi", "%"+n.getBiaoTi()+"%");
			}
		}
		PageData<DoorV> pageData = new PageData<DoorV>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DoorMapper mapper = session.getMapper(DoorMapper.class);
			pageData.setTotal(mapper.selectPageCount(params));
			pageData.setData(mapper.selectPageDoorV(params));
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回结果
		return pageData;
	}
	
	public List<DoorV> getDoorVPageFour(Integer page, DoorV n) {
		// 查询前准�?
		Map<String, Object> params = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		int begin = (page - 1) * this.getPageSize();
		params.put("begin", begin);
		params.put("end", begin + 4);
		if (n != null) {
			if(n.getUserId() != null && !"".equals(n.getUserId())){
				params.put("userId", n.getUserId());
			}
			if (n.getBiaoTi() != null && !"".equals(n.getBiaoTi())) {
				params.put("biaoTi", "%"+n.getBiaoTi()+"%");
			}
		}
		// 数据库执�?
		List<DoorV> noticeList = new ArrayList<DoorV>();
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DoorMapper mapper = session.getMapper(DoorMapper.class);
			noticeList = mapper.selectPageDoorV(params);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回结果
		return noticeList;
	}
	
	/**
	 * 分页查询
	 * 
	 * @param page
	 *            页码�?1�?�?
	 * @param v
	 *            查询条件
	 * @return
	 */
	public List<Door> getDoorAll(Integer page, Door n) {
		// 查询前准�?
		Map<String, Object> params = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		int begin = (page - 1) * this.getPageSize();
		params.put("begin", begin);
		params.put("end", begin + 9);
		// 数据库执�?
		List<Door> noticeList = new ArrayList<Door>();
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DoorMapper mapper = session.getMapper(DoorMapper.class);
			noticeList = mapper.selectAllDoor(params);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回结果
		return noticeList;
	}

	/**
	 * 获取�?个�?�知基本信息
	 * 
	 * @param id
	 * @return
	 */
	public Door getDoor(String id) {
		if (Arrays.asList(AppData.User_Nei).contains(id)) {
			this.setMessage("越权查询");
			return null;
		}
		// �?始查�?
		Door notice = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DoorMapper mapper = session.getMapper(DoorMapper.class);
			notice = mapper.get(id);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回结果
		return notice;
	}
	
	/**
	 * 修改通知
	 * 
	 * @param news
	 * @return
	 */
	public boolean noticeEdit(Door notice) {
		if (notice == null) {
			this.setMessage("数据为空");
			return false;
		}
//		if (Arrays.asList(AppData.User_Nei).contains(notice.getId())) {
//			this.setMessage("内置账号不可修改");
//			return false;
//		}
		boolean result = false;
		// 数据库执�?
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DoorMapper mapper = session.getMapper(DoorMapper.class);
			result = mapper.update(notice);
			if (result) {
				// 成功入库
				session.commit();
			}else{
				session.rollback();
				this.setMessage("操作失败");
				result = false;
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("操作失败");
			result = false;
		} finally {
			database.closeSession();
		}
		// 返回结果
		return result;
	}
	
	/**
	 * 添加通知
	 * 
	 * @param user
	 * @return
	 */
	public boolean add(DoorV notice) {
		if (notice == null) {
			this.setMessage("数据为空");
			return false;
		}
		String id = getId();
		notice.setId(id);
	
		boolean result = false;
		boolean resultUser = false;
		String userId = null;
		// �?始执行数据库
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DoorMapper mapper = session.getMapper(DoorMapper.class);
			result = mapper.insert(notice);
			if (notice.getUserId() != null) {
				userId = notice.getUserId();
				String uIds = notice.getUserId().substring(0, notice.getUserId().length() - 1);
				String[] uId = uIds.split(";");
				for (int i = 0; i < uId.length; i++) {
					notice.setUserId(uId[i]);
					resultUser  = mapper.insertDoor(notice);
				}
			}
			if (result) {
				// 成功入库
				session.commit();
			}else{
				session.rollback();
				this.setMessage("操作失败");
				result = false;
			}
		} catch (Exception ex) {
			System.out.println(ex);
			session.rollback();
			this.setMessage("操作失败");
			result = false;
		} finally {
			database.closeSession();
		}
		
		// 返回结果
		return result;
	}

	/**
	 * 修改查看通知
	 * 
	 * @param news
	 * @return
	 */
	public boolean updeatDate(DoorV noticev) {
		if (noticev == null) {
			this.setMessage("数据为空");
			return false;
		}
		boolean result = false;
		// 数据库执�?
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DoorMapper mapper = session.getMapper(DoorMapper.class);
			result = mapper.updateNotiveV(noticev);
			if (result) {
				// 成功入库
				session.commit();
			}else{
				session.rollback();
				this.setMessage("操作失败");
				result = false;
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("操作失败");
			result = false;
		} finally {
			database.closeSession();
		}
		// 返回结果
		return result;
	}
	
	/**
	 * 获取�?个新闻全部信�?
	 * 
	 * @param id
	 * @return
	 */
	public Door show(String id) {
		if (Arrays.asList(AppData.User_Nei).contains(id)) {
			this.setMessage("越权");
			return null;
		}
		// 查询
		Door notice = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DoorMapper mapper = session.getMapper(DoorMapper.class);
			notice = mapper.show(id);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回结果
		return notice;
	}
	
	/**
	 * 删除通知
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
			this.setMessage("无法删除");
			return false;
		}
		boolean result = false;
		// 数据库执�?
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DoorMapper mapper = session.getMapper(DoorMapper.class);
			result = mapper.delete(id);
			result = mapper.deleteDoorUser(id);
			if (result) {
				// 入库
				session.commit();
			}else{
				session.rollback();
				this.setMessage("操作失败");
				result = false;
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("操作失败");
			result = false;
		} finally {
			database.closeSession();
		}
		// 返回结果
		return result;
	}
	
	
	/**
	 * 删除多条通知
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteDoors(String id) {

		if (id == null) {
			this.setMessage("数据为空");
			return false;
		}
		id = id.substring(0, id.length() - 1);
		String[] uId = id.split(",");
		boolean result = false;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DoorMapper mapper = session.getMapper(DoorMapper.class);
			for (int i = 0; i < uId.length; i++) {
				result = mapper.delete(uId[i]);
				result = mapper.deleteDoorUser(uId[i]);
			}
			if (result) {
				// 入库
				session.commit();
			}else{
				session.rollback();
				this.setMessage("操作失败");
				result = false;
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("操作失败");
			result = false;
		} finally {
			database.closeSession();
		}
		return result;
	}
}