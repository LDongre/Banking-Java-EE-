package servlets;
 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.UsersDao;
import pojos.Users;
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignUpServlet() {
        super();
    
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		
		String username = request.getParameter("username");
		
		UsersDao usersDao = new UsersDao();
		
		if(username != null) {
			System.out.println("This username exist with userid = " + usersDao.checkAvailablity(username));
			if(usersDao.checkAvailablity(username) == 0) {
				//create user
				String password = request.getParameter("pass");
				String name = request.getParameter("name");
				String address = request.getParameter("address");
				String mobileNo = request.getParameter("mobileNo");
				String email = request.getParameter("email");
				
				
				
				if(password != null && name != null && address != null && mobileNo != null && email != null) {
					
					usersDao.create(new Users(1, username.trim(), password.trim(), name.trim(), address.trim(), mobileNo.trim(), email.trim()));
					//storing data into session
					
					HttpSession session = request.getSession();
					session.setAttribute("userId",usersDao.findByUsername(username).getUid());
					session.setAttribute("username", username);
					session.setAttribute("email",email);
					
					response.sendRedirect("/Banking/secure/MenuServlet");
				}
				else {
					response.sendRedirect("/Banking/index.html");
				}
			}
			else {
				response.sendRedirect("ErrorServlet?errorMessage=exists");
			}
		}
		else {
			response.sendRedirect("/Banking/index.html");
		}
	}

}
