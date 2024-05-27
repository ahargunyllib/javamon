package javamon.backend.exceptions;

public class TooMuchArgumentException extends GameException {
    public TooMuchArgumentException(){
        super("Too much argument provided");
    }

    public TooMuchArgumentException(String message){
        super(String.format("Too much %s provided", message));
    }
}
