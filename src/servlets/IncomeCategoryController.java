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

import daos.Income_categoryDao;
import pojos.Income_category;

@WebServlet("/secure/IncomeCategoryController")
public class IncomeCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IncomeCategoryController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int incCatId = 0;
		if (request.getParameter("incCatId") != null && request.getParameter("incCatId").trim().length() > 0)
			incCatId = Integer.parseInt(request.getParameter("incCatId"));
		String incCatName = request.getParameter("incCatName");
		if (incCatName == null) {
			incCatName = new String();
		}
		String incCatDetails = request.getParameter("incCatDetails");
		if (incCatDetails == null) {
			incCatDetails = new String();
		}
		String operation = request.getParameter("operation");
		if (operation == null) {
			operation = new String();
		}

		HttpSession session = request.getSession();

		int userId = Integer.parseInt("" + session.getAttribute("userId"));

		Income_categoryDao incCatDao = new Income_categoryDao();

		String path = "IncomeCategoryServlet";

		if (operation.equals("create")) {
			Income_category income_category = new Income_category(incCatId, incCatName, incCatDetails, userId);
			if (incCatDao.checkDuplicate(income_category) == 1) {
				incCatDao.create(income_category);
			} else {
				path = path + "?msg=fail";
			}
		} else if (operation.equals("edit")) {
			Income_category income_category = new Income_category(incCatId, incCatName, incCatDetails, userId);
			incCatDao.edit(income_category);
		} else if (operation.equals("remove")) {
			incCatDao.remove(incCatId);
		}

		ArrayList<Income_category> incCatList = incCatDao.findAll();
		request.setAttribute("incCatList", incCatList);

		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
