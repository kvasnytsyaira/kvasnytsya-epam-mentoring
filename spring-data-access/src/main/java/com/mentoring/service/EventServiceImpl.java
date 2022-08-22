package com.mentoring.service;

import com.mentoring.exception.RecordNotFoundException;
import com.mentoring.model.Event;
import com.mentoring.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public Event getEventById(long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new RecordNotFoundException("Event with such id does not exist!"));
    }

    @Override
    public List<Event> getEventsByTitle(String title, long pageSize, long pageNum) {
        return eventRepository.findAll().stream()
                .filter(event -> Objects.equals(event.getTitle().toLowerCase(), title.toLowerCase()))
                .skip((pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> getEventsForDay(LocalDate date, long pageSize, long pageNum) {
        return eventRepository.findAll()
                .stream()
                .filter(event -> event.getDate().isEqual(date))
                .skip((pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(long id, Event eventNew) {
        Event event = eventRepository.findById(id).get();
        event.setTitle(eventNew.getTitle());
        event.setDate(eventNew.getDate());
        return eventRepository.save(event);
    }

    @Override
    public Event updateEventTitle(long id, String title) {
        Event event = eventRepository.findById(id).get();
        event.setTitle(title);
        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(long eventId) {
        eventRepository.findById(eventId).get();
        eventRepository.deleteById(eventId);
    }

    @Override
    public List<Event> getAllEvents() {
        return new ArrayList<>(eventRepository.findAll());
    }
}
