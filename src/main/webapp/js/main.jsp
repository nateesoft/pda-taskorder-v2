<%@page import="com.ics.pdatakeorder.model.ControlMenu"%>
<%@page import="com.ics.pdatakeorder.control.BalanceControl"%>
<%@page import="com.ics.pdatakeorder.db.MySQLConnect"%>
<%@page import="com.ics.pdatakeorder.model.MenuSetup"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PDA-SOFTPOS-V2.0</title>
        <script type="text/javascript" src="jquery-latest.min.js"></script>
        <script type="text/javascript" src="pda.js"></script>
        <link rel="stylesheet" type="text/css" href="pda.css">
        <script type="text/javascript">
            function plus() {
                var cass = document.getElementById("txtOrderQty");
                var num = parseInt(cass.value);
                if (num > 99) {
                    cass.value = "1";
                } else {
                    cass.value = num + 1;
                }
            }

            function minus() {
                var cass = document.getElementById("txtOrderQty");
                var num = parseInt(cass.value);
                if (num <= 1) {
                    cass.value = "1";
                } else {
                    cass.value = num - 1;
                }
            }
            function back() {
                window.location = "Next";
            }
            function comma(x) {
                return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            }
            function detail(R_Index) {
                var text = R_Index.id;
                var prefix = document.getElementById("txtPrefix").value;
                window.location = "Order?R_Index=" + text + "&prefix=" + prefix;
            }
            function saveData(prefix, pcode) {
                toastr.options = {
                    "closeButton": false,
                    "debug": false,
                    "progressBar": false,
                    "positionClass": "toast-top-right",
                    "onclick": null,
                    "showDuration": "10",
                    "hideDuration": "500",
                    "timeOut": "100",
                    "extendedTimeOut": "500",
                    "showEasing": "swing",
                    "hideEasing": "linear",
                    "showMethod": "fadeIn",
                    "hideMethod": "fadeOut"
                };

                var txtQty = document.getElementById("txtOrderQty").value;
                $.get("Save?prefix=" + prefix + "&PCode=" + txtQty + "*" + pcode, function(responseJson) {
                    if (responseJson !== null) {
                        $.each(responseJson, function(key, value) {

                            if (value.indexOf("Error:") !== -1) {
                                alert(value);
                            } else {
                                var data = value.split(",");
                                //alert(data[0]+"="+data[1]);
                                document.getElementById("btnListBill").value = "Increase(" + data[0] + ")";
                                var cTotal = parseFloat(data[1]).toFixed(2);
                                document.getElementById("totalBill").innerHTML = comma(cTotal);
                                document.getElementById("txtOrderQty").value = "1";
                                toastr["success"]("ท่านสั่งสินค้ารหัส " + pcode + " เรียบร้อยแล้ว");
                            }
                        });
                    }
                });
            }
            function showSearch() {
                var textSearch = document.getElementById("txtSearch").value;
                if (textSearch !== "") {
                    document.getElementById("divShowDataItem").style.display = "none";
                    document.getElementById("divSearch").style.display = "";
                    var html = "<table width='100%' border='0' cellpadding='5' cellspacing='5' style='border-collapse: collapse;'>";
                    $.get("Search?w=" + textSearch, function(responseJson) {
                        if (responseJson !== null) {
                            $.each(responseJson, function(key, value) {
                                html += "<tr>";
                                html += "<td style='font-size: 24px; font-weight: bold;'>" + value['ShortName'] + "</td>";
                                html += "<td align='center'><input type='hidden' value='1' style='width: 50px; text-align: right; font-size: 24px;' /></td>";
                                html += "<td align='center'><input type='button' value='สั่ง' style='font-size: 24px; width: 75px;  background-color: #F69;' onclick=saveData('A','" + value['PCode'] + "') /></td>";
                                html += "</tr>";
                            });
                        }
                        html += "</table>";
                        document.getElementById("divSearch").innerHTML = html;
                    });
                } else {
                    document.getElementById("divShowDataItem").style.display = "";
                    document.getElementById("divSearch").style.display = "none";
                }
            }

            function showSearchByCode() {
                var txtSearchCode = document.getElementById("txtSearchCode").value;
                if (txtSearchCode !== "") {
                    document.getElementById("divShowDataItem").style.display = "none";
                    document.getElementById("divSearch").style.display = "";
                    var html = "<table width='100%' border='0' cellpadding='5' cellspacing='5' style='border-collapse: collapse;'>";
                    $.get("Search?wc=" + txtSearchCode, function(responseJson) {
                        if (responseJson !== null) {
                            $.each(responseJson, function(key, value) {
                                html += "<tr>";
                                html += "<td style='font-size: 24px; font-weight: bold;'>" + value['ShortName'] + "</td>";

                                //เดิม input type='number'
                                html += "<td align='center'><input type='hidden' value='1' style='width: 50px; text-align: right; font-size: 24px;' /></td>";
                                //เดิม input type='number' 

                                html += "<td align='center'><input type='button' value='สั่ง' style='font-size: 24px; width: 75px;  background-color: #F69;' onclick=saveData('A','" + value['PCode'] + "') /></td>";
                                html += "</tr>";
                            });
                        }
                        html += "</table>";
                        document.getElementById("divSearch").innerHTML = html;
                    });
                } else {
                    document.getElementById("divShowDataItem").style.display = "";
                    document.getElementById("divSearch").style.display = "none";
                }
            }
        </script>
        <link href="toastr.css" rel="stylesheet" type="text/css" />
        <script src="jquery.min.js"></script>
        <script src="toastr.js"></script>
    </head>
    <body>
        <%
            String prefix = (String) request.getParameter("prefix");
            if (prefix == null || prefix.equals("")) {
                prefix = "A";
            }

            String macNo = (String) session.getAttribute("macno");
            String tableNo = (String) session.getAttribute("tableNo");

            BalanceControl bCon = new BalanceControl();
            String[] dataTable = bCon.getTableSum(tableNo).split(",");
            DecimalFormat dec = new DecimalFormat("#,##0.00");

            if (macNo == null || macNo.equals("")) {
                out.println("กรุณากำหนดหมายเลขเครื่อง สำหรับสั่งอาหาร <br />(ฐานข้อมูล: " + MySQLConnect.DB + ")");
            } else {
        %>
        <input type="hidden" name="txtMacNo" id="txtMacNo" value="<%=macNo%>" />
        <input type="hidden" name="txtTableNo" id="txtTableNo" value="<%=tableNo%>" />
        <input type="hidden" name="txtPrefix" id="txtPrefix" value="<%=prefix%>" />
        <table bgcolor="orange" border="1" style="border-radius: 10px; border-color: #ffffff; border: solid 5px; width: 445px;">
            <tr>
                <td width="56" height="59" align="center" style="font-size: 28px; font-weight: bold;">Table No.</td>
                <td width="24" align="center" style="font-size: 28px; font-weight: bold;"><%=tableNo%></td>
                <td width="124" align="center" style="font-size: 28px; font-weight: bold;"><input type="button" name="button4" id="button" value="+" style="height: 100%; font-size: 36px; width: 50px; text-align:center; font-weight: bold;" onClick="plus();">
                    <input type="number" name="txtOrderQty" id="txtOrderQty" style="height: 40px; font-size: 36px; width: 50px; text-align:center; font-weight: bold;" value="1">
                    <input type="button" name="button5" id="button4" value="-" style="height: 100%; font-size: 36px; width: 50px; text-align:center; font-weight: bold;" onClick="minus();"></td>
                <td width="57" align="right" id="totalBill" style="font-size: 28px; border: 1px solid; font-weight: bold;">
                    <%=dec.format(Double.parseDouble(dataTable[1]))%></td>
            </tr>
        </table>
        <div style="width: 445px; overflow: auto;">
            <table width="100%" border="0">
                <tr>
                    <%
                        ControlMenu cm = new ControlMenu();
                        String[] hMenu = cm.getHeaderMenu();
                        String[] pref = new String[]{"A", "B", "C", "D"};
                    %>
                    <% for (int i = 0; i < hMenu.length; i++) {%>
                    <td height="43" align="left"><a href="Login2?prefix=<%=pref[i]%>">
                            <input type="button" value="<%=hMenu[i]%>" class="btnGroup" style="width: 110px; border-radius: 0px 10px 0px 10px" />
                        </a></td>
                        <% }%>
                    <td><input name="txtSearchCode" type="text" id="txtSearchCode" placeholder="Code Search" style="width: 110px; height: 50px; border-radius: 10px; font-size: 24px; text-align: center;" onKeyUp="showSearchByCode();" /></td>
                    <td><input name="txtSearch" type="text" id="txtSearch" placeholder="Name Search" style="width: 110px; height: 50px; border-radius: 10px; font-size: 24px; text-align: center;" onKeyUp="showSearch();" /></td>
                    <!--<td><input name="txtSearch" type="text" id="txtSearch" placeholder="Name Search" style="width: 135px; height: 50px; border-radius: 10px; font-size: 24px; text-align: center;" onKeyUp="showSearch();" /></td> -->
                </tr>
            </table>
        </div>

        <!-- START SHOW DATA ITEM -->
        <div id="divShowDataItem">
            <table width="100%" border="0">
                <tr>
                    <%
                        List<MenuSetup> list = cm.getDataMenu(prefix);
                        for (int i = 0; i < list.size(); i++) {
                            MenuSetup menu = (MenuSetup) list.get(i);
                            String bgColor = "#33CC66";
                            if (menu.getCode_Type().equals("S")) {
                                bgColor = "#33CC66";
                            } else if (menu.getCode_Type().equals("P") && !menu.getPCode().equals("")) {
                                bgColor = "#F2F5A9";
                            } else {
                                menu.setShortName("");
                                bgColor = "#33CC66";
                            }

                            String back = "";

                            if (i == list.size() - 1) {
                                bgColor = "#FF4000";
                                if (prefix.length() > 1) {
                                    back = prefix.substring(0, 1);
                                    bgColor = "#FF4000";
                                } else {
                                    bgColor = "#33CC66";
                                    back = "";
                                }
                            }
                    %>
                    <td height="85" align="center" bgcolor="<%=bgColor%>"><% if (i == list.size() - 1 && !back.equals("")) {%>
                        <a href="Login2?prefix=<%=back%>">
                            <input type="button" value="Back" style="font-size: 24px; height: 65px; background-color: #C33; color: #FFF; border: 0px solid #666; border-radius: 25px;" />
                        </a>
                        <% } else {%>
                        <% if (menu.getCode_Type().equals("P") && !menu.getShortName().equals("")) {%>
                        <a href="javascript:saveData('<%=prefix%>','<%=menu.getPCode()%>')" style="text-decoration: none;">
                            <button class="border2" style="width: 90px; height: 90px; font-size: <%=MySQLConnect.fontSize%>; color: #000;"><%=menu.getShortName()%></button>
                        </a>
                        <% } else {%>
                        <a href="Login2?prefix=<%=menu.getCode_ID() + "E"%>" style="text-decoration: none;">
                            <button class="border1" style="width: 90px; height: 87px; font-size: <%=MySQLConnect.fontSize%>; color: #000;"><%=menu.getShortName()%></button>
                        </a>
                        <% }%>
                        <% }%></td>
                        <%
                            if ((i + 1) % 4 == 0) {
                        %>
                </tr>
                <%
                    if (i < list.size() - 1) {
                %>
                <tr>
                    <% }%>
                    <% }%>
                    <% }%>
            </table>
        </div>
        <!-- END SHOW DATA ITEM -->

        <!-- START DIV SHOW SEARCH -->
        <div align="center" id="divSearch" style="display: none; background-color: #DAFED8; border: 2px solid; width: 450px; height: 400px; border-radius: 10px; overflow: auto;">

        </div>
        <!-- END DIV SHOW SEARCH -->

        <table border="0" style="width: 445px;border-radius: 10px; border-color: #ffffff; border: 2px solid; ">
            <tr>
                <td width="65" height="26" align="center"><a href="Order.jsp?prefix=<%=prefix%>">
                        <input type="button" name="button" id="btnListBill" value="New Order(<%=dataTable[0]%>)" style="width: 150px; height: 60px; font-size: 24px; background-color: #909; color: #fff; border-radius: 10px 0px 10px 0px; border: 1px solid;">
                    </a>
                </td>
                <td width="125" align="center"><a href="OrderOld.jsp?prefix=<%=prefix%>">
                        <input type="button" name="button2" id="button2" value="Old Order" style="width: 120px; height: 60px; font-size: 24px; background-color: #909; color: #fff; border-radius: 10px 0px 10px 0px; border: 1px solid;">
                    </a>
                </td>
                <td width="76" align="center"><input type="button" name="button3" id="button3" value="Send" onClick="back();" style="width: 150px; height: 60px; font-size: 24px; background-color: #909; color: #fff; border-radius: 10px 0px 10px 0px; border: 1px solid;"></td>
            </tr>
        </table>
        <% }%>
    </body>    
</html>
