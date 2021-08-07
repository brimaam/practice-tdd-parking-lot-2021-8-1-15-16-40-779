package com.parkinglot.story3;

public class NoAvailablePositionException extends RuntimeException{
    @Override
    public String getMessage(){
        return "No available position.";
    }
}
