package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CvView.CreatePdf;
import model.Member;
import model.MemberSingleton;
import service.Facade;


@WebServlet("/index.jsp")
public class PersonalCV extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Facade facade = Facade.getInstance(); 
	
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}