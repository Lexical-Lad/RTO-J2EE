/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Samuel
 */
public class seett extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession hs=request.getSession(true);
        
        if(!hs.getAttribute("logged").equals("*"))
        {
            RequestDispatcher r=request.getRequestDispatcher("home");
            r.forward(request, response);
        }
        
        
         PrintWriter p=response.getWriter();
         
         
         String name="";
         String licensenum="";
         String fine="";
         String issue="";
         String due="";
         
         
         try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/RTO","root","maverick");

                Statement st=con.createStatement();

                ResultSet rs=st.executeQuery("select * from ticket where status='0'");
                
                
                if(!rs.next())
        {
            
            
            p.println("<html>\n" +
"<head>\n" +
"<title>\n" +
"Uttar Pradesh Regional Transport Office\n" +
"</title>\n" +"<div id=\"headimg\"></div>\n"    +
"<div id=\"header\">\n" +
"</div>\n" +
"<link href=\"style1.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
"</head>\n" +
"<body>\n" +
"<div id=\"wrapper\">\n" +
"  <div id=\"menu\">\n" +
"    <ul>\n" +
"      	<li><a href=\"home\" accesskey=\"1\">Home</a></li>\n" +
"      	<li><a href=\"applications\" accesskey=\"2\">Applications</a></li>\n" +
"	<li><a href=\"seecomplaints\" accesskey=\"3\">Complaints</a></li>\n" +
"      	<li><a href=\"#\" accesskey=\"5\">Traffic Tickets</a>\n" +
"		<ul>\n" +
"			<li><a href=\"addtt\">Add New</a></li>\n" +
"			<li><a href=\"seett\">View</a></li>\n" +
"		</ul>\n" +
"	</li>\n" +
"	<li><a href=\"#\" accesskey=\"6\">Welcome, Admin</a>\n" +
"	<ul>\n" +
"		<li><a href=\"logout\">Logout</a></li>\n" +
"	</ul>\n" +
"	</li>\n" +
"    </ul>\n" +
"  </div>\n" +
"<div id=\"content\">" +
"<br><br><br><br><br><br><p><h3><center>There are no unpaid traffic tickets, currently.</center></h3></p><br><br>\n" +
"</div>\n" +
"</div>\n" +
"\n" +
"<div id=\"footer\">\n" +
"<p id=\"brand\"><a href=\"http://www.uptransporthelpline.org/\" target=\"_blank\"><img src=\"images/helpline.jpg\" align=\"left\" \n" +
"/></a></p>\n" +
"<p id=\"legal\">Office of the Transport Commissioner UP, Tehri Kothi, MG Marg, Lucknow - 226001 (UP)</p>\n" +
"<p id=\"legal\">Website developed in house at Transport Commissioner's Office</p>\n" +
"<p id=\"legal\"><strong><a href=\"devsquad\">Development Team</a></strong></p>\n" +
"</div>\n" +
"\n" +
"\n" +
"</body>\n" +
"</html>");
            
        }
        
        else
        {
            rs.beforeFirst();
            p.println("<html>\n" +
"<head>\n" +
"<title>\n" +
"Uttar Pradesh Regional Transport Office\n" +
"</title>\n" +"<div id=\"headimg\"></div>\n"    +
"<div id=\"header\">\n" +
"</div>\n" +
"<link href=\"style1.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
"</head>\n" +
"<body>\n" +
"<div id=\"wrapper\">\n" +
"  <div id=\"menu\">\n" +
"    <ul>\n" +
"      	<li><a href=\"home\" accesskey=\"1\">Home</a></li>\n" +
"      	<li><a href=\"applications\" accesskey=\"2\">Applications</a></li>\n" +
"	<li><a href=\"seecomplaints\" accesskey=\"3\">Complaints</a></li>\n" +
"      	<li><a href=\"#\" accesskey=\"5\">Traffic Tickets</a>\n" +
"		<ul>\n" +
"			<li><a href=\"addtt\">Add New</a></li>\n" +
"			<li><a href=\"seett\">View</a></li>\n" +
"		</ul>\n" +
"	</li>\n" +
"	<li><a href=\"#\" accesskey=\"6\">Welcome, Admin</a>\n" +
"	<ul>\n" +
"		<li><a href=\"logout\">Logout</a></li>\n" +
"	</ul>\n" +
"	</li>\n" +
"    </ul>\n" +
"  </div>\n" +
"<div id=\"content\"><br><br><br>");
         
p.println("<table style=\"width:100%\">\n" +
"<p><center><h2 style=\"color:#000000\"><strong>Unpaid Traffic Tickets :</strong></h2></center></p><br><br>\n" +       
"<tr>\n" +
"<th>License Number</th>\n" +
"<th>Name</th>\n" +
"<th>Fine Amount</th>\n" +
"<th>Issue Date</th>\n" +
"<th>Due Date</th>\n" +        
"</tr>");

   while(rs.next())
        {
            
            if(rs.getString("status").equals("1"))
                continue;
            else
            {
                licensenum=rs.getString("licensenum");
                name=rs.getString("name");
                issue=rs.getString("issue");
                fine=rs.getString("fine");
                due=rs.getString("due");
                
                due=due.substring(8)+"-"+due.substring(5,7)+"-"+due.substring(0,4);
                issue=issue.substring(8)+"-"+issue.substring(5,7)+"-"+issue.substring(0,4);
                
                p.println("<tr>\n" +
                        "<td>"+licensenum+"</td>\n" +
                        "<td>"+name+"</td>\n" +
                        "<td>"+fine+"</td>\n" +
                        "<td>"+issue+"</td>\n" +
                        "<td>"+due+"</td>\n"+
                        "</tr>\n");

                
            }
            

        }


        }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
