package com.example.demo_servlet.config;
import lombok.SneakyThrows;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseConnection {
    private static final Logger logger = Logger.getLogger(String.valueOf(DatabaseConnection.class));

    private static String DB_URL = "jdbc:mysql://127.0.0.1:3306/servlet-demo";
    private static String USER_NAME = "root";
    private static String PASSWORD = "123456";
//    private static String PASSWORD = "qaszxc@123";

    @SneakyThrows
    public static Connection connection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            logger.info("---> connect database success");
            return connection;
        }catch (ClassNotFoundException | SQLException e){
            logger.info("--->  connection failed");
            throw new ClassNotFoundException(e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return connection();
    }

    public static void disconnect(PreparedStatement ps) throws SQLException {
        try {
            ps.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
