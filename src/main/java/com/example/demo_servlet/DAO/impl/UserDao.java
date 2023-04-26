package com.example.demo_servlet.DAO.impl;

import com.example.demo_servlet.DAO.CommonDAO;
import com.example.demo_servlet.config.DatabaseConnection;
import com.example.demo_servlet.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements CommonDAO<User> {
    @Override
    public List<User> getAll() throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        List<User> users = new ArrayList<>();
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "select * from Users";
            statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            users = mapResultToUserEntity(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.disconnect(statement);
        }
        return users;
    }

    @Override
    public User findUserById(String userId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        User user = null;
        try {
            conn = DatabaseConnection.getConnection();
            ps = conn.prepareStatement("select * from Users where ID = ?");
            ps.setString(1, userId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String email = resultSet.getString("Email");
                int age = resultSet.getInt("Age");
                user = new User(id, name, email, age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.disconnect(ps);
        }
        return user;
    }

    @Override
    public void updateUser(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DatabaseConnection.getConnection();
            ps = conn.prepareStatement("update Users SET Name = ?, Email = ?, Age = ? WHERE ID = ?");
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setInt(3, user.getAge());
            ps.setInt(4, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.disconnect(ps);
        }
    }

    @Override
    public int save(User newUser) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int id = 0;
        try {
            conn = DatabaseConnection.getConnection();
            ps = conn.prepareStatement("INSERT INTO Users (Name, Email, Age) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, newUser.getName());
            ps.setString(2, newUser.getEmail());
            ps.setInt(3, newUser.getAge());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.disconnect(ps);
        }
        return id;
    }

    @Override
    public void deleteById(String userId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DatabaseConnection.getConnection();
            ps = conn.prepareStatement("DELETE FROM Users WHERE ID = ?");
            ps.setString(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.disconnect(ps);
        }
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
