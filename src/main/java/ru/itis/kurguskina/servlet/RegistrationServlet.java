package ru.itis.kurguskina.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import ru.itis.kurguskina.dao.UserDao;
import ru.itis.kurguskina.dao.impl.UserDaoJdbcImpl;
import ru.itis.kurguskina.model.User;

/**
 Регистрация пользователя
 */
@WebServlet(name="RegistrationServlet", urlPatterns = "/registr")
public class RegistrationServlet extends HttpServlet {

//    private static final long serialVersionUID = 1L;
//
    private UserDao userDao = new UserDaoJdbcImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("registration.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

//        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        String password2 = request.getParameter("password2");

            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setMaxInactiveInterval(60 * 60);

            Cookie userCookie = new Cookie("username", username);
            userCookie.setMaxAge(24 * 60 * 60);
            response.addCookie(userCookie);

            if(userDao.findUser(username,password) != null){
                response.sendRedirect("registration.html");
            }
            else{
                userDao.addUser(new User(username,password));
                response.sendRedirect("blog.html");
            }
    }

}
