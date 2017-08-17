package dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;	
import com.mysql.jdbc.PreparedStatement;

import model.Cv;
import model.Member;
import model.MemberSingleton;
import modelContent.CvContent;
import util.DBUtil;

public class CVDao extends DBUtil{
	int cvId,loopSize;
	Connection con = null;
	PreparedStatement ps;

	public boolean addCv(CvContent c,Member m){
		Connection con = null;
		
		
		try{
			con = getConnection();
			int memberId = m.getIdMember();
			
			//Cv ekleme
			String query = "INSERT INTO Cv(memberId,cvName,deletedCv) VALUES(?,?,?)";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
				ps.setInt(1, memberId);
				ps.setString(2, c.getPersonal().getCvName());
				ps.setInt(3, 0);
			int isInsert = ps.executeUpdate();
			
			//Eklenen Cvnin ID si
			cvId = (int) ps.getLastInsertID();
			
			//Personal Info Ekleme
			insertCvtoDB(cvId, 8, c.getPersonal().getPersonalName());
			insertCvtoDB(cvId, 29, c.getPersonal().getPersonalTitle());
			insertCvtoDB(cvId, 10,c.getPersonal().getPersonalObjectives());
			insertCvtoDB(cvId, 4, c.getPersonal().getPersonalDateofBirth());
			insertCvtoDB(cvId, 28,c.getPersonal().getPersonalCellPhone());
			insertCvtoDB(cvId, 11, c.getPersonal().getPersonalOfficePhone());
			insertCvtoDB(cvId, 13, c.getPersonal().getPersonalAddress());
			insertCvtoDB(cvId, 5, c.getPersonal().getPersonalMaritalStatus());
			insertCvtoDB(cvId, 9,c.getPersonal().getPersonalPhoto());
			
			//Job Experience ekleme String[] ifade olduğu için loop yapısı kullanıldı
			int numberofJob = c.getJobExperience().jobNumber();
			
			for (loopSize=0;loopSize<numberofJob;loopSize++){
				insertCvtoDB(cvId, 14, c.getJobExperience().getJobCompanyName()[loopSize]);
				insertCvtoDB(cvId, 15, c.getJobExperience().getJobTitle()[loopSize]);
				insertCvtoDB(cvId, 16, c.getJobExperience().getJobStartDate()[loopSize]);
				insertCvtoDB(cvId, 30, c.getJobExperience().getJobEndDate()[loopSize]);
				insertCvtoDB(cvId, 17, c.getJobExperience().getJobDescription()[loopSize]);
				
			}
			

			
			
			
			
			
			ps.close();
			
			
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		closeConnection(con);
		return false;
		
		
	}
	
	//insert işlemlerinde kullanılmak üzere hazırlandı
	public void insertCvtoDB(int cvId,int titleId,String c){
		try{
			con = getConnection();
			String query = "INSERT INTO Content(cvId,titleId,content) VALUES(?,?,?)";
			ps = (PreparedStatement) con.prepareStatement(query);
				ps.setInt(1, cvId );
				ps.setInt(2, titleId);
				ps.setString(3, c);
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public List<Cv> listCvbyMember(){
		Connection con = null;
		List<Cv> listCv = new ArrayList<Cv>();
		try{
			con = getConnection();
			
			String query = "SELECT * FROM Cv WHERE memberId = ? AND deletedCv = 0";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ps.setInt(1, MemberSingleton.getInstance().getId());
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
	
	public void deleteCvByRole(int cvId)
	{
		Connection con = null;
		
		if(MemberSingleton.getInstance().getRole().equals("Member"))
		{
			try
			{
				con = getConnection();
				String query = "UPDATE Cv SET deletedCv = '1' WHERE idCv = ? ";
				PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
				ps.setInt(1, cvId);
				ps.executeUpdate();
			    ps.close();

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(MemberSingleton.getInstance().getRole().equals("Manager"))
		{
			try
			{
				con = getConnection();
				String query = "DELETE FROM Cv WHERE idCv=?";
				PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
				ps.setInt(1, cvId);
				ps.executeUpdate();
			    ps.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}