package ru.kpfu.sweetlife.exceptions;

public class IngredientNotFoundException extends ApplicationException{

    private static final String DEFAULT_MESSAGE = "Ingredient not found.";

    public IngredientNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public IngredientNotFoundException(String message) {
        super(message);
    }

    public IngredientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public IngredientNotFoundException(Throwable cause) {
        super(cause);
    }
}
