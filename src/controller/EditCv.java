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
@WebServlet("/Edit")
@javax.servlet.annotation.MultipartConfig
public class EditCv extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  
	private Facade facade = Facade.getInstance();
	//private MemberSingleton member = MemberSingleton.getInstance();
	
	
	
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}



	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
    	if(request.getParameter("action")!=null){
			String cvId = request.getParameter("updateCvId");
			Member m = new Member();
			m.setIdMember(1);
			
			CvContent c = new CvContent();
			
			c = facade.getCvforUpdate(Integer.valueOf(cvId),m);
			request.setAttribute("cvContent", c);
			request.setAttribute("cvNum", cvId);
			RequestDispatcher view = request.getRequestDispatcher("editcv.jsp");
			view.forward(request, response);
     
    	}

	}
  
}
