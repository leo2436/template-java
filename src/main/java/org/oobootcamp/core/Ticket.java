package org.oobootcamp.core;

import java.util.UUID;

public class Ticket {
    public UUID parkingLotId;

    public Ticket(UUID parkingLotId) {
        this.parkingLotId = parkingLotId;
    }
}
