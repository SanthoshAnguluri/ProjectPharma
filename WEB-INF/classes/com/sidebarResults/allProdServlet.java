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

@WebServlet(name = "allProdServlet", urlPatterns = "/allProdServlet", description = "Shows all products")

public class allProdServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
    {
        System.out.println("Entered allProdServlet");
        HttpSession hs = req.getSession();
        dbConn db = (dbConn) hs.getAttribute("db");
        String sql = "select * from products";
        String updmsg = (String)req.getAttribute("updmsg");
        String addelmsg = (String)req.getAttribute("addelmsg");
        String delmsg = (String)req.getAttribute("delmsg");
        String tbl = "";
        try {
            ResultSet rs = db.runQuery(sql);
            tbl += "<table class=\"tbl\">"+
                "<tr class=\"tbl_head\">    <th>Product_id</th>  <th>Product Name</th>  <th>Cost(per sheet for tablets)</th>  <th>Product Type</th>  <th>Update Info</th>  <th>Delete Product</th> <th>Purchase</th>    </tr>";
            while(rs.next())
                tbl += "<tr> <td>" + rs.getString("id") + 
                        "</td> <td>" + rs.getString("name") + 
                        "</td> <td>" + rs.getFloat("cost") + 
                        "</td> <td>" + rs.getString("type") + 
                        "</td> <td>" +  "<form action = \"updateDBServlet\" method = \"POST\">" +
                                        "<input type=\"hidden\" name=\"table\" value=\"products\">" +
                                        "<input type=\"hidden\" name = \"id\" value=\"" + rs.getString("id") + "\">" +
                                        "<input class=\"tbl_button\"    type=\"submit\" value=\"Update\">" +
                                        "</form>" + 
                        "</td> <td>" +  "<form action = \"isElementDeleted\" method = \"POST\">" +
                                        "<input type=\"hidden\" name=\"table\" value=\"products\">" +
                                        "<input type=\"hidden\" name = \"id\" value=\"" + rs.getString("id") + "\">" +
                                        "<input class=\"tbl_button\"    type=\"submit\" value=\"Delete\" onclick=\"return confirm(\'Are you sure you want to delete " + rs.getString("id") + "?\')\" />" +
                                        "</form>" + 
                        "</td> <td>" +  "<form action = \"buySomeProducts\" method = \"POST\">" +
                                        "<input type=\"hidden\" name = \"buyProd\" value=\"" + rs.getString("id") + "\">" +
                                        "<input class=\"tbl_button\"    type=\"submit\" value=\"Purchase\">" +
                                        "</form>" +
                        "</td> </tr>";
            tbl += "</table>";
            tbl += "<form action = \"addDBServlet\" method = \"GET\">" +
                    "<input type=\"hidden\" name=\"table\" value=\"products\">" +
                    "<input type=\"submit\" value=\"Add Product\">" +
                    "</form>";

        } catch (SQLException e) {
            e.printStackTrace();
            tbl = "Sql Query not executed, SQLException occurred";
        }
        String out = "<h2 style=\"text-align: center;\">All Products available in market</h2>";
        if(updmsg != null)    out += updmsg;
        if(addelmsg != null)    out += addelmsg;
        if(delmsg != null)    out += delmsg;
        out += tbl;

        req.setAttribute("output_title", "All Products");
        req.setAttribute("output_main", out);
        RequestDispatcher rd = req.getRequestDispatcher("/pageViewServlet");
        rd.forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}