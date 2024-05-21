package javamon.backend.exceptions;

public class CannotHealException extends GameException {
    public CannotHealException() {
        super("Cannot heal");
    }

    public CannotHealException(String message) {
        super(message);
    }
}
