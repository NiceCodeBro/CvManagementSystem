package CvView;

import java.util.ArrayList;

public class JobExperience {
	private ArrayList<String> jobCompanyName = new ArrayList<String>() ;
	private ArrayList<String> jobTitle = new ArrayList<String>() ;
	private ArrayList<String> jobStartDate = new ArrayList<String>() ;
	private ArrayList<String> jobEndDate = new ArrayList<String>() ;
	private ArrayList<String> jobDescription = new ArrayList<String>() ;
	public ArrayList<String> getJobCompanyName() {
		return jobCompanyName;
	}
	public void setJobCompanyName(String CompanyName) {
		jobCompanyName.add(CompanyName);
	}
	public ArrayList<String> getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jTitle) {
		jobTitle.add(jTitle);
	}
	public ArrayList<String> getJobStartDate() {
		return jobStartDate;
	}
	public void setJobStartDate(String jStartDate) {
		jobStartDate.add(jStartDate);
	}
	public ArrayList<String> getJobEndDate() {
		return jobEndDate;
	}
	public void setJobEndDate(String endDate) {
		jobEndDate.add(endDate);
	}
	public ArrayList<String> getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jDescription) {
		jobDescription.add(jDescription);
	}
	
	
	
	
	
	
	
			
}
