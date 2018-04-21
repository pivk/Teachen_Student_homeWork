package doc.system.mapper;
import java.util.List;
import java.util.Map;

import doc.system.entity.User;
import doc.system.view.UserV;
/**
 * User表操作
 * 
 * @author jerry
 *
 */
public interface UserMapper {
	/**
	 * 查询总条数
	 * 
	 * @param params
	 * @return
	 */
	int selectCount(Map<String, Object> params);
	/**
	 * 查询所有，不分页
	 * 
	 * @param params
	 * @return
	 */
	List<UserV> selectAll(Map<String, Object> params);
	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return
	 */
	List<UserV> selectPage(Map<String, Object> params);
	/**
	 * 获取一条记录
	 * 
	 * @param id
	 * @return
	 */
	User get(String id);
	/**
	 * 获取一条记录详情
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
	 * 获取用户及其角色
	 * 
	 * @param id
	 * @return
	 */
	UserV getUserRole(String id);
	/**
	 * 用户关联角色
	 * 
	 * @param params
	 * @return
	 */
	boolean changeUserRole(Map<String, Object> params);
	/**
	 * emptyUserRole 清空用户角色
	 * 
	 * @param id
	 *            用户id
	 * @return
	 */
	boolean emptyUserRole(String id);
	/**
	 * 登陆
	 * 
	 * @param params
	 *            登录名，加密过的密码
	 * @return
	 */
	User login(Map<String, Object> params);
}