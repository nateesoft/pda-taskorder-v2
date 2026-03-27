<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
             function back() {
                window.location = "Next";
            }
        </script>
                   
    </head>
    <body>
        <h1>Hello World!</h1>
        <table>
            <td width="70" align="center"><input type="button" name="button3" id="button3" value="ส่งครัว" onClick="back();" style="width: 80px; height: 45px; font-size: 17px; background-color: #909; color: #fff; border-radius: 10px 0px 10px 0px; border: 1px solid;"></td>
        </table>
    </body>
</html>
