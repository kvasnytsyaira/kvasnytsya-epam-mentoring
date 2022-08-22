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
public class Ticket{

    public enum Category {STANDARD, PREMIUM, BAR}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    long eventId;
    long userId;
    Category category;
    @NotNull(message = "Place is mandatory")
    int place;


    public Ticket(long eventId, long userId, Category category, int place) {
        this.eventId = eventId;
        this.userId = userId;
        this.category = category;
        this.place = place;
    }

    @Override
    public String toString() {
        return "{ id=" + id +
                ", eventId=" + eventId +
                ", userId=" + userId +
                ", category=" + category +
                ", place=" + place +
                '}';
    }
}
