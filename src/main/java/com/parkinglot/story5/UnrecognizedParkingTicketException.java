package com.parkinglot.story5;

public class UnrecognizedParkingTicketException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Unrecognized parking ticket.";
    }
}
