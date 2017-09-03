package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Member;
import service.Facade;

/**
 * Servlet implementation class MembersList
 */
@WebServlet(name="MembersList",urlPatterns={"/MembersList"})
public class MembersList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Facade facade = Facade.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MembersList() {

        super();

        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter( "action" );


		List<Member> member;
		member = facade.getAllMembers();
	
		request.setAttribute("listOfMember", member);
		
		RequestDispatcher view = request.getRequestDispatcher("listmembers.jsp");
		view.forward(request, response);		
		


	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter( "action" );
		if(action == null)
		{
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		}
		else if(action.equals("addNewMember")) //update user
		{
			
			Member m = new Member();
		
			m.setMemberName((String)request.getParameter("memberName"));  
			m.setMemberPass((String)request.getParameter("memberPassword"));
			m.setRole((String)request.getParameter("memberRole"));
			
		    
				facade.addNewMember(m);
			
			
			List<Member> member = facade.getAllMembers();
			request.setAttribute("listOfMember", member);

			RequestDispatcher view = request.getRequestDispatcher("listmembers.jsp");
			view.forward(request, response);		
		}
		else if(action.equals("updateMember")) //update user
		{
			
			Member m = new Member();
		
			m.setMemberName((String)request.getParameter("memberName"));  
			m.setMemberPass((String)request.getParameter("memberPassword"));
			m.setRole((String)request.getParameter("memberRole"));
			m.setIdMember( Integer.parseInt(request.getParameter("memberId")));

			facade.updateMember(m);
			
			List<Member> member = facade.getAllMembers();
			request.setAttribute("listOfMember", member);
			System.err.println("333");

			RequestDispatcher view = request.getRequestDispatcher("listmembers.jsp");
			view.forward(request, response);		
		}
		else if(action.equals("deleteUser"))
		{
			int id = Integer.parseInt(request.getParameter("willDeletedUserId"));
			facade.deleteMember(id);
			
			List<Member> member = facade.getAllMembers();
			request.setAttribute("listOfMember", member);
			
			RequestDispatcher view = request.getRequestDispatcher("listmembers.jsp");
			view.forward(request, response);		
		}
		
	}




}
