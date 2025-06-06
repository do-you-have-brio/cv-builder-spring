package dyhb.cv.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class Handler {
    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<FormatResponse> emailExistsException(EmailExistsException exception) {
        return createHttpResponse(HttpStatus.CONFLICT, exception.getMessage());
    }

    private ResponseEntity<FormatResponse> createHttpResponse(HttpStatus httpStatus, String message) {
    return new ResponseEntity<>
        (new FormatResponse(
                httpStatus.value(),
                httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(),
                message
        ),
                httpStatus
        );
    }
}
