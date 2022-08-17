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
    @ResponseStatus(HttpStatus.CREATED)
    public void bookTicket(@RequestBody @Valid OneDayTicket ticket) {
        bookingFacade.bookTicket(ticket);
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
    public void user(@RequestBody @Valid AdultUser user) {
        bookingFacade.createUser(user);
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable long id,
                           @RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Valid AdultUser user) {
        AdultUser user1 = (AdultUser) bookingFacade.updateUser(id, user);
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
    public void createEvent(@RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Valid ConcertEvent event) {
        bookingFacade.createEvent(event);
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
