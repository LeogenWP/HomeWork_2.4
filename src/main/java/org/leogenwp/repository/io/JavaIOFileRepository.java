package org.leogenwp.repository.io;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.leogenwp.Utils.HibernateSession;
import org.leogenwp.model.File;
import org.leogenwp.repository.FileRepository;

import java.util.List;

public class JavaIOFileRepository implements FileRepository {
    @Override
    public List<File> getall() {
        List<File> files;
        Session session = HibernateSession.getSession();
        Query query = session.createQuery("SELECT f FROM File f ");
        files = query.getResultList();
        return files;
    }

    @Override
    public File save(File file) {
        try(Session session = HibernateSession.getSession()
        ) {
            session.beginTransaction();
            session.save(file);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public File getById(Integer id) {
        File file = new File();
        try(Session session = HibernateSession.getSession()) {
            file = session.get(File.class,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public File update(File file) {
        try(Session session = HibernateSession.getSession()) {
            session.beginTransaction();
            session.update(file);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public void deleteById(Integer id) {
        try(Session session = HibernateSession.getSession()) {
            session.beginTransaction();
            File file = session.get(File.class,id);
            session.remove(file);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
