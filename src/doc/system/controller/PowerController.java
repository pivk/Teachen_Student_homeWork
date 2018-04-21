package doc.system.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import doc.common.AppData;
import doc.common.BaseController;
import doc.system.entity.Log;
import doc.system.entity.Power;
import doc.system.entity.User;
import doc.system.service.LogService;
import doc.system.service.PowerService;
import doc.system.view.PowerV;
import pushunsoft.common.JsonResult;
/**
 * PowerController
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping("/system/power")
public class PowerController extends BaseController {
	@Resource
	private PowerService powerService;
	@Resource
	private LogService logService;
	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 查询角色权限
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRolePower")
	public JsonResult getRolePower(HttpServletRequest request) {
		String roleId = request.getParameter("roleId");
		List<PowerV> list = powerService.getRolePower(roleId);
		JsonResult json = new JsonResult();
		json.setState(true);
		json.setData(list);
		return json;
	}
	/**
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveRolePower")
	public JsonResult saveRolePower(HttpServletRequest request) {
		String roleId = request.getParameter("roleId");
		String[] powers = request.getParameterValues("Power");
		List<Power> powerList = new ArrayList<Power>();
		if (powers != null && powers.length > 0) {
			for (String id : powers) {
				Power power = new Power();
				power.setId(id);
				powerList.add(power);
			}
		}
		boolean result = powerService.saveRolePower(roleId, powerList);
		JsonResult json = new JsonResult();
		json.setState(result);
		if(result){
			Log log = new Log();
			log.setNeiRong("");
			log.setBiaoTi(roleId+"添加角色权限");
			User user1 = (User) request.getSession().getAttribute(AppData.Session_User);
			log.setUserId(user1.getId());
			Date date2 =new Date();
			log.setTimestamp(dateFormatter.format(date2));
			logService.add(log);
		}
		return json;
	}
}