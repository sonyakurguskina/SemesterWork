package ru.itis.kurguskina.model;

import java.util.Date;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String sex;
    private String email;
    private String login;
    private String password;

    public User(int id, String firstName,String lastName,Date birthday, String sex, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName=lastName;
        this.birthday=birthday;
        this.sex=sex;
        this.email=email;
    }

    public User() {
        this.firstName = firstName;
        this.lastName=lastName;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}