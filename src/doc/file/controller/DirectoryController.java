package doc.file.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import doc.common.BaseController;
import doc.common.annotation.LoginAnnotation;
import doc.file.entity.Directory;
import doc.file.service.DirectoryService;
import pushunsoft.common.JsonResult;

/**
 * Ŀ¼
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
	 * ���Ŀ¼
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/add")
	public Model add(HttpServletRequest request, Model model) {
		model.addAttribute("title", "创建目录");
		
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		return model;
	}

	/**
	 * ִ�����Ŀ¼
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doAdd")
	public JsonResult doAdd(HttpServletRequest request) {
		// ��������
		Directory entity = new Directory();
		String mingCheng = request.getParameter("mingCheng");
		if (mingCheng != null && !mingCheng.isEmpty()) {
			entity.setMingCheng(mingCheng);
		}
		String parentId = request.getParameter("parentId");
		if (parentId != null && !parentId.isEmpty()) {
			entity.setParentId(parentId);
		}
		String memo = request.getParameter("memo");
		if (memo != null && !memo.isEmpty()) {
			entity.setMemo(memo);
		}		
		// ִ�в���
		boolean result = directoryService.add(entity);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(directoryService.getMessage());
		return json;
	}

	/**
	 * ִ��ɾ��Ŀ¼
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doRemove")
	public JsonResult doRemove(HttpServletRequest request) {
		// ��������
		String id = request.getParameter("id");
		if (id == null || id.isEmpty()) {
			// ����
			JsonResult json = new JsonResult();
			json.setState(false);
			json.setMessage("δָ��Ŀ¼");
			return json;
		}

		// ִ�в���
		boolean result = directoryService.remove(id);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(directoryService.getMessage());
		return json;
	}
}