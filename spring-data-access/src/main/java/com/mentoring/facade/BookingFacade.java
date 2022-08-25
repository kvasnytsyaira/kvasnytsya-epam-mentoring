package com.mentoring.facade;

import com.mentoring.dto.EventDto;
import com.mentoring.dto.TicketDTO;
import com.mentoring.dto.UserAccountDTO;
import com.mentoring.dto.UserDTO;
import com.mentoring.model.Event;
import com.mentoring.model.User;

import java.time.LocalDate;
import java.util.List;

/**
 * Groups together all operations related to tickets booking.
 * Created by maksym_govorischev.
 */
public interface BookingFacade {

    /**
     * Gets event by its id.
     *
     * @return Event.
     */
    EventDto getEventDTOById(long eventId);
    Event getEventById(long eventId);

    /**
     * Get list of events by matching title. Title is matched using 'contains' approach.
     * In case nothing was found, empty list is returned.
     *
     * @param title    Event title or it's part.
     * @param pageSize Pagination param. Number of events to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of events.
     */
    List<EventDto> getEventsByTitle(String title, long pageSize, long pageNum);

    /**
     * Get list of events for specified day.
     * In case nothing was found, empty list is returned.
     *
     * @param day      Date object from which day information is extracted.
     * @param pageSize Pagination param. Number of events to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of events.
     */
    List<EventDto> getEventsForDay(LocalDate day, long pageSize, long pageNum);

    /**
     * Creates new event. Event id should be auto-generated.
     *
     * @param event Event data.
     * @return Created Event object.
     */
    EventDto createEvent(EventDto event);

    /**
     * Updates event using given data.
     *
     * @param event Event data for update. Should have id set.
     * @return Updated Event object.
     */
    EventDto updateEvent(long id, EventDto event);

    /**
     * Deletes event by its id.
     *
     * @param eventId Event id.
     * @return Flag that shows whether event has been deleted.
     */
    void deleteEvent(long eventId);

    /**
     * Gets user by its id.
     *
     * @return User.
     */
    UserDTO getUserDTOById(long userId);
    User getUserById(long userId);

    /**
     * Gets user by its email. Email is strictly matched.
     *
     * @return User.
     */
    UserDTO getUserByEmail(String email);

    /**
     * Get list of users by matching name. Name is matched using 'contains' approach.
     * In case nothing was found, empty list is returned.
     *
     * @param name     Users name or it's part.
     * @param pageSize Pagination param. Number of users to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of users.
     */
    List<UserDTO> getUsersByName(String name, long pageSize, long pageNum);

    /**
     * Creates new user. User id should be auto-generated.
     *
     * @param user User data.
     * @return Created User object.
     */
    UserDTO createUser(UserDTO user);

    /**
     * Updates user using given data.
     *
     * @param user User data for update. Should have id set.
     * @return Updated User object.
     */
    UserDTO updateUser(long id, UserDTO user);

    /**
     * Deletes user by its id.
     *
     * @param userId User id.
     * @return Flag that shows whether user has been deleted.
     */
    void deleteUser(long userId);

    /**
     * Book ticket for a specified event on behalf of specified user.
     *
     * @param ticket Ticket
     * @return Booked ticket object.
     * @throws IllegalStateException if this place has already been booked.
     */
    TicketDTO bookTicket(TicketDTO ticket);

    /**
     * Get all booked tickets for specified user. Tickets should be sorted by event date in descending order.
     *
     * @param user     User
     * @param pageSize Pagination param. Number of tickets to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of Ticket objects.
     */
    List<TicketDTO> getBookedTickets(User user, long pageSize, long pageNum);

    /**
     * Get all booked tickets for specified event. Tickets should be sorted in by user email in ascending order.
     *
     * @param event    Event
     * @param pageSize Pagination param. Number of tickets to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of Ticket objects.
     */
    List<TicketDTO> getBookedTickets(Event event, long pageSize, long pageNum);


    /**
     * Cancel ticket with a specified id.
     *
     * @param ticketId Ticket id.
     * @return Flag whether anything has been canceled.
     */
    void cancelTicket(long ticketId);

    EventDto updateEventName(int id, String eventName);

    List<EventDto> getAllEvents();

    UserAccountDTO createUserAccount(UserAccountDTO account);

    void refillAccount(UserAccountDTO userAccount);

    UserAccountDTO getUserAccountById(long accountId);
}

