package bookstore.application.validator.password;

import bookstore.application.validator.email.EmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {PasswordValidator.class})
@Target(ElementType.FIELD)
@Retention(RUNTIME)
public @interface ValidPassword {
    String message() default "Password must have at least 6 characters, at least a number and at least a special character";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
