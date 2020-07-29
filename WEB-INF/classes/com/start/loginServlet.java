package com.start;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.dbConn;

public class loginServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    
    static final String JDBC_driver = "com.mysql.cj.jdbc.Driver";
    static String DB_URL = "jdbc:mysql://localhost/pharmacy";

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        System.out.println("Entered loginServlet");
        String uname = req.getParameter("username");
        String pass = req.getParameter("password");
        System.out.println("creating db object");
        dbConn db = new dbConn(JDBC_driver, DB_URL, uname, pass);
        RequestDispatcher rd=null;
        if(db.b){
            rd = req.getRequestDispatcher("/homeServlet");
            HttpSession hs = req.getSession();
            hs.setAttribute("db", db);
            hs.setAttribute("uname", uname);
        }else{
            rd = req.getRequestDispatcher("/errLoginServlet");
            req.setAttribute("message", "Incorrect Username or Password");
        }
        rd.forward(req, res);
    }
}