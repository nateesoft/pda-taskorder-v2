package com.ics.pdatakeorder.control;

import com.ics.pdatakeorder.db.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MacnoControl {
    
    public static int MACNO_AVAILABLE = 1;
    public static int MACNO_NOT_AVAILABLE = 2;
    public static int NO_CONNECTION = 3;
    public static int NOT_FOUND_MACNO = 4;
    
    private static final MySQLConnect mysql= new MySQLConnect();
    
    public static int checkMacno(String macno){
        try {
            mysql.open();
            String sql = "select * "
                    + "from poshwsetup "
                    + "where Terminal='"+macno+"' ";
            ResultSet i = mysql.getConnection().createStatement().executeQuery(sql);
            if(i.next()){
                if(i.getString("OnAct").equals("Y")){
                    return MACNO_NOT_AVAILABLE;
                }else{
                    return MACNO_AVAILABLE;//Macno ready
                }
            }else{
                return NOT_FOUND_MACNO;//Macno not available
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return NO_CONNECTION;//No connect database
        } finally {
            mysql.close();
        }
    }
}
