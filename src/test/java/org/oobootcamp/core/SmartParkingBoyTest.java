package org.oobootcamp.core;

import org.junit.jupiter.api.Test;
import org.oobootcamp.core.exception.FullParkingLotException;
import org.oobootcamp.core.exception.InvalidTicketException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class SmartParkingBoyTest {
    // - Given 只有停车场A有空位
    // - When 停车
    // - Then 成功停车到A, 返回停车票
    @Test
    void should_return_ticket_when_parking_given_a_car_and_parking_lotA_has_vacancy() {
        // Given
        ParkingLot parkingLotA = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA));
        Car car = new Car();

        // When
        Ticket ticket = smartParkingBoy.park(car);

        // Then
        assertThat(parkingLotA.pickUp(ticket)).isEqualTo(car);
    }

    //- Given 停车场A/B均有空位
    //- When 停车
    //- Then 成功停车到A, 返回停车票
    @Test
    void should_return_ticket_when_parking_given_a_car_and_parking_lotA_and_parking_lotB_have_vacancy() {
        // Given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA, parkingLotB));
        Car parkedCar = new Car();

        // When
        Ticket ticket = smartParkingBoy.park(parkedCar);

        // Then
        assertThat(parkingLotB.pickUp(ticket)).isEqualTo(parkedCar);
    }


    //- Given 有停车场A/B，A已满，B有空位
    //- When 停车
    //- Then 成功停车到B，返回停车票
    @Test
    void should_return_ticket_when_parking_given_a_car_and_parking_lotA_is_full_and_parking_lotB_has_vacancy() {
        // Given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA, parkingLotB));
        parkingLotA.park(new Car());
        Car parkedCar = new Car();

        // When
        Ticket ticket = smartParkingBoy.park(parkedCar);

        // Then
        assertThat(parkingLotB.pickUp(ticket)).isEqualTo(parkedCar);
    }

    //-Given 有停车场A/B, 均已满
    //-When 停车
    //-Then 提示“车位已满”
    @Test
    void should_notice_parking_lot_is_full_when_parking_given_a_car_and_parking_lots_are_all_full() {
        // Given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(parkingLotA, parkingLotB));
        parkingLotA.park(new Car());
        parkingLotB.park(new Car());

        //When, then
        assertThatExceptionOfType(FullParkingLotException.class).isThrownBy(
                () -> smartParkingBoy.park(new Car())
        );
    }

    //-Given 有停车场A/B, 用户车停在B
    //-When 取车
    //-Then 取车成功
    @Test
    void should_return_car_when_pick_up_given_parking_lotA_and_parking_lotB_and_parked_in_B() {
        // Given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA, parkingLotB));
        parkingLotA.park(new Car());
        Car parkedCar = new Car();
        Ticket ticket = smartParkingBoy.park(parkedCar);

        // When
        Car pickedUpCar = smartParkingBoy.pickUp(ticket);

        // Then
        assertThat(pickedUpCar).isEqualTo(parkedCar);
    }

    //-Given 有停车场A/B, 用户车停在A
    //-When 取车
    //-Then 取车成功
    @Test
    void should_return_car_when_pick_up_given_parking_lotA_and_parking_lotB_and_parked_in_A() {
        // Given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA, parkingLotB));
        Car parkedCar = new Car();
        Ticket ticket = smartParkingBoy.park(parkedCar);

        // When
        Car pickedUpCar = smartParkingBoy.pickUp(ticket);

        // Then
        assertThat(pickedUpCar).isEqualTo(parkedCar);
    }

    //-Given 其他停车场的车票
    //-When 取车
    //-Then 提示“此票无效”
    @Test
    void should_notice_invalid_ticket_when_pick_up_given_a_ticket_not_belong_to_our_parking_lots() {
        // Given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(1), new ParkingLot(2)));

        // When, then
        assertThatExceptionOfType(InvalidTicketException.class).isThrownBy(
                () -> smartParkingBoy.pickUp(new Ticket())
        );
    }

    //-Given 已取过车的车票
    //-When 取车
    //-Then 提示“此票无效”
    @Test
    void should_notice_invalid_ticket_when_pick_up_given_a_ticket_already_used() {
        // Given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(1), new ParkingLot(2)));
        Ticket ticket = smartParkingBoy.park(new Car());
        smartParkingBoy.pickUp(ticket);

        // When, then
        assertThatExceptionOfType(InvalidTicketException.class).isThrownBy(
                () -> smartParkingBoy.pickUp(ticket)
        );
    }
}