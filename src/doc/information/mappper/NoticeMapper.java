package doc.information.mappper;

import java.util.List;
import java.util.Map;

import doc.information.entity.Notice;
import doc.information.view.NoticeV;

/**
 * User表操�?
 * 
 * @author jerry
 *
 */
public interface NoticeMapper {
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
	List<NoticeV> selectPageNoticeV(Map<String, Object> params);
	
	
	/**
	 * 分页查询当前用户id
	 * 
	 * @param params
	 * @return
	 */
	List<Notice> selectPage(Map<String, Object> params);
	
	
	List<Notice> selectAllNotice(Map<String, Object> params);

	/**
	 * 获取�?条记�?
	 * 
	 * @param id
	 * @return
	 */
	Notice get(String id);

	/**
	 * 获取�?条记录详�?
	 * 
	 * @param id
	 * @return
	 */
	Notice show(String id);

	/**
	 * INSERT
	 * 
	 * @param user
	 * @return
	 */
	boolean insert(Notice notice);
	/**
	 * INSERTUUSER
	 * 
	 * @param user
	 * @return
	 */
	boolean insertNotice(NoticeV notice);

	/**
	 * UPDATE
	 * 
	 * @param user
	 * @return
	 */
	boolean update(Notice news);
	
	
	/**
	 * UPDATE
	 * 
	 * @param user
	 * @return
	 */
	boolean updateNotiveV(NoticeV news);

	/**
	 * DELETE
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(String id);
	boolean deleteNoticeUser(String id);


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