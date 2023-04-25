package com.example.demo_servlet.service;

import com.example.demo_servlet.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<User> getAllUser() throws SQLException;

    User detail(String id);

    void update(HttpServletRequest request);
}
