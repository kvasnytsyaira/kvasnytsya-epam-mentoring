package com.mentoring.service;

import com.mentoring.dto.EventDto;
import com.mentoring.model.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventService {
    EventDto getEventDTOById(long eventId);
    Event getEventById(long eventId);

    List<EventDto> getEventsByTitle(String title, long pageSize, long pageNum);

    List<EventDto> getEventsForDay(LocalDate date, long pageSize, long pageNum);

    EventDto createEvent(EventDto event);

    EventDto updateEvent(long eventId , EventDto event);
    EventDto updateEventTitle(long id, String title);

    void deleteEvent(long eventId);

    List<EventDto> getAllEvents();
}
