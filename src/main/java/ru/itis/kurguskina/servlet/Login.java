package ru.itis.kurguskina.servlet;


import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class Login extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(Login.class);

    public static final String LOGIN = "login";
    public static final String PASSWORD = "password123";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (LOGIN.equals(login) && PASSWORD.equals(password)) {
//            logger.info("User with username = {} logged in.", login);
            HttpSession session = req.getSession();
            session.setAttribute("username", login);
            session.setMaxInactiveInterval(60 * 60);

            Cookie userCookie = new Cookie("username", login);
            userCookie.setMaxAge(24 * 60 * 60);
            resp.addCookie(userCookie);

            resp.sendRedirect("Main.jsp");
        } else {
            resp.sendRedirect("/login");
        }

    }
}
