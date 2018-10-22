package doc.system.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import doc.common.AppData;
import doc.common.BaseController;
import doc.common.annotation.LoginAnnotation;
import doc.home.view.Menu;
import doc.system.entity.Log;
import doc.system.entity.Course;
import doc.system.entity.User;
import doc.system.service.LogService;
import doc.system.service.PowerService;
import doc.system.service.CourseService;
import doc.system.view.CourseV;
import doc.system.view.StatisticsV;
import pushunsoft.common.JsonResult;
import pushunsoft.common.PageData;
/**
 * LogController
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping("/system/course")
public class CourseController extends BaseController {
	@Resource
	private CourseService courseService;
	@Resource
	private PowerService powerService;
	@Resource
	private LogService logService;
	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	/*
	 * ��״ͼ
	 */
	@ResponseBody
	@RequestMapping("/dogetStatisticsV")
	public JsonResult dogetStatisticsV(HttpServletRequest request, Model model) {
		List<StatisticsV> list = new ArrayList<StatisticsV>();
		String directoryId = request.getParameter("courseId");
		list=courseService.dogetStatisticsV(directoryId);
		JsonResult json = new JsonResult();;
		if (list != null) {
			json.setState(true);
			json.setData(list);
		} 
		return json;
	}
	@ResponseBody
	@RequestMapping("/addUsers")
	public JsonResult addUsers(HttpServletRequest request) {
		// ��������
		CourseV entity = new CourseV();
		String id = request.getParameter("id");
		if (id != null && !id.isEmpty()) {
			entity.setId(id);
		}
		String xueqi = request.getParameter("xueqi");
		if (xueqi != null && !xueqi.isEmpty()) {
			entity.setXueqi(xueqi);
		}
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			String userid = ((User) session.getAttribute(AppData.Session_User)).getId();
			entity.setUserId(userid);
		// ִ�в���
		}
		boolean result = courseService.addUsers(entity);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(courseService.getMessage());
		return json;
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
		String action = "��ѯ�γ�";
		model.addAttribute("title", action);
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
	@RequestMapping("/coursepage")
	public Model coursepage(HttpServletRequest request, Model model) {
		String action = "��ѯ�γ�";
		model.addAttribute("title", action);
		return model;
	}
	/**
	 * ���
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/add")
	public Model add(HttpServletRequest request, Model model) {
		model.addAttribute("title", "��ӿγ�");
		return model;
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
		model.addAttribute("title", "�޸Ŀγ�");
		String id = request.getParameter("id");
		model.addAttribute("id", id);
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
		model.addAttribute("title", "�鿴�γ�");
		// ��������
		String id = request.getParameter("id");
		// ִ�в���
		Course entity = courseService.get(id);
		model.addAttribute("entity", entity);
		return model;
	}
	/**
	 * �γ�Ȩ��
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/power")
	public Model power(HttpServletRequest request, Model model) {
		model.addAttribute("title", "ѡ��γ�");
		// ��������
		// ��������
		String id = request.getParameter("id");
		model.addAttribute("id", id);
		List<Menu> menuList=powerService.MakeMenu();
		ObjectMapper mapper = new ObjectMapper();
		String menuJson = "[]";
		try {
			menuJson = mapper.writeValueAsString(menuList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		model.addAttribute("tree", menuJson);
		return model;
	}
	/**
	 * ��ҳ��ѯ�γ�
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doPage")
	public JsonResult doPage(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		CourseV v = new CourseV();
		String id = request.getParameter("id");
		if (id != null && !id.isEmpty()) {
			v.setId(id);
		}
		String mingCheng = request.getParameter("mingCheng");
		if (mingCheng != null && !mingCheng.isEmpty()) {
			v.setMingCheng(mingCheng);
		}
		PageData<Course> pageData = courseService.getPage(page, v);
		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(pageData);
		return json;
	}
	/**
	 * ��ҳ��ѯ�γ�
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doUSerPage")
	public JsonResult doUSerPage(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		CourseV v = new CourseV();
		String id = request.getParameter("id");
		if (id != null && !id.isEmpty()) {
			v.setId(id);
		}
		String mingCheng = request.getParameter("mingCheng");
		if (mingCheng != null && !mingCheng.isEmpty()) {
			v.setMingCheng(mingCheng);
		}
		HttpSession session = request.getSession(false);
		if (session != null) {
			String userId = ((User) session.getAttribute(AppData.Session_User)).getId();
			v.setUserId(userId);
		}
		PageData<Course> pageData = courseService.getPage(page, v);
		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(pageData);
		return json;
	}
	/**
	 * ��ҳ��ѯ�γ�
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doUserPage")
	public JsonResult doUserPage(HttpServletRequest request) {
		CourseV v = new CourseV();
		String id = request.getParameter("id");
		if (id != null && !id.isEmpty()) {
			v.setId(id);
		}
		String userName = request.getParameter("userName");
		if (userName != null && !userName.isEmpty()) {
			v.setUserName(userName);
		}
		PageData<Course> pageData = courseService.doUserPage(v);
		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(pageData);
		return json;
	}
	/**
	 * ��ѯ���пγ�
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/courseList")
	public JsonResult courseList(HttpServletRequest request) {
		List<Course> list = courseService.getList();
		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(list);
		return json;
	}
	@ResponseBody
	@RequestMapping("/doAdd")
	public JsonResult doAdd(HttpServletRequest request) {
		// ��������
		Course entity = new Course();
		String mingCheng = request.getParameter("mingCheng");
		if (mingCheng != null && !mingCheng.isEmpty()) {
			entity.setMingCheng(mingCheng);
		}
		String code = request.getParameter("code");
		if (code != null && !code.isEmpty()) {
			entity.setCode(code);
		}
		String userId = request.getParameter("userId");
		if (userId != null && !userId.isEmpty()) {
			entity.setUserId(userId);
		}
		// ִ�в���
		boolean result = courseService.add(entity);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(courseService.getMessage());
		if(result){
			Log log = new Log();
			log.setNeiRong(mingCheng);
			log.setBiaoTi(entity.getId()+"��ӿγ�");
			User user1 = (User) request.getSession().getAttribute(AppData.Session_User);
			log.setUserId(user1.getId());
			Date date2 =new Date();
			log.setTimestamp(dateFormatter.format(date2));
			logService.add(log);
		}
		return json;
	}
	@ResponseBody
	@RequestMapping("/doGet")
	public JsonResult doGet(HttpServletRequest request) {
		// ��������
		String id = request.getParameter("id");
		// ִ�в���
		Course entity = courseService.get(id);
		// ����
		JsonResult json = new JsonResult();
		if (entity == null)
			json.setState(false);
		else
			json.setState(true);
		json.setMessage(courseService.getMessage());
		json.setData(entity);
		return json;
	}
	@ResponseBody
	@RequestMapping("/doEdit")
	public JsonResult doEdit(HttpServletRequest request) {
		// ��������
		Course entity = new Course();
		String id = request.getParameter("id");
		if (id != null && !id.isEmpty()) {
			entity.setId(id);
		}
		String mingCheng = request.getParameter("mingCheng");
		if (mingCheng != null && !mingCheng.isEmpty()) {
			entity.setMingCheng(mingCheng);
		}
		String code = request.getParameter("code");
		if (code != null && !code.isEmpty()) {
			entity.setCode(code);
		}
		String userId = request.getParameter("userId");
		if (userId != null && !userId.isEmpty()) {
			entity.setUserId(userId);
		}
		// ִ�в���
		boolean result = courseService.edit(entity);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(courseService.getMessage());
		if(result){
			Log log = new Log();
			log.setNeiRong(mingCheng);
			log.setBiaoTi(id+"�޸Ŀγ�");
			User user1 = (User) request.getSession().getAttribute(AppData.Session_User);
			log.setUserId(user1.getId());
			Date date2 =new Date();
			log.setTimestamp(dateFormatter.format(date2));
			logService.add(log);
		}
		return json;
	}
	@ResponseBody
	@RequestMapping("/doEditUser")
	public JsonResult doEditUser(HttpServletRequest request) {
		// ��������
		CourseV entity = new CourseV();
		String courseId = request.getParameter("courseId");
		if (courseId != null && !courseId.isEmpty()) {
			entity.setCourseId(courseId);
		}

		String userId = request.getParameter("userId");
		if (userId != null && !userId.isEmpty()) {
			entity.setUserId(userId);
		}
		
		String lunwenScore = request.getParameter("lunwenScore");
		if (lunwenScore != null && !lunwenScore.isEmpty()) {
			entity.setLunwenScore(lunwenScore);
		}
	
		// ִ�в���
		boolean result = courseService.doEditUser(entity);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(courseService.getMessage());
		return json;
	}
	@ResponseBody
	@RequestMapping("/doRemove")
	public JsonResult doRemove(HttpServletRequest request) {
		// ��������
		String id = request.getParameter("id");
		// ִ�в���
		boolean result = courseService.remove(id);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(courseService.getMessage());
		if(result){
			Course entity =new Course();
			Log log = new Log();
			log.setNeiRong(entity.getMingCheng());
			log.setBiaoTi(id+"ɾ���γ�");
			User user1 = (User) request.getSession().getAttribute(AppData.Session_User);
			log.setUserId(user1.getId());
			Date date2 =new Date();
			log.setTimestamp(dateFormatter.format(date2));
			logService.add(log);
		}
		return json;
	}
}