<%@page import="com.ics.pdatakeorder.db.MySQLConnect"%>
<%@page import="com.ics.pdatakeorder.model.TableFileBean"%>
<%@page import="com.ics.pdatakeorder.control.TableFileControl"%>
<%@page import="com.ics.pdatakeorder.control.EmployControl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PDA-ICS</title>
        <style type="text/css">
            .el08 {
                width:4em;
                height:4em;
            }
        </style>
        <script type="text/javascript" src="jquery-latest.min.js"></script>
        <script type="text/javascript">
            function ready() {
                var idTableCode = document.getElementById("txtTableCode");

                //focust table no
                idTableCode.focus();

                var idEmpCode = document.getElementById("txtEmpCode");
                if (idEmpCode.value === "") {
                    idEmpCode.focus();
                } else {
                    idTableCode.focus();
                }
            }
            function valid() {
                var tcode = document.getElementById("txtTableCode");
                var tcust = document.getElementById("txtCustCount");
                var tActiveStatus = document.getElementById("txtTableCodeActiveStatus").value;
                var custCount = parseInt(tcust.value);
                if (tcode.value === "") {
                    alert("Please Type Table !!!");
                    tcode.focus();
                    return false;
                } else if (tcust.value === "") {
                    alert("Please Type Qty Customer !!!");
                    tcust.focus();
                    return false;
                } else if (custCount <= 0) {
                    alert("Please Input Customer more than 1 !!!");
                    tcust.focus();
                    return false;
                } else if (tActiveStatus === "N") {
                    alert("Valid ปิดปรับปรุง" + tActiveStatus);
                    return false;
                } else {
                    return true;
                }
            }
            function show1() {
                document.getElementById("radio1").checked = "CHECKED";
            }

            function show2() {
                document.getElementById("radio2").checked = "CHECKED";
            }
            function show3() {
                document.getElementById("radio3").checked = "CHECKED";
            }
            function show4() {
                document.getElementById("radio4").checked = "CHECKED";
            }

            function test(tableNo, callback) {
                $.get("GetTableStatusActive", {tableNo: tableNo}, function (data, textStatus) {
                    callback(data);
                }, "text");
            }

            function loadCustomer() {
                $.get("GetCustCount", {tableNo: $("#txtTableCode").val()}, function (data, textStatus) {
                    var dd = data.split(",");
                    document.getElementById("txtCustCount").value = parseInt(dd[0]);
                    document.getElementById("txtTableCodeActiveStatus").value = dd[1];
                }, "text");
            }

            function loadCust(evt) {
                if (evt === 13) {
                    loadCustomer();
                }
            }

        </script>
    </head>

    <body onload="ready();" style="background-color: #FF9;">
        <div style=" alignment-adjust:  central;">
            <%
                String macNo = (String) session.getAttribute("macno");
                if (macNo == null || macNo.equals("")) {
                    out.println("Please Setting Macno <br />(ฐานข้อมูล: " + MySQLConnect.DB + ")");
                } else {
            %>
            <form action="Login" method="post" onsubmit="return valid()">
                <table width="100%" style="position:absolute; top:1%; border: 1px solid; background-color: #FFF;">
                    <tr>
                        <td height="60%" colspan="3" align="center" bgcolor="#FF6699" style="font-size: 14px; font-weight: bold; color: #FFF;">(<%=MySQLConnect.DB%>)
                            <br /><span style="font-size: 12px;">ICS - PDA Take Order</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="595" height="79" align="right" bgcolor="#0099FF" style="font-size: 22px;">เลขเครื่อง</td>
                        <td width="461" style="font-size: 22px;"><%=macNo%>
                            <input type="hidden" name="txtMacNo" style="font-size: 22px;" value="<%=macNo%>" />
                        </td>
                        <td width="481"></td>
                    </tr>
                    <%
                        EmployControl empCon = new EmployControl();
                        if (empCon.checkEmployUse()) {
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
                    %>
                    <tr>
                        <td width="400" height="78" align="right" bgcolor="#0099FF" style="font-size: 22px;">พนักงาน</td>
                        <td colspan="2">
                            <input type="number" style="font-size: 22px; width: 90px;" name="txtEmpCode" id="txtEmpCode" autocomplete="off" value="<%=cEmpCode%>" />
                            <input name="chkRemember" type="checkbox" id="chkRemember" checked="checked">
                            Remember</td>
                    </tr>

                    <% }%>
                    <tr>
                        <td height="72" align="right" bgcolor="#0099FF" style="font-size: 22px;">โต๊ะ</td>
                        <td>
                            <input type="text" name="txtTableCode" style="font-size: 22px; width: 95px;" id="txtTableCode" autocomplete="off" onblur="loadCustomer();" onkeypress="loadCust(event);" />
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" required name="txtTableCodeActiveStatus" 
                                   style="font-size: 22px; width: 95px;" 
                                   id="txtTableCodeActiveStatus" 
                                   autocomplete="off" hidden=""
                                   />
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td height="63" align="right" bgcolor="#0099FF" style="font-size: 22px;">ลูกค้า</td>
                        <td>
                            <input type="number" id="txtCustCount" style="font-size: 22px;text-align: right; width: 95px;" name="txtCustCount" autocomplete="off" value="1" />
                            <span style="font-size: 22px;">Guest</span>
                        </td>
                        <td style="font-size: 22px;"></td>
                    </tr>
                    <tr>
                        <td height="56" align="right" bgcolor="#0099FF" style="font-size: 22px;">ประเภท</td>
                        <td colspan="2" bgcolor="#FFFFFF">
                            <input type="radio" name="chkType" id="radio1" value="E" checked="CHECKED">
                            <input type="button" style="font-size: 22px; width: 200px;" onclick="show1();" value="นั่งทาน"><br /><br>
                            <input type="radio" name="chkType" id="radio2" value="T">
                            <input type="button" style="font-size: 22px; width: 200px;" onclick="show2();" value="ห่อกลับ"><br /><br>
                            <input type="radio" name="chkType" id="radio3" value="D">
                            <input type="button" style="font-size: 22px; width: 200px;" onclick="show3();" value="เดลิเวอรี่"><br /><br>
                            <input type="radio" name="chkType" id="radio4" value="S">
                            <input type="button" style="font-size: 22px; width: 200px;" onclick="show4();" value="สั่งล่วงหน้า"><br /><br>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="3" align="right" bgcolor="#FFFFFF">
                            <input name="Submit" type="submit" style="font-size: 22px; height: 80px; width: 100%; background-color: #390; color: #FFF;" value="เมนูอาหาร" />
                        </td>
                    <tr>

                </table>

            </form>
            <% } %>
        </div>
    </body>
</html>
