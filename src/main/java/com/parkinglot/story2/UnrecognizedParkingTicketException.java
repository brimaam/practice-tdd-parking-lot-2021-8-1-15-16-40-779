package com.parkinglot.story2;

public class UnrecognizedParkingTicketException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Unrecognized parking ticket.";
    }
}
