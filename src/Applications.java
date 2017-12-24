// File: Applications.java

/* A servlet to display the contents of the MySQL FDA database */

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Applications extends HttpServlet 
{
    public String getServletInfo()
    {
       return "Servlet connects to MySQL database and displays result of a SELECT";
    }

    // Use http GET

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        String loginUser = "Dude1";
        String loginPasswd = "SuperSecret7@";
        String loginUrl = "jdbc:mysql://mysql1:3306/fda";

        response.setContentType("text/html");    // Response mime type

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();

        out.println("<HTML><HEAD><TITLE>Inventory</TITLE></HEAD>");
        out.println("<BODY align='center'><H1>Inventory</H1>");

	String app=request.getParameter("application");
	String id=request.getParameter("id");

		
        // Load the mm.MySQL driver
        try
           {
              Class.forName("org.gjt.mm.mysql.Driver");
              //Class.forName("com.mysql.jdbc.Driver");
              //Class.forName("oracle.jdbc.driver.OracleDriver");
              Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
              // Declare our statement
              Statement statement = dbcon.createStatement();

	      String query = "insert into fda.applications (application_name, id) values (" + app + "," + id + ")";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = dbcon.prepareStatement(query);
      //preparedStmt.setString (1, "Barney");
      //preparedStmt.setString (2, "Rubble");
      //preparedStmt.setDate   (3, startDate);
      //preparedStmt.setBoolean(4, false);
      //preparedStmt.setInt    (5, 5000);

      // execute the preparedstatement
      preparedStmt.execute();

              out.println("Recorded added!");
              statement.close();
              dbcon.close();

            }
        catch (SQLException ex) {
              out.println(ex);
              while (ex != null) {
                    System.out.println ("SQL Exception:  " + ex.getMessage ());
                    ex = ex.getNextException ();
                }  // end while
            }  // end catch SQLException

        catch(java.lang.Exception ex)
            {
                out.println("<HTML>" +
                            "<HEAD><TITLE>" +
                            "Bedrock: Error" +
                            "</TITLE></HEAD>\n<BODY>" +
                            "<P>SQL error in doGet: " +
                            ex.getMessage() + "</P></BODY></HTML>");
                return;
            }
         out.close();
    }
}
