package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.UsersDao;

@WebFilter("/secure/*")
public class AuthenticateFilter implements Filter {

	private ServletContext context;

	public AuthenticateFilter() {

	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

				
		
	//	String uri = req.getRequestURI();
	//	this.context.log("Requested Resource:" + uri);

	//	System.out.println("Authentication filter uri: " + uri); 
			
	//	if (uri.endsWith("LogInServlet") || uri.endsWith("SignUpServlet") || uri.endsWith("html") || uri.endsWith("js")
	//			|| uri.endsWith("css") || uri.endsWith("png") || uri.endsWith("jpg") || uri.endsWith("MenuServlet")) {
	//
	//			chain.doFilter(request, response);
	//	}
	//	else {
			HttpSession session = req.getSession();
			Integer userId = 0;
			String sessionUsername = new String();
			String sessionEmail = new String();
			if (session != null) {
				UsersDao usersDao = new UsersDao();
				
				Integer sessionId = (Integer) session.getAttribute("userId");
				sessionUsername = (String) session.getAttribute("username");
				sessionEmail = (String) session.getAttribute("email");
				
				
				if (sessionId != null && sessionUsername != null && sessionEmail != null) {
					userId = new Integer(usersDao.checkAvailablityWithEmail(sessionUsername, sessionEmail));
					if (userId.toString().equals(sessionId.toString())) {
						chain.doFilter(request, response);
					} else {
						res.sendRedirect("/Banking/index.html");
					}
				} else
						res.sendRedirect("/Banking/index.html");
			}
			else {
				res.sendRedirect("/Banking/index.html");
			}
		//}

	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}

}
