package doc.system.view;

import doc.system.entity.Log;

public class LogV extends Log{
	private String xingMing;

	public LogV() {
		super();
	}
	public String getXingMing() {
		return xingMing;
	}

	public void setXingMing(String xingMing) {
		this.xingMing = xingMing;
	}	
}
