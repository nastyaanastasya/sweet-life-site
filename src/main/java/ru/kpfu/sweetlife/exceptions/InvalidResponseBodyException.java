package ru.kpfu.sweetlife.exceptions;

public class InvalidResponseBodyException extends ApplicationException{

    private static final String DEFAULT_MESSAGE = "Invalid response body";

    public InvalidResponseBodyException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidResponseBodyException(String message) {
        super(message);
    }

    public InvalidResponseBodyException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidResponseBodyException(Throwable cause) {
        super(cause);
    }
}
