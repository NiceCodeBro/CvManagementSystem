package dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;	
import com.mysql.jdbc.PreparedStatement;

import model.Cv;
import model.Member;
import modelContent.CvContent;
import util.DBUtil;

public class CVDao extends DBUtil{

	public boolean addCv(CvContent c,Member m){
		Connection con = null;
		int lastInsertId;
		
		try{
			con = getConnection();
			int memberId = m.getIdMember();
			
			String query = "INSERT INTO Cv(memberId,cvName,deletedCv) VALUES(?,?,?)";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
				ps.setInt(1, memberId);
				ps.setString(2, c.getPersonal().getCvName());
				ps.setInt(3, 0);
			int isInsert = ps.executeUpdate();
			lastInsertId = (int) ps.getLastInsertID();
			
			query = "INSERT INTO Content(cvId,titleId,content) VALUES(?,?,?)";
			ps = (PreparedStatement) con.prepareStatement(query);
				ps.setInt(1, lastInsertId );
				ps.setInt(2, 8);
				ps.setString(3, c.getPersonal().getPersonalName());
			ps.executeUpdate();
			
			
			ps.close();
			
			
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		closeConnection(con);
		return false;
		
		
	}
	
	public List<Cv> listCvbyMember(Member m){
		Connection con = null;
		List<Cv> listCv = new ArrayList<Cv>();
		try{
			con = getConnection();
			int memberId = m.getIdMember();
			
			String query = "SELECT * FROM Cv WHERE memberId = ? AND deletedCv = 0";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ps.setInt(1, memberId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Cv cv = new Cv();
				cv.setCvName(rs.getString("cvName"));
				cv.setIdCv(rs.getInt("idCv"));
				cv.setMemberId(rs.getInt("memberId"));
				cv.setDeletedCv(rs.getInt("deletedCv"));
					listCv.add(cv);
			}
			rs.close();
			ps.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		closeConnection(con);
		return listCv;
	}
	
	public List<Cv> listCvByManager(){
		Connection con = null;
		List<Cv> listCv = new ArrayList<Cv>();
		try{
			con = getConnection();
			
			String query = "SELECT * FROM Cv";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Cv cv = new Cv();
				
				cv.setCvName(rs.getString("cvName"));
				cv.setIdCv(rs.getInt("idCv"));
				cv.setMemberId(rs.getInt("memberId"));
				cv.setDeletedCv(rs.getInt("deletedCv"));
					listCv.add(cv);
			}
			rs.close();
			ps.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		closeConnection(con);
		return listCv;
	}
}