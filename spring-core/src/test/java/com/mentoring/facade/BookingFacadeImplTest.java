package com.mentoring.facade;

import com.mentoring.model.*;
import com.mentoring.service.EventServiceImpl;
import com.mentoring.service.TicketServiceImpl;
import com.mentoring.service.UserServiceImpl;
import com.mentoring.storingData.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

class BookingFacadeImplTest {
    Storage storageMock;
    BookingFacadeImpl bookingFacade;
    EventServiceImpl eventService;
    UserServiceImpl userService;
    TicketServiceImpl ticketService;
    static Map<String, Event> testEvents;
    static Map<String, User> testUsers;
    static Map<String, Ticket> testTickets;
    Event event1, event2, event3, event4;
    User user1, user2;
    Ticket ticket1, ticket2, ticket3;


    @BeforeEach
    void setUp() {
        testEvents = new LinkedHashMap<>();
        testUsers = new LinkedHashMap<>();
        testTickets = new LinkedHashMap<>();
        insertEvents();
        insertUsers();
        insertTickets();
        storageMock = mock(Storage.class);
        eventService = mock(EventServiceImpl.class);
        ticketService = mock(TicketServiceImpl.class);
        userService = mock(UserServiceImpl.class);

        bookingFacade = new BookingFacadeImpl(eventService, userService, ticketService);
        ReflectionTestUtils.setField(eventService, "storage", new Storage());
        ReflectionTestUtils.setField(ticketService, "storage", new Storage());
        ReflectionTestUtils.setField(userService, "storage", new Storage());
    }

    @Test
    void getEventById() {
        bookingFacade.getEventById(1);
        verify(eventService, times(1)).getEventById(1);
    }

    @Test
    void getEventsByTitle() {
        bookingFacade.getEventsByTitle("1 Concert", 1, 1);
        verify(eventService, times(1)).getEventsByTitle("1 Concert", 1, 1);
    }

    @Test
    void getEventsForDay() {
        bookingFacade.getEventsForDay(new Date(2022, Calendar.JANUARY, 1), 1, 1);
        verify(eventService, times(1)).getEventsForDay(new Date(2022, Calendar.JANUARY, 1), 1, 1);
    }

    @Test
    void createEvent() {
        bookingFacade.createEvent(event1);
        verify(eventService, times(1)).createEvent(event1);
    }

    @Test
    void updateEvent() {
        bookingFacade.updateEvent(event2);
        verify(eventService, times(1)).updateEvent(event2);
    }

    @Test
    void deleteEvent() {
        bookingFacade.deleteEvent(1);
        verify(eventService, times(1)).deleteEvent(1);
    }

    @Test
    void getUserById() {
        bookingFacade.getUserById(1);
        verify(userService, times(1)).getUserById(1);
    }

    @Test
    void getUserByEmail() {
        bookingFacade.getUserByEmail("email@1");
        verify(userService, times(1)).getUserByEmail("email@1");
    }

    @Test
    void getUsersByName() {
        bookingFacade.getUsersByName("Name 1", 1, 1);
        verify(userService, times(1)).getUsersByName("Name 1", 1, 1);
    }

    @Test
    void createUser() {
        bookingFacade.createUser(user1);
        verify(userService, times(1)).createUser(user1);
    }

    @Test
    void updateUser() {
        bookingFacade.updateUser(user2);
        verify(userService, times(1)).updateUser(user2);
    }

    @Test
    void deleteUser() {
        bookingFacade.deleteUser(1);
        verify(userService, times(1)).deleteUser(1);
    }

    @Test
    void bookTicket() {
        bookingFacade.bookTicket(ticket1);
        verify(ticketService, times(1)).bookTicket(ticket1);
    }

    @Test
    void getBookedTickets() {
        bookingFacade.getBookedTickets(event1, 1, 1);
        verify(ticketService, times(1)).getBookedTickets(event1, 1, 1);
    }

    @Test
    void testGetBookedTickets() {
        bookingFacade.getBookedTickets(user1, 1, 1);
        verify(ticketService, times(1)).getBookedTickets(user1, 1, 1);
    }

    @Test
    void cancelTicket() {
        bookingFacade.cancelTicket(1);
        verify(ticketService, times(1)).cancelTicket(1);
    }


    private void insertEvents() {
        event1 = new ConcertEvent(1, "1 Concert", new Date(2022, Calendar.JANUARY, 1));
        testEvents.put("event:" + event1.getId(), event1);
        event2 = new ConcertEvent(2, "2 Concert", new Date(2022, Calendar.JANUARY, 2));
        testEvents.put("event:" + event2.getId(), event2);
        event3 = new ConcertEvent(3, "3 Concert", new Date(2022, Calendar.JANUARY, 3));
        testEvents.put("event:" + event3.getId(), event3);
        event4 = new ConcertEvent(4, "4 Concert", new Date(2022, Calendar.JANUARY, 4));
        testEvents.put("event:" + event4.getId(), event4);
    }

    private void insertUsers() {
        user1 = new AdultUser(1, "Name 1", "email@1");
        testUsers.put("user:" + user1.getId(), user1);
        user2 = new AdultUser(2, "Name 2", "email@2");
        testUsers.put("user:" + user2.getId(), user2);
    }

    private void insertTickets() {
        ticket1 = new OneDayTicket(1, 1, 1, Ticket.Category.BAR, 1);
        testTickets.put("ticket:" + ticket1.getId(), ticket1);
        ticket2 = new OneDayTicket(2, 2, 1, Ticket.Category.BAR, 1);
        testTickets.put("ticket:" + ticket2.getId(), ticket2);
        ticket3 = new OneDayTicket(3, 3, 2, Ticket.Category.BAR, 1);
        testTickets.put("ticket:" + ticket3.getId(), ticket3);
    }

}