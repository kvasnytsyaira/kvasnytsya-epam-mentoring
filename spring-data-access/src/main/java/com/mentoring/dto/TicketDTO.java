package com.mentoring.dto;

import com.mentoring.model.Event;
import com.mentoring.model.Ticket;
import com.mentoring.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {

    long id;

    @NotNull(message = "Event id is mandatory")
    long eventId;

    @NotNull(message = "User id is mandatory")
    long userId;

    Ticket.Category category;

    @NotNull(message = "Place is mandatory")
    int place;


}
