package modelContent;

public class JobExperienceUp {
	private String jobCompanyName;
	private String jobTitle;
	private String jobStartDate;
	private String jobEndDate;
	private String jobDescription;
	
	public String getJobCompanyName() {
		return jobCompanyName;
	}
	public void setJobCompanyName(String jobCompanyName) {
		this.jobCompanyName = jobCompanyName;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getJobStartDate() {
		return jobStartDate;
	}
	public void setJobStartDate(String jobStartDate) {
		this.jobStartDate = jobStartDate;
	}
	public String getJobEndDate() {
		return jobEndDate;
	}
	public void setJobEndDate(String jobEndDate) {
		this.jobEndDate = jobEndDate;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public int jobNumber() {
		return jobCompanyName.length();
	}
	@Override
	public String toString() {
		return "JobExperienceUp [jobCompanyName=" + jobCompanyName + ", jobTitle=" + jobTitle + ", jobStartDate="
				+ jobStartDate + ", jobEndDate=" + jobEndDate + ", jobDescription=" + jobDescription + "]";
	}
	
	
	
	
	
}
