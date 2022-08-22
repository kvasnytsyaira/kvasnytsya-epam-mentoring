package com.mentoring.service;

import com.mentoring.exception.RecordNotFoundException;
import com.mentoring.exception.RecordsNotFoundForSearchCriteriaException;
import com.mentoring.model.User;
import com.mentoring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public User getUserById(long userId) {

        return userRepository.findById(userId)
                .orElseThrow(() -> new RecordNotFoundException("User with such id does not exist!"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findAll()
                .stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findAny()
                .orElseThrow(() -> new RecordsNotFoundForSearchCriteriaException("User with such email does not exist!"));
    }

    @Override
    public List<User> getUsersByName(String name, long pageSize, long pageNum) {
        List<User> users = userRepository.findAll()
                .stream()
                .filter(user -> user.getName().equals(name))
                .skip((pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
        if (users.size() > 0) {
            return users;
        } else throw new RecordsNotFoundForSearchCriteriaException("User with such email does not exist!");

    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(long id, User userNew) {
        User user = userRepository.findById(id).get();
        user.setName(userNew.getName());
        user.setEmail(userNew.getEmail());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }
}
