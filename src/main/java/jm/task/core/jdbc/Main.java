package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


public class Main {
    private static final UserService user = new UserServiceImpl();

    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        Util util = new Util();
        util.getSessionFactory();
        user.createUsersTable();
        user.saveUser("dsf", "sdf", (byte) 98);
        user.saveUser("dsf", "sdf", (byte) 98);
        user.saveUser("dsf", "sdf", (byte) 98);
        user.saveUser("dsf", "sdf", (byte) 98);
        user.removeUserById(1L);
        user.getAllUsers();
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}

