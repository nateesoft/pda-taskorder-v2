package com.ics.pdatakeorder.servlet;

import com.ics.pdatakeorder.model.TableFileBean;
import com.ics.pdatakeorder.control.TableFileControl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetTablefileTime", urlPatterns = {"/GetTablefileTime"})
public class GetTablefileTime extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        String tableNo = request.getParameter("tableNo");
        TableFileControl tf = new TableFileControl();
        TableFileBean bean = null;
        if (tableNo != null) {
            bean = tf.getData(tableNo);
        }
        response.setContentType("text/plain");
        try (PrintWriter out = response.getWriter()) {
            if (bean != null) {
                if (bean.getTCustomer() == 0) {
                    bean.setTCustomer(1);
                }
                out.println(bean.getTCustomer());
            } else {
                out.println("1");
            }
        }
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
