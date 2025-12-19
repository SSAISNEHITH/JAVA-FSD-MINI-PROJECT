package Employleave;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
			String u = req.getParameter("username");
			String p = req.getParameter("password");


			if (u.equals("admin") && p.equals("admin123")) {
			res.sendRedirect("viewLeaves");
			} else {
			res.getWriter().print("Invalid Admin Login");
			}
			}
}
