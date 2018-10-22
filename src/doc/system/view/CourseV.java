package doc.system.view;

import doc.system.entity.Course;
import doc.system.entity.Role;

public class CourseV extends Course{
private String userId;
private String courseId;
private String userName;
private String xueqi;
private String late;
private String crunk;
private String normal;
private String str;
private String lunwenScore;

public String getLate() {
	return late;
}
public void setLate(String late) {
	this.late = late;
}
public String getCrunk() {
	return crunk;
}
public void setCrunk(String crunk) {
	this.crunk = crunk;
}
public String getNormal() {
	return normal;
}
public void setNormal(String normal) {
	this.normal = normal;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getCourseId() {
	return courseId;
}
public void setCourseId(String courseId) {
	this.courseId = courseId;
}
public String getXueqi() {
	return xueqi;
}
public void setXueqi(String xueqi) {
	this.xueqi = xueqi;
}
public String getStr() {
	return str;
}
public void setStr(String str) {
	this.str = str;
}
public String getLunwenScore() {
	return lunwenScore;
}
public void setLunwenScore(String lunwenScore) {
	this.lunwenScore = lunwenScore;
}

}
