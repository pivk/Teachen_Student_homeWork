package doc.home.service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import doc.common.AppData;
import doc.common.BaseService;
import doc.home.view.Menu;
import doc.system.entity.Parameter;
import doc.system.entity.Power;
import doc.system.entity.User;
import doc.system.mapper.ParameterMapper;
import doc.system.mapper.PowerMapper;
import doc.system.mapper.UserMapper;
import doc.system.view.PowerV;
import doc.util.SecurityUtil;
import pushunsoft.database.MyBatis;
/**
 * ��ҳ��
 * 
 * @author jerry
 *
 */
@Service
public class HomeService extends BaseService {
	/**
	 * ��½
	 * 
	 * @param id
	 * @return
	 */
	public User login(String uid, String pwd) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", uid);
		params.put("mima", SecurityUtil.encrypt(pwd));
		// ��ʼ��ѯ���ݿ�
		User user = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			user = mapper.login(params);
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
			System.out.println(ex);
		} finally {
			database.closeSession();
		}
		// ���ش���
		return user;
	}
	/**
	 * �����˵�
	 * 
	 * @param powerList
	 * @param menu
	 */
	private void CreateMenu(List<PowerV> powerList, Menu menu) {
		for (Power power : powerList) {
			if (power.getUpId().equals(menu.getId())) {
				if (menu.getNodes() == null) {
					menu.setNodes(new ArrayList<Menu>());
				}
				Menu subMenu = new Menu();
				subMenu.setId(power.getId());
				subMenu.setText(power.getMingCheng());
				subMenu.setUrl(power.getLink());
				subMenu.setIcon(power.getIcon());
				menu.getNodes().add(subMenu);
				CreateMenu(powerList, subMenu);
			}
		}
	}
	/**
	 * ���ɲ˵�
	 * 
	 * @param uid
	 * @return
	 */
	public List<Menu> MakeMenu(String uid) {
		if (uid == null || uid.isEmpty())
			return null;
		List<PowerV> powerList = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			PowerMapper mapper = session.getMapper(PowerMapper.class);
			if (!uid.equals(AppData.User_Admin)) {
				powerList = mapper.selectUserPower(uid);
			} else {
				powerList = mapper.selectAll(null);
			}
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
		} finally {
			database.closeSession();
		}
		if (powerList == null || powerList.size() == 0)
			return null;
		// ���ش���
		List<Menu> menuList = new ArrayList<Menu>();
		for (PowerV power : powerList) {
			if (!power.getUpId().equals("0"))
				continue;
			Menu menu = new Menu();
			menu.setId(power.getId());
			menu.setText(power.getMingCheng());
			menu.setUrl(power.getLink());
			menuList.add(menu);
			CreateMenu(powerList, menu);
		}
		return menuList;
	}
	/**
	 * ��ȡһ��
	 * 
	 * @param id
	 * @return
	 */
	public User get(String id) {
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
			// �޸�����
			user.setMima(SecurityUtil.encrypt(user.getMima()));
			// �������
			user.setMima(SecurityUtil.encrypt(AppData.User_Password));
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
	 * ����Ƥ��
	 * 
	 * @param userId
	 *            �û�ID
	 * @param skin
	 *            Ƥ��ID
	 * @return
	 */
	public boolean changeSkin(String id, String skin) {
		//update cr_user set skin=skin where ID=userId
		User user=new User();
		if (id == null) {
			this.setMessage("�û�����Ϊ��");
			return false;
		}
		if (skin == null) {
			this.setMessage("Ƥ������Ϊ��");
			return false;
		}
		boolean result = false;
		if (id != null && !"".equals(id)) {
			user.setId(id);
		}
		if (skin != null && !"".equals(skin)) {
			user.setSkin(skin);
		}
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
	public List<Parameter> getList(Parameter v) {
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
}