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

@WebServlet(name = "allBranServlet", urlPatterns = "/allBranServlet", description = "Shows all branches")

public class allBranServlet extends HttpServlet{
    
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
    {
        System.out.println("Entered allBranServlet");
        HttpSession hs = req.getSession();
        dbConn db = (dbConn) hs.getAttribute("db");
        String sql = "select * from pp_branches";
        String updmsg = (String)req.getAttribute("updmsg");
        String addelmsg = (String)req.getAttribute("addelmsg");
        String delmsg = (String)req.getAttribute("delmsg");
        String tbl = "";
        try {
            ResultSet rs = db.runQuery(sql);
            tbl += "<table class=\"tbl\">"+
                "<tr class=\"tbl_head\">    <th>Branch_ID</th> <th>Branch Name</th> <th>Address</th> <th>Phone</th> <th>Update</th> <th>Delete</th> <th>Store</th>    </tr>";
            while(rs.next())
                tbl += "<tr>  <td>" + rs.getString("id") + 
                        "</td> <td>" + rs.getString("name") + 
                        "</td> <td>" + rs.getString("address") + 
                        "</td> <td>" + rs.getLong("Phone")+ 
                        "</td> <td>" +  "<form action = \"updateDBServlet\" method = \"POST\">" +
                                        "<input type=\"hidden\" name=\"table\" value=\"pp_branches\">" +
                                        "<input type=\"hidden\" name = \"id\" value=\"" + rs.getString("id") + "\">" +
                                        "<input class=\"tbl_button\"    type=\"submit\" value=\"Update\">" +
                                        "</form>" + 
                        "</td> <td>" +  "<form action = \"isElementDeleted\" method = \"POST\">" +
                                        "<input type=\"hidden\" name=\"table\" value=\"pp_branches\">" +
                                        "<input type=\"hidden\" name = \"id\" value=\"" + rs.getString("id") + "\">" +
                                        "<input class=\"tbl_button\"    type=\"submit\" value=\"Delete\" onclick=\"return confirm(\'Are you sure you want to delete " + rs.getString("id") + "?\')\" />" +
                                        "</form>" + 
                        "</td> <td>" +  "<form action = \"viewStore\" method = \"POST\" id = \"myform\">" +
                                        "<input type=\"hidden\" name = \"viewId\" value=\"" + rs.getString("id") + "\">" +
                                        "<input class=\"tbl_button\"    type=\"submit\" value=\"Stock\">" +
                                        "</form>" + 
                        "</td>  </tr>";
            tbl += "</table>";
            tbl += "<form action = \"addDBServlet\" method = \"GET\">" +
                    "<input type=\"hidden\" name=\"table\" value=\"pp_branches\">" +
                    "<input type=\"submit\" value=\"Add Branch\">" +
                    "</form>";

        } catch (SQLException e) {
            e.printStackTrace();
            tbl = "Sql Query not executed, SQLException occurred";
        }
        String out = "<h2 style=\"text-align: center;\">All Branches of ProjectPharma</h2>";
        if(updmsg != null)    out += updmsg;
        if(addelmsg != null)    out += addelmsg;
        if(delmsg != null)    out += delmsg;
        out += tbl;

        req.setAttribute("output_title", "All Branches");
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