package com.ics.pdatakeorder.model;

import com.ics.pdatakeorder.db.MySQLConnect;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.ics.pdatakeorder.util.ThaiUtil;
import java.sql.SQLException;
import java.util.List;

public class ControlMenu {

    private final MySQLConnect mysql = new MySQLConnect();

    public List<MenuSetup> getDataMenu(String prefix) {
        List<MenuSetup> listMenu = new ArrayList<>();
        MenuSetup menu;
        String search;

        for (int i = 0; i < 28; i++) {
            int index = i + 1;
            if (index < 10) {
                search = prefix + "0" + index;
            } else {
                search = prefix + index;
            }
            
            try {
                mysql.open();
                String sql = "select Code_ID, ShortName,Code_Type,PCode "
                        + "from menusetup "
                        + "where Code_ID='" + search + "'";
                try (ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql)) {
                    if (rs.next()) {
                        menu = new MenuSetup();
                        menu.setCode_ID(rs.getString("Code_ID"));
                        menu.setCode_Type(rs.getString("Code_Type"));
                        menu.setShortName(ThaiUtil.ASCII2Unicode(rs.getString("ShortName")));
                        menu.setPCode(rs.getString("PCode"));
                        
                        String test = menu.getShortName();
                        String[] t = test.split(" ");
                        String plus = "";
                        for (int aa = 0; aa < t.length; aa++) {
                            if (t[aa].equals("")) {
                                
                            } else {
                                if (aa == t.length - 1) {
                                    plus += t[aa];
                                } else {
                                    plus += t[aa] + "<br />";
                                }
                            }
                        }
                        
                        menu.setShortName(plus);
                        
                        if (!menu.getPCode().equals("")) {
                            try {
                                String sql2 = "select pcode,pstatus "
                                        + "from product "
                                        + "where pstatus='s' "
                                        + "and pcode='" + menu.getPCode() + "';";
                                ResultSet rs2 = mysql.getConnection().createStatement().executeQuery(sql2);
                                if (rs2.next()) {
                                    menu = new MenuSetup();
                                    menu.setCode_ID(search);
                                    menu.setShortName("");
                                    menu.setCode_Type("");
                                    listMenu.add(menu);
                                } else {
                                    listMenu.add(menu);
                                }
                            } catch (SQLException e) {
                                System.err.println(e.getMessage());
                            }
                        } else {
                            listMenu.add(menu);
                        }
                    } else {
                        menu = new MenuSetup();
                        menu.setCode_ID(search);
                        menu.setShortName("");
                        menu.setCode_Type("");
                        listMenu.add(menu);
                    }
                }

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } finally {
                mysql.close();
            }

        }

        return listMenu;
    }

    public List<MenuSetup> getDataMenuSearch(String search) {
        List<MenuSetup> listBean = new ArrayList<>();
        try {

            mysql.open();
            String sql = "select m.* "
                    + "from product p,menusetup m "
                    + "where p.pcode=m.pcode "
                    + "and PFix='F' "
                    + "and PStatus='P' "
                    + "and PActive='Y' "
                    + "and m.pcode<>'' "
                    + "and shortname like '%" + ThaiUtil.Unicode2ASCII(search) + "%';";
            try (ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql)) {
                while (rs.next()) {
                    MenuSetup m = new MenuSetup();
                    m.setPCode(rs.getString("PCode"));
                    m.setShortName(ThaiUtil.ASCII2Unicode(rs.getString("ShortName")));
                    
                    listBean.add(m);
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }

        return listBean;
    }

    public List<MenuSetup> getDataMenuSearchByCode(String search) {
        List<MenuSetup> listBean = new ArrayList<>();
        try {
            mysql.open();
            String sql = "select m.* "
                    + "from product p,menusetup m "
                    + "where p.pcode=m.pcode "
                    + "and PFix='F' "
                    + "and PStatus='P' "
                    + "and PActive='Y' "
                    + "and m.pcode<>'' "
                    + "and m.pcode =" + ThaiUtil.Unicode2ASCII(search) + "';";
            try (ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql)) {
                while (rs.next()) {
                    MenuSetup m = new MenuSetup();
                    m.setPCode(rs.getString("PCode"));
                    m.setShortName(ThaiUtil.ASCII2Unicode(rs.getString("ShortName")));
                    
                    listBean.add(m);
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }

        return listBean;
    }

    public String[] getHeaderMenu() {
        String hMenu = "";
        try {
            mysql.open();
            String sqlHead = "select head1, head2, head3, head4 from headmenu";
            try (ResultSet rs = mysql.getConnection().createStatement().executeQuery(sqlHead)) {
                if (rs.next()) {
                    hMenu += ThaiUtil.ASCII2Unicode(rs.getString("head1")) + ",";
                    hMenu += ThaiUtil.ASCII2Unicode(rs.getString("head2")) + ",";
                    hMenu += ThaiUtil.ASCII2Unicode(rs.getString("head3")) + ",";
                    hMenu += ThaiUtil.ASCII2Unicode(rs.getString("head4"));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            mysql.close();
        }
        return hMenu.split(",", hMenu.length());
    }

}
