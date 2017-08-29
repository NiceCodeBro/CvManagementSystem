package dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.mysql.jdbc.PreparedStatement;

import model.Cv;
import model.Information;
import model.Member;

import modelContent.*;
import util.DBUtil;

public class CVDao extends DBUtil{
	int cvId,loopSize;
	Connection con = null;
	PreparedStatement ps;
	
	Member member;
	


	public CvView.CvContent getCvContent(int cvId)
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
	
	
	public CvContent getCvforUpdate(int cvId,Member m){
		Connection con = null;
		CvContent c = new CvContent();
		String query="";
		PreparedStatement ps=null;
		ResultSet rs = null;
		int toplamKayit;
		try{
			System.out.println("DAO: Connection açıldı.");
			con = getConnection();
			
			//Personal Start
			Personal per = new  Personal();
			
			query = "SELECT * FROM Cv WHERE idCv = ?";
			ps = (PreparedStatement) con.prepareStatement(query);
				ps.setInt(1, cvId);
			rs = ps.executeQuery();
			rs.absolute(1);
			per.setCvName(rs.getString("cvName"));
			
			//Personal Info 
			query = "SELECT * FROM Content WHERE cvId = ? AND (titleId = 8 OR titleId = 29 OR titleId = 10 OR"
					+ " titleId = 4 OR titleId = 28 OR titleId = 11 OR titleId = 13 OR titleId = 5 OR titleId = 9)";
			ps = (PreparedStatement) con.prepareStatement(query);
				ps.setInt(1, cvId);
			rs = ps.executeQuery();
			
			
			
				rs.absolute(1);
				per.setPersonalName(rs.getString("content"));
				rs.absolute(2);
				per.setPersonalTitle(rs.getString("content"));
				rs.absolute(3);
				per.setPersonalObjectives(rs.getString("content"));
				rs.absolute(4);
				per.setPersonalDateofBirth(rs.getString("content"));
				rs.absolute(5);
				per.setPersonalCellPhone(rs.getString("content"));
				rs.absolute(6);
				per.setPersonalOfficePhone(rs.getString("content"));
				rs.absolute(7);
				per.setPersonalAddress(rs.getString("content"));
				rs.absolute(8);
				per.setPersonalMaritalStatus(rs.getString("content"));
				rs.absolute(9);
				per.setPersonalPhoto(rs.getString("content"));
			
			//Job Experiences End
				
			//Job Experiences Start
				
			List<JobExperienceUp> list = new ArrayList<JobExperienceUp>();
			
			query = "SELECT * FROM Content WHERE cvId = ? AND (titleId=14 OR titleId=15 OR titleId=16 OR titleId=30 OR titleId=17)";
			ps = (PreparedStatement) con.prepareStatement(query);
				ps.setInt(1, cvId);
				
			rs = ps.executeQuery();
			
			
			rs.last();
			toplamKayit= rs.getRow();
			rs.beforeFirst();
			
			if(toplamKayit>5){
				int w = toplamKayit;
				int i,temp=1,k=1,s=1;
				int cons=1;
				do{
					JobExperienceUp job = new JobExperienceUp();
					for(i = temp; i<=5*k;i++){
						rs.absolute(i);
						if(i==s){
							job.setJobCompanyName(rs.getString("content"));
						}else if(i == s + 1){
							job.setJobTitle(rs.getString("content"));
						}else if(i == s + 2){
							job.setJobStartDate(rs.getString("content"));
						}else if(i == s + 3){
							job.setJobEndDate(rs.getString("content"));
						}else if(i == s + 4){
							job.setJobDescription(rs.getString("content"));
						}
						temp++;
						
					}
					s = temp;
					
					k++;
				w = w - 5;
				list.add(job);
				}while(w>0);
					
			
				
			}else{
				int lessFive = 1;
				JobExperienceUp job = new JobExperienceUp();
				while(rs.next()){
					if(lessFive == 1){
						job.setJobCompanyName(rs.getString("content"));
					}else if(lessFive == 2){
						job.setJobTitle(rs.getString("content"));
					}else if(lessFive == 3){
						job.setJobStartDate(rs.getString("content"));
					}else if(lessFive == 4){
						job.setJobEndDate(rs.getString("content"));
					}else if(lessFive == 5){
						job.setJobDescription(rs.getString("content"));
					}
					lessFive++;
				}
				list.add(job);
			}
			
			//Job Experiences End
			
			//Education Start
			List<EducationUp> listEdu = new ArrayList<EducationUp>();
			
			query = "SELECT * FROM Content WHERE cvId = ? AND (titleId=6 OR titleId=7 OR titleId=36 OR titleId=37 OR titleId=38)";
			ps = (PreparedStatement) con.prepareStatement(query);
				ps.setInt(1, cvId);
				
			rs = ps.executeQuery();
			
			
			rs.last();
			toplamKayit= rs.getRow();
			rs.beforeFirst();
			
			if(toplamKayit>5){
				int w = toplamKayit;
				int i,temp=1,k=1,s=1;
				do{
					EducationUp edu = new EducationUp();
					for(i = temp; i<=5*k;i++){
						rs.absolute(i);
						if(i==s){
							edu.setEduSchoolName(rs.getString("content"));
						}else if(i == s + 1){
							edu.setEduSchoolDepartman(rs.getString("content"));
						}else if(i == s + 2){
							edu.setEduStartDate(rs.getString("content"));
						}else if(i == s + 3){
							edu.setEduEndDate(rs.getString("content"));
						}else if(i == s + 4){
							edu.setEduDescription(rs.getString("content"));
						}
						temp++;
						
					}
					s = temp;
					
					k++;
				w = w - 5;
				listEdu.add(edu);
				
				}while(w>0);
					
			
				
			}else{
				int lessFive = 1;
				EducationUp edu = new EducationUp();
				while(rs.next()){
					if(lessFive == 1){
						edu.setEduSchoolName(rs.getString("content"));
					}else if(lessFive == 2){
						edu.setEduSchoolDepartman(rs.getString("content"));
					}else if(lessFive == 3){
						edu.setEduStartDate(rs.getString("content"));
					}else if(lessFive == 4){
						edu.setEduEndDate(rs.getString("content"));
					}else if(lessFive == 5){
						edu.setEduDescription(rs.getString("content"));
					}
					lessFive++;
				}
				listEdu.add(edu);
			}
			
			//Education End
			
			//Project Start
			
			query = "SELECT * FROM Content WHERE cvId = ? AND titleId = 31";
			ps = (PreparedStatement) con.prepareStatement(query);
				ps.setInt(1, cvId);
			rs = ps.executeQuery();
			
			
			Project pro = new  Project();
				rs.absolute(1);
				pro.setProjectDescription(rs.getString("content"));

				
			
			//Project End
				
			//Foreign Language Start
				
				List<ForeignLanguageUp> listLang = new ArrayList<ForeignLanguageUp>();
				
				query = "SELECT * FROM Content WHERE cvId = ? AND (titleId=20 OR titleId=32)";
				ps = (PreparedStatement) con.prepareStatement(query);
					ps.setInt(1, cvId);
					
				rs = ps.executeQuery();
				
				
				rs.last();
				toplamKayit= rs.getRow();
				rs.beforeFirst();
				
				if(toplamKayit>2){
					int w = toplamKayit;
					int i,temp=1,k=1,s=1;
					do{
						ForeignLanguageUp lang = new ForeignLanguageUp();
						for(i = temp; i<=2*k;i++){
							rs.absolute(i);
							if(i==s){
								lang.setForeignName(rs.getString("content"));
							}else if(i == s + 1){
								lang.setForeignLevel(rs.getString("content"));
							}
							temp++;
							
						}
						s = temp;
						
						k++;
					w = w - 2;
					listLang.add(lang);
					
					}while(w>0);
						
				
					
				}else{
					int lessTwo = 1;
					ForeignLanguageUp lang = new ForeignLanguageUp();
					while(rs.next()){
						if(lessTwo == 1){
							lang.setForeignName(rs.getString("content"));
						}else if(lessTwo == 2){
							lang.setForeignLevel(rs.getString("content"));
						}
						lessTwo++;
					}
					listLang.add(lang);
				}
				
				
			//Foreign Language End
				
			//Skills Start
				
				query = "SELECT * FROM Content WHERE cvId = ? AND titleId = 39";
				ps = (PreparedStatement) con.prepareStatement(query);
					ps.setInt(1, cvId);
				rs = ps.executeQuery();
				
				
				Skill skill = new  Skill();
					rs.absolute(1);
					skill.setSkillDescription(rs.getString("content"));
				
			//Skills End
					
			//Courses Start
				query = "SELECT * FROM Content WHERE cvId = ? AND titleId = 33";
				ps = (PreparedStatement) con.prepareStatement(query);
					ps.setInt(1, cvId);
				rs = ps.executeQuery();
					
					
				Courses course = new  Courses();
					rs.absolute(1);
					course.setCoursesDescription(rs.getString("content"));	
					
					
			//Courses End
					
			//Certificate Start			
				query = "SELECT * FROM Content WHERE cvId = ? AND titleId = 34";
				ps = (PreparedStatement) con.prepareStatement(query);
					ps.setInt(1, cvId);
					rs = ps.executeQuery();
	
				Certificate cer = new Certificate();
					rs.absolute(1);
					cer.setCertificateDescription(rs.getString("content"));
					
			
			//Certificate End
					
			//Publication Start
			query = "SELECT * FROM Content WHERE cvId = ? AND titleId = 35";
			ps = (PreparedStatement) con.prepareStatement(query);
				ps.setInt(1, cvId);
				rs = ps.executeQuery();

				Publication pub = new Publication();
				rs.absolute(1);
				pub.setPublicationDescription(rs.getString("content"));
					
			//Publication End
					
			c.setPersonal(per);
			c.setJobExperiences(list);
			c.setEducations(listEdu);
			c.setProject(pro);
			c.setForeigns(listLang);
			c.setSkill(skill);
			c.setCourses(course);
			c.setCertificate(cer);
			c.setPublication(pub);
			
			
			ps.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		closeConnection(con);
		System.out.println("DAO: Connection kapatıldı.");
		return c;
		
	}
	
	public boolean addCv(CvContent c,Member m){
		Connection con = null;
		
		
		try{
			con = getConnection();
			System.out.println("DAO: Connection sağlandı.");
			
			int memberId = m.getIdMember();
			System.out.println("DAO: Üye ID'si alındı: "+ memberId);
			
			
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate = LocalDate.now();
			
			//Cv ekleme
			String query = "INSERT INTO Cv(memberId,cvName,deletedCv,cvAddDate) VALUES(?,?,?,?)";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
				ps.setInt(1, memberId);
				ps.setString(2, c.getPersonal().getCvName());
				ps.setInt(3, 0);
				ps.setString(4, dtf.format(localDate));
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
			
			if(c.getPersonal().getPersonalPhoto().contains("ation"))
				insertContentDB(cvId, 9,"");
			else
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
	
	public List<Cv> listCvbyMember(Member member){
		Connection con = null;
		List<Cv> listCv = new ArrayList<Cv>();
		try{
			con = getConnection();
			
			String query = "SELECT Member.memberName,Member.role, Cv.cvName ,Cv.idCv,Cv.memberId, Cv.deletedCv, Cv.cvAddDate FROM Cv JOIN Member WHERE memberId = ? AND deletedCv = 0 and memberId = Member.idMember";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ps.setInt(1, member.getIdMember());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Cv cv = new Cv();
				cv.setOwnerUsername(rs.getString("memberName"));
				cv.setOwnerRole(rs.getString("role"));
				cv.setCvName(rs.getString("cvName"));
				cv.setIdCv(rs.getInt("idCv"));
				cv.setMemberId(rs.getInt("memberId"));
				cv.setDeletedCv(rs.getInt("deletedCv"));
				cv.setAddDate(rs.getString("cvAddDate"));
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
			
			//String query = "SELECT * FROM Cv";
			String query = "SELECT Member.memberName,Member.role, Cv.cvName ,Cv.idCv,Cv.memberId,Cv.deletedCv, Cv.cvAddDate FROM CvSystem.Cv JOIN CvSystem.Member on Cv.memberId = Member.idMember";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Cv cv = new Cv();
				cv.setOwnerUsername(rs.getString("memberName"));
				cv.setOwnerRole(rs.getString("role"));
				cv.setCvName(rs.getString("cvName"));
				cv.setIdCv(rs.getInt("idCv"));
				cv.setMemberId(rs.getInt("memberId"));
				cv.setDeletedCv(rs.getInt("deletedCv"));
				cv.setAddDate(rs.getString("cvAddDate"));
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
	
	public void deleteCvByRole(int cvId, Member member)
	{
		Connection con = null;
		
		if(member.getRole().equals("Member"))
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
		else if(member.getRole().equals("Manager"))
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
	
	
	public boolean deleteCvforUpdate(int cvId)
	{
		Connection con = null;
		try{
				con = getConnection();
				String query = "DELETE FROM Cv WHERE idCv=?";
				PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
				ps.setInt(1, cvId);
				ps.executeUpdate();
			    ps.close();
			    return true;
			}catch(Exception e){
				e.printStackTrace();
			}
		return false;
		
	}
}