package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final UserDao uDHI = new UserDaoHibernateImpl();

    public void createUsersTable() {
        uDHI.createUsersTable();
    }

    public void dropUsersTable() {
        uDHI.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        uDHI.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        uDHI.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return uDHI.getAllUsers();
    }

    public void cleanUsersTable() {
        uDHI.cleanUsersTable();
    }
}
