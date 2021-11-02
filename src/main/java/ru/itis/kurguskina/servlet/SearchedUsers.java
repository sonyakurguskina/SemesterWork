package ru.itis.kurguskina.servlet;

import ru.itis.kurguskina.dao.UserDao;
import ru.itis.kurguskina.dao.impl.UserDaoJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="SearchedUsers", urlPatterns = "/searchedUsers")
public class SearchedUsers extends HttpServlet {
    UserDao userDao = new UserDaoJdbcImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("blog.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String searchedUsers = request.getParameter("searchedUsers");

        String users = userDao.findUsersByName(searchedUsers);
//
//        PrintWriter out = response.getWriter();
//        out.write(users);


        request.setAttribute("name", users);
        request.getRequestDispatcher("searchedUsers.jsp").forward(request, response);
    }
}
