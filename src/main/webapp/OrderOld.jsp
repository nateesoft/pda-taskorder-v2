<%@page import="java.util.List"%>
<%@page import="com.ics.pdatakeorder.util.ThaiUtil"%>
<%@page import="com.ics.pdatakeorder.model.PKicTran"%>
<%@page import="com.ics.pdatakeorder.model.PKicTranBean"%>
<%@page import="com.ics.pdatakeorder.model.ControlPrintCheckBill"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.ics.pdatakeorder.control.BalanceControl"%>
<%@page import="com.ics.pdatakeorder.model.BalanceBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ics.pdatakeorder.control.EmployControl"%>
<%@page import="com.ics.pdatakeorder.model.ControlPrintCheckBill"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Item Page</title>
        <script type="text/javascript" src="jquery-latest.min.js"></script>
        <script type="text/javascript">
            function delData(prefix, r_index, pcode, qty) {
                $.get("Remove?prefix=" + prefix + "&R_Index=" + r_index + "&PCode=" + qty + "*" + pcode, function (responseJson) {

                    if (responseJson !== null) {
                        $.each(responseJson, function (key, value) {

                        });
                    }
                });
            }
            function show1() {
                document.getElementById("radio1").checked = "CHECKED";
            }

            function show2() {
                document.getElementById("radio2").checked = "CHECKED";
            }
            function show3() {
                document.getElementById("radio3").checked = "CHECKED";
            }
            function show4() {
                document.getElementById("radio4").checked = "CHECKED";
            }
            function show5() {
                document.getElementById("radio5").checked = "CHECKED";
            }
            function show6() {
                document.getElementById("radio6").checked = "CHECKED";
            }
            function detail(R_Index) {
                var text = R_Index.id;
                var prefix = document.getElementById("txtPrefix").value;
                var clickPrint = document.getElementById("print").value;
                window.location = "Order?R_Index=" + text + "&prefix=" + prefix + clickPrint;
            }
        </script>
        <script>
            function myFunctionUrgentByItem($abcd) {
                var username = document.getElementById("PCodetxt").value;
                window.alert("Hello! I am an alert box!!" + username + $abcd);

                var input = document.getElementById('PCodetxt');
                var value = input.value;

                localStorage.setItem('username', "U#" + value);
                window.location.href = 'urgentFoodItem.jsp';
            }
        </script>
        <link rel="stylesheet" type="text/css" href="pda.css">
    </head>
    <body>
        <%
            int size = 0;
            String prefix = (String) request.getParameter("prefix");
            if (prefix == null || prefix.equals("")) {
                prefix = "A";
            }
        %>
        <table width="100%" border="0" id="tableBill">
            <tr>
                <th width="40" bgcolor="#00CCFF">#</th>
                <th width="155" bgcolor="#00CCFF">ประเภท</th>
                <th width="471" bgcolor="#00CCFF">รายการ</th>
                <th width="57" bgcolor="#00CCFF">จำนวน</th>
                <th width="71" bgcolor="#00CCFF">ราคา</th>
                <th width="83" bgcolor="#00CCFF">รวม</th>
                <th width="200" bgcolor="#00CCFF">พนักงาน</th>
            </tr>
            <%
                BalanceControl bc = new BalanceControl();
                DecimalFormat dec = new DecimalFormat("#,##0");
                String table = (String) session.getAttribute("tableNo");
                if (table == null) {
                    table = "";
                }
                List<BalanceBean> listBalance = bc.getAllBalanceHold(table);
                if (listBalance == null) {
                    listBalance = new ArrayList();
                }
                size = listBalance.size();
                int index = 0;
                for (int i = 0; i < listBalance.size(); i++) {
                    index++;
                    BalanceBean balanceBean = (BalanceBean) listBalance.get(i);
            %>
            <tr>
                <td height="55px" align="center"><%=index%></td>
                <td height="5%" align="center">
                    <%
                        if (balanceBean.getR_ETD().equals("E")) {
                            out.println("นั่งทาน");
                        } else if (balanceBean.getR_ETD().equals("T")) {%>
                    <span style="background-color: #00ff99;">ห่อกลับ</span>
                    <%
                    } else if (balanceBean.getR_ETD().equals("D")) {%>
                    <span style="background-color: #FF9966;">เดลิเวอรี่</span>                    
                    <%} else {
                            out.println("Unknow");
                        }
                    %>
                </td>
                <td onclick="detail(this);" style="font-size: 15px;" id="<%=balanceBean.getR_Index()%>"><%=balanceBean.getR_PluCode()%> # <%=balanceBean.getR_PName()%><br />
                    <%

                        String opt1 = balanceBean.getR_Opt1();
                        String opt2 = balanceBean.getR_Opt2();
                        String opt3 = balanceBean.getR_Opt3();
                        String opt4 = balanceBean.getR_Opt4();
                        String opt5 = balanceBean.getR_Opt5();
                        String opt6 = balanceBean.getR_Opt6();
                        String opt7 = balanceBean.getR_Opt7();
                        String opt8 = balanceBean.getR_Opt8();

                        if (!opt1.equals("")) {
                            out.print("<u>" + opt1 + "</u>,");
                        }
                        if (!opt2.equals("")) {
                            out.print("<u>" + opt2 + "</u>,");
                        }
                        if (!opt3.equals("")) {
                            out.print("<u>" + opt3 + "</u>,");
                        }
                        if (!opt4.equals("")) {
                            out.print("<u>" + opt4 + "</u>,");
                        }
                        if (!opt5.equals("")) {
                            out.print("<u>" + opt5 + "</u>,");
                        }
                        if (!opt6.equals("")) {
                            out.print("<u>" + opt6 + "</u>,");
                        }
                        if (!opt7.equals("")) {
                            out.print("<u>" + opt7 + "</u>,");
                        }
                        if (!opt8.equals("")) {
                            out.print("<u>" + opt8 + "</u>,");
                        }
                    %>
                </td>

                <td align="right"><%=dec.format(balanceBean.getR_Quan())%></td>
                <td align="right"><%=dec.format(balanceBean.getR_Price())%></td>
                <td align="right"><%=dec.format(balanceBean.getR_Total())%></td>
                <td align="center"><%=balanceBean.getR_Emp()%></td>
            </tr>
            <% }%>
        </table>
        <p><a href="Login2?prefix=<%=prefix%>">                
                <input type="button" name="button" id="button" value="กลับเมนูหลัก" style="width: 100%; height: 60px; font-size: 28px; background-color: #900; color: #FFF;">
            </a></p>
        <label>
            อาหารที่กำลังปรุง
        </label>
    <tr></tr>
    <form name="frmUrgentFood" action="urgentFoodItem.jsp">
        <table width="100%" border="0" id="tableKictran">
            <tr>
                <th width="40" bgcolor="#00CCFF">จำนวน</th>
                <th width="100" bgcolor="#00CCFF">ประเภท</th>
                <th width="471" bgcolor="#COCOCO">รายการ</th>
                <th width="65" bgcolor="#00CCFF">เวลาที่รอ ชม.นาที</th>
                <th width="65" bgcolor="#00CCFF">เชฟกดรับ</th>
                <th width="65" bgcolor="#00CCFF">สถานะ</th>
            </tr>
            <%
                if (size > 0) {
                    PKicTran kicTran = new PKicTran();
                    List<PKicTranBean> list = kicTran.getKicTran(table);
                    if (list.size() > 0) {
                        for (int i = 0; i < list.size(); i++) {
            %>


            <td align="center" height="30px">
                <%=(list.get(i).getpQty())%>
            </td>
            <td align="center">
                <%=(list.get(i).getpEtd())%>
            </td>
            <td>
                <%=(ThaiUtil.ASCII2Unicode(list.get(i).getpDesc()))%>
            <td align="center">
                <%=(list.get(i).getpWaitTime())%>
            </td>
            <td align="center">
                <%=(list.get(i).getShowDisplayAlert())%>
            </td>

            <td align="center">
                <%-- <img src="img/alert.gif" alt="Alert!" width="45" height="45"  onclick="location.href = 'urgentFoodItem.jsp?item=<%=(list.get(i).getpCode())%>?table=<%=table%>?pluName<%=(ThaiUtil.ASCII2Unicode(list.get(i).getpDesc()))%>';" style="cursor: pointer;">--%>
                <img src="img/alert.gif" alt="Alert!" width="45" height="45"  onclick="location.href = 'urgentFoodItem.jsp?tableNo=<%=table%>&pluCode=<%=(list.get(i).getpCode())%>&pluName=<%=((list.get(i).getpDesc()))%>&pindex=<%=list.get(i).getpIndex()%>';" style="cursor: pointer;">
            </td>   


            <tr>
                <%
                            }

                        }
                    } else {
                        //PKicTran kicTran = new PKicTran();
                        //kicTran.clearKicTran(table);
                    }
                %>

        </table>
    </form>
    <table width="100%" border="0" id="empty0">
        <tr>
            <td width="76" align="center" heignt="100"><input value="กรุณาเลือกเครื่องปริ้นต์" type="button" name="button3" style="width: 100%; height: 60px; font-size: 24px; background-color: #FFE4B5; color: #212F3D; border-radius: 10px 0px 10px 0px; border: 1px solid;"></td>
        </tr>
    </table>
    <form name="frmAdd" method="get" action="printCheckBill.jsp">
        <div style=" alignment-adjust:  central; width: 100%; border: 10px #002A80; border-collapse:  collapse; align-items:  center"> 
            <table width="100%" border="0" id="footer" align="center">
                <tr>
                    <td align="center" border="0">
                        <input type="radio" name="chkType" id="radio1" value="Station1" checked="CHECKED">
                        <input type="button" style="font-size: 22px; width: 200px; background-color: #FFE4B5; padding-bottom: 10px;" onclick="show1();" value="Station1"><br />
                        <input type="radio" name="chkType" id="radio2" value="Station2">
                        <input type="button" style="font-size: 22px; width: 200px; background-color:  #FFE4B5; padding-bottom: 10px;" onclick="show2();" value="Station2"><br />
                        <input type="radio" name="chkType" id="radio3" value="Station3">
                        <input type="button" style="font-size: 22px; width: 200px; background-color:  #FFE4B5; padding-bottom: 10px;" onclick="show3();" value="Station3"><br />
                        <input type="radio" name="chkType" id="radio4" value="Station4">
                        <input type="button" style="font-size: 22px; width: 200px; background-color:  #FFE4B5; padding-bottom: 10px;" onclick="show4();" value="Station4"><br />
                        <input type="radio" name="chkType" id="radio5" value="Station5">
                        <input type="button" style="font-size: 22px; width: 200px; background-color:  #FFE4B5; padding-bottom: 10px;" onclick="show5();" value="Station5"><br />
                        <input type="radio" name="chkType" id="radio6" value="Station6">
                        <input type="button" style="font-size: 22px; width: 200px; background-color:  #FFE4B5; padding-bottom: 10px;" onclick="show6();" value="Station6"><br />
                </tr>
            </table>
        </div>
        <div align="center">
            <td width="76" align="center">
                <input type="text" name="table" id="table" value="<%=table%>">
                <input type="submit" name="print" id="print" value="Check Bill Print" onclick="<%%>"  style="width: 100%; height: 80px; font-size: 30px; background-color: #909; color: #fff; border-radius: 10px 0px 10px 0px; border: 1px solid;">
            </td>
        </div>
        <div align="center">
            <td width="76" align="center">
                <input type="text" name="table" id="table" value="<%=table%>">
                <input type="submit" name="printAll" id="print" value="Check Bill All" onclick="<%%>"  style="width: 100%; height: 80px; font-size: 30px; background-color: #909; color: #fff; border-radius: 10px 0px 10px 0px; border: 1px solid;">
            </td>
        </div>
        <div align="center">
            <p><a href="Login2?prefix=<%=prefix%>">
                    <input type="button" name="button" id="button20" value="กลับเมนูหลัก" style="width: 100%; height: 60px; font-size: 28px; background-color: #900; color: #FFF;">
                </a></p>
        </div>
    </form>
    <div align="center"> 
        <p><a href="printKictranAgain.jsp?table=<%=table%>"</p>
        <td width="76" align="center">
            <input type="submit" name="print" id="urgentfood" value="ตามอาหารที่ยังไม่ได้" onclick="<%%>"  style="width: 100%; height: 80px; font-size: 30px; background-color: dodgerblue; color: #fff; border-radius: 10px 0px 10px 0px; border: 1px solid;">
        </td>
    </div>
</body>
</html>
