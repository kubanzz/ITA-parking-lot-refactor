package com.oocl.cultivation.test.story1;

import com.oocl.cultivation.story1.Car;
import com.oocl.cultivation.story1.PackingBoy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ParkingBoyFacts {
    @Test
    void should_return_ticket_when_park_given_car_pakingboy() {
        //given
        PackingBoy packingBoy = new PackingBoy();
        Car car = new Car("1234");

        //when
        String ticket = packingBoy.parkCar(car);

        //then
        Assertions.assertNotEquals("the parking is full",ticket);
    }

    @Test
    void should_return_2_ticket_when_park_given_car_parkingboy(){
        //given
        PackingBoy packingBoy = new PackingBoy();
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("1234"));
        carList.add(new Car("2345"));

        //when
        List<String> ticketList = packingBoy.parkCar(carList);

        //then
        Assertions.assertEquals(2, ticketList.size());
    }

    @Test
    void should_return_full_message_when_park_car_given_car_parkingboy_parkingspace_0(){
        //given
        int parkingSpace = 0;
        PackingBoy packingBoy = new PackingBoy(parkingSpace);
        Car car = new Car("1234");

        //when
        packingBoy.parkCar(car);

        //then
        Assertions.assertEquals("Not enough position.",packingBoy.getErrorMessage());
    }

    @Test
    void should_return_wrong_message_when_park_car_given_parked_car_and_parkingboy(){
        //given
        PackingBoy packingBoy = new PackingBoy();
        Car car = new Car("1234");

        //when
        packingBoy.parkCar(car);
        packingBoy.parkCar(car);

        //then
        Assertions.assertEquals("the car has benn packed",packingBoy.getErrorMessage());
    }

    @Test
    void should_return_wrong_message_when_park_car_given_null_car_and_parkingboy(){
        //given
        PackingBoy packingBoy = new PackingBoy();
        Car car = null;

        //when
        packingBoy.parkCar(car);

        //then
        Assertions.assertEquals("the car cannot be null",packingBoy.getErrorMessage());
    }

    @Test
    void should_return_current_ticket_when_fetch_car_given_2car_parkingboy_ticket() {
        //given
        PackingBoy packingBoy = new PackingBoy();
        String ticket = "1234";

        List<Car> carList = new ArrayList<>();
        carList.add(new Car("1234"));
        carList.add(new Car("2345"));
        packingBoy.parkCar(carList);

        //when
        Car fetchCar = packingBoy.fetchCar(ticket);

        //then
        Assertions.assertEquals(ticket, fetchCar.getCardId());
    }

    @Test
    void should_return_null_when_fetch_car_given_2car_parkingboy_wrong_ticket() {
        //given
        PackingBoy packingBoy = new PackingBoy();
        String ticket = "1235";

        List<Car> carList = new ArrayList<>();
        carList.add(new Car("1234"));
        carList.add(new Car("2345"));
        packingBoy.parkCar(carList);

        //when
        packingBoy.fetchCar(ticket);

        //then
        Assertions.assertEquals("Unrecognized parking ticket.", packingBoy.getErrorMessage());
    }

    @Test
    void should_return_wrong_message_when_fetch_car_given_2car_parkingboy_without_ticket() {
        //given
        PackingBoy packingBoy = new PackingBoy();
        String ticket = null;

        List<Car> carList = new ArrayList<>();
        carList.add(new Car("1234"));
        carList.add(new Car("2345"));
        packingBoy.parkCar(carList);

        //when
        packingBoy.fetchCar(ticket);

        //then
        Assertions.assertEquals("Please provide your parking ticket.",packingBoy.getErrorMessage());
    }

    @Test
    void should_return_null_when_fetch_car_given_2car_pakingboy_ticket_has_benn_used() {
        //given
        PackingBoy packingBoy = new PackingBoy();
        String ticket = "1234";

        List<Car> carList = new ArrayList<>();
        carList.add(new Car("1234"));
        carList.add(new Car("2345"));
        packingBoy.parkCar(carList);

        //when
        packingBoy.fetchCar(ticket);
        packingBoy.fetchCar(ticket);

        //then
        Assertions.assertEquals("Unrecognized parking ticket.",packingBoy.getErrorMessage());
    }
}
