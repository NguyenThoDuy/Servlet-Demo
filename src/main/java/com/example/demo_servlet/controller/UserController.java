package com.example.demo_servlet.controller;

import com.example.demo_servlet.service.UserService;
import com.example.demo_servlet.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/user", "/user/detail", "/user/update"})
public class UserController extends BaseController {
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
                case "/Servlet_Demo_war/user":
                    requestProcessinggetAll(request, response, userService.getAllUser(), "user.jsp");
                    break;
                case "/Servlet_Demo_war/user/detail":
                    requestProcessingDetail(request, response, userService.detail(getUserId(request)), "addform.jsp");
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        try {
            switch (uri) {
                case "/Servlet_Demo_war/user/update":
                    userService.update(request);
                    requestProcessingDetail(request, response, userService.detail(request.getParameter("id")), "addform.jsp");
                    break;
                case "/Servlet_Demo_war/user/detail":
                    requestProcessingDetail(request, response, userService.detail(request.getParameter("id")), "addform.jsp");
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String getUserId(HttpServletRequest request){
        return request.getParameter("id");
    }
}
