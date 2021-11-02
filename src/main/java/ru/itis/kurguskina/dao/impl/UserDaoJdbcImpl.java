package ru.itis.kurguskina.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itis.kurguskina.dao.UserDao;
import ru.itis.kurguskina.helper.ConnectionHelper;
import ru.itis.kurguskina.model.User;

import java.sql.*;
import java.sql.Date;
import java.util.*;


public class UserDaoJdbcImpl implements UserDao {
    private final Connection connection = ConnectionHelper.getConnection();
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoJdbcImpl.class);


    private User mappingUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setFirstname(rs.getString("firstName"));
        user.setLastname(rs.getString("lastName"));
        user.setBirthday(rs.getString("birthday"));
        user.setUsername(rs.getString("username"));
        return user;
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO users (id,firstname,lastname,birthday,username,password) VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getFirstname());
            preparedStatement.setString(3, user.getLastname());
            preparedStatement.setString(4,  user.getBirthday());
            preparedStatement.setString(5, user.getUsername());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            LOGGER.warn("Failed to save new user", throwables);
        }
    }


    @Override
    public User findUser(String username, String password) {
        try {
            String sql = "SELECT * FROM users WHERE username = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            User user = null;

            while (resultSet.next()) {
                if (resultSet.getString("username").equals(username)) {
                    user = new User(resultSet.getInt("id"),
                            resultSet.getString("firstname"),
                            resultSet.getString("lastname"),
                            resultSet.getString("birthday"),
                            resultSet.getString("username"),
                            resultSet.getString("password"));
                }
            }


            return user;
        } catch (SQLException throwables) {
            LOGGER.warn("Failed to find user in database", throwables);
            return null;
        }
    }

    public String findUsersByName(String username) {
        try {
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            String string = "";

            while (resultSet.next()) {
                string += resultSet.getString("username");
            }
            return string;
//            User user = null;
//
////            String string = resultSet.getArray(1).toString();
//
//            Array a = resultSet.getArray("username");
//            String[] nullable = (String[])a.getArray();
//            String string = nullable.toString();
//
//            return string;
        } catch (SQLException throwables) {
            LOGGER.warn("Failed to find user in database", throwables);
        }
        return null;
    }

    public List<User> getAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(sql);

            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User(resultSet.getInt("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("birthday"),
                        resultSet.getString("username"),
                        resultSet.getString("password"));
                users.add(user);
            }

            return users;
        } catch (SQLException throwables) {
            LOGGER.warn("Failed execute getAll query.", throwables);
            return new ArrayList<>();
        }
    }

    @Override
    public void delete(User user) {
        String sql = "DELETE FROM users WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            LOGGER.warn("Failed to delete user", throwables);
        }
    }
}
