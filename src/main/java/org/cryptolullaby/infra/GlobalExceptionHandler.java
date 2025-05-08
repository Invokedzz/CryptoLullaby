package org.cryptolullaby.infra;

import org.cryptolullaby.exception.BadRequestException;
import org.cryptolullaby.exception.ResourceNotFoundException;
import org.cryptolullaby.model.dto.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity <ExceptionDTO> handleNotFoundException (ResourceNotFoundException ex) {

        ExceptionDTO exception = new ExceptionDTO(

                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity <ExceptionDTO> httpMessageNotReadableException (HttpMessageNotReadableException ex) {

        ExceptionDTO exception = new ExceptionDTO(

                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity <ExceptionDTO> handleMethodArgumentNotValidException (MethodArgumentNotValidException ex) {

        FieldError fieldError = ex.getBindingResult().getFieldError();

        assert fieldError != null;

        ExceptionDTO exception = new ExceptionDTO(

                HttpStatus.BAD_REQUEST.value(),
                fieldError.getDefaultMessage(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity <ExceptionDTO> handleBadRequestException (BadRequestException ex) {

        ExceptionDTO exception = new ExceptionDTO(

                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);

    }

}
