package doc.information.view;

import doc.information.entity.Notice;

public class NoticeV extends Notice {
	private String noticeId;
    private	String userId;//用户id
    private String du;//是否已读
	private String duTime;//已读时间
	public String getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDu() {
		return du;
	}
	public void setDu(String du) {
		this.du = du;
	}
	public String getDuTime() {
		return duTime;
	}
	public void setDuTime(String duTime) {
		this.duTime = duTime;
	}
	
	
}
