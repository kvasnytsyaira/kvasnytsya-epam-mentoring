package mentoring.service;

import mentoring.model.Event;
import mentoring.model.Ticket;
import mentoring.model.User;

import java.util.List;

public interface TicketService {

    Ticket bookTicket(Ticket ticket);

    List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);

    List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);

    boolean cancelTicket(long ticketId);
}
