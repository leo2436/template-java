package org.oobootcamp.core;

import org.oobootcamp.core.exception.FullParkingLotException;
import org.oobootcamp.core.exception.InvalidTicketException;

import java.util.Comparator;
import java.util.List;

class SmartParkingBoy {
    private final List<ParkingLot> parkingLots;

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        return parkingLots.stream()
                .max(Comparator.comparing(ParkingLot::availableParkingSpot))
                .orElseThrow(FullParkingLotException::new)
                .park(car);
    }

    public Car pickUp(Ticket ticket) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.getParkedCars().containsKey(ticket)).findFirst()
                .orElseThrow(InvalidTicketException::new)
                .pickUp(ticket);
    }
}
