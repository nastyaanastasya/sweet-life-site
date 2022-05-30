package ru.kpfu.sweetlife.exceptions;

public class DuplicateEmailException extends ApplicationException {

    private static final String DEFAULT_MESSAGE = "User with such email already registered";

    public DuplicateEmailException() {
        super(DEFAULT_MESSAGE);
    }

    public DuplicateEmailException(String message) {
        super(message);
    }

    public DuplicateEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateEmailException(Throwable cause) {
        super(cause);
    }
}
