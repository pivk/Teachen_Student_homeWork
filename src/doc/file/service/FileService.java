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
 * 文件业务类
 * 
 * @author jerry
 *
 */
@Service
public class FileService extends BaseService {
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
	 * 生成新文件名
	 * 
	 * @return
	 */
	public String getFileName(String module,String project) {
		return "test";
	}

	/**
	 * 提取文件类型
	 * 
	 * @param fileName
	 * @return
	 */
	public String getFileType(String fileName) {
		if (fileName == null || fileName.isEmpty()) {
			return "未知";
		}
		int dot = fileName.lastIndexOf('.');
		if ((dot > -1) && (dot < (fileName.length() - 1))) {
			return fileName.substring(dot + 1);
		} else
			return "未知";
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param v
	 * @return
	 */
	public PageData<FileV> getPage(Integer page, FileV v) {
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
		PageData<FileV> pageData = new PageData<FileV>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			FileMapper mapper = session.getMapper(FileMapper.class);
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
	public File get(String id) {
		// 开始查询数据库
		File parameter = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			FileMapper mapper = session.getMapper(FileMapper.class);
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
	public FileV show(String id) {
		// 开始查询数据库
		FileV parameter = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			FileMapper mapper = session.getMapper(FileMapper.class);
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
	public boolean add(File entity) {
		if (entity == null) {
			this.setMessage("数据为空");
			return false;
		}
		entity.setId(getId());
		entity.setLei(AppData.Tree_Type_File);
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
			FileMapper FileMapper = session.getMapper(FileMapper.class);
			result = FileMapper.insert(entity);
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
	public boolean edit(File entity) {
		if (entity == null) {
			this.setMessage("数据为空");
			return false;
		}
		boolean result = false;
		// 开始执行数据库
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			FileMapper mapper = session.getMapper(FileMapper.class);
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
			FileMapper mapper = session.getMapper(FileMapper.class);
			result = mapper.delete(id);
			if (!result) {
				session.rollback();
				this.setMessage("操作文件失败");
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