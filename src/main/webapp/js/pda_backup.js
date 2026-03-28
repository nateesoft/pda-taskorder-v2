
function clickSubGroup(h) {
    var html = "";
    document.getElementById("scroll").style.display = "";
    document.getElementById("searchPage").style.display = "none";
    document.getElementById("optMenu").style.display = "none";
    document.getElementById("txtSearch").value = "";
    $.get("PDASubMenuServlet?h=" + h, function(responseJson) {
        if (responseJson !== null) {
            $.each(responseJson, function(key, value) {

                html += "<div class=\"border1\" onclick=\"clickProduct(this);\" id=\"" + value['Code_ID'] + "\"><table width=\"100%\" align=\"center\"><tr><td>" + value['ShortName'] + "</td></tr></table></div>";
            });
        }
        document.getElementById("mySub").innerHTML = html;
    });
}

function clickProduct(g) {
    var html = "";
    $.get("PDAProductList?menuItem=" + g.id, function(responseJson) {
        if (responseJson !== null) {
            var c1 = "border3";
            var c2 = "border2";
            var c3 = "border4";
            var c;
            $.each(responseJson, function(key, value) {
                if (value['PDesc'].length > 18) {
                    c = c1;
                } else {
                    c = c2;
                }
                if (value['PStatus'] === "P") {

                    html += "<div class=\"" + c + "\" onclick=\"saveProduct(this);\" id=\"" + value['PCode'] + "\">";
                    html += "<table width=\"100%\" align=\"center\"><tr><td align=\"right\"><span class=\"ovalMenuAt\">" + value['PDesc2'] + "</span></td></tr><tr><td>" + value['PDesc'] + "</td></tr></table></div>";
                } else {
                    c = c3;
                    html += "<div class=\"" + c + "\" onclick=\"alertInvalid();\" id=\"" + value['PCode'] + "\">";
                    html += "<table width=\"100%\" align=\"center\"><tr><td align=\"right\"><span class=\"ovalMenuAt\">" + value['PDesc2'] + "</span></td></tr><tr><td>" + value['PDesc'] + "</td></tr></table></div>";
                }
            });
        }
        document.getElementById("mySub").innerHTML = html;
    });
}

function alertInvalid() {
    alert("Cannot to Take Order\n Please take at the Cashier \n This is Open price");
}

function holdTableTest() {
    var act1 = document.getElementById("txtFlagSave").value;
    var table = document.getElementById("txtTableNo").value;
    if (act1 === "no") {
        return;
    }
    $.get("PDAHoldTable?table=" + table, function(responseJson) {
        if (responseJson !== null) {
            document.getElementById("tableLogin").style.display = "none";
            document.getElementById("tbTest").style.display = "none";
            document.getElementById("tbTest2").style.display = "none";
            document.getElementById("showFixHeader").style.display = "none";
            document.getElementById("optMenu").style.display = "none";
            document.getElementById("searchPage").style.display = "none";

            document.getElementById("openTable").style.display = "";
            document.getElementById("txtTableNo").value = "";
            document.getElementById("txtCustNo").value = "";
            document.getElementById("hdd2").value = "1";
            document.getElementById("tableNo").innerHTML = "";
            document.getElementById("total").innerHTML = "0.00";
            document.getElementById("chargeTotal").innerHTML = "0.00";


            document.getElementById("txtTableNo").focus();
        }
    });
    document.getElementById("txtFlagSave").value = "no";

    clickSubGroup("A");
}

