package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {

    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    protected ParkingLot parkInAvailableParkingSpace() {
        return parkingLots.stream()
                .filter(ParkingLot::isAvailable)
                .max(Comparator.comparingDouble(parkingLot -> parkingLot.getLargerAvailableRate(parkingLot)))
                .orElseThrow(NoAvailablePositionException::new);
    }
}
