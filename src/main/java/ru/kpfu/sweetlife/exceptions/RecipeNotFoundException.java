package ru.kpfu.sweetlife.exceptions;

public class RecipeNotFoundException extends ApplicationException {

    private static final String DEFAULT_MESSAGE = "Recipe not found.";

    public RecipeNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public RecipeNotFoundException(String message) {
        super(message);
    }

    public RecipeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecipeNotFoundException(Throwable cause) {
        super(cause);
    }
}
