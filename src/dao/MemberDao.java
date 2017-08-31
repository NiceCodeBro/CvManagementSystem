package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
			ps.close();
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
	
	public List<Member> getAllMembers()
	{
		Connection con = null;
		//MemberSingleton member = MemberSingleton.getInstance();
		List<Member> member = new ArrayList<Member>();
		
		try
		{
			
			con = getConnection();
	//		String query = "SELECT m.idMember, m.memberName, m.memberPass, m.role,COUNT(c.memberId) as Count FROM Member m INNER JOIN Cv c on c.memberId= m.idMember GROUP BY c.memberId, m.idMember";
			String query = "SELECT * FROM CvSystem.Member ";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ResultSet rs = ps.executeQuery( );
			
			while (rs.next()) {
				Member m = new Member();
				m.setIdMember(rs.getInt("idMember"));
				m.setMemberName(rs.getString("memberName"));
				m.setMemberPass(rs.getString("memberPass"));
				//m.setNumberOfCv(rs.getInt("Count"));
				m.setRole(rs.getString("role"));
				m.setStatus(true);
				
				member.add(m);
			}		
			
			ps.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return member;
	}
	
	public void deleteMember(int memberId)
	{
		Connection con = null;
		
		try
		{
			
			con = getConnection();
			String query = "DELETE FROM Member WHERE idMember=?";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ps.setInt(1, memberId);
			ps.executeUpdate();	
			ps.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void updateMember(Member member)
	{
	Connection con = null;
		
		try
		{
			
			con = getConnection();
			String query = "UPDATE Member SET memberName = ?, memberPass = ?, role = ? WHERE idMember=? ";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			System.out.println(member);
			ps.setString(1, member.getMemberName());
			ps.setString(2, member.getMemberPass());
			ps.setString(3, member.getRole());
			ps.setInt(4, member.getIdMember());
			
			ps.executeUpdate();
			ps.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void addNewMember(Member member)
	{
	Connection con = null;
		
		try
		{ 
			con = getConnection();
			String query = "INSERT INTO Member (memberName,memberPass,role) VALUES (?,?,?)";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			System.out.println(member);
			ps.setString(1, member.getMemberName());
			ps.setString(2, member.getMemberPass());
			ps.setString(3, member.getRole());
			
			ps.executeUpdate();
			ps.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
