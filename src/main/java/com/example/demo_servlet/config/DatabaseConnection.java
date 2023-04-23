package com.example.demo_servlet.config;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String DB_URL = "jdbc:mysql://localhost:3306/servlet-demo";
    private static String USER_NAME = "root";
    private static String PASSWORD = "qaszxc@123";

    @SneakyThrows
    public static Connection connection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("connect database success");
            return connection;
        }catch (ClassNotFoundException | SQLException e){
            System.out.println("connection failed");
            throw new ClassNotFoundException(e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        return connection();
    }
}
