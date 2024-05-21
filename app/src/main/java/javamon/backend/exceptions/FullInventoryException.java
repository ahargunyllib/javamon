package javamon.backend.exceptions;

public class FullInventoryException extends GameException {
    public FullInventoryException() {
        super("Inventory is full");
    }

    public FullInventoryException(String message) {
        super(message);
    }
}
