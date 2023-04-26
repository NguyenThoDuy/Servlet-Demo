package com.example.demo_servlet.service.impl;

import com.example.demo_servlet.DAO.CommonDAO;
import com.example.demo_servlet.DAO.impl.UserDao;
import com.example.demo_servlet.entity.User;
import com.example.demo_servlet.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(String.valueOf(UserServiceImpl.class));
    private CommonDAO<User> userDao = new UserDao();

    @Override
    public List<User> getAllUser() throws SQLException {
        return userDao.getAll();
    }

    @Override
    public User detail(String id) throws SQLException {
        return userDao.findUserById(id);
    }

    @Override
    public void update(HttpServletRequest request) throws SQLException {
        // lấy các giá trị user gửi lên qua request
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int age = Integer.parseInt(request.getParameter("age"));
        User user = new User(id,name,email,age);
        userDao.updateUser(user);
        logger.info("UserServiceImpl.update() - SUCCESS");
    }

    @Override
    public int save(HttpServletRequest request) throws SQLException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int age = Integer.parseInt(request.getParameter("age"));
        User newUser = User.builder().name(name).email(email).age(age).build();
        int newId = userDao.save(newUser);
        logger.info("UserServiceImpl.save() - SUCCESS");
        return newId;
    }

    @Override
    public void delete(String userId) throws SQLException {
        userDao.deleteById(userId);
        logger.info("UserServiceImpl.delete() - SUCCESS");
    }
}
