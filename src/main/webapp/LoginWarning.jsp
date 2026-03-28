<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="th">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login Warning</title>
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

            .warning-card {
                background: rgba(255, 255, 255, 0.05);
                backdrop-filter: blur(20px);
                border: 1px solid rgba(255, 180, 0, 0.3);
                border-radius: 20px;
                width: 100%;
                max-width: 420px;
                overflow: hidden;
                box-shadow: 0 25px 50px rgba(0, 0, 0, 0.5);
                text-align: center;
            }

            .card-header {
                background: linear-gradient(135deg, #f7971e, #ffd200);
                padding: 28px 24px 20px;
            }

            .warning-icon {
                font-size: 48px;
                display: block;
                margin-bottom: 10px;
            }

            .card-header h1 {
                font-size: 20px;
                font-weight: 700;
                color: #1a1a2e;
                letter-spacing: 0.5px;
                word-break: break-word;
            }

            .card-body {
                padding: 28px 24px;
                display: flex;
                flex-direction: column;
                gap: 20px;
            }

            .reason-box {
                background: rgba(255, 180, 0, 0.08);
                border: 1px solid rgba(255, 180, 0, 0.2);
                border-radius: 12px;
                padding: 16px;
            }

            .reason-label {
                font-size: 12px;
                font-weight: 600;
                color: rgba(255, 210, 0, 0.7);
                text-transform: uppercase;
                letter-spacing: 1px;
                margin-bottom: 8px;
            }

            .reason-text {
                font-size: 16px;
                color: rgba(255, 255, 255, 0.85);
                line-height: 1.5;
                word-break: break-word;
            }

            .back-btn {
                display: block;
                width: 100%;
                padding: 16px;
                font-size: 18px;
                font-weight: 700;
                color: #fff;
                background: linear-gradient(135deg, #e91e8c, #ff6b35);
                border: none;
                border-radius: 12px;
                cursor: pointer;
                text-decoration: none;
                transition: opacity 0.2s, transform 0.1s;
                letter-spacing: 0.5px;
            }

            .back-btn:active {
                transform: scale(0.98);
                opacity: 0.9;
            }
        </style>
    </head>
    <body>
        <div class="warning-card">
            <div class="card-header">
                <span class="warning-icon">⚠️</span>
                <h1>${topic}</h1>
            </div>
            <div class="card-body">
                <div class="reason-box">
                    <div class="reason-label">เหตุผล</div>
                    <div class="reason-text">${reason}</div>
                </div>
                <a class="back-btn" href="Welcome?macno=${macno}">← กลับ</a>
            </div>
        </div>
    </body>
</html>