function findProduct() {
    var k = document.getElementById("txtSearch").value;
    if (k === "") {
        document.getElementById("divSearchProduct").innerHTML = "";
        return;
    }
    document.getElementById("optMenu").style.display = "none";
    document.getElementById("tbOptMenu").style.display = "none";
    document.getElementById("divSearchProduct").innerHTML = "";
    var t = document.getElementById("typeSearch").value;
    var html = "";

    $.post("PDASearchProductServlet", {key: k, typeSearch: t}, function(responseJson) {
        if (responseJson !== null) {
            var c1 = "border3";
            var c2 = "border2";
            var c3 = "border4";
            var c;
            $.each(responseJson, function(key, value) {
                var addHtml;
                if (value['PDesc'].length > 18) {
                    c = c1;
                } else {
                    c = c2;
                }
                var ddd = value['PDesc2'];
                if (ddd === "") {
                    addHtml = "";
                } else {
                    addHtml = "<span class=\"ovalMenuAt\">" + ddd + "</span></td></tr>";
                }
                if (value['PStatus'] === "P") {
                    html += "<div class=\"" + c + "\" onclick=\"saveProduct(this);\" id=\"" + value['PCode'] + "\">";
                    html += "<table width=\"100%\" align=\"center\">" + addHtml + "<tr><td>" + value['PDesc'] + "</td></tr></table></div>";
                } else {
                    c = c3;
                    html += "<div class=\"" + c + "\" onclick=\"alertInvalid();\" id=\"" + value['PCode'] + "\">";
                    html += "<table width=\"100%\" align=\"center\"><tr><td align=\"right\"><span class=\"ovalMenuAt\">" + value['PDesc2'] + "</span></td></tr><tr><td>" + value['PDesc'] + "</td></tr></table></div>";
                }
            });
        }
        html += "</table>";
        document.getElementById("divSearchProduct").innerHTML = html;
    });
}

function saveProduct(PCode) {
    var flag = document.getElementById("txtFlagSave").value;
    var table = document.getElementById("txtTableNo").value;
    //alert(table);
    if (flag === "no") {
        return;
    }

    var r = confirm("Confirm ?");
    if (r === true) {
        document.getElementById("openTable").style.display = "none";

        $.get("PDASaveOrderServlet?PCode=" + PCode.id + "&table=" + table, function(responseJson) {

            if (responseJson !== null) {
                LoadTableBill(table);
                $.each(responseJson, function(key, value) {
                    
                });
            }
        });
        
    } else {
        x = "You pressed Cancel!";
    }
}

function loadTableBill() {
    var table = document.getElementById("txtTableNo").value;
    var custNo = document.getElementById("txtCustNo").value;
    var customer = custNo;
    var type = document.getElementById("txtType").value;

    if (tableNo === "") {
        alert("Please Type Table No!!!");
        document.getElementById("txtTableNo").focus();
        return;
    } else {
        $.get("PDACheckTableServlet?table=" + table, function(responseJson) {
            if (responseJson !== null) {
                $.each(responseJson, function(key, value) {
                    if (value[0] === "OK") {
                        document.getElementById("txtCustNo").value = value[1];
                        LoadTableBill(table);
                        document.getElementById("tableNo").innerHTML = table;
                        document.getElementById("txtFlagSave").value = "yes";
                        document.getElementById("openTable").style.display = "none";
                    } else {
                        alert(value[0]);
                        document.getElementById("txtTableNo").value = "";
                        document.getElementById("txtCustNo").value = "";

                        document.getElementById("hdd2").value = "1";
                        document.getElementById("x1").innerHTML = "*";
                        document.getElementById("x2").innerHTML = "";
                    }
                });
            }
        });
    }
}

function showTotal(table) {
    $.get("ShowNetTotalBillAServlet?table=" + table, function(responseJson) {
        var total = "0.00";
        if (responseJson !== null) {

            $.each(responseJson, function(key, value) {
                total = value["R_TotalServiceAmt"];
            });

            document.getElementById("total").innerHTML = total.toFixed(2);
        }

    });
}

function login() {
    var pass = document.getElementById("txtInput2").value;
    var macno = document.getElementById("macnoShow").value;

    if (pass === "") {
        return;
    }
    $.get("PDALoginServlet?p=" + pass + "&m=" + macno, function(responseJson) {

        if (responseJson !== null) {
            $.each(responseJson, function(key, value) {
                if (value === "Success") {
                    document.getElementById("openTable").style.display = "";
                    document.getElementById("tableLogin").style.display = "none";

                    document.getElementById("txtTableNo").focus();
                } else {
                    alert(value);
                    document.getElementById("s2").innerHTML = "*";

                    document.getElementById("txtInput2").focus();
                }
            });
        }
    });

    document.getElementById("txtInput2").value = "";
    document.getElementById("hdd").value = "1";
    document.getElementById("s2").innerHTML = "*";

}

function showVoidMsg() {
    alert("Item are Calceled");
}

