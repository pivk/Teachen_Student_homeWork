package doc.file.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import doc.common.BaseController;
import doc.common.annotation.LoginAnnotation;
import doc.file.service.TreeService;
import doc.file.view.TreePageV;
import doc.file.view.TreeV;
import pushunsoft.common.JsonResult;
import pushunsoft.common.PageData;

/**
 * ��
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
	 * ����ѯ
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/page")
	public Model page(HttpServletRequest request, Model model) {
		model.addAttribute("title", "云盘");
		return model;
	}

	/**
	 * ����ѯ
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doPage")
	public JsonResult doPage(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		// ��������
		TreeV v = new TreeV();
		String mingCheng = request.getParameter("mingCheng");
		if (mingCheng != null && !mingCheng.isEmpty()) {
			v.setMingCheng(mingCheng);
		}
		String parentId = request.getParameter("parentId");
		if (parentId != null && !parentId.isEmpty()) {
			v.setParentId(parentId);
		}
		// ִ�в���
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
	@RequestMapping("/tree")
	public Model tree(HttpServletRequest request, Model model) {
		model.addAttribute("title", "云盘");
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
		String parentId = request.getParameter("parentId");
		if (parentId != null && !parentId.isEmpty()) {
			v.setParentId(parentId);
		}
		String projectId = request.getParameter("projectId");
		if (projectId != null && !projectId.isEmpty()) {
			v.setProjectId(projectId);
		}
		String libraryId = request.getParameter("libraryId");
		if (libraryId != null && !libraryId.isEmpty()) {
			v.setLibraryId(libraryId);
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
	
	
	
	
}