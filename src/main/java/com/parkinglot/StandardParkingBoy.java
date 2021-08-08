package com.parkinglot;

import java.util.List;

public class StandardParkingBoy {
    private List<ParkingLot> parkingLots;
    private ParkingLot parkingLot;

    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }
    public StandardParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket parkCar(Car car) {
        return parkInAvailableParkingSpace().parkCar(car);
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    private ParkingLot parkInAvailableParkingSpace() {
        if(parkingLot == null) {
            return parkingLots.stream()
                    .filter(ParkingLot::isAvailable)
                    .findFirst()
                    .orElseThrow(NoAvailablePositionException::new);
        }
        if(!ParkingLot.isAvailable(parkingLot)){
            throw new NoAvailablePositionException();
        }
        return parkingLot;
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        return findParkingLotBasedOnTicket(parkingTicket).fetchCar(parkingTicket);
    }

    private ParkingLot findParkingLotBasedOnTicket(ParkingTicket parkingTicket) {
        if(parkingLot == null) {
            return parkingLots.stream()
                    .filter(parkingLot -> parkingLot.isBasedOnTicket(parkingTicket))
                    .findFirst()
                    .orElseThrow(UnrecognizedParkingTicketException::new);
        }
        if(!parkingLot.isBasedOnTicket(parkingTicket)){
            throw new UnrecognizedParkingTicketException();
        }
        return parkingLot;
    }
}