function showOption(tr) {
    document.getElementById("searchPage").style.display = "none";
    document.getElementById("scroll").style.display = "none";
    document.getElementById("tbOptMenu").style.display = "";
    document.getElementById("optMenu").style.display = "";
    $.get("PDATakeOptionServlet?index=" + tr.id, function(responseJson) {
        if (responseJson !== null) {
            var kicprint;
            $.each(responseJson, function(key, value) {
                document.getElementById("optPCode").innerHTML = value['PIndex'];
                document.getElementById("PCode").value = value['PCode'];
                document.getElementById("optPDesc").innerHTML = value['PDesc'];
                alert("เลือกรายการ=>" + value['PDesc']);
                document.getElementById("optPUnit").innerHTML = value['PQty'];
                document.getElementById("optPPrice").innerHTML = value['PPrice'].toFixed(2);
                kicprint = value['KicPrint'];
                var optLength = value['Opt'].length;
                var i;
                var html = "<table>";
                for (i = 0; i < optLength; i++) {
                    html += "<tr><td>";
                    html += "<input type=\"checkbox\" class=\"optBig\" value=\"" + value['Opt'][i] + "\" name=\"optChk\" />" + value['Opt'][i];
                    html += "</td></tr>";
                }
                html += "<tr><td>";
                html += "<input type=\"text\" name=\"txtOptAdd\" id=\"txtOptAdd\" />";
                html += "</td></tr>";
                html += "</table>";

                document.getElementById("showOptList").innerHTML = html;
            });
        }
    });
}

function saveOptionConfirm() {
    var RIndex = document.getElementById("optPCode").innerHTML;
    var opt = document.getElementsByName('optChk');
    var txtOptAdd = document.getElementById("txtOptAdd").value;
    var table = document.getElementById("txtTableNo").value;
    
    var optStr = "";

    var len = opt.length;
    for (var i = 0; i < len; i++) {
        if (opt[i].checked) {
            optStr += opt[i].value + ",";
        }
    }

    if (txtOptAdd !== null || txtOptAdd !== "") {
        optStr += txtOptAdd;
    }

    var t = document.getElementById("typeETD").value;


    $.post("PDASaveOptionConfirmServlet", {R_index: RIndex, type: t, opt: optStr}, function(responseJson) {
        if (responseJson !== null) {

            $.each(responseJson, function(key, value) {
                
            });
        }
    });
    
    LoadTableBill(table);
}

function deleteItemBill() {
    var RIndex = document.getElementById("optPCode").innerHTML;
    var table = document.getElementById("txtTableNo").value;

    $.post("PDADeleteItemServlet", {R_index: RIndex}, function(responseJson) {

        if (responseJson !== null) {
            $.each(responseJson, function(key, value) {

                LoadTableBill(table);
            });
        }
    });
}

function logout() {
    document.getElementById("openTable").style.display = "none";
    document.getElementById("optMenu").style.display = "none";
    document.getElementById("tableLogin").style.display = "";

    $.get("PDALogoutUserServlet", function(responseJson) {
        if (responseJson !== null) {
            document.getElementById("hdd").value = "1";
            document.getElementById("txtInput1").value = "";
            document.getElementById("txtInput2").value = "";
            document.getElementById("tbTest").style.display = "none";
            document.getElementById("tbTest2").style.display = "none";
            document.getElementById("s1").innerHTML = "*";
            document.getElementById("s2").innerHTML = "";
            document.getElementById("x1").innerHTML = "*";
            document.getElementById("x2").innerHTML = "";
            document.getElementById("txtTableNo").value = "";
            document.getElementById("txtCustNo").value = "";
            document.getElementById("hdd2").value = "1";
            document.getElementById("tableNo").innerHTML = "";
            document.getElementById("total").innerHTML = "0.00";
            document.getElementById("searchPage").style.display = "none";

            document.getElementById("txtFlagSave").value = "no";
            document.getElementById("showFixHeader").style.display = "none";

            document.getElementById("txtInput1").focus();


        }
    });

    clickSubGroup("A");
}

function defaultPage() {
    document.getElementById("openTable").style.display = "none";
    document.getElementById("searchPage").style.display = "none";
    document.getElementById("s1").innerHTML = "*";
    document.getElementById("x1").innerHTML = "*";
    document.getElementById("optMenu").style.display = "none";

    document.getElementById("txtInput1").focus();
}

