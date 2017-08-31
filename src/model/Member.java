package model;

public class Member {

	private int idMember;
	private String memberName;
	private String memberPass;
	private String role="";
//	private int numberOfCv;
	private boolean status=false; // It is used when the login experiment is done.

	public int getIdMember() {
		return idMember;
	}
	public void setIdMember(int idMember) {
		this.idMember = idMember;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberPass() {
		return memberPass;
	}
	public void setMemberPass(String memberPass) {
		this.memberPass = memberPass;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
//	public int getNumberOfCv() {
//		return numberOfCv;
//	}
//	public void setNumberOfCv(int numberOfCv) {
//		this.numberOfCv = numberOfCv;
//	}
	public String toString()
	{
		return idMember + " " + memberName + " " + memberPass + " " + role;
	}
}
