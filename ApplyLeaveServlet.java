package Employleave;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/applyLeave")
public class ApplyLeaveServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
			int empId = (int) req.getSession().getAttribute("empId");
			String type = req.getParameter("type");
			String from = req.getParameter("from");
			String to = req.getParameter("to");
			String reason = req.getParameter("reason");


			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/leave_management","root","root");
			PreparedStatement ps = con.prepareStatement(
			"INSERT INTO leave_request(emp_id,leave_type,from_date,to_date,reason) VALUES(?,?,?,?,?)");
			ps.setInt(1, empId);
			ps.setString(2, type);
			ps.setString(3, from);
			ps.setString(4, to);
			ps.setString(5, reason);
			ps.executeUpdate();
			res.getWriter().print("Leave Applied Successfully");
			} catch (Exception e) {
			e.printStackTrace();
			}
			}

}
