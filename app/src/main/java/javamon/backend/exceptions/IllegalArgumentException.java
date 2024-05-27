package javamon.backend.exceptions;

public class IllegalArgumentException extends GameException {
    public IllegalArgumentException(){
        super("Illegal argument provided");
    }

    public IllegalArgumentException(String message){
        super(String.format("Illegal %s provided", message));
    }
}
