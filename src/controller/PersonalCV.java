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

import CvView.CreatePdf;
import model.Member;

import service.Facade;


@WebServlet("/index.jsp")
public class PersonalCV extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Facade facade = Facade.getInstance();
//	private MemberSingleton member = MemberSingleton.getInstance();
	
    public PersonalCV() {
        super();
        // TODO Auto-generated constructor stub
    }
	private Member getLoggedMemberInf(HttpSession session)
	{
		Member member = new Member();
		if(session.getAttribute("isLoggedIn") == "true"){
			
			member.setMemberName((String)session.getAttribute("loggedUserName"));  
			member.setMemberPass((String)session.getAttribute("loggedPassword"));
			member.setRole((String)session.getAttribute("loggedMemberRole"));
			member.setIdMember( (Integer)session.getAttribute("loggedMemberId"));
	   	 System.out.println(member.getMemberName() + " " + member.getMemberPass() + " " + member.getRole());
		}
		return member;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				CreatePdf.createPDF(facade.getCvContetn(idCv),request,member);
				//Download Cv
				File f = new File (request.getRealPath("")+"/"+member.getMemberName()+"_resume.pdf");
				response.setContentType ("application/pdf");
				response.setHeader ("Content-Disposition", "attachment;filename="+member.getMemberName()+"_resume.pdf");
			
					InputStream in = new FileInputStream(f);
					ServletOutputStream outs = response.getOutputStream();
					
					
					int bit = 256;
					int i = 0;
					try {
						while ((bit) >= 0) {
							bit = in.read();
							outs.write(bit);
						}
						//System.out.println("" +bit);
					} catch (IOException ioe) {
						ioe.printStackTrace(System.out);
					}finally{
						f.delete();
					}
						//System.out.println( "\n" + i + " bytes sent.");
						//System.out.println( "\n" + f.length() + " bytes sent.");
					outs.flush();
					outs.close();
					in.close();
					
			}
//			else if (action.equals("listMembers"))
//			{
//				System.out.println("ttttttttttttt " + facade.getAllMembers().size());
//				request.setAttribute("listOfMember", facade.getAllMembers());
//			
//				RequestDispatcher view = request.getRequestDispatcher("/listmembers.jsp");
//				view.forward(request, response);	
//			}
			
	
		}

	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}


	

}