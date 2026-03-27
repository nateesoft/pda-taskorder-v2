<%@page import="chocolate.control.CompanyMenu"%>
<%@page import="chocolate.control.MacnoControl"%>
<%@page import="database.MySQLConnect"%>
<%@page import="java.util.ArrayList"%>
<%@page import="control.ControlMenu"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="js/jquery-latest.min.js"></script>
        <script type="text/javascript" src="takeorder/pda.js"></script>
        <link rel="stylesheet" type="text/css" href="takeorder/pda.css">
        <script type="text/javascript">
            var myVar = setInterval(function() {
                myTimer();
            }, 1000);

            function myTimer() {
                var macno = document.getElementById("macnoShow").value;
                var d = new Date();
                var t = d.toLocaleTimeString();

                document.getElementById("softTime").innerHTML = "(" + macno + ")" + t;
            }
        </script>
    </head>
    <body background="img/wood.jpg" bgproperties="fixed" onLoad="defaultPage();
            clickSubGroup('A');">
        <%
            String MACNO = request.getParameter("macno");
        %>

        <%
            int msg = MacnoControl.checkMacno(MACNO);
            if (msg == MacnoControl.MACNO_NOT_AVAILABLE) {%>
        <script>
            alert("Macno " +<%=MACNO%> + " มีการนำไปใช้งานในระบบแล้ว !");
        </script>
        <% } else if (msg == MacnoControl.NO_CONNECTION) {%>
        <script>
            alert("ไม่สามารถเชื่อมต่อฐานข้อมูลได้ !");
        </script>
        <% } else if (msg == MacnoControl.NOT_FOUND_MACNO) {%>
        <script>
            alert("ยังไม่ได้กำหนดค่าการใช้งานเหมายเลขเครื่อง " +<%=MACNO%> + " !");
        </script>
        <% } else {%>
        <input type="hidden" name="txtFlagSave" id="txtFlagSave" value="no" />

        <div id="allPage1">

            <div class="divTotal">
                <table width="100%" class="labelTotal">
                    <tr>
                        <td width="97">โต๊ะ</td>
                        <td id="tableNo" width="119" align="center" class="divHeadButton"></td>
                        <td width="152" align="right" id="softTime" style="font-size: 22px; font-weight: bold;">&nbsp;</td>
                        <td width="152" align="right">รวม</td>
                        <td width="177" align="center" id="total" class="divHeadButton">0.00</td>
                    </tr>
                </table>
            </div>

            <div id="divLeft" class="left" style="width: 552px;">
                <div id="tableBillShow2" style="width: 552px;"></div>
                <div id="tableBillShow" style="width: 552px;"></div>
                <div id="tableLogin" style="width: 552px;">
                    <table width="100%" border="0" cellspacing="2" cellpadding="2">
                        <tr style="display: none;">
                            <td width="102" align="right" class="tdHilight">บริกร:</td>
                            <td width="152">
                                <input type="hidden" value="<%=MACNO%>" id="macnoShow" name="macnoShow" />
                                <input type="text" autocomplete="off" name="txtInput1" id="txtInput1" onClick="click1();" class="txtNormal">
                            </td>
                            <td width="8" align="left"> <label id="s1"></label></td>
                            <td width="147" rowspan="2">
                                <input type="button" name="button2" id="button2" value="Login" style="background-color: #FC3; font-weight: bold; border: 1px solid; font-size: 28px; border-radius: 10px; width: 100%; height: 100%;" onClick="login();">
                            </td>
                        </tr>
                        <tr>
                            <td align="right" class="tdHilight">รหัสบริกร:</td>
                            <td><input type="password" autocomplete="off" name="txtInput2" id="txtInput2" onClick="click2();" class="txtNormal" style="height: 55px;">

                                <input type="button" value="Login" style="background-color: #FC3; font-weight: bold; border: 1px solid; font-size: 28px; height: 65px;" onClick="login();">

                            </td>
                            <td align="left"><label id="s2"></label></td>
                        </tr>
                        <tr>
                            <td colspan="4" height="300px" width="100%" align="right" valign="bottom" background="img/rat2.JPG" style="border: 2px solid #FFC; border-radius: 10px; color: #FF0; font-size: 14px; font-weight: bold;">
                            </td>
                        </tr>
                    </table>
                </div>
                <div id="openTable">
                    <table id="table2" width="100%">
                        <tr>
                            <td width="29%" align="right" bgcolor="#FF3366" style="color: #FFF;">เบอร์โต๊ะ :</td>
                            <td width="42%">
                                <input type="number" autocomplete="off" name="txtTableNo" id="txtTableNo" class="txtNormal"/>
                                <label id="x1"></label>
                            </td>
                            <td width="29%" rowspan="3"><input name="button" type="button" id="button" value="เปิดโต๊ะ" class="btnOpenTable" onClick="loadTableBill();"></td>
                        </tr>
                        <tr>
                            <td align="right" bgcolor="#FF3366" style="color: #FFF;">ลูกค้า :</td>
                            <td><input type="number" autocomplete="off" name="txtCustNo" id="txtCustNo" class="txtNormal">
                                <label id="x2"></label>
                            </td>
                        </tr>
                        <tr>
                            <td align="right" bgcolor="#FF3366" style="color: #FFF;">ประเภท :</td>
                            <td><select name="txtType" id="txtType" class="txtNormal" style="width: 155px; height: 50px;">
                                    <option value="E">Eat In</option>
                                    <option value="T">Take Away</option>
                                </select></td>
                        </tr>
                        <tr>
                            <td colspan="3" height="268px" width="100%" align="center" background="img/rat7.JPG" style="border: 2px solid #FFC; border-radius: 10px;"></td>
                        </tr>
                    </table>
                </div>                
            </div>
            <div class="right">
                <div class="divOption">
                    <table width="100%">
                        <tr>
                            <td align="left">
                                <input type="button" class="btnOption" value="พักโต๊ะ" onClick="holdTableTest();" />
                                <input type="button" class="btnOption" value="ค้นหาสินค้า" onClick="searchProduct();" /></td>
                        </tr>
                    </table>
                </div>
                <div style="width: 552px;">
                    <table width="100%">
                        <tr>
                            <%
                                ControlMenu cm = new ControlMenu();
                                ArrayList<CompanyMenu> list = cm.getAllMenu();

                            %>
                            <% String[] data = new String[]{"A", "B", "C", "D"};%>
                            <% for (int x = 0; x < list.size(); x++) {%>
                            <% CompanyMenu comMenu = (CompanyMenu) list.get(x);%>
                            <td align="left">
                                <a href="javascript:clickSubGroup('<%=data[x]%>');">
                                    <input type="button" class="btnGroup" style="width: 100%;" value="<%=comMenu.getHeadName()%>" />
                                </a>
                            </td>
                            <% }%>
                        </tr>
                    </table>
                </div>
                <div id="scroll" style="height: 320px; width: 552px;">
                    <table width="100%">
                        <tr>
                            <td id="mySub"></td>
                        </tr>
                    </table>                
                </div>
                <div id="searchPage" style="width: 552px;">
                    <table width="100%">
                        <tr>
                            <td bgcolor="#006699" align="center" style="border-radius: 10px 0px 0px 10px; color: #FFF; font-size: 20px;">ค้นหา</td>
                            <td bgcolor="#006699" style="border-radius: 0px 10px 10px 0px;">
                                <input name="txtSearch" type="text" id="txtSearch" onKeyUp="findProduct();" style="width: 200px;" autocomplete="off">
                                <select name="typeSearch" id="typeSearch" class="txtNormal" style="width: 155px;">
                                    <option value="Name">ชื่อสินค้า</option>
                                    <option value="Menu">เลขที่เมนู</option>
                                </select></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div id="divSearchProduct" style="height: 240px; overflow: auto;">

                                </div>
                            </td>
                        </tr>
                    </table>
                </div>            
                <div id="optMenu" style=" width: 552px;">
                    <table width="100%" id="tbOptMenu">
                        <tr>
                            <td class="textOptHeader" colspan="2" height="28px" id="optPCode">&nbsp;</td>
                            <td height="28px" colspan="4" class="textOptHeader" id="optPDesc">สลัดนิซัว</td>
                        </tr>
                        <tr>
                            <td width="16%">จำนวน</td>
                            <td width="9%" id="optPUnit" align="right">1</td>
                            <td width="12%">รายการ</td>
                            <td width="8%">ราคา</td>
                            <td width="21%" id="optPPrice" align="right">265</td>
                            <td width="34%" align="center"> บาท</td>
                        </tr>
                        <tr>
                            <td>ประเภท</td>
                            <td colspan="5">
                                <select name="select" id="typeETD" class="txtNormal">
                                    <option value="E">Eat In</option>
                                    <option value="T">Take Away</option>
                                </select>
                                <input type="hidden" id="PCode" name="PCode" value="" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="6" class="textOptSeleted">ข้อความพิเศษเพิ่มเติม</td>
                        </tr>
                        <tr>
                            <td colspan="6">
                                <div style="overflow: auto; height: 105px;">
                                    <div id="showOptList"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="5">
                                <input name="btnEditOpt" type="button" id="btnEditOpt" value="บันทึกข้อมูล" style="width: 150px;" onClick="saveOptionConfirm();">
                                <input type="button" name="btnDelItem" id="btnDelItem" value="DELETE" class="btnDel" onClick="deleteItemBill();" style="display: none;">
                            </td>
                            <td align="right">
                                <select name="selAddProduct" id="selAddProduct" onChange="addProductNew();" class="txtNormal">
                                    <option value="0">สั่งเพิ่ม</option>
                                    <%
                                        for (int y = 1; y <= 100; y++) {
                                    %>
                                    <option value="<%=y%>"><%="+" + y%></option>
                                    <% }%>
                                </select></td>
                        </tr>
                    </table>
                </div>
            </div>
            <input type="hidden" name="hdd" id="hdd" value="1" />
            <input type="hidden" name="hdd2" id="hdd2" value="1" />
            <% }%>
        </div>
    </body>
</html>
