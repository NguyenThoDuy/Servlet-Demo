package com.example.demo_servlet.controller;

import com.example.demo_servlet.service.UserService;
import com.example.demo_servlet.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
//@WebServlet({"/user"}) thiết lập các đường dẫn (URLs) của các chức năng quản lý người dùng trong một ứng dụng web Java Servlet.
@WebServlet({"/user", "/user/new", "/user/save", "/user/detail", "/user/edit", "/user/update", "/user/delete", "/user/redirectHome"})
public class UserController extends BaseController {
    private transient UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    // kiểm tra user đang gọi tới phương thức nào và điều hướng nó tới phương thức đó
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String method = request.getMethod();
        switch (method) {
            case "GET":
                doGet(request, response);
                break;
            case "POST":
                doPost(request, response);
                break;
            default:
                break;
        }
    }

    //doGet() nơi xử lý các yêu cầu theo phương thức GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uri = request.getServletPath();
        try {
            switch (uri) {
                case "/user":
                    requestProcessinggetAll(request, response, userService.getAllUser(), "index.jsp");
                    break;
                case "/user/new":
                    showNewForm(request, response, "/view/userForm.jsp");
                    break;
                case "/user/detail":
                    requestProcessingDetail(request, response, userService.detail(getUserId(request)), "/view/userForm.jsp");
                    break;
                case "/user/delete":
                    userService.delete(getUserId(request));
                    response.sendRedirect(request.getContextPath().concat("/user"));
                    break;
                default:
                    response.sendRedirect(request.getContextPath().concat("/user"));
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //doPost() nơi xử lý các yêu cầu theo phương thức POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getServletPath();
        String contextPath = request.getContextPath();
        try {
            //kiểm tra user đang gọi url nào và điều hướng tới service
            switch (uri) {
                case "/user/save":
                    int newId = userService.save(request);
                    // sendRedirect  thực hiện điều hớn tới url khác
                    response.sendRedirect(contextPath + "/user/detail?id=" + newId);
                    break;
                case "/user/update":
                    userService.update(request);
                    response.sendRedirect(contextPath + "/user/detail?id=" + getUserId(request));
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getUserId(HttpServletRequest request) {
        return request.getParameter("id");
    }
}
