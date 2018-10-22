package doc.system.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import doc.common.AppData;
import doc.common.BaseController;
import doc.common.annotation.LoginAnnotation;
import doc.home.view.Menu;
import doc.system.entity.Log;
import doc.system.entity.Role;
import doc.system.entity.User;
import doc.system.service.LogService;
import doc.system.service.PowerService;
import doc.system.service.RoleService;
import doc.system.view.RoleV;
import pushunsoft.common.JsonResult;
import pushunsoft.common.PageData;
/**
 * LogController
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {
	@Resource
	private RoleService roleService;
	@Resource
	private PowerService powerService;
	@Resource
	private LogService logService;
	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 分页查询
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/page")
	public Model page(HttpServletRequest request, Model model) {
		String action = "查询角色";
		model.addAttribute("title", action);
		return model;
	}
	/**
	 * 添加
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/add")
	public Model add(HttpServletRequest request, Model model) {
		model.addAttribute("title", "添加角色");
		return model;
	}
	/**
	 * 编辑
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/edit")
	public Model edit(HttpServletRequest request, Model model) {
		model.addAttribute("title", "修改角色");
		String id = request.getParameter("id");
		model.addAttribute("id", id);
		return model;
	}
	/**
	 * 查看
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/show")
	public Model show(HttpServletRequest request, Model model) {
		model.addAttribute("title", "查看角色");
		// 接收数据
		String id = request.getParameter("id");
		// 执行操作
		Role entity = roleService.get(id);
		model.addAttribute("entity", entity);
		return model;
	}
	/**
	 * 角色权限
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/power")
	public Model power(HttpServletRequest request, Model model) {
		model.addAttribute("title", "角色权限");
		// 接收数据
		// 接收数据
		String id = request.getParameter("id");
		model.addAttribute("id", id);
		List<Menu> menuList=powerService.MakeMenu();
		ObjectMapper mapper = new ObjectMapper();
		String menuJson = "[]";
		try {
			menuJson = mapper.writeValueAsString(menuList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		model.addAttribute("tree", menuJson);
		return model;
	}
	/**
	 * 分页查询角色
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doPage")
	public JsonResult doPage(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		RoleV v = new RoleV();
		PageData<Role> pageData = roleService.getPage(page, v);
		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(pageData);
		return json;
	}
	/**
	 * 查询所有角色
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/roleList")
	public JsonResult roleList(HttpServletRequest request) {
		List<Role> list = roleService.getList();
		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(list);
		return json;
	}
	@ResponseBody
	@RequestMapping("/doAdd")
	public JsonResult doAdd(HttpServletRequest request) {
		// 接收数据
		Role entity = new Role();
		String mingCheng = request.getParameter("mingCheng");
		if (mingCheng != null && !mingCheng.isEmpty()) {
			entity.setMingCheng(mingCheng);
		}
		// 执行操作
		boolean result = roleService.add(entity);
		// 返回
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(roleService.getMessage());
		if(result){
			Log log = new Log();
			log.setNeiRong(mingCheng);
			log.setBiaoTi(entity.getId()+"添加角色");
			User user1 = (User) request.getSession().getAttribute(AppData.Session_User);
			log.setUserId(user1.getId());
			Date date2 =new Date();
			log.setTimestamp(dateFormatter.format(date2));
			logService.add(log);
		}
		return json;
	}
	@ResponseBody
	@RequestMapping("/doGet")
	public JsonResult doGet(HttpServletRequest request) {
		// 接收数据
		String id = request.getParameter("id");
		// 执行操作
		Role entity = roleService.get(id);
		// 返回
		JsonResult json = new JsonResult();
		if (entity == null)
			json.setState(false);
		else
			json.setState(true);
		json.setMessage(roleService.getMessage());
		json.setData(entity);
		return json;
	}
	@ResponseBody
	@RequestMapping("/doEdit")
	public JsonResult doEdit(HttpServletRequest request) {
		// 接收数据
		Role entity = new Role();
		String id = request.getParameter("id");
		if (id != null && !id.isEmpty()) {
			entity.setId(id);
		}
		String mingCheng = request.getParameter("mingCheng");
		if (mingCheng != null && !mingCheng.isEmpty()) {
			entity.setMingCheng(mingCheng);
		}
		// 执行操作
		boolean result = roleService.edit(entity);
		// 返回
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(roleService.getMessage());
		if(result){
			Log log = new Log();
			log.setNeiRong(mingCheng);
			log.setBiaoTi(id+"修改角色");
			User user1 = (User) request.getSession().getAttribute(AppData.Session_User);
			log.setUserId(user1.getId());
			Date date2 =new Date();
			log.setTimestamp(dateFormatter.format(date2));
			logService.add(log);
		}
		return json;
	}
	@ResponseBody
	@RequestMapping("/doRemove")
	public JsonResult doRemove(HttpServletRequest request) {
		// 接收数据
		String id = request.getParameter("id");
		// 执行操作
		boolean result = roleService.remove(id);
		// 返回
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(roleService.getMessage());
		if(result){
			Role entity =new Role();
			Log log = new Log();
			log.setNeiRong(entity.getMingCheng());
			log.setBiaoTi(id+"删除角色");
			User user1 = (User) request.getSession().getAttribute(AppData.Session_User);
			log.setUserId(user1.getId());
			Date date2 =new Date();
			log.setTimestamp(dateFormatter.format(date2));
			logService.add(log);
		}
		return json;
	}
}