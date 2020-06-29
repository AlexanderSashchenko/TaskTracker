package com.inmost.tasktracker.validation;

import com.inmost.tasktracker.validation.impl.UniqueUsernameValidatorImpl;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = UniqueUsernameValidatorImpl.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsernameValidator {
    String message() default "The username you entered is occupied. Try another one.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
