package mentoring.service;

import mentoring.model.Event;
import mentoring.storingData.Storage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EventServiceImpl implements EventService {
    @Autowired
    private Storage storage;


    @Override
    public Event getEventById(long eventId) {
        Event event = storage.getEvents().get("event:" + eventId);
        return event;
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return storage.getEvents()
                .values()
                .stream()
                .filter(event -> event.getTitle().equals(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> getEventsForDay(Date date, int pageSize, int pageNum) {
        return storage.getEvents()
                .values()
                .stream()
                .filter(event -> event.getDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public Event createEvent(Event event) {
        return storage.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return storage.save(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return storage.removeEvent(eventId);
    }
}
