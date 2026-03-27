<%-- 
    Document   : newjsp
    Created on : May 23, 2025, 6:32:49 PM
    Author     : Administrator
--%>

<%@page import="com.ics.pdatakeorder.db.MySQLConnect"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="jquery-latest.min.js"></script>
        <script type="text/javascript">
            function detail(R_Index) {
                var text = R_Index.id;
                var prefix = document.getElementById("txtPrefix").value;
                var clickPrint = document.getElementById("print").value;
                window.location = "Order?R_Index=" + text + "&prefix=" + prefix + clickPrint;
            }
        </script>
        <script>
            function check() {
                var url = window.location.href;
                var params = url.split('?');
                var pcode = params[1];
                var tableNo = params[2];

                document.getElementById("myText").value = pcode;
                document.getElementById('textbox_id').value
            }
        </script>

        <link rel="stylesheet" type="text/css" href="pda.css">
    </head>
    <body>
        <%
            int size = 0;
            String prefix = (String) request.getParameter("prefix");
            if (prefix == null || prefix.equals("")) {
                prefix = "A";
            }
        %>
        <h1>Hello World!</h1>
        <div style='text-align: center;'>
            <!-- insert your custom barcode setting your data in the GET parameter "data" -->
            <img alt='Barcode Generator TEC-IT'
                 src='https://barcode.tec-it.com/barcode.ashx?data=A10/994&code=Code128&translate-esc=on'/>
        </div>
    <dir>
        <h1>
            ส่งคำสั่งแจ้งเตือนเชฟ ให้แล้ว
        </h1>
    </dir>
    <!--        <div style='padding-top:8px; text-align:center; font-size:15px; font-family: Source Sans Pro, Arial, sans-serif;'>
                 back-linking to www.tec-it.com is required 
                <a href='https://www.tec-it.com' title='Barcode Software by TEC-IT' target='_blank'>
                    TEC-IT Barcode Generator<br/>
                     logos are optional 
                    <img alt='TEC-IT Barcode Software' border='0'
                         src='http://www.tec-it.com/pics/banner/web/TEC-IT_Logo_75x75.gif'>
                </a>
            </div>-->
   <!-- <p><a href="OrderOld.jsp?prefix=<%=prefix%>">     -->

    <form>
        <input type="text" id="myText" value="">
        <input type="button" name="button" id="button" value="กลับ" onclick="check()" style="width: 100%; height: 60px; font-size: 28px; background-color: #900; color: #FFF;">
        <%
            MySQLConnect mysql = new MySQLConnect();

            try {
                String tableNo = (String) session.getAttribute("tableNo");
                mysql.open();
                System.out.println(tableNo);
            } catch (Exception e) {
            }
        %>
    </form>
</body>
</html>
