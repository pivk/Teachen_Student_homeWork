package doc.file.service;

import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import doc.common.AppData;
import doc.common.BaseService;
import doc.file.entity.File;
import doc.file.mapper.FileMapper;
import doc.file.mapper.TreeMapper;
import doc.file.view.FileV;
import doc.util.SeqUtil;
import pushunsoft.common.PageData;
import pushunsoft.database.MyBatis;

/**
 * �ļ�ҵ����
 * 
 * @author jerry
 *
 */
@Service
public class FileService extends BaseService {
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
	 * �������ļ���
	 * 
	 * @return
	 */
	public String getFileName(String module,String project) {
		return "test";
	}

	/**
	 * ��ȡ�ļ�����
	 * 
	 * @param fileName
	 * @return
	 */
	public String getFileType(String fileName) {
		if (fileName == null || fileName.isEmpty()) {
			return "δ֪";
		}
		int dot = fileName.lastIndexOf('.');
		if ((dot > -1) && (dot < (fileName.length() - 1))) {
			return fileName.substring(dot + 1);
		} else
			return "δ֪";
	}

	/**
	 * ��ҳ��ѯ
	 * 
	 * @param page
	 * @param v
	 * @return
	 */
	public PageData<FileV> getPage(Integer page, FileV v) {
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
		PageData<FileV> pageData = new PageData<FileV>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			FileMapper mapper = session.getMapper(FileMapper.class);
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
	public File get(String id) {
		// ��ʼ��ѯ���ݿ�
		File parameter = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			FileMapper mapper = session.getMapper(FileMapper.class);
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
	public FileV show(String id) {
		// ��ʼ��ѯ���ݿ�
		FileV parameter = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			FileMapper mapper = session.getMapper(FileMapper.class);
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
	public boolean add(File entity) {
		if (entity == null) {
			this.setMessage("����Ϊ��");
			return false;
		}
		entity.setId(getId());
		entity.setLei(AppData.Tree_Type_File);
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
			FileMapper FileMapper = session.getMapper(FileMapper.class);
			result = FileMapper.insert(entity);
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
	public boolean edit(File entity) {
		if (entity == null) {
			this.setMessage("����Ϊ��");
			return false;
		}
		boolean result = false;
		// ��ʼִ�����ݿ�
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			FileMapper mapper = session.getMapper(FileMapper.class);
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
			FileMapper mapper = session.getMapper(FileMapper.class);
			result = mapper.delete(id);
			if (!result) {
				session.rollback();
				this.setMessage("�����ļ�ʧ��");
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