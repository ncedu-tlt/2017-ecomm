package ru.ncedu.ecomm.utils;

import ru.ncedu.ecomm.Configuration;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

    public static DataSource getDataSource() {
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
