package com.mentoring.storingData;

import com.mentoring.model.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
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
        if (Objects.equals(ticket.getId(), null)) {
            ticket.setId(setTicketId());
        }
        return getTickets().put("ticket:" + ticket.getId(), ticket);
    }

    public Event save(Event event) {
        if (Objects.equals(event.getId(), null)) {
            event.setId(setEventId());
        }
        return getEvents().put("event:" + event.getId(), event);
    }

    public User save(User user) {
        if (Objects.equals(user.getId(), null)) {
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
        insertEvents();
        insertUsers();
        insertTickets();
    }

    private void insertEvents() {
        Event event1 = new ConcertEvent(setEventId(), "1 Concert", new Date(2022, Calendar.JANUARY, 1));
        events.put("event:" + event1.getId(), event1);
        Event event2 = new ConcertEvent(setEventId(), "2 Concert", new Date(2022, Calendar.JANUARY, 2));
        events.put("event:" + event2.getId(), event2);
        Event event3 = new ConcertEvent(setEventId(), "3 Concert", new Date(2022, Calendar.JANUARY, 3));
        events.put("event:" + event3.getId(), event3);
        Event event4 = new ConcertEvent(setEventId(), "4 Concert", new Date(2022, Calendar.JANUARY, 4));
        events.put("event:" + event4.getId(), event4);
        Event event5 = new ConcertEvent(setEventId(), "5 Concert", new Date(2022, Calendar.JANUARY, 5));
        events.put("event:" + event5.getId(), event5);
        Event event6 = new ConcertEvent(setEventId(), "6 Concert", new Date(2022, Calendar.JANUARY, 6));
        events.put("event:" + event6.getId(), event6);
    }

    private void insertUsers() {
        User user1 = new AdultUser(setUserId(), "Name 1", "email@1");
        users.put("user:" + user1.getId(), user1);
        User user2 = new AdultUser(setUserId(), "Name 2", "email@2");
        users.put("user:" + user2.getId(), user2);
        User user3 = new AdultUser(setUserId(), "Name 3", "email@3");
        users.put("user:" + user3.getId(), user3);
        User user4 = new AdultUser(setUserId(), "Name 4", "email@4");
        users.put("user:" + user4.getId(), user4);
    }

    private void insertTickets() {
        Ticket ticket1 = new OneDayTicket(setTicketId(), 1, 1, Ticket.Category.BAR, 1);
        tickets.put("ticket:" + ticket1.getId(), ticket1);
        Ticket ticket2 = new OneDayTicket(setTicketId(), 2, 1, Ticket.Category.BAR, 1);
        tickets.put("ticket:" + ticket2.getId(), ticket2);
        Ticket ticket3 = new OneDayTicket(setTicketId(), 3, 1, Ticket.Category.BAR, 1);
        tickets.put("ticket:" + ticket3.getId(), ticket3);
        Ticket ticket4 = new OneDayTicket(setTicketId(), 4, 1, Ticket.Category.BAR, 1);
        tickets.put("ticket:" + ticket4.getId(), ticket4);
        Ticket ticket5 = new OneDayTicket(setTicketId(), 6, 2, Ticket.Category.STANDARD, 31);
        tickets.put("ticket:" + ticket5.getId(), ticket5);
        Ticket ticket6 = new OneDayTicket(setTicketId(), 4, 2, Ticket.Category.BAR, 21);
        tickets.put("ticket:" + ticket6.getId(), ticket6);
        Ticket ticket7 = new OneDayTicket(setTicketId(), 5, 3, Ticket.Category.PREMIUM, 11);
        tickets.put("ticket:" + ticket7.getId(), ticket7);
        Ticket ticket8 = new OneDayTicket(setTicketId(), 6, 4, Ticket.Category.PREMIUM, 51);
        tickets.put("ticket:" + ticket8.getId(), ticket8);
    }

    private long setTicketId() {
        return getTickets().size();
    }

    private long setEventId() {
        return getEvents().size();
    }

    private long setUserId() {
        return getUsers().size();
    }

}
