package com.ics.pdatakeorder.control;

import com.ics.pdatakeorder.model.ProductBean;
import com.ics.pdatakeorder.model.MemberBean;
import com.ics.pdatakeorder.model.BranchBean;
import com.ics.pdatakeorder.model.CharactorCheck;
import com.ics.pdatakeorder.model.BalanceBean;
import com.ics.pdatakeorder.model.PKicTran;
import com.ics.pdatakeorder.model.VatControl;
import com.ics.pdatakeorder.db.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.ics.pdatakeorder.util.ThaiUtil;

public class BalanceControl {

    private BalanceBean balanceCurrent;
    private CharactorCheck charEngCheck = new CharactorCheck();

    public ArrayList<BalanceBean> getAllBalance(String table) {
        ArrayList<BalanceBean> beanData = new ArrayList<>();
        MySQLConnect mysql = new MySQLConnect();
        try {
            mysql.open();
            String sql = "select * from balance "
                    + "where R_Table='" + ThaiUtil.Unicode2ASCII(charEngCheck.charEngCheck(table)) + "' "
                    + "order by R_Index";
            Statement stmt = mysql.getConnection().createStatement();
            try (ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next() && !rs.wasNull()) {
                    BalanceBean balanceBean = new BalanceBean();
                    balanceBean.setR_Index(rs.getString("R_Index"));
                    balanceBean.setR_Table(rs.getString("R_Table"));
                    balanceBean.setR_Time(rs.getString("R_Time"));
                    balanceBean.setMacno(rs.getString("Macno"));
                    balanceBean.setCashier(rs.getString("Cashier"));
                    balanceBean.setR_Emp(rs.getString("R_Emp"));
                    balanceBean.setR_PluCode(rs.getString("R_PluCode"));
                    balanceBean.setR_PName(ThaiUtil.ASCII2Unicode(rs.getString("R_PName")));
                    balanceBean.setR_Unit(rs.getString("R_Unit"));
                    balanceBean.setR_Group(rs.getString("R_Group"));
                    balanceBean.setR_Status(rs.getString("R_Status"));
                    balanceBean.setR_Normal(rs.getString("R_Normal"));
                    balanceBean.setR_Discount(rs.getString("R_Discount"));
                    balanceBean.setR_Service(rs.getString("R_Service"));
                    balanceBean.setR_Stock(rs.getString("R_Stock"));
                    balanceBean.setR_Set(rs.getString("R_Set"));
                    balanceBean.setR_Vat(rs.getString("R_Vat"));
                    balanceBean.setR_Type(rs.getString("R_Type"));
                    balanceBean.setR_ETD(rs.getString("R_ETD"));
                    balanceBean.setR_Quan(rs.getFloat("R_Quan"));
                    balanceBean.setR_Price(rs.getFloat("R_Price"));
                    balanceBean.setR_Total(rs.getFloat("R_Total"));
                    balanceBean.setR_PrType(rs.getString("R_PrType"));
                    balanceBean.setR_PrCode(rs.getString("R_PrCode"));
                    balanceBean.setR_PrDisc(rs.getFloat("R_PrDisc"));
                    balanceBean.setR_PrBath(rs.getFloat("R_PrBath"));
                    balanceBean.setR_PrAmt(rs.getFloat("R_PrAmt"));
                    balanceBean.setR_DiscBath(rs.getFloat("R_DiscBath"));
                    balanceBean.setR_PrCuType(rs.getString("R_PrCuType"));
                    balanceBean.setR_PrCuQuan(rs.getFloat("R_PrCuQuan"));
                    balanceBean.setR_PrCuAmt(rs.getFloat("R_PrCuAmt"));
                    balanceBean.setR_Redule(rs.getFloat("R_Redule"));
                    balanceBean.setR_Kic(rs.getString("R_Kic"));
                    balanceBean.setR_KicPrint(rs.getString("R_KicPrint"));
                    balanceBean.setR_Void(rs.getString("R_Void"));
                    balanceBean.setR_VoidUser(rs.getString("R_VoidUser"));
                    balanceBean.setR_VoidTime(rs.getString("R_VoidTime"));
                    balanceBean.setR_Opt1(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt1")));
                    balanceBean.setR_Opt2(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt2")));
                    balanceBean.setR_Opt3(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt3")));
                    balanceBean.setR_Opt4(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt4")));
                    balanceBean.setR_Opt5(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt5")));
                    balanceBean.setR_Opt6(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt6")));
                    balanceBean.setR_Opt7(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt7")));
                    balanceBean.setR_Opt8(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt8")));
                    balanceBean.setR_Opt9(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt9")));
                    balanceBean.setR_PrCuCode(rs.getString("R_PrCuCode"));
                    balanceBean.setR_Serve(rs.getString("R_Serve"));
                    balanceBean.setR_PrintOK(rs.getString("R_PrintOK"));
                    balanceBean.setR_KicOK(rs.getString("R_KicOK"));
                    balanceBean.setStkCode(rs.getString("StkCode"));
                    balanceBean.setPosStk(rs.getString("PosStk"));
                    balanceBean.setR_PrChkType(rs.getString("R_PrChkType"));
                    balanceBean.setR_PrQuan(rs.getFloat("R_PrQuan"));
                    balanceBean.setR_PrSubType(rs.getString("R_PrSubType"));
                    balanceBean.setR_PrSubCode(rs.getString("R_PrSubCode"));
                    balanceBean.setR_PrSubQuan(rs.getFloat("R_PrSubQuan"));
                    balanceBean.setR_PrSubDisc(rs.getFloat("R_PrSubDisc"));
                    balanceBean.setR_PrSubBath(rs.getFloat("R_PrSubBath"));
                    balanceBean.setR_PrSubAmt(rs.getFloat("R_PrSubAmt"));
                    balanceBean.setR_PrSubAdj(rs.getFloat("R_PrSubAdj"));
                    balanceBean.setR_PrCuDisc(rs.getFloat("R_PrCuDisc"));
                    balanceBean.setR_PrCuBath(rs.getFloat("R_PrCuBath"));
                    balanceBean.setR_PrCuAdj(rs.getFloat("R_PrCuAdj"));
                    balanceBean.setR_QuanCanDisc(rs.getFloat("R_QuanCanDisc"));
                    balanceBean.setR_Order(rs.getString("R_Order"));
                    balanceBean.setR_PItemNo(rs.getInt("R_PItemNo"));
                    balanceBean.setR_PKicQue(rs.getInt("R_PKicQue"));
                    balanceBean.setR_MemSum(rs.getString("R_MemSum"));
                    balanceBean.setR_MoveItem(rs.getString("R_MoveItem"));
                    balanceBean.setR_MoveFrom(rs.getString("R_MoveFrom"));
                    balanceBean.setR_MoveUser(rs.getString("R_MoveUser"));
                    balanceBean.setR_MoveFlag(rs.getString("R_MoveFlag"));
                    balanceBean.setR_MovePrint(rs.getString("R_MovePrint"));
                    balanceBean.setR_Pause(rs.getString("R_Pause"));
                    balanceBean.setR_TotalServiceAmt(0);
                    try {
                        balanceBean.setR_Date(rs.getDate("R_Date"));
                    } catch (SQLException e) {
                        System.out.println("Error Date: " + e.getMessage());
                    }

                    beanData.add(balanceBean);
                }
            }
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }

        return beanData;
    }

    public ArrayList<BalanceBean> getAllBalancePtype(String table) {
        ArrayList<BalanceBean> beanData = new ArrayList<>();
        MySQLConnect mysql = new MySQLConnect();
        try {
            String sql = "select * from balance "
                    + "where R_Table='" + ThaiUtil.Unicode2ASCII(charEngCheck.charEngCheck(table)) + "' "
                    + "order by R_Index";
            sql = "select sum(b.r_quan) ,sum(b.r_total) ,b.r_price ,b.r_pname ,b.r_type ,b.r_etd from balance b where b.r_table='$P{tableNo}' and b.r_void<>'V' and b.r_quan>'0' group by r_plucode order by r_type,r_time";
            mysql.open();
            Statement stmt = mysql.getConnection().createStatement();
            try (ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next() && !rs.wasNull()) {
                    BalanceBean balanceBean = new BalanceBean();
                    balanceBean.setR_Index(rs.getString("R_Index"));
                    balanceBean.setR_Table(rs.getString("R_Table"));
                    balanceBean.setR_Time(rs.getString("R_Time"));
                    balanceBean.setMacno(rs.getString("Macno"));
                    balanceBean.setCashier(rs.getString("Cashier"));
                    balanceBean.setR_Emp(rs.getString("R_Emp"));
                    balanceBean.setR_PluCode(rs.getString("R_PluCode"));
                    balanceBean.setR_PName(ThaiUtil.ASCII2Unicode(rs.getString("R_PName")));
                    balanceBean.setR_Unit(rs.getString("R_Unit"));
                    balanceBean.setR_Group(rs.getString("R_Group"));
                    balanceBean.setR_Status(rs.getString("R_Status"));
                    balanceBean.setR_Normal(rs.getString("R_Normal"));
                    balanceBean.setR_Discount(rs.getString("R_Discount"));
                    balanceBean.setR_Service(rs.getString("R_Service"));
                    balanceBean.setR_Stock(rs.getString("R_Stock"));
                    balanceBean.setR_Set(rs.getString("R_Set"));
                    balanceBean.setR_Vat(rs.getString("R_Vat"));
                    balanceBean.setR_Type(rs.getString("R_Type"));
                    balanceBean.setR_ETD(rs.getString("R_ETD"));
                    balanceBean.setR_Quan(rs.getFloat("R_Quan"));
                    balanceBean.setR_Price(rs.getFloat("R_Price"));
                    balanceBean.setR_Total(rs.getFloat("R_Total"));
                    balanceBean.setR_PrType(rs.getString("R_PrType"));
                    balanceBean.setR_PrCode(rs.getString("R_PrCode"));
                    balanceBean.setR_PrDisc(rs.getFloat("R_PrDisc"));
                    balanceBean.setR_PrBath(rs.getFloat("R_PrBath"));
                    balanceBean.setR_PrAmt(rs.getFloat("R_PrAmt"));
                    balanceBean.setR_DiscBath(rs.getFloat("R_DiscBath"));
                    balanceBean.setR_PrCuType(rs.getString("R_PrCuType"));
                    balanceBean.setR_PrCuQuan(rs.getFloat("R_PrCuQuan"));
                    balanceBean.setR_PrCuAmt(rs.getFloat("R_PrCuAmt"));
                    balanceBean.setR_Redule(rs.getFloat("R_Redule"));
                    balanceBean.setR_Kic(rs.getString("R_Kic"));
                    balanceBean.setR_KicPrint(rs.getString("R_KicPrint"));
                    balanceBean.setR_Void(rs.getString("R_Void"));
                    balanceBean.setR_VoidUser(rs.getString("R_VoidUser"));
                    balanceBean.setR_VoidTime(rs.getString("R_VoidTime"));
                    balanceBean.setR_Opt1(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt1")));
                    balanceBean.setR_Opt2(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt2")));
                    balanceBean.setR_Opt3(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt3")));
                    balanceBean.setR_Opt4(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt4")));
                    balanceBean.setR_Opt5(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt5")));
                    balanceBean.setR_Opt6(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt6")));
                    balanceBean.setR_Opt7(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt7")));
                    balanceBean.setR_Opt8(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt8")));
                    balanceBean.setR_Opt9(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt9")));
                    balanceBean.setR_PrCuCode(rs.getString("R_PrCuCode"));
                    balanceBean.setR_Serve(rs.getString("R_Serve"));
                    balanceBean.setR_PrintOK(rs.getString("R_PrintOK"));
                    balanceBean.setR_KicOK(rs.getString("R_KicOK"));
                    balanceBean.setStkCode(rs.getString("StkCode"));
                    balanceBean.setPosStk(rs.getString("PosStk"));
                    balanceBean.setR_PrChkType(rs.getString("R_PrChkType"));
                    balanceBean.setR_PrQuan(rs.getFloat("R_PrQuan"));
                    balanceBean.setR_PrSubType(rs.getString("R_PrSubType"));
                    balanceBean.setR_PrSubCode(rs.getString("R_PrSubCode"));
                    balanceBean.setR_PrSubQuan(rs.getFloat("R_PrSubQuan"));
                    balanceBean.setR_PrSubDisc(rs.getFloat("R_PrSubDisc"));
                    balanceBean.setR_PrSubBath(rs.getFloat("R_PrSubBath"));
                    balanceBean.setR_PrSubAmt(rs.getFloat("R_PrSubAmt"));
                    balanceBean.setR_PrSubAdj(rs.getFloat("R_PrSubAdj"));
                    balanceBean.setR_PrCuDisc(rs.getFloat("R_PrCuDisc"));
                    balanceBean.setR_PrCuBath(rs.getFloat("R_PrCuBath"));
                    balanceBean.setR_PrCuAdj(rs.getFloat("R_PrCuAdj"));
                    balanceBean.setR_QuanCanDisc(rs.getFloat("R_QuanCanDisc"));
                    balanceBean.setR_Order(rs.getString("R_Order"));
                    balanceBean.setR_PItemNo(rs.getInt("R_PItemNo"));
                    balanceBean.setR_PKicQue(rs.getInt("R_PKicQue"));
                    balanceBean.setR_MemSum(rs.getString("R_MemSum"));
                    balanceBean.setR_MoveItem(rs.getString("R_MoveItem"));
                    balanceBean.setR_MoveFrom(rs.getString("R_MoveFrom"));
                    balanceBean.setR_MoveUser(rs.getString("R_MoveUser"));
                    balanceBean.setR_MoveFlag(rs.getString("R_MoveFlag"));
                    balanceBean.setR_MovePrint(rs.getString("R_MovePrint"));
                    balanceBean.setR_Pause(rs.getString("R_Pause"));
                    balanceBean.setR_TotalServiceAmt(0);
                    try {
                        balanceBean.setR_Date(rs.getDate("R_Date"));
                    } catch (SQLException e) {
                        System.out.println("Error Date: " + e.getMessage());
                    }

                    beanData.add(balanceBean);
                }
            }
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }

        return beanData;
    }

    public ArrayList<BalanceBean> getAllBalanceHold(String table) {
        ArrayList<BalanceBean> beanData = new ArrayList<>();
        table = ThaiUtil.Unicode2ASCII(charEngCheck.charEngCheck(table));
        MySQLConnect mysql = new MySQLConnect();
        try {
            mysql.open();
            String sql = "select *,sum(r_total) SR_Total,sum(r_quan) SR_Quan "
                    + "from balance where r_table='" + table + "' and r_void<>'V' and r_pause='P' "
                    + "group by r_plucode order by r_etd,r_index";
//            String sql = "select *,sum(R_Quan) SR_Quan "
//                    + "from balance "
//                    + "where R_Table='" + table + "' "
//                    + "and r_pause='P' "
//                    + "and r_plucode<>'8899' "
//                    + "and r_void<>'V' "
//                    + "or R_Table='" + table + "' "
//                    + "and r_pause='Y' "
//                    + "and r_plucode<>'8899' "
//                    + "and r_void<>'V' "
//                    + "group by R_PluCode "
//                    + "order by r_type,r_etd,r_index";
//            String sqlOpenFood = "select *,R_Quan SR_Quan "
//                    + "from balance "
//                    + "where R_Table='" + table + "' "
//                    + "and r_pause='P' "
//                    + "and r_plucode='8899' "
//                    + "and r_void<>'V' "
//                    + "or R_Table='" + table + "' "
//                    + "and r_pause='Y' "
//                    + "and r_plucode='8899' "
//                    + "and r_void<>'V' "
//                    + "order by r_type,r_etd,r_index";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                BalanceBean balanceBean = new BalanceBean();
                balanceBean.setR_Index(rs.getString("R_Index"));
                balanceBean.setR_Table(rs.getString("R_Table"));
                balanceBean.setR_Time(rs.getString("R_Time"));
                balanceBean.setMacno(rs.getString("Macno"));
                balanceBean.setCashier(rs.getString("Cashier"));
                balanceBean.setR_Emp(rs.getString("R_Emp"));
                balanceBean.setR_PluCode(rs.getString("R_PluCode"));
                balanceBean.setR_PName(ThaiUtil.ASCII2Unicode(rs.getString("R_PName")));
                balanceBean.setR_Unit(rs.getString("R_Unit"));
                balanceBean.setR_Group(rs.getString("R_Group"));
                balanceBean.setR_Status(rs.getString("R_Status"));
                balanceBean.setR_Normal(rs.getString("R_Normal"));
                balanceBean.setR_Discount(rs.getString("R_Discount"));
                balanceBean.setR_Service(rs.getString("R_Service"));
                balanceBean.setR_Stock(rs.getString("R_Stock"));
                balanceBean.setR_Set(rs.getString("R_Set"));
                balanceBean.setR_Vat(rs.getString("R_Vat"));
                balanceBean.setR_Type(rs.getString("R_Type"));
                balanceBean.setR_ETD(rs.getString("R_ETD"));
                balanceBean.setR_Quan(rs.getFloat("SR_Quan"));
                balanceBean.setR_Price(rs.getFloat("R_Price"));
                balanceBean.setR_Total(rs.getFloat("r_total"));
//                balanceBean.setR_Total(rs.getFloat("R_Total") * rs.getFloat("SR_Quan"));
                balanceBean.setR_PrType(rs.getString("R_PrType"));
                balanceBean.setR_PrCode(rs.getString("R_PrCode"));
                balanceBean.setR_PrDisc(rs.getFloat("R_PrDisc"));
                balanceBean.setR_PrBath(rs.getFloat("R_PrBath"));
                balanceBean.setR_PrAmt(rs.getFloat("R_PrAmt"));
                balanceBean.setR_DiscBath(rs.getFloat("R_DiscBath"));
                balanceBean.setR_PrCuType(rs.getString("R_PrCuType"));
                balanceBean.setR_PrCuQuan(rs.getFloat("R_PrCuQuan"));
                balanceBean.setR_PrCuAmt(rs.getFloat("R_PrCuAmt"));
                balanceBean.setR_Redule(rs.getFloat("R_Redule"));
                balanceBean.setR_Kic(rs.getString("R_Kic"));
                balanceBean.setR_KicPrint(rs.getString("R_KicPrint"));
                balanceBean.setR_Void(rs.getString("R_Void"));
                balanceBean.setR_VoidUser(rs.getString("R_VoidUser"));
                balanceBean.setR_VoidTime(rs.getString("R_VoidTime"));
                balanceBean.setR_Opt1(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt1")));
                balanceBean.setR_Opt2(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt2")));
                balanceBean.setR_Opt3(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt3")));
                balanceBean.setR_Opt4(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt4")));
                balanceBean.setR_Opt5(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt5")));
                balanceBean.setR_Opt6(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt6")));
                balanceBean.setR_Opt7(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt7")));
                balanceBean.setR_Opt8(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt8")));
                balanceBean.setR_Opt9(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt9")));
                balanceBean.setR_PrCuCode(rs.getString("R_PrCuCode"));
                balanceBean.setR_Serve(rs.getString("R_Serve"));
                balanceBean.setR_PrintOK(rs.getString("R_PrintOK"));
                balanceBean.setR_KicOK(rs.getString("R_KicOK"));
                balanceBean.setStkCode(rs.getString("StkCode"));
                balanceBean.setPosStk(rs.getString("PosStk"));
                balanceBean.setR_PrChkType(rs.getString("R_PrChkType"));
                balanceBean.setR_PrQuan(rs.getFloat("R_PrQuan"));
                balanceBean.setR_PrSubType(rs.getString("R_PrSubType"));
                balanceBean.setR_PrSubCode(rs.getString("R_PrSubCode"));
                balanceBean.setR_PrSubQuan(rs.getFloat("R_PrSubQuan"));
                balanceBean.setR_PrSubDisc(rs.getFloat("R_PrSubDisc"));
                balanceBean.setR_PrSubBath(rs.getFloat("R_PrSubBath"));
                balanceBean.setR_PrSubAmt(rs.getFloat("R_PrSubAmt"));
                balanceBean.setR_PrSubAdj(rs.getFloat("R_PrSubAdj"));
                balanceBean.setR_PrCuDisc(rs.getFloat("R_PrCuDisc"));
                balanceBean.setR_PrCuBath(rs.getFloat("R_PrCuBath"));
                balanceBean.setR_PrCuAdj(rs.getFloat("R_PrCuAdj"));
                balanceBean.setR_QuanCanDisc(rs.getFloat("R_QuanCanDisc"));
                balanceBean.setR_Order(rs.getString("R_Order"));
                balanceBean.setR_PItemNo(rs.getInt("R_PItemNo"));
                balanceBean.setR_PKicQue(rs.getInt("R_PKicQue"));
                balanceBean.setR_MemSum(rs.getString("R_MemSum"));
                balanceBean.setR_MoveItem(rs.getString("R_MoveItem"));
                balanceBean.setR_MoveFrom(rs.getString("R_MoveFrom"));
                balanceBean.setR_MoveUser(rs.getString("R_MoveUser"));
                balanceBean.setR_MoveFlag(rs.getString("R_MoveFlag"));
                balanceBean.setR_MovePrint(rs.getString("R_MovePrint"));
                balanceBean.setR_Pause(rs.getString("R_Pause"));
                balanceBean.setR_TotalServiceAmt(0);
                balanceBean.setR_LinkIndex("r_linkindex");
                if (balanceBean.getR_LinkIndex().equals("null")) {
                    balanceBean.setR_LinkIndex("");
                }
                balanceBean.setR_LinkIndex("r_index");
                try {
                    balanceBean.setR_Date(rs.getDate("R_Date"));
                } catch (SQLException e) {
                    System.out.println("Error Date: " + e.getMessage());
                }

                beanData.add(balanceBean);
            }
//            ResultSet rs1 = c.getConnection().createStatement().executeQuery(sqlOpenFood);
//            while (rs1.next()) {
//                BalanceBean balanceBean = new BalanceBean();
//                balanceBean.setR_Index(rs1.getString("R_Index"));
//                balanceBean.setR_Table(rs1.getString("R_Table"));
//                balanceBean.setR_Time(rs1.getString("R_Time"));
//                balanceBean.setMacno(rs1.getString("Macno"));
//                balanceBean.setCashier(rs1.getString("Cashier"));
//                balanceBean.setR_Emp(rs1.getString("R_Emp"));
//                balanceBean.setR_PluCode(rs1.getString("R_PluCode"));
//                balanceBean.setR_PName(ThaiUtil.ASCII2Unicode(rs1.getString("R_PName")));
//                balanceBean.setR_Unit(rs1.getString("R_Unit"));
//                balanceBean.setR_Group(rs1.getString("R_Group"));
//                balanceBean.setR_Status(rs1.getString("R_Status"));
//                balanceBean.setR_Normal(rs1.getString("R_Normal"));
//                balanceBean.setR_Discount(rs1.getString("R_Discount"));
//                balanceBean.setR_Service(rs1.getString("R_Service"));
//                balanceBean.setR_Stock(rs1.getString("R_Stock"));
//                balanceBean.setR_Set(rs1.getString("R_Set"));
//                balanceBean.setR_Vat(rs1.getString("R_Vat"));
//                balanceBean.setR_Type(rs1.getString("R_Type"));
//                balanceBean.setR_ETD(rs1.getString("R_ETD"));
//                balanceBean.setR_Quan(rs1.getFloat("SR_Quan"));
//                balanceBean.setR_Price(rs1.getFloat("R_Price"));
//                balanceBean.setR_Total(rs1.getFloat("R_Total"));
//                balanceBean.setR_PrType(rs1.getString("R_PrType"));
//                balanceBean.setR_PrCode(rs1.getString("R_PrCode"));
//                balanceBean.setR_PrDisc(rs1.getFloat("R_PrDisc"));
//                balanceBean.setR_PrBath(rs1.getFloat("R_PrBath"));
//                balanceBean.setR_PrAmt(rs1.getFloat("R_PrAmt"));
//                balanceBean.setR_DiscBath(rs1.getFloat("R_DiscBath"));
//                balanceBean.setR_PrCuType(rs1.getString("R_PrCuType"));
//                balanceBean.setR_PrCuQuan(rs1.getFloat("R_PrCuQuan"));
//                balanceBean.setR_PrCuAmt(rs1.getFloat("R_PrCuAmt"));
//                balanceBean.setR_Redule(rs1.getFloat("R_Redule"));
//                balanceBean.setR_Kic(rs1.getString("R_Kic"));
//                balanceBean.setR_KicPrint(rs1.getString("R_KicPrint"));
//                balanceBean.setR_Void(rs1.getString("R_Void"));
//                balanceBean.setR_VoidUser(rs1.getString("R_VoidUser"));
//                balanceBean.setR_VoidTime(rs1.getString("R_VoidTime"));
//                balanceBean.setR_Opt1(ThaiUtil.ASCII2Unicode(rs1.getString("R_Opt1")));
//                balanceBean.setR_Opt2(ThaiUtil.ASCII2Unicode(rs1.getString("R_Opt2")));
//                balanceBean.setR_Opt3(ThaiUtil.ASCII2Unicode(rs1.getString("R_Opt3")));
//                balanceBean.setR_Opt4(ThaiUtil.ASCII2Unicode(rs1.getString("R_Opt4")));
//                balanceBean.setR_Opt5(ThaiUtil.ASCII2Unicode(rs1.getString("R_Opt5")));
//                balanceBean.setR_Opt6(ThaiUtil.ASCII2Unicode(rs1.getString("R_Opt6")));
//                balanceBean.setR_Opt7(ThaiUtil.ASCII2Unicode(rs1.getString("R_Opt7")));
//                balanceBean.setR_Opt8(ThaiUtil.ASCII2Unicode(rs1.getString("R_Opt8")));
//                balanceBean.setR_Opt9(ThaiUtil.ASCII2Unicode(rs1.getString("R_Opt9")));
//                balanceBean.setR_PrCuCode(rs1.getString("R_PrCuCode"));
//                balanceBean.setR_Serve(rs1.getString("R_Serve"));
//                balanceBean.setR_PrintOK(rs1.getString("R_PrintOK"));
//                balanceBean.setR_KicOK(rs1.getString("R_KicOK"));
//                balanceBean.setStkCode(rs1.getString("StkCode"));
//                balanceBean.setPosStk(rs1.getString("PosStk"));
//                balanceBean.setR_PrChkType(rs1.getString("R_PrChkType"));
//                balanceBean.setR_PrQuan(rs1.getFloat("R_PrQuan"));
//                balanceBean.setR_PrSubType(rs1.getString("R_PrSubType"));
//                balanceBean.setR_PrSubCode(rs1.getString("R_PrSubCode"));
//                balanceBean.setR_PrSubQuan(rs1.getFloat("R_PrSubQuan"));
//                balanceBean.setR_PrSubDisc(rs1.getFloat("R_PrSubDisc"));
//                balanceBean.setR_PrSubBath(rs1.getFloat("R_PrSubBath"));
//                balanceBean.setR_PrSubAmt(rs1.getFloat("R_PrSubAmt"));
//                balanceBean.setR_PrSubAdj(rs1.getFloat("R_PrSubAdj"));
//                balanceBean.setR_PrCuDisc(rs1.getFloat("R_PrCuDisc"));
//                balanceBean.setR_PrCuBath(rs1.getFloat("R_PrCuBath"));
//                balanceBean.setR_PrCuAdj(rs1.getFloat("R_PrCuAdj"));
//                balanceBean.setR_QuanCanDisc(rs1.getFloat("R_QuanCanDisc"));
//                balanceBean.setR_Order(rs1.getString("R_Order"));
//                balanceBean.setR_PItemNo(rs1.getInt("R_PItemNo"));
//                balanceBean.setR_PKicQue(rs1.getInt("R_PKicQue"));
//                balanceBean.setR_MemSum(rs1.getString("R_MemSum"));
//                balanceBean.setR_MoveItem(rs1.getString("R_MoveItem"));
//                balanceBean.setR_MoveFrom(rs1.getString("R_MoveFrom"));
//                balanceBean.setR_MoveUser(rs1.getString("R_MoveUser"));
//                balanceBean.setR_MoveFlag(rs1.getString("R_MoveFlag"));
//                balanceBean.setR_MovePrint(rs1.getString("R_MovePrint"));
//                balanceBean.setR_Pause(rs1.getString("R_Pause"));
//                balanceBean.setR_TotalServiceAmt(0);
//                balanceBean.setR_LinkIndex("r_linkindex");
//                if (balanceBean.getR_LinkIndex().equals("null")) {
//                    balanceBean.setR_LinkIndex("");
//                }
//                balanceBean.setR_LinkIndex("r_index");
//                try {
//                    balanceBean.setR_Date(rs1.getDate("R_Date"));
//                } catch (SQLException e) {
//                    System.out.println("Error Date: " + e.getMessage());
//                }
//
//                beanData.add(balanceBean);
//            }
            rs.close();
            stmt.close();
//            rs1.close();
//
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }

        return beanData;

    }

    public ArrayList<BalanceBean> getAllBalanceNew(String table) {
        ArrayList<BalanceBean> beanData = new ArrayList<>();
        MySQLConnect mysql = new MySQLConnect();
        try {
            mysql.open();
            table = ThaiUtil.Unicode2ASCII(charEngCheck.charEngCheck(table));
            String sql = "select * from balance "
                    + "where R_Table='" + table + "' "
                    + "and r_pause='' "
                    + "and r_kicprint<>'P'"
                    + " and r_void<>'V' "
                    + "order by R_Index";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                BalanceBean balanceBean = new BalanceBean();
                balanceBean.setR_Index(rs.getString("R_Index"));
                balanceBean.setR_Table(rs.getString("R_Table"));
                balanceBean.setR_Time(rs.getString("R_Time"));
                balanceBean.setMacno(rs.getString("Macno"));
                balanceBean.setCashier(rs.getString("Cashier"));
                balanceBean.setR_Emp(rs.getString("R_Emp"));
                balanceBean.setR_PluCode(rs.getString("R_PluCode"));
                balanceBean.setR_PName(ThaiUtil.ASCII2Unicode(rs.getString("R_PName")));
                balanceBean.setR_Unit(rs.getString("R_Unit"));
                balanceBean.setR_Group(rs.getString("R_Group"));
                balanceBean.setR_Status(rs.getString("R_Status"));
                balanceBean.setR_Normal(rs.getString("R_Normal"));
                balanceBean.setR_Discount(rs.getString("R_Discount"));
                balanceBean.setR_Service(rs.getString("R_Service"));
                balanceBean.setR_Stock(rs.getString("R_Stock"));
                balanceBean.setR_Set(rs.getString("R_Set"));
                balanceBean.setR_Vat(rs.getString("R_Vat"));
                balanceBean.setR_Type(rs.getString("R_Type"));
                balanceBean.setR_ETD(rs.getString("R_ETD"));
                balanceBean.setR_Quan(rs.getFloat("R_Quan"));
                balanceBean.setR_Price(rs.getFloat("R_Price"));
                balanceBean.setR_Total(rs.getFloat("R_Total"));
                balanceBean.setR_PrType(rs.getString("R_PrType"));
                balanceBean.setR_PrCode(rs.getString("R_PrCode"));
                balanceBean.setR_PrDisc(rs.getFloat("R_PrDisc"));
                balanceBean.setR_PrBath(rs.getFloat("R_PrBath"));
                balanceBean.setR_PrAmt(rs.getFloat("R_PrAmt"));
                balanceBean.setR_DiscBath(rs.getFloat("R_DiscBath"));
                balanceBean.setR_PrCuType(rs.getString("R_PrCuType"));
                balanceBean.setR_PrCuQuan(rs.getFloat("R_PrCuQuan"));
                balanceBean.setR_PrCuAmt(rs.getFloat("R_PrCuAmt"));
                balanceBean.setR_Redule(rs.getFloat("R_Redule"));
                balanceBean.setR_Kic(rs.getString("R_Kic"));
                balanceBean.setR_KicPrint(rs.getString("R_KicPrint"));
                balanceBean.setR_Void(rs.getString("R_Void"));
                balanceBean.setR_VoidUser(rs.getString("R_VoidUser"));
                balanceBean.setR_VoidTime(rs.getString("R_VoidTime"));
                balanceBean.setR_Opt1(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt1")));
                balanceBean.setR_Opt2(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt2")));
                balanceBean.setR_Opt3(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt3")));
                balanceBean.setR_Opt4(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt4")));
                balanceBean.setR_Opt5(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt5")));
                balanceBean.setR_Opt6(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt6")));
                balanceBean.setR_Opt7(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt7")));
                balanceBean.setR_Opt8(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt8")));
                balanceBean.setR_Opt9(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt9")));
                balanceBean.setR_PrCuCode(rs.getString("R_PrCuCode"));
                balanceBean.setR_Serve(rs.getString("R_Serve"));
                balanceBean.setR_PrintOK(rs.getString("R_PrintOK"));
                balanceBean.setR_KicOK(rs.getString("R_KicOK"));
                balanceBean.setStkCode(rs.getString("StkCode"));
                balanceBean.setPosStk(rs.getString("PosStk"));
                balanceBean.setR_PrChkType(rs.getString("R_PrChkType"));
                balanceBean.setR_PrQuan(rs.getFloat("R_PrQuan"));
                balanceBean.setR_PrSubType(rs.getString("R_PrSubType"));
                balanceBean.setR_PrSubCode(rs.getString("R_PrSubCode"));
                balanceBean.setR_PrSubQuan(rs.getFloat("R_PrSubQuan"));
                balanceBean.setR_PrSubDisc(rs.getFloat("R_PrSubDisc"));
                balanceBean.setR_PrSubBath(rs.getFloat("R_PrSubBath"));
                balanceBean.setR_PrSubAmt(rs.getFloat("R_PrSubAmt"));
                balanceBean.setR_PrSubAdj(rs.getFloat("R_PrSubAdj"));
                balanceBean.setR_PrCuDisc(rs.getFloat("R_PrCuDisc"));
                balanceBean.setR_PrCuBath(rs.getFloat("R_PrCuBath"));
                balanceBean.setR_PrCuAdj(rs.getFloat("R_PrCuAdj"));
                balanceBean.setR_QuanCanDisc(rs.getFloat("R_QuanCanDisc"));
                balanceBean.setR_Order(rs.getString("R_Order"));
                balanceBean.setR_PItemNo(rs.getInt("R_PItemNo"));
                balanceBean.setR_PKicQue(rs.getInt("R_PKicQue"));
                balanceBean.setR_MemSum(rs.getString("R_MemSum"));
                balanceBean.setR_MoveItem(rs.getString("R_MoveItem"));
                balanceBean.setR_MoveFrom(rs.getString("R_MoveFrom"));
                balanceBean.setR_MoveUser(rs.getString("R_MoveUser"));
                balanceBean.setR_MoveFlag(rs.getString("R_MoveFlag"));
                balanceBean.setR_MovePrint(rs.getString("R_MovePrint"));
                balanceBean.setR_Pause(rs.getString("R_Pause"));
                balanceBean.setR_TotalServiceAmt(0);
                balanceBean.setR_LinkIndex("R_LinkIndex");
                if (balanceBean.getR_LinkIndex().equals("null")) {
                    balanceBean.setR_LinkIndex("");
                }
                balanceBean.setR_LinkIndex("r_index");
                try {
                    balanceBean.setR_Date(rs.getDate("R_Date"));
                } catch (SQLException e) {
                    System.out.println("Error Date: " + e.getMessage());
                }

                beanData.add(balanceBean);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }

        return beanData;
    }

    public ArrayList<BalanceBean> getAllBalanceAllField(String table) {
        ArrayList<BalanceBean> beanData = new ArrayList<>();
        MySQLConnect mysql = new MySQLConnect();
        try {
            mysql.open();
            table = ThaiUtil.Unicode2ASCII(charEngCheck.charEngCheck(table));
            String sql = "select * from balance "
                    + "where R_Table='" + table + "' order by R_Index";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                BalanceBean balanceBean = new BalanceBean();
                balanceBean.setR_Index(rs.getString("R_Index"));
                balanceBean.setR_Table(rs.getString("R_Table"));
                balanceBean.setR_Time(rs.getString("R_Time"));
                balanceBean.setMacno(rs.getString("Macno"));
                balanceBean.setCashier(rs.getString("Cashier"));
                balanceBean.setR_Emp(rs.getString("R_Emp"));
                balanceBean.setR_PluCode(rs.getString("R_PluCode"));
                balanceBean.setR_PName(ThaiUtil.ASCII2Unicode(rs.getString("R_PName")));
                balanceBean.setR_Unit(rs.getString("R_Unit"));
                balanceBean.setR_Group(rs.getString("R_Group"));
                balanceBean.setR_Status(rs.getString("R_Status"));
                balanceBean.setR_Normal(rs.getString("R_Normal"));
                balanceBean.setR_Discount(rs.getString("R_Discount"));
                balanceBean.setR_Service(rs.getString("R_Service"));
                balanceBean.setR_Stock(rs.getString("R_Stock"));
                balanceBean.setR_Set(rs.getString("R_Set"));
                balanceBean.setR_Vat(rs.getString("R_Vat"));
                balanceBean.setR_Type(rs.getString("R_Type"));
                balanceBean.setR_ETD(rs.getString("R_ETD"));
                balanceBean.setR_Quan(rs.getFloat("R_Quan"));
                balanceBean.setR_Price(rs.getFloat("R_Price"));
                balanceBean.setR_Total(rs.getFloat("R_Total"));
                balanceBean.setR_PrType(rs.getString("R_PrType"));
                balanceBean.setR_PrCode(rs.getString("R_PrCode"));
                balanceBean.setR_PrDisc(rs.getFloat("R_PrDisc"));
                balanceBean.setR_PrBath(rs.getFloat("R_PrBath"));
                balanceBean.setR_PrAmt(rs.getFloat("R_PrAmt"));
                balanceBean.setR_DiscBath(rs.getFloat("R_DiscBath"));
                balanceBean.setR_PrCuType(rs.getString("R_PrCuType"));
                balanceBean.setR_PrCuQuan(rs.getFloat("R_PrCuQuan"));
                balanceBean.setR_PrCuAmt(rs.getFloat("R_PrCuAmt"));
                balanceBean.setR_Redule(rs.getFloat("R_Redule"));
                balanceBean.setR_Kic(rs.getString("R_Kic"));
                balanceBean.setR_KicPrint(rs.getString("R_KicPrint"));
                balanceBean.setR_Void(rs.getString("R_Void"));
                balanceBean.setR_VoidUser(rs.getString("R_VoidUser"));
                balanceBean.setR_VoidTime(rs.getString("R_VoidTime"));
                balanceBean.setR_Opt1(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt1")));
                balanceBean.setR_Opt2(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt2")));
                balanceBean.setR_Opt3(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt3")));
                balanceBean.setR_Opt4(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt4")));
                balanceBean.setR_Opt5(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt5")));
                balanceBean.setR_Opt6(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt6")));
                balanceBean.setR_Opt7(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt7")));
                balanceBean.setR_Opt8(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt8")));
                balanceBean.setR_Opt9(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt9")));
                balanceBean.setR_PrCuCode(rs.getString("R_PrCuCode"));
                balanceBean.setR_Serve(rs.getString("R_Serve"));
                balanceBean.setR_PrintOK(rs.getString("R_PrintOK"));
                balanceBean.setR_KicOK(rs.getString("R_KicOK"));
                balanceBean.setStkCode(rs.getString("StkCode"));
                balanceBean.setPosStk(rs.getString("PosStk"));
                balanceBean.setR_PrChkType(rs.getString("R_PrChkType"));
                balanceBean.setR_PrQuan(rs.getFloat("R_PrQuan"));
                balanceBean.setR_PrSubType(rs.getString("R_PrSubType"));
                balanceBean.setR_PrSubCode(rs.getString("R_PrSubCode"));
                balanceBean.setR_PrSubQuan(rs.getFloat("R_PrSubQuan"));
                balanceBean.setR_PrSubDisc(rs.getFloat("R_PrSubDisc"));
                balanceBean.setR_PrSubBath(rs.getFloat("R_PrSubBath"));
                balanceBean.setR_PrSubAmt(rs.getFloat("R_PrSubAmt"));
                balanceBean.setR_PrSubAdj(rs.getFloat("R_PrSubAdj"));
                balanceBean.setR_PrCuDisc(rs.getFloat("R_PrCuDisc"));
                balanceBean.setR_PrCuBath(rs.getFloat("R_PrCuBath"));
                balanceBean.setR_PrCuAdj(rs.getFloat("R_PrCuAdj"));
                balanceBean.setR_QuanCanDisc(rs.getFloat("R_QuanCanDisc"));
                balanceBean.setR_Order(rs.getString("R_Order"));
                balanceBean.setR_PItemNo(rs.getInt("R_PItemNo"));
                balanceBean.setR_PKicQue(rs.getInt("R_PKicQue"));
                balanceBean.setR_MemSum(rs.getString("R_MemSum"));
                balanceBean.setR_MoveItem(rs.getString("R_MoveItem"));
                balanceBean.setR_MoveFrom(rs.getString("R_MoveFrom"));
                balanceBean.setR_MoveUser(rs.getString("R_MoveUser"));
                balanceBean.setR_MoveFlag(rs.getString("R_MoveFlag"));
                balanceBean.setR_MovePrint(rs.getString("R_MovePrint"));
                balanceBean.setR_Pause(rs.getString("R_Pause"));
                balanceBean.setR_TotalServiceAmt(0);
                balanceBean.setR_LinkIndex("R_LinkIndex");
                if (balanceBean.getR_LinkIndex().equals("null")) {
                    balanceBean.setR_LinkIndex("");
                }
                balanceBean.setR_LinkIndex("r_index");
                try {
                    balanceBean.setR_Date(rs.getDate("R_Date"));
                } catch (SQLException e) {
                    System.out.println("Error Date: " + e.getMessage());
                }

                beanData.add(balanceBean);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }

        return beanData;
    }

    public boolean checkQuantity(String R_Table, String PCode, double R_Quan) {
        MySQLConnect mysql = new MySQLConnect();
        try {
            mysql.open();
            String sql = "select sum(R_Quan) R_Quan "
                    + "from balance "
                    + "where R_PluCode='" + PCode + "' "
                    + "and R_Table='" + ThaiUtil.Unicode2ASCII(charEngCheck.charEngCheck(R_Table)) + "' "
                    + "and R_Void<>'V' "
                    + "group by R_PluCode";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            double quan = R_Quan;
            if (rs.next()) {
                quan = rs.getDouble("R_Quan");
                quan += R_Quan;
            }
            rs.close();
            stmt.close();
            return quan >= 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            mysql.close();
        }
    }

    public boolean saveBalance(String table, String pluCode, double quan, String username, String[] opt, String etd, String emp, double price, String MACNO) {
        try {
            ProductControl pc = new ProductControl();
            BalanceBean bill = new BalanceBean();
            BranchBean branchBean = new BranchBean();
            branchBean = BranchControl.getData();
            int R_KicItemNo = branchBean.getKicItemNo();
            R_KicItemNo++;
            //### Save to balance ###
            bill.setR_ETD(etd);//set default E
            if (etd.equals("S")) {
                bill.setR_ETD("E");
            }
            bill.setR_Table(charEngCheck.charEngCheck(table));
            bill.setR_Emp(emp);
            bill.setR_PluCode(pluCode);
            bill.setR_Quan(quan);
            bill.setR_PrQuan(0);
            bill.setMacno(MACNO);

            /*สำหรับคำนวณโปรโมชัน*/
            bill.setR_PrType("");//set default ''
            bill.setR_PrCode("");//set default ''
            bill.setR_PrDisc(0);
            bill.setR_PrBath(0);
            bill.setR_PrAmt(0);
            bill.setR_PrCuType("");//set default ''
            bill.setR_PrCuCode("");
            bill.setR_PrChkType("");
            bill.setR_PrSubType("");
            bill.setR_PrSubCode("");

            /*Add New Field*/
            bill.setR_KicPrint("");//Default เป็น P ไว้ก่อนเน้ออ
            if(etd.equals("S")){
                bill.setR_KicPrint("P");
            }
            bill.setR_PItemNo(R_KicItemNo);
            bill.setR_Void("");
            bill.setR_Serve("");
            bill.setR_PrintOK("Y");
            bill.setPosStk("0");
            bill.setR_QuanCanDisc(quan);
            bill.setR_Order("1");
            bill.setR_MemSum("N");
            bill.setR_MoveItem("N");
            bill.setR_MovePrint("N");
            bill.setR_Pause("");
            //bill.setR_CardPay("N");
            bill.setR_Pickup("P");
            bill.setR_KicOK("");

            bill.setR_CallWait("");
            bill.setR_VoidPause("");

            bill.setR_Opt1(ThaiUtil.Unicode2ASCII(opt[0]));
            bill.setR_Opt2(ThaiUtil.Unicode2ASCII(opt[1]));
            bill.setR_Opt3(ThaiUtil.Unicode2ASCII(opt[2]));
            bill.setR_Opt4(ThaiUtil.Unicode2ASCII(opt[3]));
            bill.setR_Opt5(ThaiUtil.Unicode2ASCII(opt[4]));
            bill.setR_Opt6(ThaiUtil.Unicode2ASCII(opt[5]));
            bill.setR_Opt7(ThaiUtil.Unicode2ASCII(opt[6]));
            bill.setR_Opt8(ThaiUtil.Unicode2ASCII(opt[7]));
            bill.setR_Opt9(ThaiUtil.Unicode2ASCII(opt[8]));

            bill.setR_MoveFrom("");
            bill.setR_MoveUser("");

            bill.setCashier(username);

            bill.setR_Status("Y");
            bill.setR_Void("");
            bill.setR_VoidUser("");
            bill.setR_VoidTime("");
            bill.setR_MoveFlag("0");

            ProductBean product = pc.getData(bill.getR_PluCode());
            bill.setR_Index(getIndexBalance(bill.getR_Table()));//หมายเลขโต๊ะ/ลำดับที่รายการอาหาร
            bill.setR_Table(bill.getR_Table());//หมายเลขโต๊ะ
            //bean.setR_Date(Calendar.getInstance().getTime());
            //bean.setR_Time("16:04:14");
            bill.setMacno(bill.getMacno());//หมายเลขเครื่อง
            bill.setCashier(bill.getCashier());//รหัส login
            bill.setR_Emp(bill.getR_Emp());//รหัสบริกร
            bill.setR_PluCode(product.getPCode());//รหัสสินค้า
            bill.setR_PName(ThaiUtil.Unicode2ASCII(product.getPDesc()));//ชื่อสินค้า
            bill.setR_Unit(ThaiUtil.Unicode2ASCII(product.getPUnit1()));//หน่วยนับ
            bill.setR_Group(product.getPGroup());//กลุ่มสินค้า
            bill.setR_Status(product.getPStatus());//การคิดราคาขาย (P=อัตโนมัติ(Plu), D=กำหนดเองตามป้าย(SDP), T=คำนวณตามเวลา(Time))
            bill.setR_Normal(product.getPNormal());//สถานะสินค้า (N=Normal, C=Consign, S=Special)
            bill.setR_Discount(product.getPDiscount());//สามารถให้ส่วนลดได้
            bill.setR_Service(product.getPService());//คิดค่าบริการ
            bill.setR_Stock(product.getPStock());//จัดทำสต็อกหรือไม่
            bill.setR_Set(product.getPSet());//เป็นสินค้าชุดหรือไม่
            bill.setR_Vat(product.getPVat());//คิดภาษีหรือไม่
            bill.setR_Type(product.getPType());//ประเภทสินค้า
            //bill.setR_ETD(bill.getR_ETD());//EatIn,TakeAway,Delivery
            bill.setR_Quan(bill.getR_Quan());//จำนวนที่สั่ง
            bill.setR_Price(price);//ราคาสินค้า
            bill.setR_Total(bill.getR_Quan() * bill.getR_Price());//ราคารวม            
            bill.setR_Kic(product.getPKic());
            bill.setR_LinkIndex(bill.getR_Index());
            //Add new Field
            String stkCode = "";
            MySQLConnect mysql = new MySQLConnect();

            try {

                mysql.open();
                String sql = "select StkCode from stockfile;";
                ResultSet rrr = mysql.getConnection().createStatement().executeQuery(sql);
                if (rrr.next()) {
                    stkCode = rrr.getString("StkCode");
                }
                rrr.close();

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } finally {
                mysql.close();
            }
            bill.setStkCode(stkCode);
            bill.setPosStk(product.getPOSStk());

            //copy balance bean
            balanceCurrent = bill;
            BranchControl.updateKicItemNo();
            return saveBillNoSQL(bill);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    private boolean saveBillNoSQL(BalanceBean bill) {
        MySQLConnect mysql = new MySQLConnect();
        try {
            String productName = bill.getR_PName();
            try {
                productName = productName.replace("\'", "");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            try {
                mysql.open();
                String sqlInsert = "insert into balance "
                        + "(R_Index,R_Table,R_Date,R_Time,Macno,"
                        + "Cashier,R_Emp,R_PluCode,R_PName,R_Unit,"
                        + "R_Group,R_Status,R_Normal,R_Discount,R_Service,"
                        + "R_Stock,R_Set,R_Vat,R_Type,R_ETD,R_Quan,R_Price,"
                        + "R_Total,R_PrType,R_PrCode,R_PrDisc,R_PrBath,"
                        + "R_PrAmt,R_DiscBath,R_PrCuType,R_PrCuQuan,R_PrCuAmt,"
                        + "R_Redule,R_Kic,R_KicPrint,R_Void,R_VoidUser,R_VoidTime,"
                        + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,R_Opt7,R_Opt8,"
                        + "R_Opt9,R_PrCuCode,R_Serve,R_PrintOK,R_KicOK,StkCode,"
                        + "PosStk,R_PrChkType,R_PrQuan,R_PrSubType,R_PrSubCode,"
                        + "R_PrSubQuan,R_PrSubDisc,R_PrSubBath,R_PrSubAmt,R_PrSubAdj,"
                        + "R_PrCuDisc,R_PrCuBath,R_PrCuAdj,R_QuanCanDisc,R_Order,R_PItemNo,"
                        + "R_PKicQue,R_MemSum,R_MoveItem,R_MoveFrom,R_MoveUser,R_MoveFlag,"
                        + "R_MovePrint,R_Pause,R_SPIndex,R_linkIndex) "
                        + "values('" + ThaiUtil.Unicode2ASCII(bill.getR_Index()) + "','" + ThaiUtil.Unicode2ASCII(charEngCheck.charEngCheck(bill.getR_Table())) + "',CURDATE(),CURTIME(),'"
                        + bill.getMacno() + "','" + bill.getCashier() + "','"
                        + bill.getR_Emp() + "','" + bill.getR_PluCode() + "','" + ThaiUtil.Unicode2ASCII(productName) + "','"
                        + bill.getR_Unit() + "','" + bill.getR_Group() + "','" + bill.getR_Status() + "','"
                        + bill.getR_Normal() + "','" + bill.getR_Discount() + "','" + bill.getR_Service() + "','"
                        + bill.getR_Stock() + "','" + bill.getR_Set() + "','" + bill.getR_Vat() + "','"
                        + bill.getR_Type() + "','" + bill.getR_ETD() + "','" + bill.getR_Quan() + "','"
                        + bill.getR_Price() + "','" + bill.getR_Total() + "','" + bill.getR_PrType() + "','"
                        + bill.getR_PrCode() + "','" + bill.getR_PrDisc() + "','" + bill.getR_PrBath() + "','"
                        + bill.getR_PrAmt() + "','" + bill.getR_DiscBath() + "','" + bill.getR_PrCuType() + "','"
                        + bill.getR_PrCuQuan() + "','" + bill.getR_PrCuAmt() + "','" + bill.getR_Redule() + "','"
                        + bill.getR_Kic() + "','" + bill.getR_KicPrint() + "','" + bill.getR_Void() + "','"
                        + bill.getR_VoidUser() + "','" + bill.getR_VoidTime() + "','" + bill.getR_Opt1() + "','"
                        + bill.getR_Opt2() + "','" + bill.getR_Opt3() + "','" + bill.getR_Opt4() + "','"
                        + bill.getR_Opt5() + "','" + bill.getR_Opt6() + "','" + bill.getR_Opt7() + "','"
                        + bill.getR_Opt8() + "','" + bill.getR_Opt9() + "','" + bill.getR_PrCuCode() + "','"
                        + bill.getR_Serve() + "','" + bill.getR_PrintOK() + "','" + bill.getR_KicOK() + "','"
                        + bill.getStkCode() + "','" + bill.getPosStk() + "','" + bill.getR_PrChkType() + "','"
                        + bill.getR_PrQuan() + "','" + bill.getR_PrSubType() + "','" + bill.getR_PrSubCode() + "','"
                        + bill.getR_PrSubQuan() + "','" + bill.getR_PrSubDisc() + "','" + bill.getR_PrSubBath() + "','"
                        + bill.getR_PrSubAmt() + "','" + bill.getR_PrSubAdj() + "','" + bill.getR_PrCuDisc() + "','"
                        + bill.getR_PrCuBath() + "','" + bill.getR_PrCuAdj() + "','" + bill.getR_QuanCanDisc() + "','"
                        + bill.getR_Order() + "','" + bill.getR_PItemNo() + "','" + bill.getR_PKicQue() + "','"
                        + bill.getR_MemSum() + "','" + bill.getR_MoveItem() + "','" + bill.getR_MoveFrom() + "','"
                        + bill.getR_MoveUser() + "','" + bill.getR_MoveFlag() + "','" + bill.getR_MovePrint() + "','"
                        + bill.getR_Pause() + "','" + ThaiUtil.Unicode2ASCII(bill.getR_Index()) + "','" + ThaiUtil.Unicode2ASCII(bill.getR_LinkIndex()) + "')";

                mysql.getConnection().createStatement().executeUpdate(sqlInsert);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            try {
                mysql.open();
                String sss = "update balance set "
                        + "trantype='PDA' "
                        + "where R_Index='" + ThaiUtil.Unicode2ASCII(bill.getR_Index()) + "' and "
                        + "R_Table='" + ThaiUtil.Unicode2ASCII(charEngCheck.charEngCheck(bill.getR_Table())) + "' and "
                        + "R_PluCode='" + bill.getR_PluCode() + "'";
                mysql.getConnection().createStatement().executeUpdate(sss);

                String updateEmpTableFile = "update tablefile set tuser='" + bill.getR_Emp() + "' where tcode='"
                        + ThaiUtil.Unicode2ASCII(charEngCheck.charEngCheck(bill.getR_Table())) + "'";
                mysql.getConnection().createStatement().executeUpdate(updateEmpTableFile);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } finally {
                mysql.close();
            }

            //update service
            BalanceControl bc = new BalanceControl();
            bc.updateProSerTable(ThaiUtil.Unicode2ASCII(charEngCheck.charEngCheck(bill.getR_Table())), null);

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String getIndexBalance(String R_Table) {
        String index = R_Table + "/001";
        MySQLConnect mysql = new MySQLConnect();
        try {
            String sql = "select max(R_Index) R_Index "
                    + "from balance "
                    + "where R_Table = '" + ThaiUtil.Unicode2ASCII(R_Table) + "' "
                    + "order by R_Index";
            mysql.open();
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
            String R_Index;
            boolean notfound = false;
            while (rs.next()) {
                notfound = true;
                R_Index = ThaiUtil.Unicode2ASCII(rs.getString("R_Index"));

                if (R_Index == null) {
                    break;
                }

                String[] data = R_Index.split("/");
                int id;
                try {
                    id = Integer.parseInt(data[1]);
                    id = id + 1;
                } catch (NumberFormatException e) {
                    id = 1;
                }

                if (id < 10) {
                    index = charEngCheck.charEngCheck(R_Table) + "/00" + id;
                } else if (id < 100) {
                    index = charEngCheck.charEngCheck(R_Table) + "/0" + id;
                } else if (id < 1000) {
                    index = charEngCheck.charEngCheck(R_Table) + "/" + id;
                }
            }
            rs.close();
            if (!notfound) {
                //not found data
                index = charEngCheck.charEngCheck(R_Table) + "/001";
            }
        } catch (Exception e) {
            index = charEngCheck.charEngCheck(R_Table) + "/001";
        } finally {
            mysql.close();
        }

        return index;
    }
//    public String getIndexBalance(String R_Table) {
//        DecimalFormat df = new DecimalFormat("000");
//        String index = R_Table;
//        try {
//            String sql = "select count(r_index) newIndex from balance where r_table='" + ThaiUtil.Unicode2ASCII(charEngCheck.charEngCheck(R_Table)) + "';";
//            c.open();
//            ResultSet rs = c.getConnection().createStatement().executeQuery(sql);
//            int R_Index = 0;
////            boolean notfound = false;
//            if (rs.next()) {
////                notfound = true;
//                R_Index = rs.getInt("newIndex");
//                R_Index++;
//                index = ThaiUtil.Unicode2ASCII(charEngCheck.charEngCheck(R_Table) + "/" + df.format(R_Index));
////                if (R_Index == null) {
////                    break;
////                }
////
////                String[] data = R_Index.split("/");
////                int id;
////                try {
////                    id = Integer.parseInt(data[1]);
////                    id = id + 1;
////                } catch (NumberFormatException e) {
////                    id = 1;
////                }
////
////                if (id < 10) {
////                    index = charEngCheck.charEngCheck(R_Table) + "/00" + id;
////                } else if (id < 100) {
////                    index = charEngCheck.charEngCheck(R_Table) + "/0" + id;
////                } else if (id < 1000) {
////                    index = charEngCheck.charEngCheck(R_Table) + "/" + id;
////                }
//            }
//            rs.close();
////            if (!notfound) {
////                //not found data
////                index = charEngCheck.charEngCheck(R_Table) + "/001";
////            }
//
//            c.close();
//        } catch (Exception e) {
////            index = charEngCheck.charEngCheck(R_Table) + "/001";
//            e.printStackTrace();
//        }
//
//        return index;
//    }
////    public String getIndexBalance(String R_Table) {
////        String index = R_Table + "/001";
////        try {
////            String sql = "select max(R_Index) R_Index "
////                    + "from balance "
////                    + "where R_Table = '" + ThaiUtil.Unicode2ASCII(R_Table) + "' "
////                    + "order by R_Index";
////            ResultSet rs = c.getConnection().createStatement().executeQuery(sql);
////            String R_Index;
////            boolean notfound = false;
////            while (rs.next()) {
////                notfound = true;
////                R_Index = ThaiUtil.Unicode2ASCII(rs.getString("R_Index"));
////
////                if (R_Index == null) {
////                    break;
////                }
////
////                String[] data = R_Index.split("/");
////                int id;
////                try {
////                    id = Integer.parseInt(data[1]);
////                    id = id + 1;
////                } catch (NumberFormatException e) {
////                    id = 1;
////                }
////
////                if (id < 10) {
////                    index = charEngCheck.charEngCheck(R_Table) + "/00" + id;
////                } else if (id < 100) {
////                    index = charEngCheck.charEngCheck(R_Table) + "/0" + id;
////                } else if (id < 1000) {
////                    index = charEngCheck.charEngCheck(R_Table) + "/" + id;
////                }
////            }
////            rs.close();
////            if (!notfound) {
////                //not found data
////                index = charEngCheck.charEngCheck(R_Table) + "/001";
////            }
////
////            c.close();
////        } catch (Exception e) {
////            index = charEngCheck.charEngCheck(R_Table) + "/001";
////        }
////
////        return index;
////    }

    public boolean deleteItemBill(String index) {
        boolean isDel = false;
        MySQLConnect mysql = new MySQLConnect();
        try {
            mysql.open();
            String sql = "delete from balance "
                    + "where R_Index='" + index + "' "
                    + "and R_Pause<>'P' ";
            isDel = mysql.getConnection().createStatement().executeUpdate(sql) > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }

        return isDel;
    }

    public BalanceBean getBalanceIndex(String Table, String R_Index) {
        BalanceBean balanceBean = null;
        MySQLConnect mysql = new MySQLConnect();
        try {
            mysql.open();
            String sql = "select * from balance "
                    + "where R_Table='" + Table + "' "
                    + "and R_Index='" + R_Index + "'";
//            String sql = "select * from balance "
//                    + "where R_Table='" + ThaiUtil.Unicode2ASCII(charEngCheck.charEngCheck(Table)) + "' "
//                    + "and R_Index='" + ThaiUtil.Unicode2ASCII(charEngCheck.charEngCheck(R_Index)) + "'";
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                balanceBean = new BalanceBean();
                balanceBean.setR_Index(rs.getString("R_Index"));
                balanceBean.setR_Table(rs.getString("R_Table"));
                balanceBean.setR_Time(rs.getString("R_Time"));
                balanceBean.setMacno(rs.getString("Macno"));
                balanceBean.setCashier(rs.getString("Cashier"));
                balanceBean.setR_Emp(rs.getString("R_Emp"));
                balanceBean.setR_PluCode(rs.getString("R_PluCode"));
                balanceBean.setR_PName(ThaiUtil.ASCII2Unicode(rs.getString("R_PName")));
                balanceBean.setR_Unit(ThaiUtil.ASCII2Unicode(rs.getString("R_Unit")));
                balanceBean.setR_Group(rs.getString("R_Group"));
                balanceBean.setR_Status(rs.getString("R_Status"));
                balanceBean.setR_Normal(rs.getString("R_Normal"));
                balanceBean.setR_Discount(rs.getString("R_Discount"));
                balanceBean.setR_Service(rs.getString("R_Service"));
                balanceBean.setR_Stock(rs.getString("R_Stock"));
                balanceBean.setR_Set(rs.getString("R_Set"));
                balanceBean.setR_Vat(rs.getString("R_Vat"));
                balanceBean.setR_Type(rs.getString("R_Type"));
                balanceBean.setR_ETD(rs.getString("R_ETD"));
                balanceBean.setR_Quan(rs.getFloat("R_Quan"));
                balanceBean.setR_Price(rs.getFloat("R_Price"));
                balanceBean.setR_Total(rs.getFloat("R_Total"));
                String R_PrType = rs.getString("R_PrType");
                if (R_PrType == null) {
                    R_PrType = "";
                }
                balanceBean.setR_PrType(R_PrType);
                balanceBean.setR_PrCode(rs.getString("R_PrCode"));
                balanceBean.setR_PrDisc(rs.getFloat("R_PrDisc"));
                balanceBean.setR_PrBath(rs.getFloat("R_PrBath"));
                balanceBean.setR_PrAmt(rs.getFloat("R_PrAmt"));
                balanceBean.setR_DiscBath(rs.getFloat("R_DiscBath"));
                balanceBean.setR_PrCuType(rs.getString("R_PrCuType"));
                balanceBean.setR_PrCuQuan(rs.getFloat("R_PrCuQuan"));
                balanceBean.setR_PrCuAmt(rs.getFloat("R_PrCuAmt"));
                balanceBean.setR_Redule(rs.getFloat("R_Redule"));
                balanceBean.setR_Kic(rs.getString("R_Kic"));
                balanceBean.setR_KicPrint(rs.getString("R_KicPrint"));
                balanceBean.setR_Void(rs.getString("R_Void"));
                balanceBean.setR_VoidUser(rs.getString("R_VoidUser"));
                balanceBean.setR_VoidTime(rs.getString("R_VoidTime"));
                balanceBean.setR_Opt1(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt1")));
                balanceBean.setR_Opt2(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt2")));
                balanceBean.setR_Opt3(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt3")));
                balanceBean.setR_Opt4(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt4")));
                balanceBean.setR_Opt5(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt5")));
                balanceBean.setR_Opt6(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt6")));
                balanceBean.setR_Opt7(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt7")));
                balanceBean.setR_Opt8(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt8")));
                balanceBean.setR_Opt9(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt9")));
                balanceBean.setR_PrCuCode(rs.getString("R_PrCuCode"));
                balanceBean.setR_Serve(rs.getString("R_Serve"));
                balanceBean.setR_PrintOK(rs.getString("R_PrintOK"));
                balanceBean.setR_KicOK(rs.getString("R_KicOK"));
                balanceBean.setStkCode(rs.getString("StkCode"));
                balanceBean.setPosStk(rs.getString("PosStk"));
                balanceBean.setR_PrChkType(rs.getString("R_PrChkType"));
                balanceBean.setR_PrQuan(rs.getFloat("R_PrQuan"));
                balanceBean.setR_PrSubType(rs.getString("R_PrSubType"));
                balanceBean.setR_PrSubCode(rs.getString("R_PrSubCode"));
                balanceBean.setR_PrSubQuan(rs.getFloat("R_PrSubQuan"));
                balanceBean.setR_PrSubDisc(rs.getFloat("R_PrSubDisc"));
                balanceBean.setR_PrSubBath(rs.getFloat("R_PrSubBath"));
                balanceBean.setR_PrSubAmt(rs.getFloat("R_PrSubAmt"));
                balanceBean.setR_PrSubAdj(rs.getFloat("R_PrSubAdj"));
                balanceBean.setR_PrCuDisc(rs.getFloat("R_PrCuDisc"));
                balanceBean.setR_PrCuBath(rs.getFloat("R_PrCuBath"));
                balanceBean.setR_PrCuAdj(rs.getFloat("R_PrCuAdj"));
                balanceBean.setR_QuanCanDisc(rs.getFloat("R_QuanCanDisc"));
                balanceBean.setR_Order(rs.getString("R_Order"));
                balanceBean.setR_PItemNo(rs.getInt("R_PItemNo"));
                balanceBean.setR_PKicQue(rs.getInt("R_PKicQue"));
                balanceBean.setR_MemSum(rs.getString("R_MemSum"));
                balanceBean.setR_MoveItem(rs.getString("R_MoveItem"));
                balanceBean.setR_MoveFrom(rs.getString("R_MoveFrom"));
                balanceBean.setR_MoveUser(rs.getString("R_MoveUser"));
                balanceBean.setR_MoveFlag(rs.getString("R_MoveFlag"));
                balanceBean.setR_MovePrint(rs.getString("R_MovePrint"));
                balanceBean.setR_Pause(rs.getString("R_Pause"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        } finally {
            mysql.close();
        }

        return balanceBean;
    }

    public void updateProSerTable(String table, MemberBean memberBean) {
        //คำนวณโปรโมชัน + ค่าบริการ และคำนวณภาษีมูลค่าเพิ่ม
        //PromotionControl proControl = new PromotionControl();
        //proControl.updatePromotion(table);

        // ให้ส่วนลดสมาชิก
        //MemberControl memControl = new MemberControl();
        //memControl.updateMemberDiscount(table, memberBean);
        //คำนวณส่วนลด
        //DiscountControl discControl = new DiscountControl();
        //discControl.updateDiscount(table);
        //คำนวณค่าบริการ + คำนวณภาษีมูลค่าเพิ่ม
        ServiceControl serviceControl = new ServiceControl();
        serviceControl.updateService(ThaiUtil.Unicode2ASCII(charEngCheck.charEngCheck(table)));

        //คำนวณ VAT
        VatControl vatControl = new VatControl(ThaiUtil.Unicode2ASCII(charEngCheck.charEngCheck(table)));
        vatControl.updateVat();
    }

    public ArrayList<BalanceBean> getAllBalancePromotion(String table) {
        ArrayList<BalanceBean> beanData = new ArrayList<BalanceBean>();
        MySQLConnect mysql = new MySQLConnect();
        try {
            mysql.open();
            table = ThaiUtil.Unicode2ASCII(charEngCheck.charEngCheck(table));
            String sql = "select * from balance "
                    + "where R_Table='" + table + "' "
                    + "and R_Discount='Y' "
                    + "and R_Void <> 'V' "
                    + "group by R_PluCode "
                    + "order by R_PluCode, R_Index";
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                BalanceBean balanceBean = new BalanceBean();
                balanceBean.setR_Index(rs.getString("R_Index"));
                balanceBean.setR_Table(rs.getString("R_Table"));
                balanceBean.setR_Time(rs.getString("R_Time"));
                balanceBean.setMacno(rs.getString("Macno"));
                balanceBean.setCashier(rs.getString("Cashier"));
                balanceBean.setR_Emp(rs.getString("R_Emp"));
                balanceBean.setR_PluCode(rs.getString("R_PluCode"));
                balanceBean.setR_PName(ThaiUtil.ASCII2Unicode(rs.getString("R_PName")));
                balanceBean.setR_Unit(rs.getString("R_Unit"));
                balanceBean.setR_Group(rs.getString("R_Group"));
                balanceBean.setR_Status(rs.getString("R_Status"));
                balanceBean.setR_Normal(rs.getString("R_Normal"));
                balanceBean.setR_Discount(rs.getString("R_Discount"));
                balanceBean.setR_Service(rs.getString("R_Service"));
                balanceBean.setR_Stock(rs.getString("R_Stock"));
                balanceBean.setR_Set(rs.getString("R_Set"));
                balanceBean.setR_Vat(rs.getString("R_Vat"));
                balanceBean.setR_Type(rs.getString("R_Type"));
                balanceBean.setR_ETD(rs.getString("R_ETD"));
                balanceBean.setR_Quan(rs.getFloat("R_Quan"));
                balanceBean.setR_Price(rs.getFloat("R_Price"));
                balanceBean.setR_Total(rs.getFloat("R_Total"));
                String R_PrType = rs.getString("R_PrType");
                if (R_PrType == null) {
                    R_PrType = "";
                }
                balanceBean.setR_PrType(R_PrType);

                balanceBean.setR_PrCode(rs.getString("R_PrCode"));
                balanceBean.setR_PrDisc(rs.getFloat("R_PrDisc"));
                balanceBean.setR_PrBath(rs.getFloat("R_PrBath"));
                balanceBean.setR_PrAmt(rs.getFloat("R_PrAmt"));
                balanceBean.setR_DiscBath(rs.getFloat("R_DiscBath"));
                balanceBean.setR_PrCuType(rs.getString("R_PrCuType"));
                balanceBean.setR_PrCuQuan(rs.getFloat("R_PrCuQuan"));
                balanceBean.setR_PrCuAmt(rs.getFloat("R_PrCuAmt"));
                balanceBean.setR_Redule(rs.getFloat("R_Redule"));
                balanceBean.setR_Kic(rs.getString("R_Kic"));
                balanceBean.setR_KicPrint(rs.getString("R_KicPrint"));
                balanceBean.setR_Void(rs.getString("R_Void"));
                balanceBean.setR_VoidUser(rs.getString("R_VoidUser"));
                balanceBean.setR_VoidTime(rs.getString("R_VoidTime"));
                balanceBean.setR_Opt1(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt1")));
                balanceBean.setR_Opt2(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt2")));
                balanceBean.setR_Opt3(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt3")));
                balanceBean.setR_Opt4(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt4")));
                balanceBean.setR_Opt5(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt5")));
                balanceBean.setR_Opt6(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt6")));
                balanceBean.setR_Opt7(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt7")));
                balanceBean.setR_Opt8(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt8")));
                balanceBean.setR_Opt9(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt9")));
                balanceBean.setR_PrCuCode(rs.getString("R_PrCuCode"));
                balanceBean.setR_Serve(rs.getString("R_Serve"));
                balanceBean.setR_PrintOK(rs.getString("R_PrintOK"));
                balanceBean.setR_KicOK(rs.getString("R_KicOK"));
                balanceBean.setStkCode(rs.getString("StkCode"));
                balanceBean.setPosStk(rs.getString("PosStk"));
                balanceBean.setR_PrChkType(rs.getString("R_PrChkType"));
                balanceBean.setR_PrQuan(rs.getFloat("R_PrQuan"));
                balanceBean.setR_PrSubType(rs.getString("R_PrSubType"));
                balanceBean.setR_PrSubCode(rs.getString("R_PrSubCode"));
                balanceBean.setR_PrSubQuan(rs.getFloat("R_PrSubQuan"));
                balanceBean.setR_PrSubDisc(rs.getFloat("R_PrSubDisc"));
                balanceBean.setR_PrSubBath(rs.getFloat("R_PrSubBath"));
                balanceBean.setR_PrSubAmt(rs.getFloat("R_PrSubAmt"));
                balanceBean.setR_PrSubAdj(rs.getFloat("R_PrSubAdj"));
                balanceBean.setR_PrCuDisc(rs.getFloat("R_PrCuDisc"));
                balanceBean.setR_PrCuBath(rs.getFloat("R_PrCuBath"));
                balanceBean.setR_PrCuAdj(rs.getFloat("R_PrCuAdj"));
                balanceBean.setR_QuanCanDisc(rs.getFloat("R_QuanCanDisc"));
                balanceBean.setR_Order(rs.getString("R_Order"));
                balanceBean.setR_PItemNo(rs.getInt("R_PItemNo"));
                balanceBean.setR_PKicQue(rs.getInt("R_PKicQue"));
                balanceBean.setR_MemSum(rs.getString("R_MemSum"));
                balanceBean.setR_MoveItem(rs.getString("R_MoveItem"));
                balanceBean.setR_MoveFrom(rs.getString("R_MoveFrom"));
                balanceBean.setR_MoveUser(rs.getString("R_MoveUser"));
                balanceBean.setR_MoveFlag(rs.getString("R_MoveFlag"));
                balanceBean.setR_MovePrint(rs.getString("R_MovePrint"));
                balanceBean.setR_Pause(rs.getString("R_Pause"));

                try {
                    balanceBean.setR_Date(rs.getDate("R_Date"));
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }

                beanData.add(balanceBean);
            }
            rs.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }

        return beanData;
    }

    public String getTableSum(String tableNo) {
        String data = "0,0";
        String total = "0.00";
        MySQLConnect mysql = new MySQLConnect();
        try {
            mysql.open();
            String sql = "select NetTotal "
                    + "from tablefile "
                    + "where tcode='" + ThaiUtil.Unicode2ASCII(charEngCheck.charEngCheck(tableNo)) + "';";
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                total = "" + rs.getDouble("NetTotal");
            }

            rs.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            String sql = "select count(R_Table) C "
                    + "from balance "
                    + "where r_pause='' "
                    + "and r_table='" + ThaiUtil.Unicode2ASCII(charEngCheck.charEngCheck(tableNo)) + "' "
                    + "and r_kicprint<>'P' ;";
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                data = rs.getString("C") + "," + total;
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }
        if (Double.parseDouble(total) < 0.1) {
            PKicTran.clearKicTran(tableNo);
        }
        return data;
    }
}
