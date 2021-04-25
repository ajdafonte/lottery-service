package pt.caires.lottery.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.caires.lottery.domain.exception.ConflictServiceException;
import pt.caires.lottery.domain.exception.NotFoundServiceException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_CONFLICT;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @Mock
    private HttpServletResponse response;

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        this.globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void should_handle_illegal_argument_exception() throws IOException {
        IllegalArgumentException exception = new IllegalArgumentException("message");

        globalExceptionHandler.handleIllegalArgumentException(exception, response);

        verify(response).sendError(HTTP_BAD_REQUEST, exception.getMessage());
    }

    @Test
    void should_handle_conflict_service_exception() throws IOException {
        ConflictServiceException exception = new ConflictServiceException("message");

        globalExceptionHandler.handleConflictServiceException(exception, response);

        verify(response).sendError(HTTP_CONFLICT, exception.getMessage());
    }

    @Test
    void should_handle_not_found_service_exception() throws IOException {
        NotFoundServiceException exception = new NotFoundServiceException("message");

        globalExceptionHandler.handleNotFoundServiceException(exception, response);

        verify(response).sendError(HTTP_NOT_FOUND, exception.getMessage());
    }

}