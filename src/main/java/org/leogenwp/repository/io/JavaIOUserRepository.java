package org.leogenwp.repository.io;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.leogenwp.Utils.HibernateSession;
import org.leogenwp.model.Event;
import org.leogenwp.model.File;
import org.leogenwp.model.User;
import org.leogenwp.repository.UserRepository;

import java.util.List;

public class JavaIOUserRepository implements UserRepository {
    private static Configuration conf = new Configuration().configure("hibernate.cfg.xml")
            .addAnnotatedClass(User.class)
            .addAnnotatedClass(File.class)
            .addAnnotatedClass(Event.class);
    private static SessionFactory sessionFactory = conf.buildSessionFactory();
    private static Session session;

    @Override
    public List<User> getall() {
        List<User> users;
        Session session = HibernateSession.getSession();
        Query query = session.createQuery("SELECT u FROM User u left join FETCH u.events t");
        users = query.getResultList();
        return users;
    }

    @Override
    public User save(User user) {
        try(Session session = HibernateSession.getSession()
        ) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getById(Integer id) {
        User user = new User();
        try(Session session = HibernateSession.getSession()) {
            //user = (User) session.get(User.class,id);
            Query query = session.createQuery("select u  FROM User u left join FETCH u.events WHERE u.id = ?1");
            query.setParameter(1,id);
            if(!query.getResultList().isEmpty()){
                user =(User) query.getResultList().get(0);
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    @Override
    public User getByLogin(String login) {
        User user = new User();
        try(Session session = HibernateSession.getSession()) {
            Query query = session.createQuery("from User where login = :paramLogin");
            query.setParameter("paramLogin", login);
            List<User> list = query.list();
            if (list.size() != 0) {
                user = list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User update(User user) {
        try(Session session = HibernateSession.getSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void deleteById(Integer id) {
        try(Session session = HibernateSession.getSession()) {
            session.beginTransaction();
            User user = (User) session.get(User.class,id);
            session.remove(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }
}
