package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection = getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE users (id INT AUTO_INCREMENT, name VARCHAR(40),\n" +
                    " lastName VARCHAR(40), age INT(3),PRIMARY KEY (id))");
        } catch (SQLException e) {
            System.out.println("таблица уже создана");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE users ");
        } catch (SQLException e) {
            System.out.println("таблица была удалена ранее");
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, lastName, age) values (?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User c именем  " + name + "  добавлен в базу данных");
        } catch (Exception e) {
            System.out.println("пользователь не добавлен");
        }

    }

    public void removeUserById(long id) {

        String str = "DELETE FROM users WHERE Id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(str)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь удалён");
        } catch (SQLException e) {
            e.printStackTrace();

            System.out.println("пользователь не удалён");
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String str = "SELECT * FROM users";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(str);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("вывод все беда");
        }
        return userList;
    }

    public void cleanUsersTable() {

        String str = "DELETE FROM users WHERE Id > 0";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(str);
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            System.out.println("очищение таблицы беда");
        }

    }


}
