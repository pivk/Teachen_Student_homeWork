package doc.system.service;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import doc.common.AppData;
import doc.common.BaseService;
import doc.system.entity.Parameter;
import doc.system.mapper.ParameterMapper;
import doc.system.view.ParameterV;
import pushunsoft.common.PageData;
import pushunsoft.database.MyBatis;
/**
 * 字典业务类
 * 
 * @author jerry
 *
 */
@Service
public class DictionaryService extends BaseService {
	/**
	 * 生成ID
	 * 
	 * @return
	 */
	private String getId() {
		doc.util.SeqUtil seq = new doc.util.SeqUtil();
		return seq.getId();
	}
	/**
	 * 获取部门列表
	 * @param v
	 * @return
	 */
	public List<Parameter> getList(ParameterV v) {
		// 查询前准备
		Map<String, Object> params = new HashMap<String, Object>();
		// 开始查询数据库
		List<Parameter> list= null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			ParameterMapper mapper = session.getMapper(ParameterMapper.class);
			list=mapper.selectAll(params);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return list;
	}
	public PageData<Parameter> getPage(Integer page, ParameterV v) {
		// 查询前准备
		Map<String, Object> params = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		int begin = (page - 1) * this.getPageSize();
		params.put("begin", begin);
		params.put("PageSize", this.getPageSize());
		if (v != null) {
			if (v.getLei() != null && !"".equals(v.getLei())) {
				params.put("lei", "%" + v.getLei().trim() + "%");
			}
		}
		// 开始查询数据库
		PageData<Parameter> pageData = new PageData<Parameter>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			ParameterMapper mapper = session.getMapper(ParameterMapper.class);
			pageData.setTotal(mapper.selectCount(params));
			pageData.setData(mapper.selectPage(params));
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return pageData;
	}
	/**
	 * 获取一个
	 * 
	 * @param id
	 * @return
	 */
	public Parameter get(String id) {
		// 开始查询数据库
		Parameter parameter = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			ParameterMapper mapper = session.getMapper(ParameterMapper.class);
			parameter = mapper.get(id);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return parameter;
	}
	/**
	 * 获取一个
	 * 
	 * @param id
	 * @return
	 */
	public ParameterV show(String id) {
		if (Arrays.asList(AppData.User_Nei).contains(id)) {
			this.setMessage("越权查询");
			return null;
		}
		// 开始查询数据库
		ParameterV parameter = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			ParameterMapper mapper = session.getMapper(ParameterMapper.class);
			parameter = mapper.show(id);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return parameter;
	}
	/**
	 * 添加
	 * 
	 * @param user
	 * @return
	 */
	public boolean add(Parameter parameter) {
		if (parameter == null) {
			this.setMessage("数据为空");
			return false;
		}
		parameter.setId(getId());
		boolean result=false;
		// 开始执行数据库
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			ParameterMapper mapper = session.getMapper(ParameterMapper.class);
			result = mapper.insert(parameter);
			if (result) {
				// 真正入库
				session.commit();
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("操作失败");
			result = false;
		} finally {
			database.closeSession();
		}
		// 返回处理
		return result;
	}
	/**
	 * 修改
	 * 
	 * @param user
	 * @return
	 */
	public boolean edit(Parameter entity) {
		if (entity == null) {
			this.setMessage("数据为空");
			return false;
		}
		boolean result = false;
		// 开始执行数据库
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			ParameterMapper mapper = session.getMapper(ParameterMapper.class);
			result = mapper.update(entity);
			if (result) {
				// 真正入库
				session.commit();
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("操作失败");
			result = false;
		} finally {
			database.closeSession();
		}
		// 返回处理
		return result;
	}
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean remove(String id) {
		if (id == null) {
			this.setMessage("数据为空");
			return false;
		}
		boolean result = false;
		// 开始执行数据库
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			ParameterMapper mapper = session.getMapper(ParameterMapper.class);
			result = mapper.delete(id);
			if (result) {
				// 真正入库
				session.commit();
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("操作失败");
			result = false;
		} finally {
			database.closeSession();
		}
		// 返回处理
		return result;
	}
	
	/**
	 * 获取省份
	 * @param v
	 * @return
	 */
	public List<Parameter> getSheng(Parameter parameter) {
		// 查询前准备
		Map<String, Object> params = new HashMap<String, Object>();
		if (parameter != null) {
			if (parameter.getLei() != null && !"".equals(parameter.getLei())) {
				params.put("lei", parameter.getLei());
			}
			if (parameter.getJian() != null && !"".equals(parameter.getJian())) {
				params.put("jian", parameter.getJian().trim() + "%");
			}
		}
		// 开始查询数据库
		List<Parameter> list= null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			ParameterMapper mapper = session.getMapper(ParameterMapper.class);
			list=mapper.selectAllSheng(params);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return list;
	}
}