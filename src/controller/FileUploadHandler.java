package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.SimpleFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import model.Member;
import modelContent.Certificate;
import modelContent.Courses;
import modelContent.CvContent;
import modelContent.Education;
import modelContent.ForeignLanguage;
import modelContent.JobExperience;
import modelContent.Personal;
import modelContent.Project;
import modelContent.Publication;
import modelContent.Skill;
import service.Facade;

/**
 * Servlet implementation class FileUploadHandler
 */
@WebServlet("/upload")
@javax.servlet.annotation.MultipartConfig
public class FileUploadHandler extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  
	private Facade facade = Facade.getInstance();
	private static Logger logger = LogManager.getLogger(FileUploadHandler.class);

	private Member getLoggedMemberInf(HttpSession session)
	{
		Member member = new Member();
		member.setMemberName((String)session.getAttribute("loggedUserName"));  
		member.setMemberPass((String)session.getAttribute("loggedPassword"));
		member.setRole((String)session.getAttribute("loggedMemberRole"));
		member.setStatus(true);
		member.setIdMember( (Integer)session.getAttribute("loggedMemberId"));
	
		return member;
	}
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	logger.info("DoPost method started.");
  
    	request.setCharacterEncoding("UTF-8");	
    	Member m = getLoggedMemberInf(request.getSession(false));
    	CvContent c = setCv(request);
		facade.addCv(c,m);
		
    	logger.info("DoPost method - Cv adding proccess has successfull. Redirected to index.jsp page.");
    	response.sendRedirect("index.jsp");
     
    }
    
    
    public CvContent setCv(HttpServletRequest request) throws IOException, IllegalStateException, ServletException{
    	logger.info("setCv Method  started. All informations that filled at cv adding page filling to related java classes.");

    	request.setCharacterEncoding("UTF-8");
    	//Personal Kısmı için veri çekimi
    	String cvName = request.getParameter("cvName");
    	String personalName = request.getParameter("personalName");
    	String personalTitle =  request.getParameter("personalTitle");
    	String personalObjectives = request.getParameter("personalObjectives");
    	String personalDateofBirth = request.getParameter("personalDateofBirth");
    	String personalCellPhone = request.getParameter("personalCellPhone");
    	String personalOfficePhone = request.getParameter("personalOfficePhone");
    	String personalAddress = request.getParameter("personalAddress");
    	String personalMaritalStatus = request.getParameter("personalMaritalStatus");
    	String personalMail = request.getParameter("personalMail");
    	String personalPhoto = getValue(request);
    	
    //Job Kısmı için veri çekimi
    	
    	//Kaç tane iş yeri girilmiş sayısını verir
    	//System.out.println(jobCompanyName.length);
    	
    	String[] jobCompanyName = request.getParameterValues("jobCompanyName");
    	String[] jobTitle = request.getParameterValues("jobTitle");
    	String[] jobStartDate = request.getParameterValues("jobStartDate");
    	String[] jobEndDate = request.getParameterValues("jobEndDate");
    	String[] jobDescription = request.getParameterValues("jobDescription");
  
    //Education Kısmı için veri çekimi
    	
    	String[] eduSchoolName = request.getParameterValues("eduSchoolName");
    	String[] eduSchoolDepartman = request.getParameterValues("eduSchoolDepartman");
    	String[] eduStartDate = request.getParameterValues("eduStartDate");
    	String[] eduEndDate = request.getParameterValues("eduEndDate");
    	//String[] eduContinue = request.getParameterValues("eduContinue");
    	String[] eduDescription = request.getParameterValues("eduDescription");
    
    //Projects kısmı için veri çekimi
    	String projectDescription = request.getParameter("projectDescription");
    	
    //Foreign Lang. kısmı için veri çekimi	
    	String[] foreignName = request.getParameterValues("foreignName");
    	String[] foreignLevel = request.getParameterValues("foreignLevel");
    	
    	
    //Skills kısmı için veri çekimi
    	String skillDescription = request.getParameter("skillDescription");
    	
    
    //Courses kısmı için veri çekimi
    	String coursesDescription = request.getParameter("coursesDescription");
    	
    //Certificate kısmı için veri çekimi
    	String certificateDescription = request.getParameter("certificateDescription");
    	
    //Publication kısmı için veri çekimi
    	String publicationDescription = request.getParameter("publicationDescription");

    	
    	
    	CvContent content = new CvContent();
    	
    	Publication pub = new Publication();
			pub.setPublicationDescription(publicationDescription);
		
    	Certificate cer = new Certificate();
    		cer.setCertificateDescription(certificateDescription);
			
		Courses cour = new Courses();
    		cour.setCoursesDescription(coursesDescription);
    	
    	Skill skill = new Skill();
    		skill.setSkillDescription(skillDescription);
    	
    	ForeignLanguage foreign = new ForeignLanguage();
    		foreign.setForeignName(foreignName);
    		foreign.setForeignLevel(foreignLevel);
    	
    	Project pro = new Project();
    		pro.setProjectDescription(projectDescription);
    	
    	Education edu = new Education();
    		edu.setEduSchoolName(eduSchoolName);
    		edu.setEduSchoolDepartman(eduSchoolDepartman);
    		edu.setEduStartDate(eduStartDate);
    		edu.setEduEndDate(eduEndDate);
    		//edu.setEduContinue(eduContinue);
    		edu.setEduDescription(eduDescription);
    	
    	JobExperience job = new JobExperience();
			job.setJobCompanyName(jobCompanyName);
			job.setJobTitle(jobTitle);
			job.setJobStartDate(jobStartDate);
			job.setJobEndDate(jobEndDate);
			job.setJobDescription(jobDescription);
		
    	Personal per = new Personal();
    		per.setCvName(cvName);
    		per.setPersonalName(personalName);
    		per.setPersonalTitle(personalTitle);
    		per.setPersonalObjectives(personalObjectives);
    		per.setPersonalDateofBirth(personalDateofBirth);
    		per.setPersonalCellPhone(personalCellPhone);
    		per.setPersonalOfficePhone(personalOfficePhone);
    		per.setPersonalAddress(personalAddress);
    		per.setPersonalMaritalStatus(personalMaritalStatus);
    		per.setPersonalPhoto(personalPhoto);
    		per.setPersonalMail(personalMail);
    		
        	
    	
    	content.setPersonal(per);
    	content.setJobExperience(job);
    	content.setEducation(edu);
    	content.setProject(pro);
    	content.setForeign(foreign);
    	content.setSkill(skill);
    	content.setCourses(cour);
    	content.setCertificate(cer);
    	content.setPublication(pub);
    	
    	logger.info("Set cv method | All informations has filled to one java class named CvContent. And returned.");
    	
    	return content;
    	
    	
    }
    
    //getParamterer yerine
    public String getValue(HttpServletRequest request) throws IllegalStateException, IOException, ServletException{
    	logger.info("getValue method | Started.");
    	String name = null;
    	DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		Member member = getLoggedMemberInf(request.getSession(false));
		
		Part part = request.getPart("file");
		try
		{
			if(part.getContentType().contains("image") ){
				try{
			    	
			    		InputStream fileContent = part.getInputStream();
				    	
				    	name = String.valueOf(member.getIdMember())+dateFormat.format(date)+"."+part.getContentType().substring(6);
				    	
				    	File dir = new File(request.getRealPath("")+"/profilePhoto");
				    	if(!dir.exists()){
				    		dir.mkdir();
				    	}
				    	File file = new File(new File(request.getRealPath("")+"/profilePhoto"), name);
				    	
				    	Files.copy(fileContent, file.toPath());
			    	
				    	logger.info("getValue method | File path is returned and method ended.");
				}catch(Exception e){
					logger.error("getValue method | Error occured." + e.getMessage());
					e.printStackTrace();
				}
			}else{
				logger.info("getValue method | Ended.");
				return "";
			}
		}
		catch(Exception e)
		{
			return "";
		}

    	return "profilePhoto" + File.separator + name;
    }
    

  
}
