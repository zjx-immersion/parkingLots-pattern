package com.thoughtworks.oobootcamp;

public interface Parkable {
    Ticket park(Car car);

    Car pickUp(Ticket ticket);

    int getAvailableLots();

    boolean isTicketValid(Ticket ticket);

    double getVacancyRate();

    int getCapacity();
}
