package doc.file.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import doc.common.AppData;
import doc.common.BaseController;
import doc.common.annotation.LoginAnnotation;
import doc.file.service.TreeService;
import doc.file.view.TreePageV;
import doc.file.view.TreeV;
import doc.system.entity.User;
import pushunsoft.common.JsonResult;
import pushunsoft.common.PageData;

/**
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping("/tree")
public class TreeController extends BaseController {
	@Resource
	private TreeService treeService;

	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/page")
	public Model page(HttpServletRequest request, Model model) {
		model.addAttribute("title", "目录");
		return model;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doPage")
	public JsonResult doPage(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		TreeV v = new TreeV();
		String mingCheng = request.getParameter("mingCheng");
		if (mingCheng != null && !mingCheng.isEmpty()) {
			v.setMingCheng(mingCheng);
		}
		String parentId = request.getParameter("parentId");
		if (parentId != null && !parentId.isEmpty()) {
			v.setParentId(parentId);
		}
		
		PageData<TreeV> pageData = treeService.getPage(page, v);
		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(pageData);
		return json;
	}

	/**
	
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/teacher/tree")
	public Model tree(HttpServletRequest request, Model model) {
		model.addAttribute("title", "首页");
		model.addAttribute("parentId", "0");
		return model;
	}
	/**
	
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/student/tree")
	public Model Studenttree(HttpServletRequest request, Model model) {
		model.addAttribute("title", "首页");
		model.addAttribute("parentId", "0");
		return model;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doTree")
	public JsonResult doTree(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		
		TreeV v = new TreeV();
		String mingCheng = request.getParameter("mingCheng");
		if (mingCheng != null && !mingCheng.isEmpty()) {
			v.setMingCheng(mingCheng);
		}
		String xueqi = request.getParameter("xueqi");
		if (xueqi != null && !xueqi.isEmpty()) {
			v.setXueqi(xueqi);;
		}
		HttpSession session = request.getSession(false);
		if (session != null) {
			String creator = ((User) session.getAttribute(AppData.Session_User)).getId();
			v.setCreator(creator);
		}
		String parentId = request.getParameter("parentId");
		if (parentId != null && !parentId.isEmpty()) {
			v.setParentId(parentId);
		}
	
		TreePageV data=new TreePageV();
		PageData<TreeV> pageData = treeService.getPage(page, v);
		data.setData(pageData);
		data.setNav(treeService.getNav(parentId));
		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(data);
		return json;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doStudentTree")
	public JsonResult doStudentTree(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		TreeV v = new TreeV();
		String mingCheng = request.getParameter("mingCheng");
		if (mingCheng != null && !mingCheng.isEmpty()) {
			v.setMingCheng(mingCheng);
		}
		String xueqi = request.getParameter("xueqi");
		if (xueqi != null && !xueqi.isEmpty()) {
			v.setXueqi(xueqi);;
		}
		HttpSession session = request.getSession(false);
		if (session != null) {
			String creator = ((User) session.getAttribute(AppData.Session_User)).getId();
			v.setCreator(creator);
		}
		String parentId = request.getParameter("parentId");
		if (parentId != null && !parentId.isEmpty()) {
			v.setParentId(parentId);
		}
		TreePageV data=new TreePageV();
		PageData<TreeV> pageData = treeService.getStudentPage(page, v);
		data.setData(pageData);
		data.setNav(treeService.getNav(parentId));
		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(data);
		return json;
	}
	
	
	
	
}