package com.oocl.cultivation.story1;

import java.util.ArrayList;
import java.util.List;

public class PackingBoy {

    private int parkingSpace;

    public PackingBoy() {
        parkingSpace = 10;
    }

    public PackingBoy(int parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public String parkCar(Car car) {
        if (this.parkingSpace > 0) {
            return "123";
        }
        return "the parking is full";
    }

    public List<String> parkCar(List<Car> carList) {
        List<String> ticketList = new ArrayList<>();

        for (int i = 0; i < carList.size(); i++) {
            ticketList.add(i + "");
        }
        return ticketList;
    }
}
