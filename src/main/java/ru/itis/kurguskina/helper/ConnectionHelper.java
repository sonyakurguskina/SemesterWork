package ru.itis.kurguskina.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    public static final Logger LOGGER = LoggerFactory.getLogger(ConnectionHelper.class);

    private static Connection connection;
    private static String url = "jdbc:postgresql://localhost:5432/semestr_work_num_1";
    private static String user = "postgres";
    private static String password = "123456";

    public static Connection getConnection(){
        if(connection == null){
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException | SQLException e) {
                LOGGER.error("Failed connect to db", e);
            }
        }
        return connection;
    }

}
