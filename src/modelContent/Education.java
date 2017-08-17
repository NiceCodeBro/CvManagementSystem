package modelContent;

public class Education {
	private String[] eduSchoolName;
	private String[] eduSchoolDepartman;
	private String[] eduStartDate;
	private String[] eduEndDate;
	private String[] eduContinue;
	private String[] eduDescription;
	
	public int eduNumber(){
		return eduSchoolName.length;
	}
	
	public String[] getEduSchoolName() {
		return eduSchoolName;
	}
	public void setEduSchoolName(String[] eduSchoolName) {
		this.eduSchoolName = eduSchoolName;
	}
	public String[] getEduSchoolDepartman() {
		return eduSchoolDepartman;
	}
	public void setEduSchoolDepartman(String[] eduSchoolDepartman) {
		this.eduSchoolDepartman = eduSchoolDepartman;
	}
	public String[] getEduStartDate() {
		return eduStartDate;
	}
	public void setEduStartDate(String[] eduStartDate) {
		this.eduStartDate = eduStartDate;
	}
	public String[] getEduEndDate() {
		return eduEndDate;
	}
	public void setEduEndDate(String[] eduEndDate) {
		this.eduEndDate = eduEndDate;
	}
	public String[] getEduContinue() {
		return eduContinue;
	}
	public void setEduContinue(String[] eduContinue) {
		this.eduContinue = eduContinue;
	}
	public String[] getEduDescription() {
		return eduDescription;
	}
	public void setEduDescription(String[] eduDescription) {
		this.eduDescription = eduDescription;
	}
	

	

}
