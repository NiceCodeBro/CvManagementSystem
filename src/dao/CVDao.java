package dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.mysql.jdbc.PreparedStatement;

import model.Cv;
import model.Information;
import model.Member;
import model.MemberSingleton;

import modelContent.*;
import util.DBUtil;

public class CVDao extends DBUtil{
	int cvId,loopSize;
	Connection con = null;
	PreparedStatement ps;
	

	public CvView.CvContent getCvContetn(int cvId)
	{
		CvView.CvContent cvContent = new CvView.CvContent();
		Connection con = null;
		
		try
		{
//			int nOfJop=0, nOfEducation=0, nOfLanguage=0;
			CvView.Personal personal = new CvView.Personal();
			CvView.JobExperience jobExperience = new CvView.JobExperience();
			CvView.Education education = new CvView.Education();
			CvView.Project project = new CvView.Project();
			CvView.ForeignLanguage foreign = new CvView.ForeignLanguage();
			CvView.Skill skill = new CvView.Skill();
			CvView.Courses courses = new CvView.Courses();
			CvView.Certificate certificate = new CvView.Certificate();
			CvView.Publication publication = new CvView.Publication();
			
			con = getConnection();

			System.out.println("DAO: Connection sağlandı.");
			/*String query = "SELECT Title.title, Content.content from Content join Title on  cvId = ? and Title.idTitle = Content.titleId and Title.parentIdTitle = ?";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ps.setInt(1, 62);
			ps.setInt(2, 1);

			List<Pair> tempArr = new ArrayList<Pair>();

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				Pair tempEntry = new Pair(title,content);
				tempArr.add(tempEntry);
			}*/
			
			
			String query = "SELECT Title.parentIdTitle, Title.title, Content.content from Content join Title on  cvId = ? and Title.idTitle = Content.titleId";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ps.setInt(1, cvId);

			List<Information> allInf = new ArrayList<Information>();

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString("parentIdTitle");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Information tempInf = new Information(id,title,content);
				allInf.add(tempInf);
				
				
//				if(title.equals("Company Name")) ++nOfJop;
//				else if(title.equals("School Name")) ++nOfEducation;
//				else if(title.equals("Name Of Language")) ++nOfLanguage;
			}
			
	
			
			
			for(int i = 0 ; i < allInf.size(); ++i)
			{
				String content = allInf.get(i).getContent();
				if(allInf.get(i).getParentId().equals("1"))
				{
					if(allInf.get(i).getTitle().equals("Date Of Birth"))
						personal.setPersonalDateofBirth(content);
					else if(allInf.get(i).getTitle().equals("Martial Status"))
						personal.setPersonalMaritalStatus(content);
					else if(allInf.get(i).getTitle().equals("Name Surname"))
						personal.setPersonalName(content);
					else if(allInf.get(i).getTitle().equals("Foto Path"))
						personal.setPersonalPhoto(content);
					else if(allInf.get(i).getTitle().equals("Objective"))
						personal.setPersonalObjectives(content);
					else if(allInf.get(i).getTitle().equals("Office Phone"))
						personal.setPersonalOfficePhone(content);
					else if(allInf.get(i).getTitle().equals("Adress"))
						personal.setPersonalAddress(content);
					else if(allInf.get(i).getTitle().equals("Cell Phone"))
						personal.setPersonalCellPhone(content);
					else if(allInf.get(i).getTitle().equals("Personal Info Title"))
						personal.setPersonalTitle(content);
				}
				else if(allInf.get(i).getParentId().equals("2"))
				{
					if(allInf.get(i).getTitle().equals("Company Name"))
						jobExperience.setJobCompanyName(content);
					else if(allInf.get(i).getTitle().equals("Jop Title"))
						jobExperience.setJobTitle(content);
					else if(allInf.get(i).getTitle().equals("Jop Experience Start Date"))
						jobExperience.setJobStartDate(content);
					else if(allInf.get(i).getTitle().equals("Jop Explanation"))
						jobExperience.setJobDescription(content);
					else if(allInf.get(i).getTitle().equals("Jop Experience End Date"))
						jobExperience.setJobEndDate(content);
				}
				
				else if(allInf.get(i).getParentId().equals("3"))
				{
					if(allInf.get(i).getTitle().equals("School Name"))
						education.setEduSchoolName(content);
					else if(allInf.get(i).getTitle().equals("Departman Name"))
						education.setEduSchoolDepartman(content);
					else if(allInf.get(i).getTitle().equals("Education Start Date"))
						education.setEduStartDate(content);
					else if(allInf.get(i).getTitle().equals("Education End Date"))
						education.setEduEndDate(content);
					else if(allInf.get(i).getTitle().equals("Education Description"))
						education.setEduDescription(content);
				}
				else if(allInf.get(i).getParentId().equals("12"))
				{
					if(allInf.get(i).getTitle().equals("Projects Description"))
						project.setProjectDescription(content);
				}
				else if(allInf.get(i).getParentId().equals("19"))
				{
					if(allInf.get(i).getTitle().equals("Name Of Language"))
						foreign.setForeignName(content);
					else if(allInf.get(i).getTitle().equals("Language Level"))
						foreign.setForeignLevel(content);	
				}
				else if(allInf.get(i).getParentId().equals("24"))
				{
					if(allInf.get(i).getTitle().equals("Skills Description"))
						skill.setSkillDescription(content);
				}
				else if(allInf.get(i).getParentId().equals("25"))
				{
					if(allInf.get(i).getTitle().equals("Courses and Seminars Description"))
						courses.setCoursesDescription(content);
				}
				else if(allInf.get(i).getParentId().equals("26"))
				{
					if(allInf.get(i).getTitle().equals("Certificates Description"))
						certificate.setCertificateDescription(content);
				}
				else if(allInf.get(i).getParentId().equals("27"))
				{
					if(allInf.get(i).getTitle().equals("Publications Description"))
						publication.setPublicationDescription(content);
				}
			}
		
			cvContent.setCertificate(certificate);
			cvContent.setCourses(courses);
			cvContent.setEducation(education);
			cvContent.setForeign(foreign);
			cvContent.setJobExperience(jobExperience);
			cvContent.setPersonal(personal);
			cvContent.setProject(project);
			cvContent.setPublication(publication);
			cvContent.setSkill(skill);
			
			closeConnection(con);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return cvContent;
	}
	
	
	
