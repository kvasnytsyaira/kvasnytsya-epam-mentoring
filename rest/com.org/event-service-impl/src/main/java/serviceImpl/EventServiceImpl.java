package serviceImpl;

import model.Event;
import org.springframework.stereotype.Service;
import service.EventService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class EventServiceImpl implements EventService {

    static Set<Event> eventsList = getEventsList();

    @Override
    public Event createEvent(Event event) {
        eventsList.add(event);
        return event;
    }

    @Override
    public Event updateEvent(Event newEvent) throws Exception {
        Event event = getEvent(newEvent.getId());
        event.setTitle(newEvent.getTitle());
        event.setPlace(newEvent.getPlace());
        event.setEventType(newEvent.getEventType());
        event.setDateTime(newEvent.getDateTime());
        event.setSpeaker(newEvent.getSpeaker());
        return event;
    }

    @Override
    public Event getEvent(String id) throws Exception {
        List<Event> collect = eventsList.stream().filter(event -> event.getId().equals(id)).collect(Collectors.toList());
        if (collect.isEmpty()) {
            throw new Exception(" We dont have event with id : " + id);
        } else return collect.get(0);
    }

    @Override
    public void deleteEvent(String id) throws Exception {
        eventsList.remove(getEvent(id));
    }

    @Override
    public Set<Event> getAllEvents() {
        return eventsList;
    }

    @Override
    public Set<Event> getAllEventsByTitle(String title) {
        return eventsList.stream()
                .filter(event -> event.getTitle().equals(title))
                .collect(Collectors.toSet());
    }

    private static Set<Event> getEventsList() {
        HashSet<Event> events = new HashSet<>();
        events.add(new Event("1", "Title 1", "Lviv", "Olha", "Meetup", "tomorrow"));
        events.add(new Event("2", "Title 2", "Kyiv", "Kate", "Concert", "tomorrow"));
        events.add(new Event("3", "Title 3", "Lviv", "Sava", "Meetup", "tomorrow"));
        events.add(new Event("4", "Title 4", "Lviv", "Kate", "Concert", "tomorrow"));
        return events;
    }
}
