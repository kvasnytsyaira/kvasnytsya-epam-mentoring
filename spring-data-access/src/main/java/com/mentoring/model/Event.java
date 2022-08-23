package com.mentoring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maksym_govorischev.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @NotBlank(message = "Title is mandatory")
    String title;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Future(message = "You cannot create an event in the past!")
            @Column(name = "event_date")
    LocalDate date;

    int price;

    @OneToMany
    List<Ticket> ticketList = new ArrayList<>();

}
