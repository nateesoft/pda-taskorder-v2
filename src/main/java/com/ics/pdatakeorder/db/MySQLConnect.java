package com.ics.pdatakeorder.db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class MySQLConnect {
    private String msgError = "พบการเชื่อมต่อมีปัญหา ไม่สามารถดำเนินการต่อได้\nท่านต้องการปิดโปรแกรมอัตโนมัติหรือไม่ ?";

    private Connection connect;
    private final static String MYSQL_SERVER;
    private final static String MYSQL_DATABASE;
    private final static String MYSQL_USER;
    private final static String MYSQL_PASS;
    private final static String MYSQL_PORT;
    
    static {
        MYSQL_SERVER = "localhost";
        MYSQL_DATABASE = "MyRestaurantPaiboon";
        MYSQL_USER = "root";
        MYSQL_PASS = "nathee2024";
        MYSQL_PORT = "3307";
    }

    public void open() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://" + MYSQL_SERVER + ":" + MYSQL_PORT + "/" + MYSQL_DATABASE + "?characterEncoding=utf-8", MYSQL_USER, MYSQL_PASS);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void close() {
        if (connect != null) {
            try {
                connect.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
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
