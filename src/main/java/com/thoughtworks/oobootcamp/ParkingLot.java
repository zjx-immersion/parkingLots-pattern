package com.thoughtworks.oobootcamp;

import com.thoughtworks.oobootcamp.exception.ParkingLotIsFullException;
import com.thoughtworks.oobootcamp.exception.TicketIsInvalidException;

import java.util.HashMap;

public class ParkingLot implements Parkable {
    private int capacity;
    private HashMap<Ticket,Car> carTickets = new HashMap<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Ticket park(Car car) {
        boolean hasAvailableLots = getAvailableLots() > 0;
        if(hasAvailableLots){
            Ticket ticket = new Ticket();
            carTickets.put(ticket,car);
            return ticket;
        }
        throw new ParkingLotIsFullException();
    }

    public Car pickUp(Ticket ticket) {
        if(isTicketValid(ticket)){
            return carTickets.remove(ticket);
        }
        throw new TicketIsInvalidException();
    }

    public int getAvailableLots() {
        return capacity - carTickets.size();
    }

    public boolean isTicketValid(Ticket ticket) {
        return carTickets.containsKey(ticket);
    }

    @Override
    public double getVacancyRate() {
        return Math.round(((double) getAvailableLots() / (double) capacity) * 100) * 0.01;
    }

    @Override
    public int getCapacity(){
        return capacity;
    }
}
