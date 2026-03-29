package com.ics.pdatakeorder.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.ics.pdatakeorder.control.BalanceControl;
import com.ics.pdatakeorder.model.Option;
import com.ics.pdatakeorder.model.ProductBean;
import com.ics.pdatakeorder.control.ProductControl;
import com.ics.pdatakeorder.model.STCardBean;
import com.ics.pdatakeorder.control.StockControl;
import com.ics.pdatakeorder.control.TableFileControl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "Save", urlPatterns = {"/Save"})
public class Save extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        List<String> listBalance = new ArrayList<>();
        String PCode = (String) request.getParameter("PCode");

        StockControl stockControl = new StockControl();
        STCardBean stcBean = new STCardBean();
        double R_Quan;
        String username = "";

        HttpSession session = request.getSession();
        String R_ETD = (String) session.getAttribute("saleType");
        String MACNO = (String) session.getAttribute("macno");
        String R_Table = ((String) session.getAttribute("tableNo"));

        TableFileControl tableFileControl = new TableFileControl();
        if (tableFileControl.checkBillReady(R_Table)) {
            listBalance.add("โต๊ะนี้พิมพ์บิลแล้วไม่สามารถสั่งอาหารได้(กำลังเช็คบิล)");
        } else {
            String EMP = (String) session.getAttribute("empCode");

            if (EMP != null) {
                username = EMP;
            }

            String stockPOS;
            ProductControl pControl = new ProductControl();
            SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss");

            String[] data;
            data = Option.splitPrice(PCode);
            R_Quan = (int) Double.parseDouble(data[0]);
            if (R_Quan == 0) {
                R_Quan = 1;
            }
            PCode = data[1];

            ProductBean productBean = pControl.getData(PCode);
            BalanceControl balance = new BalanceControl();

            /* สินค้านี้จัดทำสต็อกหรือไม่ */
            if (productBean.getPStock().equals("Y")) {
                System.out.println("เป็นสินค้าจัดทำสต็อก");

                stockPOS = stockControl.GET_STOCK_NAME(PCode, R_Table, MACNO);
                //Update ข้อมูลลงตาราง StkFile, Insert ข้อมูลลง StCard
                if (balance.checkQuantity(R_Table, PCode, R_Quan)) {
                    //สำหรับเพิ่มรายการ
                    if (R_Quan > 0) {
                        //update stkfile
                        stockControl.updateSTKFileAdd(PCode, stockPOS, (int) R_Quan);
                    } //สำหรับลดรายการ หรือ Void
                    else if (R_Quan < 0) {
                        //update stkfile
                        stockControl.updateSTKFileVoid(PCode, stockPOS, (int) R_Quan);
                    }

                    //insert stcard จะทำเหมือนกันทั้งการเพิ่มสินค้า หรือ void สินค้าออก                                    
                    stcBean.setS_Rem("SAL");
                    stcBean.setS_User(username);
                    stcBean.setS_Stk(stockPOS);
                    stcBean.setS_PCode(PCode);
                    stcBean.setS_No("1" + s.format(new Date()));
                    stcBean.setS_Que(0);
                    stcBean.setS_Out(R_Quan);
                    stcBean.setS_InCost(0);
                    stcBean.setS_OutCost(R_Quan * productBean.getPPrice11());
                    stcBean.setS_ACost(0);
                    stcBean.setS_SubNo("");
                    stcBean.setS_Link("");

                    stockControl.saveSTCard(stcBean, R_ETD);
                }
            }

            String[] opt = new String[]{"", "", "", "", "", "", "", "", ""};

            //ตรวจสอบการบันทึกข้อมูล ถ้าบันทึกสำเร็จ
            if (!balance.saveBalance(R_Table, PCode, R_Quan, username, opt, R_ETD, EMP, productBean.getPPrice11(), MACNO)) {                
                out.print("ไม่สามารถบันทึกข้อมูลการสั่งซื้อได้ !!!");
            }

            BalanceControl bc = new BalanceControl();
            String dataTableFile = bc.getTableSum(R_Table);            
            listBalance.add(dataTableFile);

        }

        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(listBalance, new TypeToken<List<String>>() {
        }.getType());

        com.google.gson.JsonArray jsonArray = element.getAsJsonArray();
        response.setContentType("application/json");
        //response.setCharacterEncoding("utf8");
        response.getWriter().print(jsonArray);
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
