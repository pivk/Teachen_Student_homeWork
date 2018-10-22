package doc.work.mapper;

import java.util.List;
import java.util.Map;

import doc.work.entity.Work;
import doc.work.view.WorkV;

/**
 * Ŀ¼
 * 
 * @author jerry
 *
 */

public interface WorkMapper {
	/**
	 * 
	 * @param params
	 * @return
	 */
	int selectCount(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	List<WorkV> selectPage(Map<String, Object> params);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Work get(String id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	WorkV show(String id);

	/**
	 * INSERT
	 * 
	 * @param user
	 * @return
	 */
	boolean insert(Work entity);

	/**
	 * UPDATE
	 * 
	 * @param user
	 * @return
	 */
	boolean update(Work entity);

	/**
	 * DELETE
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(String id);

	int selectStudentCount(Map<String, Object> params);

	List<WorkV> selectStudentPage(Map<String, Object> params);

	WorkV showFile(WorkV v);
}