package javamon.backend.exceptions;

public class FullInventoryException extends GameException {
    public FullInventoryException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.message;
    }
}
