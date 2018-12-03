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

import pojos.Expenses;
import pojos.Incomes;

@WebServlet("/secure/DayBookServlet")
public class DayBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DayBookServlet() {
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

		out.println("<!-- Day book -->\r\n"
				+ "        <form name = \"listForm\" action =\"DayBookController\" method = \"post\">\r\n"
				+ "        \r\n" + "            <div class=\"container-fluid listOutput\">\r\n"
				+ "                <div class = \"back backPad\" onclick = \"location.href='MenuServlet'\"></div>"
				+ "				 <div class =\"dot dotPad\"></div>\r\n" + "                <div class=\"rect\">\r\n"
				+ "                    <div class=\"listHead text-center\">\r\n"
				+ "                        Day Book \r\n" + "                    </div>\r\n");
		out.println("<table class = \"table table-condensed table-striped table-bordered\">\r\n"
				+ "                        <tr>\r\n" + "                            <td>\r\n"
				+ "                                Day Book\r\n" + "                            </td>\r\n"
				+ "                            <td colspan =\"2\">\r\n"
				+ "                                Date from <input type = \"date\" name = \"fromDate\" required>\r\n"
				+ "                            </td>\r\n" + "                            <td>\r\n"
				+ "                                To <input type = \"date\" name = \"toDate\" required>\r\n"
				+ "                            </td>\r\n"
				+ "                            <td colspan =\"2\" class = \"text-center\">\r\n"
				+ "                                <input type = \"submit\" name = \"show\" class = \"btn btn-md btn-primary\" value = \"show\">\r\n"
				+ "                            </td>\r\n" + "                        </tr>\r\n"
				+ "<tr><td>S. No.</td><td>Account Name</td><td>Date</td><td>Amount</td><td>Pay/Receive</td><td>Remark</td></tr>");


		@SuppressWarnings("unchecked")
		ArrayList<Expenses> list = (ArrayList<Expenses>)request.getAttribute("dayBookList");
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-YYYY");
		
		int i = 1;
		if (list != null && !list.isEmpty()) {
			out.println("<tr><td colspan =\"6\">Expenses</td></tr>");
			for (Expenses expenses : list) {
				out.println("<tr>");

				out.println("<td>");
				out.println(i++);
				out.println("</td>");

				out.println("<td>");
				out.println(expenses.getExp_ac());
				out.println("</td>");

				out.println("<td>");
				String formattedDate = sd.format(expenses.getTran_date());
				out.println(formattedDate);
				out.println("</td>");

				out.println("<td>");
				out.println(expenses.getAmount());
				out.println("</td>");

				out.println("<td>");
				out.println(expenses.getPayby());
				out.println("</td>");

				out.println("<td>");
				out.println(expenses.getRemark());
				out.println("</td>");

				out.println("</tr>");

			}

			out.println("<tr><td colspan =\"6\">Income</td></tr>");
			i = 1;
			@SuppressWarnings("unchecked")
			ArrayList<Incomes> list2 =(ArrayList<Incomes>)request.getAttribute("dayBookList2");
			if (list2 != null && !list2.isEmpty()) {
				for (Incomes incomes : list2) {
					out.println("<tr>");

					out.println("<td>");
					out.println(i++);
					out.println("</td>");

					out.println("<td>");
					out.println(incomes.getInc_ac());
					out.println("</td>");

					out.println("<td>");
					String formattedDate = sd.format(incomes.getTran_date());
					out.println(formattedDate);
					out.println("</td>");

					out.println("<td>");
					out.println(incomes.getAmount());
					out.println("</td>");

					out.println("<td>");
					out.println(incomes.getReceiveby());
					out.println("</td>");

					out.println("<td>");
					out.println(incomes.getRemark());
					out.println("</td>");

					out.println("</tr>");

				}
			}

		}
		out.println("		</table>" + "		</div>" + "	</div>" + "</form>");
		
		out.println("</body>\r\n" + "</html>\r\n");
	}

}
