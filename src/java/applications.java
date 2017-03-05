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
public class applications extends HttpServlet {

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
         
         String username="";
         String name="";
         String gender="";
         String email="";
         String apnumber="";
         String lictype="";
         String address="";
         String dob="";
         String hov="";
         String lictypemessage="";
         
         
         
         
         
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

//table  logic

    p.println("<table style=\"width:100%\">\n" +
"<p><center><h2 style=\"color:#000000\"><strong>Pending Applications :</strong></h2></center></p><br><br>\n" +
"<br><p><center><strong>(Hover over the applicant names to see more details about the respective candidates)</strong></center></p><br><br>\n" +        
"<tr>\n" +
"<th>Application No.</th>\n" +
"<th>Applicant Name</th>\n" +
"<th>License Type</th>\n" +
"<th>Select</th>\n" +
"</tr>" +
"<form action=\"apprej\" method=\"post\">");


    try
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/RTO","root","maverick");

        Statement st=con.createStatement();
        
        ResultSet rs=st.executeQuery("select * from users");
        
        while(rs.next())
        {
            if(!rs.getString("apstatus").equals("1"))
                continue;
            else
            {
                username=rs.getString("username");
                name=rs.getString("fname")+" "+rs.getString("lname");
                gender=rs.getString("gender");
                email=rs.getString("email");
                address=rs.getString("address");
                lictype=rs.getString("lictype");
                apnumber=rs.getString("apnumber");
                dob=rs.getString("dob");
                dob=dob.substring(8)+"-"+dob.substring(5,7)+"-"+dob.substring(0,4);
                
                if(lictype.equals("0"))
                        lictypemessage="Learner's License";
                    else if(lictype.equals("1"))
                        lictypemessage="2-Wheeler's License";
                    else if(lictype.equals("2"))
                        lictypemessage="4-Wheeler's License";
                
                hov="Username : "+username+"\nGender : "+gender+"\nEmail : "+email+"\nAddress : "+address+"\nDate of Birth : "+dob;
                
                p.println("<tr>\n" +
                        "<td>"+apnumber+"</td>\n" +
                        "<td><span title=\""+hov+"\">"+name+"</span></td>\n" +
                        "<td>"+lictypemessage+"</td>\n" +
                        "<td><input type=\"checkbox\" name=\"check\" value=\""+username+"\"></td>\n"  +
                        "</tr>\n");
                
                
                
                
            }
        
        
        }
        
        
    }
    catch(Exception e)
    {
        e.printStackTrace();;
        
    }
    
    
    p.println("</table><br><br><br><center><input type=\"submit\" name=\"submit\" value=\"Approve\">&nbsp<input type=\"submit\" name=\"submit\" value=\"Reject\"></center><br><br>\n" +
"</form>");
    

         
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
