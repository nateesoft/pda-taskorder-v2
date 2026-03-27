package com.ics.pdatakeorder.model;

import com.ics.pdatakeorder.db.MySQLConnect;
import java.sql.ResultSet;
import com.ics.pdatakeorder.util.ThaiUtil;

public class OptionFile {

    public static String[] getListOption(String PGroup) {
        String opt = "";
        MySQLConnect mysql =new MySQLConnect();
        try {
            
            mysql.open();
            String sql = "select * from optionfile where PGroup =  '" + PGroup + "';";
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                opt += ThaiUtil.ASCII2Unicode(rs.getString("OptionName")) + ",";
            }
            rs.close();
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }finally{
            mysql.close();
        }

        return opt.split(",");
    }
}
