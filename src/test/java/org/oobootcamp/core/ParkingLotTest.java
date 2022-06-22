package org.oobootcamp.core;

import org.junit.jupiter.api.Test;
import org.oobootcamp.core.exception.FullParkingLotException;
import org.oobootcamp.core.exception.InvalidTicketException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ParkingLotTest {

    @Test
    void should_return_ticket_when_park_given_parking_lot_has_enough_carports() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);

        Ticket ticket = parkingLot.park(car);

        assertThat(ticket).isNotNull();
    }

    @Test
    void should_notice_parking_lot_is_full_when_parking_given_a_car_and_a_full_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car firstCar = new Car();
        parkingLot.park(firstCar);

        Car secondCar = new Car();

        assertThatExceptionOfType(FullParkingLotException.class).isThrownBy(
                () -> parkingLot.park(secondCar)
        );
    }

    @Test
    void should_return_car_when_user_pick_up_given_the_ticket_is_valid() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car actualCar = new Car();
        Ticket ticket = parkingLot.park(actualCar);

        Car expectedCar = parkingLot.pickUp(ticket);

        assertThat(expectedCar).isEqualTo(actualCar);
    }

    @Test
    void should_notice_invalid_ticket_when_pick_up_given_ticket_not_belong_to_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        parkingLot.park(car);

        Ticket otherTicket = new Ticket();

        assertThatExceptionOfType(InvalidTicketException.class).isThrownBy(
                () -> parkingLot.pickUp(otherTicket)
        );
    }

    @Test
    void should_notice_invalid_ticket_when_pick_up_given_ticket_already_used() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        parkingLot.pickUp(ticket);

        assertThatExceptionOfType(InvalidTicketException.class).isThrownBy(
                () -> parkingLot.pickUp(ticket)
        );
    }

    @Test
    void should_return_true_when_parking_lot_is_full() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car());

        assertThat(parkingLot.isFull()).isTrue();
    }

    @Test
    void should_return_false_when_parking_lot_is_not_full() {
        ParkingLot parkingLot = new ParkingLot(1);

        assertThat(parkingLot.isFull()).isFalse();
    }
}
