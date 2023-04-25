package com.example.demo_servlet.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class BaseController extends HttpServlet {

    public void requestProcessinggetAll(HttpServletRequest request, HttpServletResponse response, List<? extends Object> data, String htmlFile) throws ServletException, IOException {
        request.setAttribute("data", data);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/".concat(htmlFile));
        dispatcher.forward(request, response);
    }

    public void requestProcessingDetail(HttpServletRequest request, HttpServletResponse response, Object data, String htmlFile) throws ServletException, IOException {
        System.out.println(data.toString());
        request.setAttribute("data", data);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/".concat(htmlFile));
        dispatcher.forward(request, response);
    }
}
