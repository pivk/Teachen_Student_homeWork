package doc.system.mapper;
import java.util.List;
import java.util.Map;

import doc.system.entity.User;
import doc.system.view.UserV;
/**
 * User�����
 * 
 * @author jerry
 *
 */
public interface UserMapper {
	/**
	 * ��ѯ������
	 * 
	 * @param params
	 * @return
	 */
	int selectCount(Map<String, Object> params);
	/**
	 * ��ѯ���У�����ҳ
	 * 
	 * @param params
	 * @return
	 */
	List<UserV> selectAll(Map<String, Object> params);
	/**
	 * ��ҳ��ѯ
	 * 
	 * @param params
	 * @return
	 */
	List<UserV> selectPage(Map<String, Object> params);
	/**
	 * ��ȡһ����¼
	 * 
	 * @param id
	 * @return
	 */
	User get(String id);
	/**
	 * ��ȡһ����¼����
	 * 
	 * @param id
	 * @return
	 */
	UserV show(String id);
	/**
	 * INSERT
	 * 
	 * @param user
	 * @return
	 */
	boolean insert(User user);
	/**
	 * UPDATE
	 * 
	 * @param user
	 * @return
	 */
	boolean update(User user);
	/**
	 * RESET/update MIMA
	 * 
	 * @param user
	 * @return
	 */
	boolean updateMima(User user);
	/**
	 * DELETE
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(String id);
	/**
	 * ��ȡ�û������ɫ
	 * 
	 * @param id
	 * @return
	 */
	UserV getUserRole(String id);
	/**
	 * �û�������ɫ
	 * 
	 * @param params
	 * @return
	 */
	boolean changeUserRole(Map<String, Object> params);
	/**
	 * emptyUserRole ����û���ɫ
	 * 
	 * @param id
	 *            �û�id
	 * @return
	 */
	boolean emptyUserRole(String id);
	/**
	 * ��½
	 * 
	 * @param params
	 *            ��¼�������ܹ�������
	 * @return
	 */
	User login(Map<String, Object> params);
}