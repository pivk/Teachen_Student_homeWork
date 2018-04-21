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
 * 目录业务类
 * 
 * @author jerry
 *
 */
@Service
public class DirectoryService extends BaseService {
	/**
	 * 生成ID
	 * 
	 * @return
	 */
	private String getId() {
		SeqUtil seq = new SeqUtil();
		return seq.getId();
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param v
	 * @return
	 */
	public PageData<DirectoryV> getPage(Integer page, DirectoryV v) {
		// 查询前准备
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
		// 开始查询数据库
		PageData<DirectoryV> pageData = new PageData<DirectoryV>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DirectoryMapper mapper = session.getMapper(DirectoryMapper.class);
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
	public Directory get(String id) {
		// 开始查询数据库
		Directory parameter = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DirectoryMapper mapper = session.getMapper(DirectoryMapper.class);
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
	public DirectoryV show(String id) {
		// 开始查询数据库
		DirectoryV parameter = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DirectoryMapper mapper = session.getMapper(DirectoryMapper.class);
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
	 * @param entity
	 * @return
	 */
	public boolean add(Directory entity) {
		if (entity == null) {
			this.setMessage("数据为空");
			return false;
		}
		entity.setId(getId());
		entity.setLei(AppData.Tree_Type_Directory);
		entity.setIcon("folder");
		boolean result = false;
		// 开始执行数据库
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			TreeMapper treeMapper = session.getMapper(TreeMapper.class);
			result = treeMapper.insert(entity);
			if (!result) {
				session.rollback();
				this.setMessage("操作树失败");
				return false;
			}
			DirectoryMapper directoryMapper = session.getMapper(DirectoryMapper.class);
			result = directoryMapper.insert(entity);
			if (!result) {
				session.rollback();
				this.setMessage("操作目录失败");
				return false;
			}
			// 真正入库
			session.commit();
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("操作异常");
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
	public boolean edit(Directory entity) {
		if (entity == null) {
			this.setMessage("数据为空");
			return false;
		}
		boolean result = false;
		// 开始执行数据库
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			DirectoryMapper mapper = session.getMapper(DirectoryMapper.class);
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
			TreeMapper treeMapper = session.getMapper(TreeMapper.class);
			result = treeMapper.delete(id);
			if (!result) {
				session.rollback();
				this.setMessage("操作树失败");
				return false;
			}
			DirectoryMapper mapper = session.getMapper(DirectoryMapper.class);
			result = mapper.delete(id);
			if (!result) {
				session.rollback();
				this.setMessage("操作目录失败");
				return false;
			}
			// 真正入库
			session.commit();
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("操作异常");
			result = false;
		} finally {
			database.closeSession();
		}
		// 返回处理
		return result;
	}
}