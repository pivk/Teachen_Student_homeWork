package doc.system.mapper;
import java.util.List;
import java.util.Map;

import doc.system.entity.Unit;
import doc.system.view.UnitV;
/**
 * Unit表操作
 * @author jerry
 *
 */
public interface UnitMapper {
	/**
	 * 查询总条数
	 * @param params
	 * @return
	 */
	int selectCount(Map<String, Object> params);
	/**
	 * 查询所有，不分页
	 * @param params
	 * @return
	 */
	List<UnitV> selectAll(Map<String, Object> params);
	/**
	 * 分页查询
	 * @param params
	 * @return
	 */
	List<UnitV> selectPage(Map<String, Object> params);
	/**
	 * 获取一条记录
	 * @param id
	 * @return
	 */
	Unit get(String id);
	/**
	 * 获取一条记录详情
	 * 
	 * @param id
	 * @return
	 */
	UnitV show(String id);
	/**
	 * 根据父类id查询子类id
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