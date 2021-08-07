package com.parkinglot.story2;

public class NoAvailablePositionException extends RuntimeException{
    @Override
    public String getMessage(){
        return "No available position.";
    }
}
