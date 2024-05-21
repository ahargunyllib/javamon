package javamon.backend.exceptions;

public class CannotHealException extends GameException {
    public CannotHealException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.message;
    }
    
}
