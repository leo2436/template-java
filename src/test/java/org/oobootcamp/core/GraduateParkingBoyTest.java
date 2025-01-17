package org.oobootcamp.core;

import org.junit.jupiter.api.Test;
import org.oobootcamp.core.exception.FullParkingLotException;
import org.oobootcamp.core.exception.InvalidTicketException;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class GraduateParkingBoyTest {
    @Test
    void should_return_ticket_and_car_in_parking_lotA_when_parking_given_parking_lotA_with_1_vacancy() {
        // Given
        ParkingLot parkingLotA = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA));
        Car car = new Car();

        // When
        Ticket ticket = graduateParkingBoy.park(car);

        // Then
        assertThat(parkingLotA.pickUp(ticket)).isEqualTo(car);
    }

    @Test
    void should_return_ticket_and_car_in_parking_lotA_when_parking_given_parking_lotA_with_1_vacancy_and_parking_lotB_with_2_vacancy() {
        // Given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));
        Car parkedCar = new Car();

        // When
        Ticket ticket = graduateParkingBoy.park(parkedCar);

        // Then
        assertThat(parkingLotA.pickUp(ticket)).isEqualTo(parkedCar);
    }

    @Test
    void should_return_ticket_and_car_in_parking_lotB_when_parking_given_parking_lotA_is_full_and_parking_lotB_with_1_vacancy() {
        // Given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));
        parkingLotA.park(new Car());
        Car parkedCar = new Car();

        // When
        Ticket ticket = graduateParkingBoy.park(parkedCar);

        // Then
        assertThat(parkingLotB.pickUp(ticket)).isEqualTo(parkedCar);
    }

    @Test
    void should_notice_parking_lot_is_full_when_parking_given_parking_lots_are_all_full() {
        // Given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));
        parkingLotA.park(new Car());
        parkingLotB.park(new Car());

        //When, then
        assertThatExceptionOfType(FullParkingLotException.class).isThrownBy(
                () -> graduateParkingBoy.park(new Car())
        );
    }

    @Test
    void should_return_car_when_pick_up_given_parking_lotA_and_parking_lotB_and_parked_in_B() {
        // Given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));
        parkingLotA.park(new Car());
        Car parkedCar = new Car();
        Ticket ticket = graduateParkingBoy.park(parkedCar);

        // When
        Car pickedUpCar = graduateParkingBoy.pickUp(ticket);

        // Then
        assertThat(pickedUpCar).isEqualTo(parkedCar);
    }

    @Test
    void should_return_car_when_pick_up_given_parking_lotA_and_parking_lotB_and_parked_in_A() {
        // Given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));
        Car parkedCar = new Car();
        Ticket ticket = graduateParkingBoy.park(parkedCar);

        // When
        Car pickedUpCar = graduateParkingBoy.pickUp(ticket);

        // Then
        assertThat(pickedUpCar).isEqualTo(parkedCar);
    }

    @Test
    void should_notice_invalid_ticket_when_pick_up_given_a_ticket_belong_to_other_parking_lot() {
        // Given
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(new ParkingLot(1), new ParkingLot(2)));

        // When, then
        assertThatExceptionOfType(InvalidTicketException.class).isThrownBy(
                () -> graduateParkingBoy.pickUp(new Ticket())
        );
    }

    @Test
    void should_notice_invalid_ticket_when_pick_up_given_a_ticket_already_used() {
        // Given
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(new ParkingLot(1), new ParkingLot(2)));
        Ticket ticket = graduateParkingBoy.park(new Car());
        graduateParkingBoy.pickUp(ticket);

        // When, then
        assertThatExceptionOfType(InvalidTicketException.class).isThrownBy(
                () -> graduateParkingBoy.pickUp(ticket)
        );
    }
}
