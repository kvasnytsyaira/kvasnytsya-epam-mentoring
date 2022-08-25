package com.mentoring.service;

import com.mentoring.dto.EventDto;
import com.mentoring.exception.RecordNotFoundException;
import com.mentoring.exception.RecordsNotFoundForSearchCriteriaException;
import com.mentoring.model.Event;
import com.mentoring.repository.EventRepository;
import com.mentoring.utills.MainUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final MainUtil util;

    private final TicketServiceImpl ticketService;

    @Override
    public Event getEventById(long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new RecordNotFoundException("Event with such id does not exist!"));
    }

    public EventDto getEventDTOById(long eventId) {
        return util.convertEntityToDto(eventRepository.findById(eventId)
                .orElseThrow(() -> new RecordNotFoundException("Event with such id does not exist!")));
    }

    @Override
    public List<EventDto> getEventsByTitle(String title, long pageSize, long pageNum) {
        List<EventDto> eventDtos = util.convertEntityEventsToDto(eventRepository.findAll().stream()
                .filter(event -> Objects.equals(event.getTitle().toLowerCase(), title.toLowerCase()))
                .skip((pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList()));
        if (eventDtos.size() > 0) return eventDtos;
        else throw new RecordsNotFoundForSearchCriteriaException("No events with such title!");
    }

    @Override
    public List<EventDto> getEventsForDay(LocalDate date, long pageSize, long pageNum) {
        List<EventDto> eventDtos = util.convertEntityEventsToDto(eventRepository.findAll()
                .stream()
                .filter(event -> event.getDate().isEqual(date))
                .skip((pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList()));

        if (eventDtos.size() > 0) return eventDtos;
        else throw new RecordsNotFoundForSearchCriteriaException("No events with such date!");
    }

    @Override
    public EventDto createEvent(EventDto event) {
        Event savedEvent = eventRepository.save(util.convertDtoToEntity(event));
        return util.convertEntityToDto(savedEvent);
    }

    @Override
    public EventDto updateEvent(long id, EventDto eventNew) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No event found with id: " + id));
        event.setTitle(eventNew.getTitle());
        event.setDate(eventNew.getDate());
        return util.convertEntityToDto(eventRepository.save(event));
    }

    @Override
    public EventDto updateEventTitle(long id, String title) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No event found with id: " + id));
        event.setTitle(title);
        return util.convertEntityToDto(eventRepository.save(event));
    }

    @Override
    @Transactional
    public void deleteEvent(long id) {
        eventRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No event found with id: " + id));
        ticketService.cancelTicketsForEvent(id);
        eventRepository.deleteById(id);
    }

    @Override
    public List<EventDto> getAllEvents() {
        return util.convertEntityEventsToDto(new ArrayList<>(eventRepository.findAll()));
    }
}
