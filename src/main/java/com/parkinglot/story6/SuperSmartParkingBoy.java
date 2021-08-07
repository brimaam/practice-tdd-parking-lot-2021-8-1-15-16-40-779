package com.parkinglot.story6;

import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy {
    private List<ParkingLot> parkingLots;

    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket parkCar(Car car) {
        return parkInLargerAvailableParkingLotRate().parkCar(car);
    }

    private ParkingLot parkInLargerAvailableParkingLotRate() {

        return parkingLots.stream()
                .max(Comparator.comparingDouble(parkingLot -> parkingLot.getLargerAvailableRate(parkingLot)))
                .orElseThrow(NoAvailablePositionException::new);
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        return findParkingLotBasedOnTicket(parkingTicket).fetchCar(parkingTicket);
    }

    private ParkingLot findParkingLotBasedOnTicket(ParkingTicket parkingTicket) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.isBasedOnTicket(parkingTicket))
                .findFirst()
                .orElseThrow(UnrecognizedParkingTicketException::new);
    }
}
