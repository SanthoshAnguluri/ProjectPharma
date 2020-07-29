package com.salesNstocks;

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

@WebServlet(name = "viewStore", urlPatterns = "/viewStore", description = "Shows stocks of all branches")

public class viewStore extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("Entered viewStore");

        String out = "";
        String bran = req.getParameter("viewId");
        if (bran == null)
            bran = "All";

        HttpSession hs = req.getSession();
        dbConn db = (dbConn) hs.getAttribute("db");

        try {
            ResultSet branch_rs = db.getTable("pp_branches");
            out += "<p><label for=\"pp_branch\">To Branch: </label>"+
                    "<select name=\"pp_branch\" id=\"pp_branch\">"+
                        "<option value=\"All\">All</option>";
                        while (branch_rs.next()){
                            if (branch_rs.getString("id").equals(bran))
                                out += "<option selected value=\"" + branch_rs.getString("id") + "\">" + branch_rs.getString("name") + "</option>";
                            else
                                out += "<option value=\"" + branch_rs.getString("id") + "\">" + branch_rs.getString("name") + "</option>";
                        }
            out += "</select></p>";



        } catch (SQLException e) {
            out += "SQLException occurred! Try Again!";
            e.printStackTrace();
        }

        out += "<p>Under Development</p>";

        req.setAttribute("output_title", "Warehouse");
        req.setAttribute("output_main", out);
        RequestDispatcher rd = req.getRequestDispatcher("/pageViewServlet");
        rd.forward(req, res);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
    {
        doPost(req, res);
    }
    
}