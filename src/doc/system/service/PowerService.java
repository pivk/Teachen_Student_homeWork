package doc.system.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import doc.common.BaseService;
import doc.system.entity.Power;
import doc.system.entity.RolePower;
import doc.system.mapper.PowerMapper;
import doc.system.view.PowerTree;
import doc.system.view.PowerV;
import pushunsoft.database.MyBatis;
/**
 * 权限业务类
 * 
 * @author jerry
 *
 */
@Service
public class PowerService extends BaseService {
	/**
	 * 获取所有权限
	 * 
	 * @return
	 */
	public List<PowerV> getAll() {
		// 查询前准备
		Map<String, Object> params = new HashMap<String, Object>();
		// 开始查询数据库
		List<PowerV> list = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			PowerMapper mapper = session.getMapper(PowerMapper.class);
			list = mapper.selectAll(params);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return list;
	}
	/**
	 * 查询角色权限
	 * 
	 * @param roleId
	 * @return
	 */
	public List<PowerV> selectUserPower(String userId) {
		// 开始查询数据库
		List<PowerV> list = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			PowerMapper mapper = session.getMapper(PowerMapper.class);
			list = mapper.selectUserPower(userId);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return list;
	}
	/**
	 * 查询角色权限
	 * 
	 * @param roleId
	 * @return
	 */
	public List<PowerV> getRolePower(String roleId) {
		// 开始查询数据库
		List<PowerV> list = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			PowerMapper mapper = session.getMapper(PowerMapper.class);
			list = mapper.selectRolePower(roleId);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return list;
	}
	/**
	 * 保存角色权限
	 * 
	 * @param roleId
	 * @param powerList
	 * @return
	 */
	public boolean saveRolePower(String roleId, List<Power> powerList) {
		// 开始查询数据库
		boolean result = false;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			PowerMapper mapper = session.getMapper(PowerMapper.class);
			List<PowerV> allPowerList = mapper.selectAll(null);
			List<RolePower> toDoList = new ArrayList<RolePower>();
			for (Power power : powerList) {
				RolePower rp = new RolePower();
				rp.setPowerId(power.getId());
				rp.setRoleId(roleId);
				toDoList.add(rp);
				// 获取上级权限
				for (PowerV powerV : allPowerList) {
					if (powerV.getId() == null) {
						continue;
					}
					if ("Y".equals(powerV.getLeaf())) {
						powerV.setId(null);// 没用了，不要再比较了
						continue;
					}
					if (power.getId().startsWith(powerV.getId())) {
						rp = new RolePower();
						rp.setPowerId(powerV.getId());
						rp.setRoleId(roleId);
						toDoList.add(rp);
						powerV.setId(null);// 已经比较了，就不要再比较了
					}
				}
			}
			// 更新
			result = mapper.deleteRolePower(roleId);
			for (RolePower power : toDoList) {
				result = mapper.insertRolePower(power);
			}
			if (result) {
				session.commit();
			}
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return result;
	}
	/**
	 * 查询用户权限
	 * 
	 * @param roleId
	 * @return
	 */
	public List<PowerV> getUserPower(String userId) {
		// 开始查询数据库
		List<PowerV> list = null;
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			PowerMapper mapper = session.getMapper(PowerMapper.class);
			list = mapper.selectUserPower(userId);
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return list;
	}
	/**
	 * 创建多级树
	 * 
	 * @param powerList
	 * @param node
	 */
	private void CreateTree(List<PowerV> powerList, PowerTree node) {
		for (Power power : powerList) {
			if (power.getUpId().equals(node.getId())) {
				if (node.getNodes() == null) {
					node.setNodes(new ArrayList<PowerTree>());
				}
				PowerTree subNode = new PowerTree();
				subNode.setId(power.getId());
				subNode.setMingCheng(power.getMingCheng());
				subNode.setLink(power.getLink());
				node.getNodes().add(subNode);
				CreateTree(powerList, subNode);
			}
		}
	}
	/**
	 * 获取权限树
	 * 
	 * @return
	 */
	public PowerTree getTree() {
		List<PowerV> list = getAll();
		PowerTree tree = new PowerTree();
		tree.setId("0");
		tree.setMingCheng("根");
		CreateTree(list, tree);
		return tree;
	}
	/**
	 * 创建二级树，忽略中间节点
	 * 
	 */
	public PowerTree getTree2() {
		List<PowerV> powerList = getAll();
		PowerTree tree = new PowerTree();
		tree.setId("0");
		tree.setMingCheng("根");
		tree.setNodes(new ArrayList<PowerTree>());
		for (Power power : powerList) {
			if (power.getUpId().equals(tree.getId())) {
				PowerTree subNode = new PowerTree();
				subNode.setId(power.getId());
				subNode.setMingCheng(power.getMingCheng());
				subNode.setLink(power.getLink());
				tree.getNodes().add(subNode);
				for (Power power2 : powerList) {
					if (power2.getUpId().startsWith(subNode.getId()) && "Y".equals(power2.getLeaf())) {
						if (subNode.getNodes() == null) {
							subNode.setNodes(new ArrayList<PowerTree>());
						}
						PowerTree subNode2 = new PowerTree();
						subNode2.setId(power2.getId());
						subNode2.setMingCheng(power2.getMingCheng());
						subNode2.setLink(power2.getLink());
						subNode.getNodes().add(subNode2);
					}
				}
			}
		}
		return tree;
	}
}