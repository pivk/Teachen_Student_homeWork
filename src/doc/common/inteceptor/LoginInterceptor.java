package doc.common.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import doc.common.AppData;
import doc.common.annotation.LoginAnnotation;
import doc.system.entity.User;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(!(handler instanceof HandlerMethod)) return true;
		HandlerMethod methodHandler = (HandlerMethod) handler;
		LoginAnnotation auth = methodHandler.getMethodAnnotation(LoginAnnotation.class);
		if (auth == null || !auth.check())
			return true;
//		User user = (User) request.getSession().getAttribute(AppData.Session_User);
//		if (user == null) {
//			response.sendRedirect("/home/redirect.action");
//			return false;
//		}
		return true;
	}
}
