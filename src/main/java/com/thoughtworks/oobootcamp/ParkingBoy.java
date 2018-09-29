package com.thoughtworks.oobootcamp;

import com.thoughtworks.oobootcamp.exception.TicketIsInvalidException;
import com.thoughtworks.oobootcamp.strategy.ParkingLotFindable;

import java.util.List;
import java.util.Optional;

public class ParkingBoy implements Parkable{
    private final ParkingLotFindable parkingLotFindable;
    private List<Parkable> parkables;

    public ParkingBoy(List<Parkable> parkables, ParkingLotFindable parkingLotFindable) {
        this.parkables = parkables;
        this.parkingLotFindable = parkingLotFindable;
    }

    @Override
    public Ticket park(Car car) {
        return parkingLotFindable.find(parkables).park(car);
    }

    @Override
    public Car pickUp(Ticket ticket) {
        Optional<Parkable> first = parkables.stream().filter(lot -> lot.isTicketValid(ticket)).findFirst();
        if(first.isPresent()){
            return first.get().pickUp(ticket);
        }
        throw new TicketIsInvalidException();
    }

    @Override
    public int getAvailableLots() {
        return parkables.stream().mapToInt(x->x.getAvailableLots()).sum();
    }

    @Override
    public boolean isTicketValid(Ticket ticket) {
        return parkables.stream().anyMatch(x->x.isTicketValid(ticket));
    }

    @Override
    public double getVacancyRate() {
        return Math.round(((double)this.getAvailableLots()/(double)this.getCapacity())*100)*0.01;
    }

    public int getCapacity() {
        return parkables.stream().mapToInt(p->p.getAvailableLots()).sum();
    }

}
