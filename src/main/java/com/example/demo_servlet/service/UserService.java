package com.example.demo_servlet.service;

import com.example.demo_servlet.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<User> getAllUser() throws SQLException;
}
