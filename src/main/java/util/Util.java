package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Util {

    private Connection connection;

    public static Connection getConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");
        String dbName = resource.getString("db.name");
        String dbInfo = resource.getString("db.info");
        return DriverManager.getConnection(url + dbName + "?" + dbInfo, user, pass);
    }


}
