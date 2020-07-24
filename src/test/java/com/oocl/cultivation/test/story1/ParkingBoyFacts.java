package com.oocl.cultivation.test.story1;

import com.oocl.cultivation.story1.Car;
import com.oocl.cultivation.story1.PackingBoy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkingBoyFacts {
    @Test
    void should_return_ticket_when_give_car_pakingboy() {
        //given
        PackingBoy packingBoy = new PackingBoy();
        Car car = new Car();

        //when
        String ticket = packingBoy.parkCar(car);

        //then
        Assertions.assertNotNull(ticket);
    }



}
