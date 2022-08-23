package com.mentoring.service;

import com.mentoring.dto.EventDto;
import com.mentoring.dto.TicketDTO;
import com.mentoring.dto.UserDTO;
import com.mentoring.model.Event;
import com.mentoring.model.User;

import java.util.List;

public interface TicketService {

    TicketDTO bookTicket(TicketDTO ticket);

    List<TicketDTO> getBookedTickets(User user, long pageSize, long pageNum);

    List<TicketDTO> getBookedTickets(Event event, long pageSize, long pageNum);

    void cancelTicket(long ticketId);
}
