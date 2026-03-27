package com.ics.pdatakeorder.control;

import com.ics.pdatakeorder.db.MySQLConnect;
import java.sql.ResultSet;
import com.ics.pdatakeorder.util.ThaiUtil;
import java.sql.SQLException;

public class EmployControl {
    
    private final MySQLConnect mysql = new MySQLConnect();

    public boolean checkEmployUse() {
        try {
            mysql.open();
            
            String sql = "select P_EmpUse from posconfigsetup where P_EmpUse='Y';";
            try (ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql)) {
                if (rs.next()) {
                    return true;
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }
        return false;
    }

    public boolean isEmployExists(String empCode) {
        try {
            mysql.open();
            String sql = "select * from employ where code='" + empCode + "'";
            try (ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql)) {
                if (rs.next()) {
                    return true;
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }
        return false;
    }

    public String empName(String empCode) {
        String empName = "";
        try {
            mysql.open();
            String sql = "select name from employ where code='" + empCode + "'";
            try (ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql)) {
                if (rs.next() && !rs.wasNull()) {
                    empName = ThaiUtil.ASCII2Unicode(rs.getString("name"));
                }
            }
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }finally{
            mysql.close();
        }
            
        return empName;
    }

}
