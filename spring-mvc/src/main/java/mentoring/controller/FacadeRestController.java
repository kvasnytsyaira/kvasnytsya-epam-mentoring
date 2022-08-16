package mentoring.controller;

import mentoring.controller.exceptions.ValidationException;
import mentoring.facade.BookingFacade;
import mentoring.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api")
@Validated
public class FacadeRestController {

    @Autowired
    BookingFacade bookingFacade;

    @ExceptionHandler({ValidationException.class})
    public void handle() {
//
    }

    @PostMapping(value = "/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ticket> bookTicket(@RequestBody @Valid OneDayTicket ticket) {
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
    public ResponseEntity<User> user(@RequestBody @Valid AdultUser user) {
        AdultUser user1 = (AdultUser) bookingFacade.createUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id,
                                        @RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Valid AdultUser user) {
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


    @PostMapping(value = "/events", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConcertEvent> createEvent(@RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Valid ConcertEvent event) {
        ConcertEvent event1 = (ConcertEvent) bookingFacade.createEvent(event);
        return new ResponseEntity<>(event1, HttpStatus.CREATED);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable int id,
                                         @RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Valid ConcertEvent event) {
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
