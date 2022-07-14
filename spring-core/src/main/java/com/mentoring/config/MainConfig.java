package com.mentoring.config;

import com.mentoring.facade.BookingFacadeImpl;
import com.mentoring.service.EventServiceImpl;
import com.mentoring.service.TicketServiceImpl;
import com.mentoring.service.UserServiceImpl;
import com.mentoring.storingData.Storage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig {
    @Bean
    public Storage storage() {
        return new Storage();
    }

    @Bean
    public EventServiceImpl eventService() {
        return new EventServiceImpl();
    }

    @Bean
    public TicketServiceImpl ticketService() {
        return new TicketServiceImpl();
    }

    @Bean
    public UserServiceImpl userService() {
        return new UserServiceImpl();
    }

    @Bean
    public BookingFacadeImpl bookingFacade() {
        return new BookingFacadeImpl(eventService(), userService(), ticketService());
    }

    @Bean
    public StorageBeanPostProcessor storageBeanPostProcessor() {
        return new StorageBeanPostProcessor();
    }
}
