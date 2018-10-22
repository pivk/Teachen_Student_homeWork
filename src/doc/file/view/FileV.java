package doc.file.view;

import doc.file.entity.File;

public class FileV extends File {
	private String unitId;
	private String Fname;


	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getFname() {
		return Fname;
	}

	public void setFname(String fname) {
		Fname = fname;
	}

}
