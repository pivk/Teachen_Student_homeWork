package doc.system.mapper;
import java.util.List;
import java.util.Map;

import doc.system.entity.Course;
import doc.system.view.CourseV;
import doc.system.view.StatisticsV;
/**
 * Course�����
 * @author jerry
 *
 */
public interface CourseMapper {
	/**
	 * ��ѯ������
	 * @param params
	 * @return
	 */
	int selectCount(Map<String, Object> params);
	/**
	 * ��ѯ���У�����ҳ
	 * @param params
	 * @return
	 */
	List<Course> selectAll(Map<String, Object> params);
	/**
	 * ��ҳ��ѯ
	 * @param params
	 * @return
	 */
	List<Course> selectPage(Map<String, Object> params);
	/**
	 * ��ȡһ����¼
	 * @param id
	 * @return
	 */
	Course get(String id);
	/**
	 * ��ȡһ����¼����
	 * 
	 * @param id
	 * @return
	 */
	CourseV show(String id);
	/**
	 * INSERT
	 * @param user
	 * @return
	 */
	boolean insert(Course entity);
	/**
	 * UPDATE
	 * @param user
	 * @return
	 */
	boolean update(Course entity);
	/**
	 * DELETE
	 * @param id
	 * @return
	 */
	boolean delete(String id);
	boolean insertUser(CourseV courseV);
	List<String> selectUserId(String id);
	int selectUserCount(Map<String, Object> params);
	List<Course> selectUserPage(Map<String, Object> params);
	
	boolean updateUSer(CourseV entity);
	StatisticsV dogetStatisticsV(Map<String, Object> params);
}