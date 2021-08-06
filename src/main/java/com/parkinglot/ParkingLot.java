package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<ParkingTicket, Car> parkedPosition = new HashMap<>();

    public ParkingTicket parkCar(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkedPosition.put(parkingTicket,car);

        return parkingTicket;
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        Car car = parkedPosition.get(parkingTicket);
        parkedPosition.remove(parkingTicket);

        return car;
    }
}
