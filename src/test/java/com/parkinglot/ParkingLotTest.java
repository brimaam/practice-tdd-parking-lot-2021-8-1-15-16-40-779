package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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

    //Story 3
    @Test
    void should_return_parking_ticket_when_park_given_a_parking_lot_a_standard_parking_boy_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
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
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);

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
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
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
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
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
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);

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
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        standardParkingBoy.parkCar(new Car());
        Car car = new Car();

        //when
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> standardParkingBoy.parkCar(car));

        //then
        assertEquals("No available position.", exception.getMessage());
    }

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
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);

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

        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        standardParkingBoy.parkCar(new Car());
        Car car = new Car();

        //when
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> standardParkingBoy.parkCar(car));

        //then
        assertEquals("No available position.", exception.getMessage());
    }

    //Story 5
    @Test
    void should_return_car_parked_in_the_first_parking_lot_when_park_given_a_smart_parking_boy_with_two_parking_lots_both_with_same_available_position_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot = new ParkingLot(10);

        List<ParkingLot> parkingLots = Arrays.asList(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = smartParkingBoy.parkCar(car);

        //then
        assertEquals(car, parkingLots.get(0).fetchCar(parkingTicket));
    }

    @Test
    void should_return_car_parked_in_the_first_parking_lot_when_park_given_a_smart_parking_boy_with_two_parking_lots_first_lot_has_more_available_position_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot = new ParkingLot(5);

        List<ParkingLot> parkingLots = Arrays.asList(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = smartParkingBoy.parkCar(car);

        //then
        assertEquals(car, parkingLots.get(0).fetchCar(parkingTicket));
    }

    @Test
    void should_return_car_parked_in_the_second_parking_lot_when_park_given_a_smart_parking_boy_with_two_parking_lots_second_lot_has_more_available_position_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot secondParkingLot = new ParkingLot(15);

        List<ParkingLot> parkingLots = Arrays.asList(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = smartParkingBoy.parkCar(car);

        //then
        assertEquals(car, parkingLots.get(1).fetchCar(parkingTicket));
    }

    @Test
    void should_return_car_parked_in_the_second_parking_lot_when_park_given_a_smart_parking_boy_with_two_parking_lots_first_is_full_and_second_with_available_position_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(0);
        ParkingLot secondParkingLot = new ParkingLot(10);

        List<ParkingLot> parkingLots = Arrays.asList(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = smartParkingBoy.parkCar(car);

        //then
        assertEquals(car, parkingLots.get(1).fetchCar(parkingTicket));
    }

    @Test
    void should_return_the_right_car_with_each_ticket_when_fetch_given_a_smart_parking_boy_with_two_parking_lots_both_with_a_parked_car_and_two_tickets() {
        //given
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot(), new ParkingLot());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingTicket firstParkingTicket = smartParkingBoy.parkCar(firstCar);
        ParkingTicket secondParkingTicket = smartParkingBoy.parkCar(secondCar);

        //when
        Car firstActualCar = smartParkingBoy.fetchCar(firstParkingTicket);
        Car secondActualCar = smartParkingBoy.fetchCar(secondParkingTicket);

        //then
        assertEquals(firstCar, firstActualCar);
        assertEquals(secondCar, secondActualCar);
    }

    @Test
    void should_return_exception_with_error_message_when_fetch_given_a_smart_parking_boy_with_two_parking_lots_both_and_an_unrecognized_ticket() {
        //given
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot(), new ParkingLot());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class,
                () -> smartParkingBoy.fetchCar(unrecognizedParkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_exception_with_error_message_when_fetch_given_a_smart_parking_boy_with_two_parking_lots_both_and_a_used_ticket() {
        //given
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot(), new ParkingLot());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        ParkingTicket usedParkingTicket = smartParkingBoy.parkCar(new Car());
        smartParkingBoy.fetchCar(usedParkingTicket);

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class,
                () -> smartParkingBoy.fetchCar(usedParkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_exception_with_error_message_when_park_given_a_smart_parking_boy_with_two_parking_lots_both_without_any_position_and_a_car() {
        //given
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot(0), new ParkingLot(1));

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        smartParkingBoy.parkCar(new Car());
        Car car = new Car();

        //when
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> smartParkingBoy.parkCar(car));

        //then
        assertEquals("No available position.", exception.getMessage());
    }

    //Story 6
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
    void should_return_car_parked_in_the_first_parking_lot_when_park_given_a_super_smart_parking_boy_with_two_parking_lots_second_is_full_and_first_with_available_position_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot = new ParkingLot(0);

        List<ParkingLot> parkingLots = Arrays.asList(firstParkingLot, secondParkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = superSmartParkingBoy.parkCar(car);

        //then
        assertEquals(car, parkingLots.get(0).fetchCar(parkingTicket));
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

    @Test
    void should_return_exception_with_error_message_when_park_given_a_super_smart_parking_boy_with_two_parking_lots_both_without_any_position_and_a_car() {
        //given
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot(1), new ParkingLot(1));

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        superSmartParkingBoy.parkCar(new Car());
        superSmartParkingBoy.parkCar(new Car());

        Car car = new Car();

        //when
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> superSmartParkingBoy.parkCar(car));

        //then
        assertEquals("No available position.", exception.getMessage());
    }
}
