package doc.file.service;

import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import doc.common.AppData;
import doc.common.BaseService;
import doc.file.entity.Directory;
import doc.file.mapper.DirectoryMapper;
import doc.file.mapper.TreeMapper;
import doc.file.view.DirectoryV;
import doc.util.SeqUtil;
import pushunsoft.common.PageData;
import pushunsoft.database.MyBatis;

/**
 * Ŀ¼ҵ����
 * 
 * @author jerry
 *
 */
@Service
public class DirectoryService extends BaseService {
	/**
	 * ����ID
	 * 
	 * @return
	 */
	private String getId() {
		SeqUtil seq = new SeqUtil();
		return seq.getId();
	}

	/**
	 * ��ҳ��ѯ
	 * 
	 * @param page
	 * @param v
	 * @return
	 */
	public PageData<DirectoryV> getPage(Integer page, DirectoryV v) {
		// ��ѯǰ׼��
		Map<String, Object> params = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		int begin = (page - 1) * this.getPageSize();
		params.put("begin", begin);
		params.put("PageSize", this.getPageSize());
		if (v != null) {
			if (v.getMingCheng() != null && !v.getMingCheng().isEmpty()) {
				params.put("mingCheng", "%" + v.getMingCheng().trim() + "%");
			}
		}
		// ��ʼ��ѯ���ݿ�
		PageData<DirectoryV> pageData = new PageData<DirectoryV>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DirectoryMapper mapper = session.getMapper(DirectoryMapper.class);
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
	public Directory get(String id) {
		// ��ʼ��ѯ���ݿ�
		Directory parameter = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DirectoryMapper mapper = session.getMapper(DirectoryMapper.class);
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
	public DirectoryV show(String id) {
		// ��ʼ��ѯ���ݿ�
		DirectoryV parameter = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DirectoryMapper mapper = session.getMapper(DirectoryMapper.class);
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
	 * @param entity
	 * @return
	 */
	public boolean add(Directory entity) {
		if (entity == null) {
			this.setMessage("����Ϊ��");
			return false;
		}
		entity.setId(getId());
		entity.setLei(AppData.Tree_Type_Directory);
		entity.setIcon("folder");
		boolean result = false;
		// ��ʼִ�����ݿ�
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			TreeMapper treeMapper = session.getMapper(TreeMapper.class);
			result = treeMapper.insert(entity);
			if (!result) {
				session.rollback();
				this.setMessage("������ʧ��");
				return false;
			}
			DirectoryMapper directoryMapper = session.getMapper(DirectoryMapper.class);
			result = directoryMapper.insert(entity);
			if (!result) {
				session.rollback();
				this.setMessage("����Ŀ¼ʧ��");
				return false;
			}
			// �������
			session.commit();
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("�����쳣");
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
	public boolean edit(Directory entity) {
		if (entity == null) {
			this.setMessage("����Ϊ��");
			return false;
		}
		boolean result = false;
		// ��ʼִ�����ݿ�
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DirectoryMapper mapper = session.getMapper(DirectoryMapper.class);
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
			TreeMapper treeMapper = session.getMapper(TreeMapper.class);
			result = treeMapper.delete(id);
			if (!result) {
				session.rollback();
				this.setMessage("������ʧ��");
				return false;
			}
			DirectoryMapper mapper = session.getMapper(DirectoryMapper.class);
			result = mapper.delete(id);
			if (!result) {
				session.rollback();
				this.setMessage("����Ŀ¼ʧ��");
				return false;
			}
			// �������
			session.commit();
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("�����쳣");
			result = false;
		} finally {
			database.closeSession();
		}
		// ���ش���
		return result;
	}
}