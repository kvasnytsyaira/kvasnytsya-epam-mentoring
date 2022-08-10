package mentoring.service;

import mentoring.model.Event;

import java.util.Date;
import java.util.List;

public interface EventService {

    Event getEventById(long eventId);

    List<Event> getEventsByTitle(String title, long pageSize, long pageNum);

    List<Event> getEventsForDay(Date date, long pageSize, long pageNum);

    Event createEvent(Event event);

    Event updateEvent(long eventId , Event event);
    Event updateEventTitle(int id, String title);

    boolean deleteEvent(long eventId);

}
