package ru.kpfu.sweetlife.exceptions;

public class CommentNotFoundException extends ApplicationException{
    private final static String DEFAULT_MESSAGE = "Comments not found";

    public CommentNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public CommentNotFoundException(String message) {
        super(message);
    }

    public CommentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommentNotFoundException(Throwable cause) {
        super(cause);
    }
}
