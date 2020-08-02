package com.oocl.cultivation.test.story1;

import com.oocl.cultivation.story1.AbstractPackingBoy;
import com.oocl.cultivation.story1.PackingLot;
import com.oocl.cultivation.story1.SmallParkingBoy;
import com.oocl.cultivation.story1.entity.Car;
import com.oocl.cultivation.story1.entity.Ticket;
import com.oocl.cultivation.story1.exceptions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SmartParkingBoyTest extends BaseTest{

    @Test
    void should_return_true_when_parkingCar_and_find_in_parkingLot2_given_parkingLot1WithSpace5_and_parkingLot2WithSpace10_and_smallParkingBoy_and_car() {
        //given
        List<PackingLot> packingLotList = new ArrayList<>();
        PackingLot packingLot1 = new PackingLot(5);
        PackingLot packingLot2 = new PackingLot(10);
        packingLotList.add(packingLot1);
        packingLotList.add(packingLot2);

        AbstractPackingBoy smallPackingBoy = new SmallParkingBoy(packingLotList);
        Car car = new Car("1234");

        //when
        smallPackingBoy.parkCar(car);
        List<PackingLot> packingLotListInPackingBoy = smallPackingBoy.getPackingLots();
        boolean isFind = packingLotListInPackingBoy.get(1).isCarHaveBeenParked(car);

        //then
        Assertions.assertTrue(isFind);
    }

    @Test
    void should_return_true_when_parkingCar_and_find_car_in_parkingLot1_and_car2_in_parkingLot2_given_parkingLot1WithSpace5_and_parkingLot2WithSpace5_and_smallParkingBoy_and_2car() {
        //given
        List<PackingLot> packingLotList = new ArrayList<>();
        PackingLot packingLot1 = new PackingLot(5);
        PackingLot packingLot2 = new PackingLot(5);
        packingLotList.add(packingLot1);
        packingLotList.add(packingLot2);

        AbstractPackingBoy smallPackingBoy = new SmallParkingBoy(packingLotList);
        List<Car> carList = new ArrayList<>();
        Car car1 = new Car("1234");
        Car car2 = new Car("2345");
        carList.add(car1);
        carList.add(car2);

        //when
        smallPackingBoy.parkCar(carList);
        List<PackingLot> packingLotListInPackingBoy = smallPackingBoy.getPackingLots();
        boolean isFindCar1InParkingLot1 = packingLotListInPackingBoy.get(0).isCarHaveBeenParked(car1);
        boolean isFindCar2InParkingLot2 = packingLotListInPackingBoy.get(1).isCarHaveBeenParked(car2);

        //then
        Assertions.assertTrue(isFindCar1InParkingLot1 && isFindCar2InParkingLot2);
    }

    @Test
    void should_return_ticket_when_park_given_car_smallPakingboy() {
        //given
        AbstractPackingBoy smallPackingBoy = new SmallParkingBoy();
        Car car = new Car("1234");

        //when
        Ticket ticket = smallPackingBoy.parkCar(car);

        //then
        Assertions.assertEquals(Ticket.generateTicket(car),ticket);
    }

    @Test
    void should_return_2_ticket_when_park_given_car_smallParkingboy(){
        //given
        AbstractPackingBoy packingBoy = new SmallParkingBoy();
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("1234"));
        carList.add(new Car("2345"));

        //when
        List<Ticket> ticketList = packingBoy.parkCar(carList);

        //then
        Assertions.assertEquals(2, ticketList.size());
    }

    @Test
    void should_return_full_message_when_park_car_given_car_smallParkingBoy_parkingSpace_0(){
        //given
        PackingLot packingLot = new PackingLot(0);
        AbstractPackingBoy smallPackingBoy = new SmallParkingBoy(packingLot);
        Car car = new Car("1234");

        //when
        BaseException exception =
                Assertions.assertThrows(NotEnoughPositionException.class, () -> smallPackingBoy.parkCar(car));

        //then
        Assertions.assertEquals(PARKING_HAVE_NO_SPACE,exception.getMessage());
    }

    @Test
    void should_return_wrong_message_when_park_car_given_parked_car_and_smallParkingBoy(){
        //given
        AbstractPackingBoy smallPackingBoy = new SmallParkingBoy();
        Car car = new Car("1234");

        //when
        smallPackingBoy.parkCar(car);
        BaseException exception = Assertions.assertThrows(CarHaveBeenParkedException.class, () -> smallPackingBoy.parkCar(car));

        //then
        Assertions.assertEquals(PARKING_CAR_HAVE_BEEN_PARKED,exception.getMessage());
    }

    @Test
    void should_return_wrong_message_when_park_car_given_null_car_and_smallParkingBoy(){
        //given
        AbstractPackingBoy smallPackingBoy = new SmallParkingBoy();
        Car car = null;

        //when
        BaseException exception = Assertions.assertThrows(CarIllegalException.class,() -> smallPackingBoy.parkCar(car));

        //then
        Assertions.assertEquals(CAR_NULL_ERROR_MESSAGE,exception.getMessage());
    }

    @Test
    void should_return_current_ticket_when_fetch_car_given_2car_smallParkingBoy_ticket() {
        //given
        AbstractPackingBoy smallParkingBoy = new SmallParkingBoy();

        List<Car> carList = new ArrayList<>();
        Car car1 = new Car("1234");
        Car car2 = new Car("2345");
        carList.add(car1);
        carList.add(car2);
        smallParkingBoy.parkCar(carList);

        Ticket ticket = Ticket.generateTicket(car1);

        //when
        Car fetchCar = smallParkingBoy.fetchCar(ticket);

        //then
        Assertions.assertEquals(car1, fetchCar);
    }

    @Test
    void should_return_null_when_fetch_car_given_2car_smallParkingBoy_wrong_ticket() {
        //given
        AbstractPackingBoy smallParkingBoy = new SmallParkingBoy();

        List<Car> carList = new ArrayList<>();
        Car car1 = new Car("1234");
        Car car2 = new Car("2345");
        Car carNoPacked = new Car("1235");
        carList.add(car1);
        carList.add(car2);
        smallParkingBoy.parkCar(carList);

        Ticket ticket = Ticket.generateTicket(carNoPacked);

        //when
        BaseException exception = Assertions.assertThrows(TicketException.class,() -> smallParkingBoy.fetchCar(ticket));

        //then
        Assertions.assertEquals(FETCHING_ERROR_TICKET, exception.getMessage());
    }

    @Test
    void should_return_wrong_message_when_fetch_car_given_2car_smallParkingBoy_without_ticket() {
        //given
        AbstractPackingBoy smallParkingBoy = new SmallParkingBoy();
        Ticket ticket = null;

        List<Car> carList = new ArrayList<>();
        carList.add(new Car("1234"));
        carList.add(new Car("2345"));
        smallParkingBoy.parkCar(carList);

        //when
        BaseException exception = Assertions.assertThrows(TicketException.class, () -> smallParkingBoy.fetchCar(ticket));

        //then
        Assertions.assertEquals(FETCHING_HAVE_NO_TICKET, exception.getMessage());
    }

    @Test
    void should_return_unrecognized_message_when_fetch_car_given_2car_smallParkingBoy_ticket_has_been_used() {
        //given
        AbstractPackingBoy smallParkingBoy = new SmallParkingBoy();

        List<Car> carList = new ArrayList<>();
        Car car1 = new Car("1234");
        Car car2 = new Car("2345");
        Car carNoPacked = new Car("1235");
        carList.add(car1);
        carList.add(car2);
        smallParkingBoy.parkCar(carList);

        Ticket ticket = Ticket.generateTicket(car1);

        //when
        smallParkingBoy.fetchCar(ticket);
        BaseException exception =
                Assertions.assertThrows(TicketException.class, () -> smallParkingBoy.fetchCar(ticket));

        //then
        Assertions.assertEquals(FETCHING_ERROR_TICKET, exception.getMessage());
    }

    @Test
    void should_return_true_when_park_car_given_fullParkingLot_unFullParkingLot_smallParkingBoy_car() {
        //given
        List<PackingLot> packingLotList = new ArrayList<>();
        PackingLot fullPackingLot = new PackingLot(1);
        PackingLot unFullPackingLot = new PackingLot(2);
        packingLotList.add(fullPackingLot);
        packingLotList.add(unFullPackingLot);

        AbstractPackingBoy smallParkingBoy = new SmallParkingBoy(packingLotList);
        Car car = new Car("1234");

        //when
        smallParkingBoy.parkCar(new Car("2345"));
        Ticket ticket = smallParkingBoy.parkCar(car);

        //then
        Assertions.assertNotNull(ticket);
    }

    @Test
    void should_return_has_been_packed_message_when_park_car_given_unfullParkingLot_after_carParked_unFullParkingLot_smallParkingBoy_and_2Car() {
        //given
        List<PackingLot> packingLotList = new ArrayList<>();
        PackingLot fullPackingLot = new PackingLot(1);
        PackingLot unFullPackingLot = new PackingLot(2);
        packingLotList.add(fullPackingLot);
        packingLotList.add(unFullPackingLot);

        AbstractPackingBoy smallPackingBoy = new SmallParkingBoy(packingLotList);
        Car car1 = new Car("1234");
        Car car2 = new Car("4567");

        //when
        Ticket ticket1 = smallPackingBoy.parkCar(car1);
        smallPackingBoy.parkCar(car2);
        smallPackingBoy.fetchCar(ticket1);
        BaseException exception =
                Assertions.assertThrows(CarHaveBeenParkedException.class, () -> smallPackingBoy.parkCar(car2));


        //then
        Assertions.assertEquals(PARKING_CAR_HAVE_BEEN_PARKED, exception.getMessage());
    }
}
