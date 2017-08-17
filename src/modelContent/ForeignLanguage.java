package modelContent;

public class ForeignLanguage {
	private String[] foreignName;
	private String[] foreignLevel;
	
	public int foreignNumber(){
		return foreignName.length;
	}
	
	public String[] getForeignName() {
		return foreignName;
	}
	public void setForeignName(String[] foreignName) {
		this.foreignName = foreignName;
	}
	public String[] getForeignLevel() {
		return foreignLevel;
	}
	public void setForeignLevel(String[] foreignLevel) {
		this.foreignLevel = foreignLevel;
	}
	
	
}
