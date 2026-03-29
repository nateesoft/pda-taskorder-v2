package com.ics.pdatakeorder.control;

import com.ics.pdatakeorder.model.POSHWSetup;
import com.ics.pdatakeorder.model.POSConfigSetup;
import com.ics.pdatakeorder.db.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ics.pdatakeorder.util.ThaiUtil;

public class PosControl {

    private final MySQLConnect mysql = new MySQLConnect();
    private POSConfigSetup posConfigSetup = null;
    private POSHWSetup poshwsetup = null;

    public POSConfigSetup getData() {
        if (posConfigSetup != null) {
            return posConfigSetup;
        }

        posConfigSetup = new POSConfigSetup();

        try {
            mysql.open();
            String sql = "select * from posconfigsetup";
            try (ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql)) {
                if (rs.next()) {
                    posConfigSetup.setP_Terminal(rs.getString("P_Terminal"));
                    posConfigSetup.setP_Vat(rs.getFloat("P_Vat"));
                    posConfigSetup.setP_Service(rs.getFloat("P_Service"));
                    posConfigSetup.setP_ServiceType(rs.getString("P_ServiceType"));
                    posConfigSetup.setP_VatPrn(rs.getString("P_VatPrn"));
                    posConfigSetup.setP_VatType(rs.getString("P_VatType"));
                    posConfigSetup.setP_BillCopy(rs.getInt("P_BillCopy"));
                    posConfigSetup.setP_BillCopyOne(rs.getString("P_BillCopyOne"));
                    posConfigSetup.setP_DefaultSaleType(rs.getString("P_DefaultSaleType"));
                    posConfigSetup.setP_EmpUse(rs.getString("P_EmpUse"));
                    posConfigSetup.setP_CodePrn(rs.getString("P_CodePrn"));
                    posConfigSetup.setP_CheckBillBeforCash(rs.getString("P_CheckBillBeforCash"));
                    posConfigSetup.setP_PrintDetailOnRecp(rs.getString("P_PrintDetailOnRecp"));
                    posConfigSetup.setP_PrintSum(rs.getString("P_PrintSum"));
                    posConfigSetup.setP_PrintRecpMessage(ThaiUtil.ASCII2Unicode(rs.getString("P_PrintRecpMessage")));
                    posConfigSetup.setP_MemDisc(rs.getString("P_MemDisc"));
                    posConfigSetup.setP_MemDiscChk(rs.getString("P_MemDiscChk"));
                    posConfigSetup.setP_MemDiscGet(rs.getString("P_MemDiscGet"));
                    posConfigSetup.setP_MemDiscMax(rs.getString("P_MemDiscMax"));
                    posConfigSetup.setP_FastDisc(rs.getString("P_FastDisc"));
                    posConfigSetup.setP_FastDiscChk(rs.getString("P_FastDiscChk"));
                    posConfigSetup.setP_FastDiscGet(rs.getString("P_FastDiscGet"));
                    posConfigSetup.setP_FastDiscMax(rs.getString("P_FastDiscMax"));
                    posConfigSetup.setP_EmpDisc(rs.getString("P_EmpDisc"));
                    posConfigSetup.setP_EmpDiscChk(rs.getString("P_EmpDiscChk"));
                    posConfigSetup.setP_EmpDiscGet(rs.getString("P_EmpDiscGet"));
                    posConfigSetup.setP_EmpDiscMax(rs.getString("P_EmpDiscMax"));
                    posConfigSetup.setP_TrainDisc(rs.getString("P_TrainDisc"));
                    posConfigSetup.setP_TrainDiscChk(rs.getString("P_TrainDiscChk"));
                    posConfigSetup.setP_TrainDiscGet(rs.getString("P_TrainDiscGet"));
                    posConfigSetup.setP_TrainDiscMax(rs.getString("P_TrainDiscMax"));
                    posConfigSetup.setP_SubDisc(rs.getString("P_SubDisc"));
                    posConfigSetup.setP_SubDiscChk(rs.getString("P_SubDiscChk"));
                    posConfigSetup.setP_SubDiscGet(rs.getString("P_SubDiscGet"));
                    posConfigSetup.setP_SubDiscMax(rs.getString("P_SubDiscMax"));
                    posConfigSetup.setP_DiscBathChk(rs.getString("P_DiscBathChk"));
                    posConfigSetup.setP_DiscBathMax(rs.getInt("P_DiscBathMax"));
                    posConfigSetup.setP_PromotionChk(rs.getString("P_PromotionChk"));
                    posConfigSetup.setP_SpacialChk(rs.getString("P_SpacialChk"));
                    posConfigSetup.setP_DiscRound(rs.getString("P_DiscRound"));
                    posConfigSetup.setP_ServiceRound(rs.getString("P_ServiceRound"));
                    posConfigSetup.setP_SerChkBySaleType(rs.getString("P_SerChkBySaleType"));
                    posConfigSetup.setP_DiscChkBySaleType(rs.getString("P_DiscChkBySaleType"));
                    posConfigSetup.setP_MemberSystem(rs.getString("P_MemberSystem"));
                    posConfigSetup.setKicSum(rs.getString("KicSum"));
                    posConfigSetup.setKicCopy(rs.getString("KicCopy"));
                    posConfigSetup.setP_PrintByItemType(rs.getString("P_PrintByItemType"));
                    posConfigSetup.setP_PrintTotalSumItemType(rs.getString("P_PrintTotalSumItemType"));
                    posConfigSetup.setP_PrintTotalSumNormalType(rs.getString("P_PrintTotalSumNormalType"));
                    posConfigSetup.setP_PrintTotalSumGroup(rs.getString("P_PrintTotalSumGroup"));
                    posConfigSetup.setWTime(rs.getString("WTime"));
                    posConfigSetup.setLTime(rs.getString("LTime"));
                    posConfigSetup.setP_PrintProductValue(rs.getString("P_PrintProductValue"));
                    posConfigSetup.setP_LimitTime(rs.getInt("P_LimitTime"));
                    posConfigSetup.setP_RefreshTime(rs.getInt("P_RefreshTime"));
                    posConfigSetup.setP_SaleDecimal(rs.getString("P_SaleDecimal"));
                    posConfigSetup.setP_PayBahtRound(rs.getString("P_PayBahtRound"));
                }
            }
            mysql.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            mysql.close();
        }

