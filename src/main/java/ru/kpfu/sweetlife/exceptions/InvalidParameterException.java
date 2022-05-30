package ru.kpfu.sweetlife.exceptions;

public class InvalidParameterException extends ApplicationException {

    private static final String DEFAULT_MESSAGE = "Invalid request parameter";

    public InvalidParameterException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidParameterException(String message) {
        super(message);
    }

    public InvalidParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidParameterException(Throwable cause) {
        super(cause);
    }
}
