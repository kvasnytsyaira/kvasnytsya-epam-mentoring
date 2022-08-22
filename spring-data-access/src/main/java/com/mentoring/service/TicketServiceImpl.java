package com.mentoring.service;

import com.mentoring.model.Event;
import com.mentoring.model.Ticket;
import com.mentoring.model.User;
import com.mentoring.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {


    private final TicketRepository ticketRepository;

    @Override
    public Ticket bookTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getBookedTickets(User user, long pageSize, long pageNum) {
        return ticketRepository.findAll()
                .stream()
                .filter(ticket -> ticket.getUserId() == user.getId())
                .skip((pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, long pageSize, long pageNum) {
        return ticketRepository.findAll()
                .stream()
                .filter(ticket -> event.getId() == ticket.getEventId())
                .skip((pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public void cancelTicket(long ticketId) {
        ticketRepository.deleteById(ticketId);
    }

}
