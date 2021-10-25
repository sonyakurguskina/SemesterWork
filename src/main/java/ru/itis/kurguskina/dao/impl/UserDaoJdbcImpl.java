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
            String sql = "insert into user(firstName,lastName, birthday, sex, email) values (?,?,?,?,?) ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setDate(3, new java.sql.Date(user.getBirthday().getTime()));
            ps.setString(4, user.getSex());
            ps.setString(5, user.getEmail());
            ps.executeUpdate();
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
            String sql = "select id, firstName, lastName, birthday, sex, email from user where firstName=? && lastName=?";
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
            String sql = "select id, firstName, lastName, birthday, sex, email  from user where id=?";
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
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setBirthday(rs.getDate("birthday"));
        user.setSex(rs.getString("sex"));
        user.setEmail(rs.getString("email"));
        return user;
    }

    public void update(User user) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "update user set firstName=?, lastName=?,  birthday=?, sex=?, email=? where id=? ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setDate(3, new java.sql.Date(user.getBirthday().getTime()));
            ps.setString(4, user.getSex());
            ps.setString(5, user.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtils.free(rs, ps, connection);
        }
    }
}