function searchProduct() {
    document.getElementById("searchPage").style.display = "";
    document.getElementById("scroll").style.display = "none";
    document.getElementById("optMenu").style.display = "none";
}

function click1() {
    document.getElementById("hdd").value = "1";
    document.getElementById("s1").innerHTML = "*";
    document.getElementById("s2").innerHTML = "";
}

function click2() {
    document.getElementById("hdd").value = "2";
    document.getElementById("s1").innerHTML = "";
    document.getElementById("s2").innerHTML = "*";
}

function addProductNew() {
    var PCode = document.getElementById("PCode").value;
    var selQty = document.getElementById("selAddProduct").value;
    var table = document.getElementById("txtTableNo").value;
    if (selQty === 0) {
        return;
    }

    var r = confirm("Comfirm ?");
    if (r === true) {
        document.getElementById("openTable").style.display = "none";

        $.get("PDASaveOrderServlet?PCode=" + selQty + "*" + PCode+"&table="+table, function(responseJson) {

            if (responseJson !== null) {
                //alert("Add Item Already.(" + selQty + ")");
                $.each(responseJson, function(key, value) {
                    LoadTableBill(table);
                });
            }
        });
    }
    
    alert("Allready add");

}

function checkTable() {
    var tb = document.getElementById("txtTableNo").value;
    var result = false;
    if (tb === "") {
        alert("Please type Table No !!!");
        return result;
    } else {
        $.get("PDACheckTableServlet?table=" + tb, function(responseJson) {
            if (responseJson !== null) {
                $.each(responseJson, function(key, value) {
                    alert(value[0] + "," + value[1]);
                    if (value[0] === "OK") {
                        document.getElementById("txtCustNo").value = value[1];
                        result = true;
                    } else {
                        document.getElementById("txtTableNo").value = "";
                        document.getElementById("txtCustNo").value = "";

                        document.getElementById("hdd2").value = "1";
                        document.getElementById("x1").innerHTML = "*";
                        document.getElementById("x2").innerHTML = "";
                        result = false;
                    }
                });
            }
        });
    }

    return result;
}

function deleteProductFromBill(R_Index) {
    var table = document.getElementById("txtTableNo").value;

    $.get("PDADeletPCodeFromBillServlet?R_Index=" + R_Index.id, function(responseJson) {

        if (responseJson !== null) {
            $.each(responseJson, function(key, value) {
                LoadTableBill(table);
            });
        }
    });
    
    alert("Removed");
}

function urlEncode(inputString, encodeAllCharacter) {
    var outputString = '';
    if (inputString !== null) {
        for (var i = 0; i < inputString.length; i++) {
            var charCode = inputString.charCodeAt(i);
            var tempText = "";
            if (charCode < 128) {
                if (encodeAllCharacter)
                {
                    var hexVal = charCode.toString(16);
                    outputString += '%' + (hexVal.length < 2 ? '0' : '') + hexVal.toUpperCase();
                } else {
                    outputString += String.fromCharCode(charCode);
                }

            } else if ((charCode > 127) && (charCode < 2048)) {
                tempText += String.fromCharCode((charCode >> 6) | 192);
                tempText += String.fromCharCode((charCode & 63) | 128);
                outputString += escape(tempText);
            } else {
                tempText += String.fromCharCode((charCode >> 12) | 224);
                tempText += String.fromCharCode(((charCode >> 6) & 63) | 128);
                tempText += String.fromCharCode((charCode & 63) | 128);
                outputString += escape(tempText);
            }
        }
    }
    return outputString;
}

