package doc.system.mapper;
import java.util.List;
import java.util.Map;

import doc.system.entity.Parameter;
import doc.system.view.ParameterV;
public interface ParameterMapper {
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
	List<Parameter> selectAll(Map<String, Object> params);
	/**
	 * ��ҳ��ѯ
	 * 
	 * @param params
	 * @return
	 */
	List<Parameter> selectPage(Map<String, Object> params);
	/**
	 * ��ȡһ����¼
	 * 
	 * @param id
	 * @return
	 */
	Parameter get(String id);
	/**
	 * ��ȡһ����¼����
	 * 
	 * @param id
	 * @return
	 */
	ParameterV show(String id);
	/**
	 * INSERT
	 * 
	 * @param user
	 * @return
	 */
	boolean insert(Parameter Parameter);
	/**
	 * UPDATE
	 * 
	 * @param Parameter
	 * @return
	 */
	boolean update(Parameter Parameter);
	/**
	 * DELETE
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(String id);
	
	/**
	 * ��ѯ����ʡ�ݣ�����ҳ
	 * 
	 * @param params
	 * @return
	 */
	List<Parameter> selectAllSheng(Map<String, Object> params);
}
