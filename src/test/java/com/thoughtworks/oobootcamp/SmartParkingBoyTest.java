package com.thoughtworks.oobootcamp;

import com.thoughtworks.oobootcamp.*;
import org.junit.jupiter.api.Test;

import com.thoughtworks.oobootcamp.exception.ParkingLotIsFullException;
import com.thoughtworks.oobootcamp.exception.TicketIsInvalidException;
import com.thoughtworks.oobootcamp.strategy.MaxAvailableParkingLotFinder;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {

    @Test
    public void should_park_to_second_lot() {
        //given
        List<Parkable> parkables = new ArrayList<>();
        Parkable firstParkable = new ParkingLot(1);
        parkables.add(firstParkable);
        Parkable secondParkable = new ParkingLot(10);
        parkables.add(secondParkable);

        Parkable parkingBoy = new ParkingBoy(parkables, new MaxAvailableParkingLotFinder());

        Car expectedCar = new Car();
        Ticket ticket = parkingBoy.park(expectedCar);

        assertEquals(1, firstParkable.getAvailableLots());
        assertEquals(9, secondParkable.getAvailableLots());
        Car actualCar = secondParkable.pickUp(ticket);
        assertSame(expectedCar, actualCar);
    }

    @Test
    public void should_park_failed() {
        //given full
        List<Parkable> parkables = new ArrayList<>();
        Parkable firstParkable = new ParkingLot(1);
        parkables.add(firstParkable);
        Parkable secondParkable = new ParkingLot(1);
        parkables.add(secondParkable);

        Parkable parkingBoy = new ParkingBoy(parkables, new MaxAvailableParkingLotFinder());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());

        //when
        assertThrows(ParkingLotIsFullException.class, ()->parkingBoy.park(new Car()));
    }

    @Test
    public void should_pickup_right_car_given_right_ticket(){
        //given full
        List<Parkable> parkables = new ArrayList<>();
        Parkable firstParkable = new ParkingLot(1);
        parkables.add(firstParkable);

        Parkable secondParkable = new ParkingLot(10);
        parkables.add(secondParkable);

        Parkable parkingBoy = new ParkingBoy(parkables, new MaxAvailableParkingLotFinder());
        Car expectedCar = new Car();
        Ticket ticket = parkingBoy.park(expectedCar);

        //when
        Car actualCar = parkingBoy.pickUp(ticket);

        //then
        assertSame(expectedCar,actualCar);
        assertEquals(1, firstParkable.getAvailableLots());
        assertEquals(10, secondParkable.getAvailableLots());
    }

    @Test
    public void should_pickup_failed_given_invalid_ticket(){

        //given full
        List<Parkable> parkables = new ArrayList<>();
        Parkable firstParkable = new ParkingLot(1);
        parkables.add(firstParkable);
        Parkable secondParkable = new ParkingLot(1);
        parkables.add(secondParkable);

        Parkable parkingBoy = new ParkingBoy(parkables, new MaxAvailableParkingLotFinder());

        //when
        assertThrows(TicketIsInvalidException.class,() -> parkingBoy.pickUp(new Ticket()));

    }
}
