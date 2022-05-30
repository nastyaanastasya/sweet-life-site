package ru.kpfu.sweetlife.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<Username, String> {

    private final String USERNAME_REGEX = "^[a-zA-Z0-9_\\\\.]+$";

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return username.trim().matches(USERNAME_REGEX);
    }
}
