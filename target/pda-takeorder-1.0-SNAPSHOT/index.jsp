<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PDA-ICS</title>
    </head>
    <body>
        <h1>Welcome To Take Order</h1>
        Design by ICS<br />
        <a href="TakeOrder.apk">
            <input type="button" value="Download" style="height: 150px; width: 150px;" />
        </a>
        <br /><br /><br /><br />
        <a href='<%=request.getRequestURL() %>Welcome?macno=901'>
            
            <input type="button" value="TEST TAKEORDER SAMPLE<br />MAC:901" style="height: 50px; border-radius: 25px; border: 1px solid; background-color: #ff0000;" />
        </a>
        
        <a href='<%=request.getRequestURL() %>Welcome?macno=902'>
            
            <input type="button" value="TEST TAKEORDER SAMPLE<br />MAC:902" style="height: 50px; border-radius: 25px; border: 1px solid; background-color: #03C; color: #FFF;" />
        </a>
    </body>
</html>