        return posConfigSetup;
    }

    public POSHWSetup getData(String macno) {
        if (poshwsetup != null) {
            return poshwsetup = new POSHWSetup();
        }
        try {
            mysql.open();
            String sql = "select * from poshwsetup where terminal='" + macno + "'";
            try (ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql)) {
                poshwsetup = new POSHWSetup();
                if (rs.next()) {
                    poshwsetup.setTerminal(rs.getString("Terminal"));
                    poshwsetup.setOnAct(rs.getString("OnAct"));
                    poshwsetup.setMacNo(macno);
                    poshwsetup.setReceNo1(rs.getDouble("ReceNo1"));
                    poshwsetup.setSaleType(rs.getString("SaleType"));
                    poshwsetup.setTStock(rs.getString("TStock"));
                    poshwsetup.setTSone(rs.getString("TSone"));
                    poshwsetup.setHeading1(rs.getString("Heading1"));
                    poshwsetup.setHeading2(rs.getString("Heading2"));
                    poshwsetup.setHeading3(rs.getString("Heading3"));
                    poshwsetup.setHeading4(rs.getString("Heading4"));
                    poshwsetup.setFootting1(rs.getString("Footting1"));
                    poshwsetup.setFootting2(rs.getString("Footting2"));
                    poshwsetup.setDRPort(rs.getString("DRPort"));
                    poshwsetup.setDRType(rs.getString("DRType"));
                    poshwsetup.setDRCOM(rs.getString("DRCOM"));
                    poshwsetup.setDISPort(rs.getString("DISPort"));
                    poshwsetup.setDISType(rs.getString("DISType"));
                    poshwsetup.setDISCOM(rs.getString("DISCOM"));
                    poshwsetup.setPRNPort(rs.getString("PRNPort"));
                    poshwsetup.setPRNTYPE(rs.getString("PRNTYPE"));
                    poshwsetup.setPRNCOM(rs.getString("PRNCOM"));
                    poshwsetup.setPRNThaiLevel(rs.getString("PRNThaiLevel"));
                    poshwsetup.setKIC1Port(rs.getString("KIC1Port"));
                    poshwsetup.setKIC1Type(rs.getString("KIC1Type"));
                    poshwsetup.setKIC1Com(rs.getString("KIC1Com"));
                    poshwsetup.setKIC1ThaiLevel(rs.getString("KIC1ThaiLevel"));
                    poshwsetup.setKIC2Port(rs.getString("KIC2Port"));
                    poshwsetup.setKIC2Type(rs.getString("KIC2Type"));
                    poshwsetup.setKIC2Com(rs.getString("KIC2Com"));
                    poshwsetup.setKIC2ThaiLevel(rs.getString("KIC2ThaiLevel"));
                    poshwsetup.setKIC3Port(rs.getString("KIC3Port"));
                    poshwsetup.setKIC3Type(rs.getString("KIC3Type"));
                    poshwsetup.setKIC3Com(rs.getString("KIC3Com"));
                    poshwsetup.setKIC3ThaiLevel(rs.getString("KIC3ThaiLevel"));
                    poshwsetup.setKIC4Port(rs.getString("KIC4Port"));
                    poshwsetup.setKIC4Type(rs.getString("KIC4Type"));
                    poshwsetup.setKIC4Com(rs.getString("KIC4Com"));
                    poshwsetup.setKIC4ThaiLevel(rs.getString("KIC4ThaiLevel"));
                    poshwsetup.setEJounal(rs.getString("EJounal"));
                    poshwsetup.setEJDailyPath(rs.getString("EJDailyPath"));
                    poshwsetup.setEJBackupPath(rs.getString("EJBackupPath"));
                    poshwsetup.setPrnRate(rs.getInt("PrnRate"));
                    poshwsetup.setDrRate(rs.getInt("DrRate"));
                    poshwsetup.setDisRate(rs.getInt("DisRate"));
                    poshwsetup.setEDCPort(rs.getString("EDCPort"));
                    poshwsetup.setSMPBank(rs.getString("SMPBank"));
                    poshwsetup.setMenuItemList(rs.getString("MenuItemList"));
                    poshwsetup.setUseFloorPlan(rs.getString("UseFloorPlan"));
                    poshwsetup.setTakeOrderChk(rs.getString("TakeOrderChk"));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }

        return poshwsetup;
    }

    private String[] getETDPW(String data) {
        String[] d = data.split("/");
        String str = "";
        if (d[0].equals("Y")) {
            str += "E,";
        }
        if (d[1].equals("Y")) {
            str += "T,";
        }
        if (d[2].equals("Y")) {
            str += "D,";
        }

        if (d[3].equals("Y")) {
            str += "P,";
        }

        if (d[4].equals("Y")) {
            str += "W,";
        }

        return str.split(",");
    }

    public boolean getETDPW_Active(String ETD, String data) {
        if (ETD == null || data == null) {
            return false;
        }
        String[] d = getETDPW(data);
        for (String dd : d) {
            if (ETD.equals(dd)) {
                return true;
            }
        }

        return false;
    }

}
