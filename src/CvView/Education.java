package CvView;

import java.util.ArrayList;

public class Education {

	private ArrayList<String> eduSchoolName = new ArrayList<String>() ;
	private ArrayList<String> eduSchoolDepartman = new ArrayList<String>() ;
	private ArrayList<String> eduStartDate = new ArrayList<String>() ;
	private ArrayList<String> eduEndDate = new ArrayList<String>() ;
	private ArrayList<String> eduDescription = new ArrayList<String>() ;
	public ArrayList<String> getEduSchoolName() {
		return eduSchoolName;
	}
	public void setEduSchoolName(String schoolName) {
		this.eduSchoolName.add(schoolName);
	}
	public ArrayList<String> getEduSchoolDepartman() {
		return eduSchoolDepartman;
	}
	public void setEduSchoolDepartman(String schoolDepartman) {
		this.eduSchoolDepartman.add(schoolDepartman);
	}
	public ArrayList<String> getEduStartDate() {
		return eduStartDate;
	}
	public void setEduStartDate(String startDate) {
		this.eduStartDate.add(startDate);
	}
	public ArrayList<String> getEduEndDate() {
		return eduEndDate;
	}
	public void setEduEndDate(String endDate) {
		this.eduEndDate.add(endDate);
	}
	public ArrayList<String> getEduDescription() {
		return eduDescription;
	}
	public void setEduDescription(String description) {
		this.eduDescription.add(description);
	}
	
	

}
