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
public class loginverification extends HttpServlet {

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
        
        String username=request.getParameter("username");
        String pass=request.getParameter("pass");
        String dbpass="";
        PrintWriter p=response.getWriter();
        
   
        boolean flagu=false;
        boolean flagp=false;
       
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/RTO","root","maverick");
            
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery("select username,password from users;");
            
            while(rs.next())
            {
                if(rs.getString("username").equals(username))
                {
                    flagu=true;
                    dbpass=rs.getString("password");
                    break;
                    
                }
            }
            
            if(dbpass.equals(pass))
                flagp=true;
            
            if(flagu==true && flagp==true)
            {
                HttpSession hs=request.getSession();
                hs.setAttribute("logged", username);
                
                RequestDispatcher rd=request.getRequestDispatcher("usermenu");
                rd.forward(request, response);
                
            }
            
            else 
            {
                p.println("<!DOCTYPE html><html>\n" +
"<head>\n" +
"<title>\n" +
"RTO- User Login\n" +
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
"<center><h2 class=\"title\">USER LOGIN</h2></center>\n" +
"<br><br>\n");
          
                if(flagu==false)
                {
                    p.println("<p><center><div id=\"temp\" style=\"color:red\">No such eser exists!</div></center></p><br><br>");
                    
                }
                else if(flagu==true && flagp==false)
                {
                    p.println("<p><center><div id=\"temp\" style=\"color:red\">Password Incorrect!</div></center></p><br><br>");
                }
p.println("<form name=\"login\" action=\"loginverification\" method=\"post\" >\n" +
"<p><center>Username: &nbsp<input type=\"text\" name=\"username\" required></center></p><br>\n" +
"<p><center>Password: &nbsp<input type=\"password\" name=\"pass\" required></center></p><br>\n" +
"<center><input type=\"submit\" name=\"Submit\" value=\"submit\"></center><br>\n" +
"</form>\n" +
"<br><br><p><center><a href=\"forgot\" color=\"blue\">Forgot Password?</a></center></p><br>\n" +                 
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
        catch(Exception e)
        {
            e.printStackTrace();
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
