package com.parkinglot.story4;

import java.util.List;

public class StandardParkingBoy {
    private List<ParkingLot> parkingLots;

    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket parkCar(Car car) {
        return parkInAvailableParkingSpace().parkCar(car);
    }

    private ParkingLot parkInAvailableParkingSpace() {
        return parkingLots.stream()
                .filter(ParkingLot::isAvailable)
                .findFirst()
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
