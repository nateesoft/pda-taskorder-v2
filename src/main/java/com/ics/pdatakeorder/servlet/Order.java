package com.ics.pdatakeorder.servlet;

import com.ics.pdatakeorder.model.BalanceBean;
import com.ics.pdatakeorder.control.BalanceControl;
import com.ics.pdatakeorder.model.CharactorCheck;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import com.ics.pdatakeorder.util.ThaiUtil;

@WebServlet(name = "Order", urlPatterns = {"/Order"})
public class Order extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        CharactorCheck charEngCheck = new CharactorCheck();
        String tableNo = (String) session.getAttribute("tableNo");
        String R_Index = (String) request.getParameter("R_Index");

        BalanceControl bc = new BalanceControl();
        BalanceBean bean = bc.getBalanceIndex(ThaiUtil.Unicode2ASCII(charEngCheck.charEngCheck(tableNo)), ThaiUtil.Unicode2ASCII(charEngCheck.charEngCheck(R_Index)));

        request.setAttribute("bean", bean);
        RequestDispatcher req = request.getRequestDispatcher("/OrderDetail");
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
