package com.ics.pdatakeorder.servlet;

import com.ics.pdatakeorder.model.ControlPrintCheckBill;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author nateelive
 */
@WebServlet(name = "PrintCheckBill", urlPatterns = {"/PrintCheckBill"})
public class PrintCheckBill extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String token = request.getParameter("chkType");

        Cookie[] cookies = request.getCookies();
        String cEmpCode = "";
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("c_empcode")) {
                    cEmpCode = c.getValue();
                    break;
                }
            }
        }

        HttpSession session = request.getSession();
        String tableNo = (String) session.getAttribute("tableNo");
        String macNo = (String) session.getAttribute("macno");

        try {
            ControlPrintCheckBill cb = new ControlPrintCheckBill();
            cb.PrintCheckBill(tableNo, true, cEmpCode, token, macNo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String prefix = request.getParameter("prefix");
        if (prefix == null || prefix.isEmpty()) {
            prefix = "A";
        }

        request.setAttribute("prefix", prefix);

        RequestDispatcher req = request.getRequestDispatcher("/printCheckBill.jsp");
        req.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
