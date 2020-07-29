package com.db.addData;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.dbConn;


@WebServlet(name = "addDBServlet", urlPatterns = "/addDBServlet", description = "Takes data to add new element")

public class addDBServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
    {
        System.out.println("Entered addDBServlet");
        String frm="", id="" , table = req.getParameter("table");
        HttpSession hs = req.getSession();
        dbConn db = (dbConn) hs.getAttribute("db");

        switch (table) {
        case "pp_branches":
            try {
                id = db.getid(table);
                frm = "<p>* Id is auto-generated </p>" 
                        + "<form action=\"isNewElementAdded\" method = \"POST\">"
                        + "<input type=\"hidden\" name=\"table\" value=\"pp_branches\">" 
                        + "<input type=\"hidden\" name=\"id\" value=\"" + id + "\">"
                        + "<table>"
                        + "<tr><td>Branch_id</td>     <td>" + id + "</td></tr>"
                        + "<tr><td>Branch Name</td>   <td><input type=\"text\" name=\"name\" ></td></tr>"
                        + "<tr><td>Address</td>       <td><textarea name=\"address\" cols=\"30\" rows=\"10\"></textarea></td></tr>"
                        + "<tr><td>Phone</td>         <td><input type=\"text\" name=\"phone\" ></td></tr>"
                        + "</table>" 
                        + "<input type=\"submit\" value=\"Add Data\">" 
                        + "</form>";
            } catch (SQLException e) {
                frm = "SQLException Occurred! Try Again";
                e.printStackTrace();
            }
            break;
        case "products":
            try {
                id = db.getid(table);
                frm = "<p>* Id is auto-generated </p>" 
                        + "<form action=\"isNewElementAdded\" method = \"POST\">"
                        + "<input type=\"hidden\" name=\"table\" value=\"products\">" 
                        + "<input type=\"hidden\" name=\"id\" value=\"" + id + "\">"
                        + "<table>"
                        + "<tr><td>Product_id</td>     <td>" + id + "</td></tr>"
                        + "<tr><td>Product Name</td>   <td><input type=\"text\" name=\"name\" ></td></tr>"
                        + "<tr><td>Cost</td>           <td><input type=\"text\" name=\"cost\" ></td></tr>"
                        + "<tr><td>Product Type</td>   <td><input type=\"text\" name=\"type\" ></td></tr>"
                        + "</table>" 
                        + "<input type=\"submit\" value=\"Add Data\">" 
                        + "</form>";
            } catch (SQLException e) {
                frm = "SQLException Occurred! Try Again";
                e.printStackTrace();
            }
            break;
        case "suppliers":
            try {
                id = db.getid(table);
                frm = "<p>* Id is auto-generated </p>" +
                        "<form action=\"isNewElementAdded\" method = \"POST\">"+
                        "<input type=\"hidden\" name=\"table\" value=\"suppliers\">" +
                        "<input type=\"hidden\" name=\"id\" value=\"" + id + "\">" +
                        "<table>"+
                            "<tr><td>Supplier_id</td>     <td>" + id + "</td></tr>"+
                            "<tr><td>Supplier Name</td>   <td><input type=\"text\" name=\"name\" ></td></tr>"+
                            "<tr><td>Address</td>         <td><textarea name=\"address\" cols=\"30\" rows=\"10\"></textarea></td></tr>"+
                            "<tr><td>Phone</td>           <td><input type=\"text\" name=\"phone\" ></td></tr>"+
                        "</table>"+
                        "<input type=\"submit\" value=\"Add Data\">"+
                        "</form>";
            } catch (SQLException e) {
                frm = "SQLException Occurred! Try Again";
                e.printStackTrace();
            }
            break;
        case "customers":
            try {
                id = db.getid(table);
                frm = "<p>* Id is auto-generated </p>" +
                    "<form action=\"isNewElementAdded\" method = \"POST\">"+
                    "<input type=\"hidden\" name=\"table\" value=\"customers\">" +
                    "<input type=\"hidden\" name=\"id\" value=\"" + id + "\">" +
                    "<table>"+
                        "<tr><td>Customer_id</td>    <td>" + id + "</td></tr>"+
                        "<tr><td>Customer Name</td>  <td><input type=\"text\" name=\"name\" ></td></tr>"+
                        "<tr><td>Card Type</td>      <td><input type=\"text\" name=\"card\" ></td></tr>"+
                        "<tr><td>Address</td>        <td><textarea name=\"address\" cols=\"30\" rows=\"10\"></textarea></td></tr>"+
                        "<tr><td>Phone</td>          <td><input type=\"text\" name=\"phone\" ></td></tr>"+
                    "</table>"+
                    "<input type=\"submit\" value=\"Add Data\">"+
                    "</form>";
            } catch (SQLException e) {
                frm = "SQLException Occurred! Try Again";
                e.printStackTrace();
            }
            break;
        case "employee":
            try {
                id = db.getid(table);
                frm = "<p>* Id is auto-generated </p>" +
                    "<form action=\"isNewElementAdded\" method = \"POST\">"+
                    "<input type=\"hidden\" name=\"table\" value=\"employee\">" +
                    "<input type=\"hidden\" name=\"id\" value=\"" + id + "\">" +
                        "<table>"+
                        "<tr><td>Employee_id</td>       <td>" + id + "</td></tr>"+
                        "<tr><td>Employee Name</td>     <td><input type=\"text\" name=\"name\" ></td></tr>"+
                        "<tr><td>Branch_id</td>         <td><input type=\"text\" name=\"b_id\" ></td></tr>"+
                        "<tr><td>Address</td>           <td><textarea name=\"address\" cols=\"30\" rows=\"10\"></textarea></td></tr>"+
                        "<tr><td>Phone</td>             <td><input type=\"text\" name=\"phone\" ></td></tr>"+
                        "<tr><td>Salary</td>            <td><input type=\"text\" name=\"salary\" ></td></tr>"+
                    "</table>"+
                    "<input type=\"submit\" value=\"Add Data\">"+
                    "</form>";
            } catch (SQLException e) {
                frm = "SQLException Occurred! Try Again";
                e.printStackTrace();
            }
            break;
        case "discount":
            frm = "<p>* Make sure the new name doesn't exist before </p>" +
                "<form action=\"isNewElementAdded\" method = \"POST\">"+
                "<input type=\"hidden\" name=\"table\" value=\"discount\">" +
                "<table>"+
                    "<tr><td>Card_Name</td>         <td><input type=\"text\" name=\"id\" ></td></tr>"+
                    "<tr><td>Discount (in %)</td>   <td><input type=\"text\" name=\"dis\" ></td></tr>"+
                "</table>"+
                "<input type=\"submit\" value=\"Add Data\">"+
                "</form>";
            break;
        }
        
        req.setAttribute("output_title", "Add Page");
        req.setAttribute("output_main", frm);
        RequestDispatcher rd = req.getRequestDispatcher("/pageViewServlet");
        rd.forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
    {
        doGet(req, res);
    }
}