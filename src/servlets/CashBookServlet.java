package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojos.Cash_book;

@WebServlet("/secure/CashBookServlet")
public class CashBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CashBookServlet() {
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
				"<!-- Cash Book -->\r\n" + 
				"        <form name = \"listForm\" action =\"CashBookController\" method = \"post\">\r\n" + 
				"        \r\n" + 
				"            <div class=\"container-fluid listOutput\">\r\n" + 
				"                <div class = \"back backPad\" onclick = \"location.href='MenuServlet'\"></div>"
				+ "				 <div class =\"dot dotPad\"></div>\r\n" + 
				"                <div class=\"rect\">\r\n" + 
				"                    <div class=\"listHead text-center\">\r\n" + 
				"                        Cash Book \r\n" + 
				"                    </div>\r\n");
		out.println("<table class = \"table table-condensed table-striped table-bordered\">\r\n" + 
				"                        <tr>\r\n" + 
				"                            <td>\r\n" + 
				"                                Cash Book\r\n" + 
				"                            </td>\r\n" + 
				"                            <td>\r\n" + 
				"                                Date from <input type = \"date\" name = \"fromDate\" required>\r\n" + 
				"                            </td>\r\n" + 
				"                            <td>\r\n" + 
				"                                To <input type = \"date\" name = \"toDate\" required>\r\n" + 
				"                            </td>\r\n" + 
				"                            <td class = \"text-center\">\r\n" + 
				"                                <input type = \"submit\" name = \"show\" class = \"btn btn-md btn-primary\" value = \"show\">\r\n" + 
				"                            </td>\r\n" + 
				"                        </tr>\r\n"
				+ "<tr><td>S. No.</td><td>Date</td><td>Amount</td><td>Pay/Receive</td></tr>" );
		
		out.println("");
		
		@SuppressWarnings("unchecked")
		ArrayList<Cash_book> list = (ArrayList<Cash_book>)(request.getAttribute("cashBookList"));
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-YYYY");
		int i = 1;
		if(list != null) {
			for(Cash_book cash_book: list) {
				out.println("<tr>");
				
				out.println("<td>");
				out.println(i++);
				out.println("</td>");
				
				out.println("<td>");
				String formattedDate = sd.format(cash_book.getTran_date());
				out.println(formattedDate);
				out.println("</td>");
				
				out.println("<td>");
				out.println(cash_book.getAmount());
				out.println("</td>");
				
				out.println("<td>");
				out.println(cash_book.getOperation());
				out.println("</td>");
				
				out.println("</tr>");
				
			}
		}
		
		
		out.println("		</table>"
				+ "		</div>"
				+ "	</div>"
				+ "</form>");
		
		out.println("</body>\r\n" + 
				"</html>\r\n");
	}

}
