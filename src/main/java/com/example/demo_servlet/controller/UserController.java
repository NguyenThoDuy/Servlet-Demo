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
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@WebServlet({"/user"})
public class UserController extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String method = request.getMethod();
        switch (method) {
            case "GET":
                doGet(request, response);
                break;
            case "POST":
                doPost(request, response);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uri = request.getRequestURI();
        try {
            switch (uri) {
                case "/demo_servlet_war/user":
                    requestProcessing(request, response, userService.getAllUser(), "user.jsp");
                    break;
                default:
                    break;
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private void requestProcessing(HttpServletRequest request, HttpServletResponse response, List<? extends Object> data, String htmlFile) throws ServletException, IOException {
        request.setAttribute("data", data);
        RequestDispatcher dispatcher = request.getRequestDispatcher(htmlFile);
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

}
