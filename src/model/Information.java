package model;

public class Information {
	private String parentId;
	private String title="";
	private String content="";
	
	
	
	public Information(String parentId, String title, String content) {
		this.parentId = parentId;
		this.title = title;
		this.content = content;
	}
	
	
	public String getParentId() {
		return parentId;
	}


	public void setParentId(String parentId) {
		this.parentId = parentId;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String toString()
	{
		return parentId + " " + title + " " + content + "\n";
	}
	
	
}
