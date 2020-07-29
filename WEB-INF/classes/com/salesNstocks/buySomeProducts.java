package com.salesNstocks;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.dbConn;

@WebServlet(name = "buySomeProducts", urlPatterns = "/buySomeProducts", description = "Transaction between suppliers and branches")

public class buySomeProducts extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("Entered buySomeProducts");

        String out = "";

        HttpSession hs = req.getSession();
        dbConn db = (dbConn) hs.getAttribute("db");

        
        String str = (String)req.getParameter("addToCart");
        try {

            ResultSet branch_rs = db.getTable("pp_branches");
            ResultSet prod_rs = db.getTable("products");
            ResultSet supp_rs = db.getTable("suppliers");

            if (str == null)
            {
                out += this.transStart(prod_rs, branch_rs, supp_rs);
                hs.removeAttribute("pNames");
                hs.removeAttribute("bNames");
                hs.removeAttribute("qValues");
                hs.removeAttribute("numberInBuy");
                hs.removeAttribute("suppId");
            }else
            {
                String suppId = (String)hs.getAttribute("suppId");
                
                if (suppId == null){
                    suppId = req.getParameter("supplier");
                    hs.setAttribute("suppId", suppId);
                }

                ArrayList<String> pNames = (ArrayList<String>)hs.getAttribute("pNames");
                ArrayList<String> bNames = (ArrayList<String>)hs.getAttribute("bNames");
                ArrayList<Integer> qValues = (ArrayList<Integer>)hs.getAttribute("qValues");
                Integer n = (Integer)hs.getAttribute("numberInBuy");
                Float total = Float.parseFloat("0");

                if (n == null){
                    pNames = new ArrayList<String>();
                    bNames = new ArrayList<String>();
                    qValues = new ArrayList<Integer>();
                    n = 0;
                }

                if (str.equals("del"))
                {
                    int idx = Integer.parseInt(req.getParameter("idxToDel"));
                    pNames.remove(idx);
                    bNames.remove(idx);
                    qValues.remove(idx);
                    n--;
                }else
                {
                    String p1 = (String)req.getParameter("product");
                    String p2 = (String)req.getParameter("pp_branch");
                    Integer p3 = Integer.parseInt(req.getParameter("quantity"));

                    if (p1.equals("None") || p2.equals("None") || p3 <= 0 || suppId.equals("None"))
                    {
                        out += "Invalid Input!  Check if no input is set to None and quantity > 0";
                    }else
                    {
                        pNames.add(p1);
                        bNames.add(p2);
                        qValues.add(p3);
                        n++;
                    }
                }

                hs.removeAttribute("pNames");       hs.setAttribute("pNames", pNames);
                hs.removeAttribute("bNames");       hs.setAttribute("bNames", bNames);
                hs.removeAttribute("qValues");      hs.setAttribute("qValues", qValues);
                hs.removeAttribute("numberInBuy");  hs.setAttribute("numberInBuy", n);  
                
                //supplier details
                Float dis = (float) 0.6;
                if (suppId.equals("None")){
                    out += "<p>Please select a Supplier</p>";
                    out += this.transStart(prod_rs, branch_rs, supp_rs);
                }else{
                    
                    out += "<h2 style=\"text-align: center;\">Bill Details</h2>";
                    
                    ResultSet suppDetails = db.getAll("suppliers", suppId);
                    out += "<table><tr><td><b>Supplier ID: </b></td><td>" + suppDetails.getString("id") +
                            "</td></tr><tr><td><b>Supplier Name: </b></td><td>" + suppDetails.getString("name") +
                            "</td></tr><tr><td><b>Address: </b></td><td>" + suppDetails.getString("address") +
                            "</td></tr><tr><td><b>Phone: </b></td><td>" + suppDetails.getString("Phone") +
                            "</td></tr></table><br>";
                
                    out += "<table style=\"width:100%;\">"+
                            "<tr>  <th>S.No.</th> <th>Product </th> <th>Branch</th> <th>M.R.P</th> <th>Quantity</th> <th>Cost (60% off)</th>   </tr>";
                            for (Integer i = 0; i < n; ++i)
                            {
                                String a = pNames.get(i);
                                String b = bNames.get(i);
                                Integer c = qValues.get(i);
                                Float e = qValues.get(i)*db.getCostOfProd(a);
                                Float f = e*(1-dis);    total = total + f;

                                out += "<tr><td style=\"text-align: center;\">" + String.valueOf(i+1) +"</td><td> " + db.getName("products",a) + "</td><td>" + db.getName("pp_branches",b);
                                out += "</td><td style=\"text-align: center;\">" + db.getCostOfProd(a) + "</td><td style=\"text-align: center;\">" + c + "</td><td style=\"text-align: center;\">" + f;
                                
                                out += "</td><td style=\"text-align: center;\"><form action = \"buySomeProducts\" method = \"POST\">"+
                                        "<input type=\"hidden\" name=\"addToCart\" value=\"del\">"+
                                        "<input type=\"hidden\" name=\"idxToDel\" value=\"" + i + "\">"+
                                        "<input type=\"submit\" value=\"Remove\">"+
                                        "</form></td></tr>";
                            }
                    out +=  "<tr><td colspan=\"5\"></td><td style=\"text-align: center;\">Total: " + total + "</td></tr>" +
                            "</table><br>";

                    out += "<form action = \"buySomeProducts\" method = \"POST\"><table style=\"width:100%;\"><tr>"+
                            "<input type=\"hidden\" name=\"addToCart\" value=\"yes\">"+
                            "<td><label for=\"product\">Choose the Product: </label>"+
                            "<select name=\"product\" id=\"product\">"+
                                "<option value=\"None\">None</option>";
                                while (prod_rs.next()){
                                    out += "<option value=\"" + prod_rs.getString("id") + "\">" + prod_rs.getString("name") + "</option>";
                                }
                    out += "</select></td>"+
                            "<td><label for=\"pp_branch\">To Branch: </label>"+
                            "<select name=\"pp_branch\" id=\"pp_branch\">"+
                                "<option value=\"None\">None</option>";
                                while (branch_rs.next()){
                                    out += "<option value=\"" + branch_rs.getString("id") + "\">" + branch_rs.getString("name") + "</option>";
                                }
                    out += "</select></td>"+
                            "<td><label for=\"quantity\">Quantity: </label>"+
                            "<input type=\"number\" name=\"quantity\" value=\"0\" style=\"width: 25%;\"></td>"+

                            "<td><input type=\"submit\" value=\"Add to cart\"></td>"+
                            "</tr></table></form><br>";
                            
                    out += "<table style=\"text-align: center; width: 35%; margin-left: auto; margin-right: auto;\">"+
                            "<tr><td><form action=\"buySuccessful\"><input type=\"submit\" value=\"Confirm\" name=\"Confirm\" onclick=\"return confirm(\'Finalise the Transaction ?\')\"></form>"+
                            "</td><td><form action=\"homeServlet\"><input type=\"submit\" value=\"Cancel\" name=\"Cancel\" onclick=\"return confirm(\'Cancel the Transaction ?\')\"></form></td></tr></table>";
                }
            }
        } catch (SQLException e) {
            out += "SQLException occurred! Try Again!";
            e.printStackTrace();
        }

        req.setAttribute("output_title", "Product Sale Portal");
        req.setAttribute("output_main", out);
        RequestDispatcher rd = req.getRequestDispatcher("/pageViewServlet");
        rd.forward(req, res);
            
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
    {
        doPost(req, res);
    }

    String transStart(ResultSet prod_rs, ResultSet branch_rs, ResultSet supp_rs) throws SQLException 
    {
        String out = "";
        out += "<form action = \"buySomeProducts\" method = \"POST\"><table style=\"width:100%;\"><tr>"+
                        "<input type=\"hidden\" name=\"addToCart\" value=\"yes\">"+
                        "<td><label for=\"product\">Choose the Product: </label>"+
                        "<select name=\"product\" id=\"product\">"+
                            "<option value=\"None\">None</option>";
                            while (prod_rs.next()){
                                out += "<option value=\"" + prod_rs.getString("id") + "\">" + prod_rs.getString("name") + "</option>";
                            }
                out += "</select></td>"+
                        "<td><label for=\"pp_branch\">To Branch: </label>"+
                        "<select name=\"pp_branch\" id=\"pp_branch\">"+
                            "<option value=\"None\">None</option>";
                            while (branch_rs.next()){
                                out += "<option value=\"" + branch_rs.getString("id") + "\">" + branch_rs.getString("name") + "</option>";
                            }
                out += "</select></td>"+
                        "<td><label for=\"supplier\">From Supplier: </label>"+
                        "<select name=\"supplier\" id=\"supplier\">"+
                            "<option value=\"None\">None</option>";
                            while (supp_rs.next()){
                                out += "<option value=\"" + supp_rs.getString("id") + "\">" + supp_rs.getString("name") + "</option>";
                            }
                out += "</select></td>"+
                        "<td><label for=\"quantity\">Quantity: </label>"+
                        "<input type=\"number\" name=\"quantity\" value=\"0\" style=\"width: 25%;\"></td>"+

                        "<td><input type=\"submit\" value=\"Add to cart\"></td>"+
                        "</tr></table></form><br>";
        return out;
    }
}