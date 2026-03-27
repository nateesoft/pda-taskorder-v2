package com.ics.pdatakeorder.db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class MySQLConnect {
    private String msgError = "พบการเชื่อมต่อมีปัญหา ไม่สามารถดำเนินการต่อได้\nท่านต้องการปิดโปรแกรมอัตโนมัติหรือไม่ ?";

    private Connection connect;
    public static String server;
    public static String db;
    public static String DB;
    public static String username;
    public static String password;
    public static String port;
    public static String charset;
    public static String fontSize;
    public static String inputTable;

    public void initLoadFileConfig() {
        server = "localhost";
        db = "MyRestaurantPaiboon";
        DB = "MyRestaurantPaiboon";
        username = "root";
        password = "nathee2024";
        port = "3307";
        charset = "utf-8";
        fontSize = "170%";
        inputTable = "text";
    }

    public void open() {
        initLoadFileConfig();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (connect == null) {
                connect = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + db + "?characterEncoding=utf-8", username, password);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (connect != null) {
            try {
                connect.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public Connection getConnection() {
        return connect;
    }

}
