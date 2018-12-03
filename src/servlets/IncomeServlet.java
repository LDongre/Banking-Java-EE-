package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/secure/IncomeServlet")
public class IncomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IncomeServlet() {
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
				"<!-- Incomes -->\r\n" + 
				"        <form name = \"listForm\" action =\"IncomeController\" method = \"post\">\r\n" + 
				"        \r\n" + 
				"            <div class=\"container-fluid listOutput\">\r\n" + 
				"                <div class = \"back backPad\" onclick = \"location.href='MenuServlet'\"></div>"
				+ "				 <div class =\"dot dotPad\"></div>\r\n" + 
				"                <div class=\"rect\">\r\n" + 
				"                    <div class=\"listHead text-center\">\r\n" + 
				"                        Incomes \r\n" + 
				"                    </div>\r\n");
		
				out.println("<div class=\"form-group\">\r\n" + 
						"                        <label for=\"inc\">income:</label>\r\n" + 
						"                        <input type=\"text\" class=\"form-control\" id=\"inc\" name = \"inc\" required>\r\n" + 
						"                    </div>\r\n" + 
						"\r\n" + 
						"                    <select class = \"form-control\" name = \"category\">\r\n" + 
						"                        <option class= \"dropdown-item\" value = \"Indirect Expenses\">Indirect Expenses</option>\r\n" + 
						"                        <option  class= \"dropdown-item\" value = \"Direct Expenses\">Direct Expenses</option>\r\n" + 
						"                    </select>\r\n" + 
						"\r\n" + 
						"                    <div class=\"form-group\">\r\n" + 
						"                            <label for=\"amount\">Amount:</label>\r\n" + 
						"                            <input type=\"text\" class=\"form-control\" id=\"amount\" name = \"amount\" required>\r\n" + 
						"                    </div>\r\n" + 
						"\r\n" + 
						"                    <select class = \"form-control\" name = \"mode\">\r\n" + 
						"                            <option class= \"dropdown-item\" value = \"cash\">Cash</option>\r\n" + 
						"                            <option  class= \"dropdown-item\" value = \"card\">Card</option>\r\n" + 
						"                            <option  class= \"dropdown-item\" value = \"cheque\">Cheque</option>\r\n" + 
						"                    </select>\r\n" + 
						"                    \r\n" + 
						"                    <div class=\"form-group\">\r\n" + 
						"                            <label for=\"remark\">Remark:</label>\r\n" + 
						"                            <input type=\"text\" class=\"form-control\" id=\"remark\" name = \"remark\" required>\r\n" + 
						"                    </div>\r\n" + 
						"\r\n" + 
						"                    <div class=\"form-group\">\r\n" + 
						"                            <label for=\"date\">Date:</label>\r\n" + 
						"                            <input type=\"date\" class=\"form-control\" id=\"date\" name = \"date\" required>\r\n" + 
						"                    </div>   \r\n" + 
						"\r\n" + 
						"                    <div class = \"text-center\" style = \"margin-bottom: 10px;\">\r\n" + 
						"                        <input id =\"add\" type = \"submit\" class = \"btn btn-md btn-primary\" name = \"add\" value=\"add\">\r\n" + 
						"                        <input id = \"cancel\" style =\"width:80px;\" onclick = \"location.reload(); listForm.reset();\" class = \" btn btn-md btn-primary\" name = \"cancel\" value= \"Cancel\">\r\n" + 
						"                    </div>\r\n" + 
						"                    \r\n" + 
						"                    <input name = \"operation\" id = \"operation\"  value = \"create\" type = \"hidden\">\r\n" + 
						"                    <input name = \"incId\" id = \"incId\"   type = \"hidden\">\r\n" + 
						"");
		
				out.println("		</div>"
				+ "		</div>"
				+ "</form>");
		
		if(request.getParameter("msg") != null) {
			String msg = request.getParameter("msg");
			if(msg.equals("")) {
			out.println("<script>alert(\"This value already exist.\")"
					+ "</script>");
			}
			else  {
				out.println("<script>alert(\"This value already exist.\")"
						+ "</script>");
			
			}
		}
		out.println("</body>\r\n" + 
				"</html>\r\n");


	}

}
