package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class dbConn {
    private Connection conn = null;
    public boolean b = true;
    private PreparedStatement prodstmt, branstmt, suppstmt, custstmt, empstmt, disstmt;

    public dbConn(String JDBC_driver, String DB_URL, String uname, String pass)
    {
        try {
            Class.forName(JDBC_driver);
            conn = DriverManager.getConnection(DB_URL, uname, pass);
        } catch (SQLException e) {
            b = false;
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            b = false;
            e.printStackTrace();
        }
    }

    public ResultSet getAll(String table, String a)throws SQLException
    {
        String sql="";
        switch(table){
            case "customers":   sql = "SELECT * FROM customers WHERE id=\'" + a + "\'"; break;
            case "suppliers":   sql = "SELECT * FROM suppliers WHERE id=\'" + a + "\'"; break;
        }
        ResultSet rs = this.runQuery(sql);  rs.next();
        return rs;
    }
    
    public String getid(String table) throws SQLException {
        String sql, tb="";
        int n;
        ResultSet rs;
        sql = "SELECT * FROM " + table + " ORDER BY id DESC LIMIT 1";
        rs = this.runQuery(sql);
        switch(table){
            case "pp_branches": tb = "B";   break;
            case "products":    tb = "P";   break;
            case "suppliers":   tb = "S";   break;
            case "customers":   tb = "C";   break;
            case "employee":    tb = "E";   break;
        }
        if(rs.next()){
            n = Integer.parseInt( rs.getString("id").substring(1) );  n++;
            return tb + Integer.toString(n);
        }else{
            return tb + "1";
        }
    }

	public String getName(String table, String a) throws SQLException {
        String sql="";
        switch (table){
            case "pp_branches": sql = "SELECT name FROM pp_branches WHERE id=\'" + a + "\'";    break;
            case "products":    sql = "SELECT name FROM products WHERE id=\'" + a + "\'";       break;
            case "suppliers":   sql = "SELECT name FROM suppliers WHERE id=\'" + a + "\'";      break;
            case "customers":   sql = "SELECT name FROM customers WHERE id=\'" + a + "\'";      break;
        }
        ResultSet rs = this.runQuery(sql);  rs.next();
		return rs.getString("name");
	}

    public ResultSet getTable(String table) throws SQLException {
        String sql="";
        switch(table){
            case "pp_branches": sql = "SELECT * FROM pp_branches";  break;
            case "products":    sql = "SELECT * FROM products";     break;
            case "suppliers":   sql = "SELECT * FROM suppliers";    break;
            case "customers":   sql = "SELECT * FROM customers";    break;
            case "employee":    sql = "E";   break;
        }
		return this.runQuery(sql);
    }
    
	public Float getDiscount(String custId) throws SQLException {
        String sql = "SELECT * FROM discount AS ds WHERE ds.id = (SELECT card FROM customers AS cus WHERE cus.id='" + custId + "')";
        ResultSet rs = this.runQuery(sql);
        rs.next();
        return rs.getFloat("dis");
	}
    public int del(String table, String id){
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM "+ table +" WHERE id=?");
            pstmt.setString(1, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    //Branch Operations
    public int branUpdate(String id, String name, String address, String phone){
        try {
            branstmt = conn.prepareStatement("UPDATE pp_branches SET name = ?, address = ?, phone = ? WHERE id = ?");
            branstmt.setString(1, name);
            branstmt.setString(2, address);
            branstmt.setLong(3, Long.parseLong(phone));
            branstmt.setString(4, id);
            return branstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    
    public int addBranch(String id, String name, String address, String phone){
        try {
            branstmt = conn.prepareStatement("INSERT INTO pp_branches VALUES(?, ?, ?, ?)");
            branstmt.setString(1, id);
            branstmt.setString(2, name);
            branstmt.setString(3, address);
            branstmt.setLong(4, Long.parseLong(phone));
            return branstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }


    //Product Operations
    public int prodUpdate(String id, String name, String cost, String type){
        try {
            prodstmt = conn.prepareStatement("UPDATE products SET name = ?, cost = ?, type = ? WHERE id = ?");
            prodstmt.setString(1, name);
            prodstmt.setFloat(2, Float.parseFloat(cost));
            prodstmt.setString(3, type);
            prodstmt.setString(4, id);
            return prodstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    
    public int addProduct(String id, String name, String cost, String type){
        try {
            prodstmt = conn.prepareStatement("INSERT INTO products VALUES(?, ?, ?, ?)");
            prodstmt.setString(1, id);
            prodstmt.setString(2, name);
            prodstmt.setFloat(4, Float.parseFloat(cost));
            prodstmt.setString(3, type);
            return prodstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    
    public Float getCostOfProd(String str) throws SQLException {
        String sql = "SELECT cost FROM products WHERE id=\'" + str + "\'";
        ResultSet rs = this.runQuery(sql);
        rs.next();
		return rs.getFloat("cost");
	}


    //Supplier Operations
    public int suppUpdate(String id, String name, String address, String phone){
        try {
            suppstmt = conn.prepareStatement("UPDATE suppliers SET name = ?, address = ?, phone = ? WHERE id = ?");
            suppstmt.setString(1, name);
            suppstmt.setString(2, address);
            suppstmt.setLong(3, Long.parseLong(phone));
            suppstmt.setString(4, id);
            return suppstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    
    public int addSupplier(String id, String name, String address, String phone){
        try {
            suppstmt = conn.prepareStatement("INSERT INTO suppliers VALUES(?, ?, ?, ?)");
            suppstmt.setString(2, name);
            suppstmt.setString(3, address);
            suppstmt.setLong(4, Long.parseLong(phone));
            suppstmt.setString(1, id);
            return suppstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }


    //Customer Operations
    public int custUpdate(String id, String name, String card, String address, String phone){
        try {
            custstmt = conn.prepareStatement("UPDATE customers SET name = ?, address = ?, phone = ?, card = ? WHERE id = ?");
            custstmt.setString(1, name);
            custstmt.setString(2, address);
            custstmt.setLong(3, Long.parseLong(phone));
            custstmt.setString(4, card);
            custstmt.setString(5, id);
            return custstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    
    public int addCustomer(String id, String name, String card, String address, String phone){
        try {
            custstmt = conn.prepareStatement("INSERT INTO customers VALUES(?, ?, ?, ?, ?)");
            custstmt.setString(1, id);
            custstmt.setString(2, name);
            custstmt.setString(3, card);
            custstmt.setString(5, address);
            custstmt.setLong(4, Long.parseLong(phone));
            return custstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }


    //Employee Operations
    public int empUpdate(String id, String name, String bid, String address, String phone, String salary){
        try {
            empstmt = conn.prepareStatement("UPDATE employee SET name = ?, b_id = ?, address = ?, phone = ?, salary = ? WHERE id = ?");
            empstmt.setString(1, name);
            empstmt.setString(2, bid);
            empstmt.setString(3, address);
            empstmt.setLong(4, Long.parseLong(phone));
            empstmt.setLong(5, Long.parseLong(salary));
            empstmt.setString(6, id);
            return empstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    
    public int addEmployee(String id, String name, String bid, String address, String phone, String salary){
        try {
            empstmt = conn.prepareStatement("INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?)");
            empstmt.setString(1, id);
            empstmt.setString(2, name);
            empstmt.setString(3, bid);
            empstmt.setString(4, address);
            empstmt.setLong(5, Long.parseLong(phone));
            empstmt.setLong(6, Long.parseLong(salary));
            return empstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }


    //Discount Operations
    public int disUpdate(String card_type, String dis){
        try {
            disstmt = conn.prepareStatement("UPDATE discount SET dis = ? WHERE id = ?");
            disstmt.setString(2, card_type);
            disstmt.setFloat(1, Float.parseFloat(dis)/100);
            return disstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }    

    public int addMemberType(String card_type, String dis){
        try {
            disstmt = conn.prepareStatement("INSERT INTO discount VALUES(?, ?)");
            disstmt.setString(1, card_type);
            disstmt.setFloat(2, Float.parseFloat(dis)/100);
            return disstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public ResultSet runQuery(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql);
    }
    
    public void closeConn() throws SQLException
    {
        if(conn!=null){
            conn.close();
            System.out.println("db object is deleting");
        }
    }


    //records
    public float getRecord(String str) throws SQLException 
    {
        ResultSet rs = this.runQuery("SELECT * FROM records");  rs.next();
        float k=0;
        switch(str){
            case "buyTrans":    k = rs.getInt("buyTrsId");  break;
            case "sellTrans":   k = rs.getInt("sellTrsId"); break;
            case "balance":     k = rs.getFloat("balance"); break;
        }
		return k;
    }

	public void setRecord(String string) throws SQLException {
        ResultSet rs = this.runQuery("SELECT * FROM records");  rs.next();
        int k=0;
        PreparedStatement pstmt = null;
        switch(string){
            case "buyTrans":    
                k = rs.getInt("buyTrsId");  k = k + 1;
                pstmt = conn.prepareStatement("UPDATE records SET buyTrsId = ?");
                pstmt.setInt(1, k);
                break;
                
            case "sellTrans":   
                k = rs.getInt("sellTrsId"); k = k + 1;
                pstmt = conn.prepareStatement("UPDATE records SET sellTrsId = ?");
                pstmt.setInt(1, k);
                break;
        }
        pstmt.executeUpdate();
	}
    
    public void setBalRecord(Float total, String str) throws SQLException 
    {
        ResultSet rs = this.runQuery("SELECT * FROM records");  rs.next();
        float f = rs.getFloat("balance");
        PreparedStatement pstmt = conn.prepareStatement("UPDATE records SET balance = ?");
        switch(str){
            case "buy":     pstmt.setFloat(1, f-total); break;
            case "sell":    pstmt.setFloat(1, f+total); break;
        }
        pstmt.executeUpdate();
	}

	public void setBuyInfo1(String billId, String suppId, String cur_time) throws SQLException {
        //this.runQuery("INSERT INTO buyinfo1 VALUES(\'"+ billId +"\', \'" +suppId + "\', \'" + cur_time + "\')");
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO buyinfo1 VALUES(?, ?, ?)");
        pstmt.setString(1, billId);
        pstmt.setString(2, suppId);
        pstmt.setString(3, cur_time);
        pstmt.executeUpdate();
	}

	public void setBuyInfo2(String billId, String sno, String a, String b2, Integer c, float e) throws SQLException {
        //this.runQuery("INSERT INTO buyinfo2 VALUES(\'"+ billId +"\', \'"+ sno +"\', \'"+ a +"\', \'"+ b2 +"\', " + Integer.toString(c) + ", " + Float.toString(e) + ")");
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO buyinfo2 VALUES(?, ?, ?, ?, ?, ?)");
        pstmt.setString(1, billId);
        pstmt.setString(2, sno);
        pstmt.setString(3, a);
        pstmt.setString(4, b2);
        pstmt.setInt(5, c);
        pstmt.setFloat(6, e);
        pstmt.executeUpdate();
    }

	public void setSellInfo1(String billId, String custId, String cur_time) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO sellinfo1 VALUES(?, ?, ?)");
        pstmt.setString(1, billId);
        if (custId.equals("Unregistered")){
            pstmt.setNull(2, Types.NULL);
        }else{
            pstmt.setString(2, custId);
        }
        pstmt.setString(3, cur_time);
        pstmt.executeUpdate();
	}

	public void setSellInfo2(String billId, String sno, String a, String b2, Integer c, float e) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO sellinfo2 VALUES(?, ?, ?, ?, ?, ?)");
        pstmt.setString(1, billId);
        pstmt.setString(2, sno);
        pstmt.setString(3, a);
        pstmt.setString(4, b2);
        pstmt.setInt(5, c);
        pstmt.setFloat(6, e);
        pstmt.executeUpdate();
	}

	public ResultSet getTrans(String string) throws SQLException {
        String sql = "";
        switch(string){
            case "All buy":     sql = "select * from buyinfo1 natural join buyinfo2";   break;
            case "All sell":    sql = "select * from sellinfo1 natural join sellinfo2"; break;
        }
        ResultSet rs = this.runQuery(sql);
		return rs;
	}

}