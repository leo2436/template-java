package org.oobootcamp.core;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ParkingLotManagerTest {
    @Test
    void should_return_ticket_and_car_in_parking_lotA_when_parking_given_parking_lotA_and_parking_lotB_both_with_1_vacancy() {
        // Given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        ParkingLotManager parkingLotManager = new ParkingLotManager(List.of(parkingLotA, parkingLotB), List.of());
        Car car = new Car();

        // When
        Ticket ticket = parkingLotManager.park(car);

        // Then
        assertThat(parkingLotA.pickUp(ticket)).isEqualTo(car);
    }

    @Test
    void should_return_ticket_and_car_in_parking_lotB_when_parking_given_parking_lotA_is_full_and_parking_lotB_1_vacancy() {
        // Given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        parkingLotA.park(new Car());
        ParkingLotManager parkingLotManager = new ParkingLotManager(List.of(parkingLotA, parkingLotB), List.of());
        Car car = new Car();

        // When
        Ticket ticket = parkingLotManager.park(car);

        // Then
        assertThat(parkingLotB.pickUp(ticket)).isEqualTo(car);
    }

    @Test
    void should_return_ticket_and_car_in_parking_lotA_when_parking_given_both_parking_boys_have_vacancy() {
        // Given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        GraduateParkingBoy parkingBoyA = new GraduateParkingBoy(List.of(parkingLotA));
        SmartParkingBoy parkingBoyB = new SmartParkingBoy(List.of(parkingLotB));
        ParkingLotManager parkingLotManager = new ParkingLotManager(List.of(), List.of(parkingBoyA, parkingBoyB));
        Car car = new Car();

        // When
        Ticket ticket = parkingLotManager.park(car);

        // Then
        assertThat(parkingLotA.pickUp(ticket)).isEqualTo(car);
    }

    @Test
    void should_return_ticket_and_car_in_parking_lotB_when_parking_given_parking_boy1_is_full_and_parking_boy2_has_vacancy() {
        // Given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        parkingLotA.park(new Car());
        GraduateParkingBoy parkingBoyA = new GraduateParkingBoy(List.of(parkingLotA));
        SmartParkingBoy parkingBoyB = new SmartParkingBoy(List.of(parkingLotB));
        ParkingLotManager parkingLotManager = new ParkingLotManager(List.of(), List.of(parkingBoyA, parkingBoyB));
        Car car = new Car();

        // When
        Ticket ticket = parkingLotManager.park(car);

        // Then
        assertThat(parkingLotB.pickUp(ticket)).isEqualTo(car);
    }

    @Test
    void should_return_ticket_and_car_in_parking_lotA_when_parking_given_parking_boyA_managed_by_self_and_parking_lotB_managed_by_boy1_has_vacancy() {
        // Given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        GraduateParkingBoy parkingBoyA = new GraduateParkingBoy(List.of(parkingLotB));
        ParkingLotManager parkingLotManager = new ParkingLotManager(List.of(parkingLotA), List.of(parkingBoyA));
        Car car = new Car();

        // When
        Ticket ticket = parkingLotManager.park(car);

        // Then
        assertThat(parkingLotA.pickUp(ticket)).isEqualTo(car);
    }

    @Test
    void should_return_ticket_and_car_in_parking_lotB_when_parking_given_parking_boyA_managed_by_self_is_full_and_parking_lotB_managed_by_boy1_has_vacancy() {
        // Given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        parkingLotA.park(new Car());
        GraduateParkingBoy parkingBoyA = new GraduateParkingBoy(List.of(parkingLotB));
        ParkingLotManager parkingLotManager = new ParkingLotManager(List.of(parkingLotA), List.of(parkingBoyA));
        Car car = new Car();

        // When
        Ticket ticket = parkingLotManager.park(car);

        // Then
        assertThat(parkingLotB.pickUp(ticket)).isEqualTo(car);
    }
}
