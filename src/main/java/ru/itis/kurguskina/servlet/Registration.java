package ru.itis.kurguskina.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import ru.itis.kurguskina.dao.UserDao;
import ru.itis.kurguskina.dao.impl.UserDaoJdbcImpl;
import ru.itis.kurguskina.model.User;

/**
 Регистрация пользователя
 */
@WebServlet("/registr")
public class Registration extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDao userDao = UserDaoJdbcImpl.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String email = request.getParameter("email");


        Map<String, String> errors = new HashMap<String,String>();
        if (login == null || "".equals(login)) {
            errors.put("login", "Имя пользователя не может быть пустым!");
        } else if (login != null && userDao.findUser(login,password) != null) {
            errors.put("userName", "Этот пользователь уже зарегистрирован!");
        }

        if (password == null || "".equals(password)) {
            errors.put("password","Пароль не может быть пустым!");
        } else if (password != null && password.length() < 3) {
            errors.put("password","Длина пароля не может быть меньше 3 цифр!");
        }

        if (password2 == null || "".equals(password2)) {
            errors.put("password2", "Подтверждение пароля не может быть пустым!");
        } else if (password2 != null && !password2.equals(password)) {
            errors.put("password2", "Пароли, введенные дважды, не совпадают!");
        }
        if (email == null || "".equals(email)) {
            errors.put("email", "электронная почта не может быть пустой!");

        } else if (email != null && !email.matches("[0-9a-zA-Z_-]+@[0-9a-zA-Z_-]+\\.[0-9a-zA-Z_-]+(\\.[0-9a-zA-Z_-])*")) {
            errors.put("email", "Неверный формат электронной почты!");
        }


        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/registerUI").forward(request, response);
            return;
        }

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
//        user.setActivated(false);

        userDao.addUser(user);

        // После успешной регистрации отправляем ссылку активации аккаунта
//        EmailUtils.sendAccountActivateEmail(user);

        // Успешная регистрация напрямую сохраняет текущего пользователя в сеансе
        request.getSession().setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/pages/registerSuccess.jsp").forward(request,response);
    }

}
