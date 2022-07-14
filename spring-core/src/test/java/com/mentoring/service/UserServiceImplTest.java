package com.mentoring.service;

import com.mentoring.model.AdultUser;
import com.mentoring.model.Event;
import com.mentoring.model.Ticket;
import com.mentoring.model.User;
import com.mentoring.storingData.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    UserServiceImpl userService= new UserServiceImpl();
    Storage storageMock = new Storage();

    Map<String, User> testUsers= new LinkedHashMap<>();
    User user1, user2;


    @BeforeEach
    void setUp() {
        insertUsers();
        ReflectionTestUtils.setField(userService, "storage", storageMock);
        ReflectionTestUtils.setField(storageMock, "users", testUsers);
    }

    @Test
    void getUserById() {

        User actual = userService.getUserById(1);
        assertEquals(user1, actual);
    }

    @Test
    void getUserByEmail() {
        User actual = userService.getUserByEmail("email@1");
        assertEquals(user1, actual);
    }

    @Test
    void getUsersByName() {
        List<User> actual = userService.getUsersByName("Name 1", 1, 1);
        assertEquals(Collections.singletonList(user1), actual);
    }

    @Test
    void createUser() {
        User actual = userService.createUser(user1);
        assertEquals(user1, actual);
    }

    @Test
    void updateUser() {
        User actual = userService.updateUser(user2);
        assertEquals(user2, actual);
    }

    @Test
    void deleteUser() {
        boolean actual = userService.deleteUser(1);
        assertTrue(actual);
    }

    private void insertUsers() {
        user1 = new AdultUser(1, "Name 1", "email@1");
        testUsers.put("user:" + user1.getId(), user1);
        user2 = new AdultUser(2, "Name 2", "email@2");
        testUsers.put("user:" + user2.getId(), user2);
    }
}