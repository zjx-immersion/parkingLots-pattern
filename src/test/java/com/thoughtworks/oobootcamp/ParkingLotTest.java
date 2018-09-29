package com.thoughtworks.oobootcamp;

import com.thoughtworks.oobootcamp.Car;
import com.thoughtworks.oobootcamp.Parkable;
import com.thoughtworks.oobootcamp.ParkingLot;
import com.thoughtworks.oobootcamp.Ticket;
import org.junit.jupiter.api.Test;

import com.thoughtworks.oobootcamp.exception.ParkingLotIsFullException;
import com.thoughtworks.oobootcamp.exception.TicketIsInvalidException;

import static org.junit.jupiter.api.Assertions.*;


public class ParkingLotTest {

    @Test
    public void should_park_succeed_given_available_lots(){
        //given
        Parkable parkable = new ParkingLot(1);

        //when
        Ticket ticket = parkable.park(new Car());

        //then
        assertNotNull(ticket);
    }

    @Test
    public void should_failed_given_lots_is_full(){
        //given
        Parkable parkable = new ParkingLot(1);
        Car expectedCar = new Car();
        Ticket ticket = parkable.park(expectedCar);

        assertThrows(ParkingLotIsFullException.class, ()-> parkable.park(new Car()));
    }

    @Test
    public void should_pickup_right_car_given_a_ticket(){
        //given
        Parkable parkable = new ParkingLot(1);
        Car expectedCar = new Car();
        Ticket ticket = parkable.park(expectedCar);

        //when pickup
        Car actualCar = parkable.pickUp(ticket);

        //then
        assertEquals(expectedCar,actualCar);
    }

    @Test
    public void should_failed_given_an_invalid_ticket(){
        assertThrows(TicketIsInvalidException.class, () -> {
            Parkable parkable = new ParkingLot(1);
            parkable.pickUp(new Ticket());
        });
    }

    @Test
    public void should_return_available_lots(){
        Parkable parkable = new ParkingLot(10);
        parkable.park(new Car());

        int availableLots = parkable.getAvailableLots();
        assertEquals(9, availableLots);
    }

    @Test
    public void should_return_vailable_lots(){
        Parkable parkable = new ParkingLot(10);
        parkable.pickUp(parkable.park(new Car()));
        int availableLots = parkable.getAvailableLots();
        assertEquals(10, availableLots);

    }


}
