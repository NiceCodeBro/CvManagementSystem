package controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.Member;
import modelContent.CvContent;
import modelContent.JobExperience;
import modelContent.Personal;
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
	private final String UPLOAD_DIRECTORY = "/";
  
	private Facade facade = Facade.getInstance();
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Member m = new Member();
		m.setIdMember(1);
    	
    	CvContent c = setCv(request);
    	facade.addCv(c, m);
 
    	
    	//uploadImage(request);
    
    	//request.getRequestDispatcher("/index.jsp").forward(request, response);
     
    }
    public CvContent setCv(HttpServletRequest request) throws UnsupportedEncodingException{
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
    	String personalPhoto = "";
    //Job Kısmı için veri çekimi
    	
    	//Kaç tane iş yeri girilmiş sayısını verir
    	//System.out.println(jobCompanyName.length);
    	
    	String[] jobCompanyName = request.getParameterValues("jobCompanyName");
    	String[] jobTitle = request.getParameterValues("jobTitle");
    	String[] jobStartDate = request.getParameterValues("jobStartDate");
    	String[] jobEndDate = request.getParameterValues("jobEndDate");
    	String[] jobDescription = request.getParameterValues("jobDescription");
 
    	
    	CvContent content = new CvContent();
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
    		
    	JobExperience job = new JobExperience();
    		job.setJobCompanyName(jobCompanyName);
    		job.setJobTitle(jobTitle);
    		job.setJobStartDate(jobStartDate);
    		job.setJobEndDate(jobEndDate);
    		job.setJobDescription(jobDescription);
    	
    		//Geri kalan bölümlerde aynı şekilde öncelikle modelContent altında class oluştulup öyle eklenecek.
    	
    	
    	content.setPersonal(per);
    	content.setJobExperience(job);
    	
    	return content;
    	
    	
    }
    
    public void uploadImage(HttpServletRequest request){
    	if(ServletFileUpload.isMultipartContent(request)){
            try {
                @SuppressWarnings("unchecked")
				List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);
              
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                    }
                }
                System.out.println(multiparts.iterator().next().getName());
               //File uploaded successfully
               request.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }          
         
        }else{
            request.setAttribute("message",
                                 "Sorry this Servlet only handles file upload request");
        }
    }
  
}