package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/secure/MenuServlet")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MenuServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		
		RequestDispatcher rd = request.getRequestDispatcher("MenuFrame");
		rd.include(request, response);

		HttpSession session = request.getSession();
		String sessionUsername = new String();
		
		if(session == null ) {
			response.sendRedirect("index.html");
		}
		
	String passedValue = request.getParameter("SelectedValue");
	if(passedValue!=null)
	{
		if (passedValue.equals("logOut")) {
			if (passedValue.equals("logOut")) {
				session = request.getSession();
				session.invalidate();
				response.sendRedirect("/Banking/index.html");
			}
		}

		else if (passedValue.equals("home")) {
			response.sendRedirect("MenuServlet");
		} else if (passedValue.equals("profile")) {
			response.sendRedirect("ProfileController");
		}

	}else
	{
	response.getWriter().println("<div class=\"container-fluid listOutput\">\r\n" + 
			"            <div class = \"back backPad\" onclick = \"location.href='MenuServlet'\"></div>				 <div class =\"dot dotPad\"></div>\r\n" + 
			"            <div class=\"rect\" style = \"vertical-align:middle\">\r\n" + 
			"                <div class = \"text-center\" style=\"vertical-align:middle; font-size: 30px;margin-top:13x 0px;\">Welcome"+sessionUsername+"</div>\r\n" + 
			"            </div>\r\n" + 
			"        </div>");	
	}
	PrintWriter out = response.getWriter();out.println("</body>");out.println("</html>");
}

}
