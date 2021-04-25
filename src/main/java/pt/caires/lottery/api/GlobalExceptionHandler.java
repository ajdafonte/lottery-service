package pt.caires.lottery.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pt.caires.lottery.domain.exception.ConflictServiceException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_CONFLICT;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgumentException(IllegalArgumentException exception,
                                               HttpServletResponse response) throws IOException {
        LOGGER.warn("Illegal argument exception", exception);
        response.sendError(HTTP_BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(ConflictServiceException.class)
    public void handleConflictServiceException(ConflictServiceException exception,
                                               HttpServletResponse response) throws IOException {
        LOGGER.warn("Conflict exception", exception);
        response.sendError(HTTP_CONFLICT, exception.getMessage());
    }

}