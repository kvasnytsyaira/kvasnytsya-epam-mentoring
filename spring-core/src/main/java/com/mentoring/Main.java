package com.mentoring;

import com.mentoring.config.MainConfig;
import com.mentoring.facade.BookingFacade;
import com.mentoring.facade.BookingFacadeImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.mentoring")

public class Main {

    public static void main(String[] args) {
        init();
    }

    private static void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        BookingFacade bookingFacade = context.getBean(BookingFacadeImpl.class);

        System.out.println(bookingFacade.getEventById(1));
    }


}
