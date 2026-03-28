package com.ics.pdatakeorder.servlet;

import com.ics.pdatakeorder.model.BalanceBean;
import com.ics.pdatakeorder.control.BalanceControl;
import com.ics.pdatakeorder.model.CharactorCheck;
import com.ics.pdatakeorder.model.PKicTran;
import com.ics.pdatakeorder.control.TableFileControl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import com.ics.pdatakeorder.util.ThaiUtil;
import java.util.List;

@WebServlet(name = "Next", urlPatterns = {"/Next"})
public class Next extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        CharactorCheck charEngCheck = new CharactorCheck();
        String tableNo = (String) session.getAttribute("tableNo");

        List<BalanceBean> bill;
        BalanceControl blControl = new BalanceControl();
        bill = blControl.getAllBalanceNew(tableNo);
        PKicTran.setPKicTran(bill, 0);

        TableFileControl tfControl = new TableFileControl();
        tfControl.updateTableHold(ThaiUtil.Unicode2ASCII(charEngCheck.charEngCheck(tableNo)));

        session.setAttribute("tableNo", "");
        session.setAttribute("empCode", "");
        session.setAttribute("custCount", "");
        session.setAttribute("saleType", "");
        session.setAttribute("pluCode", "");
        session.setAttribute("pluName", "");
        session.setAttribute("pindex", "");

        String macNo = (String) session.getAttribute("macno");
        response.sendRedirect("Welcome?macno=" + macNo);

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
