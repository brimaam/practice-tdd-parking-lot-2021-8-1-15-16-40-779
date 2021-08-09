package com.parkinglot;

import java.util.List;

public class StandardParkingBoy extends ParkingBoy {

    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public StandardParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }


    @Override
    protected ParkingLot parkInAvailableParkingSpace() {
        if (parkingLot == null) {
            return parkingLots.stream()
                    .filter(ParkingLot::isAvailable)
                    .findFirst()
                    .orElseThrow(NoAvailablePositionException::new);
        }
        if (!ParkingLot.isAvailable(parkingLot)) {
            throw new NoAvailablePositionException();
        }
        return parkingLot;
    }
}
