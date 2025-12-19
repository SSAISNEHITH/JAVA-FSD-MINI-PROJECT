package Employleave;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewLeave")
public class ViewLeaveServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Integer empId = (Integer) request.getSession().getAttribute("empId");

        if (empId == null) {
            out.println("Session expired. Please login again.");
            return;
        }

        out.println("<h2>Your Leave Requests</h2>");
        out.println("<table border='1'>");
        out.println("<tr>");
        out.println("<th>Leave ID</th>");
        out.println("<th>Leave Type</th>");
        out.println("<th>From Date</th>");
        out.println("<th>To Date</th>");
        out.println("<th>Reason</th>");
        out.println("<th>Status</th>");
        out.println("</tr>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/leave_management",
                "root",
                "root"
            );

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM leave_request WHERE emp_id=?"
            );
            ps.setInt(1, empId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("leave_id") + "</td>");
                out.println("<td>" + rs.getString("leave_type") + "</td>");
                out.println("<td>" + rs.getDate("from_date") + "</td>");
                out.println("<td>" + rs.getDate("to_date") + "</td>");
                out.println("<td>" + rs.getString("reason") + "</td>");
                out.println("<td>" + rs.getString("status") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<br><a href='applyLeave.html'>Apply New Leave</a>");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error while fetching leave details.");
        }
    }
}
