package dao;

import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import model.Member;
import util.DBUtil;

public class MemberDao extends DBUtil{
	
	public Member getMemberInf(String loginUserName, String loginPassword)
	{
		Connection con = null;
		//MemberSingleton member = MemberSingleton.getInstance();
		Member member = new Member();
		int id=0;
		String username="";
		String pass = "";
		String role = "";
		try
		{
			
			con = getConnection();
			String query = "SELECT * FROM Member  WHERE memberName= ? ";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, loginUserName);
			ResultSet rs = ps.executeQuery( );
			
			while (rs.next()) {
				id = rs.getInt("idMember");
				username = rs.getString("memberName");
				pass = rs.getString("memberPass");
				role = rs.getString("role");
			}
			
			if(username.equals(loginUserName) && pass.equals(loginPassword)) //if success
			{
				member.setIdMember(id);
				member.setMemberName(username);
				member.setMemberPass(pass);
				member.setRole(role);
				member.setStatus(true);
				return member;
			}
			else 	//invalid username or password
			{
				member.setStatus(false);
				return member;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			member.setStatus(false);
		}
		return member;
	}
}
