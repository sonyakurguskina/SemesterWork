package ru.itis.kurguskina.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itis.kurguskina.dao.DaoException;
import ru.itis.kurguskina.dao.UserDao;
import ru.itis.kurguskina.helper.ConnectionHelper;
import ru.itis.kurguskina.model.User;

import java.sql.*;


public class UserDaoJdbcImpl implements UserDao {
    private final Connection connection = ConnectionHelper.getConnection();
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoJdbcImpl.class);


    private User mappingUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setFirstname(rs.getString("firstName"));
        user.setLastname(rs.getString("lastName"));
        user.setBirthday(rs.getDate("birthday"));
        user.setGender(rs.getString("gender"));
        user.setUsername(rs.getString("username"));
        return user;
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);//позволяет предотвращать sql инъекции
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            LOGGER.warn("Failed to save new user", throwables);
        }
    }

    @Override
    public User getUser(int UserId) {
        return null;
    }

    @Override
    public User findUser(String login, String password) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
