package org.project.dbUtils;

import org.project.Config.Config;

import java.sql.*;
import java.util.Properties;

public class DBConnection {
    private static final Properties props = new Properties();

    private static Connection connection;

    public static Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(Config.getDbUrl(), Config.getDbUser(), Config.getDbPassword());

        } catch (ClassNotFoundException | SQLException e) {
            handleException(e);
        }
        return connection;
    }

    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            handleException(e);
        }
    }

    private static void handleException(Exception e) {
        e.printStackTrace();
    }
}