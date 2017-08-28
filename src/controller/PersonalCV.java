package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import CvView.CreatePdf;
import model.Member;
import model.MemberSingleton;
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


@WebServlet("/index.jsp")
public class PersonalCV extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Facade facade = Facade.getInstance();
	private MemberSingleton member = MemberSingleton.getInstance();
	
    public PersonalCV() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter( "action" );
		if(action == null)
		{	
			if(MemberSingleton.getInstance().getRole().equals("Member"))
			{
				request.setAttribute("listOfCv", facade.listCvbyMember());
			}
			else
			{	
				request.setAttribute("listOfCv", facade.listCvbyManager());
			}
				RequestDispatcher view = request.getRequestDispatcher("cvpage.jsp");
				view.forward(request, response);		
			}
		else
		{
			if(action.equals("deleteCv"))
			{				
				int idCv = Integer.parseInt(request.getParameter("willDeletedCvId"));
				facade.deleteCvByRole(idCv);
				response.sendRedirect("index.jsp");
			}
			else if (action.equals("viewCv"))
			{
				int idCv = Integer.parseInt(request.getParameter("willViewedCvId"));
				CreatePdf.createPDF(facade.getCvContetn(idCv));
				response.sendRedirect("index.jsp");
			}
	
		}

	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}


	

}