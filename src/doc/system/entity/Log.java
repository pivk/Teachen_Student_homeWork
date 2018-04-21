package doc.system.entity;
public class Log {
	@Override
	public String toString() {
		return "Log [id=" + id + ", biaoTi=" + biaoTi + ", neiRong=" + neiRong + ", date=" + date + ", userId=" + userId
				+ "]";
	}
	private String id;
	private String biaoTi;
	private String neiRong;
	private String date;
	private String userId;
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
	public String getNeiRong() {
		return neiRong;
	}
	public void setNeiRong(String neiRong) {
		this.neiRong = neiRong;
	}
	public String getDate() {
		return date;
	}
	public void setTimestamp(String date) {
		this.date = date;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
