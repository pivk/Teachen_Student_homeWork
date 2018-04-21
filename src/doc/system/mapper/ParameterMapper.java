package doc.system.mapper;
import java.util.List;
import java.util.Map;

import doc.system.entity.Parameter;
import doc.system.view.ParameterV;
public interface ParameterMapper {
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
	List<Parameter> selectAll(Map<String, Object> params);
	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return
	 */
	List<Parameter> selectPage(Map<String, Object> params);
	/**
	 * 获取一条记录
	 * 
	 * @param id
	 * @return
	 */
	Parameter get(String id);
	/**
	 * 获取一条记录详情
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
	 * 查询所有省份，不分页
	 * 
	 * @param params
	 * @return
	 */
	List<Parameter> selectAllSheng(Map<String, Object> params);
}
