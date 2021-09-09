package org.leogenwp.service;

import org.junit.jupiter.api.Test;
import org.leogenwp.model.User;
import org.leogenwp.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Test
    void save() {
        User user = new User();
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService_underTest = new UserService(userRepository);
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user,userService_underTest.save(user));
    }

    @Test
    void getById() {
        User user = new User();
        user.setId(1);
        user.setName("go");
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService_underTest = new UserService(userRepository);
        when(userService_underTest.getById(1)).thenReturn(user);
        assertEquals(user,userService_underTest.getById(1));
    }

    @Test
    void getAll() {
        List<User> users = getUserList();
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService_underTest = new UserService(userRepository);
        when(userService_underTest.getAll()).thenReturn(users);
        assertEquals(users,userService_underTest.getAll());
    }

    @Test
    void getByLogin() {
        User user = new User();
        user.setId(1);
        user.setLogin("Bob");
        user.setName("go");
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService_underTest = new UserService(userRepository);
        when(userService_underTest.getByLogin("Bob")).thenReturn(user);
        assertEquals(user,userService_underTest.getByLogin("Bob"));
    }

    @Test
    void update() {
        User user = new User();
        user.setId(1);
        user.setName("go");
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService_underTest = new UserService(userRepository);
        when(userService_underTest.update(user)).thenReturn(user);
        assertEquals(user,userService_underTest.update(user));
    }

    @Test
    void deleteById() {
        UserService userService_underTest = mock(UserService.class);
        userService_underTest.deleteById(5);
        verify(userService_underTest).deleteById(5);
    }

    private List<User> getUserList() {
        List<User> users = Arrays.asList(
                new User(),
                new User()
        );
        return users;
    }
}