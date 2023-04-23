package com.example.demo_servlet.service.impl;

import com.example.demo_servlet.DAO.CommonDAO;
import com.example.demo_servlet.DAO.impl.UserDao;
import com.example.demo_servlet.entity.User;
import com.example.demo_servlet.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private CommonDAO dao = new UserDao();

    @Override
    public List<User> getAllUser() {
        return null;
    }
}
