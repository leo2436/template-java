package org.oobootcamp.core;

import org.oobootcamp.core.exception.FullParkingLotException;
import org.oobootcamp.core.exception.InvalidTicketException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class AbstractParkingBoy {
    private final List<ParkingLot> parkingLots;

    public AbstractParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        return findTargetParkingLot(parkingLots.stream())
                .orElseThrow(FullParkingLotException::new)
                .park(car);
    }


    public Car pickUp(Ticket ticket) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.contains(ticket)).findFirst()
                .orElseThrow(InvalidTicketException::new)
                .pickUp(ticket);
    }

     protected abstract Optional<ParkingLot> findTargetParkingLot(Stream<ParkingLot> parkingLots);

    public boolean hasCapacity() {
        return parkingLots.stream().anyMatch(parkingLot -> !parkingLot.isFull());
    }

    public boolean contains(Ticket ticket) {
        return parkingLots.stream().anyMatch(parkingLot -> parkingLot.contains(ticket));
    }
}
