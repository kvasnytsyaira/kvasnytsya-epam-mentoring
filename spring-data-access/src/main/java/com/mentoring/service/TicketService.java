package com.mentoring.service;

import com.mentoring.model.Event;
import com.mentoring.model.Ticket;
import com.mentoring.model.User;

import java.util.List;

public interface TicketService {

    Ticket bookTicket(Ticket ticket);

    List<Ticket> getBookedTickets(User user, long pageSize, long pageNum);

    List<Ticket> getBookedTickets(Event event, long pageSize, long pageNum);

    void cancelTicket(long ticketId);
}
