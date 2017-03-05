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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Samuel
 */
public class displaycomplaintstatus extends HttpServlet {

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
        
        
        HttpSession hs=request.getSession(true);
        
        String fn="";
        String username="";
        PrintWriter p=response.getWriter();
        String reply="";
        String status="";
        String message="";
        
        String id=request.getParameter("id");
        
        
        
        if(hs.getAttribute("logged")==null)
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
"<div id=\"content\">" +
"<br><br>\n");
        }
        
        
        else
        {
            username=hs.getAttribute("logged").toString();
            
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/RTO","root","maverick");
            
                Statement st=con.createStatement();
                ResultSet rs;
                
                rs=st.executeQuery("select fname from users where username=\'"+username+"\';");
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
"<div id=\"content\">\n" +
"<br><br>\n");
            
            
            
            
            
        }
        
            


try
{
    Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/RTO","root","maverick");
            
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery("select status,reply,message from complaints where id='"+id+"';");
                
                if(!rs.next())
                {
                    p.println("<br><center><h2 class=\"title\">Invalid Complaint ID. Try Again!</h2></center><br><br>\n");
                }
                else
                {
                    status=rs.getString("status");
                    reply=rs.getString("reply");
                    message=rs.getString("message");
                    
                    if(status.equals("0"))
                    {
                        p.println("<br><center><h2 class=\"title\">No reply yet. Please be patient!</h2></center><br><br>\n");
                    }
                    else if(status.equals("1"))
                    {
                        p.println("<br><center><h2 class=\"title\">The complaint has been replied to</h2></center><br><br>\n" +
                                "<center><form action=\"#\" method=\"post\">\n" +
"\n" +
"<p><strong><center>Your message : <textarea rows=\"5\" cols=\"25\" readonly>"+message+"</textarea></center></strong></p><br><br>\n" +
"<p><strong><center>Reply : <textarea rows=\"5\" cols=\"25\" readonly>"+reply+"</textarea></center></strong></p><br><br>\n" +
"</form></center><br><br>\n");
                    }
                }
}
catch(Exception e)
{
    e.printStackTrace();
}

                                
                    

p.println("</div>\n" +
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
