package mentoring.controller;

import mentoring.facade.BookingFacade;
import mentoring.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class FacadeController {
    @Autowired
    BookingFacade bookingFacade;

    /**
     * Simply selects the home view to render by returning its name.
     */
    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @GetMapping(value = "/users/{userId}/tickets")
    public ResponseEntity<List<Ticket>> ticketsByUser(Model model, @PathVariable int userId,
                                                      @RequestParam("page-size") long pageSize, @RequestParam("page-num") long pageNum) {
        User userById = bookingFacade.getUserById(userId);
        List<Ticket> bookedTickets = bookingFacade.getBookedTickets(userById, pageSize, pageNum);
        model.addAttribute("tickets", bookedTickets);
        model.addAttribute("prev", pageNum - 1);
        model.addAttribute("next", pageNum + 1);
        return ResponseEntity.ok().body(bookedTickets);
    }

    @GetMapping(value = "/events/{eventId}/tickets")
    public ResponseEntity<List<Ticket>> ticketsByEvent(Model model, @PathVariable int eventId,
                                                       @RequestParam("page-size") long pageSize, @RequestParam("page-num") long pageNum) {
        Event event = bookingFacade.getEventById(eventId);
        List<Ticket> bookedTickets = bookingFacade.getBookedTickets(event, pageSize, pageNum);
        model.addAttribute("tickets", bookedTickets);
        model.addAttribute("prev", pageNum - 1);
        model.addAttribute("next", pageNum + 1);
        return ResponseEntity.ok().body(bookedTickets);
    }


    @PostMapping(value = "/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ticket> bookTicket(@RequestBody OneDayTicket ticket) {
        OneDayTicket ticket1 = (OneDayTicket) bookingFacade.bookTicket(ticket);
        return new ResponseEntity<>(ticket1, HttpStatus.CREATED);
    }

    @GetMapping(value = "/tickets/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> pdf(@RequestParam long userId,
                                      @RequestParam("page-size") long pageSize, @RequestParam("page-num") long pageNum) throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);
        byte[] pdf = bookingFacade.createPdf(userId, pageSize, pageNum);
        return ResponseEntity.ok().headers(httpHeaders).body(pdf);
    }

    @DeleteMapping(value = "/tickets/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable long id) {
        bookingFacade.cancelTicket(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> userById(@PathVariable int id) {
        User user = bookingFacade.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/users/email/{email}")
    public ResponseEntity<User> userByEmail(@PathVariable String email) {
        User user = bookingFacade.getUserByEmail(email);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<User> user(@RequestBody AdultUser user) {
        AdultUser user1 = (AdultUser) bookingFacade.createUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id,
                                        @RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) AdultUser user) {
        AdultUser user1 = (AdultUser) bookingFacade.updateUser(id, user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId) {
        bookingFacade.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping(value = "/events/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> eventById(@PathVariable int eventId) {
        Event event = bookingFacade.getEventById(eventId);
        return ResponseEntity.ok().body(event);
    }

    @GetMapping("/events/title/{title}")
    public String eventsByTitle(Model model, @PathVariable String title,
                                @RequestParam("page-size") long pageSize, @RequestParam("page-num") long pageNum) {
        List<Event> events = bookingFacade.getEventsByTitle(title, pageSize, pageNum);
        model.addAttribute("events", events);
        return "events";
    }

    @GetMapping("/events/date/{date}")
    public String eventsByDate(Model model, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                               @RequestParam("page-size") long pageSize, @RequestParam("page-num") long pageNum) {
        List<Event> events = bookingFacade.getEventsForDay(date, pageSize, pageNum);
        model.addAttribute("events", events);
        return "events";
    }

    @PostMapping(value = "/events", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConcertEvent> createEvent(@RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) ConcertEvent event) {
        ConcertEvent event1 = (ConcertEvent) bookingFacade.createEvent(event);
        return new ResponseEntity<>(event1, HttpStatus.CREATED);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable int id,
                                         @RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) ConcertEvent event) {
        ConcertEvent event1 = (ConcertEvent) bookingFacade.updateEvent(id, event);
        return new ResponseEntity<>(event1, HttpStatus.CREATED);
    }

    @PatchMapping("/events/{id}")
    public ResponseEntity<Event> updateEventName(@PathVariable int id, @RequestBody String name) {
        ConcertEvent event1 = (ConcertEvent) bookingFacade.updateEventName(id, name);
        return ResponseEntity.ok().body(event1);
    }

    @DeleteMapping(value = "/events/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable int eventId) {
        bookingFacade.deleteEvent(eventId);
        return ResponseEntity.noContent().build();
    }
}