package Employleave;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/update")
public class UpdateLeaveStatusServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
			String status = req.getParameter("status");
			int id = Integer.parseInt(req.getParameter("id"));


			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/leave_management","root","root");
			PreparedStatement ps = con.prepareStatement(
			"UPDATE leave_request SET status=? WHERE leave_id=?");
			ps.setString(1, status);
			ps.setInt(2, id);
			ps.executeUpdate();
			res.sendRedirect("viewLeaves");
			} catch (Exception e) {
			e.printStackTrace();
			}
			}

}
