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
class EventServiceImplTest {
    Storage storageMock = new Storage();
    EventServiceImpl eventService = new EventServiceImpl();

    Map<String, Event> testEvents = new LinkedHashMap<>();
    Event event1, event2, event3, event4;


    @BeforeEach
    void setUp() {
        insertEvents();
        ReflectionTestUtils.setField(eventService, "storage", storageMock);
        ReflectionTestUtils.setField(storageMock, "events", testEvents);
    }


    @Test
    void getEventById() {

        Event actual = eventService.getEventById(1);
        Assertions.assertEquals(event1, actual);
    }

    @Test
    void getEventsByTitle() {

        List<Event> actual = eventService.getEventsByTitle("1 Concert", 1, 1);
        Assertions.assertEquals(Collections.singletonList(event1), actual);
    }

    @Test
    void getEventsForDay() {
        List<Event> actual = eventService.getEventsForDay(new Date(2022, Calendar.JANUARY, 1), 1, 1);
        Assertions.assertEquals(Collections.singletonList(event1), actual);
    }

    @Test
    void createEvent() {
        Event actual = eventService.createEvent(event3);
        Assertions.assertEquals(event3, actual);
    }

    @Test
    void updateEvent() {
        Event actual = eventService.updateEvent(event1);
        Assertions.assertEquals(event1, actual);
    }

    @Test
    void deleteEvent() {
        boolean actual = eventService.deleteEvent(1);
        Assertions.assertTrue(actual);

    }

    private void insertEvents() {
        event1 = new ConcertEvent(1, "1 Concert", new Date(2022, Calendar.JANUARY, 1));
        testEvents.put("event:" + event1.getId(), event1);
        event2 = new ConcertEvent(2, "2 Concert", new Date(2022, Calendar.JANUARY, 2));
        testEvents.put("event:" + event2.getId(), event2);
        event3 = new ConcertEvent(3, "3 Concert", new Date(2022, Calendar.JANUARY, 3));
        testEvents.put("event:" + event3.getId(), event3);
    }

}