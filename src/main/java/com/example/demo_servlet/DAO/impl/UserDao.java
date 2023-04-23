package com.example.demo_servlet.DAO.impl;

import com.example.demo_servlet.DAO.CommonDAO;
import com.example.demo_servlet.config.DatabaseConnection;
import com.example.demo_servlet.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements CommonDAO<User> {
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    @Override
    public List<User> getAll() throws SQLException {
        Connection connection = databaseConnection.getConnection();
        String sql = "select * from Users";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        return mapResultToUserEntity(resultSet);
    }

    private List<User> mapResultToUserEntity(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("Name");
            String email = resultSet.getString("Email");
            int age = resultSet.getInt("Age");
            User user = User.builder()
                    .id(id).name(name).age(age).email(email)
                    .build();
            users.add(user);
        }
        return users;
    }

}
