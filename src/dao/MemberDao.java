package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.mysql.jdbc.PreparedStatement;

import model.Member;
import util.DBUtil;

public class MemberDao extends DBUtil{
	private static Logger logger = LogManager.getLogger(MemberDao.class);

	public Member getMemberInf(String loginUserName, String loginPassword) 
	{
		logger.info("getMemberInf method | started. Someone is trying to login. login name:" + loginUserName + ".");
		
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
			logger.error("getMemberInf method | " + e.getMessage());

			member.setStatus(false);
		}
		finally {
			closeConnection(con);
			closePreparedSatement(ps);
			closeResultSet(rs);
		}
		logger.info("getMemberInf method | ended.");

		return member;
	}
	
	public List<Member> getAllMembers() 
	{
		logger.info("getAllMembers method | started.");

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
			logger.info("getAllMembers method | " + e.getMessage());
		}
		finally {
			closeConnection(con);
			closePreparedSatement(ps);
			closeResultSet(rs);
		}
		logger.info("getAllMembers method | ended.");

		return member;
	}
	
	public void deleteMember(int memberId) 
	{
		logger.info("deleteMember method | started. Member id:" + memberId  + ".");

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
			logger.error("deleteMember method | " + e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.error("deleteMember method | Rollback error. SQLException: " + e1.getMessage());
			}
		}
		finally {
			closeConnection(con);
			closePreparedSatement(ps);
		}
		logger.info("deleteMember method | ended. Member id:" + memberId  + ".");

	}
	public void updateMember(Member member) 
	{
		logger.info("updateMember method | started. Member id:" + member.getIdMember()  + ".");

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
			logger.error("updateMember method | " + e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.error("updateMember method | Rollback error.SQLException:" + e1.getMessage());
			}
		}
		finally {
			closeConnection(con);
			closePreparedSatement(ps);
		}
		logger.info("updateMember method | started. Member id:" + member.getIdMember()  + ".");

	}
	
	public void addNewMember(Member member) 
	{
		logger.info("addNewMember method | started. Member id:" + member.getIdMember()  + ".");

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
			logger.error("addNewMember method | " + e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.error("addNewMember method | Rollback error.SQLException:" + e1.getMessage());
			}
		}
		finally {
			closeConnection(con);
			closePreparedSatement(ps);

		}
		logger.info("addNewMember method | started. Member id:" + member.getIdMember()  + ".");

	}
}
