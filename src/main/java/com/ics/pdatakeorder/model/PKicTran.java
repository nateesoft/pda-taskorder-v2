package com.ics.pdatakeorder.model;

import com.ics.pdatakeorder.control.ProductControl;
import com.ics.pdatakeorder.db.MySQLConnect;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.ics.pdatakeorder.util.DateConvert;
import com.ics.pdatakeorder.util.ThaiUtil;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class PKicTran {
    
    private static final MySQLConnect mysql = new MySQLConnect();

    public static void setPKicTran(List<BalanceBean> bill, int kicItemNo) {
        DateConvert dc = new DateConvert();
        String today = dc.GetCurrentDate();
        String time = dc.GetCurrentTime();
        try {
            mysql.open();
            if (!bill.isEmpty()) {
                for (int i = 0; i < bill.size(); i++) {

                    String sqlINSKictran = "INSERT INTO kictran ("
                            + "PItemNo, PDate, PCode,PIndex, MacNo,"
                            + " Cashier, Emp, PTable, PKic, PTimeIn,"
                            + " PTimeOut, PVoid, PETD, PQty, PFlage,"
                            + " PServe, PServeTime, PWaitTime, PPayment, PInvNo,"
                            + " PWaitServe, PWaitTotal, R_PEName ,R_UrgentFoodItemName) "
                            + "VALUES ("
                            + "'" + bill.get(i).getR_PItemNo() + "', '" + today + "', '" + bill.get(i).getR_PluCode() + "', '" + bill.get(i).getR_Index() + "', '" + bill.get(i).getMacno() + "',"
                            + " '" + bill.get(i).getCashier() + "', '" + bill.get(i).getR_Emp() + "', '" + bill.get(i).getR_Table() + "', '" + bill.get(i).getR_Kic() + "', '" + time + "',"
                            + " '00:00:00', '', '" + bill.get(i).getR_ETD() + "', '" + bill.get(i).getR_Quan() + "', 'N',"
                            + " 'N', '00:00:00', '00:00:00', 'N', '',"
                            + " '00:00:00', '00:00:00', '', ''); ";
                    mysql.getConnection().createStatement().executeUpdate(sqlINSKictran);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public List<PKicTranBean> getKicTran(String tableNo) {
        ProductControl ProductControl = new ProductControl();
        DateConvert dc = new DateConvert();
        List<PKicTranBean> list = new ArrayList();
        
        try {
            mysql.open();
            String sql = "select pitemno,pcode,pindex,ptable,ptimein,"
                    + "pqty,pflage,petd,r_showdisplayalert "
                    + "from kictran "
                    + "where ptable='" + tableNo + "' "
                    + "and pflage='N' "
                    + "and r_printCheckOut='N' "
                    + "order by pitemno,petd;";
            try (ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql)) {
                while (rs.next()) {
                    PKicTranBean kicTranBean = new PKicTranBean();
                    ProductBean bean = ProductControl.getData(rs.getString("pcode"));
                    kicTranBean.setPItemNo(rs.getString("pitemno"));
                    kicTranBean.setPCode(rs.getString("pcode"));
                    kicTranBean.setPDesc(bean.getPDesc());
                    kicTranBean.setPIndex(rs.getString("pindex"));
                    kicTranBean.setPTable(rs.getString("ptable"));
                    kicTranBean.setPTimeIn(rs.getString("ptimeIn"));
                    kicTranBean.setPQty(rs.getInt("pqty"));
                    kicTranBean.setPFlage(rs.getString("pflage"));
                    String etd = rs.getString("petd");
                    kicTranBean.setShowDisplayAlert(rs.getString("R_ShowDisplayAlert"));
                    if (etd.equals("E")) {
                        etd = "นั่งทาน";
                    }
                    if (etd.equals("T")) {
                        etd = "ห่อกลับ";
                    }
                    if (etd.equals("D")) {
                        etd = "Delivery";
                    }
                    if (etd.equals("P")) {
                        etd = "Pinto";
                    }

                    if (etd.equals("W")) {
                        etd = "WholeSale";
                    }
                    kicTranBean.setPEtd(etd);
                    String timeWait;
                    timeWait = getDefferentTime(kicTranBean.getPTimeIn(), dc.GetCurrentTime());
                    kicTranBean.setPWaitTime(timeWait);
                    list.add(kicTranBean);
                }
            }
        } catch (SQLException | ParseException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }
        
        return list;
    }

    public String getDefferentTime(String time1, String time2) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date1 = format.parse(time1);
        Date date2 = format.parse(time2);
        long difference = date2.getTime() - date1.getTime();
        long diffMinutes = difference / (60 * 1000) % 60;
        long diffHours = difference / (60 * 60 * 1000) % 24;
        String time = diffHours + "." + diffMinutes;
        
        return time;
    }

    public static void setPKicTranAgain(String TableNo) {
        DateConvert dc = new DateConvert();
        try {
            String sql = "update kictran "
                    + "set R_AlertKitChen='N',R_FoodUrgent='Y' "
                    + "where ptable='" + ThaiUtil.Unicode2ASCII(TableNo) + "' "
                    + "and PFlage='N';";
            mysql.open();
            mysql.getConnection().createStatement().executeUpdate(sql);
            try {
                String sqlInsTemp = "insert into kictran_logUrgent (select * from kictran where ptable='" + TableNo + "');";
                mysql.open();
                mysql.getConnection().createStatement().executeUpdate(sqlInsTemp);
                String sqlInsLog = "insert into kictran_urgentclick (pTable,pDate,pTime) values('" + TableNo + "','" + dc.GetCurrentDate() + "','" + dc.GetCurrentTime() + "');";
                mysql.open();
                mysql.getConnection().createStatement().executeUpdate(sqlInsLog);
                mysql.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public static void clearKicTran(String TableNo) {
        String sql = "delete from kictran where ptable='" + TableNo + "';";
        try {
            mysql.open();
            mysql.getConnection().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }
    }
}
