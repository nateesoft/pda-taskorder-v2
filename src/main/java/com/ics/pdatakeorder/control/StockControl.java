package com.ics.pdatakeorder.control;

import com.ics.pdatakeorder.model.STCardBean;
import com.ics.pdatakeorder.db.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StockControl {

    public MySQLConnect mysql = new MySQLConnect();

    public String GET_STOCK_NAME(String PCode, String table, String MACNO) {
        String sql = "select PCode,PGroup,PDesc,POSStk, MSStk from product "
                + "where PActive='Y' and PStock='Y' and PCode='" + PCode + "'";
        String stock = "";
        try {
            mysql.open();
            int Stock_Int;
            try (ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql)) {
                if (rs.next()) {

                    Stock_Int = rs.getInt("POSStk");

                    //คลังหลัก ถูกกำหนดโดยตรงจาก Company
                    switch (Stock_Int) {
                        //คลังเลือก ถูกกำหนดโดยแต่ละสาขา ซึ่งจะแยกเป็น ตัดตามสต็อกที่กำหนดโดย Table/POS
                        case 0:
                            String sqlCom = "select PosStock from company";
                            ResultSet rsCom = mysql.getConnection().createStatement().executeQuery(sqlCom);
                            if (rsCom.next()) {
                                stock = rsCom.getString("PosStock");
                            }
                            rsCom.close();
                            break;
                        //คลังย่อย แต่ละสินค้าจะเป็นตัวกำหนดคลังในการตัดสต็อกเอง
                        case 1:
                            String sqlBranch = "select PSelectStk from branch";
                            ResultSet rsBranch = mysql.getConnection().createStatement().executeQuery(sqlBranch);
                            if (rsBranch.next()) {
                                String selectedStk = rsBranch.getString("PSelectStk");

                                //พิจารณาตัดสต็อกตาม POS
                                if (selectedStk.equals("P")) {
                                    String sqlStock = "select TStock from poshwsetup where Terminal='" + MACNO + "'";
                                    try (ResultSet rsStock = mysql.getConnection().createStatement().executeQuery(sqlStock)) {
                                        if (rsStock.next()) {
                                            stock = rsStock.getString("TStock");
                                        }
                                    }
                                } //พิจารณาตัดสต็อกตาม Table ซึ่งแต่ละ Table จะมีการกำหนดค่าสต็อก 2 คลัง เช่น คลังหลัก และคลังย่อย อย่างใดอย่างหนึ่ง
                                else if (selectedStk.equals("T")) {
                                    String sqlTable = "select Tcode, StkCode1, StkCode2 from tablefile where Tcode='" + table + "'";
                                    try (ResultSet rsTable = mysql.getConnection().createStatement().executeQuery(sqlTable)) {
                                        if (rsTable.next()) {
                                            String stkCode1 = rsTable.getString("StkCode1");
                                            String stkCode2 = rsTable.getString("StkCode2");

                                            //ถ้ามีการกำหนดคลังหลัก
                                            if (!stkCode1.equals("")) {
                                                stock = stkCode1;
                                            } //หากคลังหลักไม่สามารถใช้งานได้
                                            else if (!stkCode2.equals("")) {
                                                stock = stkCode2;
                                            }
                                        }
                                    }
                                }
                            }
                            rsBranch.close();
                            break;
                        case 2:
                            stock = rs.getString("MSStk");
                            break;
                        default:
                            break;
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }

        return stock;
    }

    public void updateSTKFileAdd(String BPCode, String StockCode, int qty) {
        SimpleDateFormat sim = new SimpleDateFormat("MM");
        int month;
        try {
            month = 12 + Integer.parseInt(sim.format(new Date()));
        } catch (NumberFormatException e) {
            month = 13;
        }
        // check stkfile is exists
        try {
            mysql.open();
            String sql = "select BPCode from stkfile "
                    + "where BPCode='" + BPCode + "' and BStk='" + StockCode + "'";
            try (ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql)) {
                if (rs.next()) {
                    String sqlUpd = "UPDATE stkfile "
                            + "set BQty" + month + "=BQty" + month + "-" + qty + " "
                            + "where BPCode='" + BPCode + "' and BStk='" + StockCode + "'";
                    try {
                        int Update = mysql.getConnection().createStatement().executeUpdate(sqlUpd);
                        if (Update > 0) {

                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    // insert stkfile
                    String sqlIns = "INSERT INTO stkfile "
                            + "(BPCode, BStk, BQty" + month + ") "
                            + "values('" + BPCode + "','" + StockCode + "',BQty" + month + "-" + qty + ");";
                    mysql.getConnection().createStatement().executeUpdate(sqlIns);
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }

    }

    public void updateSTKFileVoid(String BPCode, String StockCode, int qty) {
        SimpleDateFormat sim = new SimpleDateFormat("MM");
        int month;
        try {
            month = 12 + Integer.parseInt(sim.format(new Date()));
        } catch (NumberFormatException e) {
            month = 13;
        }

        String strQty;
        if (qty < 0) {
            strQty = "-" + qty;
        } else {
            strQty = "+" + qty;
        }

        String sql = "UPDATE stkfile "
                + "set BQty" + month + "=BQty" + month + strQty + " "
                + "where BPCode='" + BPCode + "' and BStk='" + StockCode + "'";
        try {
            mysql.open();
            int Update = mysql.getConnection().createStatement().executeUpdate(sql);
            if (Update > 0) {

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }

    }

    public void saveSTCard(STCardBean bean, String ETD) {
        try {
            mysql.open();
            String sql = "insert into stcard (S_Date,S_No,S_SubNo,S_Que,S_PCode,S_Stk,S_In,S_Out,S_InCost,S_OutCost,S_ACost,S_Rem,S_User,S_EntryDate,S_EntryTime,S_Link) "
                    + "values(curdate(),'" + bean.getS_No() + "','" + bean.getS_SubNo() + "','" + bean.getS_Que() + "','" + bean.getS_PCode() + "','" + bean.getS_Stk() + "',"
                    + "'" + bean.getS_In() + "','" + bean.getS_Out() + "','" + bean.getS_InCost() + "','" + bean.getS_OutCost() + "','" + bean.getS_ACost() + "','" + bean.getS_Rem() + "',"
                    + "'" + bean.getS_User() + "',curdate(),curtime(),'" + bean.getS_Link() + "')";
            mysql.getConnection().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }

    }

}
