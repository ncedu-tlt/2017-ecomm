package ru.ncedu.ecomm.utils;

import ru.ncedu.ecomm.Configuration;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

    private static final String JDBC_DATABASE_URL_VARIABLE = "JDBC_DATABASE_URL";

    public static Connection getConnection() {
        String dbUrl = System.getenv(JDBC_DATABASE_URL_VARIABLE);
        if (dbUrl != null && !dbUrl.isEmpty()) {
            try {
                return DriverManager.getConnection(dbUrl);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        DataSource dataSource = getDataSource();
        if (dataSource == null) {
            throw new RuntimeException("Data source not found!");
        }

        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static DataSource getDataSource() {
        try {
            InitialContext cxt = new InitialContext();
            return (DataSource) cxt.lookup(Configuration.getProperty("db.datasource"));
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ignored) {
            }
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ignored) {
            }
        }
    }

}
