package doc.system.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import doc.common.AppData;
import doc.common.BaseController;
import pushunsoft.common.JsonResult;
import pushunsoft.common.PageData;
import doc.common.annotation.LoginAnnotation;
import doc.system.entity.Log;
import doc.system.entity.User;
import doc.system.service.LogService;
import doc.system.service.UserService;
import doc.system.view.UserV;
/**
 * UserController
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {
	@Resource
	private UserService userService;
	@Resource
	private LogService logService;
	/**
	 * 添加用户
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/add")
	public Model add(HttpServletRequest request, Model model) {
		model.addAttribute("title", "添加用户");
		return model;
	}
	/**
	 * 导入用户
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/importUser")
	public Model importUser(HttpServletRequest request, Model model) {
		model.addAttribute("title", "导入用户");
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
		model.addAttribute("title", "修改用户");
		String id = request.getParameter("id");
		model.addAttribute("id", id);
		return model;
	}
	/**
	 * 关联角色
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/userRole")
	public Model userRole(HttpServletRequest request, Model model) {
		model.addAttribute("title", "用户关联角色");
		String id = request.getParameter("id");
		User user = userService.get(id);
		model.addAttribute("user", user);
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
		model.addAttribute("title", "查看用户");
		// 接收数据
		String id = request.getParameter("id");
		// 执行操作
		UserV user = userService.show(id);
		model.addAttribute("user", user);
		return model;
	}
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
		String action = "查询用户";
		model.addAttribute("title", action);
		return model;
	}
	@ResponseBody
	@RequestMapping("/doPage")
	public JsonResult doPage(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		UserV user = new UserV();
		String xingMing = request.getParameter("xingMing");
		if (xingMing != null && !xingMing.isEmpty()) {
			user.setXingMing(xingMing);
		}
		String unitId = request.getParameter("unitId");
		if (unitId != null && !"-".equals(unitId)) {
			user.setUnitId(unitId);
		}
		PageData<UserV> pageData = userService.getPage(page, user);
		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(pageData);
		return json;
	}
	@ResponseBody
	@RequestMapping("/doAdd")
	public JsonResult doAdd(HttpServletRequest request) {
		// 接收数据
		User user = new User();
		String id = request.getParameter("id");
		if (id != null && !id.isEmpty()) {
			user.setId(id);
		}
		String xingMing = request.getParameter("xingMing");
		if (xingMing != null && !xingMing.isEmpty()) {
			user.setXingMing(xingMing);
		}
		String mobile = request.getParameter("mobile");
		if (mobile != null && !mobile.isEmpty()) {
			user.setMobile(mobile);
		}
		String email = request.getParameter("email");
		if (email != null && !email.isEmpty()) {
			user.setEmail(email);
		}
		String unitId = request.getParameter("unitId");
		if (unitId != null && !"-".equals(unitId)) {
			user.setUnitId(unitId);
		}
		// 执行操作
		boolean result = userService.add(user);
		// 返回
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(userService.getMessage());
		return json;
	}
	@ResponseBody
	@RequestMapping("/doGet")
	public JsonResult doGet(HttpServletRequest request) {
		// 接收数据
		String id = request.getParameter("id");
		// 执行操作
		User user = userService.get(id);
		// 返回
		JsonResult json = new JsonResult();
		if (user == null)
			json.setState(false);
		else
			json.setState(true);
		json.setMessage(userService.getMessage());
		json.setData(user);
		return json;
	}
	@ResponseBody
	@RequestMapping("/getRole")
	public JsonResult getRole(HttpServletRequest request) {
		// 接收数据
		String id = request.getParameter("id");
		// 执行操作
		UserV user = userService.getUserRole(id);
		// 返回
		JsonResult json = new JsonResult();
		if (user == null)
			json.setState(false);
		else
			json.setState(true);
		json.setMessage(userService.getMessage());
		json.setData(user);
		return json;
	}
	@ResponseBody
	@RequestMapping("/doEdit")
	public JsonResult doEdit(HttpServletRequest request) {
		// 接收数据
		User user = new User();
		String id = request.getParameter("id");
		if (id != null && !id.isEmpty()) {
			user.setId(id);
		}
		String xingMing = request.getParameter("xingMing");
		if (xingMing != null && !xingMing.isEmpty()) {
			user.setXingMing(xingMing);
		}
		String mobile = request.getParameter("mobile");
		if (mobile != null && !mobile.isEmpty()) {
			user.setMobile(mobile);
		}
		String email = request.getParameter("email");
		if (email != null && !email.isEmpty()) {
			user.setEmail(email);
		}
		String unitId = request.getParameter("unitId");
		if (unitId != null && !"-".equals(unitId)) {
			user.setUnitId(unitId);
		}
		// 执行操作
		boolean result = userService.edit(user);
		// 返回
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(userService.getMessage());
		return json;
	}
	@ResponseBody
	@RequestMapping("/doRemove")
	public JsonResult doRemove(HttpServletRequest request) {
		// 接收数据
		String id = request.getParameter("id");
		// 执行操作
		boolean result = userService.remove(id);
		// 返回
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(userService.getMessage());
		return json;
	}
	@ResponseBody
	@RequestMapping("/importUserExcel")
	public JsonResult importUser(HttpServletRequest request) {
		// 接收数据
		String path = request.getParameter("path");
		// 执行操作
		boolean result = userService.importUser(path);
		// 返回
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(userService.getMessage());
		return json;
	}
	@ResponseBody
	@RequestMapping("/deleteUsers")
	public JsonResult deleteUsers(HttpServletRequest request) {
		// 接收数据
		String id = request.getParameter("id");
		// 执行操作
		boolean result = userService.deleteUsers(id);
		// 返回
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(userService.getMessage());
		return json;
	}
	@ResponseBody
	@RequestMapping("/setUserRole")
	public JsonResult setUserRole(HttpServletRequest request) {
		// 接收数据
		String id = request.getParameter("id");
		String rId = request.getParameter("roleId");
		// 执行操作
		boolean result = userService.userRole(id, rId);
		// 返回
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(userService.getMessage());
		return json;
	}
	@ResponseBody
	@RequestMapping("/doReset")
	public JsonResult resetPassword(HttpServletRequest request) {
		// 接收数据
		String id = request.getParameter("id");
		UserV user = new UserV();
		user.setId(id);
		// 执行操作
		boolean result = userService.editMima(user);
		// 返回
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(userService.getMessage());
		return json;
	}
}