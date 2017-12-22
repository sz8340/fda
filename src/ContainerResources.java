// File: ContainerResources.java

/* A servlet to display the contents of the MySQL FDA database */

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ContainerResources extends HttpServlet 
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
        String loginUrl = "jdbc:mysql://localhost:3306/fda";

        response.setContentType("text/html");    // Response mime type

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();

        out.println("<HTML><HEAD><TITLE>Cost Calculation</TITLE></HEAD>");
        out.println("<BODY><H1>Inventory</H1>");


       out.println("<form action='ContainerCosts' method='get'>");
       out.println("<select name='application'>");
       out.println("<option value='Toris'>Toris</option>");
       out.println("<option value='app-2'>app-2</option>");
       out.println("<option value='app-3'>app-3</option>");
       out.println("</select>");
       out.println(" Team <input type='text' size='20' name='team'/> <br>");
       out.println("<input type='submit' value='Calculate' /> </form>");

	String app=request.getParameter("application");
	String team=request.getParameter("team");
	//String user=request.getParameter("user");
        String filter = ""; 

        if ( !app.equals("") ) {
           filter = " and applications.application_name='" + app + "'";
        }

        if ( !team.equals("") ) {
          filter += " and teams.team_name='" + team + "'";
        }
       /* 
        if ( !user.equals("") ) {
          filter += " and user='" + user + "'";
        }
*/
        
		
        // Load the mm.MySQL driver
        try
           {
              Class.forName("org.gjt.mm.mysql.Driver");
              //Class.forName("com.mysql.jdbc.Driver");
              //Class.forName("oracle.jdbc.driver.OracleDriver");
              Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
              // Declare our statement
              Statement statement = dbcon.createStatement();
/*
              String query = "SELECT application, team, ";
              query +=       "       user, container_size, container_price ";
              query +=       "FROM   fda.costs ";
              query +=       filter;
*/

              String query = "select application_name from fda.applications";
              //out.println(query);

              // Perform the query
              ResultSet rs = statement.executeQuery(query);



              // Iterate through each row of rs
              while (rs.next())
              {
                  String m_application_name = rs.getString("application_name");

                  out.println("<select name='application'>");
                  out.println("<option value=" + m_application_name+">"+m_application_name+"</option>");
              }
              out.println("</select>");


              rs.close();
              statement.close();
              //dbcon.close();
              //Connection dbcon2 = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
              // Declare our statement
              Statement statement2 = dbcon.createStatement();

              String query2 = "select sum(containers.container_price) from fda.inventory, fda.teams , fda.containers, fda.applications  where inventory.team_id=teams.id and inventory.container_size=containers.id and inventory.application_id=applications.id " + filter + ";";
              ResultSet rs2 = statement2.executeQuery(query2);
              while (rs2.next())
              {
                  String m_total = rs2.getString("SUM(containers.container_price)");
                  out.println("<tr>" +
                              "<td colspan='4'>" + "TOTAL: " + "</td>" +
                              "<td align='right'>" + m_total + "</td>" +
                              "</tr>");
              }
              out.println("</TABLE>");
              out.println("<button onclick='history.back()'>Go Back</button>");
              rs2.close();
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
