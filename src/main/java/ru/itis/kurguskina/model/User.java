package ru.itis.kurguskina.model;

import javax.persistence.*;
import java.util.Date;

public class User {
    private int id;
    private String firstname;
    private String lastname;
    private Date birthday;
    private String gender;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int id, String firstname, String lastname, Date birthday, String gender, String username) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday=birthday;
        this.gender = gender;
        this.username = username;
    }

    public User() {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstName) {
        this.firstname = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String sex) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    public void setUsername(String login) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", firstName=" + firstname +
                ", lastName=" + lastname +
                ", birthday=" + birthday +
                ", sex=" + gender +
                ", email" + username +
                ", login" + username +
                ", password" + password +
                "}";
    }
}

