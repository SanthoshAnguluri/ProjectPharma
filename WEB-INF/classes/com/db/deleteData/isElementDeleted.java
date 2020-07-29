package com.db.deleteData;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.dbConn;

@WebServlet(name = "isElementDeleted", urlPatterns = "/isElementDeleted", description = "Deletes elements in DB")

public class isElementDeleted extends HttpServlet{

    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
    {
        System.out.println("Entered isElementDeleted");
        String table = req.getParameter("table");
        String id = req.getParameter("id");
        dbConn db = (dbConn)req.getSession().getAttribute("db");
        RequestDispatcher rd = null;
        
        int i = db.del(table, id);
        if (i==-1){
            req.setAttribute("delmsg", "<h4>Deletion Unsuccessful! Check if there are no related transactions</h4><br>");
        }else{
            req.setAttribute("delmsg", "<h4>"+ i +" row(s) Deleted Successfully!</h4><br>");
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