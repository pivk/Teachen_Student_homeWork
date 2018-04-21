package doc.system.view;
import java.util.List;

import doc.system.entity.Power;
public class PowerTree extends Power{
	private List<PowerTree> nodes;
	public List<PowerTree> getNodes() {
		return nodes;
	}
	public void setNodes(List<PowerTree> nodes) {
		this.nodes = nodes;
	}
}
