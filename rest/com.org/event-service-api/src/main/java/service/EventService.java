package service;

import model.Event;

import java.util.Set;

public interface EventService {
    Event createEvent(Event event);

    Event updateEvent(Event event) throws Exception;

    Event getEvent(String id) throws Exception;

    void deleteEvent(String id) throws Exception;

    Set<Event> getAllEvents();

    Set<Event> getAllEventsByTitle(String title);
}
