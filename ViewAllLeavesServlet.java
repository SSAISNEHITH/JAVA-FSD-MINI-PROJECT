package Employleave;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewLeaves")
public class ViewAllLeavesServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Admin - Leave Requests</title></head><body>");
        out.println("<h2>All Leave Requests</h2>");

        out.println("<table border='1' cellpadding='10'>");
        out.println("<tr>");
        out.println("<th>Leave ID</th>");
        out.println("<th>Employee ID</th>");
        out.println("<th>Leave Type</th>");
        out.println("<th>From Date</th>");
        out.println("<th>To Date</th>");
        out.println("<th>Reason</th>");
        out.println("<th>Status</th>");
        out.println("<th>Action</th>");
        out.println("</tr>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/leave_management",
                "root",
                "root"
            );

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM leave_request");

            while (rs.next()) {
                int leaveId = rs.getInt("leave_id");
                String status = rs.getString("status");

                out.println("<tr>");
                out.println("<td>" + leaveId + "</td>");
                out.println("<td>" + rs.getInt("emp_id") + "</td>");
                out.println("<td>" + rs.getString("leave_type") + "</td>");
                out.println("<td>" + rs.getDate("from_date") + "</td>");
                out.println("<td>" + rs.getDate("to_date") + "</td>");
                out.println("<td>" + rs.getString("reason") + "</td>");
                out.println("<td>" + status + "</td>");

                out.println("<td>");
                if (status.equalsIgnoreCase("Pending")) {
                    out.println(
                        "<a href='update?status=Approved&id=" + leaveId + "'>Approve</a> | " +
                        "<a href='update?status=Rejected&id=" + leaveId + "'>Reject</a>"
                    );
                } else {
                    out.println("No Action");
                }
                out.println("</td>");

                out.println("</tr>");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<tr><td colspan='8'>Error loading data</td></tr>");
        }

        out.println("</table>");
        out.println("</body></html>");
    }
}

