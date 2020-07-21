import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
// Создание таблицы User(ов)
    // Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
    // Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
    // Очистка таблицы User(ов)
    // Удаление таблицы

    private static final String DROP_TABLE_QUERY =
            "DROP TABLE IF EXISTS user;";
    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE user ("

                    + "`id` bigint NOT NULL AUTO_INCREMENT,"
                    + "`name` varchar(45) DEFAULT NULL,"
                    + "`last_name` varchar(45) DEFAULT NULL,"
                    + "`age` tinyint DEFAULT NULL,"
                    + " PRIMARY KEY(`id`)"
                    + ") AUTO_INCREMENT = 1 "
                    + "DEFAULT CHARSET = utf8;";
    private static final String CLEAR_TABLE_QUERY =
            "DELETE FROM user;";

    private static final String INSERT_USER_IN_TABLE =
            "INSERT INTO user (name,last_name, age) VALUES(?, ?, ?);";

    private static final String DELETE_USER_FROM_TABLE =
            "DELETE FROM user WHERE id = ?;";

    private static final String SELECT_ALL_USERS_FROM_TABLE =
            "SELECT * FROM user;";

    private static List<User> users = new ArrayList<>();


    public static void main(String[] args) {

        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();

        User user = new User("Ivan", "Ivanov", (byte) 26);
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();

        sessionFactory = Util.getSessionFactory();
        session = sessionFactory.openSession();

        user = new User("Petr", "Petrov", (byte) 27);
        transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();

//        try (Connection connection = Util.getConnection();
//             Statement statement = connection.createStatement();) {
//            statement.addBatch(DROP_TABLE_QUERY);
//            statement.addBatch(CREATE_TABLE_QUERY);
//            statement.executeBatch();
//            try (PreparedStatement preparedStatementInsert = connection.prepareStatement(INSERT_USER_IN_TABLE)) {
//                preparedStatementInsert.setString(1, "Petr");
//                preparedStatementInsert.setString(2, "Petrov");
//                preparedStatementInsert.setByte(3, (byte) 32);
//                preparedStatementInsert.executeUpdate();
//                preparedStatementInsert.setString(1, "Ivan");
//                preparedStatementInsert.setString(2, "Ivanov");
//                preparedStatementInsert.setByte(3, (byte) 25);
//                preparedStatementInsert.executeUpdate();
//            }
//
//            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS_FROM_TABLE)) {
//                while (resultSet.next()) {
//                    users.add(new User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("last_name"), resultSet.getByte("age")));
//                }
//                System.out.println(users);
//            }
//            try (PreparedStatement preparedStatementDelete = connection.prepareStatement(DELETE_USER_FROM_TABLE)) {
//                preparedStatementDelete.setLong(1, 1);
//                preparedStatementDelete.executeUpdate();
//            }
////            statement.executeUpdate("DELETE FROM user WHERE id = 1;");
//            statement.addBatch(CLEAR_TABLE_QUERY);
//            statement.addBatch(DROP_TABLE_QUERY);
//            statement.executeBatch();
//            System.out.println(connection.isValid(0));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }
}
