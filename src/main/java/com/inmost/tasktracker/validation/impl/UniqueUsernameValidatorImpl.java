package com.inmost.tasktracker.validation.impl;

import com.inmost.tasktracker.repository.UserRepository;
import com.inmost.tasktracker.validation.UniqueUsernameValidator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidatorImpl
        implements ConstraintValidator<UniqueUsernameValidator, String> {
    private final UserRepository userRepository;

    public UniqueUsernameValidatorImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String username,
                           ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findByUsername(username).isEmpty();
    }
}
