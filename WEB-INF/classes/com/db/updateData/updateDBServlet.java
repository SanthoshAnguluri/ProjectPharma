package com.db.updateData;

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

@WebServlet(name = "updateDBServlet", urlPatterns = "/updateDBServlet", description = "Takes data to update any specified data")

public class updateDBServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("Entered updateDBServlet");
        String table = req.getParameter("table");
        String id = req.getParameter("id");

        String frm = "";
        String sql = "select * from " + table + " where id='" + id + "'";
        HttpSession hs = req.getSession();
        dbConn db = (dbConn) hs.getAttribute("db");
        ResultSet rs = null;
        try {
            rs = db.runQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        switch (table){
            case "pp_branches":
                try {
                    if (rs.next()) {
                        frm = "<p>* Id cannot be changed </p>"+
                        "<form action=\"IsUpdateASuccess\" method = \"POST\">"+
                                "<input type=\"hidden\" name=\"table\" value=\"pp_branches\">" +
                                "<input type=\"hidden\" name=\"id\" value=\"" + rs.getString("id") + "\">" +
                                "<table>"+
                                    "<tr><td>Branch_id</td>     <td>" + rs.getString("id") + "</td></tr>"+
                                    "<tr><td>Branch Name</td>   <td><input type=\"text\" name=\"name\" value=\"" + rs.getString("name") + "\"></td></tr>"+
                                    "<tr><td>Address</td>       <td><textarea name=\"address\" cols=\"30\" rows=\"10\">" + rs.getString("address") + "</textarea></td></tr>"+
                                    "<tr><td>Phone</td>         <td><input type=\"text\" name=\"phone\" value=\"" + rs.getLong("phone") + "\"></td></tr>"+
                                "</table>"+
                                "<input type=\"submit\" value=\"Update\">"+
                            "</form>";
                    } else {
                        frm = "Error Occurred! Try Again";
                        break;
                    }
                } catch (SQLException e) {
                    frm = "SQLException Occurred! Try Again";
                    e.printStackTrace();
                }
                break;
            
            case "products":
                try {
                    if (rs.next()) {
                        frm = "<p>* Id cannot be changed </p>"+
                        "<form action=\"IsUpdateASuccess\" method = \"POST\">"+
                                "<input type=\"hidden\" name=\"table\" value=\"products\">" +
                                "<input type=\"hidden\" name=\"id\" value=\"" + rs.getString("id") + "\">" +
                                "<table>"+
                                    "<tr><td>Product_id</td>     <td>" + rs.getString("id") + "</td></tr>"+
                                    "<tr><td>Product Name</td>   <td><input type=\"text\" name=\"name\" value=\"" + rs.getString("name") + "\"></td></tr>"+
                                    "<tr><td>Cost</td>           <td><input type=\"text\" name=\"cost\" value=\"" + rs.getFloat("cost") + "\"></td></tr>"+
                                    "<tr><td>Product Type</td>   <td><input type=\"text\" name=\"type\" value=\"" + rs.getString("type") + "\"></td></tr>"+
                                "</table>"+
                                "<input type=\"submit\" value=\"Update\">"+
                            "</form>";
                    } else {
                        frm = "Error Occurred! Try Again";
                        break;
                    }
                } catch (SQLException e) {
                    frm = "SQLException Occurred! Try Again";
                    e.printStackTrace();
                }
                break;
            
            case "suppliers":
                try {
                    if (rs.next()) {
                        frm = "<p>* Id cannot be changed </p>"+
                        "<form action=\"IsUpdateASuccess\" method = \"POST\">"+
                                "<input type=\"hidden\" name=\"table\" value=\"suppliers\">" +
                                "<input type=\"hidden\" name=\"id\" value=\"" + rs.getString("id") + "\">" +
                                "<table>"+
                                    "<tr><td>Supplier_id</td>     <td>" + rs.getString("id") + "</td></tr>"+
                                    "<tr><td>Supplier Name</td>   <td><input type=\"text\" name=\"name\" value=\"" + rs.getString("name") + "\"></td></tr>"+
                                    "<tr><td>Address</td>       <td><textarea name=\"address\" cols=\"30\" rows=\"10\">" + rs.getString("address") + "</textarea></td></tr>"+
                                    "<tr><td>Phone</td>         <td><input type=\"text\" name=\"phone\" value=\"" + rs.getLong("phone") + "\"></td></tr>"+
                                "</table>"+
                                "<input type=\"submit\" value=\"Update\">"+
                            "</form>";
                    } else {
                        frm = "Error Occurred! Try Again";
                        break;
                    }
                } catch (SQLException e) {
                    frm = "SQLException Occurred! Try Again";
                    e.printStackTrace();
                }
                break;

            case "customers":
                try {
                    if (rs.next()) {
                        frm = "<p>* Id cannot be changed </p>"+
                        "<form action=\"IsUpdateASuccess\" method = \"POST\">"+
                                "<input type=\"hidden\" name=\"table\" value=\"customers\">" +
                                "<input type=\"hidden\" name=\"id\" value=\"" + rs.getString("id") + "\">" +
                                "<table>"+
                                    "<tr><td>Customer_id</td>    <td>" + rs.getString("id") + "</td></tr>"+
                                    "<tr><td>Customer Name</td>  <td><input type=\"text\" name=\"name\" value=\"" + rs.getString("name") + "\"></td></tr>"+
                                    "<tr><td>Card Type</td>      <td><input type=\"text\" name=\"card\" value=\"" + rs.getString("card") + "\"></td></tr>"+
                                    "<tr><td>Address</td>        <td><textarea name=\"address\" cols=\"30\" rows=\"10\">" + rs.getString("address") + "</textarea></td></tr>"+
                                    "<tr><td>Phone</td>          <td><input type=\"text\" name=\"phone\" value=\"" + rs.getLong("phone") + "\"></td></tr>"+
                                "</table>"+
                                "<input type=\"submit\" value=\"Update\">"+
                            "</form>";
                    } else {
                        frm = "Error Occurred! Try Again";
                        break;
                    }
                } catch (SQLException e) {
                    frm = "SQLException Occurred! Try Again";
                    e.printStackTrace();
                }
                break;

            case "employee":
                try {
                    if (rs.next()) {
                        frm = "<p>* Id cannot be changed </p>"+
                        "<form action=\"IsUpdateASuccess\" method = \"POST\">"+
                                "<input type=\"hidden\" name=\"table\" value=\"employee\">" +
                                "<input type=\"hidden\" name=\"id\" value=\"" + rs.getString("id") + "\">" +
                                "<table>"+
                                    "<tr><td>Employee_id</td>       <td>" + rs.getString("id") + "</td></tr>"+
                                    "<tr><td>Employee Name</td>     <td><input type=\"text\" name=\"name\" value=\"" + rs.getString("name") + "\"></td></tr>"+
                                    "<tr><td>Branch_id</td>         <td><input type=\"text\" name=\"b_id\" value=\"" + rs.getString("b_id") + "\"></td></tr>"+
                                    "<tr><td>Address</td>           <td><textarea name=\"address\" cols=\"30\" rows=\"10\">" + rs.getString("address") + "</textarea></td></tr>"+
                                    "<tr><td>Phone</td>             <td><input type=\"text\" name=\"phone\" value=\"" + rs.getLong("phone") + "\"></td></tr>"+
                                    "<tr><td>Salary</td>            <td><input type=\"text\" name=\"salary\" value=\"" + rs.getInt("salary") + "\"></td></tr>"+
                                "</table>"+
                                "<input type=\"submit\" value=\"Update\">"+
                            "</form>";
                    } else {
                        frm = "Error Occurred! Try Again";
                        break;
                    }
                } catch (SQLException e) {
                    frm = "SQLException Occurred! Try Again";
                    e.printStackTrace();
                }
                break;

            case "discount":
                try {
                    if (rs.next()) {
                        frm = "<p>* Card_Name cannot be changed </p>"+
                        "<form action=\"IsUpdateASuccess\" method = \"POST\">"+
                                "<input type=\"hidden\" name=\"table\" value=\"discount\">" +
                                "<input type=\"hidden\" name=\"id\" value=\"" + rs.getString("id") + "\">" +
                                "<table>"+
                                    "<tr><td>Card_Name</td>     <td>" + rs.getString("id") + "</td></tr>"+
                                    "<tr><td>Discount (in %)</td>   <td><input type=\"text\" name=\"dis\" value=\"" + rs.getFloat("dis")*100 + "\"></td></tr>"+
                                "</table>"+
                                "<input type=\"submit\" value=\"Update\">"+
                            "</form>";
                    } else {
                        frm = "Error Occurred! Try Again";
                        break;
                    }
                } catch (SQLException e) {
                    frm = "SQLException Occurred! Try Again";
                    e.printStackTrace();
                }
                break;
        }

        req.setAttribute("output_title", "Update Page");
        req.setAttribute("output_main", frm);
        RequestDispatcher rd = req.getRequestDispatcher("/pageViewServlet");
        rd.forward(req, res);
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
    {
        doPost(req, res);
    }
}