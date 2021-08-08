package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy extends StandardParkingBoy {

    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket parkCar(Car car) {
        return parkInLargerAvailableParkingLotRate().parkCar(car);
    }

    private ParkingLot parkInLargerAvailableParkingLotRate() {
        return getParkingLots().stream()
                .filter(ParkingLot::isAvailable)
                .max(Comparator.comparingDouble(parkingLot -> parkingLot.getLargerAvailableRate(parkingLot)))
                .orElseThrow(NoAvailablePositionException::new);
    }

}
