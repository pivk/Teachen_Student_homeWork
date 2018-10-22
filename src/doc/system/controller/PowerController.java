package doc.system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import doc.system.entity.Power;
import doc.system.entity.User;
import doc.system.service.PowerService;
import doc.system.view.PowerV;
import pushunsoft.common.JsonResult;

/**
 * PowerController
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping("/system/power")
public class PowerController extends BaseController {
	@Resource
	private PowerService powerService;

	/**
	 * 查询
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/index")
	public Model page(HttpServletRequest request, Model model) {
		model.addAttribute("title", "权限管理");
		List<Menu> menuList = powerService.MakeMenu();
		ObjectMapper mapper = new ObjectMapper();
		String menuJson = "[]";
		try {
			menuJson = mapper.writeValueAsString(menuList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		model.addAttribute("units", menuJson);
		return model;
	}

	/**
	 * 查询角色权限
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRolePower")
	public JsonResult getRolePower(HttpServletRequest request) {
		String roleId = request.getParameter("roleId");
		List<PowerV> list = powerService.getRolePower(roleId);
		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(list);
		return json;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveRolePower")
	public JsonResult saveRolePower(HttpServletRequest request) {
		String roleId = request.getParameter("roleId");
		String powerIds =request.getParameter("powerIds");
		powerIds = powerIds.substring(0, powerIds.length()-1);
		String[] powers = powerIds.split(",");
		List<Power> powerList = new ArrayList<Power>();
		if (powers != null && powers.length > 0) {
			for (String id : powers) {
				Power power = new Power();
				power.setId(id);
				powerList.add(power);
			}
		}
		boolean result = powerService.saveRolePower(roleId, powerList);
		JsonResult json = new JsonResult();
		json.setState(result);

		return json;
	}
	@LoginAnnotation
	@RequestMapping("/power")
	public Model power(HttpServletRequest request, Model model) {
		model.addAttribute("title", "自定义日常办�?");
		List<Menu> menuList=null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			String userId = ((User) session.getAttribute(AppData.Session_User)).getId();
			menuList=powerService.MakeUserMenu(userId);
		}
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
	 * 查询用户日常办公
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getOffice")
	public JsonResult getOffice(HttpServletRequest request) {
		String userId=null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			userId = ((User) session.getAttribute(AppData.Session_User)).getId();
		}
		List<PowerV> list = powerService.getOffice(userId);
		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(list);
		return json;
	}
	/*@ResponseBody
	@RequestMapping("/saveOffice")
	public JsonResult saveOffice(HttpServletRequest request) {
		String userId=null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			userId = ((User) session.getAttribute(AppData.Session_User)).getId();
		}
		String powerIds =request.getParameter("powerIds");
		powerIds = powerIds.substring(0, powerIds.length()-1);
		String[] powers = powerIds.split(",");
		List<Power> powerList = new ArrayList<Power>();
		if (powers != null && powers.length > 0) {
			for (String id : powers) {
				Power power = new Power();
				power.setId(id);
				powerList.add(power);
			}
		}
		boolean result = powerService.saveOffice(userId, powerList);
		List<Menu> officeList = powerService.MakeOfficeMenu(userId);
		ObjectMapper mapper = new ObjectMapper();
		String officeJson = "[]";
		try {
			officeJson = mapper.writeValueAsString(officeList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		session.setAttribute("office", officeJson);
		JsonResult json = new JsonResult();
		json.setState(result);

		return json;
	}*/
}