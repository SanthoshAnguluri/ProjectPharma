package com.sidebarResults;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.dbConn;

@WebServlet(name = "allCustServlet", urlPatterns = "/allCustServlet", description = "Shows all Customers")

public class allCustServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
    {
        System.out.println("Entered allCustServlet");
        HttpSession hs = req.getSession();
        dbConn db = (dbConn) hs.getAttribute("db");
        String sql = "select * from customers";
        String updmsg = (String)req.getAttribute("updmsg");
        String addelmsg = (String)req.getAttribute("addelmsg");
        String delmsg = (String)req.getAttribute("delmsg");
        String tbl = "";
        try {
            ResultSet rs = db.runQuery(sql);
            tbl += "<table class=\"tbl\">"+
                "<tr class=\"tbl_head\">  <th>Customer_ID</th> <th>Customer Name</th> <th>Member_Type</th> <th>Phone</th> <th>Address</th> <th>Update Info</th> <th>Remove Customer</th>  </tr>";
            while(rs.next())
                tbl += "<tr> <td>" + rs.getString("id") + 
                        "</td> <td>" + rs.getString("name") + 
                        "</td> <td>" + rs.getString("card") + 
                        "</td> <td>" + rs.getBigDecimal("phone") + 
                        "</td> <td>" + rs.getString("address") +  
                        "</td> <td>" +  "<form action = \"updateDBServlet\" method = \"POST\">" +
                                        "<input type=\"hidden\" name=\"table\" value=\"customers\">" +
                                        "<input type=\"hidden\" name = \"id\" value=\"" + rs.getString("id") + "\">" +
                                        "<input class=\"tbl_button\"    type=\"submit\" value=\"Update\">" +
                                        "</form>" + 
                        "</td> <td>" +  "<form action = \"isElementDeleted\" method = \"POST\">" +
                                        "<input type=\"hidden\" name=\"table\" value=\"customers\">" +
                                        "<input type=\"hidden\" name = \"id\" value=\"" + rs.getString("id") + "\">" +
                                        "<input class=\"tbl_button\"    type=\"submit\" value=\"Cancel Membership\" onclick=\"return confirm(\'Are you sure you want to remove " + rs.getString("id") + "?\')\" />" +
                                        "</form>" + 
                        "</td> </tr>";
            tbl += "</table>";
            tbl += "<form action = \"addDBServlet\" method = \"GET\">" +
                    "<input type=\"hidden\" name=\"table\" value=\"customers\">" +
                    "<input type=\"submit\" value=\"Add Customer\">" +
                    "</form>";

        } catch (SQLException e) {
            e.printStackTrace();
            tbl = "Sql Query not executed, SQLException occurred";
        }
        String out = "<h2 style=\"text-align: center;\">Registered Customers of ProjectPharma</h2>";
        if(updmsg != null)    out += updmsg;
        if(addelmsg != null)    out += addelmsg;
        if(delmsg != null)    out += delmsg;
        out += tbl;

        req.setAttribute("output_title", "All Customers");
        req.setAttribute("output_main", out);
        RequestDispatcher rd = req.getRequestDispatcher("/pageViewServlet");
        rd.forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        doGet(req, res);
    }
}