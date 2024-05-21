package javamon.backend.exceptions;

public class CannotEvolveException extends GameException {
    public CannotEvolveException() {
        super("Cannot evolve");
    }

    public CannotEvolveException(String message) {
        super(message);
    }
}
