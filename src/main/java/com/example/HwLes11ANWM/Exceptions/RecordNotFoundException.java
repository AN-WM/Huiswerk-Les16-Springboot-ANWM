package com.example.HwLes11ANWM.Exceptions;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException() {
        super("This record wasn't found");
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}
