package doc.work.view;

import doc.work.entity.Work;

public class WorkV extends Work{
 private String courseName;
 private String FName;

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
 
}