	public boolean addCv(CvContent c,Member m){
		Connection con = null;
		
		
		try{
			con = getConnection();
			System.out.println("DAO: Connection sağlandı.");
			
			int memberId = m.getIdMember();
			System.out.println("DAO: Üye ID'si alındı: "+ memberId);
			
			//Cv ekleme
			String query = "INSERT INTO Cv(memberId,cvName,deletedCv) VALUES(?,?,?)";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
				ps.setInt(1, memberId);
				ps.setString(2, c.getPersonal().getCvName());
				ps.setInt(3, 0);
			int isInsert = ps.executeUpdate();
			
			//Eklenen Cvnin ID si
			cvId = (int) ps.getLastInsertID();
			
			System.out.println("DAO: CV tablosuna CV bilgisi eklendi.CV ID: "+ cvId );
			
			//Personal Info Ekleme
			insertContentDB(cvId, 8, c.getPersonal().getPersonalName());
			insertContentDB(cvId, 29, c.getPersonal().getPersonalTitle());
			insertContentDB(cvId, 10,c.getPersonal().getPersonalObjectives());
			insertContentDB(cvId, 4, c.getPersonal().getPersonalDateofBirth());
			insertContentDB(cvId, 28,c.getPersonal().getPersonalCellPhone());
			insertContentDB(cvId, 11, c.getPersonal().getPersonalOfficePhone());
			insertContentDB(cvId, 13, c.getPersonal().getPersonalAddress());
			insertContentDB(cvId, 5, c.getPersonal().getPersonalMaritalStatus());
			insertContentDB(cvId, 9,c.getPersonal().getPersonalPhoto());
			
			System.out.println("DAO: Personal Info bilgisi Content tablosuna eklendi ");
			
			//Job Experience ekleme String[] ifade olduğu için loop yapısı kullanıldı
			int numberofJob = c.getJobExperience().jobNumber();
			
			for (loopSize=0;loopSize<numberofJob;loopSize++){
				insertContentDB(cvId, 14, c.getJobExperience().getJobCompanyName()[loopSize]);
				insertContentDB(cvId, 15, c.getJobExperience().getJobTitle()[loopSize]);
				insertContentDB(cvId, 16, c.getJobExperience().getJobStartDate()[loopSize]);
				insertContentDB(cvId, 30, c.getJobExperience().getJobEndDate()[loopSize]);
				insertContentDB(cvId, 17, c.getJobExperience().getJobDescription()[loopSize]);
				
			}
			System.out.println("DAO: Job Experience bilgisi Content tablosuna eklendi");
			//Education Kısmı
			int numberofEdu = c.getEducation().eduNumber();
			
			for (loopSize=0;loopSize<numberofEdu;loopSize++){
				insertContentDB(cvId, 6, c.getEducation().getEduSchoolName()[loopSize]);
				insertContentDB(cvId, 7, c.getEducation().getEduSchoolDepartman()[loopSize]);
				insertContentDB(cvId, 36, c.getEducation().getEduStartDate()[loopSize]);
				insertContentDB(cvId, 37, c.getEducation().getEduEndDate()[loopSize]);
				insertContentDB(cvId, 38, c.getEducation().getEduDescription()[loopSize]);
				
			}
			
			System.out.println("DAO: Education bilgisi Content tablosuna eklendi");
			//Projects kısmı
			insertContentDB(cvId, 31,c.getProject().getProjectDescription());
			
			
			System.out.println("DAO: Projects bilgisi Content tablosuna eklendi");
			//Foreign Lang. kısmı
			int numberofForeign = c.getForeign().foreignNumber();
			
			for (loopSize=0;loopSize<numberofForeign;loopSize++){
				insertContentDB(cvId, 20, c.getForeign().getForeignName()[loopSize]);
				insertContentDB(cvId, 32, c.getForeign().getForeignLevel()[loopSize]);	
			}
			
			System.out.println("DAO: Foreign Language bilgisi Content tablosuna eklendi");
			
			//Skills kısmı
			insertContentDB(cvId, 39,c.getSkill().getSkillDescription());
			
			System.out.println("DAO: Skills bilgisi Content tablosuna eklendi");
			//Courses kısmı
			insertContentDB(cvId, 33,c.getCourses().getCoursesDescription());
			
			System.out.println("DAO: Courses bilgisi Content tablosuna eklendi");
			
			//Certificate kısmı
			insertContentDB(cvId, 34,c.getCertificate().getCertificateDescription());
			
			System.out.println("DAO: Certificate bilgisi Content tablosuna eklendi");
			
			//Publication kısmı
			insertContentDB(cvId, 35,c.getPublication().getPublicationDescription());
			
			System.out.println("DAO: Publication bilgisi Content tablosuna eklendi");
			
			ps.close();
			closeConnection(con);
			System.out.println("DAO: PreparedStatement kapatıldı");
			
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		closeConnection(con);
		System.out.println("DAO: Connection kapatıldı.");
		return false;
		
		
	}
	
	//insert işlemlerinde kullanılmak üzere hazırlandı
	public void insertContentDB(int cvId,int titleId,String c){
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