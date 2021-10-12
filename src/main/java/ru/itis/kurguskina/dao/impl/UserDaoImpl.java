package ru.itis.kurguskina.dao.impl;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import ru.itis.kurguskina.dao.Dao;
import ru.itis.kurguskina.helper.PostgresConnectionHelper;
import ru.itis.kurguskina.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements Dao<User> {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    private final Connection connection = PostgresConnectionHelper.getConnection();

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(sql);

            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
                users.add(user);
            }

            return users;
        } catch (SQLException throwables) {
            LOGGER.warn("Failed execute getAll query.", throwables);
            return new ArrayList<>();
        }
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (name,login, password) VALUES (?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            LOGGER.warn("Failed to save new user.", throwables);
        }
    }
}
