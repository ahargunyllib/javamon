package javamon.backend.exceptions;

public class NoArgumentException extends GameException {
    public NoArgumentException(){
        super("No argument provided");
    }

    public NoArgumentException(String message){
        super(String.format("No %s provided", message));
    }
}
