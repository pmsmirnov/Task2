package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = """
                CREATE TABLE IF NOT EXISTS Users
                (
                        id       BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        name     VARCHAR(50) NOT NULL,
                        lastName VARCHAR(50) NOT NULL,
                        age      TINYINT     NOT NULL
                );""";
        session.createSQLQuery(sql).executeUpdate();
        transaction.commit();
        session.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {

        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction drUTab = session.beginTransaction();
            session.createSQLQuery("DROP Table users;").executeUpdate();
            drUTab.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction saveU = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.persist(user);
            saveU.commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction saveU = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            saveU.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {

        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction cleanUTab = session.beginTransaction();
            session.createSQLQuery("TRUNCATE Table users;").executeUpdate();
            cleanUTab.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
