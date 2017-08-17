package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberSingleton;
import service.Facade;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Facade facade = Facade.getInstance(); 

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String username = request.getParameter("LoginUsername");
         String pass = request.getParameter("LoginPassword");
         
         if(facade.loginCheck(username, pass))
         {
        	 System.out.println("giriş başarılı");
        	 System.out.println(MemberSingleton.getInstance().getId() + " " + MemberSingleton.getInstance().getUsername() + " " +  MemberSingleton.getInstance().getPassword() + " " + MemberSingleton.getInstance().getRole() ) ;
     		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
    		view.forward(request, response);
         }
         else
        	 System.out.println("giriş başarısız");
         
         
         

         
	}

}
