package com.mentoring.utills;

import com.mentoring.dto.EventDto;
import com.mentoring.dto.TicketDTO;
import com.mentoring.dto.UserAccountDTO;
import com.mentoring.dto.UserDTO;
import com.mentoring.model.Event;
import com.mentoring.model.Ticket;
import com.mentoring.model.User;
import com.mentoring.model.UserAccount;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class MainUtil{

    private final ModelMapper mapper;

    public EventDto convertEntityToDto(Event event){
        return mapper.map(event, EventDto.class);
    }
    public Event convertDtoToEntity(EventDto dto){
        return mapper.map(dto, Event.class);
    }
    public TicketDTO convertEntityToDto(Ticket event){
        return mapper.map(event, TicketDTO.class);
    }
    public Ticket convertDtoToEntity(TicketDTO dto){
        return mapper.map(dto, Ticket.class);
    }
    public UserDTO convertEntityToDto(User user){
        return mapper.map(user, UserDTO.class);
    }
    public User convertDtoToEntity(UserDTO user){
        return mapper.map(user, User.class);
    }
    public UserAccountDTO convertEntityToDto(UserAccount user){
        return mapper.map(user, UserAccountDTO.class);
    }
    public UserAccount convertDtoToEntity(UserAccountDTO user){
        return mapper.map(user, UserAccount.class);
    }















    public List<TicketDTO> convertEntityTicketsToDto(List<Ticket> tickets){
        ArrayList<TicketDTO> ticketDTOS = new ArrayList<>();
        tickets.forEach((event)->ticketDTOS.add(mapper.map(event, TicketDTO.class)));
        return ticketDTOS;
    }
    public List<EventDto> convertEntityEventsToDto(List<Event> events){
        ArrayList<EventDto> eventDtos = new ArrayList<>();
        events.forEach((event)->eventDtos.add(mapper.map(event, EventDto.class)));
        return eventDtos;
    }
    public List<UserDTO> convertEntityUsersToDto(List<User> users){
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        users.forEach((user)->userDTOS.add(mapper.map(user, UserDTO.class)));
        return userDTOS;
    }
    public List<UserAccountDTO> convertEntityUserAccountsToDto(List<UserAccount> users){
        ArrayList<UserAccountDTO> userDTOS = new ArrayList<>();
        users.forEach((user)->userDTOS.add(mapper.map(user, UserAccountDTO.class)));
        return userDTOS;
    }
}
