package doc.file.mapper;

import java.util.List;
import java.util.Map;

import doc.file.entity.File;
import doc.file.view.FileV;

/**
 * Ŀ¼
 * 
 * @author jerry
 *
 */
public interface FileMapper {
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
	List<FileV> selectPage(Map<String, Object> params);

	/**
	 * 
	 * @param id
	 * @return
	 */
	File get(String id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	FileV show(String id);

	/**
	 * INSERT
	 * 
	 * @param user
	 * @return
	 */
	boolean insert(File entity);

	/**
	 * UPDATE
	 * 
	 * @param user
	 * @return
	 */
	boolean update(File entity);

	/**
	 * DELETE
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(String id);
}