package doc.system.mapper;
import java.util.List;
import java.util.Map;

import doc.system.entity.RolePower;
import doc.system.view.PowerV;
/**
 * User表操作
 * 
 * @author jerry
 *
 */
public interface PowerMapper {
	/**
	 * 查询所有，不分页
	 * 
	 * @param params
	 * @return
	 */
	List<PowerV> selectAll(Map<String, Object> params);
	List<PowerV> selectRolePower(String roleId);
	boolean insertRolePower(RolePower entity);
	boolean deleteRolePower(String roleId);
	List<PowerV> selectUserPower(String userId);
}