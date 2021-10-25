package ru.itis.kurguskina.dao;


import ru.itis.kurguskina.model.User;

public interface UserDao  {
    public void addUser(User user);
    public User getUser(int UserId);
    public User findUser(String login,String password);
    public void update(User user);
    public void delete(User user);
}
