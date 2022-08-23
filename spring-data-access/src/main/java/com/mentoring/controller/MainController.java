package com.mentoring.controller;

import com.mentoring.dto.EventDto;
import com.mentoring.dto.TicketDTO;
import com.mentoring.dto.UserAccountDTO;
import com.mentoring.dto.UserDTO;
import com.mentoring.facade.BookingFacade;
import com.mentoring.model.Event;
import com.mentoring.model.Ticket;
import com.mentoring.model.User;
import com.mentoring.model.UserAccount;
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
    public List<TicketDTO> ticketsByUser(Model model, @PathVariable int userId,
                                         @RequestParam("page-size") long pageSize, @RequestParam("page-num") long pageNum) {
        User userById = bookingFacade.getUserById(userId);
        List<TicketDTO> bookedTickets = bookingFacade.getBookedTickets(userById, pageSize, pageNum);
        return bookedTickets;
    }
    @GetMapping(value = "/events/{eventId}/tickets")
    public List<TicketDTO> ticketsByEvent(Model model, @PathVariable int eventId,
                                          @RequestParam("page-size") long pageSize, @RequestParam("page-num") long pageNum) {
        Event event = bookingFacade.getEventById(eventId);
        List<TicketDTO> bookedTickets = bookingFacade.getBookedTickets(event, pageSize, pageNum);
        return bookedTickets;
    }

    @GetMapping(value = "/events", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EventDto> events() {
        List<EventDto> events = bookingFacade.getAllEvents();
        return events;
    }


    @GetMapping("/events/title/{title}")
    public List<EventDto> eventsByTitle(@PathVariable String title,
                                        @RequestParam("page-size") long pageSize, @RequestParam("page-num") long pageNum) {
        List<EventDto> events = bookingFacade.getEventsByTitle(title, pageSize, pageNum);
        return events;
    }

    @GetMapping("/events/date/{date}")
    public List<EventDto> eventsByDate(Model model, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") String date,
                                       @RequestParam("page-size") long pageSize, @RequestParam("page-num") long pageNum) {
        LocalDate filterDate = LocalDate.parse(date);
        List<EventDto> events = bookingFacade.getEventsForDay(filterDate, pageSize, pageNum);
        return events;
    }

    @PostMapping(value = "/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void bookTicket(@RequestBody @Valid TicketDTO ticket) {
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
    public UserDTO userById(@PathVariable int id) {
        UserDTO user = bookingFacade.getUserDTOById(id);
        return user;
    }

    @GetMapping(value = "/users/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO userByEmail(@PathVariable String email) {
        UserDTO user = bookingFacade.getUserByEmail(email);
        return user;
    }

    @PostMapping(value = "/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void user(@RequestBody @Valid UserDTO user) {
        bookingFacade.createUser(user);
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable long id,
                           @RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Valid UserDTO user) {
        UserDTO user1 = (UserDTO) bookingFacade.updateUser(id, user);
    }

    @DeleteMapping(value = "/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int userId) {
        bookingFacade.deleteUser(userId);
    }

    @PostMapping(value = "/account")
    @ResponseStatus(HttpStatus.CREATED)
    public void account(@RequestBody @Valid UserAccountDTO account) {
        bookingFacade.createUserAccount(account);
    }

    @GetMapping(value = "/events/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public EventDto eventById(@PathVariable int eventId) {
        EventDto event = bookingFacade.getEventDTOById(eventId);
        return event;
    }


    @PostMapping(value = "/events", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createEvent(@RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Valid EventDto event) {
        bookingFacade.createEvent(event);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable int id,
                                         @RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Valid EventDto event) {
        EventDto event1 = (EventDto) bookingFacade.updateEvent(id, event);
        return new ResponseEntity<>(event1, HttpStatus.CREATED);
    }

    @PatchMapping("/events/{id}")
    public ResponseEntity<EventDto> updateEventName(@PathVariable int id, @RequestBody String name) {
        EventDto event1 = (EventDto) bookingFacade.updateEventName(id, name);
        return ResponseEntity.ok().body(event1);
    }

    @DeleteMapping(value = "/events/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable int eventId) {
        bookingFacade.deleteEvent(eventId);
        return ResponseEntity.noContent().build();
    }
}
