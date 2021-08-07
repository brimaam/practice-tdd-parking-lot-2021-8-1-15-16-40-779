package com.parkinglot.story4;

public class NoAvailablePositionException extends RuntimeException {
    @Override
    public String getMessage() {
        return "No available position.";
    }
}
