<%@page import="com.ics.pdatakeorder.model.FollowItemFoodUrgent"%>
<%@page import="com.ics.pdatakeorder.control.EmployControl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ตามอาหาร ลูกค้ารอนาน</title>
        <script type="text/javascript" src="js/jquery-latest.min.js"></script>
        <script>
        </script>
        <link rel="stylesheet" type="text/css" href="css/pda.css">
    </head>
    <body>
        <%
            int size = 0;
            String prefix = (String) request.getParameter("prefix");
            if (prefix == null || prefix.equals("")) {
                prefix = "A";
            }
        %>
    <dir align="center" style=" background-color: #D9EDF7; border-bottom-right-radius: 1px; border-style: groove">
        <h2>
            ส่งคำสั่งแจ้งเตือนเชฟ ให้แล้ว
        </h2>
    </dir>

    <%
        try {
            String tableNo = (String) session.getAttribute("tableNo");
            String pluCode = request.getParameter("pluCode");
            String pluName = request.getParameter("pluName");
            String pindex = request.getParameter("pindex");
            
            FollowItemFoodUrgent fl = new FollowItemFoodUrgent();
            fl.FollowItemFoodUrgentByItem(tableNo, pluCode, pluName, pindex);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    %>
    <div align="center">
        <p><a href="main.jsp?prefix=<%=prefix%>">                
                <input type="button" name="button" id="button20" value="กลับเมนูหลัก" style="width: 100%; height: 60px; font-size: 28px; background-color: #900; color: #FFF;">
            </a></p>
    </div>
</body>
</html>
