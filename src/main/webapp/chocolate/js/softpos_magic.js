function showTime() {
    $('#divProductDetail').hide();
}

function inputText(key) {
    var text = document.getElementById("txtProductId");
    text.value = text.value + key.value;
}

function backspace() {
    var text = document.getElementById("txtProductId");
    text.value = text.value.substr(0, text.value.length - 1);
}

function chageTotal(sel) {
    var qty = sel.value;
    var price = document.getElementById("showPPrice").innerHTML;
    var total = qty * price;

    document.getElementById("showPTotal").innerHTML = total;
}

function showProduct(td) {
    showTime();
    document.getElementById("txtSearch").value = "";
    // document.getElementById("txtSearch").focus();
    $.get('ProductPop?GroupCode=' + td.id, function(responseJson) {
        if (responseJson !== null) {
            $("#tbProduct").find("tr").remove();
            var table1 = $("#tbProduct");
            $.each(responseJson, function(key, value) {
                var rowNew = $(
                        "<tr>" +
                        "<td bgcolor=#EEEEEE align=center width=10px></td>" +
                        "<td height=50px id=" + value['PCode'] + " bgcolor=#EEEEEE onClick=saveItemBill(this);></td>" +
                        "<td width=100px bgcolor=#EEEEEE align=right></td>" +
                        "</tr>");
                rowNew.children().eq(0).text("");
                rowNew.children().eq(1).text(value['PDesc']);
                rowNew.children().eq(2).text("$12.00");
                rowNew.appendTo(table1);
            });
        }
    });
}

function saveItemBill(PCode) {
    $.post('BalanceSavePop?PCode=' + PCode.id, function(responseJson) {
        getBill();
    });
}

function saveItemBill2(PCode) {
    $.post('BalanceSavePop?PCode=' + PCode, function(responseJson) {
        getBill();
    });
}

function updateQty(PCode) {
    $.post('BalanceUpdatePop?PCode=' + PCode, function(responseJson) {
        getBill();
    });
}

function searchData(txt) {
    $.get('SearchProductPop?word=' + txt.value, function(responseJson) {
        if (responseJson !== null) {
            $("#tbProduct").find("tr").remove();
            var table1 = $("#tbProduct");
            $.each(responseJson, function(key, value) {
                var rowNew = $(
                        "<tr>" +
                        "<td bgcolor=#EEEEEE align=center width=10px></td>" +
                        "<td height=50px id=" + value['PCode'] + " bgcolor=#EEEEEE onClick=saveItemBill(this);></td>" +
                        "<td width=100px bgcolor=#EEEEEE align=right></td>" +
                        "</tr>");
                rowNew.children().eq(0).text("");
                rowNew.children().eq(1).text(value['PDesc']);
                rowNew.children().eq(2).text("$12.00");
                rowNew.appendTo(table1);
            });
        }
    });
}

function showProductDetail(key) {
    $('#divProductDetail').show();
    var PCode = $("#tbBill").find("tr").eq(key).find("td").eq(0).text();
    var PDesc = $("#tbBill").find("tr").eq(key).find("td").eq(1).text();
    var PPrice = $("#tbBill").find("tr").eq(key).find("td").eq(2).text();
    var PQty = $("#tbBill").find("tr").eq(key).find("td").eq(3).text();
    var PTotal = $("#tbBill").find("tr").eq(key).find("td").eq(4).text();

    //alert(data);
    document.getElementById("showPCode").innerHTML = (key + 1) + "#[" + PCode + "] " + PDesc;
    document.getElementById("showPPrice").innerHTML = PPrice;
    document.getElementById("showPTotal").innerHTML = PTotal;
    document.getElementById("showPQty").value = PQty;
}

function getBill() {
    $.get('BalancePop', function(responseJson) {
        if (responseJson !== null) {
            $("#tbBill").find("tr").remove();
            var table1 = $("#tbBill");
            var totalBill = 0;
            $.each(responseJson, function(key, value) {
                var rowNew = $(
                        "<tr onClick=showProductDetail(" + key + ");>" +
                        "<td width=40px align=center></td>" +
                        "<td width=250px align=left></td>" +
                        "<td width=80px align=right></td>" +
                        "<td width=70px align=right></td>" +
                        "<td width=150px align=right></td>" +
                        "</tr>"
                        );
                rowNew.children().eq(0).text(value['R_PluCode']);
                rowNew.children().eq(1).text(value['R_PName']);
                rowNew.children().eq(2).text(value['R_Price']);
                rowNew.children().eq(3).text(value['R_Quan']);
                rowNew.children().eq(4).text(value['R_Total']);
                rowNew.appendTo(table1);

                totalBill += value['R_Total'];
            });
            document.getElementById("totalBill").innerHTML = totalBill;
        }
    });
}

