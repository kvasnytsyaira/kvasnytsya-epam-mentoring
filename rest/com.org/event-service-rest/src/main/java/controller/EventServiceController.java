package controller;

import model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviceImpl.EventServiceImpl;

import java.util.Set;

@RestController
@RequestMapping("/")
@ComponentScan
public class EventServiceController {

    @Autowired
    private final EventServiceImpl eventService;

    public EventServiceController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }


    @GetMapping(value = "/events", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Event>> getAllEvents() {
        Set<Event> events = eventService.getAllEvents();
        events.stream().forEach(System.out::println);
        System.out.println("help");
        return new ResponseEntity<>(events, HttpStatus.OK);
    }


    @GetMapping("/events/title/{title}")
    public ResponseEntity<Set<Event>> eventsByTitle(@PathVariable String title) {
        Set<Event> events = eventService.getAllEventsByTitle(title);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping(value = "/events/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Event> eventById(@PathVariable String eventId) throws Exception {
        Event event = eventService.getEvent(eventId);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable String id,
                                         @RequestBody Event event) throws Exception {
        Event event1 = eventService.updateEvent(event);
        return new ResponseEntity<>(event1, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/events/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable String eventId) throws Exception {
        eventService.deleteEvent(eventId);
        return ResponseEntity.noContent().build();
    }
}
