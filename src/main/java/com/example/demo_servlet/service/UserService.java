package com.example.demo_servlet.service;

import com.example.demo_servlet.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<User> getAllUser() throws SQLException;

    User detail(String id) throws SQLException;

    void update(HttpServletRequest request) throws SQLException;

    int save(HttpServletRequest request) throws SQLException;

    void delete(String userId) throws SQLException;
}
