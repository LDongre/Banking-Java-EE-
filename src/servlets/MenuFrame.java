package servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/secure/MenuFrame")
public class MenuFrame extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MenuFrame() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		
		response.getWriter().println("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"    <head>\r\n" + 
				"        <meta charset=\"utf-8\" />\r\n" + 
				"        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
				"        <title>Banking</title>\r\n"
				+ "<link rel=\"icon\" type = \"image/png\" href=\"/Banking/black-and-blue-globe-512-243993.png\">" + 
				"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
				"        \r\n" + 
				"         <!-- Latest compiled and minified CSS -->\r\n" + 
				"		<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n" + 
				"\r\n" + 
				"		<!-- jQuery library -->\r\n" + 
				"		<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n" + 
				"\r\n" + 
				"		<!-- Latest compiled JavaScript -->\r\n" + 
				"		<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script> \r\n" + 
				"		\r\n" + 
				"		<!--css file made by me -->\r\n" + 
				"        <link rel = \"stylesheet\" href =\"/Banking/style.css\">\r\n" + 
				"        \r\n" + 
				"        <!-- js file made by me -->\r\n" + 
				"        <script src = \"/Banking/javascript.js\" type = \"text/javascript\"></script>\r\n" + 
				"    </head>\r\n" + 
				"    <body>\r\n" + 
				"\r\n" + 
				"        <!-- Navigation Bar-->\r\n" + 
				"        \r\n" + 
				"        <nav class = \"navbar navbar-static-top bg-primary navbar-dark\">\r\n" + 
				"                <div class = \"container\">\r\n" + 
				"                    <div class = \"navbar-header\">\r\n" + 
				"\r\n" + 
				"                        <a href=\"#\" class = \"navbar-brand nav-item doWhite sizeHeading\">\r\n" + 
				"                            <span class=\"glyphicon glyphicon-credit-card \" ></span> Banking | Menu </a>\r\n" + 
				"\r\n" + 
				"                        <button type = \"button\" class = \"navbar-toggle\" data-toggle = \"collapse\" data-target = \"#myNavbar\">\r\n" + 
				"                            <span class=\"icon-bar\"></span>\r\n" + 
				"                            <span class=\"icon-bar\"></span>\r\n" + 
				"                            <span class=\"icon-bar\"></span>                        \r\n" + 
				"                        </button>\r\n" + 
				"                    </div>\r\n" + 
				"                    <div class=\"collapse navbar-collapse\" id=\"myNavbar\">\r\n" + 
				"                            <ul class=\"nav navbar-nav navbar-right \">\r\n" + 
				"                                <li>\r\n" + 
				"                                    <a href = \"MenuServlet?SelectedValue=home\" class=\"glyphicon glyphicon-home doWhite\" > Home</a>\r\n" + 
				"                                </li>\r\n" + 
				"                                <li>\r\n" + 
				"                                    <a href=\"MenuServlet?SelectedValue=profile\" class=\"glyphicon glyphicon-user doWhite\"> Profile</a>\r\n" + 
				"                                </li>\r\n" + 
				"                                <li>\r\n" + 
				"                                    <a href=\"MenuServlet?SelectedValue=logOut\" class=\"glyphicon glyphicon-log-out doWhite\"> Log out</a>\r\n" + 
				"                                </li>\r\n" + 
				"                            </ul>\r\n" + 
				"                    </div>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"            </div>\r\n" + 
				"        </nav>  \r\n" + 
				"        <!-- END Navigation Bar-->\r\n" + 
				"        \r\n" + 
				"        <!-- Master -->\r\n" + 
				"\r\n" + 
				"        <div class = \"masterContainer container-fluid\">\r\n" + 
				"            <div class=\"row\" style = \"margin-top: 20px;\"></div>\r\n" + 
				"           <div class = \"dot\"></div>\r\n" + 
				"            <div class = \"speaker\"></div>\r\n" + 
				"            \r\n" + 
				"            <div class=\"list-group \">\r\n" + 
				"                    <a class=\"list-group-item list-group-item-info text-center\">Master</a>\r\n" + 
				"                    <a href=\"/Banking/secure/ExpensesCategoryController\" class=\"list-group-item\">Expenses Category</a>\r\n" + 
				"                    <a href=\"/Banking/secure/IncomeCategoryController\" class=\"list-group-item\">Income Category</a>\r\n" + 
				"                    <a href=\"/Banking/secure/ExpensesController\" class=\"list-group-item\">Expenses</a>\r\n" + 
				"                    <a href=\"/Banking/secure/IncomeController\" class=\"list-group-item\">Incomes</a>\r\n" + 
				"                    <a href=\"/Banking/secure/CashBookController\" class=\"list-group-item\">Cash Book</a>\r\n" + 
				"                    <a href=\"/Banking/secure/BankBookController\" class=\"list-group-item\">Bank Book</a>\r\n" + 	
				"                    <a href=\"/Banking/secure/DayBookController\" class=\"list-group-item\">Day Book</a>\r\n" + 
				"                    <a href=\"/Banking/secure/BalanceSheetController\" class=\"list-group-item\">Balance Sheet</a>\r\n" + 
				"                  </div> \r\n" + 
				"\r\n" + 
				"        <div class=\"back\" onclick = \"location.href='MenuServlet'\"></div>\r\n" + 
				"        </div>\r\n" + 
				"        <!-- END Master -->\r\n" + 
				"        \r\n");
		
	}

}
