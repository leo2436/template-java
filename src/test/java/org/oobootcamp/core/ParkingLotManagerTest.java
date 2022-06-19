package org.oobootcamp.core;

import org.junit.jupiter.api.Test;
import org.oobootcamp.core.exception.FullParkingLotException;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class ParkingLotManagerTest {
    @Test
    void should_throw_parking_lot_is_full_exception_when_parking_given_VIP_consumer_with_a_car_and_parking_lots_are_all_full() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingLotManager parkingLotManager = new ParkingLotManager(Arrays.asList(parkingLot1, parkingLot2));
        parkingLot1.park(new Car());
        parkingLot2.park(new Car());


        assertThatExceptionOfType(FullParkingLotException.class).isThrownBy(
                () -> parkingLotManager.park(new Car())
        ).withMessage("Parking lot is full");
    }

    @Test
    void should_return_ticket_when_parking_given_VIP_consumer_with_a_car_and_parking_lot1_is_full_but_parking_lot2_parking_lot3_are_not_full() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot targetParkingLot = new ParkingLot(2);
        ParkingLotManager parkingLotManager = new ParkingLotManager(
                Arrays.asList(parkingLot1, targetParkingLot, new ParkingLot(3))
        );
        parkingLot1.park(new Car());

        Ticket ticket = parkingLotManager.park(new Car());

        assertThat(ticket).isNotNull();
        assertThat(ticket.parkingLotId).isEqualTo(targetParkingLot.id);
    }
}