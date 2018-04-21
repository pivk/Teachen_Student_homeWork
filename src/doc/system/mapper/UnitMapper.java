package doc.system.mapper;
import java.util.List;
import java.util.Map;

import doc.system.entity.Unit;
import doc.system.view.UnitV;
/**
 * Unit�����
 * @author jerry
 *
 */
public interface UnitMapper {
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
	List<UnitV> selectAll(Map<String, Object> params);
	/**
	 * ��ҳ��ѯ
	 * @param params
	 * @return
	 */
	List<UnitV> selectPage(Map<String, Object> params);
	/**
	 * ��ȡһ����¼
	 * @param id
	 * @return
	 */
	Unit get(String id);
	/**
	 * ��ȡһ����¼����
	 * 
	 * @param id
	 * @return
	 */
	UnitV show(String id);
	/**
	 * ���ݸ���id��ѯ����id
	 * 
	 * @param id
	 * @return
	 */
	String getChilId(String id);
	/**
	 * INSERT
	 * @param user
	 * @return
	 */
	boolean insert(Unit entity);
	/**
	 * UPDATE
	 * @param user
	 * @return
	 */
	boolean update(Unit entity);
	/**
	 * DELETE
	 * @param id
	 * @return
	 */
	boolean delete(String id);
}