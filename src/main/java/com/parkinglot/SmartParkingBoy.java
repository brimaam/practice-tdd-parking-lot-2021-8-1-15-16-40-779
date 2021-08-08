package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends StandardParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket parkCar(Car car) {
        return parkInMoreAvailableParkingLot().parkCar(car);
    }

    private ParkingLot parkInMoreAvailableParkingLot() {
        return   getParkingLots().stream()
                .filter(ParkingLot::isAvailable)
                .max(Comparator.comparingInt(ParkingLot::getCapacity))
                .orElseThrow(NoAvailablePositionException::new);
    }
}
