package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.UsersDao;
//import pojos.Users;

@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LogInServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		
		String username = request.getParameter("logInUsername");
		String password = request.getParameter("logInPassword");
		
		if(username != null && password != null) {
			UsersDao usersDao = new UsersDao();
			username = username.trim();
			password = password.trim();
			if(usersDao.checkAvailablity(username, password) != 0) {
				
				//Users users = usersDao.authenticate(username.trim(), password.trim());
				//storing user data in session
				HttpSession session = request.getSession();
				session.setAttribute("userId",usersDao.findByUsername(username).getUid());
				session.setAttribute("username", username);
				session.setAttribute("email",usersDao.findByUsername(username).getEmail());
			
				
				response.sendRedirect("/Banking/secure/MenuServlet");

				System.out.println("User successfully logged in: " + username);
			}
			else {
				response.sendRedirect("ErrorServlet?errorMessage=failLogin");
			}
		}
		else {
			response.sendRedirect("/Banking/index.html");
		}
	}

}
