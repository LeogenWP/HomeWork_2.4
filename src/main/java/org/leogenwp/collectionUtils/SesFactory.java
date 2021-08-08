package org.leogenwp.collectionUtils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.leogenwp.model.Event;
import org.leogenwp.model.File;
import org.leogenwp.model.User;

public class SesFactory {
    private static Configuration conf = new Configuration().configure("hibernate.cfg.xml")
            .addAnnotatedClass(User.class)
            .addAnnotatedClass(File.class)
            .addAnnotatedClass(Event.class);
    private static SessionFactory sessionFactory = conf.buildSessionFactory();
    private static Session session;

    public static Session getSession() {
        return session = sessionFactory.openSession();
    }
}
