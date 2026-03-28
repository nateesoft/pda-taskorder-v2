<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>PDA-ICS</title>
        <script type="text/javascript" src="jquery-latest.min.js"></script>
        <script type="text/javascript" src="pda.js"></script>
        <link rel="stylesheet" type="text/css" href="pda.css">

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
        <link href="toastr.css" rel="stylesheet" type="text/css" />
        <script src="jquery.min.js"></script>
        <script src="toastr.js"></script>

        <style>
            :root {
                --green-dark:   #1a5c1a;
                --green-main:   #2e7d32;
                --green-mid:    #43a047;
                --green-light:  #a5d6a7;
                --green-pale:   #e8f5e9;
                --green-accent: #00c853;
                --amber:        #f59f00;
                --red-btn:      #c0392b;
                --purple-btn:   #7b1fa2;
                --white:        #ffffff;
                --text-dark:    #1b2b1b;
                --radius-sm:    8px;
                --radius-md:    14px;
                --radius-lg:    20px;
                --shadow-sm:    0 2px 6px rgba(0,0,0,.15);
                --shadow-md:    0 4px 14px rgba(0,0,0,.2);
            }

            * { box-sizing: border-box; margin: 0; padding: 0; }

            body {
                font-family: 'Segoe UI', Arial, sans-serif;
                background: var(--green-pale);
                color: var(--text-dark);
            }

            /* ── TOP BAR ───────────────────────────────────── */
            .top-bar {
                display: flex;
                align-items: center;
                gap: 8px;
                background: linear-gradient(135deg, var(--green-dark) 0%, var(--green-main) 60%, var(--green-mid) 100%);
                padding: 8px 12px;
                border-bottom: 3px solid var(--amber);
                position: sticky;
                top: 0;
                z-index: 100;
                box-shadow: var(--shadow-md);
            }

            .top-bar .table-badge {
                display: flex;
                flex-direction: column;
                align-items: center;
                background: rgba(255,255,255,.15);
                border: 1px solid rgba(255,255,255,.3);
                border-radius: var(--radius-sm);
                padding: 4px 10px;
                min-width: 56px;
            }
            .top-bar .table-badge .lbl { font-size: 11px; color: var(--green-light); letter-spacing:.5px; }
            .top-bar .table-badge .val { font-size: 22px; font-weight: 800; color: var(--white); line-height:1; }

            .qty-controls {
                display: flex;
                align-items: center;
                gap: 4px;
            }
            .qty-btn {
                width: 36px; height: 36px;
                border: none;
                border-radius: 50%;
                font-size: 22px; font-weight: 700;
                cursor: pointer;
                color: var(--white);
                transition: transform .1s, opacity .2s;
                display: flex; align-items: center; justify-content: center;
                box-shadow: var(--shadow-sm);
            }
            .qty-btn.plus  { background: var(--green-accent); }
            .qty-btn.minus { background: var(--amber); color: #333; }
            .qty-btn:active { transform: scale(.9); }

            #txtOrderQty {
                width: 48px; height: 36px;
                text-align: center;
                font-size: 20px; font-weight: 700;
                border: 2px solid var(--green-light);
                border-radius: var(--radius-sm);
                background: rgba(255,255,255,.9);
                color: var(--text-dark);
            }

            .total-box {
                margin-left: auto;
                background: rgba(0,0,0,.25);
                border: 1px solid rgba(255,255,255,.3);
                border-radius: var(--radius-sm);
                padding: 4px 12px;
                text-align: right;
            }
            .total-box .lbl { font-size: 11px; color: var(--green-light); }
            .total-box #totalBill { font-size: 22px; font-weight: 800; color: var(--amber); }

            /* ── MENU TAB BAR ──────────────────────────────── */
            .menu-tab-bar {
                background: linear-gradient(180deg, #1b4d1b 0%, var(--green-dark) 100%);
                padding: 8px 8px 0;
                overflow-x: auto;
                white-space: nowrap;
                display: flex;
                align-items: flex-end;
                gap: 4px;
                scrollbar-width: none;
            }
            .menu-tab-bar::-webkit-scrollbar { display: none; }

            .tab-link { text-decoration: none; flex-shrink: 0; }
            .tab-link input[type=button].btnGroup {
                border-radius: var(--radius-sm) var(--radius-sm) 0 0 !important;
                border-bottom: none !important;
                font-size: 15px !important;
                padding: 8px 14px !important;
                height: auto !important;
                width: auto !important;
                min-width: 90px;
                transition: background .2s, transform .1s;
            }
            .tab-link input[type=button].btnGroup:active { transform: translateY(1px); }

            /* ── SEARCH ROW ────────────────────────────────── */
            .search-row {
                background: var(--green-dark);
                padding: 8px 10px 10px;
                display: flex;
                gap: 8px;
                border-bottom: 2px solid var(--green-mid);
            }
            .search-row .search-wrap {
                flex: 1;
                position: relative;
            }
            .search-row .search-wrap::before {
                content: '🔍';
                position: absolute;
                left: 9px; top: 50%;
                transform: translateY(-50%);
                font-size: 14px;
                pointer-events: none;
                opacity: .6;
            }
            .search-row input[type=text] {
                width: 100%; height: 36px;
                padding: 0 10px 0 30px;
                border-radius: var(--radius-md);
                border: 2px solid var(--green-mid);
                background: rgba(255,255,255,.93);
                font-size: 15px;
                color: var(--text-dark);
                transition: border-color .2s, box-shadow .2s;
            }
            .search-row input[type=text]:focus {
                outline: none;
                border-color: var(--green-accent);
                box-shadow: 0 0 0 3px rgba(0,200,83,.25);
            }

            /* ── ITEM GRID ─────────────────────────────────── */
            #divShowDataItem {
                padding: 10px;
            }
            #divShowDataItem table {
                width: 100%;
                table-layout: fixed;
                border-collapse: separate;
                border-spacing: 6px;
            }
            #divShowDataItem td {
                width: 25%;
                aspect-ratio: 1 / 1;
                vertical-align: middle;
                padding: 4px;
                border-radius: var(--radius-sm);
                overflow: hidden;
            }
            #divShowDataItem td > a,
            #divShowDataItem td > a > button,
            #divShowDataItem td > button {
                height: 100%;
            }

            /* Product button overrides */
            .border1, .border2 {
                border-radius: var(--radius-sm) !important;
                box-shadow: var(--shadow-sm) !important;
                transition: transform .1s, box-shadow .15s !important;
                width: 100% !important;
                max-width: 100% !important;
                height: auto !important;
                min-height: 52px !important;
                float: none !important;
                display: block !important;
                word-break: break-word !important;
                white-space: normal !important;
            }
            .border1:hover, .border2:hover {
                transform: translateY(-2px) !important;
                box-shadow: var(--shadow-md) !important;
            }
            .border1:active, .border2:active {
                transform: scale(.96) !important;
            }

            /* Back / category button */
            .btn-back {
                font-size: 15px !important; height: 52px !important;
                background: linear-gradient(135deg, #c0392b, #922b21) !important;
                color: var(--white) !important;
                border: none !important;
                border-radius: var(--radius-sm) !important;
                width: 100% !important;
                max-width: 100% !important;
                box-shadow: var(--shadow-sm);
                cursor: pointer;
                display: block;
            }

            /* ── SEARCH RESULT PANEL ───────────────────────── */
            #divSearch {
                display: none;
                margin: 10px;
                background: var(--white);
                border: 2px solid var(--green-mid);
                border-radius: var(--radius-md);
                box-shadow: var(--shadow-md);
                overflow: auto;
                max-height: 380px;
            }
            #divSearch table {
                width: 100%;
                border-collapse: collapse;
            }
            #divSearch tr:not(:last-child) {
                border-bottom: 1px solid var(--green-pale);
            }
            #divSearch td {
                padding: 10px 12px;
                font-size: 16px;
            }
            #divSearch input[type=button] {
                background: linear-gradient(135deg, var(--green-main), var(--green-dark));
                color: var(--white);
                border: none;
                border-radius: var(--radius-sm);
                padding: 8px 18px;
                font-size: 16px;
                cursor: pointer;
                box-shadow: var(--shadow-sm);
                transition: opacity .2s;
            }
            #divSearch input[type=button]:hover { opacity: .85; }

            /* ── BOTTOM ACTION BAR ─────────────────────────── */
            .action-bar {
                position: sticky;
                bottom: 0;
                background: linear-gradient(135deg, var(--green-dark), #0d2b0d);
                padding: 10px 12px;
                display: flex;
                gap: 8px;
                justify-content: space-between;
                border-top: 3px solid var(--green-mid);
                box-shadow: 0 -4px 16px rgba(0,0,0,.3);
            }
            .action-bar a { text-decoration: none; flex: 1; }
            .action-bar .act-btn {
                width: 100%; height: 48px;
                border: none;
                border-radius: var(--radius-md);
                font-size: 16px; font-weight: 700;
                cursor: pointer;
                color: var(--white);
                box-shadow: var(--shadow-sm);
                transition: opacity .2s, transform .1s;
                letter-spacing: .5px;
            }
            .action-bar .act-btn:active { opacity: .8; transform: scale(.97); }
            .action-bar .act-btn.new-order  { background: linear-gradient(135deg, var(--purple-btn), #4a148c); }
            .action-bar .act-btn.check-order { background: linear-gradient(135deg, #1565c0, #0d47a1); }
            .action-bar .act-btn.send-kitchen { background: linear-gradient(135deg, #e65100, #bf360c); }

            /* ── MACHINE NOT SET ───────────────────────────── */
            .no-machine {
                display: flex;
                align-items: center;
                justify-content: center;
                height: 100vh;
                font-size: 20px;
                color: var(--green-dark);
                background: var(--green-pale);
            }

            /* ── MODAL OVERRIDE ────────────────────────────── */
            .modal {
                border-radius: var(--radius-lg);
                box-shadow: var(--shadow-md);
                padding: 16px;
                max-width: 320px;
            }
            .modal button {
                background: linear-gradient(135deg, var(--green-main), var(--green-dark));
                color: var(--white);
                border: none;
                border-radius: var(--radius-sm);
                padding: 10px 24px;
                font-size: 18px;
                cursor: pointer;
                margin-top: 12px;
                box-shadow: var(--shadow-sm);
            }
        </style>

        <style>
            .auto-fit-text {
                font-size: 18px;
                white-space: nowrap;
                overflow: hidden;
            }
        </style>

        <script type="text/javascript">
            function autoFitText() {
                document.querySelectorAll('.auto-fit-text').forEach(function(btn) {
                    var size = 18;
                    btn.style.fontSize = size + 'px';
                    while (btn.scrollWidth > btn.clientWidth && size > 9) {
                        size--;
                        btn.style.fontSize = size + 'px';
                    }
                });
            }
            document.addEventListener('DOMContentLoaded', autoFitText);

            function plus() {
                var cass = document.getElementById("txtOrderQty");
                var num = parseInt(cass.value);
                if (num > 99) { cass.value = "1"; } else { cass.value = num + 1; }
            }

            function minus() {
                var cass = document.getElementById("txtOrderQty");
                var num = parseInt(cass.value);
                if (num <= 1) { cass.value = "1"; } else { cass.value = num - 1; }
            }

            function back() { window.location = "Next"; }

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
                    "closeButton": false, "debug": false, "progressBar": false,
                    "positionClass": "toast-top-right", "onclick": null,
                    "showDuration": "10", "hideDuration": "500",
                    "timeOut": "100", "extendedTimeOut": "500",
                    "showEasing": "swing", "hideEasing": "linear",
                    "showMethod": "fadeIn", "hideMethod": "fadeOut"
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

            function buildSearchTable(dataJson, prefix) {
                var html = "<table>";
                if (dataJson !== null) {
                    $.each(dataJson, function (key, value) {
                        html += "<tr>";
                        html += "<td style='font-weight:600;'>" + value['ShortName'] + "</td>";
                        html += "<td><input type='hidden' value='1' /></td>";
                        html += "<td><input type='button' value='สั่ง' onclick=\"saveData('" + prefix + "','" + value['PCode'] + "')\" /></td>";
                        html += "</tr>";
                    });
                }
                html += "</table>";
                return html;
            }

            function showSearchByName() {
                var textSearch = document.getElementById("txtSearch").value;
                if (textSearch !== "") {
                    document.getElementById("divShowDataItem").style.display = "none";
                    document.getElementById("divSearch").style.display = "";
                    $.get("Search?w=" + textSearch, function (responseJson) {
                        document.getElementById("divSearch").innerHTML = buildSearchTable(responseJson, 'A');
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
                    $.get("Search?wc=" + txtSearchCode, function (responseJson) {
                        document.getElementById("divSearch").innerHTML = buildSearchTable(responseJson, 'A');
                    });
                } else {
                    document.getElementById("divShowDataItem").style.display = "";
                    document.getElementById("divSearch").style.display = "none";
                }
            }
        </script>
    </head>
    <body>
        <c:choose>
            <c:when test="${empty macNo}">
                <div class="no-machine">กรุณากำหนดหมายเลขเครื่อง สำหรับสั่งอาหาร</div>
            </c:when>
            <c:otherwise>
                <input type="hidden" name="txtMacNo"   id="txtMacNo"   value="${macNo}" />
                <input type="hidden" name="txtTableNo" id="txtTableNo" value="${tableNo}" />
                <input type="hidden" name="txtPrefix"  id="txtPrefix"  value="${prefix}" />

                <!-- ── TOP BAR ── -->
                <div class="top-bar">
                    <div class="table-badge">
                        <span class="lbl">โต๊ะ</span>
                        <span class="val">${tableNo}</span>
                    </div>

                    <div class="qty-controls">
                        <button class="qty-btn plus"  onclick="plus()">+</button>
                        <input type="number" name="txtOrderQty" id="txtOrderQty" value="1" />
                        <button class="qty-btn minus" onclick="minus()">−</button>
                    </div>

                    <div class="total-box">
                        <div class="lbl">ยอดรวม</div>
                        <div id="totalBill">${totalBill}</div>
                    </div>
                </div>

                <!-- ── MENU TABS ── -->
                <div class="menu-tab-bar">
                    <c:forEach items="${headerMenu}" var="hLabel" varStatus="hs">
                        <a href="Login2?prefix=${headerPrefixes[hs.index]}" class="tab-link">
                            <input type="button" value="${hLabel}" class="btnGroup" />
                        </a>
                    </c:forEach>
                </div>

                <!-- ── SEARCH ROW ── -->
                <div class="search-row">
                    <div class="search-wrap">
                        <input name="txtSearchCode" type="text" id="txtSearchCode"
                               placeholder="ค้นหารหัส"
                               onkeyup="showSearchByCode();" />
                    </div>
                    <div class="search-wrap">
                        <input name="txtSearch" type="text" id="txtSearch"
                               placeholder="ค้นหาชื่อ"
                               onkeyup="showSearchByName();" />
                    </div>
                </div>

                <!-- ── ITEM GRID ── -->
                <div id="divShowDataItem">
                    <table>
                        <tr>
                        <c:forEach items="${menuItems}" var="item" varStatus="s">
                            <td align="center" bgcolor="${item.bgColor}">
                                <c:choose>
                                    <c:when test="${item.backButton}">
                                        <a href="Login2?prefix=${item.backPrefix}">
                                            <input type="button" value="เมนูหลัก" class="btn-back" />
                                        </a>
                                    </c:when>
                                    <c:when test="${item.menu.code_Type eq 'P' and not empty item.menu.shortName}">
                                        <a href="#${item.menu.PCode}" rel="modal:open">
                                            <img src="img/${item.menu.PCode}.png" alt="" width="72" height="72"
                                                 style="border-radius:8px; box-shadow:0 2px 6px rgba(0,0,0,.2);"
                                                 onerror="this.style.display='none'">
                                        </a>
                                        <div id="${item.menu.PCode}" class="modal">
                                            <div style="text-align:center; font-weight:700; margin-bottom:8px;">รายละเอียดสินค้า</div>
                                            <img id="imgShow" src="img/${item.menu.PCode}.png" width="100%" height="100%" alt="" style="border-radius:8px;" onerror="this.style.display='none'" />
                                            <div style="text-align:center;">
                                                <button onclick="saveData('${prefix}','${item.menu.PCode}')">สั่งสินค้า</button>
                                            </div>
                                        </div>
                                        <a href="javascript:saveData('${prefix}','${item.menu.PCode}')" style="text-decoration:none;">
                                            <button class="border2 auto-fit-text" style="width:100%; color:#000;">${item.menu.shortName}</button>
                                            <button class="border1 auto-fit-text" style="width:100%; color:#06F;">${item.menu.PCode}</button>
                                        </a>
                                    </c:when>
                                    <c:when test="${not empty item.menu.shortName}">
                                        <a href="Login2?prefix=${item.menu.code_ID}E" style="text-decoration:none;">
                                            <button class="border1 auto-fit-text" style="width:100%; color:#000;">${item.menu.shortName}</button>
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

                <!-- ── SEARCH RESULTS ── -->
                <div id="divSearch"></div>

                <!-- ── ACTION BAR ── -->
                <div class="action-bar">
                    <a href="Order.jsp?prefix=${prefix}">
                        <button class="act-btn new-order" id="btnListBill">ใหม่(${billCount})</button>
                    </a>
                    <a href="OrderOld.jsp?prefix=${prefix}">
                        <button class="act-btn check-order">ตรวจสอบ</button>
                    </a>
                    <button class="act-btn send-kitchen" onclick="back();" style="flex:1;">ส่งครัว</button>
                </div>

            </c:otherwise>
        </c:choose>
    </body>
</html>
