package com.epam;

public class PlaceholderNotProvidedException extends RuntimeException{
    public PlaceholderNotProvidedException(String message) {
        super(message);
    }
}
