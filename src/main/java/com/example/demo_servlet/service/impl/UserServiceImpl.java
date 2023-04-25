package com.example.demo_servlet.service.impl;

import com.example.demo_servlet.DAO.CommonDAO;
import com.example.demo_servlet.DAO.impl.UserDao;
import com.example.demo_servlet.entity.User;
import com.example.demo_servlet.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private CommonDAO dao = new UserDao();

    @Override
    public List<User> getAllUser() throws SQLException {
        return dao.getAll();
    }

    @Override
    public User detail(String id) {
        return dao.findUserById(id);
    }

    @Override
    public void update(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int age = Integer.parseInt(request.getParameter("age"));
        User user = new User(id,name,email,age);
        dao.updateUser(user);
    }
}
