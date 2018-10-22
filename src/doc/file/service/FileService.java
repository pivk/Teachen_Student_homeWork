package doc.file.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import doc.common.AppData;
import doc.common.BaseService;
import doc.file.entity.File;
import doc.file.mapper.FileMapper;
import doc.file.mapper.TreeMapper;
import doc.file.view.FileV;
import doc.information.mappper.DoorMapper;
import doc.util.SeqUtil;
import pushunsoft.common.PageData;
import pushunsoft.database.MyBatis;

/**
 * 
 * @author jerry
 *
 */
@Service
public class FileService extends BaseService {

	
	
	
	
	
	
	 /**
	  * 压缩文件
	 * @param id
	 * @param mingCheng
	 * @param status 
	 * @return
	 */
	public java.io.File getFiles(String id,String mingCheng){
		// 开始查询数据库
		    String targetDir=AppData.Config.getUploadPath()+doc.common.AppData.FILECOMPRESS+java.io.File.separator;
		    java.io.File dirFile = new java.io.File(targetDir);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
		    java.io.File file=new java.io.File(targetDir+mingCheng+".zip");
		    MyBatis database = getDatabase();
			SqlSession session = database.openSession();
			try {
				FileMapper mapper = session.getMapper(FileMapper.class);
				String[] ids=id.split(",");
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("ids", ids);
				List<File> list=mapper.showFile(params);
				String[] str=new String[list.size()];
				java.io.File [] files=new java.io.File[list.size()];
				for(int i=0;i<list.size();i++){
					//String realPath = AppData.Config.getUploadPath() + list.get(i).getRealPath();
					str[i]=list.get(i).getMingCheng();
					//java.io.File fileOne=new java.io.File(realPath);
					files[i] =new java.io.File(AppData.Config.getUploadPath() +list.get(i).getRealPath());
				}
				zipFiles(files,file,str);	
			} catch (Exception ex) {	
				this.setMessage("操作失败");
			} finally {
				database.closeSession();
			}
			// 返回处理
		 return file;
		 
	 }
	
	public  void zipFiles(java.io.File[] srcfile,java.io.File zipfile,String[] str){
	    byte[] buf=new byte[1024];
	    try {
	      //ZipOutputStream类：完成文件或文件夹的压缩
	      ZipOutputStream out=new ZipOutputStream(new FileOutputStream(zipfile));
	      for(int i=0;i<srcfile.length;i++){
	        FileInputStream in=new FileInputStream(srcfile[i]);
	        out.putNextEntry(new ZipEntry(str[i]));
	        int len;
	        while((len=in.read(buf))>0){
	          out.write(buf,0,len);
	        }
	        out.closeEntry();
	        in.close();
	      }
	      out.close();
	   
	    } catch (Exception e) {
	    	this.setMessage("操作失败");
	    }
	  }	
	/**
	 * 
	 * @return
	 */
	private String getId() {
		SeqUtil seq = new SeqUtil();
		return seq.getId();
	}

	/**
	 * 
	 * @return
	 */
	public String getFileName(String module,String project) {
		return "test";
	}

	/**
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
	 * 
	 * @param page
	 * @param v
	 * @return
	 */
	public PageData<FileV> getPage(Integer page, FileV v) {
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
			if (v.getUnitId() != null && !v.getUnitId().isEmpty()) {
				params.put("unitId",  v.getUnitId().trim() );
			}
			if (v.getDirectoryId() != null && !v.getDirectoryId().isEmpty()) {
				params.put("directoryId",   v.getDirectoryId().trim());
			}
		}
		PageData<FileV> pageData = new PageData<FileV>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			FileMapper mapper = session.getMapper(FileMapper.class);
			pageData.setTotal(mapper.selectCount(params));
			pageData.setData(mapper.selectPage(params));
		} catch (Exception ex) {
			this.setMessage("数据异常");
		} finally {
			database.closeSession();
		}
		return pageData;
	}
	/**
	 * 
	 * @param page
	 * @param v
	 * @return
	 */
	public PageData<FileV> getstudentPage(Integer page, FileV v) {
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
		PageData<FileV> pageData = new PageData<FileV>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			FileMapper mapper = session.getMapper(FileMapper.class);
			pageData.setTotal(mapper.selectCount(params));
			pageData.setData(mapper.selectPage(params));
		} catch (Exception ex) {
			this.setMessage("数据异常");
		} finally {
			database.closeSession();
		}
		return pageData;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public File get(String id) {
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
		return parameter;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public FileV show(String id) {
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
		return parameter;
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	/**
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
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			/*TreeMapper treeMapper = session.getMapper(TreeMapper.class);
			result = treeMapper.insert(entity);
			if (!result) {
				session.rollback();
				this.setMessage("数据错误");
				return false;
			}
			Directory directory=new Directory();
			directory.setUserId(entity.getCreator());
			directory.setId(entity.getId());
			result = treeMapper.insertTree(directory);*/
			FileMapper FileMapper = session.getMapper(FileMapper.class);
			result = FileMapper.insert(entity);
			if (result) {
				session.commit();
			}else{
				session.rollback();
				this.setMessage("操作失败");
				return false;
			}
			
			
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("操作失败");
			result = false;
		} finally {
			database.closeSession();
		}
		return result;
	}

	/**
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
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			FileMapper mapper = session.getMapper(FileMapper.class);
			result = mapper.update(entity);
			if (result) {
				session.commit();
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("更新异常");
			result = false;
		} finally {
			database.closeSession();
		}
		return result;
	}

	/**
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
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			TreeMapper treeMapper = session.getMapper(TreeMapper.class);
			result = treeMapper.delete(id);
			if (!result) {
				session.rollback();
				this.setMessage("删除错误");
				return false;
			}
			FileMapper mapper = session.getMapper(FileMapper.class);
			result = mapper.delete(id);
			if (!result) {
				session.rollback();
				this.setMessage("删除错误");
				return false;
			}
			session.commit();
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("删除错误");
			result = false;
		} finally {
			database.closeSession();
		}
		return result;
	}
}