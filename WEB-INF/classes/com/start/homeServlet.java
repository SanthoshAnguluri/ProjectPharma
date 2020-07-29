package com.start;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class homeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        System.out.println("Entered homeServlet");
    
        
        String out = "<p><b>ProjectPharma</b> is a web-application that is used to assist the handling of data(employees, suppliers, etc) and transactions of pharmaceutical stores under ProjectPharma. </p>";
        out += "<p><b>ProjectPharma</b> consists of a web-interface and a back-end mysql database. It is created using java servlets, JDBC and some front-end.</p>";
        out += "<p>The database starts with an account balance of <b>Rs.15000.</b></p>";
        
        out += "<form action=\"\" style=\"width: 25%; text-align: center;\">"+
                "<input class=\"inp\"  type=\"submit\" value=\"Sell a Product\" name=\"logout\"   formaction=\"sellSomeProducts\">"+
                "</form>";

        req.setAttribute("output_title", "Welcome to ProjectPharma");
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