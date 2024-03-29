package mentoring.storingData;

import mentoring.model.Event;
import mentoring.model.Ticket;
import mentoring.model.User;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Storage {

    Map<String, Event> events;
    Map<String, User> users;
    Map<String, Ticket> tickets;

    public Map<String, Event> getEvents() {
        return events;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public Map<String, Ticket> getTickets() {
        return tickets;
    }


    public Ticket save(Ticket ticket) {
        if (ticket.getId() == 0) {
            ticket.setId(setTicketId());
        }
        return getTickets().put("ticket:" + ticket.getId(), ticket);
    }

    public Event save(Event event) {
        if (event.getId() == 0) {
            event.setId(setEventId());
        }
        return getEvents().put("event:" + event.getId(), event);
    }

    public User save(User user) {
        if (user.getId() == 0) {
            user.setId(setUserId());
        }
        return getUsers().put("user:" + user.getId(), user);
    }

    public boolean removeTicket(long id) {
        getTickets().remove("ticket:" + id);
        return true;
    }

    public boolean removeEvent(long id) {
        getEvents().remove("event:" + id);
        return true;
    }

    public boolean removeUser(long id) {
        getUsers().remove("user:" + id);
        return true;
    }


    public Storage() {
        events = new LinkedHashMap<>();
        users = new LinkedHashMap<>();
        tickets = new LinkedHashMap<>();
    }

    private long setTicketId() {
        return getTickets().size() + 1;
    }

    private long setEventId() {
        return getEvents().size() + 1;
    }

    private long setUserId() {
        return getUsers().size() + 1;
    }

}
