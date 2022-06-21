package org.oobootcamp.core;

import org.oobootcamp.core.exception.FullParkingLotException;
import org.oobootcamp.core.exception.InvalidTicketException;

import java.util.HashMap;

public class ParkingLot {
    private final int capacity;
    public final String name;
    private final HashMap<Ticket, Car> parkedCars = new HashMap<>();

    public ParkingLot(String name, int capacity) {
        this.capacity = capacity;
        this.name = name;
    }

    public Ticket park(Car car) {
        if (isFull()) {
            throw new FullParkingLotException();
        }
        Ticket ticket = new Ticket(name);
        parkedCars.put(ticket, car);
        return ticket;
    }

    public Car pickUp(Ticket ticket) {
        if (!parkedCars.containsKey(ticket)) {
            throw new InvalidTicketException();
        }
        Car car = parkedCars.get(ticket);
        parkedCars.remove(ticket);
        return car;
    }

    public boolean isFull() {
        return parkedCars.size() == capacity;
    }
}
