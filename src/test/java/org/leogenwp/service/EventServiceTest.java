package org.leogenwp.service;

import org.junit.jupiter.api.Test;
import org.leogenwp.model.Event;
import org.leogenwp.model.File;
import org.leogenwp.repository.EventRepository;
import org.leogenwp.repository.FileRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventServiceTest {

    @Test
    void save() {
        Event event = new Event();
        EventRepository eventRepository = mock(EventRepository.class);
        EventService eventService_underTest = new EventService(eventRepository);
        when(eventService_underTest.save(event)).thenReturn(event);
        assertEquals(event,eventService_underTest.save(event));
    }

    @Test
    void getById() {
        Event event = new Event();
        EventRepository eventRepository = mock(EventRepository.class);
        EventService eventService_underTest = new EventService(eventRepository);
        when(eventService_underTest.getById(1)).thenReturn(event);
        assertEquals(event,eventService_underTest.getById(1));
    }

    @Test
    void getAll() {
        List<Event> events = getEventList();
        EventRepository eventRepository = mock(EventRepository.class);
        EventService eventService_underTest = new EventService(eventRepository);
        when(eventService_underTest.getAll()).thenReturn(events);
        assertEquals(events,eventService_underTest.getAll());
    }

    @Test
    void update() {
        Event event = new Event();
        EventRepository eventRepository = mock(EventRepository.class);
        EventService eventService_underTest = new EventService(eventRepository);
        when(eventService_underTest.update(event)).thenReturn(event);
        assertEquals(event,eventService_underTest.update(event));
    }

    @Test
    void deleteById() {
        EventService eventService_underTest = mock(EventService.class);
        eventService_underTest.deleteById(5);
        verify(eventService_underTest).deleteById(5);
    }

    private List<Event> getEventList() {
        List<Event> events = Arrays.asList(
                new Event(),
                new Event()
        );
        return events;
    }
}