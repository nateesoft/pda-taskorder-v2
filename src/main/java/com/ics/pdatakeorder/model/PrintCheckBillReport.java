package com.ics.pdatakeorder.model;

/**
 *
 * @author User
 */
import com.ics.pdatakeorder.control.PosControl;
import com.ics.pdatakeorder.control.TableFileControl;
import com.ics.pdatakeorder.db.ConfigFile;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterName;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import com.ics.pdatakeorder.util.DateConvert;
import com.ics.pdatakeorder.util.ThaiUtil;
import java.awt.print.PrinterException;

public class PrintCheckBillReport {

    private static final String server = ConfigFile.getProperties("server");
    private static final String db = ConfigFile.getProperties("database");
    private static final String dbUser = ConfigFile.getProperties("user");
    private static final String dbPassword = ConfigFile.getProperties("pass");

    private Connection connect = null;

    private static final String MYSQL_HOST = "jdbc:mysql://" + server + ":3307/";
    private static final String MYSQL_DB = db;
    private static final String MYSQL_USER_PASS = "user=" + dbUser + "&password=" + dbPassword;
    private static final String MYSQL_URL = MYSQL_HOST + MYSQL_DB + "?" + MYSQL_USER_PASS;

    private static final String JASPER_FILE = "\\src\\java\\printReport\\printCheckBillReport.jasper";
    private final DecimalFormat df = new DecimalFormat("#,##0.00");
    private final DecimalFormat intFM = new DecimalFormat("#,##0");

    public void PrintCheckBillReport(
            final String tableNo, final String printerName, final String Macno) throws Exception {

        new Thread(() -> {
            TableFileControl tbControl = new TableFileControl();
            TableFileBean tbBean = tbControl.getData(tableNo);
            
            DateConvert dc = new DateConvert();
            PosControl control = new PosControl();
            POSHWSetup POSHWSetup = control.getData(Macno);
            ControlPrintCheckBill ctPrint = new ControlPrintCheckBill();
            
            try {
                if (tbBean.getTItem() > 0 && ConfigFile.getProperties("printerStation").equals("true")) {
                    PrintCheckBillReport report = new PrintCheckBillReport();
                    
                    // open connection
                    report.openConnection();
                    
                    String sourceFileName = "D:\\Code JavaFrom Dell\\pdaV.EngTabletOppoA12\\src\\java\\printReport\\printCheckBillReport.jrxml";
                    sourceFileName = "D:\\Report\\printCheckBillReport.jrxml";
                    String reportSource = JasperCompileManager.compileReportToFile(sourceFileName);
                    // set parameters
                    Map param = new HashMap();
                    //Header
                    param.put("headTitle", "*ใบตรวจสอบรายการไม่ใช่ใบเสร็จรับเงิน*");
                    param.put("tableNo", tbBean.getTcode());
                    param.put("heading1", ThaiUtil.ASCII2Unicode(POSHWSetup.getHeading1()));
                    param.put("heading2", ThaiUtil.ASCII2Unicode(POSHWSetup.getHeading2()));
                    param.put("heading3", ThaiUtil.ASCII2Unicode(POSHWSetup.getHeading3()));
                    param.put("heading4", ThaiUtil.ASCII2Unicode(POSHWSetup.getHeading4()));
                    param.put("textHeader", dc.dateGetToShow(dc.GetCurrentDate()) + " " + dc.GetCurrentTime() + "  Cashier:" + ThaiUtil.ASCII2Unicode(tbBean.getCashier()) + " Mac:" + tbBean.getMacNo());
                    param.put("printerName", printerName);
                    
                    //Footer
                    param.put("item", ThaiUtil.ASCII2Unicode(intFM.format(tbBean.getTItem())));
                    param.put("subTotal", ThaiUtil.ASCII2Unicode(df.format(tbBean.getTAmount())));
                    param.put("netTotal", ThaiUtil.ASCII2Unicode(df.format(tbBean.getNetTotal())));
                    param.put("custTomer", ThaiUtil.ASCII2Unicode(intFM.format(tbBean.getTCustomer())));
                    param.put("textVat", ThaiUtil.ASCII2Unicode(POSHWSetup.getFootting1()));
                    
                    JasperPrint jasperPrint = JasperFillManager.fillReport(reportSource, param, report.getConnection());
                    
                    //Class Printer Job
                    PrinterJob printerJob = PrinterJob.getPrinterJob();
                    
                    PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
                    printerJob.defaultPage(pageFormat);
                    
                    int selectedService = 0;
                    
                    AttributeSet attributeSet = new HashPrintServiceAttributeSet(new PrinterName(printerName, null));
                    PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, attributeSet);
                    
                    try {
                        printerJob.setPrintService(printService[selectedService]);
                    } catch (PrinterException e) {
                        System.out.println(e);
                    }
                    JRPrintServiceExporter exporter;
                    PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
                    printRequestAttributeSet.add(MediaSizeName.NA_LETTER);
                    printRequestAttributeSet.add(new Copies(1));
                    
                    // these are deprecated
                    exporter = new JRPrintServiceExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService[selectedService]);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService[selectedService].getAttributes());
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                    exporter.exportReport();
                    
                    param.clear();
                    report.closeConnection();
                    ctPrint.setPrintCheckBillItemAfterSendKic(tableNo);
                    Thread.sleep(100);
                }
                
            } catch (Exception ex) {
                Logger.getLogger(PrintCheckBillReport.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();

    }

    public Connection getConnection() throws Exception {
        return connect;
    }

    public void closeConnection() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }

    public Connection openConnection() throws Exception {
        if (connect == null) {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(MYSQL_URL);
        }
        return connect;
    }

}
