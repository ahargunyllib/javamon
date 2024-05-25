package javamon.backend.exceptions;

public class NoSaveFoundException extends GameException {
    public NoSaveFoundException() {
        super("No save file found");
    }

    public NoSaveFoundException(String message) {
        super(message);
    }
}
