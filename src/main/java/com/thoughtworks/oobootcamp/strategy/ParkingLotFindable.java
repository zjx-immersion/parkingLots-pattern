package com.thoughtworks.oobootcamp.strategy;

import com.thoughtworks.oobootcamp.Parkable;

import java.util.List;

public interface ParkingLotFindable {
    Parkable find(List<Parkable> parkables);
}
