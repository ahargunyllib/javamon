package javamon.backend.exceptions;

public class TooMuchMonsterException extends GameException {
    public TooMuchMonsterException(String message) {
        super(message);
    }

    public String getMessage() {
        return super.message;
    }
}
