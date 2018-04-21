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
import doc.system.entity.Config;
import doc.system.entity.Log;
import doc.system.entity.User;
import doc.system.service.ConfigService;
import doc.system.service.LogService;
import pushunsoft.common.JsonResult;
/**
 * ConfigController
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping("/system/config")
public class ConfigController extends BaseController {
	@Resource
	private ConfigService configService;
	@Resource
	private LogService logService;
	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 显示参数
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/get")
	public Model get(HttpServletRequest request, Model model) {
		model.addAttribute("title", "参数管理");
		return model;
	}
	@ResponseBody
	@RequestMapping("/doGet")
	public JsonResult doGet(HttpServletRequest request) {
		// 执行操作
		Config config = configService.get();
		// 返回
		JsonResult json = new JsonResult();
		if (config == null)
			json.setState(false);
		else
			json.setState(true);
		json.setMessage(configService.getMessage());
		json.setData(config);
		return json;
	}
	@ResponseBody
	@RequestMapping("/doSet")
	public JsonResult doSet(HttpServletRequest request) {
		Config config = new Config();
		String autoReport = request.getParameter("autoReport");
		if (autoReport != null && !autoReport.isEmpty()) {
			config.setAutoReport(autoReport);
		}
		String autoApproveSubmit = request.getParameter("autoApproveSubmit");
		if (autoApproveSubmit != null && !autoApproveSubmit.isEmpty()) {
			config.setAutoApproveSubmit(autoApproveSubmit);
		}
		String autoApprovePass = request.getParameter("autoApprovePass");
		if (autoApprovePass != null && !autoApprovePass.isEmpty()) {
			config.setAutoApprovePass(autoApprovePass);
		}
		String reportUrl = request.getParameter("reportUrl");
		if (reportUrl != null && !reportUrl.isEmpty()) {
			config.setReportUrl(reportUrl);
		}
		String uploadPath = request.getParameter("uploadPath");
		if (uploadPath != null && !uploadPath.isEmpty()) {
			config.setUploadPath(uploadPath);
		}
		// 执行操作
		boolean result = configService.set(config);
		// 返回
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(configService.getMessage());
		if(result){
			Log log = new Log();
			log.setNeiRong("");
			log.setBiaoTi("修改字典");
			User user1 = (User) request.getSession().getAttribute(AppData.Session_User);
			log.setUserId(user1.getId());
			Date date2 =new Date();
			log.setTimestamp(dateFormatter.format(date2));
			logService.add(log);
		}
		return json;
	}
}