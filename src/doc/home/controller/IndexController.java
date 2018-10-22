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
 * ï¿½ï¿½Ò³
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
	 * ï¿½ï¿½Ò³
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/index")
	public Model index(HttpServletRequest request, Model model) {
		model.addAttribute("title", "Ê×Ò³");
		User user = (User) request.getSession().getAttribute(AppData.Session_User);
		if (user != null) {
			model.addAttribute("userName", user.getXingMing());
		}
		return model;
	}

	/**
	 * ï¿½ï¿½Ó­Ò³
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/welcome")
	public Model welcome(HttpServletRequest request, Model model) {
		model.addAttribute("title", "»¶Ó­");
		return model;
	}

	/**
	 * ï¿½ï¿½Â½
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
	 * ï¿½Ë³ï¿½
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
	 * ï¿½Þ¸ï¿½ï¿½ï¿½ï¿½ï¿½
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/password")
	public Model password(HttpServletRequest request, Model model) {
		model.addAttribute("title", "ÐÞ¸ÄÃÜÂë");
		return model;
	}

	/**
	 * ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/me")
	public Model me(HttpServletRequest request, Model model) {
		model.addAttribute("title", "¸öÈË×ÊÁÏ");
		return model;
	}

	/**
	 * ï¿½ï¿½ï¿½ï¿½
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/help")
	public Model help(HttpServletRequest request, Model model) {
		model.addAttribute("title", "°ïÖú");
		return model;
	}

	/**
	 * ï¿½ï¿½ï¿½ï¿½
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/error")
	public Model error(HttpServletRequest request, Model model) {
		model.addAttribute("title", "´íÎóÒ³Ãæ¡¤");
		return model;
	}

	@ResponseBody
	@RequestMapping("/doLogin")
	public JsonResult doLogin(HttpServletRequest request) {
		// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
		String uid = request.getParameter("uid");
		String pwd = request.getParameter("pwd");
		// Ö´ï¿½Ð²ï¿½ï¿½ï¿½
		User user = homeService.login(uid, pwd);
		if (user != null) {
			// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ï¢
			HttpSession session = request.getSession();
			session.setAttribute(AppData.Session_User, user);
			// ï¿½ï¿½ï¿½ï¿½È¨ï¿½ï¿½
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
		// ï¿½ï¿½ï¿½ï¿½
		JsonResult json = new JsonResult();
		if (user == null) {
			json.setState(false);
			json.setMessage("ï¿½ï¿½Â½Ê§ï¿½ï¿½");
		} else {
			json.setState(true);
		}
		return json;
	}

	@ResponseBody
	@RequestMapping("/doMe")
	public JsonResult doGet(HttpServletRequest request) {
		// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
		String id = request.getParameter("id");
		// Ö´ï¿½Ð²ï¿½ï¿½ï¿½
		User user = homeService.get(id);
		// ï¿½ï¿½ï¿½ï¿½
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
		// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
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
		boolean result = homeService.editMima(user);
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(homeService.getMessage());
		return json;
	}

	/**
	 * ï¿½à¼­
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@LoginAnnotation
	@RequestMapping("/edit")
	public Model edit(HttpServletRequest request, Model model) {
		model.addAttribute("title", "ÐÞ¸Ä¸öÈË×ÊÁÏ");
		String id = request.getParameter("id");
		model.addAttribute("id", id);
		return model;
	}

	@ResponseBody
	@RequestMapping("/doEdit")
	public JsonResult doEdit(HttpServletRequest request) {
		// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
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
		// Ö´ï¿½Ð²ï¿½ï¿½ï¿½
		boolean result = homeService.edit(user);
		// ï¿½ï¿½ï¿½ï¿½
		JsonResult json = new JsonResult();
		json.setState(result);
		json.setMessage(homeService.getMessage());
		return json;
	}
}
