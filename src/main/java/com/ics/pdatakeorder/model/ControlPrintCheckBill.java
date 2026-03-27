package com.ics.pdatakeorder.model;

import com.ics.pdatakeorder.control.EmployControl;
import com.ics.pdatakeorder.db.MySQLConnect;
import com.ics.pdatakeorder.util.ThaiUtil;
import java.sql.SQLException;

/**
 *
 * @author Dell-Softpos
 */
public class ControlPrintCheckBill {

    private final MySQLConnect mysql = new MySQLConnect();

    public void PrintCheckBill(String tableNO, boolean CheckBill, String emp, String PrinterName, String Macno) {

        EmployControl empc = new EmployControl();
        if (CheckBill == true) {
            emp = ThaiUtil.Unicode2ASCII(empc.empName(emp));
            try {
                mysql.open();
                String sql = "update balance set "
                        + "PDAPrintCheck='Y',pdaemp='" + ThaiUtil.Unicode2ASCII(emp) + "',"
                        + "PDAPrintCheckStation='" + PrinterName + "' "
                        + "where r_table='" + tableNO.toUpperCase() + "' "
                        + "and trantype ='PDA';";
                mysql.getConnection().createStatement().executeUpdate(sql);
                PrintCheckBillReport print = new PrintCheckBillReport();
                print.PrintCheckBillReport(tableNO, PrinterName, Macno);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            } finally {
                mysql.close();
            }
        }
    }

    public void setPrintCheckBillItemAfterSendKic(String tableNO) {
        try {
            String sql = "update balance set PDAPrintChekItemStation='Y' "
                    + "where PDAPrintChekItemStation='N' and r_table='" + tableNO + "'";
            mysql.open();
            mysql.getConnection().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public void PrintUrgentFood(String tableNO) {
        try {
            String sql = "update kictran set R_FoodUrgent='Y',R_AlertKitChen='Y' where PTable='" + tableNO + "' and PFlage='N';";
            mysql.open();
            mysql.getConnection().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }

    }
}
