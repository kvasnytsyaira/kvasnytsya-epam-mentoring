package com.mentoring.service;

import com.mentoring.dto.TicketDTO;
import com.mentoring.exception.NotEnoughMoneyException;
import com.mentoring.exception.RecordNotFoundException;
import com.mentoring.model.Event;
import com.mentoring.model.Ticket;
import com.mentoring.model.User;
import com.mentoring.model.UserAccount;
import com.mentoring.repository.EventRepository;
import com.mentoring.repository.TicketRepository;
import com.mentoring.repository.UserAccountRepository;
import com.mentoring.repository.UserRepository;
import com.mentoring.utills.MainUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {


    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;
    private final UserAccountRepository userAccountRepository;
    private final UserRepository userRepository;
    private final MainUtil util;


    @Override
    @Transactional
    public TicketDTO bookTicket(TicketDTO ticket) {
        UserAccount account = userAccountRepository.findByUser(ticket.getUserId());
        User user = userRepository.findById(ticket.getUserId())
                .orElseThrow(() -> new RecordNotFoundException("No user found with id : " + ticket.getUserId()));
        Event event = eventRepository.findById(ticket.getEventId())
                .orElseThrow(() -> new RecordNotFoundException("No event found with id : " + ticket.getEventId()));
        if (account.getWallet() >= event.getPrice()) {
            account.setWallet(account.getWallet() - event.getPrice());
            userAccountRepository.save(account);
            return util.convertEntityToDto(
                    ticketRepository.save(new Ticket(event, user, ticket.getCategory(), ticket.getPlace())));
        } else throw new NotEnoughMoneyException("Refill the account to buy ticket to this event!");
    }

    @Override
    public List<TicketDTO> getBookedTickets(User user, long pageSize, long pageNum) {
        return util.convertEntityTicketsToDto(ticketRepository.findAll()
                .stream()
                .filter(ticket -> ticket.getUser().getId() == user.getId())
                .skip((pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList()));
    }

    @Override
    public List<TicketDTO> getBookedTickets(Event event, long pageSize, long pageNum) {
        return util.convertEntityTicketsToDto(ticketRepository.findAll()
                .stream()
                .filter(ticket -> event.getId() == ticket.getEvent().getId())
                .skip((pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList()));
    }

    @Override
    public void cancelTicket(long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        User user = ticket.getUser();
        UserAccount account = userAccountRepository.findByUser(user.getId());
        account.setWallet(account.getWallet() + ticket.getEvent().getPrice());
        ticketRepository.deleteById(ticketId);
    }

}
