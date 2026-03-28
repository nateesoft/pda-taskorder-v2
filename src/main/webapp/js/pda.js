function alertInvalid() {
    alert("Cannot Take Order\n Please Take Order at Cashier \n Because This item are Open price");
}

function showVoidMsg() {
    alert("Item are Removed");
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

