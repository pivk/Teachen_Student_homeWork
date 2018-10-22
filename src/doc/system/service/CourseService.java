package doc.system.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import doc.common.AppData;
import doc.common.BaseService;
import doc.file.mapper.FileMapper;
import pushunsoft.common.PageData;
import doc.system.entity.Course;
import doc.system.mapper.CourseMapper;
import doc.system.mapper.UserMapper;
import doc.system.view.CourseV;
import doc.system.view.StatisticsV;
import pushunsoft.database.MyBatis;
/**
 * ��ɫҵ����
 * 
 * @author jerry
 *
 */
@Service
public class CourseService extends BaseService {

	/**
	 * ����ID
	 * 
	 * @return
	 */
	private String getId() {
		doc.util.SeqUtil seq = new doc.util.SeqUtil();
		return seq.getId();
	}
	
	public boolean addUsers(CourseV courseV) {
		if (courseV == null) {
			this.setMessage("����Ϊ��");
			return false;
		}
		String id = courseV.getId().substring(0, courseV.getId().length() - 1);
		String[] uId = id.split(",");
	
		boolean result = false;
		// ��ʼִ�����ݿ�
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			CourseMapper mapper = session.getMapper(CourseMapper.class);
			// ִ��ɾ��
			for (int i = 0; i < uId.length; i++) {
				courseV.setCourseId(uId[i]);
				result = mapper.insertUser(courseV);
			}
			if (result) {
				// �������
				session.commit();
			}
		} catch (Exception ex) {
			System.out.println(ex);
			session.rollback();
			this.setMessage("����ʧ��");
			result = false;
		} finally {
			database.closeSession();
		}
		// ���ش���
		return result;
	}
	/**
	 * ��ȡ��ɫ�б�
	 * 
	 * @return
	 */
	public List<Course> getList() {
		// ��ѯǰ׼��
		Map<String, Object> params = new HashMap<String, Object>();
		// ��ʼ��ѯ���ݿ�
		List<Course> list = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			CourseMapper mapper = session.getMapper(CourseMapper.class);
			list = mapper.selectAll(params);
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
		} finally {
			database.closeSession();
		}
		// ���ش���
		return list;
	}
	/**
	 * 
	 * @return
	 */
	public List<StatisticsV> dogetStatisticsV(String directoryId) {
		List<StatisticsV> list = new ArrayList<StatisticsV>();
		Map<String, Object> params = new HashMap<String, Object>();
		if(directoryId!=null){
			params.put("directoryId",directoryId);
		}
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			FileMapper mapper = session.getMapper(FileMapper.class);
			for (int i=0; i<=4 ;i++) {
				if(i<4){
					params.put("begin", 100-i*10);
					params.put("end", 100-i*10-10);
				}else {
					params.put("begin", 60);
					params.put("end", 0);
				}
				StatisticsV	statisticsV = mapper.dogetStatisticsV(params);
				list.add(statisticsV);
			}
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
			System.out.println(ex);
		} finally {
			database.closeSession();
		}
		// ���ش���
		return list;
	}
	/**
	 * ��ҳ��ѯ
	 * 
	 * @param page
	 *            ҳ�룬��1��ʼ
	 * @param v
	 *            ��ѯ ����
	 * @return
	 */
	public PageData<Course> getPage(Integer page, CourseV v) {
		// ��ѯǰ׼��
		Map<String, Object> params = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		if (v.getId() != null && !"".equals(v.getId())) {
			params.put("courseId", v.getId());
		}
		if (v.getMingCheng() != null && !"".equals(v.getMingCheng())) {
			params.put("mingCheng", "%"+v.getMingCheng()+ "%");
		}
		if (v.getUserId() != null && !"".equals(v.getUserId())) {
			params.put("userId",v.getUserId());
		}
		int begin = (page - 1) * this.getPageSize();
		params.put("begin", begin);
		params.put("PageSize", this.getPageSize());
		// ��ʼ��ѯ���ݿ�
		PageData<Course> pageData = new PageData<Course>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			CourseMapper mapper = session.getMapper(CourseMapper.class);
			pageData.setTotal(mapper.selectCount(params));
			pageData.setData(mapper.selectPage(params));
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
		} finally {
			database.closeSession();
		}
		// ���ش���
		return pageData;
	}
	/**
	 * ��ҳ��ѯ
	 * 
	 * @param page
	 *            ҳ�룬��1��ʼ
	 * @param v
	 *            ��ѯ ����
	 * @return
	 */
	public PageData<Course> doUserPage(CourseV v) {
		// ��ѯǰ׼��
		Map<String, Object> params = new HashMap<String, Object>();	
		// ��ʼ��ѯ���ݿ�
		if (v.getUserName() != null && !"".equals(v.getUserName())) {
			params.put("userName", "%"+v.getUserName() +"%");
		}
		if (v.getId() != null && !"".equals(v.getId())) {
			params.put("courseId",v.getId());
		}
		PageData<Course> pageData = new PageData<Course>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			CourseMapper mapper = session.getMapper(CourseMapper.class);
			pageData.setTotal(mapper.selectUserCount(params));
			pageData.setData(mapper.selectUserPage(params));
		} catch (Exception ex) {
			System.out.println(ex);
			this.setMessage("����ʧ��");
		} finally {
			database.closeSession();
		}
		// ���ش���
		return pageData;
	}
	
	/**
	 * ��ȡһ��
	 * 
	 * @param id
	 * @return
	 */
	public Course get(String id) {
		// ��ʼ��ѯ���ݿ�
		Course entity = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			CourseMapper mapper = session.getMapper(CourseMapper.class);
			entity = mapper.get(id);
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
		} finally {
			database.closeSession();
		}
		// ���ش���
		return entity;
	}
	/**
	 * ��ӽ�ɫ
	 * 
	 * @return
	 */
	public boolean add(Course entity) {
		if (entity == null) {
			this.setMessage("����Ϊ��");
			return false;
		}
		entity.setId(getId());
		boolean result = false;
		// ��ʼ��ѯ���ݿ�
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			CourseMapper mapper = session.getMapper(CourseMapper.class);
			result = mapper.insert(entity);
			if (result) {
				// �������
				session.commit();
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("����ʧ��");
			result = false;
		} finally {
			database.closeSession();
		}
		// ���ش���
		return result;
	}
	/**
	 * �޸�
	 * 
	 * @param user
	 * @return
	 */
	public boolean edit(Course entity) {
		if (entity == null) {
			this.setMessage("����Ϊ��");
			return false;
		}
		boolean result = false;
		// ��ʼִ�����ݿ�
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			CourseMapper mapper = session.getMapper(CourseMapper.class);
			result = mapper.update(entity);
			if (result) {
				// �������
				session.commit();
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("����ʧ��");
			result = false;
		} finally {
			database.closeSession();
		}
		// ���ش���
		return result;
	}
	/**
	 * �޸�
	 * 
	 * @param user
	 * @return
	 */
	public boolean doEditUser(CourseV entity) {
		if (entity == null) {
			this.setMessage("����Ϊ��");
			return false;
		}
		boolean result = false;
		// ��ʼִ�����ݿ�
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			CourseMapper mapper = session.getMapper(CourseMapper.class);
			result = mapper.updateUSer(entity);
			if (result) {
				// �������
				session.commit();
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("����ʧ��");
			System.out.println(ex);
			result = false;
		} finally {
			database.closeSession();
		}
		// ���ش���
		return result;
	}
	/**
	 * ɾ��
	 * 
	 * @param id
	 * @return
	 */
	public boolean remove(String id) {
		if (id == null) {
			this.setMessage("����Ϊ��");
			return false;
		}
		boolean result = false;
		// ��ʼִ�����ݿ�
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			CourseMapper mapper = session.getMapper(CourseMapper.class);
			result = mapper.delete(id);
			if (result) {
				// �������
				session.commit();
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("����ʧ��");
			result = false;
		} finally {
			database.closeSession();
		}
		// ���ش���
		return result;
	}
}