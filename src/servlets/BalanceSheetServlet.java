package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojos.Expenses;
import pojos.Incomes;

@WebServlet("/secure/BalanceSheetServlet")
public class BalanceSheetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BalanceSheetServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("MenuFrame");
		rd.include(request, response);

		PrintWriter out = response.getWriter();

		out.println("<!-- Balance sheet -->\r\n" + 
				"        <form name = \"listForm\" action =\"BalanceSheetController\" method = \"post\">\r\n" + 
				"            <div class=\"container-fluid listOutput\">\r\n" + 
				"                <div class = \"back backPad\" onclick = \"location.href='MenuServlet'\"></div>\r\n" + 
				"                <div class =\"dot dotPad\"></div>\r\n" + 
				"                <div class=\"rect\">\r\n" + 
				"                    <div class=\"listHead text-center\">\r\n" + 
				"                        Balance Sheet \r\n" + 
				"                    </div>\r\n" + 
				"\r\n" + 
				"                    <div = 'table-responsive'><table class = \"table table-condensed table-striped table-bordered\">\r\n" + 
				"                        <tr>\r\n" + 
				"                            <td colspan =\"3\">\r\n" + 
				"                                Date from <input type = \"date\" name = \"fromDate\" required>\r\n" + 
				"                            </td>\r\n" + 
				"                            <td colspan =\"3\">\r\n" + 
				"                                To <input type = \"date\" name = \"toDate\" required>\r\n" + 
				"                            </td>\r\n" + 
				"                            <td class = \"text-center\" colspan =\"2\">\r\n" + 
				"                                <input type = \"submit\" name = \"show\" class = \"btn btn-md btn-primary\" value = \"show\">\r\n" + 
				"                            </td>\r\n" + 
				"                        </tr>\r\n" + 
				"                        <tr>\r\n" + 
				"                            <td colspan =\"4\" class = \"text-center\">\r\n" + 
				"                                Incomes\r\n" + 
				"                            </td>\r\n" + 
				"                            <td colspan =\"4\" class =\"text-center\">\r\n" + 
				"                                Expenses\r\n" + 
				"                            </td>\r\n" + 
				"                        </tr>\r\n" + 
				"                        <tr>\r\n" + 
				"                            <td colspan =\"2\">\r\n" + 
				"                                Incomes\r\n" + 
				"                            </td>\r\n" + 
				"                            <td colspan=\"2\">\r\n" + 
				"                                Amount\r\n" + 
				"                            </td>\r\n" + 
				"                            <td colspan =\"2\">\r\n" + 
				"                                Expenses\r\n" + 
				"                            </td>\r\n" + 
				"                            <td colspan=\"2\">\r\n" + 
				"                                Amount\r\n" + 
				"                            </td>\r\n" + 
				"                        </tr>");	

		@SuppressWarnings("unchecked")
		ArrayList<Incomes> list = (ArrayList<Incomes>)request.getAttribute("balanceSheetList");
		@SuppressWarnings("unchecked")
		ArrayList<Expenses> list2 = (ArrayList<Expenses>)request.getAttribute("balanceSheetList2");
		
		Iterator<Incomes> itIncomes = list.iterator();
		Iterator<Expenses> itExpenses = list2.iterator();
		
		double   amtIncomes = 0.0;
		double amtExpenses = 0.0;
		
		while(itIncomes.hasNext() || itExpenses.hasNext()) {
			
			out.println("<tr>");
			if(itIncomes.hasNext()) {
				Incomes incomes = itIncomes.next();
				out.println("<td colspan =\"2\">" + incomes.getInc_ac() + "</td>");
				out.println("<td colspan = \"2\">" + incomes.getAmount() + "</td>");
				amtIncomes+=incomes.getAmount();
			}
			else {
				out.println("<td colspan = \"4\"></td>");
			}
			
			if(itExpenses.hasNext()) {
				Expenses expenses = itExpenses.next();
				out.println("<td colspan = \"2\">" + expenses.getExp_ac() + "</td>");
				out.println("<td colspan =\"2\">" + expenses.getAmount() + "</td>");
				amtExpenses+=expenses.getAmount();
			} 
			else{
				out.println("<td colspan =\"4\"></td>");
			}
			out.println("</tr>");
			
		}
		
		out.println("<tr><td colspan =\"2\">Total</td><td colspan=\"2\">"+ amtIncomes +"</td><td colspan =\"2\">Total</td><td colspan =\"2\">"+ amtExpenses+"</td></tr>");
		out.println("<tr><td colspan=\"4\">Gross Profit</td><td colspan =\"4\">"+ (amtIncomes - amtExpenses)+"</td></tr>");
		
		out.println("</table></div>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"</form>\r\n" + 
				"</body>\r\n" + 
				"</html>");
	
	}

}
