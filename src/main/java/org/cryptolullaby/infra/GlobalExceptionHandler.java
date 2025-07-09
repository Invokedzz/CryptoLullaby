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
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity <ExceptionDTO> handleNotFoundException (ResourceNotFoundException ex) {

        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity <ExceptionDTO> handleMethodArgumentNotValidException (MethodArgumentNotValidException ex) {

        String msg = getBindingResultDefaultMessage(ex);

        return buildResponse(HttpStatus.BAD_REQUEST, msg);

    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity <ExceptionDTO> handlePayloadTooLargeException (MaxUploadSizeExceededException ex) {

        return buildResponse(HttpStatus.PAYLOAD_TOO_LARGE, ex.getMessage());

    }

    @ExceptionHandler(UnauthorizedRequestException.class)
    public ResponseEntity <ExceptionDTO> handleUnauthorizedException (UnauthorizedRequestException ex) {

        return buildResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());

    }

    @ExceptionHandler(BadGatewayException.class)
    public ResponseEntity <ExceptionDTO> handleBadGatewayException (BadGatewayException ex) {

        return buildResponse(HttpStatus.BAD_GATEWAY, ex.getMessage());

    }

    @ExceptionHandler(GatewayTimeoutException.class)
    public ResponseEntity <ExceptionDTO> handleGatewayTimeoutException (GatewayTimeoutException ex) {

        return buildResponse(HttpStatus.GATEWAY_TIMEOUT, ex.getMessage());

    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity <ExceptionDTO> handleUnprocessableEntityException (UnprocessableEntityException ex) {

        return buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());

    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity <ExceptionDTO> handleSpringNotFoundException (NoResourceFoundException ex) {

        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());

    }

    @ExceptionHandler(FeignException.Forbidden.class)
    public ResponseEntity <ExceptionDTO> handleFeignForbiddenException (FeignException.Forbidden ex) {

        return buildResponse(HttpStatus.FORBIDDEN, ex.getMessage());

    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity <ExceptionDTO> handleIllegalStateException (IllegalStateException ex) {

        return buildResponse(HttpStatus.FORBIDDEN, ex.getMessage());

    }

    @ExceptionHandler({

            MethodArgumentTypeMismatchException.class,
            UnexpectedTypeException.class,
            MissingServletRequestParameterException.class,
            ConstraintViolationException.class,
            BadRequestException.class,
            HttpMessageNotReadableException.class,

    })
    public ResponseEntity <ExceptionDTO> handleBadRequestException (Exception ex) {

        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());

    }

    @ExceptionHandler({ClassCastException.class, InternalServerException.class})
    public ResponseEntity <ExceptionDTO> handleInternalServerErrorException (Exception ex) {

        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());

    }

    private ResponseEntity <ExceptionDTO> buildResponse (HttpStatus status, String message) {

        ExceptionDTO exception = new ExceptionDTO(

                status.value(),
                message,
                LocalDateTime.now()

        );

        return new ResponseEntity<>(exception, status);

    }

    private String getBindingResultDefaultMessage (MethodArgumentNotValidException ex) {

        return ex.getBindingResult()
                 .getFieldErrors()
                 .stream()
                 .map(FieldError::getDefaultMessage)
                 .collect(Collectors.joining(", "));

    }

}
