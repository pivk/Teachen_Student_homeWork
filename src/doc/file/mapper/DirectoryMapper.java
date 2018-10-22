package doc.file.mapper;

import java.util.List;
import java.util.Map;

import doc.file.entity.Directory;
import doc.file.view.DirectoryV;

/**
 * Ŀ¼
 * 
 * @author jerry
 *
 */
public interface DirectoryMapper {
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
	List<DirectoryV> selectPage(Map<String, Object> params);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Directory get(String id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	DirectoryV show(String id);

	/**
	 * INSERT
	 * 
	 * @param user
	 * @return
	 */
	boolean insert(Directory entity);

	/**
	 * UPDATE
	 * 
	 * @param user
	 * @return
	 */
	boolean update(Directory entity);

	/**
	 * DELETE
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(String id);

	int selectStudentCount(Map<String, Object> params);

	List<DirectoryV> selectStudentPage(Map<String, Object> params);

	DirectoryV showFile(DirectoryV v);
}