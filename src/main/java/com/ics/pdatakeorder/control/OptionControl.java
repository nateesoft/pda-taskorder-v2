package com.ics.pdatakeorder.control;

import com.ics.pdatakeorder.db.MySQLConnect;
import com.ics.pdatakeorder.util.ThaiUtil;
import java.sql.SQLException;

public class OptionControl {

    private final MySQLConnect mysql = new MySQLConnect();

    public boolean updateOption(String index, String opt, String type) {
        String[] dataOpt = new String[]{"", "", "", "", "", "", "", "", ""};
        opt = ThaiUtil.Unicode2ASCII(opt);
        String[] inpOpt = opt.split(",");
        System.arraycopy(inpOpt, 0, dataOpt, 0, inpOpt.length);

        String sql;
        try {
            mysql.open();
            sql = "update balance set "
                    + "R_ETD='" + type + "' ";
            if (!dataOpt[0].equals("")) {
                sql += ",R_Opt1='" + dataOpt[0] + "', "
                        + "R_Opt2='" + dataOpt[1] + "', "
                        + "R_Opt3='" + dataOpt[2] + "', "
                        + "R_Opt4='" + dataOpt[3] + "', "
                        + "R_Opt5='" + dataOpt[4] + "', "
                        + "R_Opt6='" + dataOpt[5] + "', "
                        + "R_Opt7='" + dataOpt[6] + "', "
                        + "R_Opt8='" + dataOpt[7] + "', "
                        + "R_Opt9='" + dataOpt[8] + "' ";
            }

            sql += " where R_Index='" + ThaiUtil.Unicode2ASCII(index) + "' ";

            mysql.getConnection().createStatement().executeUpdate(sql);

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            mysql.close();
        }

        return false;
    }

}
