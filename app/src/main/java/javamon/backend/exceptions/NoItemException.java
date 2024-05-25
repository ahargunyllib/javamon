package javamon.backend.exceptions;

public class NoItemException extends GameException {
    public NoItemException() {
        super("No item found");
    }

    public NoItemException(String message) {
        super(message);
    }
}
