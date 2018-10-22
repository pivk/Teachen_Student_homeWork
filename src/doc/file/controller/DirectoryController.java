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
import doc.file.entity.Directory;
import doc.file.service.DirectoryService;
import doc.file.view.DirectoryV;
import doc.file.view.TreeV;
import doc.system.entity.Parameter;
import doc.system.entity.User;
import doc.system.view.ParameterV;
import pushunsoft.common.JsonResult;
import pushunsoft.common.PageData;

/**
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping("/directory")
public class DirectoryController extends BaseController {
	@Resource
	private DirectoryService directoryService;
	
	
	
	
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
		model.addAttribute("title", "查看");
		// 接收数据
		String id = request.getParameter("id");
		// 执行操作
		DirectoryV directoryV = directoryService.show(id);
		model.addAttribute("directoryV", directoryV);
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
	@RequestMapping("/showFile")
	public Model showFile(HttpServletRequest request, Model model) {
		model.addAttribute("title", "查看");
		// 接收数据
		DirectoryV v=new DirectoryV();
		String id = request.getParameter("id");
		if (id != null && !id.isEmpty()) {
			v.setId(id);
		}
		HttpSession session = request.getSession(false);
		if (session != null) {
			String creator = ((User) session.getAttribute(AppData.Session_User)).getId();
			v.setCreator(creator);
		}
		// 执行操作
		DirectoryV directoryV = directoryService.showFile(v);
		model.addAttribute("directoryV", directoryV);
		return model;
	}
	@ResponseBody
	@RequestMapping("/doGet")
	public JsonResult doGet(HttpServletRequest request) {
		// 接收数据
		String id = request.getParameter("id");
		// 执行操作
		DirectoryV parameter = directoryService.show(id);
		// 返回
		JsonResult json = new JsonResult();
		if (parameter == null)
			json.setState(false);
		else
			json.setState(true);
		json.setMessage(directoryService.getMessage());
		json.setData(parameter);
		return json;
	}

	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/page")
	public Model page(HttpServletRequest request, Model model) {
		model.addAttribute("title", "论文文件夹");
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
		DirectoryV v = new DirectoryV();
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
		PageData<DirectoryV> pageData = directoryService.getPage(page, v);
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
	@RequestMapping("/student/page")
	public Model studentpage(HttpServletRequest request, Model model) {
		model.addAttribute("title", "论文文件夹");
		return model;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/student/doPage")
	public JsonResult studentdoPage(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		DirectoryV v = new DirectoryV();
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
		PageData<DirectoryV> pageData = directoryService.getstudentPage(page, v);
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
	@RequestMapping("/add")
	public Model add(HttpServletRequest request, Model model) {
		model.addAttribute("title", "创建文件夹");
		return model;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doAdd")
	public JsonResult doAdd(HttpServletRequest request) {
		Directory entity = new Directory();
		String mingCheng = request.getParameter("mingCheng");
		if (mingCheng != null && !mingCheng.isEmpty()) {
			entity.setMingCheng(mingCheng);
		}
		String endTime = request.getParameter("endTime");
		if (endTime != null && !endTime.isEmpty()) {
			entity.setEndTime(endTime);
		}
		String courseId = request.getParameter("courseId");
		if (courseId != null && !courseId.isEmpty()) {
			entity.setCourseId(courseId);
		}
	
		String xueqi = request.getParameter("xueqi");
		if (xueqi != null && !xueqi.isEmpty()) {
			entity.setXueqi(xueqi);
		}
		String memo = request.getParameter("memo");
		if (memo != null && !memo.isEmpty()) {
			entity.setMemo(memo);
		}		
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			String creator = ((User) session.getAttribute(AppData.Session_User)).getId();
			entity.setCreator(creator);
		}
		boolean result = directoryService.add(entity);
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(directoryService.getMessage());
		return json;
	}
	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/edit")
	public Model edit(HttpServletRequest request, Model model) {
		model.addAttribute("title", "修改文件夹");
		return model;
	}
	
	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/showTu")
	public Model showTu(HttpServletRequest request, Model model) {
		model.addAttribute("title", "成绩图");
		return model;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doEdit")
	public JsonResult doEdit(HttpServletRequest request) {
		Directory entity = new Directory();
		String mingCheng = request.getParameter("mingCheng");
		if (mingCheng != null && !mingCheng.isEmpty()) {
			entity.setMingCheng(mingCheng);
		}
		String id = request.getParameter("id");
		if (id != null && !id.isEmpty()) {
			entity.setId(id);
		}
		String endTime = request.getParameter("endTime");
		if (endTime != null && !endTime.isEmpty()) {
			entity.setEndTime(endTime);
		}
		String courseId = request.getParameter("courseId");
		if (courseId != null && !courseId.isEmpty()) {
			entity.setCourseId(courseId);
		}
		
		String locked = request.getParameter("locked");
		if (locked != null && !locked.isEmpty()) {
			entity.setLocked(locked);
		}
		
		String xueqi = request.getParameter("xueqi");
		if (xueqi != null && !xueqi.isEmpty()) {
			entity.setXueqi(xueqi);
		}
		String memo = request.getParameter("memo");
		if (memo != null && !memo.isEmpty()) {
			entity.setMemo(memo);
		}		
		HttpSession session = request.getSession(false);
		if (session != null) {
			String creator = ((User) session.getAttribute(AppData.Session_User)).getId();
			entity.setCreator(creator);
		}
		boolean result = directoryService.edit(entity);
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(directoryService.getMessage());
		return json;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doRemove")
	public JsonResult doRemove(HttpServletRequest request) {
		String id = request.getParameter("id");
		if (id == null || id.isEmpty()) {
			JsonResult json = new JsonResult();
			json.setState(false);
			json.setMessage("内部错误");
			return json;
		}
		boolean result = directoryService.remove(id);
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(directoryService.getMessage());
		return json;
	}
}