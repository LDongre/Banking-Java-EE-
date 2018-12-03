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

import pojos.Bank_book;

@WebServlet("/secure/BankBookServlet")
public class BankBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BankBookServlet() {
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

		out.println("<!-- Bank book -->\r\n"
				+ "        <form name = \"listForm\" action =\"BankBookController\" method = \"post\">\r\n"
				+ "        \r\n" + "            <div class=\"container-fluid listOutput\">\r\n"
				+ "                <div class = \"back backPad\" onclick = \"location.href='MenuServlet'\"></div>"
				+ "				 <div class =\"dot dotPad\"></div>\r\n" + "                <div class=\"rect\">\r\n"
				+ "                    <div class=\"listHead text-center\">\r\n"
				+ "                        Bank Book \r\n" + "                    </div>\r\n");
		out.println("<table class = \"table table-condensed table-striped table-bordered\">\r\n"
				+ "                        <tr>\r\n" + "                            <td>\r\n"
				+ "                                Bank Book\r\n" + "                            </td>\r\n"
				+ "                            <td>\r\n"
				+ "                                Date from <input type = \"date\" name = \"fromDate\" required>\r\n"
				+ "                            </td>\r\n" + "                            <td>\r\n"
				+ "                                To <input type = \"date\" name = \"toDate\" required>\r\n"
				+ "                            </td>\r\n"
				+ "                            <td class = \"text-center\">\r\n"
				+ "                                <input type = \"submit\" name = \"show\" class = \"btn btn-md btn-primary\" value = \"show\">\r\n"
				+ "                            </td>\r\n" + "                        </tr>\r\n"
				+ "<tr><td>S. No.</td><td>Date</td><td>Amount</td><td>Pay/Receive</td></tr>");

		out.println("");

		@SuppressWarnings("unchecked")
		ArrayList<Bank_book> list = (ArrayList<Bank_book>) (request.getAttribute("bankBookList"));
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-YYYY");
		int i = 1;
		if (list != null) {
			for (Bank_book bank_book : list) {
				out.println("<tr>");

				out.println("<td>");
				out.println(i++);
				out.println("</td>");

				out.println("<td>");
				String formattedDate = sd.format(bank_book.getTran_date());
				out.println(formattedDate);
				out.println("</td>");

				out.println("<td>");
				out.println(bank_book.getAmount());
				out.println("</td>");

				out.println("<td>");
				out.println(bank_book.getOperation());
				out.println("</td>");

				out.println("</tr>");

			}
		}

		out.println("		</table>" + "		</div>" + "	</div>" + "</form>");

		out.println("</body>\r\n" + "</html>\r\n");
	}

}
