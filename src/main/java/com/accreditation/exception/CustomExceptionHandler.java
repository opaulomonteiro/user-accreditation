package com.accreditation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleException(MethodArgumentNotValidException ex) {

        List<FieldError> errors = ex.getBindingResult().getFieldErrors();

        List<ErrorResponse.ErrorDetails> errorDetails = new ArrayList<>();
        for (FieldError fieldError : errors) {
            ErrorResponse.ErrorDetails error = new ErrorResponse.ErrorDetails();
            error.setFieldName(fieldError.getField());
            error.setMessage(fieldError.getDefaultMessage());
            errorDetails.add(error);
        }

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrors(errorDetails);

        return errorResponse;
    }

    @ExceptionHandler(InvalidPayloadException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleInvalidPayloadException(InvalidPayloadException invalidPayloadException) {
        List<ErrorResponse.ErrorDetails> errorDetails = new ArrayList<>();
        ErrorResponse.ErrorDetails error = new ErrorResponse.ErrorDetails();
        error.setFieldName(invalidPayloadException.getField());
        error.setMessage(invalidPayloadException.getMessage());
        errorDetails.add(error);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrors(errorDetails);
        return errorResponse;
    }
}
