package doc.kaoqin.mapper;

import java.util.List;
import java.util.Map;

import doc.kaoqin.entity.Kaoqin;
import doc.kaoqin.view.KaoqinV;

/**
 * Ŀ¼
 * 
 * @author jerry
 *
 */

public interface KaoqinMapper {
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
	List<KaoqinV> selectPage(Map<String, Object> params);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Kaoqin get(String id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	KaoqinV show(String id);

	/**
	 * INSERT
	 * 
	 * @param user
	 * @return
	 */
	boolean insert(Kaoqin entity);

	/**
	 * UPDATE
	 * 
	 * @param user
	 * @return
	 */
	boolean update(Kaoqin entity);

	/**
	 * DELETE
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(String id);

	int selectStudentCount(Map<String, Object> params);

	List<KaoqinV> selectStudentPage(Map<String, Object> params);

	KaoqinV showFile(KaoqinV v);
}