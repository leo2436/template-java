package org.oobootcamp.core;

import org.oobootcamp.core.exception.FullParkingLotException;
import org.oobootcamp.core.exception.InvalidTicketException;

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

    public Car pickUp(Ticket ticket) {
        return parkingLots.stream().filter(parkingLot -> parkingLot.contains(ticket)).findFirst()
                .map(parkingLot -> parkingLot.pickUp(ticket))
                .orElseGet(() -> parkingBoys.stream().filter(parkingBoy -> parkingBoy.contains(ticket)).findFirst()
                        .map(parkingBoy -> parkingBoy.pickUp(ticket))
                        .orElseThrow(InvalidTicketException::new));
    }
}
