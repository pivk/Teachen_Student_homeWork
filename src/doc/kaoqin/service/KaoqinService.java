package doc.kaoqin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import doc.common.BaseService;
import doc.information.mappper.DoorMapper;
import doc.kaoqin.entity.Kaoqin;
import doc.kaoqin.mapper.KaoqinMapper;
import doc.kaoqin.view.KaoqinV;
import doc.system.mapper.CourseMapper;
import doc.system.view.CourseV;
import doc.util.SeqUtil;
import pushunsoft.common.PageData;
import pushunsoft.database.MyBatis;

/**
 * 
 * @author jerry
 *
 */
@Service
public class KaoqinService extends BaseService {
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	public boolean add(CourseV entity) {
		
		boolean result = false;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			 CourseMapper courseMapper = session.getMapper(CourseMapper.class);
			 String userId1=entity.getUserId().substring(0, entity.getUserId().length()-1);
			 String zhi1=entity.getStr().substring(0, entity.getStr().length()-1);
				String[] userId = userId1.split(",");
				String[] zhi =zhi1.split(",");
				if (userId.length != zhi.length) {
					return false;
				}
				for (int i = 0; i < userId.length; i++) {
					CourseV courseV = new CourseV();
					courseV.setCourseId(entity.getCourseId());
					courseV.setUserId(userId[i]);
					switch (zhi[i]) {
						case ("1"):courseV.setLate(zhi[i]);break;
						case ("2"):courseV.setCrunk(zhi[i]);break;
						case ("3"):courseV.setNormal(zhi[i]);break;
					default:
						break;
					}
					if(zhi[i].equals("undefined")) continue;
					result = courseMapper.updateUSer(courseV);
					if (!result) {
						session.rollback();
						this.setMessage("操作文件失败");
						return false;
					}
				}
			if (result) {
				session.commit();
			}
		} catch (Exception ex) {
			System.out.println(ex);
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
	public boolean edit(Kaoqin entity) {
		if (entity == null) {
			this.setMessage("数据为空");
			return false;
		}
		boolean result = false;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			KaoqinMapper mapper = session.getMapper(KaoqinMapper.class);
			result = mapper.update(entity);
			if (result) {
				session.commit();
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("操作异常");
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
//			TreeMapper treeMapper = session.getMapper(TreeMapper.class);
//			result = treeMapper.delete(id);
//			if (!result) {
//				session.rollback();
//				this.setMessage("a");
//				return false;
//			}
			KaoqinMapper mapper = session.getMapper(KaoqinMapper.class);
			result = mapper.delete(id);
			if (!result) {
				session.rollback();
				this.setMessage("操作失败");
				return false;
			}
			session.commit();
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("操作失败");
			result = false;
		} finally {
			database.closeSession();
		}
		return result;
	}
}