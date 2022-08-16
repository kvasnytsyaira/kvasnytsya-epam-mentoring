package mentoring.config;

import mentoring.facade.BookingFacadeImpl;
import mentoring.service.EventServiceImpl;
import mentoring.service.TicketServiceImpl;
import mentoring.service.UserServiceImpl;
import mentoring.storingData.Storage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "mentoring",
        excludeFilters = {
                            @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
                            @Filter(type = FilterType.ANNOTATION, value = Controller.class)})
public class RootConfig {

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
