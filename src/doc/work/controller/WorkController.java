package doc.work.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import doc.common.AppData;
import doc.common.BaseController;
import doc.file.view.DirectoryV;
import doc.system.entity.User;
import doc.system.service.UnitService;
import doc.system.service.UserService;
import doc.work.entity.Work;
import doc.work.service.WorkService;
import doc.work.view.WorkV;
import pushunsoft.common.JsonResult;
import pushunsoft.common.PageData;

/**
 * NewsController
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping("/work")
public class WorkController extends BaseController {
	@Resource
	UserService userServer;

	@Resource
	WorkService workService;


	@Resource
	UnitService serverUnit;


	/**
	 * 分页查询页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/adminPage")
	public Model workAdminPage(HttpServletRequest request, Model model) {
		String action = "作业管理";
		model.addAttribute("title", action);
		return model;
	}
	/**
	 * 分页查询页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/page")
	public Model page(HttpServletRequest request, Model model) {
		String action = "作业管理";
		model.addAttribute("title", action);
		return model;
	}

	/**
	 * 分页查询页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/workPage")
	public Model workPage(HttpServletRequest request, Model model) {

		String action = "作业";
		model.addAttribute("title", action);

		return model;
	}

	/**
	 * 分页查询
	 * 
	 * @param request
	 *            <table>
	 *            <tr>
	 *            <td>page</td>
	 *            <td>页码，从1�?�?</td>
	 *            </tr>
	 *            <tr>
	 *            <td>xingMing</td>
	 *            <td>姓名</td>
	 *            </tr>
	 *            </table>
	 * @return JsonResult，data格式参�?? {@link oa.meeting.entity.News
	 *         oa.meeting.entity.News}
	 */
	@ResponseBody
	@RequestMapping("/doAdminPage")
	public JsonResult doWorkPage(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		WorkV work = new WorkV();
		String biaoti = request.getParameter("biaoti");
		if (biaoti != null && !biaoti.isEmpty()) {
			work.setBiaoTi(biaoti);
		}
		HttpSession session = request.getSession(false);
		if (session != null) {
			String creatorId = ((User) session.getAttribute(AppData.Session_User)).getId();
			work.setCreatorId(creatorId);
		}
		
		PageData<WorkV> pageData = workService.getPage(page, work);

		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(pageData);
		return json;
	}

	/**
	 * 分页查询作业
	 * 
	 * @param request
	 *            <table>
	 *            <tr>
	 *            <td>page</td>
	 *            <td>页码，从1�?�?</td>
	 *            </tr>
	 *            <tr>
	 *            <td>xingMing</td>
	 *            <td>姓名</td>
	 *            </tr>
	 *            </table>
	 * @return JsonResult，data格式参�?? {@link KaoqinV.meeting.entity.Kaoqin
	 *         oa.meeting.entity.News}
	 */
	@ResponseBody
	@RequestMapping("/doWorkPageUser")
	public JsonResult doWorkPageUser(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		WorkV workv = new WorkV();

		String biaoti = request.getParameter("biaoti");
		if (biaoti != null && !biaoti.isEmpty()) {
			workv.setBiaoTi(biaoti);
		}
		HttpSession session = request.getSession(false);
		if (session != null) {
			String creatorId = ((User) session.getAttribute(AppData.Session_User)).getId();
			workv.setCreatorId(creatorId);
		}
		PageData<WorkV> pageData = workService.getPageUser(page, workv);

		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(pageData);
		return json;
	}


	/**
	 * 添加作业
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/add")
	public Model add(HttpServletRequest request, Model model) {
		model.addAttribute("title", "新增作业");
		return model;

	}
	/**
	 * 添加作业
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/edit")
	public Model edit(HttpServletRequest request, Model model) {
		model.addAttribute("title", "修改作业");
		return model;
		
	}

	/**
	 * 添加作业
	 * 
	 * @param request
	 *            <table>
	 *            <tr>
	 *            <td>biaoTi</td>
	 *            <td>标题</td>
	 *            </tr>
	 *            <tr>
	 *            <td>creator</td>
	 *            <td>创建�?</td>
	 *            </tr>
	 *            <tr>
	 *            <td>fuJian</td>
	 *            <td>附件</td>
	 *            </tr>
	 *            <tr>
	 *            <td>neiRong</td>
	 *            <td>内容</td>
	 *            </tr>
	 *            </table>
	 * @return JsonResult
	 */
	@ResponseBody
	@RequestMapping("/doAdd")
	public JsonResult doAdd(HttpServletRequest request) {
		WorkV workV = new WorkV();
		String biaoTi = request.getParameter("biaoTi");
		if (biaoTi != null && !biaoTi.isEmpty()) {
			workV.setBiaoTi(biaoTi);
		}

		String neiRong = request.getParameter("neiRong");
		if (neiRong != null && !neiRong.isEmpty()) {
			workV.setNeiRong(neiRong);
		}
		
		String courseId = request.getParameter("courseId");
		if (courseId != null && !courseId.isEmpty()) {
			workV.setCourseId(courseId);
		}
		
		String endTime = request.getParameter("endTime");
		if (endTime != null && !endTime.isEmpty()) {
			workV.setEndTime(endTime);
		}
		HttpSession session = request.getSession(false);
		if (session != null) {
			String creator = ((User) session.getAttribute(AppData.Session_User)).getXingMing();
			String creatorId = ((User) session.getAttribute(AppData.Session_User)).getId();
			workV.setCreator(creator);
			workV.setCreatorId(creatorId);
		}

	/*	String fuJian = request.getParameter("fuJianUrl");
		if (fuJian != null && !fuJian.isEmpty()) {
			workV.setFuJian(fuJian);
		}
		String fuJianName = request.getParameter("fuJianName");
		if (fuJianName != null && !fuJianName.isEmpty()) {
			workV.setFuJianName(fuJianName);
		}*/	
		boolean result = workService.add(workV);
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(workService.getMessage());
		return json;
	}
