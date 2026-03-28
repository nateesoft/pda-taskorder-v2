<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.ics.pdatakeorder.control.BalanceControl"%>
<%@page import="com.ics.pdatakeorder.model.BalanceBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="jquery-latest.min.js"></script>
        <script type="text/javascript">
            function delData(prefix, r_index, pcode, qty) {
                window.location = "Remove?prefix=" + prefix + "&R_Index=" + r_index + "&PCode=" + qty + "*" + pcode;
                $.get("Remove?prefix=" + prefix + "&R_Index=" + r_index + "&PCode=" + qty + "*" + pcode, function(responseJson) {

                if (responseJson !== null) {
                $.each(responseJson, function(key, value) {

                });
                }
                });
            }
            function detail(R_Index) {
                var text = R_Index.id;
                var prefix = document.getElementById("txtPrefix").value;
                window.location = "Order?R_Index=" + text + "&prefix=" + prefix;
            }
        </script>
        <link rel="stylesheet" type="text/css" href="pda.css">
    </head>
    <body>
        <div style=" alignment-adjust:  central; width: 100%">
        <%
            String prefix = (String) request.getParameter("prefix");
            if (prefix == null || prefix.equals("")) {
                prefix = "A";
            }
        %>
        <input type="hidden" id="txtPrefix" value="<%=prefix%>">
        <table width="100%" border="0" id="tableBill">
            <tr>
                <th width="40" bgcolor="#00CCFF">#</th>
                <th width="155" bgcolor="#00CCFF">ประเภท</th>
                <th width="471" bgcolor="#00CCFF">รายการ</th>
                <th width="57" bgcolor="#00CCFF">จำนวน</th>
                <th width="71" bgcolor="#00CCFF">ราคา</th>
                <th width="83" bgcolor="#00CCFF">รวม</th>
                <th width="200" bgcolor="#00CCFF">ยกเลิก</th>
            </tr>
            <%
                BalanceControl bc = new BalanceControl();
                DecimalFormat dec = new DecimalFormat("#,##0");
                String table = (String) session.getAttribute("tableNo");
                String r_etd = (String) session.getAttribute("tableNo");
                if (table == null) {
                    table = "";
                }
                List<BalanceBean> listBalance = bc.getAllBalanceNew(table);
                if (listBalance == null) {
                    listBalance = new ArrayList();
                }
                int index = 0;
                for (int i = 0; i < listBalance.size(); i++) {
                    index++;
                    BalanceBean balanceBean = (BalanceBean) listBalance.get(i);
            %>
            <tr>
                <td height="55px" align="center"><%=index%></td>
                <td height="5%" align="center">
                    <%
                        if (balanceBean.getR_ETD().equals("E")) {
                            out.println("นั่งทาน");
                        } else if (balanceBean.getR_ETD().equals("T")) {%>
                    <span style="background-color: #00ff99;">ห่อกลับ</span>
                    <%} else if (balanceBean.getR_ETD().equals("D")) {%>
                    <span style="background-color: #FF9966;">เดลิเวอรี่</span> 
                    <%} else {
                            out.println("Unknow");
                        }
                    %>
                </td>
                <td onclick="detail(this);" id="<%=balanceBean.getR_Index()%>" style="background-color: #06F; color: #FFF; font-size: 15px;"><%=balanceBean.getR_PluCode()%> # <%=balanceBean.getR_PName()%><br />
                    <%

                        String opt1 = balanceBean.getR_Opt1();
                        String opt2 = balanceBean.getR_Opt2();
                        String opt3 = balanceBean.getR_Opt3();
                        String opt4 = balanceBean.getR_Opt4();
                        String opt5 = balanceBean.getR_Opt5();
                        String opt6 = balanceBean.getR_Opt6();
                        String opt7 = balanceBean.getR_Opt7();
                        String opt8 = balanceBean.getR_Opt8();

                        if (!opt1.equals("")) {
                            out.print("<u>" + opt1 + "</u>,");
                        }
                        if (!opt2.equals("")) {
                            out.print("<u>" + opt2 + "</u>,");
                        }
                        if (!opt3.equals("")) {
                            out.print("<u>" + opt3 + "</u>,");
                        }
                        if (!opt4.equals("")) {
                            out.print("<u>" + opt4 + "</u>,");
                        }
                        if (!opt5.equals("")) {
                            out.print("<u>" + opt5 + "</u>,");
                        }
                        if (!opt6.equals("")) {
                            out.print("<u>" + opt6 + "</u>,");
                        }
                        if (!opt7.equals("")) {
                            out.print("<u>" + opt7 + "</u>,");
                        }
                        if (!opt8.equals("")) {
                            out.print("<u>" + opt8 + "</u>,");
                        }
                    %>
                </td>
                <td align="right"><%=(int) balanceBean.getR_Quan()%></td>
                <td align="right"><%=dec.format(balanceBean.getR_Price())%></td>
                <td align="right"><%=dec.format(balanceBean.getR_Total())%></td>
                <td align="right">
                    <%
                        if (!balanceBean.getR_Pause().equals("P")) {
                    %>
                    <a href="javascript:delData('<%=prefix%>','<%=balanceBean.getR_Index()%>','<%=balanceBean.getR_Quan()%>','<%=balanceBean.getR_PluCode()%>')">
                        <input type="button" value="ยกเลิก" style="width: 100%; height: 100%; font-size: 16px; background-color: #ff3300; color: #FFF;" />
                        <% }%>
                </td>
            </tr>
            <% }%>
            
        </table>
        <p><a href="Login2?prefix=<%=prefix%>">                
                <input type="button" name="button" id="button" value="กลับสู่เมนู" style="width: 100%; height: 60px; font-size: 32px; background-color: #900; color: #FFF;">
            </a></p>
        </div>
    </body>
</html>
