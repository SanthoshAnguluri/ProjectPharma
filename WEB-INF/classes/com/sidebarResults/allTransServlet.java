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

@WebServlet(name = "allTransServlet", urlPatterns = "/allTransServlet", description = "Shows all transactions")

public class allTransServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("Entered allTransServlet");

        String out = "";

        HttpSession hs = req.getSession();
        dbConn db = (dbConn) hs.getAttribute("db");

        try {
            ResultSet rs = db.getTrans("All buy");
            out += "<h2 style=\"text-align: center;\">All Purchases from Suppliers</h2>"+
                    "<table class=\"tbl\">"+
                    "<tr class=\"tbl_head\"> <th>Bill no.</th> <th>Supplier</th> <th>S.no</th> <th>Branch</th> <th>Product</th> <th>Quantity</th> <th>Cost</th> <th>Date and Time</th> </tr>";
                while(rs.next())
                {
                    String bill = rs.getString("bill_id");
                    String s_id = rs.getString("s_id");
                    String s_no = Integer.toString(rs.getInt("s_no"));
                    String b_id = rs.getString("b_id");
                    String p_id = rs.getString("p_id");
                    String quantity = Integer.toString(rs.getInt("quantity"));
                    String cost = Float.toString(rs.getFloat("cost"));
                    String d_t = rs.getString("date_time");
                    out += "<tr> <td>" + bill +
                            "</td> <td>" + db.getName("suppliers", s_id) +
                            "</td> <td>" + s_no +
                            "</td> <td>" + db.getName("pp_branches", b_id) +
                            "</td> <td>" + db.getName("products", p_id) +
                            "</td> <td>" + quantity +
                            "</td> <td>" + cost +
                            "</td> <td>" + d_t + "</td> </tr>";
                }
            out += "</table>";
            out += "<br><br>";

            rs = db.getTrans("All sell");
            out += "<h2 style=\"text-align: center;\">All Customer Transactions</h2>"+
                    "<table class=\"tbl\">"+
                    "<tr class=\"tbl_head\"> <th>Bill no.</th> <th>Customer</th> <th>S.no</th> <th>Branch</th> <th>Product</th> <th>Quantity</th> <th>Cost</th> <th>Date and Time</th> </tr>";
                while(rs.next())
                {
                    String bill = rs.getString("bill_id");
                    String c_id = rs.getString("c_id");
                    if (c_id == null)
                        c_id = "Unregistered";
                    else
                        c_id = db.getName("customers", c_id);
                    String s_no = Integer.toString(rs.getInt("s_no"));
                    String b_id = rs.getString("b_id");
                    String p_id = rs.getString("p_id");
                    String quantity = Integer.toString(rs.getInt("quantity"));
                    String cost = Float.toString(rs.getFloat("cost"));
                    String d_t = rs.getString("date_time");
                    out += "<tr> <td>" + bill +
                            "</td> <td>" + c_id +
                            "</td> <td>" + s_no +
                            "</td> <td>" + db.getName("pp_branches", b_id) +
                            "</td> <td>" + db.getName("products", p_id) +
                            "</td> <td>" + quantity +
                            "</td> <td>" + cost +
                            "</td> <td>" + d_t + "</td> </tr>";
                }
            out += "</table>";
        } catch (SQLException e) {
            out += "SQLException Occurred!";
            e.printStackTrace();
        }

        req.setAttribute("output_title", "All Transactions");
        req.setAttribute("output_main", out);
        RequestDispatcher rd = req.getRequestDispatcher("/pageViewServlet");
        rd.forward(req, res);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        doPost(req, res);
    }
    
}