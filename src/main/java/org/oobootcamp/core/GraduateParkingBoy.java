package org.oobootcamp.core;

import org.oobootcamp.core.exception.FullParkingLotException;
import org.oobootcamp.core.exception.InvalidTicketException;

import java.util.List;
import java.util.Objects;

class GraduateParkingBoy {
    private final List<ParkingLot> parkingLots;

    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        ParkingLot targetParkingLot = parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFull()).findFirst()
                .orElseThrow(FullParkingLotException::new);

        return targetParkingLot.park(car);
    }

    public Car pickUp(Ticket ticket) {
        ParkingLot targetParkingLot = parkingLots.stream()
                .filter(parkingLot -> parkingLot.name.equals(ticket.parkingLotName)).findFirst()
                .orElseThrow(InvalidTicketException::new);

        return targetParkingLot.pickUp(ticket);
    }
}