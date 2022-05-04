package exceptions;

public class IncorrectCardNumberException extends RuntimeException{
    public IncorrectCardNumberException(String message) {
        super(message);
    }
}
