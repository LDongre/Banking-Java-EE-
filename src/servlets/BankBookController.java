package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.Bank_bookDao;
import pojos.Bank_book;

@WebServlet("/secure/BankBookController")
public class BankBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BankBookController() {
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
			ArrayList<Bank_book> list = new ArrayList<Bank_book>();
			
			if(fromDate != null && toDate != null && userId != null) {
				list = new Bank_bookDao().findAllDateWise(fromDate, toDate, userId);		
				
			}
			
			request.setAttribute("bankBookList", list);
		}
			
			
			
		RequestDispatcher rd = request.getRequestDispatcher("BankBookServlet");
		rd.forward(request, response);

	}

}
