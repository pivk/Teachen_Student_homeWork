package doc.system.entity;
public class Config {
	//是否自动上报数据
	private String autoReport;
	//是否自动提审
	private String autoApproveSubmit;
	//是否自动审核通过
	private String autoApprovePass;
	//上报地址
	private String reportUrl;
	//上传文件保存地址
	private String uploadPath;
	public Config() {
	}
	public String getAutoReport() {
		return autoReport;
	}
	public void setAutoReport(String autoReport) {
		this.autoReport = autoReport;
	}
	public String getReportUrl() {
		return reportUrl;
	}
	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	public String getAutoApproveSubmit() {
		return autoApproveSubmit;
	}
	public void setAutoApproveSubmit(String autoApproveSubmit) {
		this.autoApproveSubmit = autoApproveSubmit;
	}
	public String getAutoApprovePass() {
		return autoApprovePass;
	}
	public void setAutoApprovePass(String autoApprovePass) {
		this.autoApprovePass = autoApprovePass;
	}
	@Override
	public String toString() {
		return "Config [autoReport=" + autoReport + ", reportUrl=" + reportUrl + ", uploadPath=" + uploadPath + "]";
	}
}
