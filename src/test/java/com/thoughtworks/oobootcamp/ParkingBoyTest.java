package com.thoughtworks.oobootcamp;

import com.thoughtworks.oobootcamp.*;
import org.junit.jupiter.api.Test;

import com.thoughtworks.oobootcamp.exception.ParkingLotIsFullException;
import com.thoughtworks.oobootcamp.exception.TicketIsInvalidException;
import com.thoughtworks.oobootcamp.strategy.OrderedParkingLotFinder;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {

    @Test
    public void should_park_into_first_lot_given_lots_is_available(){
        //given
        List<Parkable> parkables = new ArrayList<>();
        Parkable firstParkable = new ParkingLot(10);
        parkables.add(firstParkable);
        Parkable secondParkable = new ParkingLot(10);
        parkables.add(secondParkable);

        Parkable parkingBoy = new ParkingBoy(parkables, new OrderedParkingLotFinder());

        Car expectedCar = new Car();
        Ticket ticket = parkingBoy.park(expectedCar);
        assertEquals(9, firstParkable.getAvailableLots());

        Car actualCar = firstParkable.pickUp(ticket);
        assertSame(expectedCar,actualCar);

        assertEquals(10, secondParkable.getAvailableLots());
    }

    @Test
    public void should_park_into_second_lot_given_first_is_full(){
        //given
        List<Parkable> parkables = new ArrayList<>();
        Parkable firstParkable = new ParkingLot(0);
        parkables.add(firstParkable);
        Parkable secondParkable = new ParkingLot(10);
        parkables.add(secondParkable);

        Parkable parkingBoy = new ParkingBoy(parkables, new OrderedParkingLotFinder());

        Car expectedCar = new Car();
        Ticket ticket = parkingBoy.park(expectedCar);

        Car actualCar = secondParkable.pickUp(ticket);

        assertSame(expectedCar,actualCar);
    }

    @Test
    public void should_failed_when_all_lots_are_full(){
        //given full
        List<Parkable> parkables = new ArrayList<>();
        Parkable firstParkable = new ParkingLot(1);
        parkables.add(firstParkable);
        Parkable secondParkable = new ParkingLot(1);
        parkables.add(secondParkable);

        ParkingBoy parkingBoy = new ParkingBoy(parkables, new OrderedParkingLotFinder());
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
        Parkable secondParkable = new ParkingLot(1);
        parkables.add(secondParkable);

        ParkingBoy parkingBoy = new ParkingBoy(parkables, new OrderedParkingLotFinder());
        Car expectedCar = new Car();
        Ticket ticket = parkingBoy.park(expectedCar);

        //when
        Car actualCar = parkingBoy.pickUp(ticket);

        //then
        assertSame(expectedCar,actualCar);
    }

    @Test
    public void should_pickup_failed_given_invalid_ticket(){
        //given full
        List<Parkable> parkables = new ArrayList<>();
        Parkable firstParkable = new ParkingLot(1);
        parkables.add(firstParkable);
        Parkable secondParkable = new ParkingLot(1);
        parkables.add(secondParkable);

        Parkable parkingBoy = new ParkingBoy(parkables, new OrderedParkingLotFinder());

        //when
        assertThrows(TicketIsInvalidException.class,() -> parkingBoy.pickUp(new Ticket()));

    }

}