$(document).ready(function() {
    getBill();

    $.get('BalanceSavePop', function(responseJson) {
        if (responseJson !== null) {
            getBill();
        }
    });

    $("#btnLogout").click(function(event) {
        window.location = "LoginPage.jsp";
    });

    $.get('GroupFilePop', function(responseJson) {
        if (responseJson !== null) {
            //$("#tbGroupFile").find("tr").remove();
            //var table1 = $("#tbGroupFile");
            //$.each(responseJson, function(key, value) {
            //    var rowNew = $(
            //            "<tr>" +
            //            "<td bgcolor=#CCCCCC align=center width=10px></td>" +
            //            "<td id=" + value['GroupCode'] + " height=50px bgcolor=#DDDDDD onClick=showProduct(this);></td>" +
            //            "</tr>");
            //    rowNew.children().eq(0).text("");
            //    rowNew.children().eq(1).text(value['GroupName']);
            //    rowNew.appendTo(table1);
            //});
            $("#tbGroupFile").find("tr").remove();
            var table1 = $("#tbGroupFile");
            $.each(responseJson, function(key, value) {
                var rowNew = $(
                        "<tr>" +
                        "<td bgcolor=#CCCCCC align=center width=10px></td>" +
                        "<td id=" + value['GroupCode'] + " height=50px bgcolor=#DDDDDD onClick=showProduct(this);></td>" +
                        "</tr>");
                rowNew.children().eq(0).text("");
                rowNew.children().eq(1).text(value['GroupName']);
                rowNew.appendTo(table1);
            });
        }
    });

    $('#txtProductId').keyup(function(event) {
        var code = event.keyCode;
        if (code === 13) {
            var PCode = document.getElementById("txtProductId").value;
            saveItemBill2(PCode);

            document.getElementById("txtProductId").value = "";
            //document.getElementById("txtProductId").focus();
        }
    });
    $('#btnEnter').click(function(event) {
        var code = event.keyCode;
        var PCode = document.getElementById("txtProductId").value;
        saveItemBill2(PCode);

        document.getElementById("txtProductId").value = "";
    });
    $('#divProductDetail').hide();
});

function print() {
    document.jzebra.append("A37,503,0,1,2,3,N,PRINTED USING JZEBRA\n");
    document.jzebra.append("A37,503,0,1,2,3,N,PRINTED USING JZEBRA\n");
    document.jzebra.append("A37,503,0,1,2,3,N,PRINTED USING JZEBRA\n");
    document.jzebra.append("A37,503,0,1,2,3,N,PRINTED USING JZEBRA\n");
    document.jzebra.append("A37,503,0,1,2,3,N,PRINTED USING JZEBRA\n");
    document.jzebra.append("ทดสอบการพิมพ์ภาษาไทยจ้า");
    document.jzebra.append("\n\n\n\n\n");
    document.jzebra.print();
}

function cutPaper() {
    document.jzebra.append(chr(27) + chr(105));       // cut paper
}

function printTest2() {
    document.jzebra.append("REG-ID.\n");
    document.jzebra.append("19/09/2013 17:17  Cashier:1001  Mac:001\n");
    document.jzebra.append("-------------------------------------\n");
    document.jzebra.append("N อเมริกาโน่เย็น	1	45.00E\n");
    document.jzebra.append("N เอสเพรสโซ่เย็น	1	50.00E\n");
    document.jzebra.append("-------------------------------------\n");
    document.jzebra.append("Sub-TOTAL...(Item   2 )	90.00)\n");
    document.jzebra.append("-------------------------------------\n");
    document.jzebra.append("Net-TOTAL...			95.00\n");
    document.jzebra.append("เงินสด : 100.00 	ทอน :	95.00\n");
    document.jzebra.append("-------------------------------------\n");
    document.jzebra.append("Table  :9100   Receipt  NO.:0000165\n");
    document.jzebra.append("จำนวนลูกค้า :	  1  คน\n\n");
    document.jzebra.append("Thank You Please Come Again.\n");

    document.jzebra.print();
    cutPaper();
}

function printTest() {
    document.jzebra.append("\\x00\x01\x02\xFF");
    document.jzebra.append("\\x00\x01\x02\xFF");
    document.jzebra.append("\\x00\x01\x02\xFF");
    document.jzebra.append("\\x00\x01\x02\xFF");
    document.jzebra.append("\\x00\x01\x02\xFF");
    document.jzebra.append("\\x00\x01\x02\xFF");
    document.jzebra.append("\\x00\x01\x02\xFF");

    document.jzebra.append("ทดสอบการพิมพ์ภาษาไทยจ้า");
    document.jzebra.append("\n\n\n\n\n");
    document.jzebra.print();
}


