package ru.itis.kurguskina.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itis.kurguskina.dto.UserDto;
import ru.itis.kurguskina.helper.PasswordHelper;
import ru.itis.kurguskina.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

@WebServlet(name = "MainPageAnonServlet", urlPatterns = "/mainanon")
public class MainPageAnonServlet extends HttpServlet {

//        private static final Logger logger = LoggerFactory.getLogger(ru.itis.kurguskina.servlet.SignInServlet.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

//        HttpSession session = request.getSession();
//        session.setAttribute("username", username);
//        session.setMaxInactiveInterval(60 * 60);
//
//        Cookie userCookie = new Cookie("username", username);
//        userCookie.setMaxAge(24 * 60 * 60);
//        response.addCookie(userCookie);

        Cookie[] coockies = request.getCookies();
        for (int i = 0; i< coockies.length;i++){
            String name = coockies[i].getName();
            String value = coockies[i].getValue();
        }

        resp.sendRedirect("blog.html");
    }

    @Override
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

        Cookie[] coockies = request.getCookies();
        for (int i = 0; i< coockies.length;i++){
            String name = coockies[i].getName();
            String value = coockies[i].getValue();
        }

//            if(userDao.findUser(username,password) != null){
//                response.sendRedirect("registration.html");
//            }
//            else{
//                userDao.addUser(new User(username,password));
//                response.sendRedirect("blog.html");
//            }
    }

}