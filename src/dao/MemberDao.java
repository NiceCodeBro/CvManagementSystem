package dao;

import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import model.MemberSingleton;
import util.DBUtil;

public class MemberDao extends DBUtil{
	
	public int loginCheck(String loginUserName, String loginPassword)
	{
		Connection con = null;
		MemberSingleton member = MemberSingleton.getInstance();
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
				member.setId(id);
				member.setUsername(username);
				member.setPassword(pass);
				member.setRole(role);
				
				return 0;
			}
			else 	//invalid username or password
			{
				return -1;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
}
