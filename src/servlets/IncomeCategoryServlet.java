package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojos.Income_category;

@WebServlet("/secure/IncomeCategoryServlet")
public class IncomeCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IncomeCategoryServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("MenuFrame");
		rd.include(request, response);

		PrintWriter out = response.getWriter();
		
		out.println("<script>\r\n" + 
				"function del(incCatId) {\r\n" + 
				"document.getElementById(\"incCatId\").value = incCatId;\r\n" + 
				"document.getElementById(\"operation\").value = 'remove';\r\n" + 
				"document.listForm.submit();\r\n" + 
				"}\r\n" + 
				"function mod(incCatId,incCatName,incCatDetails) {\r\n" + 
				"document.getElementById(\"incCatId\").value = incCatId;\r\n" + 
				"document.getElementById(\"incCatName\").value = incCatName;\r\n" + 
				"document.getElementById(\"incCatDetails\").value = incCatDetails;\r\n" + 
				"document.getElementById(\"add\").value = 'Save!';\r\n" + 
				"document.getElementById(\"operation\").value = 'edit';\r\n" + 
				"}\r\n" + 
				"</script>\r\n" + 
				"<!-- Incomes Category -->\r\n" + 
				"        <form name = \"listForm\" action =\"IncomeCategoryController\" method = \"post\">\r\n" + 
				"        \r\n" + 
				"            <div class=\"container-fluid listOutput\">\r\n" + 
				"                <div class = \"back backPad\" onclick = \"location.href='MenuServlet'\"></div>"
				+ "				 <div class =\"dot dotPad\"></div>\r\n" + 
				"                <div class=\"rect\">\r\n" + 
				"                    <div class=\"listHead text-center\">\r\n" + 
				"                        Income Category\r\n" + 
				"                    </div>\r\n" + 
				"                    <div class=\"form-group\">\r\n" + 
				"                        <label for=\"incCatName\">Category Name:</label>\r\n" + 
				"                        <input type=\"text\" class=\"form-control\" id=\"incCatName\" name = \"incCatName\" required>\r\n" + 
				"                    </div>\r\n" + 
				"\r\n" + 
				"                    <div class=\"form-group\">\r\n" + 
				"                        <label for=\"incCatDetails\">Category Details:</label>\r\n" + 
				"                        <textarea class=\"form-control\" rows=\"3\" id=\"incCatDetails\" name = \"incCatDetails\"></textarea>\r\n" + 
				"                    </div>\r\n" + 
				"                    <div class = \"text-center\" style = \"margin-bottom: 10px;\">\r\n" + 
				"                        <input id =\"add\" type = \"submit\" class = \"btn btn-md btn-primary\" name = \"add\" value=\"add\">\r\n" + 
				"                        <input id = \"cancel\" style = \"width: 80px;\" onclick = \"location.reload(); listForm.reset();\" class = \" btn btn-md btn-primary\" name = \"cancel\" value= \"Cancel\">\r\n" + 
				"                    </div>\r\n" + 
				"                    <input name = \"operation\" id = \"operation\"  value = \"create\" type = \"hidden\">\r\n" + 
				"                    <input name = \"incCatId\" id = \"incCatId\"   type = \"hidden\">\r\n" + 
				"\r\n" + 
				"                    <div class=\"table-responsive\">\r\n" + 
				"                        <table class=\"table table-condensed table-striped table-bordered\">\r\n" + 
				"                            <tr>\r\n" + 
				"                                <th>Category Names</th>\r\n" + 
				"                                <th>Category Details</th>\r\n" + 
				"                                <th>Edit</th>\r\n" + 
				"                                <th>Delete</th>\r\n" + 
				"                            </tr>\r\n");
		
		
		@SuppressWarnings("unchecked")
		ArrayList<Income_category> income_categoryList = (ArrayList<Income_category>)(request.getAttribute("incCatList"));
		if(income_categoryList != null)
		for (Income_category income_category: income_categoryList) {
			out.println("<tr>");
			out.println("<td>" + income_category.getInc_catname() + "</td>");
			out.println("<td>" + income_category.getInc_catdetails() + "</td>");
			out.println(
					"<td ><input  class=\"button\"  name=\"edit\"  value=\"Edit!\" type=\"button\" onclick=\"mod('"+income_category.getInc_catid()+"','"+income_category.getInc_catname()+"','"+income_category.getInc_catdetails()+"');\"></td>");
			out.println(
					"<td ><input class=\"button\" name=\"delete\"  value=\"Delete!\" type=\"button\" onclick=\"del('"+income_category.getInc_catid()+"');\" ></td>");
			out.println("</tr>");
		}
		
		out.println("			</table>"
				+ "			</div>"
				+ "		</div>"
				
				+ "	</div>"
				+ "</form>");
		
		if(request.getParameter("msg") != null) {
			out.println("<script>alert(\"This value already exist.\")"
					+ "</script>");
		}
		out.println("</body>\r\n" + 
				"</html>\r\n");

	}

}
