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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Samuel
 */
public class home extends HttpServlet {

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
        
         PrintWriter p=response.getWriter();
         
         HttpSession sc=request.getSession(false);
         
        if(sc==null || sc.getAttribute("logged")==null)
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
"      	<li><a href=\"#\" accesskey=\"2\">Services</a>\n" +
"	<ul>\n" +
"		<li><a href=\"registration\">Register</a></li>\n" +
"		<li><a href=\"filecomplaint\">File Complaint</a></li>\n" +
"		<li><a href=\"complaintstatus\">Complaint Status</a></li>\n" +
"               <li><a href=\"login\">Login</a></li>\n" +
"	</ul>\n" +
"	</li>\n" +
"      	<li><a href=\"gallery\" accesskey=\"3\">Gallery</a></li>\n" +
"      	<li><a href=\"about\" accesskey=\"4\">About</a></li>\n" +
"      	<li><a href=\"devsquad\" accesskey=\"5\">Contact Us</a></li>\n" +
"	<li><a href=\"adminlogin\" accesskey=\"6\">Admin Login</a></li>\n" +
"    </ul>\n" +
"  </div>\n" +
"<div id=\"content\">\n" +
"<br><br>\n" +
"<br><br><br><br><br>" +
"<p><strong>Welcome to the website of the Transport Commissioner, Uttar Pradesh. The Transport Commissioner's Organisation (TCO) deals with various transport related matters like driving licenses, registration of motor vehicles, grant and renewal of permits, collection of taxes / fees, and all the other regulatory and enforcement functions assigned to it under provisions of the Motor Vehicles Act, 1988, and functions through its headquarters at Lucknow, and RTO & ARTO offices spread all across the state. </strong></p><br><br>\n" +
"\n" +
"<center><h3>Departmental Heads</h3>\n" +
"          <p></p>\n" +
"          <ul>\n" +
"            <li> <strong>Principal Secretary</strong> :  Sri Kumar Arvind Singh Deo, IAS</li>\n" +
"            <li> <strong>Transport Commissioner</strong> :  Sri K. Ravinder Naik, IAS</li>\n" +
"          </ul>\n" +
"</center>\n" +
"<br><br>\n" +
"\n" +
"\n" +
"</div>\n" +
"</div>\n" +
"\n" +
"<div id=\"footer\">\n" +
"<p id=\"brand\"><a href=\"http://www.uptransporthelpline.org/\" target=\"_blank\"><img src=\"images/helpline.jpg\" align=\"left\" /></a></p>\n" +
"<p id=\"legal\">Office of the Transport Commissioner UP, Tehri Kothi, MG Marg, Lucknow - 226001 (UP)</p>\n" +
"<p id=\"legal\">Website developed in house at Transport Commissioner's Office</p>\n" +
"<p id=\"legal\"><strong><a href=\"devsquad\">Development Team</a></strong></p>\n" +
"</div>\n" +
"\n" +
"\n" +
"</body>\n" +
"</html>");
        }
        else if(!sc.getAttribute("logged").equals("*"))
        {
            String fn="";
            try{
                
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/RTO","root","maverick");
            String query="select fname from users where username ='"+sc.getAttribute("logged")+"';";
            Statement s=con.createStatement();
            
            ResultSet rs=s.executeQuery(query);
            rs.next();
            fn=rs.getString("fname");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
            
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
"      	<li><a href=\"#\" accesskey=\"2\">Services</a>\n" +
"	<ul>\n" +
"		<li><a href=\"filecomplaint\">File Complaint</a></li>\n" +
"		<li><a href=\"complaintstatus\">Complaint Status</a></li>\n" +
"		<li><a href=\"payment\">Online Payment</a></li>\n" +
"		<li><a href=\"usermenu\">Application Status</a></li>\n" +                                        
"	</ul>\n" +
"	</li>\n" +
"      	<li><a href=\"gallery\" accesskey=\"3\">Gallery</a></li>\n" +
"      	<li><a href=\"about\" accesskey=\"4\">About</a></li>\n" +
"      	<li><a href=\"devsquad\" accesskey=\"5\">Contact Us</a></li>\n" +
"	<li><a href=\"#\" accesskey=\"6\">Welcome,"+fn+"</a>\n" +
"	<ul>\n" +
"		" +
"		<li><a href=\"logout\">Logout</a></li>\n" +
"	</ul>\n" +
"	</li>\n" +
"    </ul>\n" +
"  </div>\n" +
"<div id=\"content\">"+
                    "<br><br>\n" +
"<br><br><br><br><br>" +
"<p><strong>Welcome to the website of the Transport Commissioner, Uttar Pradesh. The Transport Commissioner's Organisation (TCO) deals with various transport related matters like driving licenses, registration of motor vehicles, grant and renewal of permits, collection of taxes / fees, and all the other regulatory and enforcement functions assigned to it under provisions of the Motor Vehicles Act, 1988, and functions through its headquarters at Lucknow, and RTO & ARTO offices spread all across the state. </strong></p><br><br>\n" +
"\n" +
"<center><h3>Departmental Heads</h3>\n" +
"          <p></p>\n" +
"          <ul>\n" +
"            <li> <strong>Principal Secretary</strong> :  Sri Kumar Arvind Singh Deo, IAS</li>\n" +
"            <li> <strong>Transport Commissioner</strong> :  Sri K. Ravinder Naik, IAS</li>\n" +
"          </ul>\n" +
"</center>\n" +
"<br><br>\n" +
"\n" +
"\n" +
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
        else if(sc.getAttribute("logged").equals("*"))
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
"<br><br><br><br><br><p><h3><center>Use the Navigation Bar to access various services!</center></h3></p><br><br>\n" +                    
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
