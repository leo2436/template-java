package org.oobootcamp.core;

import org.oobootcamp.core.exception.FullParkingLotException;
import org.oobootcamp.core.exception.InvalidTicketException;

import java.util.HashMap;

public class ParkingLot {
    private final int capacity;
    HashMap<Ticket, Car> ticketCarMap = new HashMap<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) {
        if (ticketCarMap.size() == capacity) {
            throw new FullParkingLotException();
        }
        Ticket ticket = new Ticket();
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    public Car pickUp(Ticket ticket) {
        if (!ticketCarMap.containsKey(ticket)) {
            throw new InvalidTicketException();
        }
        Car car = ticketCarMap.get(ticket);
        ticketCarMap.remove(ticket);
        return car;
    }
}
