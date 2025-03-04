package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static final int DEFAULT_CAPACITY = 10;
    private final Map<ParkingTicket, Car> parkedPosition;
    private final int capacity;

    public ParkingLot() {
        this(DEFAULT_CAPACITY);
    }

    public ParkingLot(int capacity) {
        parkedPosition = new HashMap<>();
        this.capacity = capacity;
    }

    public ParkingTicket parkCar(Car car) {
        if (parkedPosition.size() >= capacity) {
            throw new NoAvailablePositionException();
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        parkedPosition.put(parkingTicket, car);

        return parkingTicket;
    }

    public Map<ParkingTicket, Car> getParkedPosition() {
        return parkedPosition;
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        if (!parkedPosition.containsKey(parkingTicket)) {
            throw new UnrecognizedParkingTicketException();
        }
        Car car = parkedPosition.get(parkingTicket);
        parkedPosition.remove(parkingTicket);

        return car;
    }

    public double getLargerAvailableRate(ParkingLot parkingLot) {
        return ((double) capacity - parkingLot.getParkedPosition().size()) / (double) capacity;
    }

    public static boolean isAvailable(ParkingLot parkingLot) {
        return parkingLot.parkedPosition.size() < parkingLot.capacity;
    }

    public boolean isBasedOnTicket(ParkingTicket parkingTicket) {
        return parkedPosition.containsKey(parkingTicket);
    }

    public int getCapacity() {
        return capacity;
    }
}

