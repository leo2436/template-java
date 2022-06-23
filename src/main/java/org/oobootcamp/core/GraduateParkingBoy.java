package org.oobootcamp.core;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

class GraduateParkingBoy extends AbstractParkingBoy {
    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    protected Optional<ParkingLot> findTargetParkingLot(Stream<ParkingLot> parkingLots) {
        return parkingLots.filter(parkingLot -> !parkingLot.isFull()).findFirst();
    }
}
