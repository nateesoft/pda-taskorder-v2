package com.ics.pdatakeorder.servlet;

import com.ics.pdatakeorder.control.EmployControl;
import com.ics.pdatakeorder.db.MySQLConnect;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Welcome", urlPatterns = {"/Welcome"})
public class Welcome extends HttpServlet {

    public static boolean isCheck = false;
    public static int Count = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Count++;

        String macno = (String) request.getParameter("macno");
        HttpSession session = request.getSession();
        if (macno == null) {
            session.setAttribute("tableNo", "");
            session.setAttribute("macno", "");
            session.setAttribute("empCode", "");
            session.setAttribute("custCount", "");
            session.setAttribute("saleType", "");
        } else {
            session.setAttribute("tableNo", "");
            session.setAttribute("macno", macno);
            session.setAttribute("empCode", "");
            session.setAttribute("custCount", "");
            session.setAttribute("saleType", "");
        }

        EmployControl empCon = new EmployControl();
        request.setAttribute("showEmployField", empCon.checkEmployUse());

        String cEmpCode = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("c_empcode")) {
                    cEmpCode = c.getValue();
                    break;
                }
            }
        }
        request.setAttribute("cEmpCode", cEmpCode);

        RequestDispatcher req = request.getRequestDispatcher("/login.jsp");
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
