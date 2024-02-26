package com.challenge_8.challenge_8.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.challenge_8.challenge_8.utils.ResponseHandler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
class InputError {
    public String field;
    public String message;
}

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
            WebRequest request) {
        List<InputError> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            InputError errObj = new InputError(fieldName, message);
            errors.add(errObj);
        });
        return ResponseHandler.generateResponseFailed(HttpStatus.BAD_REQUEST,
                "Value of some fields doesn't match the requirements.",
                errors);
    }

    @ExceptionHandler(value = { TypeMismatchException.class })
    public ResponseEntity<Object> handleTypeMismatchException(TypeMismatchException ex, WebRequest request) {
        return ResponseHandler.generateResponseFailed(HttpStatus.BAD_REQUEST,
                "Value of params '" + ex.getPropertyName() + "'' should be "
                        + ex.getRequiredType().getSimpleName());
    }

    @ExceptionHandler(value = { SignatureException.class })
    public ResponseEntity<Object> handleUserServiceException(SignatureException ex, WebRequest request) {
        return ResponseHandler.generateResponseFailed(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(value = { ExpiredJwtException.class })
    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        // String requestUri = ((ServletWebRequest)
        // request).getRequest().getRequestURI().toString();
        return ResponseHandler.generateResponseFailed(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(value = { MalformedJwtException.class })
    public ResponseEntity<Object> handleMalformedJwtException(MalformedJwtException ex, WebRequest request) {
        return ResponseHandler.generateResponseFailed(HttpStatus.FORBIDDEN,
                "Your JWT token is invalid : " + ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> accessDeniedException(AccessDeniedException ex, WebRequest request)
            throws AccessDeniedException {
        return ResponseHandler.generateResponseFailed(HttpStatus.FORBIDDEN,
                "You don't have the required permission to access this resource");
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {
        log.error("Error : " + ex);
        return ResponseHandler.generateResponseFailed(HttpStatus.INTERNAL_SERVER_ERROR,
                "There is something wrong : " + ex.getMessage());
    }

}