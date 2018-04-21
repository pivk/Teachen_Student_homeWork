package doc.system.mapper;
import java.util.List;
import java.util.Map;

import doc.system.entity.Log;
import doc.system.view.LogV;
public interface LogMapper {
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
	List<Log> selectAll(Map<String, Object> params);
	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return
	 */
	List<Log> selectPage(Map<String, Object> params);
	/**
	 * 获取一条记录
	 * 
	 * @param id
	 * @return
	 */
	Log get(String id);
	/**
	 * 获取一条记录详情
	 * 
	 * @param id
	 * @return
	 */
	LogV show(String id);
	/**
	 * INSERT
	 * 
	 * @param user
	 * @return
	 */
	boolean insert(Log log);
	/**
	 * UPDATE
	 * 
	 * @param log
	 * @return
	 */
	boolean update(Log log);
	/**
	 * RESET/update NeiRong
	 * 
	 * @param user
	 * @return
	 */
	boolean updateMima(Log log);
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
	LogV getLogRole(String id);
	/**
	 * 用户关联角色
	 * 
	 * @param params
	 * @return
	 */
	boolean changeLogRole(Map<String, Object> params);
	/**
	 * emptyUserRole 清空用户角色
	 * 
	 * @param id 用户id
	 * @return
	 */
	boolean emptyLogRole(String id);
}
