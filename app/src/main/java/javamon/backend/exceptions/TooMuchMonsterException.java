package javamon.backend.exceptions;

public class TooMuchMonsterException extends GameException {
    public TooMuchMonsterException() {
        super("Too much monster");
    }
    
    public TooMuchMonsterException(String message) {
        super(message);
    }
}