function LoadTableBill(table) {
    var etd = document.getElementById("txtType").value;
    var html1 = "<div style=\"width: 100%;\"><table id=\"tbTest\" class=\"billDetail\" border=\"0\" cellpadding=\"1\" cellspacing=\"1\">";
    html1 += "<tr><th align=\"center\">#</th>";
    html1 += "<th align=\"center\"></th>";
    html1 += "<th align=\"center\">Name</th>";
    html1 += "<th align=\"center\">จำนวน</th>";
    html1 += "<th align=\"center\">ราคา</th>";
    html1 += "<th align=\"center\">ET</th>";
    html1 += "<th align=\"center\">เวลา</th>";
    html1 += "<th align=\"center\">พนักงาน</th>";
    html1 += "</tr></table></div>";

    var html = "<div id=\"showFixHeader\"><table id=\"tbTest2\" width=\"100%\" border=\"0\" cellpadding=\"5\" cellspacing=\"5\">";

    $.get("PDALoadTableBillServlet?table=" + table+"&etd="+etd, function(responseJson) {
        var bgColor = "";
        var bgColor1 = " style=\"background-color: #EEE; color: #000; height: 50px;\" ";
        var bgColor2 = " style=\"background-color: #FFF; color: #006; height: 50px;\" ";
        var bgColor3 = " style=\"background-color: #FF0000; color: #FFFFFF; height: 50px;\" ";
        if (responseJson !== null) {

            $.each(responseJson, function(key, value) {

                //show table
                document.getElementById("tableNo").innerHTML = value['R_Table'];

                var btnDel = "";
                if (value['R_KicPrint'] === "P" || value['R_Pause'] === "P") {
                    bgColor = bgColor1;
                    btnDel = "";//value['R_PluCode'];
                } else {
                    bgColor = bgColor2;
                    btnDel = "<input type=\"button\" id=\"" + value['R_Index'] + "\" style=\"background-color: red; border: 1px solid; border-radius: 3px; height: 100%; width: 100%; font-size: 18px;\" value=\"ลบ\" onclick=\"deleteProductFromBill(this);\"";
                }

                if (value['R_Void'] === "V") {
                    bgColor = bgColor3;
                }
                html += "<tr id=" + value['R_Index'] + ">";
                html += "<td" + bgColor + "><label style=\"border: 1px solid; border-radius: 5px;\">" + (key + 1) + "</label></td>";
                html += "<td" + bgColor + " id=\"" + value['R_Index'] + "\">" + btnDel + "</td>";
                if (value['R_Void'] === "V") {
                    html += "<td" + bgColor + " id=\"" + value['R_Index'] + "\" onclick=\"showVoidMsg();\">" + value['R_PName'] + "</td>";
                } else {
                    html += "<td" + bgColor + " id=\"" + value['R_Index'] + "\" onclick=\"showOption(this);\">" + value['R_PName'] + "</td>";
                }
                html += "<td" + bgColor + "><label style=\"border: 1px solid; border-radius: 5px;\">" + value['R_Quan'] + "</label></td>";
                html += "<td" + bgColor + ">" + value['R_Price'].toFixed(2) + "</td>";
                html += "<td" + bgColor + ">" + value['R_PrType'] + "</td>";
                html += "<td" + bgColor + ">" + value['R_ETD'] + "</td>";
                html += "<td" + bgColor + ">" + value['R_Time'] + "</td>";
                html += "<td" + bgColor + ">" + value['Cashier'] + "</td>";
                html += "</tr>";

                var opt = "(พิเศษ) ";
                if (value['R_Opt1'] !== "") {
                    html += "<tr><td" + bgColor + "></td><td" + bgColor + "></td>";
                    opt += value['R_Opt1'] + ",";
                    if (value['R_Opt2'] !== "") {
                        opt += value['R_Opt2'] + ",";
                    }
                    if (value['R_Opt3'] !== "") {
                        opt += value['R_Opt3'] + ",";
                    }
                    if (value['R_Opt4'] !== "") {
                        opt += value['R_Opt4'] + ",";
                    }
                    if (value['R_Opt5'] !== "") {
                        opt += value['R_Opt5'] + ",";
                    }
                    if (value['R_Opt6'] !== "") {
                        opt += value['R_Opt6'] + ",";
                    }
                    if (value['R_Opt7'] !== "") {
                        opt += value['R_Opt7'] + ",";
                    }
                    if (value['R_Opt8'] !== "") {
                        opt += value['R_Opt8'] + ",";
                    }
                    if (value['R_Opt9'] !== "") {
                        opt += value['R_Opt9'] + ",";
                    }
                    html += "<td" + bgColor + " colspan=\"8\" style=\"height: 30px;\"><i><font color=green>" + opt + "</font></i></td>";
                    html += "</tr>";
                }

                //total = value['R_TotalServiceAmt'];//mark

            });
        }
        html += "</table></div>";

        document.getElementById("tableBillShow").innerHTML = html;
        document.getElementById("tableBillShow2").innerHTML = html1;

        showTotal(table);
    });
}
