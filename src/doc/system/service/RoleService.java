package doc.system.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import doc.common.BaseService;
import pushunsoft.common.PageData;
import doc.system.entity.Role;
import doc.system.mapper.RoleMapper;
import doc.system.view.RoleV;
import pushunsoft.database.MyBatis;
/**
 * ��ɫҵ����
 * 
 * @author jerry
 *
 */
@Service
public class RoleService extends BaseService {
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
	 * ��ȡ��ɫ�б�
	 * 
	 * @return
	 */
	public List<Role> getList() {
		// ��ѯǰ׼��
		Map<String, Object> params = new HashMap<String, Object>();
		// ��ʼ��ѯ���ݿ�
		List<Role> list = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			RoleMapper mapper = session.getMapper(RoleMapper.class);
			list = mapper.selectAll(params);
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
		} finally {
			database.closeSession();
		}
		// ���ش���
		return list;
	}
	/**
	 * ��ҳ��ѯ
	 * 
	 * @param page
	 *            ҳ�룬��1��ʼ
	 * @param v
	 *            ��ѯ ����
	 * @return
	 */
	public PageData<Role> getPage(Integer page, RoleV v) {
		// ��ѯǰ׼��
		Map<String, Object> params = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		int begin = (page - 1) * this.getPageSize();
		params.put("begin", begin);
		params.put("PageSize", this.getPageSize());
		// ��ʼ��ѯ���ݿ�
		PageData<Role> pageData = new PageData<Role>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			RoleMapper mapper = session.getMapper(RoleMapper.class);
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
	public Role get(String id) {
		// ��ʼ��ѯ���ݿ�
		Role entity = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			RoleMapper mapper = session.getMapper(RoleMapper.class);
			entity = mapper.get(id);
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
		} finally {
			database.closeSession();
		}
		// ���ش���
		return entity;
	}
	/**
	 * ��ӽ�ɫ
	 * 
	 * @return
	 */
	public boolean add(Role entity) {
		if (entity == null) {
			this.setMessage("����Ϊ��");
			return false;
		}
		entity.setId(getId());
		boolean result = false;
		// ��ʼ��ѯ���ݿ�
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			RoleMapper mapper = session.getMapper(RoleMapper.class);
			result = mapper.insert(entity);
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
	public boolean edit(Role entity) {
		if (entity == null) {
			this.setMessage("����Ϊ��");
			return false;
		}
		boolean result = false;
		// ��ʼִ�����ݿ�
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			RoleMapper mapper = session.getMapper(RoleMapper.class);
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
			RoleMapper mapper = session.getMapper(RoleMapper.class);
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
}