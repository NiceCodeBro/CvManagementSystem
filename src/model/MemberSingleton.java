package model;

public class MemberSingleton {
	private int id;
	private String username="";
	private String password="";
	private String role="";
    public static MemberSingleton memberSingleton = null;
    private MemberSingleton() {
    }
    public static synchronized MemberSingleton getInstance() {
        if ( memberSingleton == null ) {
        	memberSingleton = new MemberSingleton();
        }
        return memberSingleton;
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public static MemberSingleton getMemberSingleton() {
		return memberSingleton;
	}
	public static void setMemberSingleton(MemberSingleton memberSingleton) {
		MemberSingleton.memberSingleton = memberSingleton;
	}
    
    
}
