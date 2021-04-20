package com.sherut.exceptions;

import com.sherut.models.ResourceModels.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException badRequestException) {
        return ResponseEntity.badRequest().body(new ErrorResponse(badRequestException.getMessage()));
    }
}
