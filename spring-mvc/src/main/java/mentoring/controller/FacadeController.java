package mentoring.controller;

import mentoring.controller.exceptions.ValidationException;
import mentoring.facade.BookingFacade;
import mentoring.model.Event;
import mentoring.model.Ticket;
import mentoring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@Validated
public class FacadeController {
    @Autowired
    BookingFacade bookingFacade;

    @ExceptionHandler({ValidationException.class})
    public void handle() {
//
    }

    /**
     * Simply selects the home view to render by returning its name.
     */
    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @GetMapping(value = "/users/{userId}/tickets")
    public String ticketsByUser(Model model, @PathVariable int userId,
                                @RequestParam("page-size") long pageSize, @RequestParam("page-num") long pageNum) {
        User userById = bookingFacade.getUserById(userId);
        List<Ticket> bookedTickets = bookingFacade.getBookedTickets(userById, pageSize, pageNum);
        model.addAttribute("tickets", bookedTickets);
        model.addAttribute("prev", pageNum - 1);
        model.addAttribute("next", pageNum + 1);
        model.addAttribute("title", "Tickets by User Id : " + userId);
        return "tickets";
    }

    @GetMapping(value = "/events/{eventId}/tickets")
    public String ticketsByEvent(Model model, @PathVariable int eventId,
                                 @RequestParam("page-size") long pageSize, @RequestParam("page-num") long pageNum) {
        Event event = bookingFacade.getEventById(eventId);
        List<Ticket> bookedTickets = bookingFacade.getBookedTickets(event, pageSize, pageNum);
        model.addAttribute("tickets", bookedTickets);
        model.addAttribute("prev", pageNum - 1);
        model.addAttribute("next", pageNum + 1);
        model.addAttribute("title", "Tickets by Event Id : " + eventId);
        return "tickets";
    }

    @GetMapping(value = "/events", produces = MediaType.APPLICATION_JSON_VALUE)
    public String events(Model model) {
        List<Event> events = bookingFacade.getAllEvents();
        model.addAttribute("events", events);
        model.addAttribute("title", "Events");
        return "events";
    }


    @GetMapping("/events/title/{title}")
    public String eventsByTitle(Model model, @PathVariable String title,
                                @RequestParam("page-size") long pageSize, @RequestParam("page-num") long pageNum) {
        List<Event> events = bookingFacade.getEventsByTitle(title, pageSize, pageNum);
        model.addAttribute("events", events);
        model.addAttribute("title", "Events by Title : " + title);
        return "events";
    }

    @GetMapping("/events/date/{date}")
    public String eventsByDate(Model model, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                               @RequestParam("page-size") long pageSize, @RequestParam("page-num") long pageNum) {
        List<Event> events = bookingFacade.getEventsForDay(date, pageSize, pageNum);
        model.addAttribute("events", events);
        model.addAttribute("title", "Events by date : " + date);
        return "events";
    }

}