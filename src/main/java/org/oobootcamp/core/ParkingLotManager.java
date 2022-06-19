package org.oobootcamp.core;

import org.oobootcamp.core.exception.FullParkingLotException;

import java.util.List;

class ParkingLotManager {
    private final List<ParkingLot> parkingLots;

    public ParkingLotManager(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        ParkingLot targetParkingLot = parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFull()).findFirst()
                .orElseThrow(FullParkingLotException::new);

        return targetParkingLot.park(car);
    }
}
