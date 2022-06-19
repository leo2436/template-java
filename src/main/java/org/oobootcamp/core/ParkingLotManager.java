package org.oobootcamp.core;

import org.oobootcamp.core.exception.FullParkingLotException;

import java.util.List;

class ParkingLotManager {
    private final List<ParkingLot> parkingLots;

    public ParkingLotManager(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public void park(Car car) {
        if (parkingLots.stream().allMatch(ParkingLot::isFull)) {
            throw new FullParkingLotException();
        }
    }
}
