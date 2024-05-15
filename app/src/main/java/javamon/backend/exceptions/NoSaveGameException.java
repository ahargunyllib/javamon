package javamon.backend.exceptions;

public class NoSaveGameException extends GameException {
    public NoSaveGameException(String message) {
        super(message);
    }

    public String getMessage() {
        return super.message;
    }
}
