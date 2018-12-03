package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.UsersDao;
import pojos.Users;

@WebServlet("/secure/ProfileController")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProfileController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("update") != null) {
			//take all parameters and edit it in users table
			//create user
			String username = request.getParameter("username");
			String password = request.getParameter("pass");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String mobileNo = request.getParameter("mobileNo");
			String email = request.getParameter("email");
			
			if(password != null && name != null && address != null && mobileNo != null && email != null) {
				UsersDao usersDao = new UsersDao();
				usersDao.edit(new Users(1, username.trim(), password.trim(), name.trim(), address.trim(), mobileNo.trim(), email.trim()));
				//storing data into session
					
				response.sendRedirect("ProfileServlet");
			}
			else {
				response.sendRedirect("ProfileServlet");
			}
			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("ProfileServlet");
		rd.include(request, response);
	}

}
