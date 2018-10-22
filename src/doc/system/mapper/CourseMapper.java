package doc.system.mapper;
import java.util.List;
import java.util.Map;

import doc.system.entity.Course;
import doc.system.view.CourseV;
import doc.system.view.StatisticsV;
/**
 * Course表操作
 * @author jerry
 *
 */
public interface CourseMapper {
	/**
	 * 查询总条数
	 * @param params
	 * @return
	 */
	int selectCount(Map<String, Object> params);
	/**
	 * 查询所有，不分页
	 * @param params
	 * @return
	 */
	List<Course> selectAll(Map<String, Object> params);
	/**
	 * 分页查询
	 * @param params
	 * @return
	 */
	List<Course> selectPage(Map<String, Object> params);
	/**
	 * 获取一条记录
	 * @param id
	 * @return
	 */
	Course get(String id);
	/**
	 * 获取一条记录详情
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