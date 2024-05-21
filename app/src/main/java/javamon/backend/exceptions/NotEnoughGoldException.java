package javamon.backend.exceptions;

public class NotEnoughGoldException extends GameException {
    public NotEnoughGoldException() {
        super("Not enough gold");
    }

    public NotEnoughGoldException(String message) {
        super(message);
    }
}
