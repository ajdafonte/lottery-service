package pt.caires.lottery.domain.exception;

public class NotFoundServiceException extends RuntimeException {

    public NotFoundServiceException(String message) {
        super(message);
    }

}
