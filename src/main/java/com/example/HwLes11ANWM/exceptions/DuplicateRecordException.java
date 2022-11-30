package com.example.HwLes11ANWM.exceptions;

public class DuplicateRecordException extends RuntimeException {
    public DuplicateRecordException() {
        super("This record already exists");
    }

    public DuplicateRecordException(String message) {
        super(message);
    }
}
