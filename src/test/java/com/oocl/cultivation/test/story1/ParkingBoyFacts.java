package com.oocl.cultivation.test.story1;

import com.oocl.cultivation.story1.PackingBoyFacts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkingBoyFacts {
    @Test
    void should_return_ticket_when_give_car_given_car() {
        //given
        PackingBoyFacts packingBoyFacts = new PackingBoyFacts();

        //when
        String ticket = packingBoyFacts.parkCar();

        //then
        Assertions.assertNotNull(ticket);
    }



}
