package com.example.demo_servlet.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
// khai báo các hàm dùng chung cho mọi màn khi kế thừa có thể sử dụng mà không cần khởi tạo lại
public class BaseController extends HttpServlet {

    public void requestProcessinggetAll(HttpServletRequest request, HttpServletResponse response, List<? extends Object> data, String htmlFile) throws ServletException, IOException {
        request.setAttribute("data", data);
        RequestDispatcher dispatcher = request.getRequestDispatcher(htmlFile);
        dispatcher.forward(request, response);
    }

    public void showNewForm(HttpServletRequest request, HttpServletResponse response, String htmlFile) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(htmlFile);
        dispatcher.forward(request, response);
    }

    public void requestProcessingDetail(HttpServletRequest request, HttpServletResponse response, Object data, String htmlFile) throws ServletException, IOException {
        request.setAttribute("item", data);
        RequestDispatcher dispatcher = request.getRequestDispatcher(htmlFile);
        dispatcher.forward(request, response);
    }
}
