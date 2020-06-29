package com.inmost.tasktracker.service;

import com.inmost.tasktracker.model.User;
import java.util.List;

public interface UserService {
    User getById(long userId);

    User getByUsername(String username);

    List<User> getAll(int pageNumber);

    User update(User user);

    void delete(long userId);
}
