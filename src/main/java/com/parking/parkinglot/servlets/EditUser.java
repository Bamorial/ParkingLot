package com.parking.parkinglot.servlets;

import com.parking.parkinglot.common.CarDto;
import com.parking.parkinglot.common.UsersDto;
import com.parking.parkinglot.ejb.UsersBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditUser", value = "/EditUser")
public class EditUser extends HttpServlet {
    @Inject
    UsersBean usersBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long userId=Long.parseLong(request.getParameter("id"));
        UsersDto user=usersBean.findById(userId);
        request.setAttribute("user",user);
        request.getRequestDispatcher("/WEB-INF/pages/editUser.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        Long id=Long.parseLong(request.getParameter("id"));
        String email=request.getParameter("email");
        String password= request.getParameter("password");

        usersBean.updateUser(id ,username, email,password);

        response.sendRedirect(request.getContextPath()+"/Users");
    }
}
