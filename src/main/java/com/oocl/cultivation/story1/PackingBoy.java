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
            return car.getCardId();
        }
        return "the parking is full";
    }

    public List<String> parkCar(List<Car> carList) {
        List<String> ticketList = new ArrayList<>();

        for (Car car : carList) {
            ticketList.add(car.getCardId());
        }
        return ticketList;
    }

    public Car fetchCar(PackingBoy packingBoy, List<Car> carList, String ticket) {
        return null;
    }
}
