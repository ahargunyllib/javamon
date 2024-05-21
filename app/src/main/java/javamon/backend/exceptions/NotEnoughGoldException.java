package javamon.backend.exceptions;

public class NotEnoughGoldException extends GameException {
    public NotEnoughGoldException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.message;
    }
}
