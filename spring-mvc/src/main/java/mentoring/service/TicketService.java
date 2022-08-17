package mentoring.service;

import mentoring.model.Event;
import mentoring.model.Ticket;
import mentoring.model.User;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface TicketService {

    Ticket bookTicket(Ticket ticket);

    List<Ticket> getBookedTickets(User user, long pageSize, long pageNum);

    List<Ticket> getBookedTickets(Event event, long pageSize, long pageNum);

    boolean cancelTicket(long ticketId);

    void preloadTickets() throws JAXBException;
}
