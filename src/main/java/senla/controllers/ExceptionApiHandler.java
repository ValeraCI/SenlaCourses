package senla.controllers;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import senla.exceptions.DataBaseWorkException;
import senla.exceptions.DataChangesException;

import javax.persistence.NoResultException;

@RestControllerAdvice
public class ExceptionApiHandler extends ResponseEntityExceptionHandler {

   @ExceptionHandler(DataBaseWorkException.class)
    public ResponseEntity<String> dataBaseWorkException(DataBaseWorkException exception) {
       return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
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
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getLocalizedMessage());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> NoResultException(NoResultException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getLocalizedMessage());
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<String> noResultException(NoResultException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getLocalizedMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> Exception(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }
}
