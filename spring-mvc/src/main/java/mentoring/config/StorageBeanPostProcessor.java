package mentoring.config;

import mentoring.model.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StorageBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        insertEvents();
        insertTickets();
        insertUsers();
        Class<?> targetClass = bean.getClass();
        if (beanName.equals("storage")) {
            Field[] fields = targetClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                System.out.println("Field " + field);
                if (field.getName().equals("events")) {
                    ReflectionUtils.setField(field, bean, eventsFull);
                }
                if (field.getName().equals("events")) {
                    ReflectionUtils.setField(field, bean, ticketsFull);
                }
                if (field.getName().equals("events")) {
                    ReflectionUtils.setField(field, bean, usersFull);
                }

            }
        }
        return bean;
    }

    Map<String, Event> eventsFull = new HashMap<>();
    Map<String, User> usersFull = new HashMap<>();
    Map<String, Ticket> ticketsFull = new HashMap<>();

    private void insertEvents() {
        Event event1 = new ConcertEvent(setEventId(), "1 Concert", new Date(2022, Calendar.JANUARY, 1));
        eventsFull.put("event:" + event1.getId(), event1);
        Event event2 = new ConcertEvent(setEventId(), "2 Concert", new Date(2022, Calendar.JANUARY, 2));
        eventsFull.put("event:" + event2.getId(), event2);
        Event event3 = new ConcertEvent(setEventId(), "3 Concert", new Date(2022, Calendar.JANUARY, 3));
        eventsFull.put("event:" + event3.getId(), event3);
        Event event4 = new ConcertEvent(setEventId(), "4 Concert", new Date(2022, Calendar.JANUARY, 4));
        eventsFull.put("event:" + event4.getId(), event4);
        Event event5 = new ConcertEvent(setEventId(), "5 Concert", new Date(2022, Calendar.JANUARY, 5));
        eventsFull.put("event:" + event5.getId(), event5);
        Event event6 = new ConcertEvent(setEventId(), "6 Concert", new Date(2022, Calendar.JANUARY, 6));
        eventsFull.put("event:" + event6.getId(), event6);
    }

    private void insertUsers() {
        User user1 = new AdultUser(setUserId(), "Name 1", "email@1");
        usersFull.put("user:" + user1.getId(), user1);
        User user2 = new AdultUser(setUserId(), "Name 2", "email@2");
        usersFull.put("user:" + user2.getId(), user2);
        User user3 = new AdultUser(setUserId(), "Name 3", "email@3");
        usersFull.put("user:" + user3.getId(), user3);
        User user4 = new AdultUser(setUserId(), "Name 4", "email@4");
        usersFull.put("user:" + user4.getId(), user4);
    }

    private void insertTickets() {
        Ticket ticket1 = new OneDayTicket(setTicketId(), 1, 1, Ticket.Category.BAR, 1);
        ticketsFull.put("ticket:" + ticket1.getId(), ticket1);
        Ticket ticket2 = new OneDayTicket(setTicketId(), 2, 1, Ticket.Category.BAR, 1);
        ticketsFull.put("ticket:" + ticket2.getId(), ticket2);
        Ticket ticket3 = new OneDayTicket(setTicketId(), 3, 1, Ticket.Category.BAR, 1);
        ticketsFull.put("ticket:" + ticket3.getId(), ticket3);
        Ticket ticket4 = new OneDayTicket(setTicketId(), 4, 1, Ticket.Category.BAR, 1);
        ticketsFull.put("ticket:" + ticket4.getId(), ticket4);
        Ticket ticket5 = new OneDayTicket(setTicketId(), 6, 2, Ticket.Category.STANDARD, 31);
        ticketsFull.put("ticket:" + ticket5.getId(), ticket5);
        Ticket ticket6 = new OneDayTicket(setTicketId(), 4, 2, Ticket.Category.BAR, 21);
        ticketsFull.put("ticket:" + ticket6.getId(), ticket6);
        Ticket ticket7 = new OneDayTicket(setTicketId(), 5, 3, Ticket.Category.PREMIUM, 11);
        ticketsFull.put("ticket:" + ticket7.getId(), ticket7);
        Ticket ticket8 = new OneDayTicket(setTicketId(), 6, 4, Ticket.Category.PREMIUM, 51);
        ticketsFull.put("ticket:" + ticket8.getId(), ticket8);
    }

    private long setTicketId() {
        return getTicketsFull().size();
    }

    private long setEventId() {
        return getEventsFull().size();
    }

    private long setUserId() {
        return getUsersFull().size();
    }

    public Map<String, Event> getEventsFull() {
        return eventsFull;
    }

    public Map<String, User> getUsersFull() {
        return usersFull;
    }

    public Map<String, Ticket> getTicketsFull() {
        return ticketsFull;
    }
}
