package Employleave;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class EmployeeLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
			String email = req.getParameter("email");
			String password = req.getParameter("password");


			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/leave_management","root","root");
			PreparedStatement ps = con.prepareStatement(
			"SELECT emp_id FROM employee WHERE email=? AND password=?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
			req.getSession().setAttribute("empId", rs.getInt(1));
			res.sendRedirect("employeehome.html");
			} else {
			res.getWriter().print("Invalid Login");
			}
			} catch (Exception e) {
			e.printStackTrace();
			}
			}
}
