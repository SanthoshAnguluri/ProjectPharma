package com.salesNstocks;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.dbConn;

@WebServlet(name = "saleSuccessful", urlPatterns = "/saleSuccessful", description = "Transaction between branches and customers")

public class saleSuccessful extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("Entered saleSuccessful");

        String out = "";

        HttpSession hs = req.getSession();
        dbConn db = (dbConn) hs.getAttribute("db");

        ArrayList<String> pNames = (ArrayList<String>) hs.getAttribute("pNames");
        ArrayList<String> bNames = (ArrayList<String>) hs.getAttribute("bNames");
        ArrayList<Integer> qValues = (ArrayList<Integer>) hs.getAttribute("qValues");
        Integer n = (Integer) hs.getAttribute("numberInSell");
        Float total = Float.parseFloat("0");
        String custId = (String) hs.getAttribute("custId");

        try {
            // bill no
            float bno = db.getRecord("sellTrans");
            String billId = "TS" + Float.toString(bno);

            //filling sellInfo1
            Date dt = new java.util.Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String cur_time = sdf.format(dt);
            db.setSellInfo1(billId, custId, cur_time);

            //filling buyinfo2
            float dis = (float)0;
            if (!custId.equals("Unregistered")){
                dis = db.getDiscount(custId);
            }
            for (int i = 0; i < n; i++){
                String a = pNames.get(i);
                String b = bNames.get(i);
                Integer c = qValues.get(i);
                Float d = qValues.get(i)*db.getCostOfProd(a);
                float e = d * (1-dis);
                total = total + e;
                db.setSellInfo2(billId, Integer.toString(i+1), b, a, c, e);
            }

            //updating balance
            db.setBalRecord(total, "sell");


            hs.removeAttribute("pNames");
            hs.removeAttribute("bNames");
            hs.removeAttribute("qValues");
            hs.removeAttribute("numberInSell");
            hs.removeAttribute("suppId");

            out += "Transaction Successful! Go back to home.";
            db.setRecord("sellTrans");
        } catch (SQLException e) {
            out += "SQLException Occurred!";
            e.printStackTrace();
        }

        req.setAttribute("output_title", "Is Transaction Successful");
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