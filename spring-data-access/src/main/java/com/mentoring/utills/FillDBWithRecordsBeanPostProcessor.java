//package com.mentoring.utills;
//
//import com.mentoring.model.Event;
//import com.mentoring.model.Ticket;
//import com.mentoring.model.User;
//import com.mentoring.model.UserAccount;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//public class FillDBWithRecordsBeanPostProcessor implements BeanPostProcessor {
//    List<User> users = new ArrayList<>();
//    List<UserAccount> accounts = new ArrayList<>();
//    List<Event> events = new ArrayList<>();
//    List<Ticket> tickets = new ArrayList<>();
//    private User user1, user2, user3, user4;
//    private Event event1, event2, event3, event4, event5, event6;
//
//    @Override
//    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
//        return o;
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        Class<?> targetClass = bean.getClass();
//        if (beanName.equals("eventRepository")) {
//
//
//        }
//        createUsers();
//        createUserAccounts();
//        createEvents();
//        createTickets();
//        return bean;
//    }
//
//    private void createUsers() {
//        user1 = new User(1, "Oleh", "email@1");
//        user2 = new User(2, "James", "email@2");
//        user3 = new User(3, "Stas", "email@3");
//        user4 = new User(4, "Taras", "email@4");
//        users.add(user1);
//        users.add(user2);
//        users.add(user3);
//        users.add(user4);
//    }
//
//    private void createUserAccounts() {
//        UserAccount userAccount1 = new UserAccount(5, user1, 100);
//        UserAccount userAccount2 = new UserAccount(6, user2, 200);
//        UserAccount userAccount3 = new UserAccount(7, user3, 30);
//        UserAccount userAccount4 = new UserAccount(8, user4, 500);
//        accounts.add(userAccount1);
//        accounts.add(userAccount2);
//        accounts.add(userAccount3);
//        accounts.add(userAccount4);
//    }
//
//
//    private void createEvents() {
//        Event event1 = new Event("1 Concert", LocalDate.of(2023, 1, 1), 12);
//        Event event2 = new Event("2 Concert", LocalDate.of(2023, 1, 2), 42);
//        Event event3 = new Event("3 Concert", LocalDate.of(2023, 1, 3), 2);
//        Event event4 = new Event("4 Concert", LocalDate.of(2023, 1, 4), 50);
//        Event event5 = new Event("5 Concert", LocalDate.of(2023, 1, 5), 15);
//        Event event6 = new Event("6 Concert", LocalDate.of(2023, 1, 6), 10);
//        events.add(event1);
//        events.add(event2);
//        events.add(event3);
//        events.add(event4);
//        events.add(event5);
//        events.add(event6);
//    }
//
//
//    private void createTickets() {
//        Ticket ticket1 = new Ticket(event1, user1, Ticket.Category.BAR, 1);
//        Ticket ticket2 = new Ticket(event2, user1, Ticket.Category.BAR, 1);
//        Ticket ticket3 = new Ticket(event3, user1, Ticket.Category.BAR, 1);
//        Ticket ticket4 = new Ticket(event4, user1, Ticket.Category.BAR, 1);
//        Ticket ticket5 = new Ticket(event6, user2, Ticket.Category.STANDARD, 31);
//        Ticket ticket6 = new Ticket(event4, user2, Ticket.Category.BAR, 21);
//        Ticket ticket7 = new Ticket(event5, user3, Ticket.Category.PREMIUM, 11);
//        Ticket ticket8 = new Ticket(event6, user4, Ticket.Category.PREMIUM, 51);
//        tickets.add(ticket1);
//        tickets.add(ticket2);
//        tickets.add(ticket3);
//        tickets.add(ticket4);
//        tickets.add(ticket5);
//        tickets.add(ticket6);
//        tickets.add(ticket7);
//        tickets.add(ticket8);
//    }
//
//}
