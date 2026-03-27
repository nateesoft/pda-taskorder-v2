package com.ics.pdatakeorder;

import com.ics.pdatakeorder.model.PrintCheckBillReport;
import com.ics.pdatakeorder.db.ConfigFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class printCheckBillStation {

    private String tableNo = "";
    private String PrinterName = "";
    private String Macno = "";

    public void printCheckBillStation(String tableNo, String PrinterName, String Macno) {
        this.tableNo = tableNo;
        this.PrinterName = PrinterName;
        this.Macno = Macno;
        if (ConfigFile.getProperties("printerStation").equals("true")) {
            new Thread(() -> {
                printProcess();
            }).start();
        } else {
            System.out.print("ไม่ได้กำหนดให้ printerStation ใช้งาน \nกรุณาชำระเงินที่แคชเชียร์");
        }
    }

    private void printProcess() {
        try {
            PrintCheckBillReport print = new PrintCheckBillReport();
            print.PrintCheckBillReport(tableNo, PrinterName, Macno);
        } catch (Exception ex) {
            Logger.getLogger(printCheckBillStation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
