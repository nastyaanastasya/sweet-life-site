package ru.kpfu.sweetlife.exceptions;

public class ImpossibleToUploadMediaException extends ApplicationException {

    private static final String DEFAULT_MESSAGE = "Unable to upload media.";

    public ImpossibleToUploadMediaException() {
        super(DEFAULT_MESSAGE);
    }

    public ImpossibleToUploadMediaException(String message) {
        super(message);
    }

    public ImpossibleToUploadMediaException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImpossibleToUploadMediaException(Throwable cause) {
        super(cause);
    }
}
