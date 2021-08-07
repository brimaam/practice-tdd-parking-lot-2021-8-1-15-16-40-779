package com.parkinglot.story1;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static final int DEFAULT_CAPACITY = 10;
    private Map<ParkingTicket, Car> parkedPosition = new HashMap<>();
    private int capacity;

    public ParkingLot() {
        this(DEFAULT_CAPACITY);
    }

    public ParkingLot(int capacity) {
        parkedPosition = new HashMap<>();
        this.capacity = capacity;
    }

    public ParkingTicket parkCar(Car car) {
        if(parkedPosition.size() >= capacity){
            return null;
        }
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

