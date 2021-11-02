package ru.itis.kurguskina.dao;


import ru.itis.kurguskina.model.User;

import java.util.List;

public interface UserDao  {
    public void addUser(User user);
    public User findUser(String login,String password);
    public List<User> getAll();
    public void delete(User user);
}
