package com.mentoring.service;

import com.mentoring.facade.BookingFacadeImpl;
import com.mentoring.model.*;
import com.mentoring.storingData.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {

    TicketServiceImpl ticketService = new TicketServiceImpl();
    Storage storageMock = new Storage();
    LinkedHashMap<String, Ticket> testTickets = new LinkedHashMap<>();
    LinkedHashMap<String, Ticket> testTicketsEmpty = new LinkedHashMap<>();
    Ticket ticket1, ticket2, ticket3;
    User user1;
    Event event1;


    @BeforeEach
    void setUp() {
        user1 = new AdultUser(1, "Name 1", "email@1");
        event1 = new ConcertEvent(1, "1 Concert", new Date(2022, Calendar.JANUARY, 1));
        insertTickets();

        ReflectionTestUtils.setField(ticketService, "storage", storageMock);
        ReflectionTestUtils.setField(storageMock, "tickets", testTickets);
    }

    @Test
    void bookTicket() {
        Ticket actual = ticketService.bookTicket(ticket1);
        Assertions.assertEquals(ticket1, actual);
    }

    @Test
    void getBookedTickets() {
        List<Ticket> actual = ticketService.getBookedTickets(user1, 1, 1);
        Assertions.assertEquals(Collections.singletonList(ticket1), actual);
    }

    @Test
    void testGetBookedTickets() {
        List<Ticket> actual = ticketService.getBookedTickets(event1, 1, 1);
        Assertions.assertEquals(Collections.singletonList(ticket1), actual);
    }

    @Test
    void cancelTicket() {
        boolean actual = ticketService.cancelTicket(1);
        Assertions.assertTrue(actual);
    }

    private void insertTickets() {
        ticket1 = new OneDayTicket(1, 1, 1, Ticket.Category.BAR, 1);
        testTickets.put("ticket:" + ticket1.getId(), ticket1);
        ticket2 = new OneDayTicket(2, 2, 2, Ticket.Category.BAR, 1);
        testTickets.put("ticket:" + ticket2.getId(), ticket2);
        ticket3 = new OneDayTicket(3, 3, 3, Ticket.Category.BAR, 1);
        testTickets.put("ticket:" + ticket3.getId(), ticket3);
    }
}