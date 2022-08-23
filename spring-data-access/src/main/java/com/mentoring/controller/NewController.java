package com.mentoring.controller;

import com.mentoring.dto.TicketDTO;
import com.mentoring.dto.UserDTO;
import com.mentoring.facade.BookingFacade;
import com.mentoring.model.Ticket;
import com.mentoring.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.validation.ValidationException;
import java.util.List;
@RestController
@RequiredArgsConstructor
public class NewController {
    private final BookingFacade bookingFacade;

    @ExceptionHandler({ValidationException.class})
    public void handle() {
//
    }


}
