package com.start;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.dbConn;

public class errLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("Entered errLoginServlet");
        PrintWriter w = res.getWriter();
        String msg = (String) req.getAttribute("message");
        if (msg == null) {
            msg = "Logged out Succesfully";
            HttpSession hs = req.getSession();
            dbConn db = (dbConn) hs.getAttribute("db");
            try {
                db.closeConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            hs.removeAttribute("db");
            hs.removeAttribute("uname");
        }
        String s = "<!DOCTYPE html>"+
                        "<html lang=\"en\">"+
                        "<head></head>"+
                        "<body>"+
                        "<table style=\"margin-left: auto; margin-right: auto;\">"+
                            "<tr><td><p>" + msg + "</p></td></tr>"+
                            "<tr><td>"+
                                "<form action=\"loginServlet\" name=\"logonform\" style=\"text-align: center;\" method=\"POST\">"+
                                    "Username: <input type=\"text\" name=\"username\"><br>"+
                                    "Password: <input type=\"password\" name=\"password\"><br>"+
                                    "<center><input type=\"submit\" value=\"Submit\"></center>"+
                                "</form>"+
                            "</td></tr>"+
                        "</table>"+
                        "</body></html>";
        w.println(s);
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }
}