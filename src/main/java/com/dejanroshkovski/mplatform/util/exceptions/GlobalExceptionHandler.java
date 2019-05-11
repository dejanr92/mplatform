package com.dejanroshkovski.mplatform.util.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;

// TODO Add possible logging implementation or mailsend for caught exceptions which will be internal server error 500

@ControllerAdvice
public class GlobalExceptionHandler {

    // TODO This method containing the Generic Exception should send mail to the dev in the future
    @ExceptionHandler(Exception.class)
    @Nullable
    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        
        HttpHeaders headers = new HttpHeaders();

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        List<String> list = Stream.of(ex.getMessage()).collect(Collectors.toList());
        ApiError apiError = new ApiError(list);

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    // Handle the 404 exceptions
    @ExceptionHandler({EntityNotFoundException.class, HttpMessageNotWritableException.class})
    @Nullable
    public final ResponseEntity<ApiError> handleException(EntityNotFoundException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.NOT_FOUND;

        List<String> list = Stream.of(ex.getMessage()).collect(Collectors.toList());
        ApiError apiError = new ApiError(list);

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @Nullable
    public final ResponseEntity<ApiError> handleException(MethodArgumentNotValidException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        BindingResult bindingResult = ex.getBindingResult();

        // Parse and remap the error to a readable apiError object
        ApiError apiError = new ApiError(bindingResult.getAllErrors()
            .stream()
            // .map(contentError -> contentError.getObjectName() + " " + contentError.getDefaultMessage())
            .map(contentError -> contentError.getDefaultMessage())
            .collect(Collectors.toList()));

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }
    @ExceptionHandler({HttpMessageNotReadableException.class, ValidationException.class})
    @Nullable
    public final ResponseEntity<ApiError> handleException(HttpMessageNotReadableException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        List<String> list = Stream.of(ex.getMessage()).collect(Collectors.toList());
        ApiError apiError = new ApiError(list);

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, @Nullable ApiError body,
        HttpHeaders headers, HttpStatus status, WebRequest request) {
            
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}