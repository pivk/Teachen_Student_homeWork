package doc.file.view;

import doc.file.entity.Directory;

public class DirectoryV extends Directory{
 private String courseName;
 private String FName;
 private int countshu;
 private int zong;

public String getCourseName() {
	return courseName;
}

public void setCourseName(String courseName) {
	this.courseName = courseName;
}

public String getFName() {
	return FName;
}

public void setFName(String fName) {
	FName = fName;
}

public int getCountshu() {
	return countshu;
}

public void setCountshu(int countshu) {
	this.countshu = countshu;
}

public int getZong() {
	return zong;
}

public void setZong(int zong) {
	this.zong = zong;
}


}
