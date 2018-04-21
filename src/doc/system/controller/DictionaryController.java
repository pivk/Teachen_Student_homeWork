package doc.system.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import doc.system.entity.Parameter;
import doc.system.entity.User;
import doc.system.service.DictionaryService;
import doc.system.service.LogService;
import doc.system.view.ParameterV;

/**
 * LogController
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping("/system/dictionary")
public class DictionaryController extends BaseController {
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private LogService logService;
	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * ����ֵ�
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/add")
	public Model add(HttpServletRequest request, Model model) {
		model.addAttribute("title", "����ֵ�");
		return model;
	}

	@ResponseBody
	@RequestMapping("/doAdd")
	public JsonResult doAdd(HttpServletRequest request) {
		// ��������
		Parameter parameter = new Parameter();
		String lei = request.getParameter("lei");
		if (lei != null && !lei.isEmpty() && !"-".equals(lei)) {
			parameter.setLei(lei);
		}
		String jian = request.getParameter("jian");
		if (jian != null && !jian.isEmpty()) {
			parameter.setJian(jian);
		}
		String zhi = request.getParameter("zhi");
		if (zhi != null && !zhi.isEmpty()) {
			parameter.setZhi(zhi);
		}
		String xu = request.getParameter("xu");
		if (xu != null && !xu.isEmpty()) {
			parameter.setXu(xu);
		}
		// ִ�в���
		boolean result = dictionaryService.add(parameter);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(dictionaryService.getMessage());
		if (result) {
			Log log = new Log();
			log.setNeiRong(parameter.getLei());
			log.setBiaoTi(parameter.getId() + "����ֵ�");
			User user1 = (User) request.getSession().getAttribute(AppData.Session_User);
			log.setUserId(user1.getId());
			Date date2 = new Date();
			log.setTimestamp(dateFormatter.format(date2));
			logService.add(log);
		}
		return json;
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
		model.addAttribute("title", "�޸��ֵ�");
		String id = request.getParameter("id");
		model.addAttribute("id", id);
		return model;
	}

	@ResponseBody
	@RequestMapping("/doEdit")
	public JsonResult doEdit(HttpServletRequest request) {
		// ��������
		Parameter parameter = new Parameter();
		String id = request.getParameter("id");
		if (id != null && !id.isEmpty()) {
			parameter.setId(id);
		}
		String lei = request.getParameter("lei");
		if (lei != null && !lei.isEmpty() && !"-".equals(lei)) {
			parameter.setLei(lei);
		}
		String jian = request.getParameter("jian");
		if (jian != null && !jian.isEmpty()) {
			parameter.setJian(jian);
		}
		String zhi = request.getParameter("zhi");
		if (zhi != null && !zhi.isEmpty()) {
			parameter.setZhi(zhi);
		}
		String xu = request.getParameter("xu");
		if (xu != null && !xu.isEmpty()) {
			parameter.setXu(xu);
		}
		// ִ�в���
		boolean result = dictionaryService.edit(parameter);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(dictionaryService.getMessage());
		if (result) {
			Log log = new Log();
			log.setNeiRong(parameter.getLei());
			log.setBiaoTi(id + "�޸��ֵ�");
			User user1 = (User) request.getSession().getAttribute(AppData.Session_User);
			log.setUserId(user1.getId());
			Date date2 = new Date();
			log.setTimestamp(dateFormatter.format(date2));
			logService.add(log);
		}
		return json;
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
		model.addAttribute("title", "�鿴�ֵ�");
		// ��������
		String id = request.getParameter("id");
		// ִ�в���
		ParameterV parameter = dictionaryService.show(id);
		model.addAttribute("parameter", parameter);
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
		String action = "��ѯ�ֵ�";
		model.addAttribute("title", action);
		return model;
	}

	@ResponseBody
	@RequestMapping("/doPage")
	public JsonResult doPage(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		ParameterV parameter = new ParameterV();
		String lei = request.getParameter("lei");
		if (lei != null && !lei.isEmpty() && !"-".equals(lei)) {
			parameter.setLei(lei);
		}
		PageData<Parameter> pageData = dictionaryService.getPage(page, parameter);
		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(pageData);
		return json;
	}

	@ResponseBody
	@RequestMapping("/doGet")
	public JsonResult doGet(HttpServletRequest request) {
		// ��������
		String id = request.getParameter("id");
		// ִ�в���
		Parameter parameter = dictionaryService.get(id);
		// ����
		JsonResult json = new JsonResult();
		if (parameter == null)
			json.setState(false);
		else
			json.setState(true);
		json.setMessage(dictionaryService.getMessage());
		json.setData(parameter);
		return json;
	}

	@ResponseBody
	@RequestMapping("/doRemove")
	public JsonResult doRemove(HttpServletRequest request) {
		// ��������
		String id = request.getParameter("id");
		// ִ�в���
		boolean result = dictionaryService.remove(id);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(dictionaryService.getMessage());
		if (result) {
			Parameter parameter = new Parameter();
			Log log = new Log();
			log.setNeiRong(parameter.getLei());
			log.setBiaoTi(id + "�޸��ֵ�");
			User user1 = (User) request.getSession().getAttribute(AppData.Session_User);
			log.setUserId(user1.getId());
			Date date2 = new Date();
			log.setTimestamp(dateFormatter.format(date2));
			logService.add(log);
		}
		return json;
	}

	@ResponseBody
	@RequestMapping("/doList")
	public JsonResult doList(HttpServletRequest request) {
		// ��������
		ParameterV parameter = new ParameterV();
		// ִ�в���
		List<Parameter> result = dictionaryService.getList(parameter);
		// ����
		JsonResult json = new JsonResult();
		if (result != null) {
			json.setState(true);
			json.setData(result);
		} else {
			json.setState(false);
			json.setMessage(dictionaryService.getMessage());
		}
		return json;
	}

	@ResponseBody
	@RequestMapping("/getSheng")
	public JsonResult getSheng(HttpServletRequest request) {
		// ��������
		ParameterV parameter = new ParameterV();
		String lei = request.getParameter("lei");
		if (lei != null && !lei.isEmpty() && !"-".equals(lei)) {
			parameter.setLei(lei);
		}
		String jian = request.getParameter("jian");
		if (jian != null && !jian.isEmpty() && !"-".equals(jian)) {
			parameter.setJian(jian);
		}
		// ִ�в���
		List<Parameter> result = dictionaryService.getSheng(parameter);
		// ����
		JsonResult json = new JsonResult();
		if (result != null) {
			json.setState(true);
			json.setData(result);
		} else {
			json.setState(false);
			json.setMessage(dictionaryService.getMessage());
		}
		return json;
	}
}