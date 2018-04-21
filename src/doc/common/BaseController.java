package doc.common;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
/**
 * Controller»ùÀà
 * @author jerry
 *
 */
public class BaseController {
	protected Logger logger = LogManager.getLogger(this.getClass());
	@ModelAttribute
	public void init(HttpServletRequest request, Model model) {
		model.addAttribute("appName", AppData.App_Name);
		model.addAttribute("appVersion", AppData.App_Version);
	}
}
