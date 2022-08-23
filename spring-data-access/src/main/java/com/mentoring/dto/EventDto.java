package com.mentoring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    long id;

    @NotBlank(message = "Title is mandatory")
    String title;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Future(message = "You cannot create an event in the past!")
    LocalDate date;

    int price;


}
