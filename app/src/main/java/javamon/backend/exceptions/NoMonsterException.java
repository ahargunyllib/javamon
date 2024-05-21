package javamon.backend.exceptions;

public class NoMonsterException extends GameException {
    public NoMonsterException() {
        super("No monster");
    }

    public NoMonsterException(String message) {
        super(message);
    }
}
