package doc.system.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import doc.common.BaseController;
import pushunsoft.common.JsonResult;
import pushunsoft.common.PageData;
import doc.common.annotation.LoginAnnotation;
import doc.system.entity.Log;
import doc.system.service.LogService;
import doc.system.view.LogV;
/**
 * LogController
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping("/system/log")
public class LogController extends BaseController {
	@Resource
	private LogService logService;
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
		String action = "查询日志";
		model.addAttribute("title", action);
		return model;
	}
	@ResponseBody
	@RequestMapping("/doPage")
	public JsonResult doPage(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		String biaoTi = request.getParameter("BiaoTi");
		LogV v = new LogV();
		v.setBiaoTi(biaoTi);
		PageData<Log> pageData = logService.getPage(page, v);
		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(pageData);
		return json;
	}
	/**
	 * 已审核导出
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doExport")
	public JsonResult doExport(HttpServletRequest request) {
		String biaoTi = request.getParameter("BiaoTi");
		Log v = new Log();
		v.setBiaoTi(biaoTi);
		// 返回
		boolean result = logService.toWriteXls(v);
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(logService.getMessage());
		return json;
	}
}