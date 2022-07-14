package com.mentoring.web.controller;

import com.mentoring.facade.BookingFacade;
import com.mentoring.model.AdultUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@RestController
@WebServlet("/")
public class FacadeController extends HttpServlet {

//    private BookingFacade bookingFacade;
//
//    @Autowired
//    public void setBookingFacade(BookingFacade bookingFacade) {
//        this.bookingFacade = bookingFacade;
//    }

    @GetMapping
    public String hello() {
        System.out.println("controller");
//        bookingFacade.createUser(new AdultUser("Oleh", "olehemail@com"));
        return "Hello!";
    }

}
