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
public class UNEmailVerification extends HttpServlet {

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
        
        PrintWriter p=response.getWriter();
        
        boolean flage=true,flagu=true;
        
        String username="",email="";
        
        HttpSession s=request.getSession();
     
        
        if(s.getAttribute("status").toString().equals("u")) //email in Context is eligible
        {
             email= s.getAttribute("email").toString();
             
             username=request.getParameter("username");
             s.setAttribute("username", username);
             
             try
             {
                 Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/RTO","root","maverick");
            
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery("select username from users");
            
            while(rs.next())
            {
                if(rs.getString("username").equals(username))
                {
                    flagu=false;
                    break;
                }
                
            }
            
            if(flagu==false)
            {
                p.println("<!DOCTYPE html><html>\n" +
"<head>\n" +
"<title>\n" +
"RTO- User Registration\n" +
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
"<center><h2 class=\"title\">USER REGISTRATION</h2></center>\n" +
"<br>\n" +
"<form name=\"reg2\" action=\"UNEmailVerification\" method=\"post\">\n" +
"<p><center>Username Already Taken. Pick Another: &nbsp<input type=\"text\" name=\"username\" required></center></p><br>\n" +
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
            
            
            else
            {
                
                
              String fname=s.getAttribute("fname").toString();
              String lname=s.getAttribute("lname").toString();
              String gender=s.getAttribute("gender").toString();
              String dob=s.getAttribute("dob").toString();
              String address=s.getAttribute("address").toString();
              String state=s.getAttribute("state").toString();
              String pass=s.getAttribute("pass").toString();
              String seq=s.getAttribute("seq").toString();
              String answer=s.getAttribute("answer").toString();
              
               String query="";
                
                if(seq.indexOf("'")!=-1)
                    query="insert into users(fname,lname,gender,email,address,state,username,password,seq,answer,apstatus,dob) values('"+fname+"','"+lname+"','"+gender+"','"+email+"','"+address+"','"+state+"','"+username+"','"+pass+"','"+seq.substring(0,seq.indexOf("'"))+"\\"+seq.substring(seq.indexOf("'"))+"','"+answer+"','0','"+dob+"');";

                else
                query="insert into users(fname,lname,gender,email,address,state,username,password,seq,answer,apstatus,dob) values('"+fname+"','"+lname+"','"+gender+"','"+email+"','"+address+"','"+state+"','"+username+"','"+pass+"','"+seq+"','"+answer+"','0','"+dob+"');";
                st.executeUpdate(query);
                
                
                
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
"<br><br><br><center><h2 class=\"title\">Congratulations! You have been registered.<br>Please login to Apply for licence or check your application status</h2><center><br>" +                        
"<p><a href=\"home\">Back to home</a></p><br>" +
                        
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
              
              
              
            
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
             
             
        }
        
        
        
        if(s.getAttribute("status").toString().equals("e")) //username in Context is eligible
        {
             username= s.getAttribute("username").toString();
             
             email=request.getParameter("email");
             s.setAttribute("email", email);
             
             try
             {
                 Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/RTO","root","maverick");
            
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery("select email from users");
            
            while(rs.next())
            {
                if(rs.getString("email").equals(email))
                {
                    flage=false;
                    break;
                }
                
            }
            
            if(flage==false)
            {
                p.println("<!DOCTYPE html><html>\n" +
"<head>\n" +
"<title>\n" +
"RTO- User Registration\n" +
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
"<center><h2 class=\"title\">USER REGISTRATION</h2></center>\n" +
"<br>\n" +
"<form name=\"reg2\" action=\"UNEmailVerification\" method=\"post\">\n" +
"<p><center>Email already registered! &nbsp<input type=\"text\" name=\"email\" required>&nbsp<span id=\"em\" style=\"color:red\"> </span></center></p><br>\n" +
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
            
            
            else
            {
                
                
              String fname=s.getAttribute("fname").toString();
              String lname=s.getAttribute("lname").toString();
              String gender=s.getAttribute("gender").toString();
              String dob=s.getAttribute("dob").toString();
              String address=s.getAttribute("address").toString();
              String state=s.getAttribute("state").toString();
              String pass=s.getAttribute("pass").toString();
              String seq=s.getAttribute("seq").toString();
              String answer=s.getAttribute("answer").toString();
              
               String query="";
                
                if(seq.indexOf("'")!=-1)
                    query="insert into users(fname,lname,gender,email,address,state,username,password,seq,answer,apstatus,dob) values('"+fname+"','"+lname+"','"+gender+"','"+email+"','"+address+"','"+state+"','"+username+"','"+pass+"','"+seq.substring(0,seq.indexOf("'"))+"\\"+seq.substring(seq.indexOf("'"))+"','"+answer+"','0','"+dob+"');";

                else
                query="insert into users(fname,lname,gender,email,address,state,username,password,seq,answer,apstatus,dob) values('"+fname+"','"+lname+"','"+gender+"','"+email+"','"+address+"','"+state+"','"+username+"','"+pass+"','"+seq+"','"+answer+"','0','"+dob+"');";
                st.executeUpdate(query);
                
                
                
                p.println("<!DOCTYPE html><html>\n" +
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
"<br><br><br><center><h2 class=\"title\">Congratulations! You have been registered.<br>Please login to Apply for licence or check your application status</h2><center><br>" +                        
"<p><a href=\"home\">Back to home</a></p><br>" +
                        
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
              
              
              
            
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
             
             
        }
        
        else if(s.getAttribute("status").toString().equals("b"))
        {
            
            username=request.getParameter("username");
            email=request.getParameter("email");
            
            try
            {
                 Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/RTO","root","maverick");

                Statement st=con.createStatement();

                ResultSet rs=st.executeQuery("select email,username from users");
                
                while(rs.next())
                {
                     if(rs.getString("email").equals(email))
                        flage=false;
                
                    if(rs.getString("username").equals(username))
                        flagu=false;

                    if(flage==false && flagu== false)
                        break;    
                    
                }
                
                
                
                
                if(flagu==true && flage==true)
                {
                    
                    String fname=s.getAttribute("fname").toString();
                    String lname=s.getAttribute("lname").toString();
                    String gender=s.getAttribute("gender").toString();
                    String dob=s.getAttribute("dob").toString();
                    String address=s.getAttribute("address").toString();
                    String state=s.getAttribute("state").toString();
                    String pass=s.getAttribute("pass").toString();
                    String seq=s.getAttribute("seq").toString();
                    String answer=s.getAttribute("answer").toString();
                    
                    
                    
                    
                     String query="";
                
                if(seq.indexOf("'")!=-1)
                    query="insert into users(fname,lname,gender,email,address,state,username,password,seq,answer,apstatus,dob) values('"+fname+"','"+lname+"','"+gender+"','"+email+"','"+address+"','"+state+"','"+username+"','"+pass+"','"+seq.substring(0,seq.indexOf("'"))+"\\"+seq.substring(seq.indexOf("'"))+"','"+answer+"','0','"+dob+"');";

                else
                query="insert into users(fname,lname,gender,email,address,state,username,password,seq,answer,apstatus,dob) values('"+fname+"','"+lname+"','"+gender+"','"+email+"','"+address+"','"+state+"','"+username+"','"+pass+"','"+seq+"','"+answer+"','0','"+dob+"');";
                st.executeUpdate(query);
                
                
                
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
"<br><br><br><center><h2 class=\"title\">Congratulations! You have been registered.<br>Please login to Apply for licence or check your application status</h2><center><br>" +                        
"<p><a href=\"home\">Back to home</a></p><br>" +
                        
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
                    
                
                
                    if(flagu==false && flage==true)
                    {
                        s.setAttribute("status","u");
                        s.setAttribute("email",email);
                    }
                    else if(flagu==true && flage==false)
                    {
                        s.setAttribute("status","e");
                        s.setAttribute("username",username);
                    }
                    else
                        s.setAttribute("status","b");
                    
                    
                    
                    
                    
                    p.println("<!DOCTYPE html><html>\n" +
                    "<head>\n" +
                    "<title>\n" +
                    "RTO- User Registration\n" +
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
                    "		<li><a href=\"regisration\">Register</a></li>\n" +
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
                    "<form name=\"reg2\" action=\"UNEmailVerification\" method=\"post\">\n");

                    if(flage==false)
                    {
                        p.println("<p><center>Email already registered! &nbsp<input type=\"text\" name=\"email\" required>&nbsp<span id=\"em\" style=\"color:red\"> </span></center></p><br>\n");
                    }

                    if(flagu==false)
                    {
                        p.println("<p><center>Username Already Taken. Pick Another: &nbsp<input type=\"text\" name=\"username\" required></center></p><br>\n");
                    }


                    p.println("<center><input type=\"submit\" name=\"Submit\" value=\"submit\"></center><br>\n" +
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
            catch(Exception f)
            {
                f.printStackTrace();;
            }
            
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
