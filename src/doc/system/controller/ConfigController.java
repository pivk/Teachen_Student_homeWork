package doc.system.controller;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import doc.common.BaseController;
import doc.system.entity.Config;
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
	 * ��ʾ����
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/get")
	public Model get(HttpServletRequest request, Model model) {
		model.addAttribute("title", "��������");
		return model;
	}
	/**
	 * ��ȡ����
	 * @param request
	 * @return JsonResult��data��ʽ�ο� {@link oa.system.entity.Config oa.system.entity.Config}
	 */
	@ResponseBody
	@RequestMapping("/doGet")
	public JsonResult doGet(HttpServletRequest request) {
		// ִ�в���
		Config config = configService.get();
		// ����
		JsonResult json = new JsonResult();
		if (config == null)
			json.setState(false);
		else
			json.setState(true);
		json.setMessage(configService.getMessage());
		json.setData(config);
		return json;
	}
	/**
	 * �޸Ĳ���
	 * @param request
	 * <table>
	 * <tr><td>uploadPath</td><td>�ϴ�·��</td></tr>
	 * </table>
	 * @return JsonResult
	 */
	@ResponseBody
	@RequestMapping("/doSet")
	public JsonResult doSet(HttpServletRequest request) {
		Config config = new Config();
		String uploadPath = request.getParameter("uploadPath");
		if (uploadPath != null && !uploadPath.isEmpty()) {
			config.setUploadPath(uploadPath);
		}
		String filePath = request.getParameter("filePath");
		if (filePath != null && !filePath.isEmpty()) {
			config.setFilePath(filePath);
		}

		// ִ�в���
		boolean result = configService.set(config);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(configService.getMessage());

		return json;
	}
}