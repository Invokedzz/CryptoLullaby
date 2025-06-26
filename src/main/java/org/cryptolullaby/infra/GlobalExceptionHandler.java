package org.cryptolullaby.infra;

import feign.FeignException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import org.cryptolullaby.exception.*;
import org.cryptolullaby.model.dto.general.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.InputMismatchException;

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

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity <ExceptionDTO> handlePayloadTooLargeException (MaxUploadSizeExceededException ex) {

        ExceptionDTO exception = new ExceptionDTO(

                HttpStatus.PAYLOAD_TOO_LARGE.value(),
                ex.getMessage(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(exception, HttpStatus.PAYLOAD_TOO_LARGE);

    }

    @ExceptionHandler(UnauthorizedRequestException.class)
    public ResponseEntity <ExceptionDTO> handleUnauthorizedException (UnauthorizedRequestException ex) {

        ExceptionDTO exception = new ExceptionDTO(

                HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(exception, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(BadGatewayException.class)
    public ResponseEntity <ExceptionDTO> handleBadGatewayException (BadGatewayException ex) {

        ExceptionDTO exception = new ExceptionDTO(

                HttpStatus.BAD_GATEWAY.value(),
                ex.getMessage(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(exception, HttpStatus.BAD_GATEWAY);

    }

    @ExceptionHandler(GatewayTimeoutException.class)
    public ResponseEntity <ExceptionDTO> handleGatewayTimeoutException (GatewayTimeoutException ex) {

        ExceptionDTO exception = new ExceptionDTO(

                HttpStatus.GATEWAY_TIMEOUT.value(),
                ex.getMessage(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(exception, HttpStatus.GATEWAY_TIMEOUT);

    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity <ExceptionDTO> handleUnprocessableEntityException (UnprocessableEntityException ex) {

        ExceptionDTO exception = new ExceptionDTO(

                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                ex.getMessage(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(exception, HttpStatus.UNPROCESSABLE_ENTITY);

    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity <ExceptionDTO> handleSpringNotFoundException (NoResourceFoundException ex) {

        ExceptionDTO exception = new ExceptionDTO(

                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(FeignException.Forbidden.class)
    public ResponseEntity <ExceptionDTO> handleFeignForbiddenException (FeignException.Forbidden ex) {

        ExceptionDTO exception = new ExceptionDTO(

                HttpStatus.FORBIDDEN.value(),
                ex.getMessage(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(exception, HttpStatus.FORBIDDEN);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity <ExceptionDTO> handleConstraintViolationException (ConstraintViolationException ex) {

        ExceptionDTO exception = new ExceptionDTO(

                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity <ExceptionDTO> handleMissingServletRequestParameterException (MissingServletRequestParameterException ex) {

        ExceptionDTO exception = new ExceptionDTO(

                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity <ExceptionDTO> handleUnexpectedTypeException (UnexpectedTypeException ex) {

        ExceptionDTO exception = new ExceptionDTO(

                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity <ExceptionDTO> handleIllegalStateException (IllegalStateException ex) {

        ExceptionDTO exception = new ExceptionDTO(

                HttpStatus.FORBIDDEN.value(),
                ex.getMessage(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(exception, HttpStatus.FORBIDDEN);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity <ExceptionDTO> handleInputMismatchException (MethodArgumentTypeMismatchException ex) {

        ExceptionDTO exception = new ExceptionDTO(

                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);

    }

}
