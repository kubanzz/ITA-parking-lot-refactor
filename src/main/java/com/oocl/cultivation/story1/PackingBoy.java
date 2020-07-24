package com.oocl.cultivation.story1;

import java.util.ArrayList;
import java.util.List;

public class PackingBoy {

    private int parkingSpace;
    public PackingBoy() {
    }

    public PackingBoy(int parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public String parkCar(Car car) {
        return "123";
    }

    public List<String> parkCar(List<Car> carList) {
        List<String> ticketList = new ArrayList<>();

        for (int i = 0; i < carList.size(); i++) {
            ticketList.add(i + "");
        }
        return ticketList;
    }
}
