package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    //Story 1
    @Test
    void should_return_parking_ticket_when_park_given_a_parking_lot_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingLot.parkCar(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_parked_car_when_fetch_given_a_parking_lot_with_a_parked_car_and_a_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.parkCar(car);

        //when
        Car actualCar = parkingLot.fetchCar(parkingTicket);

        //then
        assertEquals(car, actualCar);
    }

    @Test
    void should_return_the_right_car_when_fetch_twice_given_a_parking_lot_with_two_parked_cars_and_two_parking_tickets() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingTicket firstParkingTicket = parkingLot.parkCar(firstCar);
        ParkingTicket secondParkingTicket = parkingLot.parkCar(secondCar);

        //when
        Car firstActualCar = parkingLot.fetchCar(firstParkingTicket);
        Car secondActualCar = parkingLot.fetchCar(secondParkingTicket);

        //then
        assertEquals(firstCar, firstActualCar);
        assertEquals(secondCar, secondActualCar);
    }

    @Test
    void should_return_no_car_when_fetch_given_a_parking_lot_and_a_wrong_parking_ticket() {
        try {
            //given
            ParkingLot parkingLot = new ParkingLot();
            ParkingTicket wrongParkingTicket = new ParkingTicket();

            //when
            Car car = parkingLot.fetchCar(wrongParkingTicket);

            //then
            assertNull(car);

        } catch (Exception e){
            e.getMessage();
        }
    }

    @Test
    void should_return_no_car_when_fetch_given_a_parking_lot_and_a_parking_ticket_that_has_been_fetched() {
        try{
            //given
            ParkingLot parkingLot = new ParkingLot();
            Car firstCar = new Car();
            ParkingTicket usedParkingTicket = parkingLot.parkCar(firstCar);
            parkingLot.fetchCar(usedParkingTicket);

            //when
            Car secondCar = parkingLot.fetchCar(usedParkingTicket);

            //then
            assertNull(secondCar);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Test
    void should_return_no_parking_ticket_when_park_given_a_full_parking_lot_and_a_car() {
        try {
            //given
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.parkCar(new Car());
            Car car = new Car();

            //when
            ParkingTicket parkingTicket = parkingLot.parkCar(car);

            //then
            assertNull(parkingTicket);
        } catch (Exception e){
            e.getMessage();
        }

    }

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
