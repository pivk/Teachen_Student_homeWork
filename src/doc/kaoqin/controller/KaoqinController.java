package doc.kaoqin.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import doc.common.AppData;
import doc.common.BaseController;
import doc.kaoqin.entity.Kaoqin;
import doc.kaoqin.service.KaoqinService;
import doc.kaoqin.view.KaoqinV;
import doc.system.entity.Course;
import doc.system.entity.Log;
import doc.system.entity.User;
import doc.system.service.LogService;
import doc.system.service.UnitService;
import doc.system.service.UserService;
import doc.system.view.CourseV;
import pushunsoft.common.JsonResult;
import pushunsoft.common.PageData;

/**
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping("/kaoqin")
public class KaoqinController extends BaseController {
	
	@Resource
	private KaoqinService kaoqinService;
	
	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/show")
	public Model show(HttpServletRequest request, Model model) {
		String action = "¿¼ÇÚ";
		model.addAttribute("title", action);
		return model;
	}
	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/page")
	public Model page(HttpServletRequest request, Model model) {
		String action = "¿ÎÌÃ¿¼ÇÚ";
		model.addAttribute("title", action);
		return model;
	}
	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/adminPage")
	public Model adminPage(HttpServletRequest request, Model model) {
		String action = "¿ÎÌÃ¿¼ÇÚ";
		model.addAttribute("title", action);
		return model;
	}

	@ResponseBody
	@RequestMapping("/doAdd")
	public JsonResult doAdd(HttpServletRequest request) {
		// ½ÓÊÕÊý¾Ý
		CourseV entity = new CourseV();
		String courseId = request.getParameter("id");
		if (courseId != null && !courseId.isEmpty()) {
			entity.setCourseId(courseId);
		}
		String zhi = request.getParameter("zhi");
		if (zhi != null && !zhi.isEmpty()) {
			entity.setStr(zhi);
		}
		String userId = request.getParameter("userId");
		if (userId != null && !userId.isEmpty()) {
			entity.setUserId(userId);
		}
		// Ö´ÐÐ²Ù×÷
		boolean result = kaoqinService.add(entity);
		// ·µ»Ø
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(kaoqinService.getMessage());

		return json;
	}
}