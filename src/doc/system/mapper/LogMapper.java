package doc.system.mapper;
import java.util.List;
import java.util.Map;

import doc.system.entity.Log;
import doc.system.view.LogV;
public interface LogMapper {
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
	List<Log> selectAll(Map<String, Object> params);
	/**
	 * ��ҳ��ѯ
	 * 
	 * @param params
	 * @return
	 */
	List<Log> selectPage(Map<String, Object> params);
	/**
	 * ��ȡһ����¼
	 * 
	 * @param id
	 * @return
	 */
	Log get(String id);
	/**
	 * ��ȡһ����¼����
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
	 * ��ȡ�û������ɫ
	 * 
	 * @param id
	 * @return
	 */
	LogV getLogRole(String id);
	/**
	 * �û�������ɫ
	 * 
	 * @param params
	 * @return
	 */
	boolean changeLogRole(Map<String, Object> params);
	/**
	 * emptyUserRole ����û���ɫ
	 * 
	 * @param id �û�id
	 * @return
	 */
	boolean emptyLogRole(String id);
}
