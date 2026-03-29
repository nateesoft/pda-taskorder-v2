<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>แจ้งเตือนห้องครัว</title>
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
            .icon-wrapper {
                width: 90px;
                height: 90px;
                border-radius: 50%;
                background: linear-gradient(135deg, #e67e22, #f39c12);
                display: flex;
                align-items: center;
                justify-content: center;
                margin: 0 auto 20px;
                box-shadow: 0 8px 25px rgba(243, 156, 18, 0.5);
                font-size: 44px;
            }
            .card-title {
                color: #ffffff;
                font-size: 20px;
                font-weight: bold;
                margin-bottom: 8px;
                letter-spacing: 1px;
            }
            .card-subtitle {
                color: rgba(255, 255, 255, 0.6);
                font-size: 13px;
                margin-bottom: 30px;
            }
            .status-badge {
                display: inline-block;
                background: linear-gradient(135deg, #e67e22, #f39c12);
                color: #ffffff;
                font-size: 13px;
                font-weight: bold;
                padding: 6px 18px;
                border-radius: 20px;
                margin-bottom: 30px;
                letter-spacing: 0.5px;
            }
            .btn-notify {
                display: block;
                width: 100%;
                padding: 18px;
                background: linear-gradient(135deg, #c0392b, #e74c3c);
                color: #ffffff;
                font-size: 20px;
                font-weight: bold;
                border-radius: 12px;
                text-decoration: none;
                letter-spacing: 0.5px;
                box-shadow: 0 6px 20px rgba(231, 76, 60, 0.5);
                border-bottom: 4px solid #922b21;
                margin-bottom: 15px;
                transition: all 0.2s ease;
            }
            .btn-notify:hover {
                background: linear-gradient(135deg, #a93226, #cb4335);
                transform: translateY(-1px);
                box-shadow: 0 8px 25px rgba(231, 76, 60, 0.7);
            }
            .btn-notify:active {
                transform: translateY(1px);
                border-bottom-width: 2px;
            }
            .btn-back {
                display: block;
                width: 100%;
                padding: 15px;
                background: rgba(255, 255, 255, 0.1);
                color: rgba(255, 255, 255, 0.85);
                font-size: 18px;
                font-weight: bold;
                border-radius: 12px;
                text-decoration: none;
                border: 1px solid rgba(255, 255, 255, 0.2);
                transition: all 0.2s ease;
            }
            .btn-back:hover {
                background: rgba(255, 255, 255, 0.2);
                color: #ffffff;
                transform: translateY(-1px);
            }
            .btn-back:active {
                transform: translateY(1px);
            }
        </style>
    </head>
    <body>
        <div class="card">
            <div class="icon-wrapper">&#128374;</div>
            <div class="card-title">แจ้งเตือนห้องครัว</div>
            <div class="card-subtitle">Kitchen Alert</div>

            <div class="status-badge">&#9888; ลูกค้ารอนาน</div>

            <a href="HistoryOrder?prefix=${prefix}" class="btn-notify">ระบบกำลังแจ้งเตือนห้องครัว!</a>
            <a href="Login2?prefix=${prefix}" class="btn-back">&#8592; กลับเมนูหลัก</a>
        </div>
    </body>
</html>
