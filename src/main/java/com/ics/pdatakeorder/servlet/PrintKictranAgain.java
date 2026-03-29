package com.ics.pdatakeorder.servlet;

import com.ics.pdatakeorder.model.PKicTran;
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
@WebServlet(name = "PrintKictranAgain", urlPatterns = {"/PrintKictranAgain"})
public class PrintKictranAgain extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String tableNo = (String) session.getAttribute("tableNo");

        PKicTran.setPKicTranAgain(tableNo);

        String prefix = request.getParameter("prefix");
        if (prefix == null || prefix.isEmpty()) {
            prefix = "A";
        }

        request.setAttribute("prefix", prefix);

        RequestDispatcher req = request.getRequestDispatcher("/printKictranAgain.jsp");
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
