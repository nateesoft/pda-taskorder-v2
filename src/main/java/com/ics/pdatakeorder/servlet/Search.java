package com.ics.pdatakeorder.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.ics.pdatakeorder.model.ControlMenu;
import com.ics.pdatakeorder.model.MenuSetup;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Search extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String searchText =((String) request.getParameter("w"));
        if ("".equals(searchText)) {
            String searchTextCode = (String) request.getParameter("wc");
            ControlMenu cm = new ControlMenu();
            List<MenuSetup> listBeanCode = cm.getDataMenuSearchByCode(searchTextCode);
            Gson gson = new Gson();
            JsonElement element1 = gson.toJsonTree(listBeanCode, new TypeToken<List<MenuSetup>>() {
            }.getType());
            com.google.gson.JsonArray jsonArray1 = element1.getAsJsonArray();
            response.setContentType("application/json");
            response.getWriter().print(jsonArray1);
        } else {
            
            
            ControlMenu cm = new ControlMenu();
            List<MenuSetup> listBean = cm.getDataMenuSearch(searchText);
            Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(listBean, new TypeToken<List<MenuSetup>>() {
            }.getType());

            com.google.gson.JsonArray jsonArray = element.getAsJsonArray();
            response.setContentType("application/json");
            response.getWriter().print(jsonArray);
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
