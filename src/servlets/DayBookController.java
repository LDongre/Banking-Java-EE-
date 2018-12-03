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

@WebServlet("/secure/DayBookController")
public class DayBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DayBookController() {
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
			toDate= request.getParameter("toDate");
			
			Integer userId = (Integer) request.getSession().getAttribute("userId");
			if(fromDate != null && toDate != null && userId != null) {
				ArrayList<Expenses> list = new ExpensesDao().findAllDateWise(fromDate, toDate, userId);
				request.setAttribute("dayBookList", list);
				ArrayList<Incomes> list2 = new IncomesDao().findAllDateWise(fromDate, toDate, userId);
				request.setAttribute("dayBookList2", list2);
			}
			
		}
			
			
		RequestDispatcher rd = request.getRequestDispatcher("DayBookServlet");
		rd.forward(request, response);
		
		
	}

}
