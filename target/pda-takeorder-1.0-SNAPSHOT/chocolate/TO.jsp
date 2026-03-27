<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Tessellate by HTML5 UP</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.scrolly.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/init.js"></script>
        <noscript>
        <link rel="stylesheet" href="css/skel.css" />
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/style-wide.css" />
        </noscript>
        <!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
        <!--[if lte IE 9]><link rel="stylesheet" href="css/ie/v9.css" /><![endif]-->
    </head>
    <body>

        <!-- Header -->
        <section id="header" class="dark">
            <header>
                <h1>ป้อนรหัสบริกร</h1>
                <p><input type="text" placeholder="Password" /></p>
            </header>
            <footer>
                <a href="#first" class="button scrolly">OK</a>
            </footer>
        </section>

        <section id="first" class="main">
            <header>
                <div class="container">
                    <h2>เปิดโต๊ะ</h2>
                    <p><input type="text" placeholder="Table No." /></p>
                    <p><input type="text" placeholder="Customer" /></p>
                </div>
                <div>
                	<p><a href="#second" class="button scrolly">OK</a><p>
                </div>
            </header>
            </div>
        </section>
        
        <section id="second" class="main">
            <header>
                <div class="container">
                    <h2>เลือกเมนูอาหาร</h2>
                    <p><input type="text" placeholder="Table No." /></p>
                    <p><input type="text" placeholder="Customer" /></p>
                </div>
                <div>
                	<p><a href="#third" class="button scrolly">OK</a><p>
                </div>
            </header>
            </div>
        </section>
        
        <section id="third" class="main">
            <header>
                <div class="container">
                    <h2>ยืนยันการสั่งอาหาร</h2>
                    <p><input type="text" placeholder="Table No." /></p>
                    <p><input type="text" placeholder="Customer" /></p>
                </div>
                <div>
                	<p><a href="#fouth" class="button scrolly">OK</a><p>
                </div>
            </header>
            </div>
        </section>
        
        <section id="fouth" class="main">
            <header>
                <div class="container">
                    <h2>สรุปรายการอาหารที่สั่ง</h2>
                    <table border="1">
                        <tr>
                            <th bgcolor="#CCCCCC">aaa1</th>
                            <th bgcolor="#CCCCCC">aaa2</th>
                            <th bgcolor="#CCCCCC">aaa3</th>
                            <th bgcolor="#CCCCCC">aaa4</th>
                            <th bgcolor="#CCCCCC">aaa5</th>
                            <th bgcolor="#CCCCCC">aaa6</th>
                        </tr>
                        <tr>
                            <td>aaa1</td>
                            <td>aaa2</td>
                            <td>aaa3</td>
                            <td>aaa4</td>
                            <td>aaa5</td>
                            <td>aaa6</td>
                        </tr>
                    </table>
                </div>
                <div>
                	<p><a href="#first" class="button scrolly">OK</a><p>
                </div>
            </header>
            </div>
        </section>

        <!-- Footer -->
        <section id="footer">
            <div class="copyright">
                <ul class="menu">
                    <li>&copy; บริษัทซอฟโพสท์ จำกัด</li><li>2013-2014</li>
                </ul>
            </div>
        </section>

    </body>
</html>