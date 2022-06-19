package org.oobootcamp.core;

import org.oobootcamp.core.exception.FullParkingLotException;
import org.oobootcamp.core.exception.InvalidTicketException;

import java.util.HashMap;
import java.util.UUID;

public class ParkingLot {
    private final int capacity;
    private final HashMap<Ticket, Car> ticketCarMap = new HashMap<>();
    public UUID id = UUID.randomUUID();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) {
        if (isFull()) {
            throw new FullParkingLotException();
        }
        Ticket ticket = new Ticket(id);
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

    public boolean isFull() {
        return ticketCarMap.size() == capacity;
    }
}
