package com.furniture.furniturefactory.exceptions;

public class InsufficientMaterialsException extends RuntimeException {

    public InsufficientMaterialsException(String message) {
        super(message);
    }
}