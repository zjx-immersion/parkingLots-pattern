package com.thoughtworks.oobootcamp.strategy;

import com.thoughtworks.oobootcamp.Parkable;
import com.thoughtworks.oobootcamp.exception.ParkingLotIsFullException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MaxAvailableParkingLotFinder implements ParkingLotFindable {
    @Override
    public Parkable find(List<Parkable> parkables) {
        Optional<Parkable> first = parkables.stream()
                                                .filter(lot -> lot.getAvailableLots() > 0)
                                                .max(Comparator.comparing(Parkable::getAvailableLots));
        if (!first.isPresent()) {
            throw new ParkingLotIsFullException();
        }
        return first.get();
    }
}
