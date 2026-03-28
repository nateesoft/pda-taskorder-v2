package com.ics.pdatakeorder.control;

import com.ics.pdatakeorder.model.POSConfigSetup;
import com.ics.pdatakeorder.model.TableFileBean;
import com.ics.pdatakeorder.db.MySQLConnect;
import java.sql.ResultSet;
import java.sql.Statement;
import com.ics.pdatakeorder.util.ThaiUtil;
import java.sql.SQLException;

public class TableFileControl {
    private static final MySQLConnect mysql = new MySQLConnect();
    public static final int TABLE_READY = 1;
    public static final int TABLE_NOT_ACTIVE = 2;
    public static final int TABLE_NOT_SETUP = 0;
    public static final int TABLE_EXIST_DATA = 3;
    public static final int TABLE_EXIST_DATA_IS_ACTIVE = 4;
    public static String USER_USE = "";

    public static boolean checkBillReady(String R_Table) {
        boolean isCheckBill = false;
        try {

            mysql.open();
            String sql = "select TCode from tablefile "
                    + "where chkbill='Y' "
                    + "and TCode='" + R_Table + "';";
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                isCheckBill = true;
            }

            rs.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            isCheckBill = false;
        } finally {
            mysql.close();
        }

        return isCheckBill;
    }

    public int checkTableRead(String tableNo, String empCode, String MACNO) {
        int result = TABLE_NOT_SETUP;
        try {
            mysql.open();
            String sql = "select * "
                    + "from tablefile "
                    + "where TCode='" + ThaiUtil.Unicode2ASCII(tableNo) + "'";
            try (ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql)) {
                if (rs.next()) {
                    String TActive = rs.getString("TActive");
                    if(TActive == null || TActive.equals("") || TActive.equals("N")){
                        return TABLE_NOT_ACTIVE;
                    }
                    
                    String TOnAct = rs.getString("TOnAct");
                    String TUser = rs.getString("TUSer");

                    if (!TOnAct.equalsIgnoreCase("Y")) {
                        return TABLE_EXIST_DATA;
                    }
                    
                    if (TOnAct.equalsIgnoreCase("Y") && empCode.equals(TUser)) {
                        return TABLE_READY;
                    }

                    try {
                        String sql2 = "select Name from employ where code='" + TUser + "';";
                        try (ResultSet rs2 = mysql.getConnection().createStatement().executeQuery(sql2)) {
                            if (rs2.next()) {
                                USER_USE = ThaiUtil.ASCII2Unicode(rs2.getString("Name"));
                            }

                            if (TUser.equals("")) {
                                try {
                                    String sql3 = "select Name from posuser where username='" + rs.getString("Cashier") + "';";
                                    try (ResultSet rs3 = mysql.getConnection().createStatement().executeQuery(sql3)) {
                                        if (rs3.next()) {
                                            USER_USE = ThaiUtil.ASCII2Unicode(rs3.getString("Name"));
                                        }
                                    }
                                } catch (SQLException e) {
                                    System.err.println(e.getMessage());
                                }
                            }
                        }
                        result = TABLE_EXIST_DATA_IS_ACTIVE;
                    } catch (SQLException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }

        return result;
    }

    public TableFileBean getData(String table) {
        TableFileBean bean = new TableFileBean();
        try {
            mysql.open();
            String sql = "select * "
                    + "from tablefile "
                    + "where Tcode='" + ThaiUtil.Unicode2ASCII(table.toUpperCase()) + "'";
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                bean.setTcode(rs.getString("Tcode"));
                bean.setTActive(rs.getString("TActive"));
                bean.setSoneCode(rs.getString("SoneCode"));
                bean.setMacNo(rs.getString("MacNo"));
                bean.setCashier(rs.getString("Cashier"));
                bean.setTLoginTime(rs.getString("TLoginTime"));
                bean.setTCurTime(rs.getString("TCurTime"));
                bean.setTCustomer(rs.getInt("TCustomer"));
                bean.setTItem(rs.getInt("TItem"));
                bean.setTAmount(rs.getFloat("TAmount"));
                bean.setTOnAct(rs.getString("TOnAct"));
                bean.setService(rs.getFloat("Service"));
                bean.setServiceAmt(rs.getFloat("ServiceAmt"));
                bean.setEmpDisc(rs.getString("EmpDisc"));
                bean.setEmpDiscAmt(rs.getFloat("EmpDiscAmt"));
                bean.setFastDisc(rs.getString("FastDisc"));
                bean.setFastDiscAmt(rs.getFloat("FastDiscAmt"));
                bean.setTrainDisc(rs.getString("TrainDisc"));
                bean.setTrainDiscAmt(rs.getFloat("TrainDiscAmt"));
                bean.setMemDisc(rs.getString("MemDisc"));
                bean.setMemDiscAmt(rs.getFloat("MemDiscAmt"));
                bean.setSubDisc(rs.getString("SubDisc"));
                bean.setSubDiscAmt(rs.getFloat("SubDiscAmt"));
                bean.setDiscBath(rs.getFloat("DiscBath"));
                bean.setProDiscAmt(rs.getFloat("ProDiscAmt"));
                bean.setSpaDiscAmt(rs.getFloat("SpaDiscAmt"));
                bean.setCuponDiscAmt(rs.getFloat("CuponDiscAmt"));
                bean.setItemDiscAmt(rs.getFloat("ItemDiscAmt"));
                bean.setMemCode(rs.getString("MemCode"));
                bean.setMemCurAmt(rs.getFloat("MemCurAmt"));
                bean.setMemName(rs.getString("MemName"));
                bean.setFood(rs.getFloat("Food"));
                bean.setDrink(rs.getFloat("Drink"));
                bean.setProduct(rs.getFloat("Product"));
                bean.setNetTotal(rs.getFloat("NetTotal"));
                bean.setPrintTotal(rs.getFloat("PrintTotal"));
                bean.setPrintChkBill(rs.getString("PrintChkBill"));
                bean.setPrintCnt(rs.getInt("PrintCnt"));
                bean.setPrintTime1(rs.getString("PrintTime1"));
                bean.setPrintTime2(rs.getString("PrintTime2"));
                bean.setChkBill(rs.getString("ChkBill"));
                bean.setChkBillTime(rs.getString("ChkBillTime"));
                bean.setStkCode1(rs.getString("StkCode1"));
                bean.setStkCode2(rs.getString("StkCode2"));
                bean.setTDesk(rs.getInt("TDesk"));
                bean.setTUser(rs.getString("TUser"));
                bean.setTPause(rs.getString("TPause"));
            }
            rs.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }

        return bean;
    }

    public void updateTableActive(String table, String customer, String emp, String MACNO) {
        if (customer.equals("")) {
            customer = "0";
        }
        try {
            mysql.open();
            String sql = "update tablefile set "
                    + "TOnAct='Y',"
                    + "TLoginDate=now(),"
                    + "TLoginTime=curtime(),"
                    + "TCustomer='" + customer + "',"
                    + "TUser='" + emp + "',"
                    + "TCurTime=curtime(),"
                    + "MacNo='" + MACNO + "' "
                    + "where tcode='" + ThaiUtil.Unicode2ASCII(table) + "' "
                    + "and tOnAct='N'";
            mysql.getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public void updateTableHold(String table) {
        String date_default = "1899-12-30";
        try {
            mysql.open();
            String sqlGetEmp = "SELECT r_emp FROM balance where r_table='" + table + "' ORDER BY r_index DESC LIMIT 1;";
            ResultSet rsGetEmp = mysql.getConnection().createStatement().executeQuery(sqlGetEmp);
            String TUser = "";
            if (rsGetEmp.next()) {
                TUser = rsGetEmp.getString("r_emp");
            }
            String sql = "update tablefile set "
                    + "TOnAct='N',"
                    + "TCurTime=curtime(),"
                    + "Service='" + POSConfigSetup.Bean().getP_Service() + "',"
                    + "MemBegin='" + date_default + "',"
                    + "MemEnd='" + date_default + "',"
                    + "TUser='" + TUser + "',"
                    + "MacNo='',"
                    + "Cashier='',"
                    + "TPause='Y',"
                    + "TTableIsOn='Y' "
                    + "where tcode='" + ThaiUtil.Unicode2ASCII(table) + "' "
                    + "and tOnAct='Y'";
            mysql.getConnection().createStatement().executeUpdate(sql);
            String sql2 = "update balance set R_Pause='P' where r_table='" + ThaiUtil.Unicode2ASCII(table) + "'";
            mysql.getConnection().createStatement().executeUpdate(sql2);
            rsGetEmp.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public String getTableStatusActive(String table) {
        String status = "";
        try {
            String sql = "select TActive from tablefile where tcode='" + table + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                status = rs.getString("TActive");
            } else {
                status = "Y";
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            mysql.close();
        }
        return status;
    }

}
