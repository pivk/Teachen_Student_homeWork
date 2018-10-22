package doc.information.entity;

/**
 * 门户
 * 
 * @author Administrator
 *
 */
public class Door {
	private String id;
	private String biaoTi;
	private String url;
	private String nextName;
	private String memo;
	private String creator;
	private String userId;
	private String createTime;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBiaoTi() {
		return biaoTi;
	}
	public void setBiaoTi(String biaoTi) {
		this.biaoTi = biaoTi;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNextName() {
		return nextName;
	}
	public void setNextName(String nextName) {
		this.nextName = nextName;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}




}
