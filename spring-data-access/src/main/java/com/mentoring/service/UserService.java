package com.mentoring.service;

import com.mentoring.model.User;

import java.util.List;

public interface UserService {

    User getUserById(long userId);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name, long pageSize, long pageNum);

    User createUser(User user);

    User updateUser(long id, User user);

    void deleteUser(long userId);
}
