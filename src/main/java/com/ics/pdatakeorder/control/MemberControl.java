package com.ics.pdatakeorder.control;

import com.ics.pdatakeorder.model.MemberBean;
import com.ics.pdatakeorder.model.BalanceBean;
import com.ics.pdatakeorder.db.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberControl {
    private final MySQLConnect mysql = new MySQLConnect();

    public void updateMemberDiscount(String table, MemberBean memberBean) {
        String strDisc = "";
        if (memberBean != null) {
            if (!memberBean.getMember_DiscountRate().equals("")) {
                strDisc = memberBean.getMember_DiscountRate();
            }
        }
        
        try {
            mysql.open();
            String sql = "select sum(R_PrSubAmt) as MemDiscount "
                    + "from balance where r_table='" + table + "' "
                    + "order by R_Index;";
            try (ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql)) {
                if (rs.next()) {
                    String upd = "update tablefile set "
                            + "MemDiscAmt='" + rs.getDouble("MemDiscount") + "',"
                            + "MemDisc='" + strDisc + "' "
                            + "where tcode='" + table + "'";
                    mysql.getConnection().createStatement().executeUpdate(upd);
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public void updateMemberAllBalance(String table, MemberBean memberBean) {
        try {
            /*
            R_PrSubType = -M
            R_PrSubCode = MEM
            R_PrSubQuan = 1
            R_PrSubDisc = 5 (เปอร์เซ็นต์การลด)
            R_PrSubBath = 0
            R_PrSubAmt = 4.75 (5% ของราคาสินค้า)
            R_QuanCanDisc = 0
             */
            
            mysql.open();
            String sql = "select * from balance "
                    + "where R_Table='" + table + "' "
                    + "and R_Discount='Y' "
                    + "order by R_Index;";
            try (ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql)) {
                while (rs.next()) {
                    BalanceBean balance = new BalanceBean();
                    
                    // คำนวณหาว่าลดเท่าไหร่
                    String[] subPercent = memberBean.getMember_DiscountRate().split("/");
                    int Percent = 0;
                    
                    if (subPercent.length == 3) {
                        String R_Normal = rs.getString("R_Normal");
                        if (R_Normal == null) {
                            R_Normal = "";
                        }
                        switch (R_Normal) {
                            case "N":
                                Percent = Integer.parseInt(subPercent[0].trim());
                                break;
                            case "C":
                                Percent = Integer.parseInt(subPercent[1].trim());
                                break;
                            case "S":
                                Percent = Integer.parseInt(subPercent[2].trim());
                                break;
                            default:
                                break;
                        }
                    }
                    
                    balance.setR_PrSubAmt((rs.getDouble("R_Total") * Percent) / 100);
                    balance.setR_QuanCanDisc(0);// if member default 0
                    
                    String sqlUpd = "update balance set "
                            + "R_PrSubType='-M',"
                            + "R_PrSubCode='MEM',"
                            + "R_PrSubQuan='" + rs.getDouble("R_Quan") + "',"
                            + "R_PrSubDisc='" + Percent + "',"
                            + "R_PrSubBath='0',"
                            + "R_PrSubAmt='" + ((rs.getDouble("R_Total") * Percent) / 100) + "',"
                            + "R_QuanCanDisc='0' "
                            + "where R_Index='" + rs.getString("R_Index") + "' "
                            + "and R_Table='" + rs.getString("R_Table") + "'";
                    mysql.getConnection().createStatement().executeUpdate(sqlUpd);
                }
            }
        } catch (NumberFormatException | SQLException e) {
            System.err.println(e.getMessage());
        }finally{
            mysql.close();
        }
    }
}
