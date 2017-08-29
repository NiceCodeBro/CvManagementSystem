package model;

public class Cv {
	private String ownerUsername;
	private String ownerRole;
	private int idCv;
	private int memberId;
	private String cvName;
	private int deletedCv;
	private String addDate;
	
	public int getIdCv() {
		return idCv;
	}
	public void setIdCv(int idCv) {
		this.idCv = idCv;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getCvName() {
		return cvName;
	}
	public void setCvName(String cvName) {
		this.cvName = cvName;
	}
	public int getDeletedCv() {
		return deletedCv;
	}
	public void setDeletedCv(int deletedCv) {
		this.deletedCv = deletedCv;
	}
	public String getOwnerUsername() {
		return ownerUsername;
	}
	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}
	public String getOwnerRole() {
		return ownerRole;
	}
	public void setOwnerRole(String ownerRole) {
		this.ownerRole = ownerRole;
	}
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	
	
	
}
