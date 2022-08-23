package com.mentoring.service;

import com.mentoring.dto.UserDTO;
import com.mentoring.exception.RecordNotFoundException;
import com.mentoring.exception.RecordsNotFoundForSearchCriteriaException;
import com.mentoring.model.User;
import com.mentoring.repository.UserRepository;
import com.mentoring.utills.MainUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MainUtil util;

    public UserDTO getUserDTOById(long userId) {
        return util.convertEntityToDto(userRepository.findById(userId)
                .orElseThrow(() -> new RecordNotFoundException("User with such id does not exist!")));
    }

    public User getUserById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RecordNotFoundException("User with such id does not exist!"));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return util.convertEntityToDto(userRepository.findAll()
                .stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findAny()
                .orElseThrow(() -> new RecordsNotFoundForSearchCriteriaException("User with such email does not exist!")));
    }

    @Override
    public List<UserDTO> getUsersByName(String name, long pageSize, long pageNum) {
        List<User> users = userRepository.findAll()
                .stream()
                .filter(user -> user.getName().equals(name))
                .skip((pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
        if (users.size() > 0) {
            return util.convertEntityUsersToDto(users);
        } else throw new RecordsNotFoundForSearchCriteriaException("User with such email does not exist!");

    }

    @Override
    public UserDTO createUser(UserDTO user) {
        return util.convertEntityToDto(userRepository.save(util.convertDtoToEntity(user)));
    }

    @Override
    public UserDTO updateUser(long id, UserDTO userNew) {
        User user = userRepository.findById(id).get();
        user.setName(userNew.getName());
        user.setEmail(userNew.getEmail());
        return util.convertEntityToDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }
}
