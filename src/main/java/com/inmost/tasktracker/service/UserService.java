package com.inmost.tasktracker.service;

import com.inmost.tasktracker.model.User;
import java.util.List;

public interface UserService {
    User get(long userId);

    List<User> getAll(int pageNumber);

    User update(User user);

    void delete(long userId);
}
