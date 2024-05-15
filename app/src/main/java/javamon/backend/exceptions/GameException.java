package javamon.backend.exceptions;

public abstract class GameException extends Exception {
    protected String message;

    public GameException(String message) {
        this.message = message;
    }

    public abstract String getMessage();

}
