package doc.system.service;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import doc.common.AppData;
import doc.common.BaseService;
import pushunsoft.common.PageData;
import doc.system.entity.User;
import doc.system.mapper.UserMapper;
import doc.system.view.ImportUser;
import doc.system.view.UserV;
import doc.util.SecurityUtil;
import pushunsoft.database.MyBatis;
/**
 * �û�ҵ����
 * 
 * @author jerry
 *
 */
@Service
public class UserService extends BaseService {
	/**
	 * ��ҳ��ѯ
	 * 
	 * @param page
	 *            ҳ�룬��1��ʼ
	 * @param v
	 *            ��ѯ ����
	 * @return
	 */
	public PageData<UserV> getPage(Integer page, UserV v) {
		// ��ѯǰ׼��
		Map<String, Object> params = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		int begin = (page - 1) * this.getPageSize();
		params.put("begin", begin);
		params.put("PageSize", this.getPageSize());
		if (v != null) {
			if (v.getUnitId() != null && !"".equals(v.getUnitId())) {
				params.put("unitId", v.getUnitId());
			}
			if (v.getXingMing() != null && !"".equals(v.getXingMing())) {
				params.put("xingMing", "%" + v.getXingMing().trim() + "%");
			}
			if (v.getStatus() != null && !"".equals(v.getStatus())) {
				params.put("status", v.getStatus());
			}
		}
		// �����˺Ų��ܱ���ѯ
		StringBuilder idNotIn = new StringBuilder();
		if (AppData.User_Nei.length > 0) {
			idNotIn.append("'0'");
			for (String user : AppData.User_Nei) {
				idNotIn.append(",'").append(user).append("'");
			}
		}
		if (idNotIn.length() > 0) {
			params.put("id_not_in", idNotIn.toString());
		}
		// ��ʼ��ѯ���ݿ�
		PageData<UserV> pageData = new PageData<UserV>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
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
	public User get(String id) {
		if (Arrays.asList(AppData.User_Nei).contains(id)) {
			this.setMessage("ԽȨ��ѯ");
			return null;
		}
		// ��ʼ��ѯ���ݿ�
		User user = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			user = mapper.get(id);
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
		} finally {
			database.closeSession();
		}
		// ���ش���
		return user;
	}
	/**
	 * ��ȡһ��(�����ʾ)
	 * 
	 * @param id
	 * @return
	 */
	public User getUser(String id) {
		// ��ʼ��ѯ���ݿ�
		User user = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			user = mapper.get(id);
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
		} finally {
			database.closeSession();
		}
		// ���ش���
		return user;
	}
	/**
	 * ��ȡһ��
	 * 
	 * @param id
	 * @return
	 */
	public UserV show(String id) {
		if (Arrays.asList(AppData.User_Nei).contains(id)) {
			this.setMessage("ԽȨ��ѯ");
			return null;
		}
		// ��ʼ��ѯ���ݿ�
		UserV user = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			user = mapper.show(id);
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
		} finally {
			database.closeSession();
		}
		// ���ش���
		return user;
	}
	/**
	 * ���
	 * 
	 * @param user
	 * @return
	 */
	public boolean add(User user) {
		if (user == null) {
			this.setMessage("����Ϊ��");
			return false;
		}
		if (Arrays.asList(AppData.User_Nei).contains(user.getId())) {
			this.setMessage("�˺��Ѵ���");
			return false;
		}
		// �������
		user.setMima(SecurityUtil.encrypt(AppData.User_Password));
		boolean result = false;
		// ��ʼִ�����ݿ�
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			result = mapper.insert(user);
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
	public boolean edit(User user) {
		if (user == null) {
			this.setMessage("����Ϊ��");
			return false;
		}
		if (Arrays.asList(AppData.User_Nei).contains(user.getId())) {
			this.setMessage("�˺Ų����޸�");
			return false;
		}
		boolean result = false;
		// ��ʼִ�����ݿ�
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			result = mapper.update(user);
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
	 * �޸�/���� ����
	 * 
	 * @param user
	 * @return
	 */
	public boolean editMima(User user) {
		if (user == null) {
			this.setMessage("����Ϊ��");
			return false;
		} else {
			// ��������
			if (user.getMima() == null) {
				user.setMima(SecurityUtil.encrypt(AppData.User_Password));
			} else {
				// �޸�����
				user.setMima(SecurityUtil.encrypt(user.getMima()));
			}
		}
		if (Arrays.asList(AppData.User_Nei).contains(user.getId())) {
			this.setMessage("�˺Ų����޸�");
			return false;
		}
		boolean result = false;
		// ��ʼִ�����ݿ�
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			result = mapper.updateMima(user);
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
		if (Arrays.asList(AppData.User_Nei).contains(id)) {
			this.setMessage("�˺Ų���ɾ��");
			return false;
		}
		boolean result = false;
		// ��ʼִ�����ݿ�
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
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
	 * ����ɾ��
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteUsers(String id) {
		if (id == null) {
			this.setMessage("����Ϊ��");
			return false;
		}
		id = id.substring(0, id.length() - 1);
		String[] uId = id.split(",");
		List<String> list = Arrays.asList(uId);
		List<String> nei = Arrays.asList(AppData.User_Nei);
		// �����˺Ų���ɾ��
		for (int i = 0; i < list.size(); i++) {
			if (nei.contains(list.get(i))) {
				this.setMessage("�˺Ų���ɾ��");
				return false;
			}
		}
		boolean result = false;
		// ��ʼִ�����ݿ�
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			// ִ��ɾ��
			for (int i = 0; i < uId.length; i++) {
				result = mapper.delete(uId[i]);
			}
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
	 * ͳ������
	 * 
	 * @param v
	 * @return
	 */
	public int count(UserV v) {
		// ��ѯǰ׼��
		Map<String, Object> params = new HashMap<String, Object>();
		if (v != null) {
			if (v.getId() != null && !"".equals(v.getId())) {
				params.put("id", v.getId());
			}
		}
		// �����˺Ų��ܱ���ѯ
		StringBuilder idNotIn = new StringBuilder();
		if (AppData.User_Nei.length > 0) {
			idNotIn.append("'0'");
			for (String user : AppData.User_Nei) {
				idNotIn.append(",'").append(user).append("'");
			}
		}
		if (idNotIn.length() > 0) {
			params.put("id_not_in", idNotIn.toString());
		}
		// ��ʼ��ѯ���ݿ�
		int count = 0;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			count = mapper.selectCount(params);
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
		} finally {
			database.closeSession();
		}
		// ���ش���
		return count;
	}
	/**
	 * ��ȡ�û���ɫ
	 * 
	 * @param id
	 * @return
	 */
	public UserV getUserRole(String id) {
		if (Arrays.asList(AppData.User_Nei).contains(id)) {
			this.setMessage("ԽȨ��ѯ");
			return null;
		}
		// ��ʼ��ѯ���ݿ�
		UserV user = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			user = mapper.getUserRole(id);
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
		} finally {
			database.closeSession();
		}
		// ���ش���
		return user;
	}
	/**
	 * �û�������ɫ
	 * 
	 * @param uId,�û�id
	 *            rIds,��ɫidS
	 * @return
	 */
	public boolean userRole(String uId, String rIds) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uId", uId);
		if (uId == null) {
			this.setMessage("����Ϊ��");
			return false;
		}
		if (Arrays.asList(AppData.User_Nei).contains(uId)) {
			this.setMessage("�˺Ų����޸�");
			return false;
		}
		boolean result = false;
		// ��ʼִ�����ݿ�
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			// ��ɫΪ�գ�ɾ������
			if (rIds == null || rIds.equals("")) {
				result = mapper.emptyUserRole(uId);
			} else {
				// ��ɫ��Ϊ�գ�ɾ����������
				mapper.emptyUserRole(uId);
				// ȥ�����Ķ���
				rIds = rIds.substring(0, rIds.length() - 1);
				// ����ַ���
				String[] rId = rIds.split(",");
				for (int i = 0; i < rId.length; i++) {
					params.put("rId", rId[i]);
					result = mapper.changeUserRole(params);
				}
			}
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
	 * �����û�
	 * 
	 * @param path
	 * @return
	 */
	public boolean importUser(String path) {
		ImportUser util = new ImportUser();
		List<User> list = util.ReadXls(path);
		List<String> nei = Arrays.asList(AppData.User_Nei);
		// ȥ��excel���������û���ͻ��
		for (int i = 0; i < list.size(); i++) {
			if (nei.contains(list.get(i).getId())) {
				list.remove(i);
				i--;
			}
		}
		// ��ʼִ�����ݿ�
		boolean result = false;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			for (int i = 0; i < list.size(); i++) {
				User user = mapper.get(list.get(i).getId());
				if (user != null) {
					result = mapper.update(list.get(i));
				} else {
					result = mapper.insert(list.get(i));
				}
			}
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