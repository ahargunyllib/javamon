package javamon.backend.exceptions;

public class NoSaveGameException extends GameException {
    public NoSaveGameException() {
        super("No save game");
    }

    public NoSaveGameException(String message) {
        super(message);
    }
}
