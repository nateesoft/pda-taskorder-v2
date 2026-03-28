package com.ics.pdatakeorder.servlet;

import com.ics.pdatakeorder.control.BalanceControl;
import com.ics.pdatakeorder.db.MySQLConnect;
import com.ics.pdatakeorder.model.ControlMenu;
import com.ics.pdatakeorder.model.MenuDisplayItem;
import com.ics.pdatakeorder.model.MenuSetup;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Prepares all request attributes required by main.jsp so that the JSP
 * contains no Java scriptlets — only JSTL and EL expressions.
 *
 * Call {@link #prepare(HttpServletRequest, HttpSession)} in every servlet
 * that forwards to main.jsp (Login, Login2).
 */
public class MainPageHelper {

    private static final String[] PREFIXES = {"A", "B", "C", "D"};

    private MainPageHelper() {
    }

    public static void prepare(HttpServletRequest request, HttpSession session) {
        String prefix = request.getParameter("prefix");
        if (prefix == null || prefix.isEmpty()) {
            prefix = "A";
        }

        String macNo   = (String) session.getAttribute("macno");
        String tableNo = (String) session.getAttribute("tableNo");

        // Bill summary (format: "itemCount,totalAmount")
        BalanceControl bCon = new BalanceControl();
        String[] dataTable = bCon.getTableSum(tableNo).split(",");
        DecimalFormat dec = new DecimalFormat("#,##0");
        String totalBill = dec.format(Double.parseDouble(dataTable[1]));

        // Header menu tab labels
        ControlMenu cm = new ControlMenu();
        String[] hMenu = cm.getHeaderMenu();

        // Build display items — all presentation decisions made here, not in the JSP
        List<MenuSetup> rawList = cm.getDataMenu(prefix);
        List<MenuDisplayItem> menuItems = new ArrayList<>(rawList.size());

        for (int i = 0; i < rawList.size(); i++) {
            MenuSetup menu = rawList.get(i);
            MenuDisplayItem item = new MenuDisplayItem();
            item.setMenu(menu);

            boolean isLast = (i == rawList.size() - 1);

            if (isLast) {
                if (prefix.length() > 1) {
                    // Sub-menu: show red "back to main menu" button
                    item.setBgColor("#FF4000");
                    item.setBackButton(true);
                    item.setBackPrefix(prefix.substring(0, 1));
                } else {
                    // Top-level last cell: empty green placeholder
                    item.setBgColor("#33CC66");
                    menu.setShortName("");
                }
            } else if ("S".equals(menu.getCode_Type())) {
                // Group/sub-menu cell
                item.setBgColor("#33CC66");
            } else if ("P".equals(menu.getCode_Type()) && !menu.getPCode().isEmpty()) {
                // Orderable product cell
                item.setBgColor("#F2F5A9");
            } else {
                // Empty / inactive cell
                menu.setShortName("");
                item.setBgColor("#33CC66");
            }

            menuItems.add(item);
        }

        request.setAttribute("prefix",        prefix);
        request.setAttribute("macNo",         macNo);
        request.setAttribute("tableNo",       tableNo);
        request.setAttribute("billCount",     dataTable[0]);
        request.setAttribute("totalBill",     totalBill);
        request.setAttribute("headerMenu",    hMenu);
        request.setAttribute("headerPrefixes", PREFIXES);
        request.setAttribute("menuItems",     menuItems);
        request.setAttribute("fontSize",      MySQLConnect.fontSize);
    }
}
