package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.ExpensesDao;
import daos.Expenses_categoryDao;
import pojos.Expenses;
import pojos.Expenses_category;
import utilities.DateUtils;

@WebServlet("/secure/ExpensesController")
public class ExpensesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExpensesController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String exp_ac = new String();
		if(request.getParameter("exp") != null) {
			exp_ac = request.getParameter("exp");
		}
		
		int userid = 0;
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") != null) {
			userid = Integer.parseInt("" + (Integer)session.getAttribute("userId"));
		}
		
		
		ArrayList<Expenses_category> expenses_categoryList = new Expenses_categoryDao().findAll(userid);
		int exp_catid = 0;
		if(expenses_categoryList != null) {
			for(Expenses_category expcat: expenses_categoryList) {
				exp_catid = expcat.getExp_catid();
			}
		}
		else {
			System.out.println("num list");
		}
		
		String path = "ExpensesServlet";
		double amount = 0.0;;
		if(request.getParameter("amount") != null) {
			try {
				amount = Double.parseDouble(request.getParameter("amount"));
			}
			catch(Exception e) {
				path+="?msg=nfe";
			}
		}
		
		Date tran_date = new Date();
		if(request.getParameter("date") != null) {
			tran_date = DateUtils.convertDate(request.getParameter("date"));
		}
		
		String payby = new String();
		if(request.getParameter("mode") != null) {
			payby = request.getParameter("mode");
		}
		
		String remark = new String();
		if(request.getParameter("remark") != null) {
			remark = request.getParameter("remark");
		}
		String operation = request.getParameter("operation");
		if (operation == null) {
			operation = new String();
		}
				
		ExpensesDao expDao = new ExpensesDao();
		
		if (operation.equals("create")) {
			Expenses expenses = new Expenses(1, exp_ac, userid, exp_catid, amount, tran_date, payby, remark);
			expDao.create(expenses);
		}
		
			ArrayList<Expenses> expList = expDao.findAll();
		request.setAttribute("expList", expList);
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
