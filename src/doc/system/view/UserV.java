package doc.system.view;
import java.util.List;

import doc.system.entity.Role;
import doc.system.entity.User;
public class UserV extends User {
	private List<Role> roles;
	private String unitName;
	public UserV() {
		super();
	}
	public UserV(List<Role> roles) {
		super();
		this.roles = roles;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
}
