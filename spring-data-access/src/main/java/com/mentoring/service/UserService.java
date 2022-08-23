package com.mentoring.service;

import com.mentoring.dto.UserDTO;
import com.mentoring.model.User;

import java.util.List;

public interface UserService {

    User getUserById(long userId);
    UserDTO getUserDTOById(long userId);

    UserDTO getUserByEmail(String email);

    List<UserDTO> getUsersByName(String name, long pageSize, long pageNum);

    UserDTO createUser(UserDTO user);

    UserDTO updateUser(long id, UserDTO user);

    void deleteUser(long userId);
}
