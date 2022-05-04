package repository;

import models.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryLike {
    private final List<User> users;

    public UserRepositoryLike() {
        this.users = init();
    }

    public List<User> getUsers() {
        return users;
    }

    private List<User> init() {
        List<User> users = new ArrayList<>();
        users.add(new User("Oleh", "I", LocalDate.of(1990, 12, 10)));
        users.add(new User("Ivan", "I", LocalDate.of(1999, 10, 10)));
        users.add(new User("Gena", "I", LocalDate.of(1990, 4, 10)));
        users.add(new User("Ola", "I", LocalDate.of(1990, 5, 10)));
        users.add(new User("Andriy", "I", LocalDate.of(1994, 2, 10)));
        users.add(new User("Andy", "I", LocalDate.of(1991, 2, 10)));
        users.add(new User("Kate", "I", LocalDate.of(1990, 3, 10)));
        return users;
    }

}
