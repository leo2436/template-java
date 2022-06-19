package org.oobootcamp.core.exception;

public class FullParkingLotException extends RuntimeException {
    public FullParkingLotException() {
        super("Parking lot is full");
    }
}
