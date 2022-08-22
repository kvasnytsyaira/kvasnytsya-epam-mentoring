package com.mentoring.controller;

import com.mentoring.facade.BookingFacade;
import com.mentoring.model.Event;
import com.mentoring.model.Ticket;
import com.mentoring.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
public class MainController {

    private final BookingFacade bookingFacade;

    @ExceptionHandler({ValidationException.class})
    public void handle() {
//
    }

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @GetMapping(value = "/users/{userId}/tickets")
    public List<Ticket> ticketsByUser(Model model, @PathVariable int userId,
                                      @RequestParam("page-size") long pageSize, @RequestParam("page-num") long pageNum) {
        User userById = bookingFacade.getUserById(userId);
        List<Ticket> bookedTickets = bookingFacade.getBookedTickets(userById, pageSize, pageNum);
        return bookedTickets;
    }

    @GetMapping(value = "/events/{eventId}/tickets")
    public List<Ticket> ticketsByEvent(Model model, @PathVariable int eventId,
                                       @RequestParam("page-size") long pageSize, @RequestParam("page-num") long pageNum) {
        Event event = bookingFacade.getEventById(eventId);
        List<Ticket> bookedTickets = bookingFacade.getBookedTickets(event, pageSize, pageNum);
        return bookedTickets;
    }

    @GetMapping(value = "/events", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> events(Model model) {
        List<Event> events = bookingFacade.getAllEvents();
        return events;
    }


    @GetMapping("/events/title/{title}")
    public List<Event> eventsByTitle(Model model, @PathVariable String title,
                                     @RequestParam("page-size") long pageSize, @RequestParam("page-num") long pageNum) {
        List<Event> events = bookingFacade.getEventsByTitle(title, pageSize, pageNum);
        return events;
    }

    @GetMapping("/events/date/{date}")
    public List<Event> eventsByDate(Model model, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") String date,
                                    @RequestParam("page-size") long pageSize, @RequestParam("page-num") long pageNum) {
        LocalDate filterDate = LocalDate.parse(date);
        List<Event> events = bookingFacade.getEventsForDay(filterDate, pageSize, pageNum);
        return events;
    }

    @PostMapping(value = "/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void bookTicket(@RequestBody @Valid Ticket ticket) {
        bookingFacade.bookTicket(ticket);
    }


    @DeleteMapping(value = "/tickets/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteTicket(@PathVariable long id) {
        bookingFacade.cancelTicket(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User userById(@PathVariable int id) {
        User user = bookingFacade.getUserById(id);
        return user;
    }

    @GetMapping(value = "/users/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public User userByEmail(@PathVariable String email) {
        User user = bookingFacade.getUserByEmail(email);
        return user;
    }

    @PostMapping(value = "/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void user(@RequestBody @Valid User user) {
        bookingFacade.createUser(user);
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable long id,
                           @RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Valid User user) {
        User user1 = (User) bookingFacade.updateUser(id, user);
    }

    @DeleteMapping(value = "/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int userId) {
        bookingFacade.deleteUser(userId);
    }

    @GetMapping(value = "/events/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Event eventById(@PathVariable int eventId) {
        Event event = bookingFacade.getEventById(eventId);
        return event;
    }


    @PostMapping(value = "/events", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createEvent(@RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Valid Event event) {
        bookingFacade.createEvent(event);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable int id,
                                         @RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Valid Event event) {
        Event event1 = (Event) bookingFacade.updateEvent(id, event);
        return new ResponseEntity<>(event1, HttpStatus.CREATED);
    }

    @PatchMapping("/events/{id}")
    public ResponseEntity<Event> updateEventName(@PathVariable int id, @RequestBody String name) {
        Event event1 = (Event) bookingFacade.updateEventName(id, name);
        return ResponseEntity.ok().body(event1);
    }

    @DeleteMapping(value = "/events/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable int eventId) {
        bookingFacade.deleteEvent(eventId);
        return ResponseEntity.noContent().build();
    }
}
