package org.oobootcamp.core;

import org.oobootcamp.core.exception.FullParkingLotException;
import org.oobootcamp.core.exception.InvalidTicketException;

import java.util.List;

class SmartParkingBoy {
    private final List<ParkingLot> parkingLots;

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .findFirst()
                .orElseThrow(FullParkingLotException::new);

        return parkingLots.stream()
                .reduce(parkingLots.get(0), this::compare)
                .park(car);
    }

    public Car pickUp(Ticket ticket) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.getParkedCars().containsKey(ticket)).findFirst()
                .orElseThrow(InvalidTicketException::new)
                .pickUp(ticket);
    }

    private ParkingLot compare(ParkingLot a, ParkingLot b) {
        if (a.availableParkingSpot() >= b.availableParkingSpot()) {
            return a;
        }
        return b;
    }
}
