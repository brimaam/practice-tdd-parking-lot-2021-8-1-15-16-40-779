package com.parkinglot.story6;

public class UnrecognizedParkingTicketException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Unrecognized parking ticket.";
    }
}
