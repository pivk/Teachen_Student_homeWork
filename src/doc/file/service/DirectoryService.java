package doc.file.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import doc.common.AppData;
import doc.common.BaseService;
import doc.file.entity.Directory;
import doc.file.mapper.DirectoryMapper;
import doc.file.mapper.TreeMapper;
import doc.file.view.DirectoryV;
import doc.home.view.Menu;
import doc.information.entity.Door;
import doc.information.mappper.DoorMapper;
import doc.system.entity.Unit;
import doc.system.mapper.CourseMapper;
import doc.system.view.UnitV;
import doc.util.SeqUtil;
import pushunsoft.common.PageData;
import pushunsoft.database.MyBatis;

/**
 * Ŀ¼ҵ����
 * 
 * @author jerry
 *
 */
@Service
public class DirectoryService extends BaseService {
	/**
	 * ����ID
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
	public PageData<DirectoryV> getPage(Integer page, DirectoryV v) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		int begin = (page - 1) * this.getPageSize();
		params.put("begin", begin);
		params.put("PageSize", this.getPageSize());
		if (v != null) {
			if (v.getMingCheng() != null && !v.getMingCheng().isEmpty()) {
				params.put("mingCheng", "%" + v.getMingCheng().trim() + "%");
			}
			if (v.getXueqi() != null && !v.getXueqi().isEmpty()) {
				params.put("xueqi", v.getXueqi().trim());
			}
			if (v.getCreator() != null && !v.getCreator().isEmpty()) {
				params.put("creator", v.getCreator().trim());
			}
		}
		PageData<DirectoryV> pageData = new PageData<DirectoryV>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DirectoryMapper mapper = session.getMapper(DirectoryMapper.class);
			pageData.setTotal(mapper.selectCount(params));
			pageData.setData(mapper.selectPage(params));
		} catch (Exception ex) {
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
	public PageData<DirectoryV> getstudentPage(Integer page, DirectoryV v) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		int begin = (page - 1) * this.getPageSize();
		params.put("begin", begin);
		params.put("PageSize", this.getPageSize());
		if (v != null) {
			if (v.getMingCheng() != null && !v.getMingCheng().isEmpty()) {
				params.put("mingCheng", "%" + v.getMingCheng().trim() + "%");
			}
			if (v.getXueqi() != null && !v.getXueqi().isEmpty()) {
				params.put("xueqi", v.getXueqi().trim());
			}
			if (v.getCreator() != null && !v.getCreator().isEmpty()) {
				params.put("creator", v.getCreator().trim());
			}
		}
		PageData<DirectoryV> pageData = new PageData<DirectoryV>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DirectoryMapper mapper = session.getMapper(DirectoryMapper.class);
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
	public DirectoryV show(String id) {
		DirectoryV parameter = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DirectoryMapper mapper = session.getMapper(DirectoryMapper.class);
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
	public DirectoryV showFile(DirectoryV v) {
		DirectoryV	v2=new DirectoryV();
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
			DirectoryMapper mapper = session.getMapper(DirectoryMapper.class);
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
	public boolean add(Directory entity) {
		if (entity == null) {
			this.setMessage("数据为空");
			return false;
		}
		entity.setId(getId());
		entity.setLei(AppData.Tree_Type_Directory);
		entity.setIcon("folder");
		boolean result = false;
		// 开始查询数据库
/*		List<Directory> list = null;
*/		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DirectoryMapper directoryMapper = session.getMapper(DirectoryMapper.class);
			result = directoryMapper.insert(entity);
			DoorMapper doorMapper = session.getMapper(DoorMapper.class);
			CourseMapper courseMapper = session.getMapper(CourseMapper.class);
			Door door=new Door();
            door.setBiaoTi(entity.getMingCheng()+"需上传");
            door.setNextName("文件上传");
            door.setMemo("课程论文");
            door.setUrl("/files/add.action?directoryId="+entity.getId()+"&xueqi="+entity.getXueqi());
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
	public boolean edit(Directory entity) {
		if (entity == null) {
			this.setMessage("数据为空");
			return false;
		}
		boolean result = false;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DirectoryMapper mapper = session.getMapper(DirectoryMapper.class);
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
			DirectoryMapper mapper = session.getMapper(DirectoryMapper.class);
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