package ru.itis.kurguskina.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class UserDto {
    private int id;
    private String firstname;
    private String lastname;
    private Date birthday;
    private String username;
    private String password;

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDto(int id, String firstname, String lastname, Date birthday, String username) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday=birthday;
        this.username = username;
    }
    public UserDto(int id, String firstname, String lastname, Date birthday, String username,String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday=birthday;
        this.username = username;
        this.password = password;
    }

    public UserDto() {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public UserDto(int id){
        this.id = id;
    }


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
                ", email" + username +
                ", login" + username +
                ", password" + password +
                "}";
    }
}