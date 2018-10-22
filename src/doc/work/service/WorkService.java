package doc.work.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import doc.common.AppData;
import doc.common.BaseService;
import doc.information.entity.Door;
import doc.information.mappper.DoorMapper;
import doc.system.mapper.CourseMapper;
import doc.util.SeqUtil;
import doc.work.entity.Work;
import doc.work.mapper.WorkMapper;
import doc.work.view.WorkV;
import pushunsoft.common.PageData;
import pushunsoft.database.MyBatis;

/**
 * 
 * @author jerry
 *
 */
@Service
public class WorkService extends BaseService {
	/**
	 * 
	 * @return
	 */
	private String getId() {
		SeqUtil seq = new SeqUtil();
		return seq.getId();
	}

	/**
	 * 
	 * @param page
	 * @param v
	 * @return
	 */
	public PageData<WorkV> getPage(Integer page, WorkV v) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		int begin = (page - 1) * this.getPageSize();
		params.put("begin", begin);
		params.put("PageSize", this.getPageSize());
		if (v != null) {
			if (v.getBiaoTi() != null && !v.getBiaoTi().isEmpty()) {
				params.put("biaoTi", "%" + v.getBiaoTi().trim() + "%");
			}
			if (v.getXueqi() != null && !v.getXueqi().isEmpty()) {
				params.put("xueqi", v.getXueqi().trim());
			}
		
			if (v.getCreatorId() != null && !v.getCreatorId().isEmpty()) {
				params.put("creatorId", v.getCreatorId().trim());
			}
		}
		PageData<WorkV> pageData = new PageData<WorkV>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			WorkMapper mapper = session.getMapper(WorkMapper.class);
			pageData.setTotal(mapper.selectCount(params));
			pageData.setData(mapper.selectPage(params));
		} catch (Exception ex) {
			System.out.println(ex);
			this.setMessage("内部错误");
		} finally {
			database.closeSession();
		}
		return pageData;
	}
	
	/**
	 * 
	 * @param page
	 * @param v
	 * @return
	 */
	public PageData<WorkV> getPageUser(Integer page, WorkV v) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		int begin = (page - 1) * this.getPageSize();
		params.put("begin", begin);
		params.put("PageSize", this.getPageSize());
		if (v != null) {
			if (v.getBiaoTi() != null && !v.getBiaoTi().isEmpty()) {
				params.put("biaoTi", "%" + v.getBiaoTi().trim() + "%");
			}
			if (v.getXueqi() != null && !v.getXueqi().isEmpty()) {
				params.put("xueqi", v.getXueqi().trim());
			}
			if (v.getCreator() != null && !v.getCreator().isEmpty()) {
				params.put("creator", v.getCreator().trim());
			}
			if (v.getCreatorId() != null && !v.getCreatorId().isEmpty()) {
				params.put("creatorId", v.getCreatorId().trim());
			}
		}
		PageData<WorkV> pageData = new PageData<WorkV>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			WorkMapper mapper = session.getMapper(WorkMapper.class);
			pageData.setTotal(mapper.selectStudentCount(params));
			pageData.setData(mapper.selectStudentPage(params));
		} catch (Exception ex) {
			this.setMessage("内部错误");
			System.out.println(ex);
		} finally {
			database.closeSession();
		}
		return pageData;
	}


	/**
	 * 
	 * @param id
	 * @return
	 */
	public WorkV show(String id) {
		WorkV parameter = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			WorkMapper mapper = session.getMapper(WorkMapper.class);
			parameter = mapper.show(id);
		} catch (Exception ex) {
			this.setMessage("数据异常");
		} finally {
			database.closeSession();
		}
		return parameter;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public WorkV showFile(WorkV v) {
		WorkV	v2=new WorkV();
		Map<String, Object> params = new HashMap<String, Object>();
		if (v != null) {
			
			if (v.getId() != null && !v.getId().isEmpty()) {
				params.put("id", v.getId().trim());
			}
			if (v.getCreator() != null && !v.getCreator().isEmpty()) {
				params.put("creator", v.getCreator().trim());
			}
		}
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			WorkMapper mapper = session.getMapper(WorkMapper.class);
			v2 = mapper.showFile(v);
		} catch (Exception ex) {
			this.setMessage("数据异常");
			System.out.println(ex);
		} finally {
			database.closeSession();
		}
		return v2;
	}

	public ArrayList<String> list2=new ArrayList<>();


	/**
	 * 
	 * @param entity
	 * @return
	 */
	public boolean add(Work entity) {
		if (entity == null) {
			this.setMessage("数据为空");
			return false;
		}
		entity.setId(getId());
		boolean result = false;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			WorkMapper workMapper = session.getMapper(WorkMapper.class);
			result = workMapper.insert(entity);
			DoorMapper doorMapper = session.getMapper(DoorMapper.class);
			CourseMapper courseMapper = session.getMapper(CourseMapper.class);
			Door door=new Door();
            door.setBiaoTi(entity.getBiaoTi());
            door.setNextName("作业查看");
            door.setMemo("课后作业");
            door.setUrl("/work/show.action?id="+entity.getId()+"&xueqi="+entity.getXueqi());
            List<String> list=courseMapper.selectUserId(entity.getCourseId());
			for (String string : list) {
				door.setId(entity.getId()+string);
				door.setUserId(string);
				doorMapper.insert(door);
			}
			
			/*TreeMapper treeMapper = session.getMapper(TreeMapper.class);
			result = treeMapper.insert(entity);
			if (!result) {
				session.rollback();
				this.setMessage("操作失败");
				return false;
			}

			if (entity.getUserId() != null) {
				result = treeMapper.insertTree(entity);
			list2.add(entity.getId());
				list = treeMapper.selectAll();
				CreateMenu(list, entity.getParentId());
				String uIds = entity.getUserId().substring(0, entity.getUserId().length() - 1);
				String[] uId = uIds.split("/;");
				for (int i = 0; i < uId.length; i++) {
					for (String string : list2) {
						entity.setUserId(uId[i]);
						entity.setId(string);
						result = treeMapper.insertTree(entity);
					}
				}
				String uIds = entity.getUserId().substring(0, entity.getUserId().length() - 1);
				String[] uId = uIds.split(";");
				for (int i = 0; i < uId.length; i++) {
						entity.setUserId(uId[i]);
						result = treeMapper.insertTree(entity);
				}
				
			}
*/
			if (result) {
				session.commit();
			}
		} catch (Exception ex) {
			System.out.println(ex);
			session.rollback();
			this.setMessage("操作失败");
			result = false;
		} finally {
			database.closeSession();
		}

		return result;
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	public boolean edit(Work entity) {
		if (entity == null) {
			this.setMessage("数据为空");
			return false;
		}
		boolean result = false;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			WorkMapper mapper = session.getMapper(WorkMapper.class);
			result = mapper.update(entity);
			if (result) {
				session.commit();
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("操作异常");
			result = false;
		} finally {
			database.closeSession();
		}
		return result;
	}

	/**
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
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
//			TreeMapper treeMapper = session.getMapper(TreeMapper.class);
//			result = treeMapper.delete(id);
//			if (!result) {
//				session.rollback();
//				this.setMessage("a");
//				return false;
//			}
			WorkMapper mapper = session.getMapper(WorkMapper.class);
			result = mapper.delete(id);
			if (!result) {
				session.rollback();
				this.setMessage("操作失败");
				return false;
			}
			session.commit();
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