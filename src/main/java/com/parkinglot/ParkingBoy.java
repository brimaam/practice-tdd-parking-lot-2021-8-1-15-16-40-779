package com.parkinglot;

import java.util.List;

public abstract class ParkingBoy {
    protected List<ParkingLot> parkingLots;
    protected ParkingLot parkingLot;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket parkCar(Car car) {
        return parkInAvailableParkingSpace().parkCar(car);
    }

    abstract protected ParkingLot parkInAvailableParkingSpace();

    public Car fetchCar(ParkingTicket parkingTicket) {
        return findParkingLotBasedOnTicket(parkingTicket).fetchCar(parkingTicket);
    }

    private ParkingLot findParkingLotBasedOnTicket(ParkingTicket parkingTicket) {
        if (parkingLot == null) {
            return parkingLots.stream()
                    .filter(parkingLot -> parkingLot.isBasedOnTicket(parkingTicket))
                    .findFirst()
                    .orElseThrow(UnrecognizedParkingTicketException::new);
        }
        if (!parkingLot.isBasedOnTicket(parkingTicket)) {
            throw new UnrecognizedParkingTicketException();
        }
        return parkingLot;
    }

}
