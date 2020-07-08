package com.inmost.tasktracker.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.inmost.tasktracker.exception.DataProcessingException;
import com.inmost.tasktracker.model.User;
import com.inmost.tasktracker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class UserServiceImplTest {
    private static final String USERNAME = "username";
    private static final String FAKE_USERNAME = "fakeUsername";
    private static final String PASSWORD = "123";
    private static final Long USER_ID = 1L;
    private static final Long FAKE_ID = 12L;
    private static final String EMAIL = "Email@email.com";
    private static final String UPDATED_EMAIL = "UpdEmail@email.com";
    private static final String FIRST_NAME = "FirstName";
    private static final String LAST_NAME = "LastName";
    private static final String UPDATED_FIRST_NAME = "UpdatedFirstName";
    private static final String UPDATED_LAST_NAME = "UpdatedLastName";
    private static final Integer PAGE_NUMBER = 0;
    private static final Integer EMPTY_PAGE_NUMBER = 10;
    private static final Integer PAGE_SIZE = 10;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User savedUser;
    private User updatedUser;
    private User fakeUser;
    private List<User> allUsers;
    private List<User> emptyList;

    @BeforeEach
    void setUp() {
        savedUser = new User();
        savedUser.setUserId(USER_ID);
        savedUser.setUsername(USERNAME);
        savedUser.setPassword(PASSWORD);
        savedUser.setEmail(EMAIL);
        savedUser.setFirstName(FIRST_NAME);
        savedUser.setLastName(LAST_NAME);

        updatedUser = new User();
        updatedUser.setUserId(USER_ID);
        updatedUser.setUsername(USERNAME);
        updatedUser.setPassword(PASSWORD);
        updatedUser.setEmail(UPDATED_EMAIL);
        updatedUser.setFirstName(UPDATED_FIRST_NAME);
        updatedUser.setLastName(UPDATED_LAST_NAME);

        fakeUser = new User();
        fakeUser.setUserId(FAKE_ID);
        fakeUser.setUsername(FAKE_USERNAME);
        fakeUser.setPassword(PASSWORD);
        fakeUser.setEmail(EMAIL);
        fakeUser.setFirstName(FIRST_NAME);
        fakeUser.setLastName(LAST_NAME);

        allUsers = new ArrayList<>();
        allUsers.add(savedUser);

        emptyList = new ArrayList<>();

        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(savedUser));
        when(userRepository.findById(FAKE_ID)).thenThrow(DataProcessingException.class);
        when(userRepository.findByUsername(USERNAME)).thenReturn(Optional.of(savedUser));
        when(userRepository.findByUsername(FAKE_USERNAME))
                .thenThrow(DataProcessingException.class);
        when(userRepository.findAll(PageRequest.of(PAGE_NUMBER, PAGE_SIZE)))
                .thenReturn(new PageImpl<>(allUsers));
        when(userRepository.findAll(PageRequest.of(EMPTY_PAGE_NUMBER, PAGE_SIZE)))
                .thenReturn(new PageImpl<>(emptyList));
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);
        when(userRepository.save(fakeUser)).thenThrow(DataProcessingException.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getById() {
        assertEquals(savedUser, userService.getById(USER_ID));
        assertThrows(DataProcessingException.class, () -> userService.getById(FAKE_ID));
    }

    @Test
    void getByUsername() {
        assertEquals(savedUser, userService.getByUsername(USERNAME));
        assertThrows(DataProcessingException.class, () -> userService.getByUsername(FAKE_USERNAME));
    }

    @Test
    void getAll() {
        assertEquals(allUsers, userService.getAll(PAGE_NUMBER));
        assertEquals(emptyList, userService.getAll(EMPTY_PAGE_NUMBER));
    }

    @Test
    void update() {
        assertEquals(updatedUser, userService.update(updatedUser));
        assertThrows(DataProcessingException.class, () -> userService.update(fakeUser));
    }
}
