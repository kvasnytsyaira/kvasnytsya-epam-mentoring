package com.mentoring;

import com.mentoring.config.WebConfig;
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
        ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
        BookingFacadeImpl bean = context.getBean(BookingFacadeImpl.class);
        System.out.println(bean);
        System.out.println("************************************************************************");
        System.out.println("************************************************************************");
        System.out.println("************************************************************************");
        System.out.println("************************************************************************");
        System.out.println("************************************************************************");
        System.out.println("************************************************************************");
        System.out.println("************************************************************************");
        System.out.println("************************************************************************");
        System.out.println("************************************************************************");
        System.out.println("************************************************************************");
        System.out.println("************************************************************************");
        System.out.println("************************************************************************");
        System.out.println("************************************************************************");
        System.out.println("************************************************************************");
        System.out.println("************************************************************************");
        System.out.println("************************************************************************");

    }


}
