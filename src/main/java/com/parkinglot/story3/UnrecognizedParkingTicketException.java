package com.parkinglot.story3;

public class UnrecognizedParkingTicketException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Unrecognized parking ticket.";
    }
}
