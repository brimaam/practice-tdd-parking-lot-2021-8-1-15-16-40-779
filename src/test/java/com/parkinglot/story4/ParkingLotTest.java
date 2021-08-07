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
        assertEquals(car, parkingLots.stream().findFirst().get().fetchCar(parkingTicket));
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
        assertNotNull(parkingTicket);
    }

}
