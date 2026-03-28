<%@page import="com.ics.pdatakeorder.model.PKicTran"%>
<%@page import="com.ics.pdatakeorder.control.EmployControl"%>
<%@page import="com.ics.pdatakeorder.model.ControlPrintCheckBill"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KicPrintAgain(ติดตามอาหาร ลูกค้ารอนาน)</title>
        <script type="text/javascript" src="js/jquery-latest.min.js"></script>
        <%
            String token = request.getParameter("chkType");
            System.out.print(token);
        %>
        <script type="text/javascript">
            function printClick() {
            var table = document.getElementById("txtTableNo").value;
                    var prefix = document.getElementById("txtPrefix").value;
                    var table = (String);
                    session.getAttribute("tableNo");
                    var clickPrint = document.getElementById("print").value;
        </script>
        <%
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    Cookie c = (Cookie) cookies[i];
                    if (c.getName().equals("c_empcode")) {
                        break;
                    }
                }
            }
        %>
        <link rel="stylesheet" type="text/css" href="css/pda.css">
    </head>

    <body>
        <%
            String prefix = (String) request.getParameter("prefix");
            if (prefix == null || prefix.equals("")) {
                prefix = "A";
            }
            String tableNo = (String) session.getAttribute("tableNo");
            PKicTran.setPKicTranAgain(tableNo);
        %>
        <div align="center"> 
            <p>
                <a href="HistoryOrder?prefix=<%=prefix%>">
                    <input type="button" name="print" id="print" value="ระบบกำลังแจ้งเตือนห้องครัว!" onclick=""  style="width: 100%; height: 80px; font-size: 30px; background-color:#B22222; color: #fff; border-radius: 10px 0px 10px 0px; border: 1px solid;" />
                </a>
            </p>
        </div>
        <div align="center">
            <p>
                <a href="Login2?prefix=<%=prefix%>">
                    <input type="button" name="button" id="button20" value="กลับเมนูหลัก" style="width: 100%; height: 60px; font-size: 28px; background-color: #900; color: #FFF;" />
                </a>
            </p>
        </div>
    </body>
</html>
