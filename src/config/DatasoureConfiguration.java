package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class DatasoureConfiguration {
    private final String USER_NAME = "postgres";
    private final String PASS_WORD = "example";
    private final String DB_URL = "jdbc:postgresql://localhost:5433/ceb";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER_NAME, PASS_WORD);
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        return getConnection().createStatement().executeQuery(sql);
    }
}
