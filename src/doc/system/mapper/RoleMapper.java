package doc.system.mapper;
import java.util.List;
import java.util.Map;

import doc.system.entity.Role;
import doc.system.view.RoleV;
/**
 * Role�����
 * @author jerry
 *
 */
public interface RoleMapper {
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
	List<Role> selectAll(Map<String, Object> params);
	/**
	 * ��ҳ��ѯ
	 * @param params
	 * @return
	 */
	List<Role> selectPage(Map<String, Object> params);
	/**
	 * ��ȡһ����¼
	 * @param id
	 * @return
	 */
	Role get(String id);
	/**
	 * ��ȡһ����¼����
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