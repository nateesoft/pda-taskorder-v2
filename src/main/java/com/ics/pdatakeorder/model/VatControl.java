package com.ics.pdatakeorder.model;

import com.ics.pdatakeorder.control.TableFileControl;
import com.ics.pdatakeorder.db.MySQLConnect;

public class VatControl {

    private String vatType = "";
    private String tableNo = "";
    TableFileControl tfControl = null;
    TableFileBean tBean = null;
    private double Vat = 0.00;
//    private MySQLConnect mysql = new MySQLConnect();

    public VatControl(String tableNo) {

        this.tableNo = tableNo;
        vatType = POSConfigSetup.Bean().getP_VatType();
        Vat = POSConfigSetup.Bean().getP_Vat();
        tfControl = new TableFileControl();
        tBean = tfControl.getData(tableNo);
    }

    public void updateVat() {
        if (vatType.equals("I")) {
            updateVatInclude();
        } else if (vatType.equals("E")) {
            updateVatExclude();
        }
    }

    public void updateVatInclude() {
        System.out.println("Include Vat.");

        double TAmount = tBean.getTAmount();
        double ServiceAmt = tBean.getServiceAmt();
        double ProDiscAmt = tBean.getProDiscAmt();
        double NetTotal = (TAmount + ServiceAmt) - ProDiscAmt;  //ตอนคำนวน Vat ลืมลบ ProDiscAmt
        MySQLConnect mysql = new MySQLConnect();
        try {
            mysql.open();
            String sql = "update tablefile "
                    + "set NetTotal='" + NetTotal + "' "
                    + "where TCode='" + tableNo + "'";
            mysql.getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public void updateVatExclude() {
        System.out.println("Exclude Vat.");

        double TAmount = tBean.getTAmount();
        double ServiceAmt = tBean.getServiceAmt();
        double total = TAmount + ServiceAmt;
        double NetTotal = (total * Vat / 100) + total;
        MySQLConnect mysql = new MySQLConnect();
        try {
            mysql.open();
            String sql = "update tablefile "
                    + "set NetTotal='" + NetTotal + "' "
                    + "where TCode='" + tableNo + "'";
            mysql.getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }
    }
}
