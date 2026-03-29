<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="th">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>PDA-ICS</title>
        <script type="text/javascript" src="js/jquery-latest.min.js"></script>
        <style>
            * {
                box-sizing: border-box;
                margin: 0;
                padding: 0;
            }

            body {
                min-height: 100vh;
                background: linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%);
                display: flex;
                align-items: center;
                justify-content: center;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                padding: 16px;
            }

            .card {
                background: rgba(255, 255, 255, 0.05);
                backdrop-filter: blur(20px);
                border: 1px solid rgba(255, 255, 255, 0.1);
                border-radius: 20px;
                width: 100%;
                max-width: 420px;
                overflow: hidden;
                box-shadow: 0 25px 50px rgba(0, 0, 0, 0.5);
            }

            .card-header {
                background: linear-gradient(135deg, #e91e8c, #ff6b35);
                padding: 24px 20px;
                text-align: center;
            }

            .card-header .app-name {
                font-size: 22px;
                font-weight: 700;
                color: #fff;
                letter-spacing: 1px;
            }

            .card-header .db-name {
                font-size: 13px;
                color: rgba(255, 255, 255, 0.8);
                margin-top: 4px;
            }

            .card-body {
                padding: 28px 24px;
                display: flex;
                flex-direction: column;
                gap: 18px;
            }

            .field-group {
                display: flex;
                flex-direction: column;
                gap: 6px;
            }

            .field-label {
                font-size: 13px;
                font-weight: 600;
                color: rgba(255, 255, 255, 0.6);
                text-transform: uppercase;
                letter-spacing: 0.5px;
            }

            .field-value {
                font-size: 20px;
                font-weight: 600;
                color: #fff;
                padding: 10px 14px;
                background: rgba(255, 255, 255, 0.08);
                border-radius: 10px;
                border: 1px solid rgba(255, 255, 255, 0.1);
            }

            .input-field {
                font-size: 22px;
                font-weight: 600;
                color: #fff;
                background: rgba(255, 255, 255, 0.08);
                border: 1px solid rgba(255, 255, 255, 0.2);
                border-radius: 10px;
                padding: 10px 14px;
                width: 100%;
                outline: none;
                transition: border-color 0.2s, background 0.2s;
            }

            .input-field:focus {
                border-color: #e91e8c;
                background: rgba(233, 30, 140, 0.1);
            }

            .input-field::placeholder {
                color: rgba(255, 255, 255, 0.3);
            }

            /* Remove number input arrows */
            .input-field[type=number]::-webkit-inner-spin-button,
            .input-field[type=number]::-webkit-outer-spin-button {
                -webkit-appearance: none;
            }

            .remember-row {
                display: flex;
                align-items: center;
                gap: 8px;
                margin-top: 4px;
            }

            .remember-row input[type="checkbox"] {
                width: 18px;
                height: 18px;
                accent-color: #e91e8c;
                cursor: pointer;
            }

            .remember-row label {
                font-size: 14px;
                color: rgba(255, 255, 255, 0.6);
                cursor: pointer;
            }

            /* Order type buttons */
            .type-grid {
                display: grid;
                grid-template-columns: 1fr 1fr;
                gap: 10px;
            }

            .type-option {
                position: relative;
            }

            .type-option input[type="radio"] {
                position: absolute;
                opacity: 0;
                width: 0;
                height: 0;
            }

            .type-btn {
                display: block;
                width: 100%;
                padding: 12px 8px;
                font-size: 16px;
                font-weight: 600;
                color: rgba(255, 255, 255, 0.6);
                background: rgba(255, 255, 255, 0.06);
                border: 1.5px solid rgba(255, 255, 255, 0.12);
                border-radius: 12px;
                text-align: center;
                cursor: pointer;
                transition: all 0.2s;
                user-select: none;
            }

            .type-btn .type-icon {
                display: block;
                font-size: 22px;
                margin-bottom: 4px;
            }

            .type-option input[type="radio"]:checked + .type-btn {
                color: #fff;
                background: linear-gradient(135deg, #e91e8c, #ff6b35);
                border-color: transparent;
                box-shadow: 0 4px 15px rgba(233, 30, 140, 0.4);
            }

            .submit-btn {
                width: 100%;
                padding: 16px;
                font-size: 20px;
                font-weight: 700;
                color: #fff;
                background: linear-gradient(135deg, #00b09b, #96c93d);
                border: none;
                border-radius: 12px;
                cursor: pointer;
                transition: opacity 0.2s, transform 0.1s;
                letter-spacing: 0.5px;
            }

            .submit-btn:active {
                transform: scale(0.98);
                opacity: 0.9;
            }

            .error-card {
                background: rgba(255, 255, 255, 0.05);
                backdrop-filter: blur(20px);
                border: 1px solid rgba(255, 100, 100, 0.3);
                border-radius: 20px;
                padding: 40px 32px;
                text-align: center;
                color: #fff;
                max-width: 360px;
            }

            .error-card .error-icon {
                font-size: 48px;
                margin-bottom: 16px;
            }

            .error-card h2 {
                font-size: 18px;
                margin-bottom: 8px;
            }

            .error-card p {
                font-size: 14px;
                color: rgba(255, 255, 255, 0.6);
            }

            .divider {
                height: 1px;
                background: rgba(255, 255, 255, 0.08);
            }
        </style>
    </head>

    <body>
        <c:choose>
            <c:when test="${empty sessionScope.macno}">
                <div class="error-card">
                    <div class="error-icon">⚙️</div>
                    <h2>ยังไม่ได้ตั้งค่าเครื่อง</h2>
                    <p>กรุณาตั้งค่า Machine No ก่อนใช้งาน</p>
                </div>
            </c:when>
            <c:otherwise>
                <div class="card">
                    <div class="card-header">
                        <div class="app-name">ICS — PDA Take Order</div>
                    </div>

                    <form action="Login" method="post" onsubmit="return valid()">
                        <div class="card-body">

                            <!-- Machine No -->
                            <div class="field-group">
                                <span class="field-label">เลขเครื่อง</span>
                                <div class="field-value">${sessionScope.macno}</div>
                                <input type="hidden" name="txtMacNo" value="${sessionScope.macno}" />
                            </div>

                            <!-- Employee -->
                            <c:if test="${showEmployField}">
                                <div class="divider"></div>
                                <div class="field-group">
                                    <label class="field-label" for="txtEmpCode">พนักงาน</label>
                                    <input type="number" class="input-field" name="txtEmpCode" id="txtEmpCode"
                                           autocomplete="off" value="${cEmpCode}" placeholder="รหัสพนักงาน" />
                                    <div class="remember-row">
                                        <input type="checkbox" name="chkRemember" id="chkRemember" checked="checked" />
                                        <label for="chkRemember">จำรหัสพนักงาน</label>
                                    </div>
                                </div>
                            </c:if>

                            <div class="divider"></div>

                            <!-- Table -->
                            <div class="field-group">
                                <label class="field-label" for="txtTableCode">โต๊ะ</label>
                                <input type="text" class="input-field" name="txtTableCode" id="txtTableCode"
                                       autocomplete="off" placeholder="เลขโต๊ะ"
                                       onblur="loadCustomer();"
                                       onkeypress="loadCust(event.which || event.keyCode);" />
                                <input type="text" name="txtTableCodeActiveStatus" id="txtTableCodeActiveStatus"
                                       hidden autocomplete="off" />
                            </div>

                            <!-- Guest Count -->
                            <div class="field-group">
                                <label class="field-label" for="txtCustCount">จำนวนลูกค้า (Guest)</label>
                                <input type="number" class="input-field" id="txtCustCount"
                                       name="txtCustCount" autocomplete="off" value="1"
                                       style="text-align: center;" />
                            </div>

                            <div class="divider"></div>

                            <!-- Order Type -->
                            <div class="field-group">
                                <span class="field-label">ประเภทการสั่ง</span>
                                <div class="type-grid">
                                    <label class="type-option">
                                        <input type="radio" name="chkType" id="radio1" value="E" checked="checked" />
                                        <span class="type-btn">
                                            <span class="type-icon">🍽️</span>นั่งทาน
                                        </span>
                                    </label>
                                    <label class="type-option">
                                        <input type="radio" name="chkType" id="radio2" value="T" />
                                        <span class="type-btn">
                                            <span class="type-icon">🥡</span>ห่อกลับ
                                        </span>
                                    </label>
                                    <label class="type-option">
                                        <input type="radio" name="chkType" id="radio3" value="D" />
                                        <span class="type-btn">
                                            <span class="type-icon">🛵</span>เดลิเวอรี่
                                        </span>
                                    </label>
                                    <label class="type-option">
                                        <input type="radio" name="chkType" id="radio4" value="S" />
                                        <span class="type-btn">
                                            <span class="type-icon">📋</span>สั่งล่วงหน้า
                                        </span>
                                    </label>
                                </div>
                            </div>

                            <!-- Submit -->
                            <button name="Submit" type="submit" class="submit-btn">เมนูอาหาร →</button>

                        </div>
                    </form>
                </div>
            </c:otherwise>
        </c:choose>

        <script type="text/javascript">
            function ready() {
                var idEmpCode = document.getElementById("txtEmpCode");
                var idTableCode = document.getElementById("txtTableCode");
                if (idEmpCode && idEmpCode.value === "") {
                    idEmpCode.focus();
                } else if (idTableCode) {
                    idTableCode.focus();
                }
            }

            function valid() {
                var tcode = document.getElementById("txtTableCode");
                var tcust = document.getElementById("txtCustCount");
                var tActiveStatus = document.getElementById("txtTableCodeActiveStatus").value;
                var custCount = parseInt(tcust.value);
                if (tcode.value === "") {
                    alert("กรุณาระบุเลขโต๊ะ");
                    tcode.focus();
                    return false;
                } else if (tcust.value === "") {
                    alert("กรุณาระบุจำนวนลูกค้า");
                    tcust.focus();
                    return false;
                } else if (custCount <= 0) {
                    alert("จำนวนลูกค้าต้องมากกว่า 0");
                    tcust.focus();
                    return false;
                } else if (tActiveStatus === "N") {
                    alert("โต๊ะนี้ปิดปรับปรุงชั่วคราว");
                    return false;
                }
                return true;
            }

            function loadCustomer() {
                var tableNo = $("#txtTableCode").val();
                if (!tableNo) return;
                $.get("GetCustCount", {tableNo: tableNo}, function (data) {
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

            window.onload = ready;
        </script>
    </body>
</html>
