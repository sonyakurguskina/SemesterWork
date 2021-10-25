package ru.itis.kurguskina.utils;

import java.sql.*;

import static java.lang.Class.forName;

public class JdbcUtils {
    private static String url = "jdbc:postgresql://localhost:5432/semestr_work_num_1";
    private static String user = "postgres";
    private static String password = "123456";

    private JdbcUtils(){
    }

    static {
        try {
            Class.forName("org.postgres.Driver");
        } catch (ClassNotFoundException e){
            throw new ExceptionInInitializerError(e);
        }
    }
public static Connection getConnection() throws SQLException{
    return DriverManager.getConnection(url, user, password);
}

    public static void free(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null)
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}
