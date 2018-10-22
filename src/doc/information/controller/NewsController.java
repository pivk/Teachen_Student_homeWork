package doc.information.controller;

import java.util.List;

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
import doc.information.entity.Notice;
import doc.information.service.DoorService;
import doc.information.service.NoticeService;
import doc.information.view.DoorV;
import doc.information.view.NoticeV;
import doc.system.entity.User;
import doc.system.service.UnitService;
import doc.system.service.UserService;
import pushunsoft.common.JsonResult;
import pushunsoft.common.PageData;

/**
 * NewsController
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping("/information")
public class NewsController extends BaseController {
	@Resource
	UserService userServer;

	@Resource
	NoticeService noticeService;
	@Resource
	DoorService doorService;


	@Resource
	UnitService serverUnit;


	/**
	 * 分页查询页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/noticeAdminPage")
	public Model noticeAdminPage(HttpServletRequest request, Model model) {

		String action = "通知管理";
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
	
	@RequestMapping("/noticePage")
	public Model noticePage(HttpServletRequest request, Model model) {

		String action = "通知";
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
	@RequestMapping("/doNoticePage")
	public JsonResult doNoticePage(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		Notice notice = new Notice();
		String biaoti = request.getParameter("biaoti");
		if (biaoti != null && !biaoti.isEmpty()) {
			notice.setBiaoTi(biaoti);
		}
		PageData<Notice> pageData = noticeService.getNoticePage(page, notice);

		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(pageData);
		return json;
	}

	/**
	 * 分页查询通知
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
	 * @return JsonResult，data格式参�?? {@link oa.meeting.entity.Notice
	 *         oa.meeting.entity.News}
	 */
	@ResponseBody
	@RequestMapping("/doNoticePageUser")
	public JsonResult doNoticePageUser(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		NoticeV noticev = new NoticeV();

		String biaoti = request.getParameter("biaoti");
		if (biaoti != null && !biaoti.isEmpty()) {
			noticev.setBiaoTi(biaoti);
		}

		PageData<Notice> pageData = noticeService.getNoticePage(page, noticev);

		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(pageData);
		return json;
	}

	/**
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
	@RequestMapping("/doNoticeVPageUser")
	public JsonResult doNoticeVPageUser(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		NoticeV noticev = new NoticeV();
		String biaoti = request.getParameter("biaoti");
		if (biaoti != null && !biaoti.isEmpty()) {
			noticev.setBiaoTi(biaoti);
		}
		String userId = request.getParameter("userId");
		if (userId == null) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				String userid = ((User) session.getAttribute(AppData.Session_User)).getId();
				noticev.setUserId(userid);
			}
		} else {
			noticev.setUserId(userId);
		}

		PageData<NoticeV> pageData = noticeService.getNoticeVPage(page, noticev);
		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(pageData);
		return json;
	}

	/**
	 * 修改通知
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/noticeEdit")
	public Model noticeEdit(HttpServletRequest request, Model model) {
		model.addAttribute("title", "修改通知");
		String id = request.getParameter("id");
		model.addAttribute("id", id);
		return model;

	}

	/**
	 * 执行修改通知
	 * 
	 * @param request
	 *            <table>
	 *            <tr>
	 *            <td>id</td>
	 *            <td>通知ID</td>
	 *            </tr>
	 *            <tr>
	 *            <td>biaoTi</td>
	 *            <td>标题</td>
	 *            </tr>
	 *            <tr>
	 *            <td>creator</td>
	 *            <td>创建�?</td>
	 *            </tr>
	 *            <tr>
	 *            <td>neiRong</td>
	 *            <td>内容</td>
	 *            </tr>
	 *            </table>
	 * @return JsonResult
	 */
	@ResponseBody
	@RequestMapping("/doNoticeEdit")
	public JsonResult doNoticeEdit(HttpServletRequest request) {

		String id = request.getParameter("id");
		Notice notice = new Notice();
		notice.setId(id);
		String biaoTi = request.getParameter("biaoTi");
		if (biaoTi != null && !biaoTi.isEmpty()) {
			notice.setBiaoTi(biaoTi);
		}
		String fuJian = request.getParameter("fuJianUrl");
		if (fuJian != null && !fuJian.isEmpty()) {
			notice.setFuJian(fuJian);
		}
		String fuJianName = request.getParameter("fuJianName");
		if (fuJianName != null && !fuJianName.isEmpty()) {
			notice.setFuJianName(fuJianName);
		}HttpSession session = request.getSession(false);
		if (session != null) {
			String creator = ((User) session.getAttribute(AppData.Session_User)).getXingMing();
			notice.setCreator(creator);
		}
		String neiRong = request.getParameter("neiRong");
		if (neiRong != null && !neiRong.isEmpty()) {
			notice.setNeiRong(neiRong);
		}
		boolean result = noticeService.noticeEdit(notice);
		// 返回
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(noticeService.getMessage());
		return json;

	}

	/**
	 * 添加通知
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/noticeAdd")
	public Model noticeAdd(HttpServletRequest request, Model model) {
		model.addAttribute("title", "新增通知");
		return model;

	}

	/**
	 * 添加通知
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
	@RequestMapping("/doNoticeAdd")
	public JsonResult doNoticeAdd(HttpServletRequest request) {
		NoticeV noticeV = new NoticeV();
		String zeRen = request.getParameter("zeRen");
		if (zeRen != null && !zeRen.isEmpty()) {
			noticeV.setUserId(zeRen);
		}

		String biaoTi = request.getParameter("biaoTi");
		if (biaoTi != null && !biaoTi.isEmpty()) {
			noticeV.setBiaoTi(biaoTi);
		}

		String neiRong = request.getParameter("neiRong");
		if (neiRong != null && !neiRong.isEmpty()) {
			noticeV.setNeiRong(neiRong);
		}

		HttpSession session = request.getSession(false);
		if (session != null) {
			String creator = ((User) session.getAttribute(AppData.Session_User)).getXingMing();
			noticeV.setCreator(creator);
		}

		String fuJian = request.getParameter("fuJianUrl");
		if (fuJian != null && !fuJian.isEmpty()) {
			noticeV.setFuJian(fuJian);
		}
		String fuJianName = request.getParameter("fuJianName");
		if (fuJianName != null && !fuJianName.isEmpty()) {
			noticeV.setFuJianName(fuJianName);
		}
		boolean result = noticeService.add(noticeV);
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(noticeService.getMessage());
		return json;
	}

	/**
	 * 查看页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/noticeShow")
	public Model noticeShow(HttpServletRequest request, Model model) {
		model.addAttribute("title", "查看通知");
		NoticeV notivev = new NoticeV();
		String id = request.getParameter("id");
		notivev.setNoticeId(id);
		String du = "已读";
		notivev.setDu(du);
		HttpSession session = request.getSession(false);
		if (session != null) {
			String creator = ((User) session.getAttribute(AppData.Session_User)).getXingMing();
			notivev.setCreator(creator);
		}
		noticeService.updeatDate(notivev);
		Notice notice = noticeService.show(id);
		model.addAttribute("notice", notice);
		return model;

	}

	/**
	 * 查看页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/noticeHtml")
	public Model noticeAdminShow(HttpServletRequest request, Model model) {
		model.addAttribute("title", "查看通知");
		String id = request.getParameter("id");
		Notice notice = noticeService.show(id);
		model.addAttribute("notice", notice);
		return model;

	}

	/**
	 * 删除通知
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
	@RequestMapping("/doNoticeRemove")
	public JsonResult doNoticeRemove(HttpServletRequest request) {

		String id = request.getParameter("id");

		boolean result = noticeService.remove(id);

		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(noticeService.getMessage());

		return json;

	}

	/**
	 * 删除多个通知
	 * 
	 * @param request
	 *            <table>
	 *            <tr>
	 *            <td>id</td>
	 *            <td>登录名（id指代多个登录名）</td>
	 *            </tr>
	 *            </table>
	 * @return JsonResult
	 */
	@ResponseBody
	@RequestMapping("/deleteNotice")
	public JsonResult deleteNotice(HttpServletRequest request) {
		// 接收数据
		String id = request.getParameter("id");
		// 执行操作
		boolean result = noticeService.deleteNotices(id);
		// 返回
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(noticeService.getMessage());
		return json;
	}
	/**
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
	@RequestMapping("/doDoorPage")
	public JsonResult doDoorPage(HttpServletRequest request) {
		DoorV door = new DoorV();
		String biaoti = request.getParameter("biaoti");
		if (biaoti != null && !biaoti.isEmpty()) {
			door.setBiaoTi(biaoti);
		}
		HttpSession session = request.getSession(false);
		if (session != null) {
			String creator = ((User) session.getAttribute(AppData.Session_User)).getId();
			door.setUserId(creator);
		}
		PageData<DoorV> pageData = doorService.getDoorVPage( door);
		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(pageData);
		return json;
	}

}