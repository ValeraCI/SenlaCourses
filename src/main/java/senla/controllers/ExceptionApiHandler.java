package senla.controllers;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import senla.exceptions.DataBaseWorkException;
import senla.exceptions.DataChangesException;
import senla.exceptions.InsufficientRightsException;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionApiHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataBaseWorkException.class)
    public ResponseEntity<String> dataBaseWorkException(DataBaseWorkException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getLocalizedMessage());
    }

    @ExceptionHandler(DataChangesException.class)
    public ResponseEntity<String> dataChangesException(DataChangesException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getLocalizedMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> mismatchException(MethodArgumentTypeMismatchException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getLocalizedMessage());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> expiredJwtException(ExpiredJwtException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getLocalizedMessage());
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<String> noResultException(NoResultException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getLocalizedMessage());
    }

    @ExceptionHandler(InsufficientRightsException.class)
    public ResponseEntity<String> insufficientRightsException(InsufficientRightsException exception) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(exception.getLocalizedMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<String> errors = exception.getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errors.toString());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> badCredentialsException(BadCredentialsException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getLocalizedMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception() {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Server exception");
    }
}
