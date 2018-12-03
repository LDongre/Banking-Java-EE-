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

import daos.Income_categoryDao;
import daos.IncomesDao;
import pojos.Income_category;
import pojos.Incomes;
import utilities.DateUtils;

@WebServlet("/secure/IncomeController")
public class IncomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IncomeController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String inc_ac = new String();
		if(request.getParameter("inc") != null) {
			inc_ac = request.getParameter("inc");
		}
		
		int userid = 0;
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") != null) {
			userid = Integer.parseInt("" + (Integer)session.getAttribute("userId"));
		}
		
		
		ArrayList<Income_category> income_categoryList = new Income_categoryDao().findAll(userid);
		int inc_catid = 0;
		if(income_categoryList != null) {
			for(Income_category inccat: income_categoryList) {
				inc_catid = inccat.getInc_catid();
			}
		}
		else {
			System.out.println("num list");
		}
		
		String path = "IncomeServlet";
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
		
		IncomesDao incDao = new IncomesDao();
		
		if (operation.equals("create")) {
			Incomes income = new Incomes(1, inc_ac, userid, inc_catid, amount, tran_date, payby, remark);
			incDao.create(income);
		}
		
			ArrayList<Incomes> incList = incDao.findAll();
		request.setAttribute("incList", incList);
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}

}
