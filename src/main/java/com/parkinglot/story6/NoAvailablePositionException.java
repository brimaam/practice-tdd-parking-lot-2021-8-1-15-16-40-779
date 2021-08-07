package com.parkinglot.story6;

public class NoAvailablePositionException extends RuntimeException {
    @Override
    public String getMessage() {
        return "No available position.";
    }
}
