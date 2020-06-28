package com.inmost.tasktracker.validation.impl;

import com.inmost.tasktracker.validation.SortingTypeValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SortingTypeValidatorImpl
        implements ConstraintValidator<SortingTypeValidator, String> {
    private static final String ASC = "asc";
    private static final String DESC = "desc";

    @Override
    public boolean isValid(String sortingType,
                           ConstraintValidatorContext constraintValidatorContext) {
        return sortingType.toLowerCase().equals(ASC) || sortingType.toLowerCase().equals(DESC);
    }
}
