package com.parkinglot.story3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    //Story 3
    @Test
    void should_return_parking_ticket_when_park_given_a_parking_lot_a_standard_parking_boy_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy  standardParkingBoy = new StandardParkingBoy(parkingLot);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = standardParkingBoy.parkCar(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_parked_car_when_fetch_given_a_parking_lot_with_a_parked_car_a_standard_parking_boy_and_a_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy  standardParkingBoy = new StandardParkingBoy(parkingLot);

        Car car = new Car();
        ParkingTicket parkingTicket = standardParkingBoy.parkCar(car);

        //when
        Car actualCar = standardParkingBoy.fetchCar(parkingTicket);

        //then
        assertEquals(car, actualCar);
    }

    @Test
    void should_return_the_right_car_when_fetch_twice_given_a_parking_lot_with_two_parked_cars_a_standard_parking_boy_and_two_parking_tickets() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy  standardParkingBoy = new StandardParkingBoy(parkingLot);
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingTicket firstParkingTicket = standardParkingBoy.parkCar(firstCar);
        ParkingTicket secondParkingTicket = standardParkingBoy.parkCar(secondCar);

        //when
        Car firstActualCar = standardParkingBoy.fetchCar(firstParkingTicket);
        Car secondActualCar = standardParkingBoy.fetchCar(secondParkingTicket);

        //then
        assertEquals(firstCar, firstActualCar);
        assertEquals(secondCar, secondActualCar);
    }

    @Test
    void should_return_exception_with_error_message_when_fetch_given_a_parking_lot_a_standard_parking_boy_and_a_wrong_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy  standardParkingBoy = new StandardParkingBoy(parkingLot);
        ParkingTicket wrongParkingTicket = new ParkingTicket();

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> standardParkingBoy.fetchCar(wrongParkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_exception_with_error_message_when_fetch_given_a_parking_lot_a_standard_parking_boy_and_a_used_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy  standardParkingBoy = new StandardParkingBoy(parkingLot);

        ParkingTicket parkingTicket = standardParkingBoy.parkCar(new Car());
        standardParkingBoy.fetchCar(parkingTicket);

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> standardParkingBoy.fetchCar(parkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_exception_with_error_message_when_park_given_a_parking_lot_without_any_position_a_standard_parking_boy_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        StandardParkingBoy  standardParkingBoy = new StandardParkingBoy(parkingLot);
        standardParkingBoy.parkCar(new Car());
        Car car = new Car();

        //when
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> standardParkingBoy.parkCar(car));

        //then
        assertEquals("No available position.", exception.getMessage());
    }
}
