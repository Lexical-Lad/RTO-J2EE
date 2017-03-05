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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
/**
 *
 * @author Samuel
 */
public class usermenu extends HttpServlet {

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
        
        doPost(request, response);
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
        
        if(hs.getAttribute("logged")==null)
        {
            RequestDispatcher r=request.getRequestDispatcher("home");
            r.forward(request, response);
        }
        
        
        
        PrintWriter p=response.getWriter();
        
        String username=hs.getAttribute("logged").toString();
        String apstatus="";
        String payment="";
        String fn="";
        String reap="";
        String apnumber="";
        String apstatusmessage="";
        String paymentmessage="";
        String reapmessage="";
        String today="0";
        String lictype="";
        String lictypemessage="";
        String reapdate="";
        
        try
        {
                Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/RTO","root","maverick");
            
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery("select fname,reap,apstatus,apnumber,lictype,payment from users where username='"+username+"';");
            
            if(!rs.next())
            {
                hs.invalidate();
                RequestDispatcher r=request.getRequestDispatcher("home");
                r.forward(request, response);
            }
            else
            {
                fn=rs.getString("fname");
                apstatus=rs.getString("apstatus");
                
                payment=rs.getString("payment");
                reap=rs.getString("reap");    
                apnumber=rs.getString("apnumber");
                lictype=rs.getString("lictype");
                
                if(reap!=null)reapdate=reap.substring(6)+"-"+reap.substring(4,6)+"-"+reap.substring(0,4);
                
                
                if(apstatus.equals("0"))
                    apstatusmessage="No Application for a license submitted yet!";
                
                else if(apstatus.equals("1"))
                {
                    apstatusmessage="License Applied for(Approval Pending)";
                    paymentmessage="*";
                    
                    if(lictype.equals("0"))
                        lictypemessage="Learner's License";
                    else if(lictype.equals("1"))
                        lictypemessage="2-Wheeler's License";
                    else if(lictype.equals("2"))
                        lictypemessage="4-Wheeler's License";
                    
                }
                        
                            
                
                else if(apstatus.equals("2"))
                {
                    apstatus="License Application Denied";
                    
                    DateFormat df=new SimpleDateFormat("yyyyMMdd");
                    
                    Date date=new Date();
                    
                    today=df.format(date);
                    
                    if(Integer.parseInt(today)>Integer.parseInt(reap))
                        reapmessage="Eligible";
                    else
                        reapmessage="Ineligible";
                    
                }
                
                else if(apstatus.equals("3"))
                {
                    apstatusmessage="License Application Approved";
                    
                    if(payment.equals("0"))
                        paymentmessage="Pending";
                    else
                        paymentmessage="Done";
                        
                    if(lictype.equals("0"))
                        lictypemessage="Learner's License";
                    else if(lictype.equals("1"))
                        lictypemessage="2-Wheeler's License";
                    else if(lictype.equals("2"))
                        lictypemessage="4-Wheeler's License";
                    
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
"	<li><a href=\"#\" accesskey=\"6\">Welcome,&nbsp"+fn+"</a>\n" +
"	<ul>\n" +
"		" +
"		<li><a href=\"logout\">Logout</a></li>\n" +
"	</ul>\n" +
"	</li>\n" +
"    </ul>\n" +
"  </div>\n" +
"<div id=\"content\"><br><br>\n");
         
                 
  
 if(apstatus.equals("0"))
     p.println("<table style=\"width:100%\">\n" +
"<p><center><h2 style=\"color:#000000\"><strong>License Application<strong></h2></center></p><br><br>\n" +
"\n" +
"<tr>\n" +
"<th>Application Status</th>\n" +
"</tr>\n" +
"<tr>\n" +
"<td><center>"+apstatusmessage+"<center></td>\n" +
"</tr>\n" +
"</table><br><br><br>\n" +
"<p><center><strong>You haven't yet applied for a license!<br>Pick a license type from the menu below and Click \"Apply\"</strong></center></p><br><br>\n" +
"\n" +
"<form name=\"licapply\" action=\"apply\" method=\"post\">\n" +
"<p><center>License Type: &nbsp<select name=\"lictype\">\n" +
"<option value=\"0\">Learner's License</option>\n" +
"<option value=\"1\">2-Wheeler's License</option>\n" +
"<option value=\"2\">4-Wheeler's License</option>\n" +
"</select></center></p><br>\n" +
"<center><input type=\"submit\" name=\"Apply\" value=\"Apply\"></center><br>\n" +
"</form> \n" +
"\n" +
"\n" +
"\n" +
"<br><br><br><br><br><p><center>Once applied, you can log-in any time to see the application status.<br>If accepted, you will be required to pay the pertinent fee online, through our payment gateway to secure your test date.</center></p><br><br>");
 
 else if(apstatus.equals("1"))
     p.println("<table style=\"width:100%\">\n" +
"<p><center><h2 style=\"color:#000000\"><strong>License Application<strong></h2></center></p><br><br>\n" +
"\n" +
"<tr>\n" +
"<th>Application No.</th>\n" +             
"<th>Application Status</th>\n" +
"<th>Payment</th>\n" +             
"</tr>\n" +
"<tr>\n" +
"<td>"+apnumber+"</td>\n" +            
"<td>"+apstatusmessage+"<br>("+lictypemessage+")</td>\n" +
"<td>"+paymentmessage+"</td>\n" +            
"</tr>\n" +
"</table><br><br><br>\n" +
"<p><center><strong>You have applied for a "+lictypemessage+".Your application is still pending</strong></center></p><br><br>\n" +
"<p><center><strong>Please check back regularly for the current status</strong></center></p><br><br>");


else if(apstatus.equals("2"))
        {
            if(reapmessage.equals("Eligible"))
            {
                p.println("<table style=\"width:100%\">\n" +
"<p><center><h2 style=\"color:#000000\"><strong>License Application<strong></h2></center></p><br><br>\n" +
"\n" +
"<tr>\n" +
"<th>Application No.</th>\n" +             
"<th>Application Status</th>\n" +
"<th>Payment</th>\n" + 
"<th>Re-application Eligibility</th>\n" + 
"</tr>\n" +
"<tr>\n" +
"<td>"+apnumber+"</td>\n" +            
"<td>"+apstatusmessage+"<br>("+lictypemessage+")</td>\n" +
"<td>"+paymentmessage+"</td>\n" + 
"<td>"+reapmessage+"</td>\n" + 
"</tr>\n" +
"</table><br><br><br>\n" +
"<p><center><strong>You had applied for a "+lictypemessage+". Sorry, your application was denied</strong></center></p><br>\n" +
"<p><center><strong>However, you are eligible to re-apply fora license</strong></center></p><br><br>\n" +
"<form name=\"lictype\" action=\"apply\" method=\"post\">\n" +
"<p><center>License Type: &nbsp<select name=\"lictype\">\n" +
"<option value=\"0\">Learner's License</option>\n" +
"<option value=\"1\">2-Wheeler's License</option>\n" +
"<option value=\"2\">4-Wheeler's License</option>\n" +
"</select></center></p><br>\n" +
"<p><center<input type=\"submit\" value=\"submit\">Apply</center></p><br>\n" +
"</form> \n" +
"\n" +
"\n" +
"\n" +
"<br><br><br><br><br><p><center>Once applied, you can log-in any time to see the application status.<br>If accepted, you will be required to pay the pertinent fee online, through our payment gateway to secure your test date.</center></p><br><br>");
            }
            else
            {
                p.println("<table style=\"width:100%\">\n" +
"<p><center><h2 style=\"color:#000000\"><strong>License Application<strong></h2></center></p><br><br>\n" +
"\n" +
"<tr>\n" +
"<th>Application No.</th>\n" +             
"<th>Application Status</th>\n" +
"<th>Payment</th>\n" + 
"<th>Re-application Eligibility</th>\n" + 
"</tr>\n" +
"<tr>\n" +
"<td>"+apnumber+"</td>\n" +            
"<td>"+apstatusmessage+"<br>("+lictypemessage+")</td>\n" +
"<td>"+paymentmessage+"</td>\n" + 
"<td>"+reapmessage+"</td>\n" + 
"</tr>\n" +
"</table><br><br><br>\n" +
"<p><center><strong>You had applied for a "+lictypemessage+". Sorry, your application was denied</strong></center></p><br>\n" +
"<p><center><strong>You will be eligible to reapply for a license on"+reapdate+"</strong></center></p><br>\n");
            }
        }
 
else if(apstatus.equals("3"))
    p.println("<table style=\"width:100%\">\n" +
"<p><center><h2 style=\"color:#000000\"><strong>License Application<strong></h2></center></p><br><br>\n" +
"\n" +
"<tr>\n" +
"<th>Application No.</th>\n" +             
"<th>Application Status</th>\n" +
"<th>Payment</th>\n" +             
"</tr>\n" +
"<tr>\n" +
"<td>"+apnumber+"</td>\n" +            
"<td>"+apstatusmessage+"<br>("+lictypemessage+")</td>\n" +
"<td>"+paymentmessage+"</td>\n" +            
"</tr>\n" +
"</table><br><br><br>\n" +
"<p><center><strong>Your application for a "+lictypemessage+" was approved!</strong></center></p><br>\n" +
"<p><center><strong>You need to pay the pertinent online fee through our portal('service'>>'Online payment' from the navigation menu</strong></center></p><br>\n" +
"<p><center><strong>Thereafter, the date and time for your driving test would be mailed to you.(If already paid, check your email for further details)</strong></center></p><br><br><br>");
    

             
             
             
             
             
             
             
             
             
             
p.println("</div>\n" +
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
