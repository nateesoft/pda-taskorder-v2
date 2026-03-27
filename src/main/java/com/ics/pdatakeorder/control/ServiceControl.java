package com.ics.pdatakeorder.control;

import com.ics.pdatakeorder.model.POSConfigSetup;
import com.ics.pdatakeorder.model.BalanceBean;
import com.ics.pdatakeorder.db.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceControl {

    private final PosControl posControl;

    public ServiceControl() {
        posControl = new PosControl();
    }

    public void updateService(String table) {
        MySQLConnect mysql = new MySQLConnect();
        try {
            BalanceControl balanceControl = new BalanceControl();
            ArrayList<BalanceBean> dataBalance = balanceControl.getAllBalance(table);
            double ServiceTotal = 0;
            double VatTotal = 0;
            double ServicePercent = POSConfigSetup.Bean().getP_Service();

            for (int i = 0; i < dataBalance.size(); i++) {
                double ServiceLine;
                BalanceBean balance = (BalanceBean) dataBalance.get(i);

                //ตรวจสอบสินค้าที่ Void ไปแล้ว
                if (balance.getR_Void().equals("V")) {
                    continue;
                }

                //ตรวจสอบ Type ของรายการสินค้าว่าสามารถให้ส่วนลดได้หรือไม่ ?
                if (posControl.getETDPW_Active(balance.getR_ETD(), POSConfigSetup.Bean().getP_SerChkBySaleType())) {

                    //ตรวจสอบการคิดค่าบริการ
                    if (balance.getR_Service().equals("Y")) {

                        //คิดค่าบริการแบบยอด Net
                        if (POSConfigSetup.Bean().getP_ServiceType().equals("N")) {
                            ServiceLine = balance.getR_Total() - balance.getR_PrAmt();
                            ServiceTotal += ServiceLine;
                        }

                        //คิดค่าบริการแบบยอด Gross
                        if (POSConfigSetup.Bean().getP_ServiceType().equals("G")) {
                            ServiceLine = balance.getR_Total();
                            ServiceTotal += ServiceLine;
                        }
                    }

                    //คิดภาษีหรือไม่ ?
                    if (balance.getR_Vat().equals("V")) {
                        //คิดภาษีแบบ Include Vat
                        if (POSConfigSetup.Bean().getP_VatType().equals("I")) {
                            //NetTotal += (balance.getR_Total() - balance.getR_PrAmt())+ServiceLine;
//                            VatTotal += 0;
                        } //คิดภาษีแบบ Exclude Vat
                        else if (POSConfigSetup.Bean().getP_VatType().equals("E")) {
                            VatTotal += balance.getR_Total();
                        }
                    }
                }
            }

            //คำนวณค่า Service ตรวจสอบต่อว่าจะให้ปัดขึ้นหรือลง
            double ServiceAmt = 0;
            if (ServiceTotal > 0) {
                ServiceAmt = ServiceTotal * ServicePercent / 100;

                //ปัดเศษทศนิยม
                ServiceAmt = getDouble(ServiceAmt); // ServiceAmt คำนวณถูกแล้ว
            }

            double Total_Vat_Amt = 0;
            double TAmount = 0.00;
            mysql.open();
            String sqlTotal = "select sum(R_Price*R_Quan) TAmount "
                    + "from balance "
                    + "where r_void<>'V' "
                    + "and R_Table='" + table + "';";
            ResultSet rsTotal = mysql.getConnection().createStatement().executeQuery(sqlTotal);
            if (rsTotal.next()) {
                TAmount = rsTotal.getDouble("TAmount");
            }

            rsTotal.close();

            /// คำนวณแปลกๆ (ItemDiscAmt เก็บค่ามารวมกับ ProDiscAmt อีกทีทำให้ค่าส่วนลด เยอะเกิน)
            String sql = "select * from tablefile where Tcode='" + table + "'";
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                double ProDiscAmt = rs.getDouble("ProDiscAmt");
//                        + rs.getDouble("ItemDiscAmt");

                //ไม่มีสินค้ารายการใดคิด Vat
                if (VatTotal == 0) {
                    Total_Vat_Amt = TAmount - ProDiscAmt + ServiceAmt; //ServiceAmt = 20.25 น่าจะปัดเป็นขึ้น 20.5
                } else {
                    double vat = (VatTotal - ProDiscAmt + ServiceAmt) * POSConfigSetup.Bean().getP_Vat() / 100;
                    Total_Vat_Amt = (TAmount - ProDiscAmt + ServiceAmt) + vat;
                }
            }
            rs.close();
            String tItem = "0";
            try {
                String sql2 = "select count(R_Index) TIems "
                        + "from balance "
                        + "where r_table='" + table + "'";
                ResultSet rs2 = mysql.getConnection().createStatement().executeQuery(sql2);
                if (rs2.next()) {
                    tItem = "" + rs2.getString("TIems");
                }
                rs2.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
                    
            String sqlUpd = "update tablefile "
                    + "set ServiceAmt = '" + ServiceAmt + "',"
                    + "TAmount='" + TAmount + "',"
                    + "NetTotal = " + Total_Vat_Amt + ","
                    + "TItem='" + tItem + "' " // NetTotal ผิด
                    + "where Tcode = '" + table + "'";
            mysql.getConnection().createStatement().executeUpdate(sqlUpd);
            

            // update all discount
            // ส่วนนี้ยัง งง อยู่เหมือนกันว่าทำไมถึงเรียกซ้ำอีกรอบ มันเลยทำให้ค่า ServiceAmt และ Total_Vat_Amt เปลี่ยน
            // ตอนแรกก็ใช้ได้ แต่ตอนตรวจสอบหาว่าสินค้า คิด Vat หรือไม่มันไม่มี เพราะเงื่อนไขตรวจสอบ vat ก่อนเงื่อนไขข้างล่าง
//            TableFileControl tfc = new TableFileControl();
//            TableFileBean tBean = tfc.getData(table);
//            double totalDiscount = tBean.getEmpDiscAmt()+tBean.getFastDiscAmt()+tBean.getTrainDiscAmt()+
//                    tBean.getMemDiscAmt()+tBean.getSubDiscAmt()+tBean.getDiscBath()+
//                    tBean.getSpaDiscAmt()+tBean.getCuponDiscAmt()+
////                    tBean.getItemDiscAmt()+
//                    tBean.getProDiscAmt();
//            ServiceAmt = ((tBean.getTAmount()-totalDiscount)*ServicePercent)/100; // totalDiscount เก็บค่ามาผิด 45 จริงๆ ต้องเอาค่า 20.25
//            Total_Vat_Amt = (tBean.getTAmount()-totalDiscount) + ServiceAmt; // ServiceAmt ตามจริงต้องได้ 20.25
//            try {
//                sqlUpd = "update tablefile "
//                    + "set ServiceAmt = '" + ServiceAmt + "',"
//                    + "TAmount='" + TAmount + "',"
//                    + "NetTotal = " + Total_Vat_Amt + " "
//                    + "where Tcode = '" + table + "'";
//                MySQLConnect.exeUpdate(sqlUpd);
//            } catch (Exception e) {
//                System.err.println(e.getMessage());
//            }
//            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public static double getDouble(double db) {
        if (POSConfigSetup.Bean().getP_ServiceRound().equalsIgnoreCase("U")) {
            db = NumberControl.UP_BAHT(db);
        }
        if (POSConfigSetup.Bean().getP_ServiceRound().equalsIgnoreCase("D")) {
            db = NumberControl.DOWN_BAHT(db);
        }
        if (POSConfigSetup.Bean().getP_ServiceRound().equalsIgnoreCase("O")) {
            return db;
        }
        if (POSConfigSetup.Bean().getP_ServiceRound().equalsIgnoreCase("N")) {
            db = NumberControl.UP_DOWN_NATURAL_BAHT(db);
        }
        if (POSConfigSetup.Bean().getP_ServiceRound().equalsIgnoreCase("F")) {
            db = NumberControl.UP_DOWN_25(db);
        } else {
            return db;
        }

        return db;
    }

}
