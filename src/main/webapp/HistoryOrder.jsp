<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>All Item Page</title>
        <script type="text/javascript" src="js/jquery-latest.min.js"></script>
        <script type="text/javascript">
            function delData(prefix, r_index, pcode, qty) {
                $.get("Remove?prefix=" + prefix + "&R_Index=" + r_index + "&PCode=" + qty + "*" + pcode, function (responseJson) {
                    if (responseJson !== null) {
                        $.each(responseJson, function (key, value) {});
                    }
                });
            }

            function selectStation(n) {
                document.getElementById("radio" + n).checked = true;
                document.querySelectorAll(".btn-station").forEach(function(b) { b.classList.remove("active"); });
                document.getElementById("btnStation" + n).classList.add("active");
            }

            function detail(R_Index) {
                var text       = R_Index.id;
                var prefix     = document.getElementById("txtPrefix").value;
                var clickPrint = document.getElementById("print").value;
                window.location = "Order?R_Index=" + text + "&prefix=" + prefix + clickPrint;
            }
        </script>
        <style>
            * { box-sizing: border-box; margin: 0; padding: 0; }

            body {
                font-family: 'Sarabun', 'Segoe UI', sans-serif;
                background-color: #f0f4f8;
                font-size: 15px;
            }

            .page-wrapper { padding: 12px; }

            /* ── Section label ── */
            .section-label {
                font-size: 13px;
                font-weight: 700;
                color: #888;
                text-transform: uppercase;
                letter-spacing: .5px;
                margin: 14px 0 6px;
            }

            /* ── Card table ── */
            .card-table {
                width: 100%;
                border-collapse: collapse;
                background: #fff;
                border-radius: 10px;
                overflow: hidden;
                box-shadow: 0 2px 8px rgba(0,0,0,0.10);
            }

            .card-table thead th {
                background-color: #1a3a6b;
                color: #fff;
                font-size: 13px;
                font-weight: 600;
                padding: 11px 8px;
                text-align: center;
            }

            .card-table tbody tr { border-bottom: 1px solid #e8eef6; }
            .card-table tbody tr:last-child { border-bottom: none; }
            .card-table tbody tr:nth-child(even) { background-color: #f7faff; }

            .card-table tbody td {
                padding: 10px 8px;
                font-size: 14px;
                vertical-align: middle;
            }

            /* ── Bill table columns ── */
            .col-num   { text-align: center; color: #999; font-size: 13px; width: 36px; }
            .col-type  { text-align: center; width: 90px; }
            .col-item  { cursor: pointer; }
            .col-qty, .col-price { text-align: right; }
            .col-total { text-align: right; font-weight: 700; color: #1a3a6b; }
            .col-emp   { text-align: center; font-size: 13px; color: #555; }

            .item-code { font-size: 12px; color: #aaa; }
            .item-name { font-size: 15px; font-weight: 600; color: #1a3a6b; }
            .item-opts { margin-top: 4px; }

            .opt-chip {
                display: inline-block;
                background: #e8f0fe;
                color: #3b6fb6;
                font-size: 11px;
                border-radius: 10px;
                padding: 2px 7px;
                margin: 2px 2px 0 0;
            }

            /* ── ETD badges ── */
            .badge {
                display: inline-block;
                border-radius: 10px;
                padding: 3px 8px;
                font-size: 12px;
                font-weight: 600;
                white-space: nowrap;
            }
            .badge-eat     { background: #dbeafe; color: #1d4ed8; }
            .badge-take    { background: #dcfce7; color: #166534; }
            .badge-deliver { background: #ffedd5; color: #9a3412; }

            /* ── Kitchen table ── */
            .card-table.kic thead th { background-color: #7c4f00; }
            .kic-wait { font-weight: 700; color: #7c4f00; }

            .btn-urgent {
                display: block;
                width: 100%;
                padding: 8px 4px;
                background: #e53e3e;
                color: #fff;
                border: none;
                border-radius: 7px;
                font-size: 13px;
                font-weight: 700;
                cursor: pointer;
                text-align: center;
            }
            .btn-urgent:hover { background: #c53030; }

            /* ── Station selector ── */
            .station-grid {
                display: grid;
                grid-template-columns: repeat(3, 1fr);
                gap: 8px;
                background: #fff;
                border-radius: 10px;
                box-shadow: 0 2px 8px rgba(0,0,0,0.10);
                padding: 14px;
            }

            .station-grid input[type="radio"] { display: none; }

            .btn-station {
                width: 100%;
                padding: 13px 4px;
                border: 2px solid #d0d8e4;
                border-radius: 8px;
                background: #f7faff;
                color: #555;
                font-size: 15px;
                font-weight: 600;
                cursor: pointer;
                transition: all .15s;
            }

            .btn-station.active {
                background: #1a3a6b;
                border-color: #1a3a6b;
                color: #fff;
            }

            /* ── Action buttons ── */
            .btn-print {
                display: block;
                width: 100%;
                padding: 16px;
                background: #7b2d8b;
                color: #fff;
                border: none;
                border-radius: 10px;
                font-size: 20px;
                font-weight: 700;
                cursor: pointer;
                margin-bottom: 10px;
            }
            .btn-print:hover { background: #5e1f6e; }

            .btn-back {
                display: block;
                width: 100%;
                padding: 14px;
                background: #900;
                color: #fff;
                border: none;
                border-radius: 10px;
                font-size: 18px;
                font-weight: 700;
                cursor: pointer;
                text-decoration: none;
                text-align: center;
                margin-bottom: 10px;
            }
            .btn-back:hover { background: #700; }

            .btn-urgent-food {
                display: block;
                width: 100%;
                padding: 16px;
                background: #1a6fb5;
                color: #fff;
                border: none;
                border-radius: 10px;
                font-size: 20px;
                font-weight: 700;
                cursor: pointer;
                text-decoration: none;
                text-align: center;
            }
            .btn-urgent-food:hover { background: #135590; }

            .empty-kic {
                text-align: center;
                padding: 20px;
                color: #bbb;
                font-size: 14px;
                background: #fff;
                border-radius: 10px;
                box-shadow: 0 2px 8px rgba(0,0,0,0.06);
            }
        </style>
        <link rel="stylesheet" type="text/css" href="css/pda.css">
    </head>
    <body>
        <input type="hidden" id="txtPrefix" value="${prefix}">

        <div class="page-wrapper">

            <%-- ── Bill table ── --%>
            <div class="section-label">รายการออเดอร์</div>
            <c:choose>
                <c:when test="${empty listBalance}">
                    <div class="empty-kic">ไม่มีรายการในบิลนี้</div>
                </c:when>
                <c:otherwise>
                    <table class="card-table">
                        <thead>
                            <tr>
                                <th class="col-num">#</th>
                                <th class="col-type">ประเภท</th>
                                <th>รายการ</th>
                                <th width="52">จำนวน</th>
                                <th width="68">ราคา</th>
                                <th width="78">รวม</th>
                                <th width="80">พนักงาน</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${listBalance}" varStatus="vs">
                            <tr>
                                <td class="col-num">${vs.count}</td>
                                <td class="col-type">
                                    <c:choose>
                                        <c:when test="${item.r_ETD == 'E'}"><span class="badge badge-eat">นั่งทาน</span></c:when>
                                        <c:when test="${item.r_ETD == 'T'}"><span class="badge badge-take">ห่อกลับ</span></c:when>
                                        <c:when test="${item.r_ETD == 'D'}"><span class="badge badge-deliver">เดลิเวอรี่</span></c:when>
                                        <c:otherwise><span class="badge">${item.r_ETD}</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="col-item" onclick="detail(this);" id="${item.r_Index}">
                                    <div class="item-code">${item.r_PluCode}</div>
                                    <div class="item-name">${item.r_PName}</div>
                                    <div class="item-opts">
                                        <c:if test="${not empty item.r_Opt1}"><span class="opt-chip">${item.r_Opt1}</span></c:if>
                                        <c:if test="${not empty item.r_Opt2}"><span class="opt-chip">${item.r_Opt2}</span></c:if>
                                        <c:if test="${not empty item.r_Opt3}"><span class="opt-chip">${item.r_Opt3}</span></c:if>
                                        <c:if test="${not empty item.r_Opt4}"><span class="opt-chip">${item.r_Opt4}</span></c:if>
                                        <c:if test="${not empty item.r_Opt5}"><span class="opt-chip">${item.r_Opt5}</span></c:if>
                                        <c:if test="${not empty item.r_Opt6}"><span class="opt-chip">${item.r_Opt6}</span></c:if>
                                        <c:if test="${not empty item.r_Opt7}"><span class="opt-chip">${item.r_Opt7}</span></c:if>
                                        <c:if test="${not empty item.r_Opt8}"><span class="opt-chip">${item.r_Opt8}</span></c:if>
                                    </div>
                                </td>
                                <td class="col-qty"><fmt:formatNumber value="${item.r_Quan}" pattern="#,##0"/></td>
                                <td class="col-price"><fmt:formatNumber value="${item.r_Price}" pattern="#,##0"/></td>
                                <td class="col-total"><fmt:formatNumber value="${item.r_Total}" pattern="#,##0"/></td>
                                <td class="col-emp">${item.r_Emp}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>

            <a class="btn-back" href="Login2?prefix=${prefix}" style="margin-top:12px;">กลับเมนูหลัก</a>

            <%-- ── Kitchen table ── --%>
            <div class="section-label">อาหารที่กำลังปรุง</div>
            <c:choose>
                <c:when test="${empty listKicTran}">
                    <div class="empty-kic">ไม่มีรายการที่กำลังปรุง</div>
                </c:when>
                <c:otherwise>
                    <form name="frmUrgentFood" action="UrgentFoodItem">
                        <table class="card-table kic">
                            <thead>
                                <tr>
                                    <th width="50">จำนวน</th>
                                    <th width="80">ประเภท</th>
                                    <th>รายการ</th>
                                    <th width="70">รอ ชม:นาที</th>
                                    <th width="60">เชฟรับ</th>
                                    <th width="70">ตาม</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="kic" items="${listKicTran}">
                                <tr>
                                    <td align="center" style="font-weight:700;">${kic.PQty}</td>
                                    <td align="center">${kic.PEtd}</td>
                                    <td>${kic.PDesc}</td>
                                    <td align="center" class="kic-wait">${kic.PWaitTime}</td>
                                    <td align="center" style="font-size:13px;">${kic.showDisplayAlert}</td>
                                    <td align="center">
                                        <button type="button" class="btn-urgent"
                                                onclick="location.href='UrgentFoodItem?tableNo=${table}&pluCode=${kic.PCode}&pluName=${kic.PDesc}&pindex=${kic.PIndex}'">
                                            ตาม!
                                        </button>
                                    </td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </form>
                </c:otherwise>
            </c:choose>

            <%-- ── Station selector ── --%>
            <div class="section-label">เลือกเครื่องปริ้นต์</div>
            <form name="frmAdd" method="get" action="PrintCheckBill">
                <input type="hidden" name="table" value="${table}">

                <div class="station-grid">
                    <input type="radio" name="chkType" id="radio1" value="Station1" checked>
                    <button type="button" id="btnStation1" class="btn-station active" onclick="selectStation(1)">Station 1</button>

                    <input type="radio" name="chkType" id="radio2" value="Station2">
                    <button type="button" id="btnStation2" class="btn-station" onclick="selectStation(2)">Station 2</button>

                    <input type="radio" name="chkType" id="radio3" value="Station3">
                    <button type="button" id="btnStation3" class="btn-station" onclick="selectStation(3)">Station 3</button>

                    <input type="radio" name="chkType" id="radio4" value="Station4">
                    <button type="button" id="btnStation4" class="btn-station" onclick="selectStation(4)">Station 4</button>

                    <input type="radio" name="chkType" id="radio5" value="Station5">
                    <button type="button" id="btnStation5" class="btn-station" onclick="selectStation(5)">Station 5</button>

                    <input type="radio" name="chkType" id="radio6" value="Station6">
                    <button type="button" id="btnStation6" class="btn-station" onclick="selectStation(6)">Station 6</button>
                </div>

                <div style="margin-top:12px;">
                    <button type="submit" name="print" id="print" class="btn-print">Check Bill Print</button>
                    <button type="submit" name="printAll" class="btn-print">Check Bill All</button>
                </div>

                <a class="btn-back" href="Login2?prefix=${prefix}">กลับเมนูหลัก</a>
            </form>

            <%-- ── Urgent food ── --%>
            <a class="btn-urgent-food" href="PrintKictranAgain?table=${table}">ตามอาหารที่ยังไม่ได้</a>
        </div>
    </body>
</html>
