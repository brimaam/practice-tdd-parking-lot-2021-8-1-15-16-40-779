package com.parkinglot.story2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    //Story 2
    @Test
    void should_return_exception_with_error_message_when_fetch_given_a_parking_lot_and_a_unrecognized_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetchCar(unrecognizedParkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_exception_with_error_message_when_fetch_given_a_parking_lot_and_a_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket parkingTicket = parkingLot.parkCar(new Car());
        parkingLot.fetchCar(parkingTicket);

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetchCar(parkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_exception_with_error_message_when_park_given_a_parking_lot_without_any_position_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.parkCar(new Car());
        Car car = new Car();

        //when
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> parkingLot.parkCar(car));

        //then
        assertEquals("No available position.", exception.getMessage());
    }

}
