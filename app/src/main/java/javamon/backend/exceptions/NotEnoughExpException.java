package javamon.backend.exceptions;

public class NotEnoughExpException extends GameException {
    public NotEnoughExpException() {
        super("Not enough exp");
    }

    
    public NotEnoughExpException(String message) {
        super(message);
    }
}
