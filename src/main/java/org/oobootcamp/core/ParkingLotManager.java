package org.oobootcamp.core;

import org.oobootcamp.core.exception.FullParkingLotException;

import java.util.List;

public class ParkingLotManager {
    private final List<ParkingLot> parkingLots;
    private final List<AbstractParkingBoy> parkingBoys;

    public ParkingLotManager(List<ParkingLot> parkingLots,  List<AbstractParkingBoy> parkingBoys) {
        this.parkingLots = parkingLots;
        this.parkingBoys = parkingBoys;
    }


    public Ticket park(Car car) {
        return parkingLots.stream().filter(parkingLot -> !parkingLot.isFull()).findFirst()
                .map(parkingLot -> parkingLot.park(car))
                .orElseGet(() -> parkingBoys.stream().filter(AbstractParkingBoy::hasCapacity).findFirst()
                        .map(parkingBoy -> parkingBoy.park(car))
                        .orElseThrow(FullParkingLotException::new));
    }
}
