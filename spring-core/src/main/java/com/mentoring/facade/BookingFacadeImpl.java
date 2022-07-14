package com.mentoring.facade;

import com.mentoring.model.Event;
import com.mentoring.model.OneDayTicket;
import com.mentoring.model.Ticket;
import com.mentoring.model.User;
import com.mentoring.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class BookingFacadeImpl implements BookingFacade {
    private static Logger logger = LoggerFactory.getLogger(Logger.class);

    private final EventService eventService;
    private final UserService userService;
    private final TicketService ticketService;

    @Autowired
    public BookingFacadeImpl(EventService eventService, UserService userService, TicketService ticketService) {
        this.eventService = eventService;
        this.userService = userService;
        this.ticketService = ticketService;
    }

    /**
     * Gets event by its id.
     *
     * @return Event.
     */
    public Event getEventById(long eventId) {
        logger.info("*** BookingFacade: Get event by id");
        return eventService.getEventById(eventId);
    }

    /**
     * Get list of events by matching title. Title is matched using 'contains' approach.
     * In case nothing was found, empty list is returned.
     *
     * @param title    Event title or it's part.
     * @param pageSize Pagination param. Number of events to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of events.
     */
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        logger.info("*** BookingFacade: Get event by title");
        return eventService.getEventsByTitle(title, pageSize, pageNum);
    }


    /**
     * Get list of events for specified day.
     * In case nothing was found, empty list is returned.
     *
     * @param day      Date object from which day information is extracted.
     * @param pageSize Pagination param. Number of events to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of events.
     */
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        logger.info("*** BookingFacade: Get event for day");
        return eventService.getEventsForDay(day, pageSize, pageNum);
    }


    /**
     * Creates new event. Event id should be auto-generated.
     *
     * @param event Event data.
     * @return Created Event object.
     */
    public Event createEvent(Event event) {
        logger.info("*** BookingFacade: Create event");
        return eventService.createEvent(event);
    }


    /**
     * Updates event using given data.
     *
     * @param event Event data for update. Should have id set.
     * @return Updated Event object.
     */
    public Event updateEvent(Event event) {
        logger.info("*** BookingFacade: Update event ");
        return eventService.updateEvent(event);
    }


    /**
     * Deletes event by its id.
     *
     * @param eventId Event id.
     * @return Flag that shows whether event has been deleted.
     */
    public boolean deleteEvent(long eventId) {
        logger.info("*** BookingFacade: Delete event by id");
        return eventService.deleteEvent(eventId);
    }


    /**
     * Gets user by its id.
     *
     * @return User.
     */
    public User getUserById(long userId) {

        logger.info("*** BookingFacade: Get user by id");
        return userService.getUserById(userId);
    }


    /**
     * Gets user by its email. Email is strictly matched.
     *
     * @return User.
     */
    public User getUserByEmail(String email) {
        logger.info("*** BookingFacade: Get user by user");
        return userService.getUserByEmail(email);
    }


    /**
     * Get list of users by matching name. Name is matched using 'contains' approach.
     * In case nothing was found, empty list is returned.
     *
     * @param name     Users name or it's part.
     * @param pageSize Pagination param. Number of users to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of users.
     */
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        logger.info("*** BookingFacade: Get users by name");
        return userService.getUsersByName(name, pageSize, pageNum);
    }


    /**
     * Creates new user. User id should be auto-generated.
     *
     * @param user User data.
     * @return Created User object.
     */
    public User createUser(User user) {
        logger.info("*** BookingFacade: Create user ");
        return userService.createUser(user);
    }


    /**
     * Updates user using given data.
     *
     * @param user User data for update. Should have id set.
     * @return Updated User object.
     */
    public User updateUser(User user) {
        logger.info("*** BookingFacade: Update user ");
        return userService.updateUser(user);
    }


    /**
     * Deletes user by its id.
     *
     * @param userId User id.
     * @return Flag that shows whether user has been deleted.
     */
    public boolean deleteUser(long userId) {
        logger.info("*** BookingFacade: Delete user ");
        return userService.deleteUser(userId);
    }


    /**
     * Book ticket for a specified event on behalf of specified user.
     *
     * @param userId   User Id.
     * @param eventId  Event Id.
     * @param place    Place number.
     * @param category Service category.
     * @return Booked ticket object.
     * @throws java.lang.IllegalStateException if this place has already been booked.
     */
    public Ticket bookTicket(Ticket ticket) {
        logger.info("*** BookingFacade: Book ticket ");
        return ticketService.bookTicket(ticket);
    }

    /**
     * Get all booked tickets for specified user. Tickets should be sorted by event date in descending order.
     *
     * @param user     User
     * @param pageSize Pagination param. Number of tickets to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of Ticket objects.
     */
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        logger.info("*** BookingFacade: Get booked tickets by user ");
        return ticketService.getBookedTickets(user, pageSize, pageNum);
    }


    /**
     * Get all booked tickets for specified event. Tickets should be sorted in by user email in ascending order.
     *
     * @param event    Event
     * @param pageSize Pagination param. Number of tickets to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of Ticket objects.
     */
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        logger.info("*** BookingFacade: Get booked tickets by event ");
        return ticketService.getBookedTickets(event, pageSize, pageNum);
    }

    /**
     * Cancel ticket with a specified id.
     *
     * @param ticketId Ticket id.
     * @return Flag whether anything has been canceled.
     */
    public boolean cancelTicket(long ticketId) {
        logger.info("*** BookingFacade: Cancel ticket by id");
        return ticketService.cancelTicket(ticketId);
    }

}


