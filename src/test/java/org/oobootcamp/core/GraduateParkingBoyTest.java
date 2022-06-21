package org.oobootcamp.core;

import org.junit.jupiter.api.Test;
import org.oobootcamp.core.exception.FullParkingLotException;
import org.oobootcamp.core.exception.InvalidTicketException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class GraduateParkingBoyTest {
    // - Given 只有停车场A有空位
    // - When 停车
    // - Then 成功停车到A, 返回停车票
    @Test
    void should_return_ticket_when_parking_given_a_car_and_parking_lotA_has_vacancy() {
        // Given
        ParkingLot parkingLotA = new ParkingLot("A", 1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(
                List.of(parkingLotA)
        );
        Car car = new Car();

        // When
        Ticket ticket = graduateParkingBoy.park(car);

        // Then
        assertThat(ticket).isNotNull();
        assertThat(graduateParkingBoy.pickUp(ticket)).isEqualTo(car);
    }

    //- Given 停车场A/B均有空位
    //- When 停车
    //- Then 成功停车到A, 返回停车票
    @Test
    void should_return_ticket_when_parking_given_a_car_and_parking_lotA_and_parking_lotB_have_vacancy() {
        // Given
        ParkingLot parkingLotA = new ParkingLot("A", 1);
        ParkingLot parkingLotB = new ParkingLot("B", 2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(
                Arrays.asList(parkingLotA, parkingLotB)
        );

        // When
        Ticket ticket = graduateParkingBoy.park(new Car());

        // Then
        assertThat(ticket).isNotNull();
        assertThat(ticket.parkingLotName).isEqualTo(parkingLotA.name);
    }


    //- Given 有停车场A/B，A已满，B有空位
    //- When 停车
    //- Then 成功停车到B，返回停车票
    @Test
    void should_return_ticket_when_parking_given_a_car_and_parking_lotA_is_full_and_parking_lotB_has_vacancy() {
        // Given
        ParkingLot parkingLotA = new ParkingLot("A", 1);
        ParkingLot parkingLotB = new ParkingLot("B", 1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(
                Arrays.asList(parkingLotA, parkingLotB)
        );
        parkingLotA.park(new Car());

        // When
        Ticket ticket = graduateParkingBoy.park(new Car());

        // Then
        assertThat(ticket).isNotNull();
        assertThat(ticket.parkingLotName).isEqualTo(parkingLotB.name);
    }

    //-Given 有停车场A/B, 均已满
    //-When 停车
    //-Then 提示“车位已满”
    @Test
    void should_notice_parking_lot_is_full_when_parking_given_a_car_and_parking_lots_are_all_full() {
        // Given
        ParkingLot parkingLotA = new ParkingLot("A", 1);
        ParkingLot parkingLotB = new ParkingLot("B", 1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Arrays.asList(parkingLotA, parkingLotB));
        parkingLotA.park(new Car());
        parkingLotB.park(new Car());

        //When, then
        assertThatExceptionOfType(FullParkingLotException.class).isThrownBy(
                () -> graduateParkingBoy.park(new Car())
        ).withMessage("Parking lot is full");
    }

    //-Given 有停车场A/B, 用户车停在B
    //-When 取车
    //-Then 取车成功
    @Test
    void should_return_car_when_pick_up_given_parking_lotA_and_parking_lotB_and_parked_in_B() {
        // Given
        ParkingLot parkingLotA = new ParkingLot("A", 1);
        ParkingLot parkingLotB = new ParkingLot("B", 1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(
                Arrays.asList(parkingLotA, parkingLotB)
        );
        parkingLotA.park(new Car());
        Car parkedCar = new Car();
        Ticket ticket = graduateParkingBoy.park(parkedCar);

        // When
        Car pickedUpCar = graduateParkingBoy.pickUp(ticket);

        // Then
        assertThat(pickedUpCar).isEqualTo(parkedCar);
    }

    //-Given 有停车场A/B, 用户车停在A
    //-When 取车
    //-Then 取车成功
    @Test
    void should_return_car_when_pick_up_given_parking_lotA_and_parking_lotB_and_parked_in_A() {
        // Given
        ParkingLot parkingLotA = new ParkingLot("A", 1);
        ParkingLot parkingLotB = new ParkingLot("B", 1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(
                Arrays.asList(parkingLotA, parkingLotB)
        );
        Car parkedCar = new Car();
        Ticket ticket = graduateParkingBoy.park(parkedCar);

        // When
        Car pickedUpCar = graduateParkingBoy.pickUp(ticket);

        // Then
        assertThat(pickedUpCar).isEqualTo(parkedCar);
    }

    //-Given 其他停车场的车票
    //-When 取车
    //-Then 提示“此票无效”
    @Test
    void should_notice_invalid_ticket_when_pick_up_given_a_ticket_not_belong_to_our_parking_lots() {
        // Given
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(
                Arrays.asList(new ParkingLot("A", 1), new ParkingLot("B", 2))
        );
        ParkingLot otherParkingLot = new ParkingLot("C", 3);
        Ticket ticket = otherParkingLot.park(new Car());

        // When, then
        assertThatExceptionOfType(InvalidTicketException.class).isThrownBy(
                () -> graduateParkingBoy.pickUp(ticket)
        ).withMessage("Invalid ticket");
    }

    //-Given 已取过车的车票
    //-When 取车
    //-Then 提示“此票无效”
    @Test
    void should_notice_invalid_ticket_when_pick_up_given_a_ticket_already_used() {
        // Given
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(
                Arrays.asList(new ParkingLot("A", 1), new ParkingLot("B", 2))
        );
        Ticket ticket = graduateParkingBoy.park(new Car());
        graduateParkingBoy.pickUp(ticket);

        // When, then
        assertThatExceptionOfType(InvalidTicketException.class).isThrownBy(
                () -> graduateParkingBoy.pickUp(ticket)
        ).withMessage("Invalid ticket");
    }
}
