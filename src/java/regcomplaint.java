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
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Samuel
 */
public class regcomplaint extends HttpServlet {

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
        
        
        PrintWriter p=response.getWriter();
        
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String phno=request.getParameter("phno");
        String category=request.getParameter("category");
        String message=request.getParameter("message");
        String id="";
        String query="";
        String username="";
        String fn="";
        
        
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/RTO","root","maverick");
            
            Statement st=con.createStatement();
            
            Random r=new Random();
            
            int ran=r.nextInt(90000000)+10000000;
            
            id=(new Integer(ran)).toString();
            
            boolean flag=true;
            
            ResultSet rs;
            
            while(true)
            {
                flag=true;
                st=con.createStatement();
                rs=st.executeQuery("select id from complaints;");
                
                
                while(rs.next())
                {
                    if(rs.getString("id")!=null && rs.getString("id").equals(id))
                    {
                        
                        flag=false;
                        break;
                    }
                }
                if(flag==true)
                    break;
                
                ran=r.nextInt(90000000)+10000000;
                id=(new Integer(ran)).toString();
            }
            
            query="insert into complaints values('"+id+"','"+name+"','"+email+"','"+phno+"','"+category+"','"+message+"','0',NULL);";
            
             st=con.createStatement();
            
            st.executeUpdate(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
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
"<div id=\"content\">\n" +
"<br><br>\n" +
"<br><br><center><h2 class=\"title\">Complaint Registered Successfully!</h2></center><br><br>\n" +
"<br><br><p><center><strong>Your unique Complaint ID :"+id+"</strong></center></p><br><br>\n" +                    
"<br>\n" +
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
"<br><br>\n" +
"<center><h2 class=\"title\">File Complaint</h2></center>\n" +
"<br>\n" +
"<form action=\"regcomplaint\" method=\"post\" >\n" +
"<p><center>Full Name : &nbsp<input type=\"text\" name=\"name\" required></center></p><br>\n" +
"<p><center>Email : &nbsp<input type=\"password\" name=\"email\" required></center></p><br>\n" +
"<p><center>Phone No. : &nbsp<input type=\"text\" name=\"phno\" required></center></p><br>\n" +
"<p><center>Complaint Category : &nbsp<select name=\"category\">\n" +
"<option value=\"suggestion\">Suggestion</option>\n" +
"<option value=\"traffic\">Traffic</option>\n" +
"<option value=\"services\">Services</option>\n" +
"<option value=\"employee\">Employee</option>\n" +
"<option value=\"vandalism\">Vandalism</option>\n" +
"</select></center></p><br> \n" +                   
"<p><center>Message : &nbsp<input type=\"text\" name=\"message\" required maxlength=\"500\"></center></p><br><br>\n" +                   
"<center><input type=\"submit\" name=\"Submit\" value=\"submit\"></center><br><br>\n" +
"</form>\n" +                                        
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
