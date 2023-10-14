package com.trendyol.musicapi;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}