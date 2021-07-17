package com.bobrove.ws.mobileappws.ws.ui.exception;

import com.bobrove.ws.mobileappws.ws.ui.model.response.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
@ResponseBody
public class ErrorHandlingControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        return new ValidationErrorResponse(ErrorMessage.WRONG_FIELD_VALUE,
                e.getConstraintViolations().stream().map(constraintViolation ->
                    new ValidationErrorResponse.Violation(
                        constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getMessage()))
                    .collect(Collectors.toList()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ValidationErrorResponse(ErrorMessage.WRONG_FIELD_VALUE,
                e.getBindingResult().getFieldErrors().stream().map(fieldError ->
                        new ValidationErrorResponse.Violation(
                                fieldError.getField(),
                                fieldError.getDefaultMessage()))
                        .collect(Collectors.toList()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse onUsernameNotFoundException(UsernameNotFoundException e) {
        return new ValidationErrorResponse(String.format(ErrorMessage.NO_RECORD_FOUND.getErrorMessage(), e.getMessage()));
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse handleAnyException(Exception e) {
        return new ErrorResponse(ErrorMessage.INTERNAL_SERVER_ERROR);
    }

}
