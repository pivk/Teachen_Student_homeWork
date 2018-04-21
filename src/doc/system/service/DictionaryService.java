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
 * �ֵ�ҵ����
 * 
 * @author jerry
 *
 */
@Service
public class DictionaryService extends BaseService {
	/**
	 * ����ID
	 * 
	 * @return
	 */
	private String getId() {
		doc.util.SeqUtil seq = new doc.util.SeqUtil();
		return seq.getId();
	}
	/**
	 * ��ȡ�����б�
	 * @param v
	 * @return
	 */
	public List<Parameter> getList(ParameterV v) {
		// ��ѯǰ׼��
		Map<String, Object> params = new HashMap<String, Object>();
		// ��ʼ��ѯ���ݿ�
		List<Parameter> list= null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			ParameterMapper mapper = session.getMapper(ParameterMapper.class);
			list=mapper.selectAll(params);
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
		} finally {
			database.closeSession();
		}
		// ���ش���
		return list;
	}
	public PageData<Parameter> getPage(Integer page, ParameterV v) {
		// ��ѯǰ׼��
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
		// ��ʼ��ѯ���ݿ�
		PageData<Parameter> pageData = new PageData<Parameter>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			ParameterMapper mapper = session.getMapper(ParameterMapper.class);
			pageData.setTotal(mapper.selectCount(params));
			pageData.setData(mapper.selectPage(params));
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
		} finally {
			database.closeSession();
		}
		// ���ش���
		return pageData;
	}
	/**
	 * ��ȡһ��
	 * 
	 * @param id
	 * @return
	 */
	public Parameter get(String id) {
		// ��ʼ��ѯ���ݿ�
		Parameter parameter = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			ParameterMapper mapper = session.getMapper(ParameterMapper.class);
			parameter = mapper.get(id);
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
		} finally {
			database.closeSession();
		}
		// ���ش���
		return parameter;
	}
	/**
	 * ��ȡһ��
	 * 
	 * @param id
	 * @return
	 */
	public ParameterV show(String id) {
		if (Arrays.asList(AppData.User_Nei).contains(id)) {
			this.setMessage("ԽȨ��ѯ");
			return null;
		}
		// ��ʼ��ѯ���ݿ�
		ParameterV parameter = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			ParameterMapper mapper = session.getMapper(ParameterMapper.class);
			parameter = mapper.show(id);
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
		} finally {
			database.closeSession();
		}
		// ���ش���
		return parameter;
	}
	/**
	 * ���
	 * 
	 * @param user
	 * @return
	 */
	public boolean add(Parameter parameter) {
		if (parameter == null) {
			this.setMessage("����Ϊ��");
			return false;
		}
		parameter.setId(getId());
		boolean result=false;
		// ��ʼִ�����ݿ�
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			ParameterMapper mapper = session.getMapper(ParameterMapper.class);
			result = mapper.insert(parameter);
			if (result) {
				// �������
				session.commit();
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("����ʧ��");
			result = false;
		} finally {
			database.closeSession();
		}
		// ���ش���
		return result;
	}
	/**
	 * �޸�
	 * 
	 * @param user
	 * @return
	 */
	public boolean edit(Parameter entity) {
		if (entity == null) {
			this.setMessage("����Ϊ��");
			return false;
		}
		boolean result = false;
		// ��ʼִ�����ݿ�
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			ParameterMapper mapper = session.getMapper(ParameterMapper.class);
			result = mapper.update(entity);
			if (result) {
				// �������
				session.commit();
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("����ʧ��");
			result = false;
		} finally {
			database.closeSession();
		}
		// ���ش���
		return result;
	}
	/**
	 * ɾ��
	 * 
	 * @param id
	 * @return
	 */
	public boolean remove(String id) {
		if (id == null) {
			this.setMessage("����Ϊ��");
			return false;
		}
		boolean result = false;
		// ��ʼִ�����ݿ�
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			ParameterMapper mapper = session.getMapper(ParameterMapper.class);
			result = mapper.delete(id);
			if (result) {
				// �������
				session.commit();
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("����ʧ��");
			result = false;
		} finally {
			database.closeSession();
		}
		// ���ش���
		return result;
	}
	
	/**
	 * ��ȡʡ��
	 * @param v
	 * @return
	 */
	public List<Parameter> getSheng(Parameter parameter) {
		// ��ѯǰ׼��
		Map<String, Object> params = new HashMap<String, Object>();
		if (parameter != null) {
			if (parameter.getLei() != null && !"".equals(parameter.getLei())) {
				params.put("lei", parameter.getLei());
			}
			if (parameter.getJian() != null && !"".equals(parameter.getJian())) {
				params.put("jian", parameter.getJian().trim() + "%");
			}
		}
		// ��ʼ��ѯ���ݿ�
		List<Parameter> list= null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			ParameterMapper mapper = session.getMapper(ParameterMapper.class);
			list=mapper.selectAllSheng(params);
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
		} finally {
			database.closeSession();
		}
		// ���ش���
		return list;
	}
}