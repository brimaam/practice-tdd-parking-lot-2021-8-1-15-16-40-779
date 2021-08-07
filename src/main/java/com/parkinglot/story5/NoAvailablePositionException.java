package com.parkinglot.story5;

public class NoAvailablePositionException extends RuntimeException{
    @Override
    public String getMessage(){
        return "No available position.";
    }
}
