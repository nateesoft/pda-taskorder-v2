package com.ics.pdatakeorder.servlet;

import com.ics.pdatakeorder.control.BalanceControl;
import com.ics.pdatakeorder.model.BalanceBean;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author nateelive
 */
@WebServlet(name = "OrderList", urlPatterns = {"/OrderList"})
public class OrderList extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String prefix = request.getParameter("prefix");
        if (prefix == null || prefix.isEmpty()) {
            prefix = "A";
        }

        HttpSession session = request.getSession();
        String table = (String) session.getAttribute("tableNo");
        if (table == null) {
            table = "";
        }

        BalanceControl bc = new BalanceControl();
        List<BalanceBean> listBalance = bc.getAllBalanceNew(table);
        if (listBalance == null) {
            listBalance = new ArrayList<>();
        }

        request.setAttribute("prefix", prefix);
        request.setAttribute("table", table);
        request.setAttribute("listBalance", listBalance);

        RequestDispatcher req = request.getRequestDispatcher("/Order.jsp?prefix="+prefix);
        req.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
