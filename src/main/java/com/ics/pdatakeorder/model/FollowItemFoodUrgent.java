/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ics.pdatakeorder.model;

import com.ics.pdatakeorder.db.MySQLConnect;
import com.ics.pdatakeorder.util.ThaiUtil;

/**
 *
 * @author Administrator
 */
public class FollowItemFoodUrgent {

    public void FollowItemFoodUrgentByItem(String tableNo, String pCode, String pName, String pindex) {
        MySQLConnect mysql = new MySQLConnect();
        try {
            String sql = "update kictran set "
                    + "r_foodUrgent='Y',r_alertkitchen='N', r_urgentFoodItemName='" + ThaiUtil.Unicode2ASCII(pName) + "' "
                    + "where "
                    + "pcode='" + pCode + "' "
                    + "and pindex='" + pindex + "' "
                    + "and ptable='" + tableNo + "';";
            mysql.open();
            mysql.getConnection().createStatement().executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            mysql.close();
        }
    }
}
