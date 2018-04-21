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
import doc.system.entity.Unit;
import doc.system.entity.User;
import doc.system.service.LogService;
import doc.system.service.UnitService;
import doc.system.view.UnitV;
/**
 * UnitController
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping("/system/unit")
public class UnitController extends BaseController {
	@Resource
	private UnitService unitService;
	@Resource
	private LogService logService;
	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * ��ѯ
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/page")
	public Model page(HttpServletRequest request, Model model) {
		String action = "��ѯ����";
		model.addAttribute("title", action);
		return model;
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
		model.addAttribute("title", "�鿴����");
		// ��������
		String id = request.getParameter("id");
		// ִ�в���
		UnitV unit = unitService.show(id);
		model.addAttribute("unit", unit);
		return model;
	}
	@ResponseBody
	@RequestMapping("/doPage")
	public JsonResult doPage(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		UnitV unit = new UnitV();
		String mingCheng = request.getParameter("mingCheng");
		if (mingCheng != null && !mingCheng.isEmpty()) {
			unit.setMingCheng(mingCheng);
		}
		PageData<UnitV> pageData = unitService.getPage(page,unit);
		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(pageData);
		return json;
	}
	/**
	 * �������
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/importUnit")
	public Model importUnit(HttpServletRequest request, Model model) {
		model.addAttribute("title", "�������");
		return model;
	}
	@ResponseBody
	@RequestMapping("/importUnitExcel")
	public JsonResult importUnit(HttpServletRequest request) {
		// ��������
		String path = request.getParameter("path");
		// ִ�в���
		boolean result = unitService.importUnit(path);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(unitService.getMessage());
		return json;
	}
	/**
	 * ��ӻ���
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/add")
	public Model add(HttpServletRequest request, Model model) {
		model.addAttribute("title", "��ӻ���");
		return model;
	}
	@ResponseBody
	@RequestMapping("/doAdd")
	public JsonResult doAdd(HttpServletRequest request) {
		// ��������
		Unit unit = new Unit();
		String mingCheng = request.getParameter("mingCheng");
		if (mingCheng != null && !mingCheng.isEmpty()) {
			unit.setMingCheng(mingCheng);
		}
		String code = request.getParameter("code");
		if (code != null && !code.isEmpty()) {
			unit.setCode(code);
		}
		String parentId = request.getParameter("parentId");
		if (parentId != null && !parentId.isEmpty() && !"-".equals(parentId)) {
			unit.setParentId(parentId);
		}
		// ִ�в���
		boolean result = unitService.add(unit);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(unitService.getMessage());
		if(result){
			Log log = new Log();
			log.setNeiRong(mingCheng);
			log.setBiaoTi(unit.getId()+"��ӻ���");
			User user1 = (User) request.getSession().getAttribute(AppData.Session_User);
			log.setUserId(user1.getId());
			Date date2 =new Date();
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
		model.addAttribute("title", "�޸Ļ���");
		String id = request.getParameter("id");
		model.addAttribute("id", id);
		return model;
	}
	@ResponseBody
	@RequestMapping("/doGet")
	public JsonResult doGet(HttpServletRequest request) {
		// ��������
		String id = request.getParameter("id");
		// ִ�в���
		Unit unit = unitService.get(id);
		// ����
		JsonResult json = new JsonResult();
		if (unit == null)
			json.setState(false);
		else
			json.setState(true);
		json.setMessage(unitService.getMessage());
		json.setData(unit);
		return json;
	}
	@ResponseBody
	@RequestMapping("/doEdit")
	public JsonResult doEdit(HttpServletRequest request) {
		// ��������
		Unit unit = new Unit();
		String id = request.getParameter("id");
		if (id != null && !id.isEmpty()) {
			unit.setId(id);
		}
		String mingCheng = request.getParameter("mingCheng");
		if (mingCheng != null && !mingCheng.isEmpty()) {
			unit.setMingCheng(mingCheng);
		}
		String code = request.getParameter("code");
		if (code != null && !code.isEmpty()) {
			unit.setCode(code);
		}
		String parentId = request.getParameter("parentId");
		if (parentId != null && !parentId.isEmpty() && !"-".equals(parentId)) {
			unit.setParentId(parentId);
		}
		// ִ�в���
		boolean result = unitService.edit(unit);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(unitService.getMessage());
		if(result){
			Log log = new Log();
			log.setNeiRong(mingCheng);
			log.setBiaoTi(id+"�޸Ļ���");
			User user1 = (User) request.getSession().getAttribute(AppData.Session_User);
			log.setUserId(user1.getId());
			Date date2 =new Date();
			log.setTimestamp(dateFormatter.format(date2));
			logService.add(log);
		}
		return json;
	}
	@ResponseBody
	@RequestMapping("/doRemove")
	public JsonResult doRemove(HttpServletRequest request) {
		// ��������
		String id = request.getParameter("id");
		// ִ�в���
		boolean result = unitService.remove(id);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(unitService.getMessage());
		if(result){
			Unit unit=new Unit();
			Log log = new Log();
			log.setNeiRong(unit.getMingCheng());
			log.setBiaoTi(id+"ɾ������");
			User user1 = (User) request.getSession().getAttribute(AppData.Session_User);
			log.setUserId(user1.getId());
			Date date2 =new Date();
			log.setTimestamp(dateFormatter.format(date2));
			logService.add(log);
		}
		return json;
	}
	@ResponseBody
	@RequestMapping("/doList")
	public JsonResult doList(HttpServletRequest request) {
		// ��������
		UnitV unit = new UnitV();
		// ִ�в���	
		List<UnitV> result = unitService.getList(unit) ;
		// ����
		JsonResult json = new JsonResult();
		if(result != null){
			json.setState(true);
			json.setData(result);
		}else{
			json.setState(false);
			json.setMessage(unitService.getMessage());
		}		
		return json;				
	}
}
