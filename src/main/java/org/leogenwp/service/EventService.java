package org.leogenwp.service;

import org.leogenwp.model.Event;
import org.leogenwp.model.User;
import org.leogenwp.repository.EventRepository;
import org.leogenwp.repository.UserRepository;
import org.leogenwp.repository.io.JavaIOEventRepository;
import org.leogenwp.repository.io.JavaIOUserRepository;

import java.util.List;

public class EventService {
    EventRepository eventRepository = new JavaIOEventRepository();
    public Event save(Event e) {
        return eventRepository.save(e);
    }
    public Event getById(Integer id) {
        Event e = eventRepository.getById(id);
        return e;
    }

    public List<Event> getAll() {
        return eventRepository.getall();
    }


    public Event update(Event e) {
        e = eventRepository.update(e);
        return e;
    }

    public void deleteById(Integer id) {
        eventRepository.deleteById(id);
    }

}
