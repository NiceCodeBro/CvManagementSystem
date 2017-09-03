package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import CvView.CreatePdf;
import model.Member;

import service.Facade;


@WebServlet("/index.jsp")
public class PersonalCV extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Facade facade = Facade.getInstance();
	private static Logger logger = LogManager.getLogger(PersonalCV.class);
	
    public PersonalCV() {
        super();
    }
	private Member getLoggedMemberInf(HttpSession session)
	{
		logger.info("getLoggedMemberInf method | started.");
		Member member = new Member();
		if(session.getAttribute("isLoggedIn") == "true"){
			
			member.setMemberName((String)session.getAttribute("loggedUserName"));  
			member.setMemberPass((String)session.getAttribute("loggedPassword"));
			member.setRole((String)session.getAttribute("loggedMemberRole"));
			member.setIdMember( (Integer)session.getAttribute("loggedMemberId"));
		}
		logger.info("getLoggedMemberInf method | ended.");

		return member;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("doGet method | started.");
		String action = request.getParameter( "action" );
		Member member = getLoggedMemberInf(request.getSession(true));

		if(action == null)
		{	
			if(member.getRole().equals("Member"))
			{
				request.setAttribute("listOfCv", facade.listCvbyMember(member));
			}
			else
			{	
				request.setAttribute("listOfCv", facade.listCvbyManager());
				request.setAttribute("role", "Manager");
			}
				RequestDispatcher view = request.getRequestDispatcher("cvpage.jsp");
				view.forward(request, response);
		}
		else
		{
			if(action.equals("deleteCv"))
			{				
				int idCv = Integer.parseInt(request.getParameter("willDeletedCvId"));
				facade.deleteCvByRole(idCv,member);
				response.sendRedirect("index.jsp");
			}
			else if (action.equals("viewCv"))
			{
				int idCv = Integer.parseInt(request.getParameter("willViewedCvId"));
				//CV create
				CvView.CvContent cvcontent = facade.getCvContetn(idCv);
				CreatePdf.createPDF(cvcontent,request,member);
				//Download Cv
				File f = new File (request.getRealPath("")+"/" + cvcontent.getPersonal().getPersonalName().replaceAll(" ", "") + "_resume.pdf");
				response.setContentType ("application/pdf");
				response.setHeader ("Content-Disposition", "attachment;filename="+ cvcontent.getPersonal().getPersonalName().replaceAll(" ", "") +"_resume.pdf");
			
				InputStream in = new FileInputStream(f);
				ServletOutputStream outs = response.getOutputStream();
				
				int bit = 256;
				int i = 0;
				try {
					while ((bit) >= 0) {
						bit = in.read();
						outs.write(bit);
					}
				} catch (IOException ioe) {
					ioe.printStackTrace(System.out);
				}finally{
					f.delete();
				}
				outs.flush();
				outs.close();
				in.close();
				
			}			
		}
		logger.info("doGet method | ended and forwarded to related page by action's value.");

	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}


	

}