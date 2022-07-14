package com.mentoring.service;

import com.mentoring.model.User;
import com.mentoring.storingData.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Storage storage;


    @Override
    public User getUserById(long userId) {
        return storage.getUsers().get("user:" + userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return storage.getUsers()
                .values()
                .stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findAny()
                .orElseGet(() -> null);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return storage.getUsers()
                .values()
                .stream()
                .filter(user -> user.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public User createUser(User user) {
        return storage.save(user);
    }

    @Override
    public User updateUser(User user) {
        return storage.save(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        return storage.removeUser(userId);
    }
}
