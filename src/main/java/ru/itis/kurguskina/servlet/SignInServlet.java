package ru.itis.kurguskina.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itis.kurguskina.dao.UserDao;
import ru.itis.kurguskina.dao.impl.UserDaoJdbcImpl;
import ru.itis.kurguskina.dto.UserDto;
import ru.itis.kurguskina.helper.PasswordHelper;
import ru.itis.kurguskina.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "SignInServlet", urlPatterns = "/serv")
public class SignInServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(SignInServlet.class);
    private UserDao userDao = new UserDaoJdbcImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userValue = null;
        Cookie[] coockies = req.getCookies();
        for (int i = 0; i < coockies.length; i++) {
            String name = coockies[i].getName();
            if (name == "username") {
                userValue = coockies[i].getValue();
                break;
            }
        }
        if (userValue != null) {
            Cookie userCookie = new Cookie("username", userValue);
            userCookie.setMaxAge(24 * 60 * 60);
            resp.addCookie(userCookie);
            resp.sendRedirect("blogauth.html");
        } else {
            resp.sendRedirect("signin.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String userValue = null;
//        Cookie[] coockies = req.getCookies();
//        for (int i = 0; i< coockies.length;i++){
//            String name = coockies[i].getName();
//            if (name=="username") {
//                userValue = coockies[i].getValue();
//                break;
//            }
//        }
//        if (userValue == null) {
//            resp.sendRedirect("signin.html");
//        }
//        else {
            String login = req.getParameter("username");
            String password = req.getParameter("password");
            User user = userDao.findUser(login, password);
            if (user.getUsername().equals(login) && user.getPassword().equals(password)) {
                logger.info("User with username = {} logged in.", login);
                HttpSession session = req.getSession();
                session.setAttribute("username", login);
                session.setMaxInactiveInterval(60 * 60);

                Cookie userCookie = new Cookie("username", login);
                userCookie.setMaxAge(24 * 60 * 60);
                resp.addCookie(userCookie);
                resp.sendRedirect("blogauth.html");
            } else {
                resp.sendRedirect("signin.html");
            }
//        }

    }
}