package com.oocl.cultivation.test.story1;

import com.oocl.cultivation.story1.*;
import com.oocl.cultivation.story1.entity.Car;
import com.oocl.cultivation.story1.entity.Ticket;
import com.oocl.cultivation.story1.enums.ParkingFetchingEnums;
import com.oocl.cultivation.story1.exceptions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class SuperSmartParkingBoyTest extends BaseTest{

    @Test
    void should_return_true_when_parkingCar_and_find_in_parkingLot2_given_parkingLot1WithSpace5_and_parkingLot2WithSpace10ParkedCar1_and_superSmallParkingBoy_and_car() {
        //given
        List<PackingLot> packingLotList = new ArrayList<>();
        PackingLot packingLot1 = new PackingLot(10);
        PackingLot packingLot2 = new PackingLot(5);

        packingLot1.parkCar(new Car("4444"));
        packingLotList.add(packingLot1);
        packingLotList.add(packingLot2);

        AbstractPackingBoy superSmallPackingBoy = new SuperSmallParkingBoy(packingLotList);
        Car car = new Car("1234");

        //when
        superSmallPackingBoy.parkCar(car);
        List<PackingLot> packingLotListInPackingBoy = superSmallPackingBoy.getPackingLots();
        boolean isFind = packingLotListInPackingBoy.get(1).isCarHaveBeenParked(car);

        //then
        Assertions.assertTrue(isFind);
    }

    @Test
    void should_return_ticket_when_park_given_car_superSmallParkingBoy() {
        //given
        AbstractPackingBoy superSmallPackingBoy = new SuperSmallParkingBoy();
        Car car = new Car("1234");

        //when
        Ticket ticket = superSmallPackingBoy.parkCar(car);

        //then
        Assertions.assertNotNull(ticket);
    }

    @Test
    void should_return_2_ticket_when_park_given_car_superSmallParkingBoy(){
        //given
        AbstractPackingBoy superSmallPackingBoy = new SuperSmallParkingBoy();
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("1234"));
        carList.add(new Car("2345"));

        //when
        List<Ticket> ticketList = superSmallPackingBoy.parkCar(carList);

        //then
        Assertions.assertEquals(2, ticketList.size());
    }

    @Test
    void should_return_full_message_when_park_car_given_car_superSmallParkingBoy_parkingSpace_0(){
        //given
        PackingLot packingLot = new PackingLot(0);
        AbstractPackingBoy superSmallPackingBoy = new SuperSmallParkingBoy(packingLot);
        Car car = new Car("1234");

        //when
        BaseException exception =
                Assertions.assertThrows(NotEnoughPositionException.class, () -> superSmallPackingBoy.parkCar(car));

        //then
        Assertions.assertEquals(PARKING_HAVE_NO_SPACE,exception.getMessage());
    }

    @Test
    void should_return_wrong_message_when_park_car_given_parked_car_and_superSmallParkingBoy(){
        //given
        AbstractPackingBoy superSmallPackingBoy = new SuperSmallParkingBoy();
        Car car = new Car("1234");

        //when
        superSmallPackingBoy.parkCar(car);
        BaseException exception = Assertions.assertThrows(CarHaveBeenParkedException.class, () -> superSmallPackingBoy.parkCar(car));

        //then
        Assertions.assertEquals(PARKING_CAR_HAVE_BEEN_PARKED,exception.getMessage());
    }

    @Test
    void should_return_wrong_message_when_park_car_given_null_car_and_superSmallParkingBoy(){
        //given
        AbstractPackingBoy superSmallParkingBoy = new SuperSmallParkingBoy();
        Car car = null;

        //when
        BaseException exception = Assertions.assertThrows(CarIllegalException.class,() -> superSmallParkingBoy.parkCar(car));

        //then
        Assertions.assertEquals(CAR_NULL_ERROR_MESSAGE,exception.getMessage());
    }

    @Test
    void should_return_true_when_park_car_given_fullParkingLot_unFullParkingLot_superSmallParkingBoy_car() {
        //given
        List<PackingLot> packingLotList = new ArrayList<>();
        PackingLot fullPackingLot = new PackingLot(1);
        PackingLot unFullPackingLot = new PackingLot(2);
        packingLotList.add(fullPackingLot);
        packingLotList.add(unFullPackingLot);

        AbstractPackingBoy superSmallParkingBoy = new SuperSmallParkingBoy(packingLotList);
        Car car = new Car("1234");

        //when
        superSmallParkingBoy.parkCar(new Car("2345"));
        Ticket ticket = superSmallParkingBoy.parkCar(car);

        //then
        Assertions.assertNotNull(ticket);
    }

    @Test
    void should_return_has_been_packed_message_when_park_car_given_unfullParkingLot_after_carParked_unFullParkingLot_superSmallParkingBoy_and_2Car() {
        //given
        List<PackingLot> packingLotList = new ArrayList<>();
        PackingLot fullPackingLot = new PackingLot(1);
        PackingLot unFullPackingLot = new PackingLot(2);
        packingLotList.add(fullPackingLot);
        packingLotList.add(unFullPackingLot);

        AbstractPackingBoy superSmallPackingBoy = new SuperSmallParkingBoy(packingLotList);
        Car car1 = new Car("1234");
        Car car2 = new Car("4567");

        //when
        Ticket ticket1 = superSmallPackingBoy.parkCar(car1);
        superSmallPackingBoy.parkCar(car2);
        superSmallPackingBoy.fetchCar(ticket1);
        BaseException exception = Assertions.assertThrows(CarHaveBeenParkedException.class,() -> superSmallPackingBoy.parkCar(car2));


        //then
        Assertions.assertEquals(PARKING_CAR_HAVE_BEEN_PARKED,exception.getMessage());
    }

    @Test
    void should_return_true_when_parkingCar_and_find_car_in_parkingLot1_and_car2_in_parkingLot2_given_parkingLot1WithSpace5_and_parkingLot2WithSpace4_and_superSmallParkingBoy_and_2car() {
        //given
        List<PackingLot> packingLotList = new ArrayList<>();
        PackingLot packingLot1 = new PackingLot(5);
        PackingLot packingLot2 = new PackingLot(4);
        packingLotList.add(packingLot1);
        packingLotList.add(packingLot2);

        AbstractPackingBoy superSmallPackingBoy = new SuperSmallParkingBoy(packingLotList);
        List<Car> carList = new ArrayList<>();
        Car car1 = new Car("1234");
        Car car2 = new Car("2345");
        carList.add(car1);
        carList.add(car2);

        //when
        superSmallPackingBoy.parkCar(carList);
        List<PackingLot> packingLotListInPackingBoy = superSmallPackingBoy.getPackingLots();
        boolean isFindCar1InParkingLot1 = packingLotListInPackingBoy.get(0).isCarHaveBeenParked(car1);
        boolean isFindCar2InParkingLot2 = packingLotListInPackingBoy.get(1).isCarHaveBeenParked(car2);

        //then
        Assertions.assertTrue(isFindCar1InParkingLot1 && isFindCar2InParkingLot2);
    }
}
