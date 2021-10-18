package ru.itis.kurguskina.servlet;
import ru.itis.kurguskina.dto.UserDto;
import ru.itis.kurguskina.service.UserService;
import ru.itis.kurguskina.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "usersServlet", urlPatterns = "/users")
public class User extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserDto> users = userService.getAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("users.ftl").forward(request, response);
    }
}
