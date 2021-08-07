package com.parkinglot.story4;

public class UnrecognizedParkingTicketException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Unrecognized parking ticket.";
    }
}
