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

import pojos.Expenses_category;

@WebServlet("/secure/ExpensesCategoryServlet")
public class ExpensesCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExpensesCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("MenuFrame");
		rd.include(request, response);

		PrintWriter out = response.getWriter();
		
		
		out.println("<script>\r\n" + 
				"function del(expCatId) {\r\n" + 
				"document.getElementById(\"expCatId\").value = expCatId;\r\n" + 
				"document.getElementById(\"operation\").value = 'remove';\r\n" + 
				"document.listForm.submit();\r\n" + 
				"}\r\n" + 
				"function mod(expCatId,expCatName,expCatDetails) {\r\n" + 
				"document.getElementById(\"expCatId\").value = expCatId;\r\n" + 
				"document.getElementById(\"expCatName\").value = expCatName;\r\n" + 
				"document.getElementById(\"expCatDetails\").value = expCatDetails;\r\n" + 
				"document.getElementById(\"add\").value = 'Save!';\r\n" + 
				"document.getElementById(\"operation\").value = 'edit';\r\n" + 
				"}\r\n" + 
				"</script>\r\n" + 
				"<!-- Expenses Category -->\r\n" + 
				"        <form name = \"listForm\" action =\"ExpensesCategoryController\" method = \"post\">\r\n" + 
				"        \r\n" + 
				"            <div class=\"container-fluid listOutput\">\r\n" + 
				"                <div class = \"back backPad\" onclick = \"location.href='MenuServlet'\"></div>"
				+ "				 <div class =\"dot dotPad\"></div>\r\n" + 
				"                <div class=\"rect\">\r\n" + 
				"                    <div class=\"listHead text-center\">\r\n" + 
				"                        Expenses Category\r\n" + 
				"                    </div>\r\n" + 
				"                    <div class=\"form-group\">\r\n" + 
				"                        <label for=\"expCatName\">Category Name:</label>\r\n" + 
				"                        <input type=\"text\" class=\"form-control\" id=\"expCatName\" name = \"expCatName\" required>\r\n" + 
				"                    </div>\r\n" + 
				"\r\n" + 
				"                    <div class=\"form-group\">\r\n" + 
				"                        <label for=\"expCatDetails\">Category Details:</label>\r\n" + 
				"                        <textarea class=\"form-control\" rows=\"3\" id=\"expCatDetails\" name = \"expCatDetails\"></textarea>\r\n" + 
				"                    </div>\r\n" + 
				"                    <div class = \"text-center\" style = \"margin-bottom: 10px;\">\r\n" + 
				"                        <input id =\"add\" type = \"submit\" class = \"btn btn-md btn-primary\" name = \"add\" value=\"add\">\r\n" + 
				"                        <input id = \"cancel\" style=\"width:80px;\" onclick = \"location.reload(); listForm.reset();\" class = \" btn btn-md btn-primary\" name = \"cancel\" value= \"Cancel\">\r\n" + 
				"                    </div>\r\n" + 
				"                    <input name = \"operation\" id = \"operation\"  value = \"create\" type = \"hidden\">\r\n" + 
				"                    <input name = \"expCatId\" id = \"expCatId\"   type = \"hidden\">\r\n" + 
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
		ArrayList<Expenses_category> expenses_categoryList = (ArrayList<Expenses_category>)(request.getAttribute("expCatList"));
	
		
		if(expenses_categoryList != null)
		for (Expenses_category expenses_category: expenses_categoryList) {
			out.println("<tr>");
			out.println("<td>" + expenses_category.getExp_catname() + "</td>");
			out.println("<td>" + expenses_category.getExp_catdetails() + "</td>");
			out.println(
					"<td ><input  class=\"button\"  name=\"edit\"  value=\"Edit!\" type=\"button\" onclick=\"mod('"+expenses_category.getExp_catid()+"','"+expenses_category.getExp_catname()+"','"+expenses_category.getExp_catdetails()+"');\"></td>");
			out.println(
					"<td ><input class=\"button\" name=\"delete\"  value=\"Delete!\" type=\"button\" onclick=\"del('"+expenses_category.getExp_catid()+"');\" ></td>");
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
