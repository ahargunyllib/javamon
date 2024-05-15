package javamon.backend.exceptions;

public class NoMonsterException extends GameException {
    public NoMonsterException(String message) {
        super(message);
    }

    public String getMessage() {
        return super.message;
    }
}
