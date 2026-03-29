package com.ics.pdatakeorder.servlet;

import com.ics.pdatakeorder.db.MySQLConnect;
import com.ics.pdatakeorder.model.BalanceBean;
import com.ics.pdatakeorder.model.OptionFile;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author nateelive
 */
@WebServlet(name = "OrderDetail", urlPatterns = {"/OrderDetail"})
public class OrderDetail extends HttpServlet {

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
        String macNo = (String) session.getAttribute("macno");
        String saleType = (String) session.getAttribute("saleType");
        if (saleType == null || saleType.isEmpty()) {
            saleType = "E";
        }

        request.setAttribute("prefix", prefix);
        request.setAttribute("macNo", macNo);
        request.setAttribute("saleType", saleType);

        BalanceBean bean = (BalanceBean) request.getAttribute("bean");
        if (macNo != null && !macNo.isEmpty() && bean != null) {
            String[] options = OptionFile.getListOption(bean.getR_Group());
            request.setAttribute("options", options);
        }

        RequestDispatcher req = request.getRequestDispatcher("/detail.jsp");
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
