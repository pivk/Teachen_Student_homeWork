package doc.system.mapper;
import java.util.List;
import java.util.Map;

import doc.system.entity.Role;
import doc.system.view.RoleV;
/**
 * Role表操作
 * @author jerry
 *
 */
public interface RoleMapper {
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
	List<Role> selectAll(Map<String, Object> params);
	/**
	 * 分页查询
	 * @param params
	 * @return
	 */
	List<Role> selectPage(Map<String, Object> params);
	/**
	 * 获取一条记录
	 * @param id
	 * @return
	 */
	Role get(String id);
	/**
	 * 获取一条记录详情
	 * 
	 * @param id
	 * @return
	 */
	RoleV show(String id);
	/**
	 * INSERT
	 * @param user
	 * @return
	 */
	boolean insert(Role entity);
	/**
	 * UPDATE
	 * @param user
	 * @return
	 */
	boolean update(Role entity);
	/**
	 * DELETE
	 * @param id
	 * @return
	 */
	boolean delete(String id);
}