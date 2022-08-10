package mentoring.service;

import mentoring.exceptions.RecordNotFoundException;
import mentoring.model.Event;
import mentoring.storingData.Storage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EventServiceImpl implements EventService {
    @Autowired
    private Storage storage;


    @Override
    public Event getEventById(long eventId) {
        Event event = storage.getEvents().get("event:" + eventId);
        if (event != null) {
            return event;
        } else throw new RecordNotFoundException("Event with such id does not exist!");
    }

    @Override
    public List<Event> getEventsByTitle(String title, long pageSize, long pageNum) {
        return storage.getEvents()
                .values()
                .stream()
                .filter(event -> Objects.equals(event.getTitle().toLowerCase(), title.toLowerCase()))
                .skip((pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> getEventsForDay(Date date, long pageSize, long pageNum) {
        return storage.getEvents()
                .values()
                .stream()
                .filter(event -> event.getDate() == date)
                .skip((pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public Event createEvent(Event event) {
        return storage.save(event);
    }

    @Override
    public Event updateEvent(long id, Event eventNew) {
        Event event = getEventById(id);
        event.setTitle(eventNew.getTitle());
        event.setDate(eventNew.getDate());
        return storage.save(event);
    }

    @Override
    public Event updateEventTitle(int id, String title) {
        Event event = getEventById(id);
        event.setTitle(title);
        return storage.save(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        getEventById(eventId);
        return storage.removeEvent(eventId);
    }
}
