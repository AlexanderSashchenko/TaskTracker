package com.inmost.tasktracker.validation;

import com.inmost.tasktracker.validation.impl.StatusValidatorImpl;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = StatusValidatorImpl.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StatusValidator {
    String message() default "Status should be empty or one of these:"
            + " 'View', 'In Progress', 'Done'!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
