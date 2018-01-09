// File: InventoryAdd.java

/* A servlet to display the contents of the MySQL FDA database */

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class InventoryAdd extends HttpServlet 
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

        List<String> m_application_id = new ArrayList<String>();
        List<String> m_application_name = new ArrayList<String>();

        response.setContentType("text/html");    // Response mime type

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();

        out.println("<HTML><HEAD><TITLE>Inventory List</TITLE></HEAD>");
        out.println("<BODY><H1>Inventory List</H1>");
        int i = 0;


        out.println("<form action='InventoryAddList' method='get'>");

        // Load the mm.MySQL driver
        try
           {
              Class.forName("org.gjt.mm.mysql.Driver");
              //Class.forName("com.mysql.jdbc.Driver");
              //Class.forName("oracle.jdbc.driver.OracleDriver");
              Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
              // Declare our statement
              Statement statement = dbcon.createStatement();

              String query = "select application_name from fda.applications";
              //out.println(query);

              out.println("<input type='text' name='container_id'>");

              // Perform the query
              ResultSet rs = statement.executeQuery(query);

              // Iterate through each row of rs
              while (rs.next()) {
                  m_application_name.add(rs.getString("application_name"));
              }
              while (rs.next()) {
                  m_application_id.add(rs.getString("application_id"));
              }

              out.println("<select name='application'>");
              out.println("<option value=''></option>");
              for (i = 0; i< m_application_name.size(); i ++ ) {
                  out.println("<option value=" + m_application_name.id(i)+">"+m_application_name.get(i)+"</option>");
              }
              out.println("</select>");

              rs.close();
              statement.close();

              Statement statement2 = dbcon.createStatement();
              String query2 = "select team_name from fda.teams";
              ResultSet rs2 = statement2.executeQuery(query2);
              out.println("<select name='team'>");
              out.println("<option value=''></option>");
              while (rs2.next())
              {
                  String m_team_name = rs2.getString("team_name");
                  String m_team_id = rs2.getString("team_id");
                  out.println("<option value=" + m_team_id+">"+m_team_name+"</option>");
              }
              out.println("</select>");

              rs2.close();
              statement2.close();

              Statement statement3 = dbcon.createStatement();
              String query3 = "select container_size from fda.containers";
              ResultSet rs3 = statement3.executeQuery(query3);
              out.println("<select name='container_id'>");
              out.println("<option value=''></option>");
              while (rs3.next())
              {
                  String m_container_size = rs3.getString("container_size");
                  String m_container_id = rs3.getString("container_id");
                  out.println("<option value=" + m_container_id+">"+m_container_size+"</option>");
              }
              out.println("</select>");
              out.println("<input type='submit' value='Submit' />");
              out.println("</form>");

              rs3.close();
              statement2.close();

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
