package org.leogenwp.Utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.leogenwp.model.Event;
import org.leogenwp.model.File;
import org.leogenwp.model.User;

public class HibernateSession {
    private static Configuration conf = new Configuration().configure("hibernate.cfg.xml")
            .addAnnotatedClass(User.class)
            .addAnnotatedClass(File.class)
            .addAnnotatedClass(Event.class);
    private static SessionFactory sessionFactory = conf.buildSessionFactory();

    public static Session getSession() {
        return  sessionFactory.openSession();
    }
}
