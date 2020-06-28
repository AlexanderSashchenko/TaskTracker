package com.inmost.tasktracker.validation.impl;

import com.inmost.tasktracker.validation.StatusValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StatusValidatorImpl implements ConstraintValidator<StatusValidator, String> {
    private static final String VIEW = "view";
    private static final String IN_PROGRESS = "in progress";
    private static final String DONE = "done";

    @Override
    public boolean isValid(String statusName,
                           ConstraintValidatorContext constraintValidatorContext) {
        return statusName == null || statusName.toLowerCase().equals(VIEW)
                || statusName.toLowerCase().equals(IN_PROGRESS)
                || statusName.toLowerCase().equals(DONE);
    }
}
