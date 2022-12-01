package team.asd.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AppError catchValidationException(ValidationException e) {
        log.error(e.getMessage(), e);
        return new AppError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AppError handleValidationExceptions(MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        log.error(message, e);
        return new AppError(HttpStatus.BAD_REQUEST.value(), message);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AppError handleValidationExceptions(ConstraintViolationException e) {
        String message = e.getMessage();
        log.error(message, e);
        return new AppError(HttpStatus.BAD_REQUEST.value(), message);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AppError catchException(Exception e) {
        log.error(e.getMessage(), e);
        return new AppError(HttpStatus.BAD_REQUEST.value(), "Something went wrong. ");
    }
}
