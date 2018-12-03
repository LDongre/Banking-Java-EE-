package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.Expenses_categoryDao;
import pojos.Expenses_category;

@WebServlet("/secure/ExpensesCategoryController")
public class ExpensesCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExpensesCategoryController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int expCatId = 0;
		if (request.getParameter("expCatId") != null && request.getParameter("expCatId").trim().length()>0)
			expCatId = Integer.parseInt(request.getParameter("expCatId"));
		String expCatName = request.getParameter("expCatName");
		if (expCatName == null) {
			expCatName = new String();
		}
		String expCatDetails = request.getParameter("expCatDetails");
		if (expCatDetails == null) {
			expCatDetails = new String();
		}
		String operation = request.getParameter("operation");
		if (operation == null) {
			operation = new String();
		}
		
		HttpSession session = request.getSession();
		
		int userId = Integer.parseInt("" + session.getAttribute("userId")); 
		
		Expenses_categoryDao expCatDao = new Expenses_categoryDao();
		
		String path = "ExpensesCategoryServlet";
		
		if (operation.equals("create")) {
			Expenses_category expenses_category = new Expenses_category(expCatId, expCatName, expCatDetails, userId);
			if(expCatDao.checkDuplicate(expenses_category) == 1) {
			expCatDao.create(expenses_category);
			}
			else {
				path = path + "?msg=fail";
			}
		} else if (operation.equals("edit")) {
			Expenses_category expenses_category = new Expenses_category(expCatId, expCatName, expCatDetails, userId);
			expCatDao.edit(expenses_category);
		} else if (operation.equals("remove")) {
			expCatDao.remove(expCatId);
		} 
		
			ArrayList<Expenses_category> expCatList = expCatDao.findAll();
		request.setAttribute("expCatList", expCatList);
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
