package POJO;

import java.util.List;

public class Courses {
private List<webAutomationCourse> webAutomation;
private List<apiCourse> api;
private List<mobileCourse> mobile;
public List<webAutomationCourse> getWebAutomation() {
	return webAutomation;
}
public void setWebAutomation(List<webAutomationCourse> webAutomation) {
	this.webAutomation = webAutomation;
}
public List<apiCourse> getApi() {
	return api;
}
public void setApi(List<apiCourse> api) {
	this.api = api;
}
public List<mobileCourse> getMobile() {
	return mobile;
}
public void setMobile(List<mobileCourse> mobile) {
	this.mobile = mobile;
}


}
