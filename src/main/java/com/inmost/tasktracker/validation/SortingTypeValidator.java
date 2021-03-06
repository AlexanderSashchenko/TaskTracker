package com.inmost.tasktracker.validation;

import com.inmost.tasktracker.validation.impl.SortingTypeValidatorImpl;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = SortingTypeValidatorImpl.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface SortingTypeValidator {
    String message() default "Sorting type parameter should be one of these:"
            + " 'asc' or 'desc' for ascending and descending orders respectively!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
