package com.ics.pdatakeorder.control;

import com.ics.pdatakeorder.model.POSConfigSetup;
import com.ics.pdatakeorder.db.MySQLConnect;
import java.sql.ResultSet;

public class DiscountControl_Bak {
    public static double getDouble(double db){
         if(POSConfigSetup.Bean().getP_DiscRound().equals("F")){
             return NumberControl.UP_DOWN_25(db);
         }else{
             return db;
         }
    }
    
    public void updateDiscount(String tableNo){
        try {
            MySQLConnect c = new MySQLConnect();
            c.open();
            String sql = "select sum(R_PrAmt+R_DiscBath) R_PrAmt "
                    + "from balance "
                    + "where R_Void<>'V' "
                    + "and R_Discount='Y' "
                    + "group by R_Table "
                    + "order by R_Index;";
            ResultSet rs = c.getConnection().createStatement().executeQuery(sql);
            if(rs.next()){
                String sqlUpd = "update tablefile set ItemDiscAmt='"+rs.getDouble("R_PrAmt")+"'";
                c.getConnection().createStatement().executeUpdate(sqlUpd);
            }
            
            rs.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
