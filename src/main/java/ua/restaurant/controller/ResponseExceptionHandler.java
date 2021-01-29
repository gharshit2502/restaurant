package ua.restaurant.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {
            IllegalArgumentException.class,
            IllegalStateException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleMethodArgumentNotValid(ex, headers, status, request);
//
//        List<String> errors = new ArrayList<String>();
//        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//            errors.add(error.getField() + ": " + error.getDefaultMessage());
//        }
//        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
//            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
//        }
//
//        ApiError apiError =
//                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
//        return handleExceptionInternal(
//                ex, apiError, headers, apiError.getStatus(), request);
//    }
}
