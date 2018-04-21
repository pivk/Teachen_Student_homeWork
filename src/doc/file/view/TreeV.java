package doc.file.view;

import doc.file.entity.Tree;

public class TreeV extends Tree{
	private String projectId;
	private String libraryId;
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getLibraryId() {
		return libraryId;
	}
	public void setLibraryId(String libraryId) {
		this.libraryId = libraryId;
	}
	
	
}
