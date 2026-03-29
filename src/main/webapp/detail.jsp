<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>PDA-ICS</title>
        <script src="js/jquery.min.js"></script>
        <script type="text/javascript">
            function back() {
                window.location = "OrderList?prefix=A";
            }
            function addQty() {
                var qtyAdd = document.getElementById("txtQtyPlus");
                qtyAdd.value = parseInt(qtyAdd.value) + 1;
            }
            function addNew() {
                var qtyCount = parseInt(document.getElementById("txtQtyPlus").value);
                var prefix   = document.getElementById("txtPrefix").value;
                var PCode    = document.getElementById("txtPCode").value;
                $.get("Save?prefix=" + prefix + "&PCode=" + qtyCount + "*" + PCode, function (responseJson) {
                    if (responseJson !== null) {
                        $.each(responseJson, function (key, value) {
                            window.location = "OrderList?prefix=" + prefix;
                        });
                    }
                });
            }
            function chkThis(v) {
                var cb  = document.getElementById("chkOpt" + v);
                var btn = document.getElementById("btnOpt" + v);
                if (cb.checked) {
                    cb.checked = false;
                    btn.classList.remove("active");
                } else {
                    cb.checked = true;
                    btn.classList.add("active");
                }
            }
            function chk(c) {
                var map = { E: "chkType1", T: "chkType2", D: "chkType3" };
                var btnMap = { E: "btnType1", T: "btnType2", D: "btnType3" };
                document.querySelectorAll(".btn-etd").forEach(function(b) { b.classList.remove("active"); });
                var radioId = map[c] || "chkType1";
                var btnId   = btnMap[c] || "btnType1";
                document.getElementById(radioId).checked = true;
                document.getElementById(btnId).classList.add("active");
            }
        </script>
        <style>
            * { box-sizing: border-box; margin: 0; padding: 0; }

            body {
                font-family: 'Sarabun', 'Segoe UI', sans-serif;
                background-color: #f0f4f8;
                font-size: 15px;
            }

            .page-wrapper {
                padding: 12px;
                max-width: 560px;
                margin: 0 auto;
            }

            /* ── Cards ── */
            .card {
                background: #fff;
                border-radius: 10px;
                box-shadow: 0 2px 8px rgba(0,0,0,0.10);
                padding: 16px;
                margin-bottom: 12px;
            }

            .card-label {
                font-size: 12px;
                font-weight: 700;
                color: #888;
                text-transform: uppercase;
                letter-spacing: .5px;
                margin-bottom: 8px;
            }

            /* ── Product card ── */
            .product-code  { font-size: 13px; color: #999; margin-bottom: 4px; }
            .product-name  { font-size: 20px; font-weight: 700; color: #1a3a6b; margin-bottom: 10px; }
            .product-meta  { display: flex; gap: 10px; align-items: center; }

            .badge-price {
                background: #1a3a6b;
                color: #fff;
                border-radius: 8px;
                padding: 5px 12px;
                font-size: 16px;
                font-weight: 600;
            }

            .badge-qty {
                background: #e8f0fe;
                color: #1a3a6b;
                border-radius: 8px;
                padding: 5px 12px;
                font-size: 15px;
                font-weight: 600;
            }

            /* ── ETD toggle buttons ── */
            .etd-group {
                display: flex;
                gap: 8px;
            }

            .etd-group input[type="radio"] { display: none; }

            .btn-etd {
                flex: 1;
                padding: 12px 4px;
                border: 2px solid #d0d8e4;
                border-radius: 8px;
                background: #f7faff;
                color: #555;
                font-size: 15px;
                font-weight: 600;
                cursor: pointer;
                transition: all .15s;
            }

            .btn-etd.active {
                background: #1a3a6b;
                border-color: #1a3a6b;
                color: #fff;
            }

            /* ── Options ── */
            .opts-grid {
                display: flex;
                flex-wrap: wrap;
                gap: 8px;
                max-height: 180px;
                overflow-y: auto;
                padding: 4px 0;
            }

            .opts-grid input[type="checkbox"] { display: none; }

            .btn-opt {
                padding: 10px 14px;
                border: 2px solid #2e7d32;
                border-radius: 8px;
                background: #fff;
                color: #2e7d32;
                font-size: 15px;
                font-weight: 600;
                cursor: pointer;
                transition: all .15s;
            }

            .btn-opt.active {
                background: #2e7d32;
                color: #fff;
            }

            .note-input {
                width: 100%;
                margin-top: 10px;
                padding: 10px 12px;
                border: 1.5px solid #d0d8e4;
                border-radius: 8px;
                font-size: 15px;
                font-family: inherit;
            }

            .note-hint {
                font-size: 12px;
                color: #aaa;
                margin-bottom: 6px;
            }

            /* ── Add-qty row ── */
            .add-qty-row {
                display: flex;
                align-items: center;
                gap: 8px;
            }

            .qty-display {
                width: 60px;
                padding: 8px;
                text-align: center;
                border: 1.5px solid #d0d8e4;
                border-radius: 8px;
                font-size: 18px;
                font-weight: 700;
                color: #1a3a6b;
            }

            .btn-plus {
                width: 44px;
                height: 44px;
                background: #e8f0fe;
                border: none;
                border-radius: 8px;
                font-size: 22px;
                font-weight: 700;
                color: #1a3a6b;
                cursor: pointer;
            }

            .btn-plus:hover { background: #d0e0fa; }

            .btn-add {
                flex: 1;
                padding: 12px;
                background: #1a3a6b;
                color: #fff;
                border: none;
                border-radius: 8px;
                font-size: 16px;
                font-weight: 700;
                cursor: pointer;
            }

            .btn-add:hover { background: #142d55; }

            /* ── Action footer ── */
            .btn-confirm {
                display: block;
                width: 100%;
                padding: 16px;
                background: #1a3a6b;
                color: #fff;
                border: none;
                border-radius: 10px;
                font-size: 20px;
                font-weight: 700;
                cursor: pointer;
                margin-bottom: 10px;
            }

            .btn-confirm:hover { background: #142d55; }

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
            }

            .btn-back:hover { background: #700; }

            /* ── No-session state ── */
            .no-session {
                background: #fff;
                border-radius: 10px;
                padding: 40px;
                text-align: center;
                color: #555;
                font-size: 16px;
                box-shadow: 0 2px 8px rgba(0,0,0,0.08);
            }

            /* ── Responsive: small phones (< 360px) ── */
            @media (max-width: 359px) {
                .product-name      { font-size: 16px; }
                .badge-price       { font-size: 13px; padding: 4px 8px; }
                .badge-qty         { font-size: 13px; padding: 4px 8px; }
                .btn-etd           { font-size: 13px; padding: 10px 2px; }
                .btn-opt           { font-size: 13px; padding: 8px 10px; }
                .card              { padding: 12px; }
                .btn-confirm       { font-size: 17px; padding: 13px; }
                .btn-back          { font-size: 15px; padding: 11px; }
            }

            /* ── Responsive: tablets (768px+) ── */
            @media (min-width: 768px) {
                .page-wrapper      { padding: 20px; }
                .card              { padding: 20px; margin-bottom: 16px; }
                .card-label        { font-size: 13px; margin-bottom: 12px; }
                .product-code      { font-size: 14px; }
                .product-name      { font-size: 24px; margin-bottom: 14px; }
                .badge-price       { font-size: 18px; padding: 7px 16px; }
                .badge-qty         { font-size: 17px; padding: 7px 16px; }
                .btn-etd           { font-size: 17px; padding: 15px 8px; }
                .btn-opt           { font-size: 16px; padding: 12px 18px; }
                .opts-grid         { max-height: 240px; gap: 10px; }
                .note-input        { font-size: 16px; padding: 12px 14px; }
                .qty-display       { width: 80px; font-size: 22px; }
                .btn-plus          { width: 54px; height: 54px; font-size: 26px; }
                .btn-add           { font-size: 18px; }
                .btn-confirm       { font-size: 22px; padding: 18px; margin-bottom: 12px; }
                .btn-back          { font-size: 20px; padding: 16px; }
            }

            /* ── Responsive: desktop (1024px+) ── */
            @media (min-width: 1024px) {
                .page-wrapper      { max-width: 680px; padding: 28px 0; }
            }
        </style>
    </head>

    <body>
        <form action="Update" method="post">
        <c:set var="priceFormatted"><fmt:formatNumber value="${bean.r_Price}" pattern="#,##0.00"/></c:set>

        <div class="page-wrapper">
            <c:choose>
                <c:when test="${empty macNo}">
                    <div class="no-session">
                        สำหรับสั่งอาหาร
                    </div>
                </c:when>
                <c:otherwise>
                    <input type="hidden" id="txtRIndex" name="txtRIndex" value="${bean.r_Index}" />
                    <input type="hidden" id="txtPrefix" name="txtPrefix" value="${prefix}" />

                    <%-- Product info --%>
                    <div class="card">
                        <div class="product-code">${bean.r_PluCode}</div>
                        <div class="product-name">${bean.r_PName}</div>
                        <div class="product-meta">
                            <span class="badge-price">${priceFormatted} บาท</span>
                            <span class="badge-qty">x <fmt:formatNumber value="${bean.r_Quan}" pattern="#,##0"/></span>
                        </div>
                        <input type="hidden" id="txtPCode" name="txtPCode" value="${bean.r_PluCode}">
                    </div>

                    <%-- Sale type --%>
                    <div class="card">
                        <div class="card-label">ประเภทการสั่ง</div>
                        <div class="etd-group">
                            <input type="radio" name="chkType" id="chkType1" value="E" <c:if test="${saleType == 'E'}">checked</c:if>>
                            <button type="button" id="btnType1" class="btn-etd <c:if test="${saleType == 'E'}">active</c:if>" onclick="chk('E')">นั่งทาน</button>

                            <input type="radio" name="chkType" id="chkType2" value="T" <c:if test="${saleType == 'T'}">checked</c:if>>
                            <button type="button" id="btnType2" class="btn-etd <c:if test="${saleType == 'T'}">active</c:if>" onclick="chk('T')">ห่อกลับ</button>

                            <input type="radio" name="chkType" id="chkType3" value="D" <c:if test="${saleType == 'D'}">checked</c:if>>
                            <button type="button" id="btnType3" class="btn-etd <c:if test="${saleType == 'D'}">active</c:if>" onclick="chk('D')">เดลิเวอรี่</button>
                        </div>
                    </div>

                    <%-- Options --%>
                    <div class="card">
                        <div class="card-label">ตัวเลือกเพิ่มเติม</div>
                        <div class="opts-grid">
                            <c:forEach var="opt" items="${options}" varStatus="vs">
                            <c:if test="${not empty opt}">
                                <input type="checkbox" name="chkOpt" id="chkOpt${vs.index}" value="${opt}">
                                <button type="button" id="btnOpt${vs.index}" class="btn-opt" onclick="chkThis('${vs.index}')">${opt}</button>
                            </c:if>
                            </c:forEach>
                        </div>
                        <div class="note-hint" style="margin-top:12px;">หมายเหตุเพิ่มเติม เช่น "ไม่หวาน,ไม่ใส่ผัก"</div>
                        <input type="text" name="optAddNew" id="optAddNew" class="note-input" placeholder="พิมพ์หมายเหตุ..." />
                    </div>

                    <%-- Add quantity (only if not paused) --%>
                    <c:if test="${bean.r_Pause ne 'P'}">
                    <div class="card">
                        <div class="card-label">เพิ่มจำนวน</div>
                        <div class="add-qty-row">
                            <input type="number" name="txtQtyPlus" id="txtQtyPlus" class="qty-display" value="1" readonly>
                            <button type="button" class="btn-plus" onclick="addQty()">+</button>
                            <button type="button" class="btn-add" onclick="addNew()">เพิ่มรายการ</button>
                        </div>
                    </div>
                    </c:if>

                    <%-- Actions --%>
                    <button type="submit" class="btn-confirm">ยืนยัน</button>
                    <button type="button" class="btn-back" onclick="back()">กลับเมนูหลัก</button>

                </c:otherwise>
            </c:choose>
        </div>
        </form>
    </body>
</html>
