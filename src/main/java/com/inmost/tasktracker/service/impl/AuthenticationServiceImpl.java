package com.inmost.tasktracker.service.impl;

import com.inmost.tasktracker.model.User;
import com.inmost.tasktracker.repository.UserRepository;
import com.inmost.tasktracker.service.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User login(User user) {
        return null;
    }
}
