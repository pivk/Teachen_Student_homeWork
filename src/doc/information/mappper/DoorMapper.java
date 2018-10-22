package doc.information.mappper;

import java.util.List;
import java.util.Map;

import doc.information.entity.Door;
import doc.information.view.DoorV;

/**
 * User表?
 * 
 * @author jerry
 *
 */
public interface DoorMapper {
	/**
	 * 查询总条�?
	 * 
	 * @param params
	 * @return
	 */
	int selectCount(Map<String, Object> params);
	/**
	 * 查询通知总条�?
	 * 
	 * @param params
	 * @return
	 */
	int selectPageCount(Map<String, Object> params);

	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return
	 */
	List<DoorV> selectPageDoorV(Map<String, Object> params);
	
	
	/**
	 * 分页查询当前用户id
	 * 
	 * @param params
	 * @return
	 */
	List<Door> selectPage(Map<String, Object> params);
	
	
	List<Door> selectAllDoor(Map<String, Object> params);

	/**
	 * 获取�?条记�?
	 * 
	 * @param id
	 * @return
	 */
	Door get(String id);

	/**
	 * 获取�?条记录详�?
	 * 
	 * @param id
	 * @return
	 */
	Door show(String id);

	/**
	 * INSERT
	 * 
	 * @param user
	 * @return
	 */
	boolean insert(Door notice);
	/**
	 * INSERTUUSER
	 * 
	 * @param user
	 * @return
	 */
	boolean insertDoor(DoorV notice);

	/**
	 * UPDATE
	 * 
	 * @param user
	 * @return
	 */
	boolean update(Door news);
	
	
	/**
	 * UPDATE
	 * 
	 * @param user
	 * @return
	 */
	boolean updateNotiveV(DoorV news);

	/**
	 * DELETE
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(String id);
	boolean deleteDoorUser(String id);


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
	 *           用户id
	 * @return
	 */
	boolean emptyUserRole(String uId);

}