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
    @Override
    public List<User> getAll() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "select * from Users";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        return mapResultToUserEntity(resultSet);
    }

    @Override
    public User findUserById(String userId) {
        Connection conn = null;
        PreparedStatement ps = null;
        User user = null;
        try {
            conn = DatabaseConnection.getConnection();
            ps = conn.prepareStatement("select * from Users where ID = ?");
            ps.setString(1, userId);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String email = resultSet.getString("Email");
                int age = resultSet.getInt("Age");
                user = new User(id,name,email,age);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DatabaseConnection.getConnection();
            ps = conn.prepareStatement("update User SET Name = ?, Email = ?, Age = ? WHERE ID = ?");
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setInt(3, user.getAge());
            ps.setInt(4, user.getId());
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
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
