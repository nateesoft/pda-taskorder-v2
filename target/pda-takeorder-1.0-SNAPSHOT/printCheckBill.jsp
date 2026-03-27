
<%@page import="com.ics.pdatakeorder.control.EmployControl"%>
<%@page import="com.ics.pdatakeorder.model.ControlPrintCheckBill"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PrintCheckBill(พิมพ์ตรวจสอบ)</title>
        <script type="text/javascript" src="jquery-latest.min.js"></script>
        <%
            String token = request.getParameter("chkType");
            System.out.print(token);
        %>
        <script type="text/javascript">
            function printClick() {
                var table = document.getElementById("txtTableNo").value;
                var prefix = document.getElementById("txtPrefix").value;
//            var table = (String);
//            session.getAttribute("tableNo");
                var clickPrint = document.getElementById("print").value;
                var clickPrintAll = document.getElementById("printAll").value;
            }
        </script>
        <%
            EmployControl empCon = new EmployControl();
            Cookie[] cookies = request.getCookies();
            String cEmpCode = "";
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    Cookie c = (Cookie) cookies[i];
                    if (c.getName().equals("c_empcode")) {
                        cEmpCode = c.getValue();
                        break;
                    }
                }
            }

            String tableNo = (String) session.getAttribute("tableNo");
            String macNo = (String) session.getAttribute("macno");
            try {
                ControlPrintCheckBill cb = new ControlPrintCheckBill();
                cb.PrintCheckBill(tableNo, true, cEmpCode, token, macNo);
            } catch (Exception e) {
                e.printStackTrace();
            }


        %>
        <link rel="stylesheet" type="text/css" href="pda.css">
    </head>

    <body>
        <%            String prefix = (String) request.getParameter("prefix");
            if (prefix == null || prefix.equals("")) {
                prefix = "A";
            }
        %>
        <div align="center">
            <img src="img/QR-Code.jpg" alt="Alert!" width="300px" height="300px" style="cursor: auto;">
        </div>
        <div align="center"> 
            <p><a href="main.jsp?prefix=<%=prefix%>">
                    <td width="76" align="center">
                        <input type="button" name="print" id="print" value="Print OK!" onclick=""  style="width: 100%; height: 80px; font-size: 30px; background-color:#B22222; color: #fff; border-radius: 10px 0px 10px 0px; border: 1px solid;">
                    </td>
            </p>
        </div>
    </body>
</html>
