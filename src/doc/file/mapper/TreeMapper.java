package doc.file.mapper;

import java.util.List;
import java.util.Map;

import doc.file.entity.Directory;
import doc.file.entity.Tree;
import doc.file.view.TreeV;

/**
 * �ĵ�
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


	boolean insertTree(Directory entity);

	
	List<Directory> selectAll();

	List<TreeV> selectStudentPage(Map<String, Object> params);

	int selectStudentCount(Map<String, Object> params);
}