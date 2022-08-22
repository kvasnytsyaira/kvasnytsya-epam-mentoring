package com.mentoring.service;

import com.mentoring.model.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventService {
    Event getEventById(long eventId);

    List<Event> getEventsByTitle(String title, long pageSize, long pageNum);

    List<Event> getEventsForDay(LocalDate date, long pageSize, long pageNum);

    Event createEvent(Event event);

    Event updateEvent(long eventId , Event event);
    Event updateEventTitle(long id, String title);

    void deleteEvent(long eventId);

    List<Event> getAllEvents();
}
