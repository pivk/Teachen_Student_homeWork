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
import doc.information.entity.Notice;
import doc.information.mappper.DoorMapper;
import doc.information.mappper.NoticeMapper;
import doc.information.view.NoticeV;
import pushunsoft.common.PageData;
import pushunsoft.database.MyBatis;

/**
 * 通知业务�?
 * 
 * @author jerry
 *
 */
@Service
public class NoticeService extends BaseService {
	
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
	public PageData<Notice> getNoticePage(Integer page, Notice n) {
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
		PageData<Notice> pageData = new PageData<Notice>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			NoticeMapper mapper = session.getMapper(NoticeMapper.class);
			
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
	public PageData<NoticeV> getNoticeVPage(Integer page, NoticeV n) {
		// 查询前准�?
		Map<String, Object> params = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		int begin = (page - 1) * this.getPageSize();
		params.put("begin", begin);
		params.put("end", begin + this.getPageSize());
		if (n != null) {
			if(n.getUserId() != null && !"".equals(n.getUserId())){
				params.put("userId", n.getUserId());
			}
			if (n.getBiaoTi() != null && !"".equals(n.getBiaoTi())) {
				params.put("biaoTi", "%"+n.getBiaoTi()+"%");
			}
		}
		// 数据库执�?
		PageData<NoticeV> pageData = new PageData<NoticeV>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			NoticeMapper mapper = session.getMapper(NoticeMapper.class);
			pageData.setTotal(mapper.selectPageCount(params));
			pageData.setData(mapper.selectPageNoticeV(params));
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回结果
		return pageData;
	}
	
	public List<NoticeV> getNoticeVPageFour(Integer page, NoticeV n) {
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
		List<NoticeV> noticeList = new ArrayList<NoticeV>();
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			NoticeMapper mapper = session.getMapper(NoticeMapper.class);
			noticeList = mapper.selectPageNoticeV(params);
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
	public List<Notice> getNoticeAll(Integer page, Notice n) {
		// 查询前准�?
		Map<String, Object> params = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		int begin = (page - 1) * this.getPageSize();
		params.put("begin", begin);
		params.put("end", begin + 9);
		// 数据库执�?
		List<Notice> noticeList = new ArrayList<Notice>();
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			NoticeMapper mapper = session.getMapper(NoticeMapper.class);
			noticeList = mapper.selectAllNotice(params);
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
	public Notice getNotice(String id) {
		if (Arrays.asList(AppData.User_Nei).contains(id)) {
			this.setMessage("越权查询");
			return null;
		}
		// �?始查�?
		Notice notice = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			NoticeMapper mapper = session.getMapper(NoticeMapper.class);
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
	public boolean noticeEdit(Notice notice) {
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
			NoticeMapper mapper = session.getMapper(NoticeMapper.class);
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
	public boolean add(NoticeV notice) {
		if (notice == null) {
			this.setMessage("数据为空");
			return false;
		}
		String id = getId();
		notice.setId(id);
		notice.setNoticeId(id);
		String du = "未读";
		notice.setDu(du);
		boolean result = false;
		boolean resultUser = false;
		String userId = null;
		// �?始执行数据库
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			NoticeMapper mapper = session.getMapper(NoticeMapper.class);
			result = mapper.insert(notice);
			DoorMapper doorMapper = session.getMapper(DoorMapper.class);
			Door door=new Door();
            door.setBiaoTi(notice.getBiaoTi());
            door.setNextName("查看");
            door.setMemo("消息通知");
            door.setUrl("/information/noticeHtml.action?id="+notice.getId());
			if (notice.getUserId() != null) {
				userId = notice.getUserId();
				String uIds = notice.getUserId().substring(0, notice.getUserId().length() - 1);
				String[] uId = uIds.split(";");
				for (int i = 0; i < uId.length; i++) {
					notice.setUserId(uId[i]);
					door.setId(notice.getId()+uId[i]);
					door.setUserId(uId[i]);
					resultUser  = mapper.insertNotice(notice);
					resultUser=doorMapper.insert(door);
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
	public boolean updeatDate(NoticeV noticev) {
		if (noticev == null) {
			this.setMessage("数据为空");
			return false;
		}
		boolean result = false;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			NoticeMapper mapper = session.getMapper(NoticeMapper.class);
			result = mapper.updateNotiveV(noticev);
			DoorMapper doorMapper = session.getMapper(DoorMapper.class);
			result = doorMapper.delete(noticev.getId()+noticev.getCreator());			if (result) {
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
	 * 
	 * @param id
	 * @return
	 */
	public Notice show(String id) {
		if (Arrays.asList(AppData.User_Nei).contains(id)) {
			this.setMessage("越权");
			return null;
		}
		// 查询
		Notice notice = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			NoticeMapper mapper = session.getMapper(NoticeMapper.class);
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
			NoticeMapper mapper = session.getMapper(NoticeMapper.class);
			result = mapper.delete(id);
			result = mapper.deleteNoticeUser(id);
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
	public boolean deleteNotices(String id) {

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
			NoticeMapper mapper = session.getMapper(NoticeMapper.class);
			for (int i = 0; i < uId.length; i++) {
				result = mapper.delete(uId[i]);
				result = mapper.deleteNoticeUser(uId[i]);
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