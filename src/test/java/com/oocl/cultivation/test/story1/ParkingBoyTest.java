package com.oocl.cultivation.test.story1;

import com.oocl.cultivation.story1.PackingBoy;
import com.oocl.cultivation.story1.PackingLot;
import com.oocl.cultivation.story1.entity.Car;
import com.oocl.cultivation.story1.entity.Ticket;
import com.oocl.cultivation.story1.exceptions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoyTest extends BaseTest{

    @Test
    void should_return_ticket_when_park_given_car_pakingboy() {
        //given
        PackingBoy packingBoy = new PackingBoy();
        Car car = new Car("1234");

        //when
        Ticket ticket = packingBoy.parkCar(car);

        //then
        Assertions.assertEquals(Ticket.generateTicket(car), ticket);
    }

    @Test
    void should_return_2_ticket_when_park_given_car_parkingboy(){
        //given
        PackingBoy packingBoy = new PackingBoy();
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("1234"));
        carList.add(new Car("2345"));

        //when
        List<Ticket> ticketList = packingBoy.parkCar(carList);

        //then
        Assertions.assertEquals(2, ticketList.size());
    }

    @Test
    void should_return_full_message_when_park_car_given_car_parkingboy_parkingspace_0(){
        //given
        PackingLot packingLot = new PackingLot(0);
        PackingBoy packingBoy = new PackingBoy(packingLot);
        Car car = new Car("1234");

        //when
        BaseException exception =
                Assertions.assertThrows(NotEnoughPositionException.class,() -> packingBoy.parkCar(car));

        //then
        Assertions.assertEquals(PARKING_HAVE_NO_SPACE,exception.getMessage());
    }

    @Test
    void should_return_wrong_message_when_park_car_given_parked_car_and_parkingboy(){
        //given
        PackingBoy packingBoy = new PackingBoy();
        Car car = new Car("1234");

        //when
        packingBoy.parkCar(car);
        BaseException exception = Assertions.assertThrows(CarHaveBeenParkedException.class, () -> packingBoy.parkCar(car));

        //then
        Assertions.assertEquals(PARKING_CAR_HAVE_BEEN_PARKED,exception.getMessage());
    }

    @Test
    void should_return_wrong_message_when_park_car_given_null_car_and_parkingboy(){
        //given
        PackingBoy packingBoy = new PackingBoy();
        Car car = null;

        //when
        BaseException exception =
                Assertions.assertThrows(CarIllegalException.class, () -> packingBoy.parkCar(car));

        //then
        Assertions.assertEquals(CAR_NULL_ERROR_MESSAGE,exception.getMessage());
    }

    @Test
    void should_return_current_ticket_when_fetch_car_given_2car_parkingboy_ticket() {
        //given
        PackingBoy packingBoy = new PackingBoy();

        List<Car> carList = new ArrayList<>();
        Car car1 = new Car("1234");
        Car car2 = new Car("2345");
        carList.add(car1);
        carList.add(car2);
        packingBoy.parkCar(carList);

        Ticket ticket = Ticket.generateTicket(car1);

        //when
        Car fetchCar = packingBoy.fetchCar(ticket);

        //then
        Assertions.assertEquals(car1.getCardId(), fetchCar.getCardId());
    }

    @Test
    void should_return_null_when_fetch_car_given_2car_parkingboy_wrong_ticket() {
        //given
        PackingBoy packingBoy = new PackingBoy();

        List<Car> carList = new ArrayList<>();
        Car car1 = new Car("1234");
        Car car2 = new Car("2345");
        carList.add(car1);
        carList.add(car2);
        packingBoy.parkCar(carList);

        Car carNoPacked = new Car("1324");
        Ticket ticket = Ticket.generateTicket(carNoPacked);

        //when
        BaseException exception = Assertions.assertThrows(TicketException.class, () -> packingBoy.fetchCar(ticket));

        //then
        Assertions.assertEquals(FETCHING_ERROR_TICKET, exception.getMessage());
    }

    @Test
    void should_return_wrong_message_when_fetch_car_given_2car_parkingboy_without_ticket() {
        //given
        PackingBoy packingBoy = new PackingBoy();
        Ticket ticket = null;

        List<Car> carList = new ArrayList<>();
        carList.add(new Car("1234"));
        carList.add(new Car("2345"));
        packingBoy.parkCar(carList);

        //when
        BaseException exception = Assertions.assertThrows(TicketException.class,() -> packingBoy.fetchCar(ticket));

        //then
        Assertions.assertEquals(FETCHING_HAVE_NO_TICKET,exception.getMessage());
    }

    @Test
    void should_return_null_when_fetch_car_given_2car_pakingboy_ticket_has_been_used() {
        //given
        PackingBoy packingBoy = new PackingBoy();

        List<Car> carList = new ArrayList<>();
        Car car1 = new Car("1234");
        Car car2 = new Car("2345");
        carList.add(car1);
        carList.add(car2);
        packingBoy.parkCar(carList);

        Ticket ticket = Ticket.generateTicket(car1);

        //when
        packingBoy.fetchCar(ticket);
        BaseException exception = Assertions.assertThrows(TicketException.class,() -> packingBoy.fetchCar(ticket));

        //then
        Assertions.assertEquals(FETCHING_ERROR_TICKET, exception.getMessage());
    }

    @Test
    void should_return_true_when_park_car_given_fullParkingLot_unFullParkingLot_pakingboy_car() {
        //given
        List<PackingLot> packingLotList = new ArrayList<>();
        PackingLot fullPackingLot = new PackingLot(1);
        PackingLot unFullPackingLot = new PackingLot(2);
        packingLotList.add(fullPackingLot);
        packingLotList.add(unFullPackingLot);

        PackingBoy packingBoy = new PackingBoy(packingLotList);
        Car car = new Car("1234");

        //when
        packingBoy.parkCar(new Car("2345"));
        Ticket ticket = packingBoy.parkCar(car);

        //then
        Assertions.assertEquals(Ticket.generateTicket(car), ticket);
    }

    // 检验进来的时候第一个停车场满了，所以car停在第二个停车场，这时候第一个停车场走了一辆车，客户拿着已经停过的车再来停
    @Test
    void should_return_has_been_packed_message_when_park_car_given_unfullParkingLot_after_carParked_unFullParkingLot_pakingboy_and_2Car() {
        //given
        List<PackingLot> packingLotList = new ArrayList<>();
        PackingLot fullPackingLot = new PackingLot(1);
        PackingLot unFullPackingLot = new PackingLot(2);
        packingLotList.add(fullPackingLot);
        packingLotList.add(unFullPackingLot);

        PackingBoy packingBoy = new PackingBoy(packingLotList);
        Car car1 = new Car("1234");
        Car car2 = new Car("4567");

        //when
        Ticket ticket1 = packingBoy.parkCar(car1);
        packingBoy.parkCar(car2);
        packingBoy.fetchCar(ticket1);
        BaseException exception =
                Assertions.assertThrows(CarHaveBeenParkedException.class,() -> packingBoy.parkCar(car2));

        //then
        Assertions.assertEquals(PARKING_CAR_HAVE_BEEN_PARKED,exception.getMessage());
    }
}
