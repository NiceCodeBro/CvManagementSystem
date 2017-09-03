package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import model.Member;
import util.DBUtil;

public class MemberDao extends DBUtil{
	
	public Member getMemberInf(String loginUserName, String loginPassword) 
	{
		Connection con = null;
		PreparedStatement ps = null ;
		ResultSet rs=null;
		Member member = new Member();
		int id=0;
		String username="";
		String pass = "";
		String role = "";
		try
		{
			
			con = getConnection();
			String query = "SELECT * FROM Member  WHERE memberName= ? ";
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, loginUserName);
			rs = ps.executeQuery( );
			
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
		finally {
			closeConnection(con);
			closePreparedSatement(ps);
			closeResultSet(rs);
		}
		return member;
	}
	
	public List<Member> getAllMembers() 
	{
		Connection con = null;
		PreparedStatement ps = null ;	
		ResultSet rs = null;
		List<Member> member = new ArrayList<Member>();
		
		try
		{
			
			con = getConnection();
			String query = "SELECT * FROM CvSystem.Member ";
			ps = (PreparedStatement) con.prepareStatement(query);
			rs = ps.executeQuery( );
			
			while (rs.next()) {
				Member m = new Member();
				m.setIdMember(rs.getInt("idMember"));
				m.setMemberName(rs.getString("memberName"));
				m.setMemberPass(rs.getString("memberPass"));
				m.setRole(rs.getString("role"));
				m.setStatus(true);
				
				member.add(m);
			}		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			closeConnection(con);
			closePreparedSatement(ps);
			closeResultSet(rs);
		}
		return member;
	}
	
	public void deleteMember(int memberId) 
	{
		Connection con = null;
		PreparedStatement ps = null ;		
		try
		{
			
			con = getConnection();
			con.setAutoCommit(false);
			String query = "DELETE FROM Member WHERE idMember=?";
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setInt(1, memberId);
			ps.executeUpdate();	
			con.commit();
		}
		catch(Exception e)
		{
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			closeConnection(con);
			closePreparedSatement(ps);
		}
	}
	public void updateMember(Member member) 
	{
		Connection con = null;
		PreparedStatement ps = null ;

		
		try
		{
			
			con = getConnection();
			con.setAutoCommit(false);
			String query = "UPDATE Member SET memberName = ?, memberPass = ?, role = ? WHERE idMember=? ";
			ps = (PreparedStatement) con.prepareStatement(query);
			System.out.println(member);
			ps.setString(1, member.getMemberName());
			ps.setString(2, member.getMemberPass());
			ps.setString(3, member.getRole());
			ps.setInt(4, member.getIdMember());
			
			ps.executeUpdate();
			con.commit();
		}
		catch(Exception e)
		{
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			closeConnection(con);
			closePreparedSatement(ps);
		}
	}
	
	public void addNewMember(Member member) 
	{
		Connection con = null;
		PreparedStatement ps = null ;

		try
		{ 
			con = getConnection();
			con.setAutoCommit(false);
			String query = "INSERT INTO Member (memberName,memberPass,role) VALUES (?,?,?)";
			ps = (PreparedStatement) con.prepareStatement(query);

			System.out.println(member);
			ps.setString(1, member.getMemberName().replaceAll("\\s+",""));
			ps.setString(2, member.getMemberPass());
			ps.setString(3, member.getRole());
			
			ps.executeUpdate();
			con.commit();
		}
		catch(Exception e)
		{
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			closeConnection(con);
			closePreparedSatement(ps);

		}
	}
}
