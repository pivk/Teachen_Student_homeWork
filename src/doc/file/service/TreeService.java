package doc.file.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import doc.common.BaseService;
import doc.file.entity.Tree;
import doc.file.mapper.TreeMapper;
import doc.file.view.TreeV;
import pushunsoft.common.PageData;
import pushunsoft.database.MyBatis;

/**
 * ��ҵ����
 * 
 * @author jerry
 *
 */
@Service
public class TreeService extends BaseService {
	public void getTree(String id, TreeMapper mapper, List<String> list) {
		Tree entity = mapper.get(id);
		if (entity == null)
			return;
		list.add("<li><a href=\"javascript:showDirectory('" + id + "')\">" + entity.getMingCheng() + "</a></li>");
		getTree(entity.getParentId(), mapper, list);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public String getNav(String id) {
		List<String> list = new ArrayList<String>();
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			TreeMapper mapper = session.getMapper(TreeMapper.class);
			Tree entity = mapper.get(id);
			if (entity != null) {
				list.add("<li class=\"am-active\">" + entity.getMingCheng() + "</li>");
				getTree(entity.getParentId(), mapper, list);
			}
		} catch (Exception ex) {
			this.setMessage("操作异常");
		} finally {
			database.closeSession();
		}
		list.add("<li><a href=\"javascript:showDirectory('0')\">首页</a></li>");
		StringBuilder navStr = new StringBuilder();
		if (list.size() > 0) {
			Collections.reverse(list);
			for (String str : list) {
				navStr.append(str);
			}
		}
		return navStr.toString();
	}

	/**
	 * 分页
	 * 
	 * @param page
	 * @param v
	 * @return
	 */
	public PageData<TreeV> getPage(Integer page, TreeV v) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		int begin = (page - 1) * this.getPageSize();
		params.put("begin", begin);
		params.put("end", begin + this.getPageSize());
		if (v != null) {
			if (v.getMingCheng() != null && !v.getMingCheng().isEmpty()) {
				params.put("mingCheng", "%" + v.getMingCheng().trim() + "%");
			}
			if (v.getParentId() != null && !v.getParentId().isEmpty()) {
				params.put("parentId", v.getParentId().trim());
			}
			if (v.getXueqi() != null && !v.getXueqi().isEmpty()) {
				params.put("xueqi", v.getXueqi().trim());
			}
			if (v.getCreator() != null && !v.getCreator().isEmpty()) {
				params.put("creator", v.getCreator().trim());
			}
			
		}
		PageData<TreeV> pageData = new PageData<TreeV>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			TreeMapper mapper = session.getMapper(TreeMapper.class);
			pageData.setTotal(mapper.selectCount(params));
			pageData.setData(mapper.selectPage(params));
		} catch (Exception ex) {
			this.setMessage("操作异常");
		} finally {
			database.closeSession();
		}
		return pageData;
	}
	
	/**
	 * 分页
	 * 
	 * @param page
	 * @param v
	 * @return
	 */
	public PageData<TreeV> getStudentPage(Integer page, TreeV v) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		int begin = (page - 1) * this.getPageSize();
		params.put("begin", begin);
		params.put("end", begin + this.getPageSize());
		if (v != null) {
			if (v.getMingCheng() != null && !v.getMingCheng().isEmpty()) {
				params.put("mingCheng", "%" + v.getMingCheng().trim() + "%");
			}
			if (v.getParentId() != null && !v.getParentId().isEmpty()) {
				params.put("parentId", v.getParentId().trim());
			}
			if (v.getXueqi() != null && !v.getXueqi().isEmpty()) {
				params.put("xueqi", v.getXueqi().trim());
			}
			if (v.getCreator() != null && !v.getCreator().isEmpty()) {
				params.put("creator", v.getCreator().trim());
			}
		}
		PageData<TreeV> pageData = new PageData<TreeV>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			TreeMapper mapper = session.getMapper(TreeMapper.class);
			pageData.setTotal(mapper.selectStudentCount(params));
			pageData.setData(mapper.selectStudentPage(params));
		} catch (Exception ex) {
			System.out.println(ex);
			this.setMessage("操作异常");
		} finally {
			database.closeSession();
		}
		return pageData;
	}

	/**
	 * ��ȡһ��
	 * 
	 * @param id
	 * @return
	 */
	public Tree get(String id) {
		Tree entity = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			TreeMapper mapper = session.getMapper(TreeMapper.class);
			entity = mapper.get(id);
		} catch (Exception ex) {
			this.setMessage("操作异常");
		} finally {
			database.closeSession();
		}
		return entity;
	}

	/**
	 * ��ȡһ��
	 * 
	 * @param id
	 * @return
	 */
	public TreeV show(String id) {
		// ��ʼ��ѯ���ݿ�
		TreeV entity = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			TreeMapper mapper = session.getMapper(TreeMapper.class);
			entity = mapper.show(id);
		} catch (Exception ex) {
			this.setMessage("�����쳣");
		} finally {
			database.closeSession();
		}
		// ���ش���
		return entity;
	}
}