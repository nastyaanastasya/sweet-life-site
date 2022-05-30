package ru.kpfu.sweetlife.exceptions;

public class DuplicateUsernameException extends ApplicationException {

    private static final String DEFAULT_MESSAGE = "User with such nickname already registered";

    public DuplicateUsernameException() {
        super(DEFAULT_MESSAGE);
    }

    public DuplicateUsernameException(String message) {
        super(message);
    }

    public DuplicateUsernameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateUsernameException(Throwable cause) {
        super(cause);
    }
}