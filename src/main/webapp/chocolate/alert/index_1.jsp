<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>alertify.js - example page</title>
        <link rel="stylesheet" href="alertify.core.css" />
        <link rel="stylesheet" href="alertify.default.css" id="toggleCSS" />
    </head>
    <body>
        <script src="jquery-1.9.1.js"></script>
        <script src="alertify.min.js"></script>
        <script>
            function reset() {
                $("#toggleCSS").attr("href", "alertify.default.css");
                alertify.set({
                    labels: {
                        ok: "OK",
                        cancel: "Cancel"
                    },
                    delay: 5000,
                    buttonReverse: false,
                    buttonFocus: "ok"
                });
            }

            $(document).ready(function() {
                reset();
                alertify.alert("This is an alert dialog");
            });
        </script>
    </body>
</html>