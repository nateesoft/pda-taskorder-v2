<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>พิมพ์ตรวจสอบบิล</title>
        <link rel="stylesheet" type="text/css" href="css/pda.css">
        <style>
            * {
                box-sizing: border-box;
                margin: 0;
                padding: 0;
            }
            body {
                background: linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%);
                min-height: 100vh;
                display: flex;
                align-items: center;
                justify-content: center;
                font-family: Arial, sans-serif;
            }
            .card {
                background: rgba(255, 255, 255, 0.05);
                backdrop-filter: blur(10px);
                border: 1px solid rgba(255, 255, 255, 0.1);
                border-radius: 20px;
                padding: 40px 30px;
                width: 340px;
                text-align: center;
                box-shadow: 0 20px 60px rgba(0, 0, 0, 0.5);
            }
            .card-title {
                color: #ffffff;
                font-size: 22px;
                font-weight: bold;
                margin-bottom: 8px;
                letter-spacing: 1px;
            }
            .card-subtitle {
                color: rgba(255, 255, 255, 0.6);
                font-size: 14px;
                margin-bottom: 30px;
            }
            .qr-wrapper {
                background: #ffffff;
                border-radius: 15px;
                padding: 15px;
                display: inline-block;
                margin-bottom: 30px;
                box-shadow: 0 8px 25px rgba(0, 0, 0, 0.3);
            }
            .qr-wrapper img {
                display: block;
                width: 220px;
                height: 220px;
                border-radius: 8px;
            }
            .status-badge {
                display: inline-block;
                background: linear-gradient(135deg, #27ae60, #2ecc71);
                color: #ffffff;
                font-size: 13px;
                font-weight: bold;
                padding: 6px 18px;
                border-radius: 20px;
                margin-bottom: 25px;
                letter-spacing: 0.5px;
            }
            .btn-print {
                display: block;
                width: 100%;
                padding: 18px;
                background: linear-gradient(135deg, #c0392b, #e74c3c);
                color: #ffffff;
                font-size: 22px;
                font-weight: bold;
                border: none;
                border-radius: 12px;
                cursor: pointer;
                text-decoration: none;
                letter-spacing: 1px;
                box-shadow: 0 6px 20px rgba(231, 76, 60, 0.5);
                transition: all 0.2s ease;
                border-bottom: 4px solid #922b21;
            }
            .btn-print:hover {
                background: linear-gradient(135deg, #a93226, #cb4335);
                box-shadow: 0 8px 25px rgba(231, 76, 60, 0.7);
                transform: translateY(-1px);
            }
            .btn-print:active {
                transform: translateY(1px);
                border-bottom-width: 2px;
            }
        </style>
    </head>
    <body>
        <div class="card">
            <div class="card-title">พิมพ์ตรวจสอบบิล</div>
            <div class="card-subtitle">Print Check Bill</div>

            <div class="qr-wrapper">
                <img src="img/QR-Code.jpg" alt="QR Code">
            </div>

            <div class="status-badge">&#10003; ส่งคำสั่งพิมพ์แล้ว</div>

            <a href="Login2?prefix=${prefix}" class="btn-print">Print OK!</a>
        </div>
    </body>
</html>
