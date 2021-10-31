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

//        response.sendRedirect("blog.html");

//        true false=jdbcimpl.checkuser(login, password);
//        if else

//            response.sendRedirect("blog.html");
//        } else {
//            resp.sendRedirect("signin.html");
//        }
//        Map<String, String> errors = new HashMap<String,String>();
//        if (login == null || "".equals(login)) {
//            errors.put("login", "Имя пользователя не может быть пустым!");
//        } else if (login != null && userDao.findUser(login,password) != null) {
//            errors.put("userName", "Этот пользователь уже зарегистрирован!");
//        }
//
//        if (password == null || "".equals(password)) {
//            errors.put("password","Пароль не может быть пустым!");
//        } else if (password != null && password.length() < 3) {
//            errors.put("password","Длина пароля не может быть меньше 3 цифр!");
//        }
//
//        if (password2 == null || "".equals(password2)) {
//            errors.put("password2", "Подтверждение пароля не может быть пустым!");
//        } else if (password2 != null && !password2.equals(password)) {
//            errors.put("password2", "Пароли, введенные дважды, не совпадают!");
//        }
//        if (email == null || "".equals(email)) {
//            errors.put("email", "электронная почта не может быть пустой!");
//
//        } else if (email != null && !email.matches("[0-9a-zA-Z_-]+@[0-9a-zA-Z_-]+\\.[0-9a-zA-Z_-]+(\\.[0-9a-zA-Z_-])*")) {
//            errors.put("email", "Неверный формат электронной почты!");
//        }
//
//
//        if (!errors.isEmpty()) {
//            request.setAttribute("errors", errors);
//            request.getRequestDispatcher("/registerUI").forward(request, response);
//            return;
//        }
//
//        User user = new User();
//        user.setUsername(login);
//        user.setPassword(password);
//        user.setUsername(email);
////        user.setActivated(false);
//
//        userDao.addUser(user);

        // После успешной регистрации отправляем ссылку активации аккаунта
//        EmailUtils.sendAccountActivateEmail(user);

//        // Успешная регистрация напрямую сохраняет текущего пользователя в сеансе
//        request.getSession().setAttribute("user", user);
//        request.getRequestDispatcher("/WEB-INF/pages/registerSuccess.jsp").forward(request,response);
    }

}
