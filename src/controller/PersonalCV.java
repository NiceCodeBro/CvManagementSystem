package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.Member;
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
		Member m = new Member();
		m.setIdMember(1);
		
		request.setAttribute("listOfCv", facade.listCvbyMember(m));
		
		RequestDispatcher view = request.getRequestDispatcher("cvpage.jsp");
		view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
