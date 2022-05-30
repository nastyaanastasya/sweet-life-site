package ru.kpfu.sweetlife.exceptions;

public class ImageNotFoundException extends ApplicationException {

    private static final String DEFAULT_MESSAGE = "Image not found.";

    public ImageNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ImageNotFoundException(String message) {
        super(message);
    }

    public ImageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageNotFoundException(Throwable cause) {
        super(cause);
    }
}
