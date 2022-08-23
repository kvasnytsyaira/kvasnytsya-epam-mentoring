package com.mentoring.model;

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
@Entity
@Table(name = "tickets")
public class Ticket {

    public enum Category {STANDARD, PREMIUM, BAR}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @ManyToOne
    @JoinColumn(name = "event_id")
    Event event;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @Enumerated
    Category category;
    int place;


    public Ticket(Event event, User user, Category category, int place) {
        this.event = event;
        this.user = user;
        this.category = category;
        this.place = place;
    }
}
