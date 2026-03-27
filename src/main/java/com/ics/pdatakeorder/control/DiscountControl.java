package com.ics.pdatakeorder.control;

import com.ics.pdatakeorder.model.POSConfigSetup;
import com.ics.pdatakeorder.db.MySQLConnect;
import java.sql.ResultSet;

public class DiscountControl {

    public static double getDouble(double db) {
        if (POSConfigSetup.Bean().getP_DiscRound().equals("F")) {
            return NumberControl.UP_DOWN_25(db);
        } else {
            return db;
        }
    }

    public void updateDiscount(String tableNo) {
        MySQLConnect mysql = new MySQLConnect();
        try {

            mysql.open();
            String sql = "select sum(R_PrAmt+R_DiscBath) R_PrAmt "
                    + "from balance "
                    + "where R_Void<>'V' "
                    + "and R_Discount='Y' "
                    + "group by R_Table "
                    + "order by R_Index;";
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                String sqlUpd = "update tablefile set ItemDiscAmt='" + rs.getDouble("R_PrAmt") + "'";
                mysql.getConnection().createStatement().executeUpdate(sqlUpd);
            }
            rs.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }
    }
}
