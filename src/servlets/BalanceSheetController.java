package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ExpensesDao;
import daos.IncomesDao;
import pojos.Expenses;
import pojos.Incomes;

@WebServlet("/secure/BalanceSheetController")
public class BalanceSheetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BalanceSheetController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("show") != null) {
			String fromDate = new String();
			String toDate = new String();
			
			fromDate = request.getParameter("fromDate");
			toDate = request.getParameter("toDate");
			
			Integer userId = (Integer) request.getSession().getAttribute("userId");
			ArrayList<Incomes> list = new ArrayList<Incomes>();
			ArrayList<Expenses> list2 = new ArrayList<Expenses>();
			
			if(fromDate != null && toDate != null && userId != null) {
				list = new IncomesDao().findAll(userId.intValue());
				list2 = new ExpensesDao().findAll(userId.intValue());
				
			}
			
			request.setAttribute("balanceSheetList", list);
			request.setAttribute("balanceSheetList2", list2);
		}
			
		RequestDispatcher rd = request.getRequestDispatcher("/secure/BalanceSheetServlet");
		rd.forward(request, response);
	}

}
