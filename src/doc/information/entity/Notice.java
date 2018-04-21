package doc.information.entity;

/**
 * 通知
 * 
 * @author Administrator
 *
 */
public class Notice {

	private String id;
	private String biaoTi;
	private String neiRong;
	private String fuJian;
	private String fuJianName;
	private String createTime;
	private String creator;

	public String getFuJianName() {
		return fuJianName;
	}

	public void setFuJianName(String fuJianName) {
		this.fuJianName = fuJianName;
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

	public String getNeiRong() {
		return neiRong;
	}

	public void setNeiRong(String neiRong) {
		this.neiRong = neiRong;
	}

	public String getFuJian() {
		return fuJian;
	}

	public void setFuJian(String fuJian) {
		this.fuJian = fuJian;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

}
