package doc.home.view;
import java.util.List;
public class Menu {
	private String id;
	private String text;
	private String icon;
	private String url;
	private List<Menu> nodes;
	private boolean flag;
	private String unitName;
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Menu> getNodes() {
		return nodes;
	}
	public void setNodes(List<Menu> nodes) {
		this.nodes = nodes;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
}
