package com.example.demo.web.controller.ExceptionHandler;

import com.example.demo.web.controller.RestPreConditions.MyExceptions.APIError;
import com.example.demo.web.controller.RestPreConditions.MyExceptions.MyBadRequestException;
import com.google.common.base.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, message(HttpStatus.BAD_REQUEST, ex), headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, message(HttpStatus.BAD_REQUEST, ex), headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class, MyBadRequestException.class})
    public ResponseEntity<Object> handleBadRequest (RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, message(HttpStatus.BAD_REQUEST, ex), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private APIError message(HttpStatus httpStatus, Exception ex) {

        final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
        final String devMessage = Throwables.getRootCause(ex).toString();

        return new APIError(httpStatus.value(), message, devMessage);

    }

}
