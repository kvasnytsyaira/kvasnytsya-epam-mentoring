package com.mentoring.facade;

import com.mentoring.dto.EventDto;
import com.mentoring.dto.TicketDTO;
import com.mentoring.dto.UserAccountDTO;
import com.mentoring.dto.UserDTO;
import com.mentoring.exception.RecordsNotFoundForSearchCriteriaException;
import com.mentoring.model.Event;
import com.mentoring.model.Ticket;
import com.mentoring.model.User;
import com.mentoring.model.UserAccount;
import com.mentoring.service.EventServiceImpl;
import com.mentoring.service.TicketServiceImpl;
import com.mentoring.service.UserAccountServiceImpl;
import com.mentoring.service.UserServiceImpl;
import com.mentoring.utills.MainUtil;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.UserDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookingFacadeImpl implements BookingFacade {
    private static Logger logger = LoggerFactory.getLogger(Logger.class);

    private final EventServiceImpl eventService;
    private final UserServiceImpl userService;
    private final TicketServiceImpl ticketService;
    private final UserAccountServiceImpl userAccountService;

    private final MainUtil util;


    /**
     * Gets event by its id.
     *
     * @return Event.
     */
    public Event getEventById(long eventId) {
        logger.info("*** BookingFacade: Get event by id");
        return eventService.getEventById(eventId);
    }

    public EventDto getEventDTOById(long eventId) {
        logger.info("*** BookingFacade: Get event by id");
        return eventService.getEventDTOById(eventId);
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
    public List<EventDto> getEventsByTitle(String title, long pageSize, long pageNum) {
        logger.info("*** BookingFacade: Get event by title");
        List<EventDto> eventsByTitle = eventService.getEventsByTitle(title, pageSize, pageNum);
        if (eventsByTitle.size() > 0) {
            return eventsByTitle;
        } else throw new RecordsNotFoundForSearchCriteriaException("No events with title " + title + " found");
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
    public List<EventDto> getEventsForDay(LocalDate day, long pageSize, long pageNum) {
        logger.info("*** BookingFacade: Get event for day");
        List<EventDto> eventsByDate = eventService.getEventsForDay(day, pageSize, pageNum);
        if (eventsByDate.size() > 0) {
            return eventsByDate;
        } else throw new RecordsNotFoundForSearchCriteriaException("No events with date " + day + " found");
    }


    /**
     * Creates new event. Event id should be auto-generated.
     *
     * @param event Event data.
     * @return Created Event object.
     */
    public EventDto createEvent(EventDto event) {
        logger.info("*** BookingFacade: Create event");
        return eventService.createEvent(event);
    }


    /**
     * Updates event using given data.
     *
     * @param event Event data for update. Should have id set.
     * @return Updated Event object.
     */
    public EventDto updateEvent(long eventId, EventDto event) {
        logger.info("*** BookingFacade: Update event ");
        return eventService.updateEvent(eventId, event);
    }


    /**
     * Deletes event by its id.
     *
     * @param eventId Event id.
     * @return Flag that shows whether event has been deleted.
     */
    public void deleteEvent(long eventId) {
        logger.info("*** BookingFacade: Delete event by id");
        eventService.deleteEvent(eventId);
    }

    @Override
    public EventDto updateEventName(int id, String eventName) {

        return eventService.updateEventTitle(id, eventName);
    }

    @Override
    public List<EventDto> getAllEvents() {
        return eventService.getAllEvents();
    }
    /**
     * Gets user by its id.
     *
     * @return User.
     */
    public UserDTO getUserDTOById(long userId) {

        logger.info("*** BookingFacade: Get user by id");
        return userService.getUserDTOById(userId);
    }
    public User getUserById(long userId) {

        logger.info("*** BookingFacade: Get user by id");
        return userService.getUserById(userId);
    }

    /**
     * Gets user by its email. Email is strictly matched.
     *
     * @return User.
     */
    public UserDTO getUserByEmail(String email) {
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
    public List<UserDTO> getUsersByName(String name, long pageSize, long pageNum) {
        logger.info("*** BookingFacade: Get users by name");
        return userService.getUsersByName(name, pageSize, pageNum);
    }


    /**
     * Creates new user. User id should be auto-generated.
     *
     * @param user User data.
     * @return Created User object.
     */
    public UserDTO createUser(UserDTO user) {
        logger.info("*** BookingFacade: Create user ");
        return userService.createUser(user);
    }


    /**
     * Updates user using given data.
     *
     * @param user User data for update. Should have id set.
     * @return Updated User object.
     */
    public UserDTO updateUser(long id, UserDTO user) {
        logger.info("*** BookingFacade: Update user ");
        return userService.updateUser(id, user);
    }


    /**
     * Deletes user by its id.
     *
     * @param userId User id.
     * @return Flag that shows whether user has been deleted.
     */
    public void deleteUser(long userId) {
        logger.info("*** BookingFacade: Delete user ");
        userService.deleteUser(userId);
    }

    @Override
    public UserAccountDTO createUserAccount(UserAccountDTO account) {
        return userAccountService.createUserAccount(account);
    }

    @Override
    public void refillAccount(UserAccountDTO userAccount) {
        userAccountService.refillAccount(userAccount);
    }

    @Override
    public UserAccountDTO getUserAccountById(long accountId) {
        return userAccountService.getUserAccountById(accountId);
    }

    /**
     * Book ticket for a specified event on behalf of specified user.
     *
     * @param ticket .
     * @return Booked ticket object.
     * @throws IllegalStateException if this place has already been booked.
     */
    public TicketDTO bookTicket(TicketDTO ticket) {
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
    public List<TicketDTO> getBookedTickets(User user, long pageSize, long pageNum) {
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
    public List<TicketDTO> getBookedTickets(Event event, long pageSize, long pageNum) {
        logger.info("*** BookingFacade: Get booked tickets by event ");
        return ticketService.getBookedTickets(event, pageSize, pageNum);
    }

    /**
     * Cancel ticket with a specified id.
     *
     * @param ticketId Ticket id.
     * @return Flag whether anything has been canceled.
     */
    public void cancelTicket(long ticketId) {
        logger.info("*** BookingFacade: Cancel ticket by id");
        ticketService.cancelTicket(ticketId);
    }

}
