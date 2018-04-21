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
	 * ����û�
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/add")
	public Model add(HttpServletRequest request, Model model) {
		model.addAttribute("title", "����û�");
		return model;
	}
	/**
	 * �����û�
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/importUser")
	public Model importUser(HttpServletRequest request, Model model) {
		model.addAttribute("title", "�����û�");
		return model;
	}
	/**
	 * �༭
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/edit")
	public Model edit(HttpServletRequest request, Model model) {
		model.addAttribute("title", "�޸��û�");
		String id = request.getParameter("id");
		model.addAttribute("id", id);
		return model;
	}
	/**
	 * ������ɫ
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/userRole")
	public Model userRole(HttpServletRequest request, Model model) {
		model.addAttribute("title", "�û�������ɫ");
		String id = request.getParameter("id");
		User user = userService.get(id);
		model.addAttribute("user", user);
		return model;
	}
	/**
	 * �鿴
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/show")
	public Model show(HttpServletRequest request, Model model) {
		model.addAttribute("title", "�鿴�û�");
		// ��������
		String id = request.getParameter("id");
		// ִ�в���
		UserV user = userService.show(id);
		model.addAttribute("user", user);
		return model;
	}
	/**
	 * ��ҳ��ѯ
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/page")
	public Model page(HttpServletRequest request, Model model) {
		String action = "��ѯ�û�";
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
		// ��������
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
		// ִ�в���
		boolean result = userService.add(user);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(userService.getMessage());
		return json;
	}
	@ResponseBody
	@RequestMapping("/doGet")
	public JsonResult doGet(HttpServletRequest request) {
		// ��������
		String id = request.getParameter("id");
		// ִ�в���
		User user = userService.get(id);
		// ����
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
		// ��������
		String id = request.getParameter("id");
		// ִ�в���
		UserV user = userService.getUserRole(id);
		// ����
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
		// ��������
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
		// ִ�в���
		boolean result = userService.edit(user);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(userService.getMessage());
		return json;
	}
	@ResponseBody
	@RequestMapping("/doRemove")
	public JsonResult doRemove(HttpServletRequest request) {
		// ��������
		String id = request.getParameter("id");
		// ִ�в���
		boolean result = userService.remove(id);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(userService.getMessage());
		return json;
	}
	@ResponseBody
	@RequestMapping("/importUserExcel")
	public JsonResult importUser(HttpServletRequest request) {
		// ��������
		String path = request.getParameter("path");
		// ִ�в���
		boolean result = userService.importUser(path);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(userService.getMessage());
		return json;
	}
	@ResponseBody
	@RequestMapping("/deleteUsers")
	public JsonResult deleteUsers(HttpServletRequest request) {
		// ��������
		String id = request.getParameter("id");
		// ִ�в���
		boolean result = userService.deleteUsers(id);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(userService.getMessage());
		return json;
	}
	@ResponseBody
	@RequestMapping("/setUserRole")
	public JsonResult setUserRole(HttpServletRequest request) {
		// ��������
		String id = request.getParameter("id");
		String rId = request.getParameter("roleId");
		// ִ�в���
		boolean result = userService.userRole(id, rId);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(userService.getMessage());
		return json;
	}
	@ResponseBody
	@RequestMapping("/doReset")
	public JsonResult resetPassword(HttpServletRequest request) {
		// ��������
		String id = request.getParameter("id");
		UserV user = new UserV();
		user.setId(id);
		// ִ�в���
		boolean result = userService.editMima(user);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(userService.getMessage());
		return json;
	}
}