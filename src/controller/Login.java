package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import CvView.CreatePdf;
import model.Member;
import service.Facade;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Facade facade = Facade.getInstance(); 
	private static Logger logger = LogManager.getLogger(Login.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("doPost method | Someone is trying to login.");
		 String username = request.getParameter("LoginUsername");
         String pass = request.getParameter("LoginPassword");

         Member member = facade.getMemberInf(username, pass);
 		
         if(member.isStatus())
         {
        	 HttpSession sesion = request.getSession(true);
        	 sesion.setAttribute("isLoggedIn", "true");
        	 sesion.setAttribute("loggedUserName", member.getMemberName());
        	 sesion.setAttribute("loggedPassword", member.getMemberPass());
        	 sesion.setAttribute("loggedMemberId", member.getIdMember());
        	 sesion.setAttribute("loggedMemberRole", member.getRole());
        	 
        	// sesion.setMaxInactiveInterval(20*60);
        	 
        	 logger.info("doPost method | Login procces is successfull. Member (" + member.getMemberName() + ") redirec to home page." );
     		request.getRequestDispatcher("login.jsp").forward(request, response);
         }
         else
         {
        	 logger.info("doPost method | Login procces is unsuccessfull." );
        	 request.setAttribute("invalidLogin", "true");
        	 request.getRequestDispatcher("login.jsp").forward(request, response);
         }
        	 
  			

		} 
         

         
	

}
