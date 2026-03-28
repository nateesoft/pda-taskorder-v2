<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Item Page</title>
        <script type="text/javascript" src="jquery-latest.min.js"></script>
        <script type="text/javascript">
            function delData(prefix, r_index, pcode, qty) {
                $.get("Remove?prefix=" + prefix + "&R_Index=" + r_index + "&PCode=" + qty + "*" + pcode, function (responseJson) {

                    if (responseJson !== null) {
                        $.each(responseJson, function (key, value) {

                        });
                    }
                });
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
            function show5() {
                document.getElementById("radio5").checked = "CHECKED";
            }
            function show6() {
                document.getElementById("radio6").checked = "CHECKED";
            }
            function detail(R_Index) {
                var text = R_Index.id;
                var prefix = document.getElementById("txtPrefix").value;
                var clickPrint = document.getElementById("print").value;
                window.location = "Order?R_Index=" + text + "&prefix=" + prefix + clickPrint;
            }
        </script>
        <script>
            function myFunctionUrgentByItem($abcd) {
                var username = document.getElementById("PCodetxt").value;
                window.alert("Hello! I am an alert box!!" + username + $abcd);

                var input = document.getElementById('PCodetxt');
                var value = input.value;

                localStorage.setItem('username', "U#" + value);
                window.location.href = 'urgentFoodItem.jsp';
            }
        </script>
        <link rel="stylesheet" type="text/css" href="pda.css">
    </head>
    <body>
        <table width="100%" border="0" id="tableBill">
            <tr>
                <th width="40" bgcolor="#00CCFF">#</th>
                <th width="155" bgcolor="#00CCFF">ประเภท</th>
                <th width="471" bgcolor="#00CCFF">รายการ</th>
                <th width="57" bgcolor="#00CCFF">จำนวน</th>
                <th width="71" bgcolor="#00CCFF">ราคา</th>
                <th width="83" bgcolor="#00CCFF">รวม</th>
                <th width="200" bgcolor="#00CCFF">พนักงาน</th>
            </tr>
            <c:forEach var="item" items="${listBalance}" varStatus="vs">
            <tr>
                <td height="55px" align="center">${vs.count}</td>
                <td height="5%" align="center">
                    <c:choose>
                        <c:when test="${item.R_ETD == 'E'}">นั่งทาน</c:when>
                        <c:when test="${item.R_ETD == 'T'}"><span style="background-color: #00ff99;">ห่อกลับ</span></c:when>
                        <c:when test="${item.R_ETD == 'D'}"><span style="background-color: #FF9966;">เดลิเวอรี่</span></c:when>
                        <c:otherwise>Unknow</c:otherwise>
                    </c:choose>
                </td>
                <td onclick="detail(this);" style="font-size: 15px;" id="${item.R_Index}">${item.R_PluCode} # ${item.R_PName}<br />
                    <c:if test="${not empty item.R_Opt1}"><u>${item.R_Opt1}</u>,</c:if>
                    <c:if test="${not empty item.R_Opt2}"><u>${item.R_Opt2}</u>,</c:if>
                    <c:if test="${not empty item.R_Opt3}"><u>${item.R_Opt3}</u>,</c:if>
                    <c:if test="${not empty item.R_Opt4}"><u>${item.R_Opt4}</u>,</c:if>
                    <c:if test="${not empty item.R_Opt5}"><u>${item.R_Opt5}</u>,</c:if>
                    <c:if test="${not empty item.R_Opt6}"><u>${item.R_Opt6}</u>,</c:if>
                    <c:if test="${not empty item.R_Opt7}"><u>${item.R_Opt7}</u>,</c:if>
                    <c:if test="${not empty item.R_Opt8}"><u>${item.R_Opt8}</u>,</c:if>
                </td>
                <td align="right"><fmt:formatNumber value="${item.R_Quan}" pattern="#,##0"/></td>
                <td align="right"><fmt:formatNumber value="${item.R_Price}" pattern="#,##0"/></td>
                <td align="right"><fmt:formatNumber value="${item.R_Total}" pattern="#,##0"/></td>
                <td align="center">${item.R_Emp}</td>
            </tr>
            </c:forEach>
        </table>
        <p><a href="Login2?prefix=${prefix}">
                <input type="button" name="button" id="button" value="กลับเมนูหลัก" style="width: 100%; height: 60px; font-size: 28px; background-color: #900; color: #FFF;">
            </a></p>
        <label>
            อาหารที่กำลังปรุง
        </label>
    <tr></tr>
    <form name="frmUrgentFood" action="urgentFoodItem.jsp">
        <table width="100%" border="0" id="tableKictran">
            <tr>
                <th width="40" bgcolor="#00CCFF">จำนวน</th>
                <th width="100" bgcolor="#00CCFF">ประเภท</th>
                <th width="471" bgcolor="#COCOCO">รายการ</th>
                <th width="65" bgcolor="#00CCFF">เวลาที่รอ ชม.นาที</th>
                <th width="65" bgcolor="#00CCFF">เชฟกดรับ</th>
                <th width="65" bgcolor="#00CCFF">สถานะ</th>
            </tr>
            <c:forEach var="kic" items="${listKicTran}">
            <tr>
                <td align="center" height="30px">${kic.PQty}</td>
                <td align="center">${kic.PEtd}</td>
                <td>${kic.PDesc}</td>
                <td align="center">${kic.PWaitTime}</td>
                <td align="center">${kic.showDisplayAlert}</td>
                <td align="center">
                    <img src="img/alert.gif" alt="Alert!" width="45" height="45"
                         onclick="location.href = 'urgentFoodItem.jsp?tableNo=${table}&pluCode=${kic.PCode}&pluName=${kic.PDesc}&pindex=${kic.PIndex}';"
                         style="cursor: pointer;">
                </td>
            </tr>
            </c:forEach>
        </table>
    </form>
    <table width="100%" border="0" id="empty0">
        <tr>
            <td width="76" align="center" heignt="100"><input value="กรุณาเลือกเครื่องปริ้นต์" type="button" name="button3" style="width: 100%; height: 60px; font-size: 24px; background-color: #FFE4B5; color: #212F3D; border-radius: 10px 0px 10px 0px; border: 1px solid;"></td>
        </tr>
    </table>
    <form name="frmAdd" method="get" action="printCheckBill.jsp">
        <div style=" alignment-adjust:  central; width: 100%; border: 10px #002A80; border-collapse:  collapse; align-items:  center">
            <table width="100%" border="0" id="footer" align="center">
                <tr>
                    <td align="center" border="0">
                        <input type="radio" name="chkType" id="radio1" value="Station1" checked="CHECKED">
                        <input type="button" style="font-size: 22px; width: 200px; background-color: #FFE4B5; padding-bottom: 10px;" onclick="show1();" value="Station1"><br />
                        <input type="radio" name="chkType" id="radio2" value="Station2">
                        <input type="button" style="font-size: 22px; width: 200px; background-color:  #FFE4B5; padding-bottom: 10px;" onclick="show2();" value="Station2"><br />
                        <input type="radio" name="chkType" id="radio3" value="Station3">
                        <input type="button" style="font-size: 22px; width: 200px; background-color:  #FFE4B5; padding-bottom: 10px;" onclick="show3();" value="Station3"><br />
                        <input type="radio" name="chkType" id="radio4" value="Station4">
                        <input type="button" style="font-size: 22px; width: 200px; background-color:  #FFE4B5; padding-bottom: 10px;" onclick="show4();" value="Station4"><br />
                        <input type="radio" name="chkType" id="radio5" value="Station5">
                        <input type="button" style="font-size: 22px; width: 200px; background-color:  #FFE4B5; padding-bottom: 10px;" onclick="show5();" value="Station5"><br />
                        <input type="radio" name="chkType" id="radio6" value="Station6">
                        <input type="button" style="font-size: 22px; width: 200px; background-color:  #FFE4B5; padding-bottom: 10px;" onclick="show6();" value="Station6"><br />
                    </td>
                </tr>
            </table>
        </div>
        <div align="center">
            <td width="76" align="center">
                <input type="text" name="table" id="table" value="${table}">
                <input type="submit" name="print" id="print" value="Check Bill Print" style="width: 100%; height: 80px; font-size: 30px; background-color: #909; color: #fff; border-radius: 10px 0px 10px 0px; border: 1px solid;">
            </td>
        </div>
        <div align="center">
            <td width="76" align="center">
                <input type="text" name="table" id="table" value="${table}">
                <input type="submit" name="printAll" id="print" value="Check Bill All" style="width: 100%; height: 80px; font-size: 30px; background-color: #909; color: #fff; border-radius: 10px 0px 10px 0px; border: 1px solid;">
            </td>
        </div>
        <div align="center">
            <p><a href="Login2?prefix=${prefix}">
                    <input type="button" name="button" id="button20" value="กลับเมนูหลัก" style="width: 100%; height: 60px; font-size: 28px; background-color: #900; color: #FFF;">
                </a></p>
        </div>
    </form>
    <div align="center">
        <p><a href="printKictranAgain.jsp?table=${table}"></a></p>
        <td width="76" align="center">
            <input type="submit" name="print" id="urgentfood" value="ตามอาหารที่ยังไม่ได้" style="width: 100%; height: 80px; font-size: 30px; background-color: dodgerblue; color: #fff; border-radius: 10px 0px 10px 0px; border: 1px solid;">
        </td>
    </div>
</body>
</html>
