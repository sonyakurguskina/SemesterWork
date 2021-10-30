package ru.itis.kurguskina.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "SignInServlet", urlPatterns = "/serv")
public class SignInServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(SignInServlet.class);

    public static final String LOGIN = "l";
    public static final String PASSWORD = "l";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("signin.html");
//        resp.sendRedirect("form.html");
//        resp.sendRedirect("/www/blog.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (LOGIN.equals(login) && PASSWORD.equals(password)) {
            logger.info("User with username = {} logged in.", login);
            HttpSession session = req.getSession();
            session.setAttribute("username", login);
            session.setMaxInactiveInterval(60 * 60);

            Cookie userCookie = new Cookie("username", login);
            userCookie.setMaxAge(24 * 60 * 60);
            resp.addCookie(userCookie);
//        true false=jdbcimpl.checkuser(login, paswword);
//        if else

            resp.sendRedirect("blog.html");
        } else {
            resp.sendRedirect("signin.html");
        }

    }
}