package doc.file.mapper;

import java.util.List;
import java.util.Map;

import doc.file.entity.Tree;
import doc.file.view.TreeV;

/**
 * ÎÄµµ
 * 
 * @author jerry
 *
 */
public interface TreeMapper {
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
	List<TreeV> selectPage(Map<String, Object> params);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Tree get(String id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	TreeV show(String id);

	/**
	 * INSERT
	 * 
	 * @param user
	 * @return
	 */
	boolean insert(Tree entity);

	/**
	 * UPDATE
	 * 
	 * @param user
	 * @return
	 */
	boolean update(Tree entity);

	/**
	 * DELETE
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(String id);
}