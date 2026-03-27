/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ics.pdatakeorder.model;

import com.ics.pdatakeorder.control.EmployControl;
import com.ics.pdatakeorder.db.MySQLConnect;
import com.ics.pdatakeorder.util.MSG;
import com.ics.pdatakeorder.util.ThaiUtil;

/**
 *
 * @author Dell-Softpos
 */
public class ControlPrintCheckBill {

    public void PrintCheckBill(String tableNO, boolean CheckBill, String emp, String PrinterName, String Macno) {

        EmployControl empc = new EmployControl();
        if (CheckBill == true) {
            emp = ThaiUtil.Unicode2ASCII(empc.empName(emp));
            MySQLConnect mysql = new MySQLConnect();
            try {
                mysql.open();
                String sql = "update balance set PDAPrintCheck='Y',pdaemp='" + ThaiUtil.Unicode2ASCII(emp) + "',PDAPrintCheckStation='" + PrinterName + "' "
                        + "where r_table='" + tableNO.toUpperCase() + "' "
                        + "and trantype ='PDA';";
//                String sql11 = "update tablefile set chkbill='Y' where tcode='" + tableNO + "'";
                mysql.getConnection().createStatement().executeUpdate(sql);
//                mysql.getConnection().createStatement().executeUpdate(sql11);
                PrintCheckBillReport print = new PrintCheckBillReport();
                print.PrintCheckBillReport(tableNO, PrinterName, Macno);

            } catch (Exception e) {
                System.out.println(e.toString());
            } finally {
                mysql.close();
            }
        }
    }

    public void setPrintCheckBillItemAfterSendKic(String tableNO) {
        MySQLConnect mysql = new MySQLConnect();
        try {
            String sql = "update balance set PDAPrintChekItemStation='Y' "
                    + "where PDAPrintChekItemStation='N' and r_table='" + tableNO + "'";
            mysql.open();
            mysql.getConnection().createStatement().executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            mysql.close();
        }
    }

    public void PrintUrgentFood(String tableNO) {
        MySQLConnect mysql = new MySQLConnect();
        try {
            String sql = "update kictran set R_FoodUrgent='Y',R_AlertKitChen='Y' where PTable='" + tableNO + "' and PFlage='N';";
            mysql.open();
            mysql.getConnection().createStatement().executeUpdate(sql);
        } catch (Exception e) {
            MSG.NOTICE(e.toString());
            System.out.println(e.toString());
        } finally {
            mysql.close();
        }

    }
}
