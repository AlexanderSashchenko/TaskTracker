package com.inmost.tasktracker.service;

import com.inmost.tasktracker.model.User;
import java.util.List;

public interface UserService {
    User add(User user);

    User update(User user);

    User get(long userId);

    void delete(long userId);

    List<User> getAll(int pageNumber);
}
