package doc.system.entity;
public class Config {
	//�Ƿ��Զ��ϱ�����
	private String autoReport;
	//�Ƿ��Զ�����
	private String autoApproveSubmit;
	//�Ƿ��Զ����ͨ��
	private String autoApprovePass;
	//�ϱ���ַ
	private String reportUrl;
	//�ϴ��ļ������ַ
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
