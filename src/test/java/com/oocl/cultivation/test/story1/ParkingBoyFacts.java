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
        Car car = new Car();

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
        carList.add(new Car());
        carList.add(new Car());

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
        Car car = new Car();

        //when
        String result = packingBoy.parkCar(car);

        //then
        Assertions.assertEquals("the parking is full",result);
    }

}
