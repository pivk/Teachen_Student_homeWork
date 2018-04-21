package doc.home.controller;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import doc.home.service.HomeService;
import doc.home.view.Menu;
import doc.system.entity.User;
import doc.system.view.UserV;
import pushunsoft.common.JsonResult;

/**
 * ��ҳ
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping("/home")
public class IndexController extends BaseController {
	@Resource
	private HomeService homeService;

	@RequestMapping("/redirect")
	public String redirect(HttpServletRequest request, Model model) {
		model.addAttribute("url", "<script>top.location.href='/home/login.action'</script>");
		return "/public/redirect";
		
	}
	/**
	 * ��ҳ
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/index")
	public Model index(HttpServletRequest request, Model model) {
		model.addAttribute("title", "��ҳ");
		User user = (User) request.getSession().getAttribute(AppData.Session_User);
		if (user != null) {
			model.addAttribute("userName", user.getXingMing());
		}
		return model;
	}

	/**
	 * ��ӭҳ
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/welcome")
	public Model welcome(HttpServletRequest request, Model model) {
		model.addAttribute("title", "��ӭ");
		return model;
	}

	/**
	 * ��½
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public Model login(HttpServletRequest request, Model model) {
		model.addAttribute("uid", "admin");
		model.addAttribute("pwd", "123");
		return model;
	}

	/**
	 * �˳�
	 * 
	 * @param request
	 * @param model
	 */
	@RequestMapping("/logout")
	public void logout(HttpSession session, HttpServletResponse response) {
		session.invalidate();
		try {
			response.sendRedirect("/home/login.action");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �޸�����
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/password")
	public Model password(HttpServletRequest request, Model model) {
		model.addAttribute("title", "�޸�����");
		return model;
	}

	/**
	 * ��������
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/me")
	public Model me(HttpServletRequest request, Model model) {
		model.addAttribute("title", "��������");
		return model;
	}

	/**
	 * ����
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/help")
	public Model help(HttpServletRequest request, Model model) {
		model.addAttribute("title", "����");
		return model;
	}

	/**
	 * ����
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/error")
	public Model error(HttpServletRequest request, Model model) {
		model.addAttribute("title", "����");
		return model;
	}

	@ResponseBody
	@RequestMapping("/doLogin")
	public JsonResult doLogin(HttpServletRequest request) {
		// ��������
		String uid = request.getParameter("uid");
		String pwd = request.getParameter("pwd");
		// ִ�в���
		User user = homeService.login(uid, pwd);
		if (user != null) {
			// ������Ϣ
			HttpSession session = request.getSession();
			session.setAttribute(AppData.Session_User, user);
			// ����Ȩ��
			List<Menu> menuList = homeService.MakeMenu(uid);
			ObjectMapper mapper = new ObjectMapper();
			String menuJson = "[]";
			try {
				menuJson = mapper.writeValueAsString(menuList);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			session.setAttribute("menu", menuJson);
		}
		// ����
		JsonResult json = new JsonResult();
		if (user == null) {
			json.setState(false);
			json.setMessage("��½ʧ��");
		} else {
			json.setState(true);
		}
		return json;
	}

	@ResponseBody
	@RequestMapping("/doMe")
	public JsonResult doGet(HttpServletRequest request) {
		// ��������
		String id = request.getParameter("id");
		// ִ�в���
		User user = homeService.get(id);
		// ����
		JsonResult json = new JsonResult();
		if (user == null)
			json.setState(false);
		else
			json.setState(true);
		json.setMessage(homeService.getMessage());
		json.setData(user);
		return json;
	}

	@ResponseBody
	@RequestMapping("/doPassword")
	public JsonResult doPassword(HttpServletRequest request) {
		// ��������
		UserV user = new UserV();
		String id = request.getParameter("uid");
		if (id != null && !id.isEmpty()) {
			user.setId(id);
		}
		String newMiMa = request.getParameter("newMiMa");
		if (newMiMa != null && !newMiMa.isEmpty()) {
			user.setMima(newMiMa);
		}
		user.setId(id);
		user.setMima(newMiMa);
		// ִ�в���
		boolean result = homeService.editMima(user);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(homeService.getMessage());
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
		model.addAttribute("title", "�޸��û�");
		String id = request.getParameter("id");
		model.addAttribute("id", id);
		return model;
	}

	@ResponseBody
	@RequestMapping("/doEdit")
	public JsonResult doEdit(HttpServletRequest request) {
		// ��������
		User user = new User();
		String id = request.getParameter("id");
		if (id != null && !id.isEmpty()) {
			user.setId(id);
		}
		String xingMing = request.getParameter("xingMing");
		if (xingMing != null && !xingMing.isEmpty()) {
			user.setXingMing(xingMing);
		}
		String mobile = request.getParameter("mobile");
		if (mobile != null && !mobile.isEmpty()) {
			user.setMobile(mobile);
		}
		String email = request.getParameter("email");
		if (email != null && !email.isEmpty()) {
			user.setEmail(email);
		}
		String unitId = request.getParameter("unitId");
		if (unitId != null && !"-".equals(unitId)) {
			user.setUnitId(unitId);
		}
		// ִ�в���
		boolean result = homeService.edit(user);
		// ����
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(homeService.getMessage());
		return json;
	}
}
