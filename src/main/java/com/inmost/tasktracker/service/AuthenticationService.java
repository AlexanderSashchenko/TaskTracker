package com.inmost.tasktracker.service;

import com.inmost.tasktracker.model.User;

public interface AuthenticationService {
    User add(User user);

    User login(User user);
}
