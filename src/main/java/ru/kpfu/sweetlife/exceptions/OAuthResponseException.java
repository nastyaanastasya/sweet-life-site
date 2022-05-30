package ru.kpfu.sweetlife.exceptions;

public class OAuthResponseException extends ApplicationException {

    private static final String DEFAULT_MESSAGE = "Unable to get response";

    public OAuthResponseException() {
        super(DEFAULT_MESSAGE);
    }

    public OAuthResponseException(String message) {
        super(message);
    }

    public OAuthResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    public OAuthResponseException(Throwable cause) {
        super(cause);
    }
}
