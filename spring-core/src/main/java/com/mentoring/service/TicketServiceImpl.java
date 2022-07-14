package com.mentoring.service;

import com.mentoring.model.Event;
import com.mentoring.model.Ticket;
import com.mentoring.model.User;
import com.mentoring.storingData.Storage;
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
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return storage.getTickets()
                .values()
                .stream()
                .filter(ticket -> ticket.getUserId() == user.getId())
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return storage.getTickets().values()
                .stream()
                .filter(ticket -> event.getId() == ticket.getEventId())
                .collect(Collectors.toList());
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return storage.removeTicket(ticketId);
    }
}
