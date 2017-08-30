package modelContent;

import java.io.File;

public class Personal {
	private String cvName;
	private String personalName;
	private String personalTitle;
	private String personalObjectives;
	private String personalDateofBirth;
	private String personalCellPhone;
	private String personalOfficePhone;
	private String personalAddress;
	private String personalMaritalStatus;
	private String personalPhoto;
	private String personalMail;
	
	
	
	public String getCvName() {
		return cvName;
	}
	public void setCvName(String cvName) {
		this.cvName = cvName;
	}
	public String getPersonalName() {
		return personalName;
	}
	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}
	public String getPersonalTitle() {
		return personalTitle;
	}
	public void setPersonalTitle(String personalTitle) {
		this.personalTitle = personalTitle;
	}
	public String getPersonalObjectives() {
		return personalObjectives;
	}
	public void setPersonalObjectives(String personalObjectives) {
		this.personalObjectives = personalObjectives;
	}
	public String getPersonalDateofBirth() {
		return personalDateofBirth;
	}
	public void setPersonalDateofBirth(String personalDateofBirth) {
		this.personalDateofBirth = personalDateofBirth;
	}
	public String getPersonalCellPhone() {
		return personalCellPhone;
	}
	public void setPersonalCellPhone(String personalCellPhone) {
		this.personalCellPhone = personalCellPhone;
	}
	public String getPersonalOfficePhone() {
		return personalOfficePhone;
	}
	public void setPersonalOfficePhone(String personalOfficePhone) {
		this.personalOfficePhone = personalOfficePhone;
	}
	public String getPersonalAddress() {
		return personalAddress;
	}
	public void setPersonalAddress(String personalAddress) {
		this.personalAddress = personalAddress;
	}
	public String getPersonalMaritalStatus() {
		return personalMaritalStatus;
	}
	public void setPersonalMaritalStatus(String personalMaritalStatus) {
		this.personalMaritalStatus = personalMaritalStatus;
	}
	public String getPersonalPhoto() {
		return personalPhoto;
	}
	public void setPersonalPhoto(String personalPhoto) {
		this.personalPhoto = personalPhoto;
		
	}
	
	public String getPersonalMail() {
		return personalMail;
	}
	public void setPersonalMail(String personalMail) {
		this.personalMail = personalMail;
	}
	
	@Override
	public String toString() {
		return "Personal [cvName=" + cvName + ", personalName=" + personalName + ", personalTitle=" + personalTitle
				+ ", personalObjectives=" + personalObjectives + ", personalDateofBirth=" + personalDateofBirth
				+ ", personalCellPhone=" + personalCellPhone + ", personalOfficePhone=" + personalOfficePhone
				+ ", personalAddress=" + personalAddress + ", personalMaritalStatus=" + personalMaritalStatus
				+ ", personalPhoto=" + personalPhoto + "]";
	}
	
	
	
	
	
	
	
	
}
