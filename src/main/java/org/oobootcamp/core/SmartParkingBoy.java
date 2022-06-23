package org.oobootcamp.core;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

class SmartParkingBoy extends AbstractParkingBoy {
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    Optional<ParkingLot> findTargetParkingLot(Stream<ParkingLot> parkingLots) {
        return parkingLots.max(Comparator.comparing(ParkingLot::availableParkingSpot));
    }
}
