package modelContent;

import java.util.Arrays;

public class JobExperience {
	private String[] jobCompanyName;
	private String[] jobTitle;
	private String[] jobStartDate;
	private String[] jobEndDate;
	private String[] jobDescription;
	private int jobNumber;
	
	
	public String[] getJobCompanyName() {
		return jobCompanyName;
	}
	public void setJobCompanyName(String[] jobCompanyName) {
		this.jobCompanyName = jobCompanyName;
	}
	public String[] getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String[] jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String[] getJobStartDate() {
		return jobStartDate;
	}
	public void setJobStartDate(String[] jobStartDate) {
		this.jobStartDate = jobStartDate;
	}
	public String[] getJobEndDate() {
		return jobEndDate;
	}
	public void setJobEndDate(String[] jobEndDate) {
		this.jobEndDate = jobEndDate;
	}
	public String[] getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String[] jobDescription) {
		this.jobDescription = jobDescription;
	}
	public int jobNumber() {
		if(jobCompanyName == null){
			return 0;
		}
		return jobCompanyName.length;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JobExperience [jobCompanyName=");
		builder.append(Arrays.toString(jobCompanyName));
		builder.append(", jobTitle=");
		builder.append(Arrays.toString(jobTitle));
		builder.append(", jobStartDate=");
		builder.append(Arrays.toString(jobStartDate));
		builder.append(", jobEndDate=");
		builder.append(Arrays.toString(jobEndDate));
		builder.append(", jobDescription=");
		builder.append(Arrays.toString(jobDescription));
		builder.append(", jobNumber=");
		builder.append(jobNumber);
		builder.append("]");
		return builder.toString();
	}

	
	
	
	
			
}
