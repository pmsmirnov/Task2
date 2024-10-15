package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final UserDaoHibernateImpl uDHI = new UserDaoHibernateImpl();
    public void createUsersTable() {
        //UserDaoJDBCImpl.getInstance().createUsersTable();
        uDHI.createUsersTable();
    }

    public void dropUsersTable() {
        //UserDaoJDBCImpl.getInstance().dropUsersTable();
        uDHI.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        //UserDaoJDBCImpl.getInstance().saveUser(name, lastName, age);
        uDHI.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        //UserDaoJDBCImpl.getInstance().removeUserById(id);
        uDHI.removeUserById(id);
    }

    public List<User> getAllUsers() {
        //return UserDaoJDBCImpl.getInstance().getAllUsers();
        return uDHI.getAllUsers();
    }

    public void cleanUsersTable() {
        //UserDaoJDBCImpl.getInstance().cleanUsersTable();
        uDHI.cleanUsersTable();
    }
}
