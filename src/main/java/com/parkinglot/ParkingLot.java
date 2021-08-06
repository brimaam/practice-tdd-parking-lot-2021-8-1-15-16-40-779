package com.parkinglot;

public class ParkingLot {
    private Car car;

    public ParkingTicket parkCar(Car car) {
        this.car = car;
        return new ParkingTicket();
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        return car;
    }
}
