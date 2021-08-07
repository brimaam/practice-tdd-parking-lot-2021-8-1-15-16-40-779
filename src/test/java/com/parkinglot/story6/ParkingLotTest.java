package com.parkinglot.story6;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {
    //Story

    @Test
    void should_return_car_parked_in_the_first_parking_lot_when_park_given_a_super_smart_parking_boy_with_two_parking_lots_both_with_same_available_position_rate_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(15);
        ParkingLot secondParkingLot = new ParkingLot(15);

        List<ParkingLot> parkingLots = Arrays.asList(firstParkingLot, secondParkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        superSmartParkingBoy.parkCar(new Car());
        superSmartParkingBoy.parkCar(new Car());
        superSmartParkingBoy.parkCar(new Car());
        superSmartParkingBoy.parkCar(new Car());

        Car car = new Car();

        //when
        ParkingTicket parkingTicket = superSmartParkingBoy.parkCar(car);

        //then
        assertEquals(car, parkingLots.get(0).fetchCar(parkingTicket));
    }

    @Test
    void should_return_car_parked_in_the_first_parking_lot_when_park_given_a_super_smart_parking_boy_with_two_parking_lots_first_lot_has_larger_available_position_rate_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot = new ParkingLot(15);

        List<ParkingLot> parkingLots = Arrays.asList(firstParkingLot, secondParkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        superSmartParkingBoy.parkCar(new Car());
        superSmartParkingBoy.parkCar(new Car());
        superSmartParkingBoy.parkCar(new Car());


        Car car = new Car();

        //when
        ParkingTicket parkingTicket = superSmartParkingBoy.parkCar(car);

        //then
        assertEquals(car, parkingLots.get(0).fetchCar(parkingTicket));
    }

    @Test
    void should_return_car_parked_in_the_second_parking_lot_when_park_given_a_super_smart_parking_boy_with_two_parking_lots_second_lot_has_larger_available_position_rate_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot = new ParkingLot(15);

        List<ParkingLot> parkingLots = Arrays.asList(firstParkingLot, secondParkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        superSmartParkingBoy.parkCar(new Car());
        superSmartParkingBoy.parkCar(new Car());

        Car car = new Car();

        //when
        ParkingTicket parkingTicket = superSmartParkingBoy.parkCar(car);

        //then
        assertEquals(car, parkingLots.get(1).fetchCar(parkingTicket));
    }

    @Test
    void should_return_car_parked_in_the_second_parking_lot_when_park_given_a_super_smart_parking_boy_with_two_parking_lots_second_is_full_and_first_with_available_position_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot = new ParkingLot(0);

        List<ParkingLot> parkingLots = Arrays.asList(firstParkingLot, secondParkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = superSmartParkingBoy.parkCar(car);

        //then
        assertEquals(car, parkingLots.get(1).fetchCar(parkingTicket));
    }

    @Test
    void should_return_the_right_car_with_each_ticket_when_fetch_twice_given_a_super_smart_parking_boy_with_two_parking_lots_both_with_a_parked_car_and_two_tickets() {
        //given
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot(10), new ParkingLot(7));
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingTicket firstParkingTicket = superSmartParkingBoy.parkCar(firstCar);
        ParkingTicket secondParkingTicket = superSmartParkingBoy.parkCar(secondCar);

        //when
        Car firstActualCar = superSmartParkingBoy.fetchCar(firstParkingTicket);
        Car secondActualCar = superSmartParkingBoy.fetchCar(secondParkingTicket);

        //then
        assertEquals(firstCar, firstActualCar);
        assertEquals(secondCar, secondActualCar);
    }

    @Test
    void should_return_exception_with_error_message_when_fetch_given_a_super_smart_parking_boy_with_two_parking_lots_both_and_an_unrecognized_ticket() {
        //given
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot(), new ParkingLot());
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class,
                () -> superSmartParkingBoy.fetchCar(unrecognizedParkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_exception_with_error_message_when_fetch_given_a_super_smart_parking_boy_with_two_parking_lots_both_and_a_used_ticket() {
        //given
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot(), new ParkingLot());
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        ParkingTicket usedParkingTicket = superSmartParkingBoy.parkCar(new Car());
        superSmartParkingBoy.fetchCar(usedParkingTicket);

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class,
                () -> superSmartParkingBoy.fetchCar(usedParkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
}
