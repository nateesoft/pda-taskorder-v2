<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Order List</title>
        <script type="text/javascript" src="jquery-latest.min.js"></script>
        <script type="text/javascript">
            function delData(prefix, r_index, pcode, qty) {
                window.location = "Remove?prefix=" + prefix + "&R_Index=" + r_index + "&PCode=" + qty + "*" + pcode;
            }
            function detail(R_Index) {
                var text = R_Index.id;
                var prefix = document.getElementById("txtPrefix").value;
                window.location = "Order?R_Index=" + text + "&prefix=" + prefix;
            }
        </script>
        <style>
            * { box-sizing: border-box; margin: 0; padding: 0; }

            body {
                font-family: 'Sarabun', 'Segoe UI', sans-serif;
                background-color: #f0f4f8;
            }

            .page-wrapper {
                padding: 12px;
            }

            .page-title {
                text-align: center;
                font-size: 20px;
                font-weight: bold;
                color: #1a3a6b;
                background: #fff;
                border-radius: 8px;
                padding: 12px;
                margin-bottom: 12px;
                box-shadow: 0 1px 4px rgba(0,0,0,0.1);
            }

            .order-table {
                width: 100%;
                border-collapse: collapse;
                background: #fff;
                border-radius: 10px;
                overflow: hidden;
                box-shadow: 0 2px 8px rgba(0,0,0,0.12);
            }

            .order-table thead th {
                background-color: #1a3a6b;
                color: #fff;
                font-size: 14px;
                font-weight: 600;
                padding: 12px 8px;
                text-align: center;
            }

            .order-table tbody tr {
                border-bottom: 1px solid #e0e8f0;
            }

            .order-table tbody tr:last-child {
                border-bottom: none;
            }

            .order-table tbody tr:nth-child(even) {
                background-color: #f7faff;
            }

            .order-table tbody td {
                padding: 10px 8px;
                font-size: 15px;
                vertical-align: middle;
            }

            .col-num   { text-align: center; color: #999; font-size: 13px; }
            .col-type  { text-align: center; }
            .col-item  { cursor: pointer; }
            .col-qty, .col-price { text-align: right; }
            .col-total { text-align: right; font-weight: bold; color: #1a3a6b; }
            .col-cancel{ text-align: center; }

            .item-code { font-size: 12px; color: #999; }
            .item-name { font-size: 16px; font-weight: 600; color: #1a3a6b; margin-top: 2px; }
            .item-opts { margin-top: 5px; }

            .opt-chip {
                display: inline-block;
                background-color: #e8f0fe;
                color: #3b6fb6;
                font-size: 12px;
                border-radius: 10px;
                padding: 2px 8px;
                margin: 2px 2px 0 0;
            }

            .badge {
                display: inline-block;
                border-radius: 12px;
                padding: 4px 10px;
                font-size: 13px;
                font-weight: 600;
                white-space: nowrap;
            }

            .badge-eat     { background-color: #dbeafe; color: #1d4ed8; }
            .badge-take    { background-color: #dcfce7; color: #166534; }
            .badge-deliver { background-color: #ffedd5; color: #9a3412; }

            .btn-cancel {
                display: block;
                padding: 10px 6px;
                background-color: #e53e3e;
                color: #fff;
                border: none;
                border-radius: 8px;
                font-size: 14px;
                font-weight: bold;
                cursor: pointer;
                text-decoration: none;
                text-align: center;
            }

            .btn-cancel:hover { background-color: #c53030; }

            .btn-back {
                display: block;
                width: 100%;
                padding: 16px;
                margin-top: 14px;
                background-color: #900;
                color: #fff;
                border-radius: 10px;
                font-size: 24px;
                font-weight: bold;
                text-decoration: none;
                text-align: center;
            }

            .btn-back:hover { background-color: #700; }

            .empty-state {
                text-align: center;
                padding: 48px;
                color: #999;
                font-size: 18px;
                background: #fff;
                border-radius: 10px;
                box-shadow: 0 2px 8px rgba(0,0,0,0.08);
            }
        </style>
        <link rel="stylesheet" type="text/css" href="pda.css">
    </head>
    <body>
        <div class="page-wrapper">
            <input type="hidden" id="txtPrefix" value="${prefix}">

            <div class="page-title">รายการออเดอร์</div>

            <c:choose>
                <c:when test="${empty listBalance}">
                    <div class="empty-state">ไม่มีรายการในบิลนี้</div>
                </c:when>
                <c:otherwise>
                    <table class="order-table">
                        <thead>
                            <tr>
                                <th width="36">#</th>
                                <th width="100">ประเภท</th>
                                <th>รายการ</th>
                                <th width="58">จำนวน</th>
                                <th width="78">ราคา</th>
                                <th width="88">รวม</th>
                                <th width="82">ยกเลิก</th>
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
                                <td class="col-cancel">
                                    <c:if test="${item.r_Pause ne 'P'}">
                                        <a class="btn-cancel" href="javascript:delData('${prefix}','${item.r_Index}','${item.r_Quan}','${item.r_PluCode}')">ยกเลิก</a>
                                    </c:if>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>

            <a class="btn-back" href="Login2?prefix=${prefix}">กลับสู่เมนู</a>
        </div>
    </body>
</html>
