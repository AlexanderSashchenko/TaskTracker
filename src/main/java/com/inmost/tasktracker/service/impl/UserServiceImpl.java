package com.inmost.tasktracker.service.impl;

import com.inmost.tasktracker.model.User;
import com.inmost.tasktracker.repository.UserRepository;
import com.inmost.tasktracker.service.UserService;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final int PAGE_SIZE = 10;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User get(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("Failed to find a user with id: " + userId));
    }

    @Override
    public void delete(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getAll(int pageNumber) {
        return userRepository.findAll(PageRequest.of(pageNumber, PAGE_SIZE)).getContent();
    }
}
