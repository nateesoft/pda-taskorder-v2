<%@page import="com.ics.pdatakeorder.db.MySQLConnect"%>
<%@page import="com.ics.pdatakeorder.util.ThaiUtil"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.ics.pdatakeorder.model.OptionFile"%>
<%@page import="com.ics.pdatakeorder.model.BalanceBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PDA-ICS</title>
        <script type="text/javascript">
            function back() {
                window.location = "Order.jsp?prefix=A";
            }
            function input(v) {
                document.getElementById("txtQtyOrder").value = v.value;
            }
            function addQty() {
                var qtyAdd = document.getElementById("txtQtyPlus");
                var qtyCount = parseInt(qtyAdd.value);
                qtyAdd.value = (qtyCount + 1);
            }
            function addNew() {
                var qtyAdd = document.getElementById("txtQtyPlus");
                var qtyCount = parseInt(qtyAdd.value);
                var prefix = document.getElementById("txtPrefix").value;
                var PCode = document.getElementById("txtPCode").value;

                $.get("Save?prefix=" + prefix + "&PCode=" + qtyCount + "*" + PCode, function (responseJson) {
                    if (responseJson !== null) {
                        $.each(responseJson, function (key, value) {
                            //var data = value.split(",");
                            window.location = "Order.jsp?prefix=" + prefix;
                        });
                    }
                });
            }
            function chkThis(v) {
                var vv = document.getElementById("chkOpt" + v);
                if (vv.checked === true) {
                    vv.checked = false;
                } else {
                    vv.checked = true;
                }
            }
            function chk(c) {
                if (c === "E") {
                    document.getElementById("chkType1").checked = true;
                } else if (c === "T") {
                    document.getElementById("chkType2").checked = true;
                } else if (c === "D") {
                    document.getElementById("chkType3").checked = true;
                } else {
                    document.getElementById("chkType1").checked = true;
                }
            }
        </script>
        <script src="jquery.min.js"></script>
    </head>

    <body>
        <form action="Update" method="post">        
            <%
                DecimalFormat dec = new DecimalFormat("#,##0.00");
                String prefix = (String) request.getParameter("prefix");
                if (prefix == null || prefix.equals("")) {
                    prefix = "A";
                }

                String macNo = (String) session.getAttribute("macno");

                if (macNo == null || macNo.equals("")) {
                    out.println("สำหรับสั่งอาหาร <br />(ฐานข้อมูล: " + MySQLConnect.DB + ")");
                } else {

                    BalanceBean bean = (BalanceBean) request.getAttribute("bean");
            %>

            <input type="hidden" id="txtRIndex" name="txtRIndex" value="<%=bean.getR_Index()%>" />
            <input type="hidden" id="txtPrefix" name="txtPrefix" value="<%=prefix%>" />

            <table border="0" cellpadding="2" cellspacing="2" style="width: 330x;">
                <tr>
                    <th width="321" height="26" align="right" bgcolor="#FF0099">รหัส</th>
                    <td colspan="2" bgcolor="#FFFFFF">
                        <input name="txtPCode" type="text" id="txtPCode" value="<%=bean.getR_PluCode()%>" readonly>
                    </td>
                </tr>
                <tr>
                    <th height="26" align="right" bgcolor="#FF0099">ชื่อ</th>
                    <td colspan="2" bgcolor="#FFFFFF">
                        <input name="textfield2" type="text" id="textfield2" value="<%=bean.getR_PName()%>" readonly>
                    </td>
                </tr>
                <tr>
                    <th height="26" align="right" bgcolor="#FF0099">ราคา</th>
                    <td colspan="2" width="367" bgcolor="#FFFFFF">
                        <input name="textfield3" type="text" id="textfield3" style="text-align:right;" value="<%=dec.format(bean.getR_Price())%>" readonly>
                        บาท                    </td>
                </tr>
                <tr>
                    <th height="26" align="right" bgcolor="#FF0099">ประเภท</th>
                    <td colspan="2" bgcolor="#FFFFFF" width="50%">
                        <%
                            String saleType = (String) session.getAttribute("saleType");
                            if (saleType == null) {
                                saleType = "E";
                            }
                        %>
                        <input type="radio" name="chkType" id="chkType1" value="E" <% if (saleType.equals("E")) {%> checked="CHECKED"<% }%>>
                        <input type="button" value="นั่งทาน" onClick="chk('E');" style="height: 35px; font-size: 14px;" />
                        <input type="radio" name="chkType" id="chkType2" value="T" <% if (saleType.equals("T")) {%> checked="CHECKED"<% }%>>
                        <input type="button" value="ห่อกลับ" onClick="chk('T');" style="height: 35px; font-size: 14px;" />
                        <input type="radio" name="chkType" id="chkType3" value="D" <% if (saleType.equals("D")) {%> checked="CHECKED"<% }%>>
                        <input type="button" value="เดลิเวอรี่" onClick="chk('D');" style="height: 35px; font-size: 14px;" />
                    </td>
                </tr>
                <tr>
                    <th height="26" align="right" bgcolor="#FF0099">จำนวน</th>
                    <td colspan="2" bgcolor="#FFFFFF">
                        <input name="txtQtyOrder" type="number" id="txtQtyOrder" value="<%=bean.getR_Quan()%>" style="text-align:right;" readonly>
                        List</td>
                </tr>
                <tr>
                    <th height="221" align="right" valign="top" bgcolor="#FF0099">ข้อความ</th>
                    <td colspan="2" valign="middle" bgcolor="#FFFFFF">
                        <div style="overflow: auto; height: 150px;">
                            <table width="100%" cellpadding="5" cellspacing="5">                    
                                <%
                                    String[] opt = (String[]) OptionFile.getListOption(bean.getR_Group());
                                    for (int i = 0; i < opt.length; i++) {
                                %>
                                <tr><td width="330">
                                        <label>
                                            <input type="checkbox" name="chkOpt" id="chkOpt<%=i%>" value="<%=opt[i]%>">
                                            <input type="button" value="<%=opt[i]%>" onclick="chkThis('<%=i%>')" style="width: 55%; height: 45px; font-size: 18px; background-color: #093; color: #FFF;" />
                                        </label>                    
                                    </td></tr>
                                    <% }%>
                            </table>
                        </div>
                        <br />
                        <span style="font-size: 14px;">
                            ตัวอย่าง "ไม่หวาน,ไม่..." </span>
                        <input type="text" name="optAddNew" id="optAddNew" value="" style="height: 45px; width:40%; font-size: 16px;" />
                    </td>
                </tr>
                <%
                    if (!bean.getR_Pause().equals("P")) {
                %>
                <tr>
                    <th height="26" align="right" bgcolor="#FF0099">เพิ่มจำนวน</th>
                    <td valign="middle" bgcolor="#FFFFFF">
                        <input name="txtQtyPlus" type="number" id="txtQtyPlus" value="1" style="width: 50px; height: 25px; font-size: 18px; text-align: center;" readonly>
                        <input type="button" name="button2" id="button2" value="+" style="height: 25px; width: 40px;font-size: 15px;" onclick="addQty();"></td>
                    <td align="right" valign="middle" bgcolor="#FFFFFF"><input type="button" name="button" id="button" value="เพิ่ม" style="height: 40px; width: 150px;font-size: 14px;" onClick="addNew();"></td>
                </tr>
                <%
                    }
                %>
                <tr>
                    <td height="28" align="right" bgcolor="#FF0033">&nbsp;</td>
                    <td height="28" align="right" bgcolor="#FF0033"><input type="submit" name="button4" id="button4" value="ยืนยัน" style="height: 40px; width: 90px; font-size: 16px;"></td>
                    <td height="28" align="right" bgcolor="#FF0033"><input type="button" name="button3" id="button3" value="กลับเมนูหลัก" style="height: 40px; width: 120px;font-size: 16px;" onClick="back();"></td>
                </tr>
            </table>
            <% }%>

        </form>
    </body>
</html>
