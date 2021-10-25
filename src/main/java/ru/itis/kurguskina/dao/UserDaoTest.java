package ru.itis.kurguskina.dao;

import ru.itis.kurguskina.dao.impl.UserDaoJdbcImpl;
import ru.itis.kurguskina.model.User;

import java.util.Date;

public class UserDaoTest {

    public static void main(String[] args) {
        UserDao userDao = UserDaoJdbcImpl.getInstance().getUserDao();
        // System.out.println(userDao);

        User user = new User();
        user.setFirstName("dao firstName1");
        user.setLastName("dao LastName1");
        user.setBirthday(new Date());
        user.setSex("dao sex1");
        user.setEmail("dao@gmail.com");

        userDao.addUser(user);
    }
}

