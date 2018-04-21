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
import doc.system.entity.Unit;
import doc.system.mapper.UnitMapper;
import doc.system.view.ImportUnit;
import doc.system.view.UnitV;
import pushunsoft.database.MyBatis;
/**
 * ����ҵ����
 * 
 * @author jerry
 *
 */
@Service
public class UnitService extends BaseService {
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
	public List<UnitV> getList(UnitV v) {
		// ��ѯǰ׼��
		Map<String, Object> params = new HashMap<String, Object>();
		// ��ʼ��ѯ���ݿ�
		List<UnitV> list= null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UnitMapper mapper = session.getMapper(UnitMapper.class);
			list=mapper.selectAll(params);
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
		} finally {
			database.closeSession();
		}
		// ���ش���
		return list;
	}
	public PageData<UnitV> getPage(Integer page, UnitV v) {
		// ��ѯǰ׼��
		Map<String, Object> params = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		int begin = (page - 1) * this.getPageSize();
		params.put("begin", begin);
		params.put("PageSize", this.getPageSize());
		if (v != null) {
			if (v.getMingCheng() != null && !"".equals(v.getMingCheng())) {
				params.put("mingCheng", "%" + v.getMingCheng().trim() + "%");
			}
		}
		// ��ʼ��ѯ���ݿ�
		PageData<UnitV> pageData = new PageData<UnitV>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UnitMapper mapper = session.getMapper(UnitMapper.class);
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
	public Unit get(String id) {
		// ��ʼ��ѯ���ݿ�
		Unit unit = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UnitMapper mapper = session.getMapper(UnitMapper.class);
			unit = mapper.get(id);
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
		} finally {
			database.closeSession();
		}
		// ���ش���
		return unit;
	}
	/**
	 * ��ȡһ��
	 * 
	 * @param id
	 * @return
	 */
	public UnitV show(String id) {
		if (Arrays.asList(AppData.User_Nei).contains(id)) {
			this.setMessage("ԽȨ��ѯ");
			return null;
		}
		// ��ʼ��ѯ���ݿ�
		UnitV unit = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UnitMapper mapper = session.getMapper(UnitMapper.class);
			unit = mapper.show(id);
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
		} finally {
			database.closeSession();
		}
		// ���ش���
		return unit;
	}
	/**
	 * �������
	 * 
	 * @param path
	 * @return
	 */
	public boolean importUnit(String path) {
		ImportUnit util = new doc.system.view.ImportUnit();
		List<Unit> list = util.ReadXls(path);
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
			UnitMapper mapper = session.getMapper(UnitMapper.class);
			for (int i = 0; i < list.size(); i++) {
				Unit unit = mapper.get(list.get(i).getId());
				if (unit != null) {
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
	/**
	 * ���
	 * 
	 * @param user
	 * @return
	 */
	public boolean add(Unit unit) {
		if (unit == null) {
			this.setMessage("����Ϊ��");
			return false;
		}
		unit.setId(getId());
		boolean result=false;
		// ��ʼִ�����ݿ�
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UnitMapper mapper = session.getMapper(UnitMapper.class);
			result = mapper.insert(unit);
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
	public boolean edit(Unit entity) {
		if (entity == null) {
			this.setMessage("����Ϊ��");
			return false;
		}
		boolean result = false;
		// ��ʼִ�����ݿ�
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UnitMapper mapper = session.getMapper(UnitMapper.class);
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
		boolean result=delUnit(id);
		// ���ش���
		return result;
	}
	/**
	 * ����id���¼�ȫ��ɾ�� 
	 * @param id
	 * @return
	 */
	public boolean delUnit(String id){
		boolean result = false;
		// ��ʼִ�����ݿ�
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			UnitMapper mapper = session.getMapper(UnitMapper.class);
			String chilId=mapper.getChilId(id);
			if(chilId!=null){
				delUnit(chilId);
			}
			result=mapper.delete(id);
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