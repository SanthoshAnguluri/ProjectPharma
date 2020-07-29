package com.basePage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.dbConn;

@WebServlet(name = "pageViewServlet", urlPatterns = "/pageViewServlet", description = "Basic Page Format")

public class pageViewServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = (String) req.getSession().getAttribute("uname");
        name = name.substring(0, 1).toUpperCase() + name.substring(1);

        String out, op_main, op_title;
        op_title = (String) req.getAttribute("output_title");
        op_main = (String) req.getAttribute("output_main");

        HttpSession hs = req.getSession();
        dbConn db = (dbConn) hs.getAttribute("db");

        PrintWriter w = res.getWriter();
        out = "<!DOCTYPE html>" + "<html lang=\"en\">" + "<head>" + "<title>" + op_title + "</title>"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + req.getContextPath() + "/style.css\">"
                + "</head>" + "<body>";

        try {
            out += "<strong style=\"float: right;\">Total Balance: " + db.getRecord("balance") + "</strong>";
        } catch (SQLException e) {
            out += "<strong style=\"float: right; color: red;\">Please Create the Database First</strong>";
            e.printStackTrace();
        }
    
    out +=  "<div class=\"sidebar\">"+
                "<p><b style=\"font-size: larger;\">Hi " + name + "!</b>"+
                "<br>Choose an option</p>"+
                "<form action=\"\" style=\"text-align: center;\">"+
                    "<input class=\"inp\"  type=\"submit\" value=\"Home\"               name=\"home\"     formaction=\"homeServlet\"><br>"+
                    "<input class=\"inp\"  type=\"submit\" value=\"Branches\"           name=\"bran\"     formaction=\"allBranServlet\"><br>"+
                    "<input class=\"inp\"  type=\"submit\" value=\"Products\"           name=\"prod\"     formaction=\"allProdServlet\"><br>"+
                    "<input class=\"inp\"  type=\"submit\" value=\"Suppliers\"          name=\"supp\"     formaction=\"allSuppServlet\"><br>"+
                    "<input class=\"inp\"  type=\"submit\" value=\"Customers\"          name=\"cust\"     formaction=\"allCustServlet\"><br>"+
                    "<input class=\"inp\"  type=\"submit\" value=\"Employees\"          name=\"emp\"      formaction=\"allEmpServlet\"><br>"+
                    "<input class=\"inp\"  type=\"submit\" value=\"Membership Types\"   name=\"mem\"      formaction=\"memServlet\"><br>"+
                    "<input class=\"inp\"  type=\"submit\" value=\"All Transactions\"   name=\"mem\"      formaction=\"allTransServlet\"><br>"+
                    "<input class=\"inp\"  type=\"submit\" value=\"Logout\"             name=\"logout\"   formaction=\"errLoginServlet\"><br>"+
                "</form>"+
            "</div>"+
            "<div id=\"header\">" + 
                "<h1>ProjectPharma</h1>" +
            "</div>"+
            "<div id=\"main\">"+
                op_main +
            "</div>"+
            "</body>"+
            "</html>";
        w.println(out);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
    {
        doPost(req, res);
    }
}