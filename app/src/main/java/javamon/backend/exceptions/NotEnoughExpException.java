package javamon.backend.exceptions;

public class NotEnoughExpException extends GameException {
    public NotEnoughExpException(String message) {
        super(message);
    }

    public String getMessage() {
        return super.message;
    }
}
