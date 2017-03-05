/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import static java.sql.DriverManager.println;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
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
public class registration extends HttpServlet {

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
        
        HttpSession y=request.getSession(true);
        
        if(y.getAttribute("logged")!=null)
        {
            RequestDispatcher r=request.getRequestDispatcher("home");
            r.forward(request, response);
        }
        
        PrintWriter p=response.getWriter();
        String q[]=new String[1];
        String s[]=new String[3];
        try
        {
             Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/RTO","root","maverick");
            
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from questions");
            
            String temp="";
            while(rs.next())
                temp=temp+rs.getString("question")+"|";
                
            q=temp.split("\\|");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        Random r=new Random();
        for(int i=0;i<3;i++)
        {
            int l=r.nextInt(q.length);
            if(q[l]!=null)
            {
                s[i]=q[l];
                q[l]=null;
            }
            else
                i--;
        }
        
        p.println("<!DOCTYPE html><html>\n" +
"<head>\n" +
"<title>\n" +
"RTO- User Registration\n" +
"</title>\n" +"<div id=\"headimg\"></div>\n"    +
"<div id=\"header\">\n" +
"</div>\n" +
"<link href=\"style1.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
"<script type=\"text/javascript\" src=\"regvalidate.js\"></script>\n" +
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
"<center><h2 class=\"title\">USER REGISTRATION</h2></center>\n" +
"<br>\n" +
"<form name=\"reg\" action=\"RegVerification\" method=\"post\" onSubmit=\"return validate();\">\n" +
"<p><center>First Name &nbsp<input type=\"text\" name=\"fname\" required></center></p><br>\n" +
"<p><center>Last Name &nbsp<input type=\"text\" name=\"lname\" required></center></p><br>\n" +
"<p><center>Pick Gender &nbsp<select name=\"gender\">\n" +
"<option value=\"male\">Male</option>\n" +
"<option value=\"female\">Female</option>\n" +
"</select></center></p><br> \n" +
"<center> Date of Birth(dd/mm/yyyy) <input type=\"text\" name=\"dd\" size=\"2\" required>/<input type=\"text\" name=\"mm\" size=\"2\" required>/<input type=\"text\" name=\"yyyy\" size=\"4\" required>&nbsp<span id=\"dob\" style=\"color:red\"> </span></center><br>\n" +
"<p><center>Email &nbsp<input type=\"text\" name=\"email\" required>&nbsp<span id=\"em\" style=\"color:red\"> </span></center></p><br>\n" +
"<p><center>Address &nbsp<input type=\"text\" name=\"address\" required></center></p><br>\n" +
"<p><center>State &nbsp<select name=\"state\">\n" +
"<option value='select'>Select</option>\n" +
"<option value='Andhra Pradesh'>Andhra Pradesh</option>\n" +
"<option value='Arunachal Pradesh'>Arunachal Pradesh</option>\n" +
"<option value='Assam'>Assam</option>\n" +
"<option value='Bihar'>Bihar</option>\n" +
"<option value='Chandigarh'>Chandigarh</option>\n" +
"<option value='Chhattisgarh'>Chhattisgarh</option>\n" +
"<option value='Dadra and Nagar Haveli'>Dadra and Nagar Haveli</option>\n" +
"<option value='Daman and Diu'>Daman and Diu</option>\n" +
"<option value='Delhi'>Delhi</option>\n" +
"<option value='Goa'>Goa</option>\n" +
"<option value='Gujarat'>Gujarat</option>\n" +
"<option value='Haryana'>Haryana</option>\n" +
"<option value='Himachal Pradesh'>Himachal Pradesh</option>\n" +
"<option value='Jammu and Kashmir'>Jammu and Kashmir</option>\n" +
"<option value='Jharkhand'>Jharkhand</option>\n" +
"<option value='Karnataka'>Karnataka</option>\n" +
"<option value='Kerala'>Kerala</option>\n" +
"<option value='Lakshadweep'>Lakshadweep</option>\n" +
"<option value='Madhya Pradesh'>Madhya Pradesh</option>\n" +
"<option value='Maharashtra'>Maharashtra</option>\n" +
"<option value='Manipur'>Manipur</option>\n" +
"<option value='Meghalaya'>Meghalaya</option>\n" +
"<option value='Mizoram'>Mizoram</option>\n" +
"<option value='Nagaland'>Nagaland</option>\n" +
"<option value='Odisha'>Odisha</option>\n" +
"<option value='Puducherry'>Puducherry</option>\n" +
"<option value='Punjab'>Punjab</option>\n" +
"<option value='Rajasthan'>Rajasthan</option>\n" +
"<option value='Sikkim'>Sikkim</option>\n" +
"<option value='Tamil Nadu'>Tamil Nadu</option>\n" +
"<option value='Telengana'>Telengana</option>\n" +
"<option value='Tripura'>Tripura</option>\n" +
"<option value='Uttar Pradesh'>Uttar Pradesh</option>\n" +
"<option value='Uttarakhand'>Uttarakhand</option>\n" +
"<option value='West Bengal'>West Bengal</option>\n"+
"</select>&nbsp<span id=\"st\" style=\"color:red\"> </span></center></p><br> \n" +
"<p><center>Choose a username &nbsp<input type=\"text\" name=\"username\" required></center></p><br>\n" +
"<p><center>Password &nbsp<input type=\"password\" name=\"pass\" required>&nbsp<span id=\"p\" style=\"color:red\"> </span></center></p><br>\n" +
"<p><center>Confirm Password &nbsp<input type=\"password\" name=\"pass1\" required>&nbsp<span id=\"cp\" style=\"color:red\"> </span></center></p><br>\n" +
"<p><center>Security Question &nbsp<select name=\"seq\">\n" +
"<option value=\""+s[0]+"\">"+s[0]+"</option>\n" +
"<option value=\""+s[1]+"\">"+s[1]+"</option>\n" +
"<option value=\""+s[2]+"\">"+s[2]+"</option>\n" +
"</select></center></p><br>\n" +
"<center>Answer &nbsp<input type=\"text\" name=\"answer\" required></center><br>\n" +
"<center><input type=\"submit\" name=\"Submit\" value=\"submit\"></center><br>\n" +
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
