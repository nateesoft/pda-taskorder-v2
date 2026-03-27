<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>PDA-ICS</title>
        <link href="toastr.css" rel="stylesheet" type="text/css" />
        <script src="jquery.min.js"></script>
        <script src="toastr.js"></script>
        <script type="text/javascript">
            $(function() {
                $('#showtoast').click(function() {
                    toastr.options = {
                        "closeButton": false,
                        "debug": false,
                        "progressBar": false,
                        "positionClass": "toast-top-right",
                        "onclick": null,
                        "showDuration": "100",
                        "hideDuration": "500",
                        "timeOut": "500",
                        "extendedTimeOut": "500",
                        "showEasing": "swing",
                        "hideEasing": "linear",
                        "showMethod": "fadeIn",
                        "hideMethod": "fadeOut"
                    };
                    toastr["success"]("Have fun storming the castle!");
                    //toastr["success"]("Test Toast !!!", "Header Test");
                });
            });
        </script>
    </head>
    <body>
        <input type="button" id="showtoast" value="show toast" />
    </body>
</html>
