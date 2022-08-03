package mentoring.config;


import mentoring.facade.BookingFacadeImpl;
import mentoring.service.EventServiceImpl;
import mentoring.service.TicketServiceImpl;
import mentoring.service.UserServiceImpl;
import mentoring.storingData.Storage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "mentoring")
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
