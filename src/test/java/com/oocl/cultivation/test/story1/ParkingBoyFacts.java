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
        String result = packingBoy.parkCar(car);

        //then
        Assertions.assertEquals("the parking is full",result);
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
        Car fetchCar = packingBoy.fetchCar(packingBoy, carList, ticket);

        //then
        Assertions.assertEquals(ticket, fetchCar.getCardId());
    }

}
