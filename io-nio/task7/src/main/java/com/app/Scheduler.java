package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    @Autowired
    private MyService myService;

    @Scheduled(cron = "0 0 15 * * *")
    void scheduledTask() {
        myService.deleteExpired();
        System.out.println("Scheduled task done");
    }
}
