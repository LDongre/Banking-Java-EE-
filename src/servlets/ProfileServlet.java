package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/secure/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProfileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("MenuFrame");
		rd.include(request, response);
		
PrintWriter out = response.getWriter();
		
		out.println( 
				"<!-- Profile -->\r\n" + 
				"        <form name = \"listForm\" action =\"ProfileController\" method = \"post\">\r\n" + 
				"        \r\n" + 
				"            <div class=\"container-fluid listOutput\">\r\n" + 
				"                <div class = \"back backPad\" onclick = \"location.href='MenuServlet'\"></div>"
				+ "				 <div class =\"dot dotPad\"></div>\r\n" + 
				"                <div class=\"rect\">\r\n" + 
				"                    <div class=\"listHead text-center\">\r\n" + 
				"                        Profile \r\n" + 
				"                    </div>\r\n");
		
				out.println("<div class=\"form-group\">\r\n" + 
						"                        <label for=\"username\">Username:</label>\r\n" + 
						"                        <input type=\"text\" class=\"form-control\" name = \"username\" id=\"username\" required autofocus>\r\n" + 
						"                  </div>\r\n" + 
						"                  <div class=\"form-group\">\r\n" + 
						"                        <label for=\"pass\">Password:</label>\r\n" + 
						"                        <input type=\"password\" class=\"form-control\" id=\"pass\" name = \"pass\" required>\r\n" + 
						"                  </div>\r\n" + 
						"                   <div class=\"form-group\">\r\n" + 
						"                        <label for=\"name\">Name:</label>\r\n" + 
						"                        <input type=\"text\" class=\"form-control\" name = \"name\" id=\"name\" required>\r\n" + 
						"                  </div>\r\n" + 
						"                  <div class=\"form-group\">\r\n" + 
						"                      <label for=\"address\">Address:</label>\r\n" + 
						"                      <input type=\"text\" class=\"form-control\" name = \"address\" id=\"address\" required>\r\n" + 
						"                  </div>\r\n" + 
						"                  <div class=\"form-group\">\r\n" + 
						"                      <label for=\"mobileNo\">Mobile number:</label>\r\n" + 
						"                      <input type=\"text\" class=\"form-control\" name = \"mobileNo\" id=\"mobileNo\" required>\r\n" + 
						"                  </div>\r\n" + 
						"                  <div class=\"form-group\">\r\n" + 
						"                        <label for=\"email\">Email</label>\r\n" + 
						"                        <input type=\"email\" class=\"form-control\" name = \"email\" id=\"email\" required>\r\n" + 
						"                  </div>\r\n" + 
						"              \r\n" + 
						"               <div class = \"text-center\" style = \"margin-bottom: 10px;\">\r\n" + 
						"                        <input id =\"update\" type = \"submit\" class = \"btn btn-md btn-primary\" name = \"update\" value=\"update\">\r\n" + 
						"                        <input id = \"cancel\" style=\"width:80px;\" onclick = \" listForm.reset();\" class = \" btn btn-md btn-primary\" name = \"cancel\" value= \"Cancel\">\r\n" + 
						"                    </div>");
		
				out.println("		</div>"
				+ "		</div>"
				
				+ "</form>");
		
		


		

		
	}

}
