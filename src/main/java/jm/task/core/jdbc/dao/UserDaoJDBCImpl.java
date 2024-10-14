package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    private static final UserDaoJDBCImpl INSTANCE = new UserDaoJDBCImpl();
    public static UserDaoJDBCImpl getInstance() {
        return INSTANCE;
    }

    public void createUsersTable() {
        try (Connection connection = Util.getMySQLConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS Users
                    (
                        Id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(20),
                        lastName VARCHAR(20),
                        age TINYINT
                    );""");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getMySQLConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("Drop TABLE Users;");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getMySQLConnection();
             Statement statement = connection.createStatement()) {
            String sql = "INSERT Users(name, lastName, age) VALUES ('" + name + "', '" + lastName + "' ,'" + age + "');";
            statement.executeUpdate(sql);
            System.out.println("User с именем Ч " + name + " добавлен в базу данных");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getMySQLConnection();
             Statement statement = connection.createStatement()) {
            String sql = "DELETE FROM Users WHERE Id = " + id + ";";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try (Connection connection = Util.getMySQLConnection();
             Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM Users";
            ResultSet resultSet =  statement.executeQuery(sql);
            List<User> list = new ArrayList<>();
            while(resultSet.next()){
                //int id = resultSet.getInt("Id");
                String name = resultSet.getString("Name");
                String lastName = resultSet.getString("LastName");
                byte age = resultSet.getByte("Age");
                list.add(new User(name, lastName, age));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getMySQLConnection();
             Statement statement = connection.createStatement()) {
            String sql = "TRUNCATE TABLE Users;";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
