<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PDA-ICS</title>
        <script type="text/javascript" src="jquery-latest.min.js"></script>
        <script type="text/javascript" src="pda.js"></script>
        <link rel="stylesheet" type="text/css" href="pda.css">

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />

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
                $.get("Save?prefix=" + prefix + "&PCode=" + txtQty + "*" + pcode, function (responseJson) {
                    if (responseJson !== null) {
                        $.each(responseJson, function (key, value) {
                            if (value.indexOf("Error:") !== -1) {
                                alert(value);
                            } else {
                                var data = value.split(",");
                                document.getElementById("btnListBill").value = "ใหม่(" + data[0] + ")";
                                var cTotal = parseFloat(data[1]).toFixed(2);
                                document.getElementById("totalBill").innerHTML = comma(cTotal);
                                document.getElementById("txtOrderQty").value = "1";
                                toastr["success"]("ท่านสั่งสินค้ารหัส " + pcode + " เรียบร้อยแล้ว");
                            }
                        });
                    }
                });
            }

            function showSearchByName() {
                var textSearch = document.getElementById("txtSearch").value;
                if (textSearch !== "") {
                    document.getElementById("divShowDataItem").style.display = "none";
                    document.getElementById("divSearch").style.display = "";
                    var html = "<table width='100%' border='0' cellpadding='5' cellspacing='5' style='border-collapse: collapse;'>";
                    $.get("Search?w=" + textSearch, function (responseJson) {
                        if (responseJson !== null) {
                            $.each(responseJson, function (key, value) {
                                html += "<tr>";
                                html += "<td style='font-size: 24px; font-weight: bold;'>" + value['ShortName'] + "</td>";
                                html += "<td align='center'><input type='hidden' value='1' style='width: 50px; text-align: right; font-size: 24px;' /></td>";
                                html += "<td align='center'><input type='button' value='สั่ง' style='font-size: 24px; width: 75px; background-color: #F69;' onclick=saveData('A','" + value['PCode'] + "') /></td>";
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
                    $.get("Search?wc=" + txtSearchCode, function (responseJson) {
                        if (responseJson !== null) {
                            $.each(responseJson, function (key, value) {
                                html += "<tr>";
                                html += "<td style='font-size: 24px; font-weight: bold;'>" + value['ShortName'] + "</td>";
                                html += "<td align='center'><input type='hidden' value='1' style='width: 50px; text-align: right; font-size: 24px;' /></td>";
                                html += "<td align='center'><input type='button' value='สั่ง' style='font-size: 24px; width: 75px; background-color: #F69;' onclick=saveData('A','" + value['PCode'] + "') /></td>";
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
        <c:choose>
            <c:when test="${empty macNo}">
                กรุณากำหนดหมายเลขเครื่อง สำหรับสั่งอาหาร
            </c:when>
            <c:otherwise>
                <div style="align-content: center; width: 100%; height: 100%">
                    <input type="hidden" name="txtMacNo"  id="txtMacNo"  value="${macNo}" />
                    <input type="hidden" name="txtTableNo" id="txtTableNo" value="${tableNo}" />
                    <input type="hidden" name="txtPrefix"  id="txtPrefix"  value="${prefix}" />

                    <!-- Top bar: table number, qty buttons, total -->
                    <table bgcolor="orange" border="1" style="border-radius: 10px; border-color: #ffffff; border: solid 5px; width: 100%;">
                        <tr>
                            <td width="56" height="59" align="center" style="font-size: 18px; font-weight: bold;">โต๊ะ</td>
                            <td width="24" align="center" style="font-size: 18px; font-weight: bold;">${tableNo}</td>
                            <td width="110" align="center" style="font-size: 18px; font-weight: bold;">
                                <input type="button" value="+" style="height: 100%; font-size: 18px; width: 25px; text-align: center; font-weight: bold;" onclick="plus();">
                                <input type="number" name="txtOrderQty" id="txtOrderQty" style="height: 20px; font-size: 18px; width: 30px; text-align: center; font-weight: bold;" value="1">
                                <input type="button" value="-" style="height: 100%; font-size: 18px; width: 25px; text-align: center; font-weight: bold;" onclick="minus();">
                            </td>
                            <td width="57" align="right" id="totalBill" style="font-size: 18px; border: 1px solid; font-weight: bold;">${totalBill}</td>
                        </tr>
                    </table>

                    <!-- Header menu tabs + search inputs -->
                    <div style="width: 100%; overflow: auto;">
                        <table width="100%" border="0">
                            <tr>
                                <c:forEach items="${headerMenu}" var="hLabel" varStatus="hs">
                                    <td height="10" align="left">
                                        <a href="Login2?prefix=${headerPrefixes[hs.index]}">
                                            <input type="button" value="${hLabel}" class="btnGroup"
                                                   style="width: 130px; border-radius: 0px 10px 0px 10px; font-size: 17px; text-align: center;" />
                                        </a>
                                    </td>
                                </c:forEach>
                                <td>
                                    <input name="txtSearchCode" type="text" id="txtSearchCode"
                                           placeholder="Code Search"
                                           style="width: 110px; height: 30px; border-radius: 10px; font-size: 17px; text-align: center;"
                                           onkeyup="showSearchByCode();" />
                                </td>
                                <td>
                                    <input name="txtSearch" type="text" id="txtSearch"
                                           placeholder="Name Search"
                                           style="width: 110px; height: 30px; border-radius: 10px; font-size: 17px; text-align: center;"
                                           onkeyup="showSearchByName();" />
                                </td>
                            </tr>
                        </table>
                    </div>

                    <!-- START SHOW DATA ITEM -->
                    <div id="divShowDataItem" style="alignment-adjust: central; width: 100%">
                        <table width="100%" border="0">
                            <tr>
                            <c:forEach items="${menuItems}" var="item" varStatus="s">
                                <td height="80" align="center" bgcolor="${item.bgColor}">
                                    <c:choose>
                                        <c:when test="${item.backButton}">
                                            <a href="Login2?prefix=${item.backPrefix}">
                                                <input type="button" value="เมนูหลัก"
                                                       style="font-size: 17px; height: 60px; background-color: #C33; color: #FFF; border: 0px solid #666; border-radius: 25px;" />
                                            </a>
                                        </c:when>
                                        <c:when test="${item.menu.code_Type eq 'P' and not empty item.menu.shortName}">
                                            <a href="#${item.menu.PCode}" rel="modal:open">
                                                <img src="img/${item.menu.PCode}.png" alt="No IMG!" width="75" height="75">
                                            </a>
                                            <div id="${item.menu.PCode}" class="modal">
                                                <div align="center">แสดงรายละเอียดสินค้า</div>
                                                <img id="imgShow" src="img/${item.menu.PCode}.png" width="100%" height="100%" alt="" />
                                                <div align="center">
                                                    <button onclick="saveData('${prefix}','${item.menu.PCode}')">สั่งสินค้า</button>
                                                </div>
                                            </div>
                                            <a href="javascript:saveData('${prefix}','${item.menu.PCode}')" style="text-decoration: none;">
                                                <button class="border2" style="width: 75px; height: 75px; font-size: ${fontSize}; color: #000;">${item.menu.shortName}</button>
                                                <button class="border1" style="width: 75px; height: 38px; font-size: ${fontSize}; color: #06F;">${item.menu.PCode}</button>
                                            </a>
                                        </c:when>
                                        <c:when test="${not empty item.menu.shortName}">
                                            <a href="Login2?prefix=${item.menu.code_ID}E" style="text-decoration: none;">
                                                <button class="border1" style="width: 75px; height: 75px; font-size: ${fontSize}; color: #000;">${item.menu.shortName}</button>
                                            </a>
                                        </c:when>
                                    </c:choose>
                                </td>
                                <c:if test="${s.count % 4 == 0 and not s.last}">
                                    </tr><tr>
                                </c:if>
                            </c:forEach>
                            </tr>
                        </table>
                    </div>
                    <!-- END SHOW DATA ITEM -->

                    <!-- START DIV SHOW SEARCH -->
                    <div align="center" id="divSearch"
                         style="display: none; background-color: #DAFED8; border: 2px solid; width: 310px; height: 400px; border-radius: 10px; overflow: auto;">
                    </div>
                    <!-- END DIV SHOW SEARCH -->

                    <!-- Bottom action buttons -->
                    <table border="0" style="width: 100%; border-radius: 10px; border-color: #ffffff; border: 2px solid;">
                        <tr>
                            <td width="200" height="26" align="center">
                                <a href="Order.jsp?prefix=${prefix}">
                                    <input type="button" id="btnListBill" value="ใหม่(${billCount})"
                                           style="width: 120px; height: 45px; font-size: 17px; background-color: #909; color: #fff; border-radius: 10px 0px 10px 0px; border: 1px solid;">
                                </a>
                            </td>
                            <td width="155" align="center">
                                <a href="OrderOld.jsp?prefix=${prefix}">
                                    <input type="button" value="ตรวจสอบ"
                                           style="width: 100px; height: 45px; font-size: 17px; background-color: #909; color: #fff; border-radius: 10px 0px 10px 0px; border: 1px solid;">
                                </a>
                            </td>
                            <td width="70" align="center">
                                <input type="button" value="ส่งครัว" onclick="back();"
                                       style="width: 80px; height: 45px; font-size: 17px; background-color: #909; color: #fff; border-radius: 10px 0px 10px 0px; border: 1px solid;">
                            </td>
                        </tr>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </body>
</html>
