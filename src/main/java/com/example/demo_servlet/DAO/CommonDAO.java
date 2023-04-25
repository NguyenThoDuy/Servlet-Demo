package com.example.demo_servlet.DAO;

import com.example.demo_servlet.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface CommonDAO<T> {
    List<T> getAll() throws SQLException;

    User findUserById(String userId);

    void updateUser(User user);
}
