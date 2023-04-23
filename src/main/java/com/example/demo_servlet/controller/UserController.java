package com.example.demo_servlet.controller;

import com.example.demo_servlet.entity.User;
import com.example.demo_servlet.service.UserService;
import com.example.demo_servlet.service.impl.UserServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/user"})
public class UserController extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

//    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        String method = request.getMethod();
//        switch (method) {
//            case "GET":
//                doGet(request, response);
//            case "POST":
//                doPost(request, response);
//        }
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        String uri = request.getRequestURI();
//        switch (uri){
//            case "user" :
//                getAll(request, response);
//                break;
//            default:
//                break;
//        }
        getAll();
        response.getWriter().append("Served at: ").append(request.getContextPath());
        processRequest(request, response);
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listUser", userService.getAllUser());
        RequestDispatcher dispatcher = request.getRequestDispatcher("user.jsp");
        dispatcher.forward(request, response);
    }

    private void getAll() {
        List<User> users = userService.getAllUser();
        System.out.println("-----------");
        users.forEach(System.out::println);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

}
