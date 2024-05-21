package javamon.backend.exceptions;

public class CannotEvolveException extends GameException {
    public CannotEvolveException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.message;
    }
}
