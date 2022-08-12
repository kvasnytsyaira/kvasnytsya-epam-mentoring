package mentoring.controller.exceptions;

public class RecordsNotFoundForSearchCriteriaException extends RuntimeException{
    public RecordsNotFoundForSearchCriteriaException() {
        super();
    }

    public RecordsNotFoundForSearchCriteriaException(String message, Throwable cause) {
        super(message, cause);
    }
    public RecordsNotFoundForSearchCriteriaException(String message) {
        super(message);
    }
    public RecordsNotFoundForSearchCriteriaException(Throwable cause) {
        super(cause);
    }
}
