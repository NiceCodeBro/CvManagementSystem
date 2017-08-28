package modelContent;

import java.util.List;

public class CvContent {

	private Personal personal;
	private JobExperience jobExperience;
	private Education education;
	private Project project;
	private ForeignLanguage foreign;
	private Skill skill;
	private Courses courses;
	private Certificate certificate;
	private Publication publication;
	
	private List<JobExperienceUp> jobExperiences;
	private List<EducationUp> educations;
	private List<ForeignLanguageUp> foreigns;
	
	
	public Personal getPersonal() {
		return personal;
	}
	public void setPersonal(Personal personal) {
		this.personal = personal;
	}
	public JobExperience getJobExperience() {
		return jobExperience;
	}
	public void setJobExperience(JobExperience jobExperience) {
		this.jobExperience = jobExperience;
	}
	public Education getEducation() {
		return education;
	}
	public void setEducation(Education education) {
		this.education = education;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public ForeignLanguage getForeign() {
		return foreign;
	}
	public void setForeign(ForeignLanguage foreign) {
		this.foreign = foreign;
	}
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	public Courses getCourses() {
		return courses;
	}
	public void setCourses(Courses courses) {
		this.courses = courses;
	}
	public Certificate getCertificate() {
		return certificate;
	}
	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}
	public Publication getPublication() {
		return publication;
	}
	public void setPublication(Publication publication) {
		this.publication = publication;
	}
	public List<JobExperienceUp> getJobExperiences() {
		return jobExperiences;
	}
	public void setJobExperiences(List<JobExperienceUp> list) {
		this.jobExperiences = list;
	}
	public List<EducationUp> getEducations() {
		return educations;
	}
	public void setEducations(List<EducationUp> educations) {
		this.educations = educations;
	}
	public List<ForeignLanguageUp> getForeigns() {
		return foreigns;
	}
	public void setForeigns(List<ForeignLanguageUp> foreigns) {
		this.foreigns = foreigns;
	}
	
	
	
	
}
