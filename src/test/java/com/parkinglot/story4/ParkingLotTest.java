package com.parkinglot.story4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    //Story 4
    @Test
    void should_return_car_parked_in_the_first_parking_lot_when_park_given_a_standard_parking_boy_with_two_parking_lots_both_with_available_position_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot = new ParkingLot(10);

        List<ParkingLot> parkingLots = Arrays.asList(firstParkingLot, secondParkingLot);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = standardParkingBoy.parkCar(car);

        //then
        assertEquals(car, parkingLots.get(0).fetchCar(parkingTicket));
    }

    @Test
    void should_return_car_parked_in_the_second_parking_lot_when_park_given_a_standard_parking_boy_with_two_parking_lots_first_is_full_and_second_with_available_position_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(0);
        ParkingLot secondParkingLot = new ParkingLot(10);

        List<ParkingLot> parkingLots = Arrays.asList(firstParkingLot, secondParkingLot);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = standardParkingBoy.parkCar(car);

        //then
        assertEquals(car, parkingLots.get(1).fetchCar(parkingTicket));
    }

    @Test
    void should_return_the_right_car_with_each_ticket_when_fetch_given_a_standard_parking_boy_with_two_parking_lots_both_with_a_parked_car_and_two_tickets() {
        //given
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot(), new ParkingLot());
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
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
    void should_return_exception_with_error_message_when_fetch_given_a_standard_parking_boy_with_two_parking_lots_both_and_an_unrecognized_ticket() {
        //given
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot(), new ParkingLot());
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class,
                () -> standardParkingBoy.fetchCar(unrecognizedParkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_exception_with_error_message_when_fetch_given_a_standard_parking_boy_with_two_parking_lots_both_and_a_used_ticket() {
        //given
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot(), new ParkingLot());
        StandardParkingBoy  standardParkingBoy = new StandardParkingBoy(parkingLots);

        ParkingTicket usedParkingTicket = standardParkingBoy.parkCar(new Car());
        standardParkingBoy.fetchCar(usedParkingTicket);

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class,
                () -> standardParkingBoy.fetchCar(usedParkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_exception_with_error_message_when_park_given_a_standard_parking_boy_with_two_parking_lots_both_without_any_position_and_a_car() {
        //given
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot(0), new ParkingLot(1));

        StandardParkingBoy  standardParkingBoy = new StandardParkingBoy(parkingLots);
        standardParkingBoy.parkCar(new Car());
        Car car = new Car();

        //when
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> standardParkingBoy.parkCar(car));

        //then
        assertEquals("No available position.", exception.getMessage());
    }


}
