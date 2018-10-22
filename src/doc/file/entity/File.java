package doc.file.entity;

public class File extends Tree{
	private String geShi;
	private String directoryId;
	private Integer size;
	private String originalName;
	private String score;

	public String getGeShi() {
		return geShi;
	}

	public void setGeShi(String geShi) {
		this.geShi = geShi;
	}

	public String getDirectoryId() {
		return directoryId;
	}

	public void setDirectoryId(String directoryId) {
		this.directoryId = directoryId;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	
}
