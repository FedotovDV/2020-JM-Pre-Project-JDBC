package util;


import java.util.Properties;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.User;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class Util {

    private Connection connection;
    private static SessionFactory sessionFactory;

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String LOGIN = "jmuser@loclhost";
    private static final String PASSWORD = "jmuser";
    private static final String DATABASE_NAME = "jm_core_task";
    private static final String DATABASE_INFO = "serverTimezone=Europe/Moscow&useSSL=false";
    private static final String DIALECT = "org.hibernate.dialect.MySQLDialect";
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().addAnnotatedClass(User.class)
                        .setProperty("hibernate.dialect", DIALECT)
                        .setProperty("hibernate.connection.driver_class", DRIVER_CLASS)
                        .setProperty("hibernate.connection.url", URL + DATABASE_NAME + "?" + DATABASE_INFO)
                        .setProperty("hibernate.connection.username", LOGIN)
                        .setProperty("hibernate.connection.password", PASSWORD)
                        .setProperty("hibernate.show_sql", "true")
                        .setProperty("hibernate.hbm2ddl.auto", "update");

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }


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
