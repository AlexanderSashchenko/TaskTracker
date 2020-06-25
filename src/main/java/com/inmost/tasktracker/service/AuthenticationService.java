package com.inmost.tasktracker.service;

import com.inmost.tasktracker.model.User;

public interface AuthenticationService {
    User login(User user);
}
