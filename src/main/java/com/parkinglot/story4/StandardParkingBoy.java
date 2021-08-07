package com.parkinglot.story4;

import java.util.List;

public class StandardParkingBoy {
    private ParkingLot parkingLot;
    private List<ParkingLot> parkingLots;

    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }
    public StandardParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket parkCar(Car car) {
        return parkInAvailableParkingSpace().parkCar(car);
    }
    private ParkingLot parkInAvailableParkingSpace(){
        return parkingLots.stream()
                .filter(ParkingLot::isAvailable)
                .findFirst()
                .orElseThrow(NoAvailablePositionException::new);
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        return parkingLot.fetchCar(parkingTicket);
    }
}
