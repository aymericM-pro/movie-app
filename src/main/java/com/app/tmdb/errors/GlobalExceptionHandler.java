package com.app.tmdb.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(
            BusinessException ex
    ) {
        ErrorType errorType = ex.getErrorType();
        ErrorResponse errorResponse = new ErrorResponse(
                errorType.getCode(),
                errorType.getMessage()
        );
        return new ResponseEntity<>(errorResponse, errorType.getHttpStatus());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("VALIDATION_ERROR", String.join("; ", ex.getViolations())));
    }
}
