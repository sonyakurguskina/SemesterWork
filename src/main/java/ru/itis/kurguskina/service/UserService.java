package ru.itis.kurguskina.service;

import ru.itis.kurguskina.dao.UserDao;
import ru.itis.kurguskina.model.User;

public class UserService {
    private UserDao userDao;

    public void regist(User user){
        this.userDao.addUser(user);
    }
}
