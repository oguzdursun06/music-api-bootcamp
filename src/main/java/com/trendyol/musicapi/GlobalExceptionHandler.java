package com.trendyol.musicapi;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Bir hata oluştu", HttpStatus.NOT_FOUND);
    }

}
