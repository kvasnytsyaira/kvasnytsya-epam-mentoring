package mentoring.service;

import mentoring.model.Event;
import mentoring.model.Ticket;
import mentoring.model.User;
import mentoring.storingData.Storage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class TicketServiceImpl implements TicketService {
    @Autowired
    private Storage storage;

    @Override
    public Ticket bookTicket(Ticket ticket) {
        return storage.save(ticket);
    }

    @Override
    public List<Ticket> getBookedTickets(User user, long pageSize, long pageNum) {
        return storage.getTickets()
                .values()
                .stream()
                .filter(ticket -> ticket.getUserId() == user.getId())
                .skip((pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, long pageSize, long pageNum) {
        return storage.getTickets().values()
                .stream()
                .filter(ticket -> event.getId() == ticket.getEventId())
                .skip((pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return storage.removeTicket(ticketId);
    }
}
