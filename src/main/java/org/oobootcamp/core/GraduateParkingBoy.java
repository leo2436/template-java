package org.oobootcamp.core;

import org.oobootcamp.core.exception.FullParkingLotException;
import org.oobootcamp.core.exception.InvalidTicketException;

import java.util.List;

class GraduateParkingBoy {
  private final List<ParkingLot> parkingLots;

  public GraduateParkingBoy(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  public Ticket park(Car car) {
    return parkingLots.stream()
        .filter(parkingLot -> !parkingLot.isFull()).findFirst()
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
