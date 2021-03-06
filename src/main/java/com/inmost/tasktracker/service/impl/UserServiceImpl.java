package com.inmost.tasktracker.service.impl;

import com.inmost.tasktracker.exception.DataProcessingException;
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
    public User getById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new DataProcessingException("Failed to find a user with id: " + userId));
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                new DataProcessingException("Failed to find a user with username: " + username));
    }

    @Override
    public List<User> getAll(int pageNumber) {
        return userRepository.findAll(PageRequest.of(pageNumber, PAGE_SIZE)).getContent();
    }

    @Override
    public User update(User user) {
        if (userRepository.findById(user.getUserId()).isPresent()) {
            return userRepository.save(user);
        } else {
            throw new DataProcessingException("Failed to find a user with id: " + user.getUserId());
        }
    }

    @Override
    public void delete(long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
        } else {
            throw new DataProcessingException("Failed to find a user with id: " + userId);
        }
    }
}
