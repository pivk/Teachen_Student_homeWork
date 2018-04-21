package doc.system.view;
import doc.system.entity.Unit;
public class UnitV extends Unit{
	private String parentName;
	public UnitV(){
		super();
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
