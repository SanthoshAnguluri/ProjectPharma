package com.db.addData;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.dbConn;

@WebServlet(name = "isNewElementAdded", urlPatterns = "/isNewElementAdded", description = "Adds the new element")

public class isNewElementAdded extends HttpServlet{

    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
    {
        System.out.println("Entered isNewElementAdded");
        String table = req.getParameter("table");
        dbConn db = (dbConn) req.getSession().getAttribute("db");
        int i = 0;
        RequestDispatcher rd = null;

        switch(table){
            case "pp_branches":
                i = db.addBranch(req.getParameter("id"), req.getParameter("name"), req.getParameter("address"), req.getParameter("phone"));
                break;

            case "products":
                i = db.addProduct(req.getParameter("id"), req.getParameter("name"), req.getParameter("cost"), req.getParameter("type"));
                break;
            
            case "suppliers":
                i = db.addSupplier(req.getParameter("id"), req.getParameter("name"), req.getParameter("address"), req.getParameter("phone"));
                break;

            case "customers":
                i = db.addCustomer(req.getParameter("id"), req.getParameter("name"), req.getParameter("card"), req.getParameter("address"), req.getParameter("phone"));
                break;

            case "employee":
                i = db.addEmployee(req.getParameter("id"), req.getParameter("name"), req.getParameter("b_id"), req.getParameter("address"), req.getParameter("phone"), req.getParameter("salary"));
                break;
            
            case "discount":
                i = db.addMemberType(req.getParameter("id"), req.getParameter("dis"));
                break;
        }

        if (i==-1){
            req.setAttribute("addelmsg", "<h4>Addition Unsuccessful! Try Again</h4><br>");
        }else{
            req.setAttribute("addelmsg", "<h4>"+ i +" row(s) Added Successfully!</h4><br>");
        }

        switch(table){
            case "pp_branches": rd = getServletContext().getRequestDispatcher("/allBranServlet");   break;
            case "products":    rd = getServletContext().getRequestDispatcher("/allProdServlet");   break;
            case "suppliers":   rd = getServletContext().getRequestDispatcher("/allSuppServlet");   break;
            case "customers":   rd = getServletContext().getRequestDispatcher("/allCustServlet");   break;
            case "employee":    rd = getServletContext().getRequestDispatcher("/allEmpServlet");    break;
            case "discount":    rd = getServletContext().getRequestDispatcher("/memServlet");       break;
        }

        rd.forward(req, res);
    }
    
}