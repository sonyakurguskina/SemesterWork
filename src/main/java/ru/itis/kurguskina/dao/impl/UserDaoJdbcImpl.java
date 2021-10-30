package ru.itis.kurguskina.dao.impl;

import ru.itis.kurguskina.dao.DaoException;
import ru.itis.kurguskina.dao.UserDao;;
import ru.itis.kurguskina.model.User;
import ru.itis.kurguskina.utils.JdbcUtils;

import java.sql.*;


public class UserDaoJdbcImpl implements UserDao {
    private static UserDaoJdbcImpl instance = new UserDaoJdbcImpl();

    private UserDaoJdbcImpl() {}
    private static UserDao userDao = null;

    public static UserDaoJdbcImpl getInstance() {
        return instance;
    }
    public UserDao getUserDao() {
        return userDao;
    }

    public void addUser(User user) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "insert into users(firstname,lastname, birthday, gender, username,password) values (?,?,?,?,?,?) ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getFirstname());
            ps.setString(2, user.getLastname());
            ps.setDate(3, new java.sql.Date(user.getBirthday().getTime()));
            ps.setString(4, user.getGender());
            ps.setString(5, user.getUsername());
            ps.setString(5, user.getPassword());

            ps.executeUpdate();
//            connection.commit(); //посмотреть нужно или нет
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtils.free(rs, ps, connection);
        }
    }

    public void delete(User user) {
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = JdbcUtils.getConnection();
            st = connection.createStatement();
            String sql = "delete from user where id=" + user.getId();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtils.free(rs, st, connection);
        }

    }

    public User findUser(String login, String password) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select id, firstname, lastname, birthday, gender, username from user where firstname=? && lastname=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, login);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = mappingUser(rs);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtils.free(rs, ps, connection);
        }
        return user;
    }

    public User getUser(int userId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select id, firstName, lastName, birthday, gender, username  from user where id=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = mappingUser(rs);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtils.free(rs, ps, connection);
        }
        return user;
    }

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

    public void update(User user) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "update user set firstName=?, lastName=?,  birthday=?, gender=?, email=? where id=? ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getFirstname());
            ps.setString(2, user.getLastname());
            ps.setDate(3, new java.sql.Date(user.getBirthday().getTime()));
            ps.setString(4, user.getGender());
            ps.setString(5, user.getUsername());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtils.free(rs, ps, connection);
        }
    }
}
