package org.leogenwp.repository.io;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.leogenwp.collectionUtils.SesFactory;
import org.leogenwp.model.Event;
import org.leogenwp.model.User;
import org.leogenwp.repository.EventRepository;

import java.util.List;

public class JavaIOEventRepository implements EventRepository {
    @Override
    public List<Event> getall() {
        List<Event> events;
        Session session = SesFactory.getSession();
        Query query = session.createQuery("SELECT e FROM Event e left join FETCH e.file f");
        events = query.getResultList();
        return events;
    }

    @Override
    public Event save(Event event) {
        try(Session session = SesFactory.getSession()
        ) {
            session.beginTransaction();
            session.save(event);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return event;
    }

    @Override
    public Event getById(Integer id) {
        Event event = new Event();
        try(Session session = SesFactory.getSession()) {
            event = session.get(Event.class,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }

    @Override
    public Event update(Event event) {
        try(Session session = SesFactory.getSession()) {
            session.beginTransaction();
            session.update(event);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return event;
    }

    @Override
    public void deleteById(Integer id) {
        try(Session session = SesFactory.getSession()) {
            session.beginTransaction();
            Event event = session.get(Event.class,id);
            session.remove(event);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }
}