/*
	*//**
	 * 查看页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 *//*
	
	@RequestMapping("/workShow")
	public Model workShow(HttpServletRequest request, Model model) {
		model.addAttribute("title", "查看作业");
		WorkV notivev = new WorkV();
		String id = request.getParameter("id");
		notivev.setWorkId(id);
		String du = "已读";
		notivev.setDu(du);
		HttpSession session = request.getSession(false);
		if (session != null) {
			String creator = ((User) session.getAttribute(AppData.Session_User)).getXingMing();
			notivev.setCreator(creator);
		}
		workService.updeatDate(notivev);
		Work work = workService.show(id);
		model.addAttribute("work", work);
		return model;

	}*/

	/**
	 * 查看页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/show")
	public Model show(HttpServletRequest request, Model model) {
		model.addAttribute("title", "查看作业");
		String id = request.getParameter("id");
		Work work = workService.show(id);
		model.addAttribute("work", work);
		return model;

	}
	@ResponseBody
	@RequestMapping("/doGet")
	public JsonResult doGet(HttpServletRequest request) {
		// 接收数据
		String id = request.getParameter("id");
		// 执行操作
		Work parameter = workService.show(id);
		// 返回
		JsonResult json = new JsonResult();
		if (parameter == null)
			json.setState(false);
		else
			json.setState(true);
		json.setMessage(workService.getMessage());
		json.setData(parameter);
		return json;
	}
	/**
	 * 删除作业
	 * 
	 * @param request
	 *            <table>
	 *            <tr>
	 *            <td>id</td>
	 *            <td>新闻ID</td>
	 *            </tr>
	 *            </table>
	 * @return JsonResult
	 */
	@ResponseBody
	@RequestMapping("/doWorkRemove")
	public JsonResult doWorkRemove(HttpServletRequest request) {

		String id = request.getParameter("id");

		boolean result = workService.remove(id);

		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(workService.getMessage());

		return json;

	}

}