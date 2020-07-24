package com.oocl.cultivation.story1;

import java.util.ArrayList;
import java.util.List;

public class PackingBoy {

    private int parkingSpace;
    private List<Car> packingCarList;

    public PackingBoy() {
        parkingSpace = 10;
        packingCarList = new ArrayList<>();
    }

    public PackingBoy(int parkingSpace) {
        this.parkingSpace = parkingSpace;
        packingCarList = new ArrayList<>();
    }

    public String parkCar(Car car) {
        if (car == null) {
            return "the car cannot be null";
        }

        if (this.parkingSpace > 0) {

            if (packingCarList.contains(car)) {
                return "the car has benn packed";
            }
            packingCarList.add(car);
            this.parkingSpace --;

            return car.getCardId();
        }
        return "the parking is full";
    }

    public List<String> parkCar(List<Car> carList) {
        List<String> ticketList = new ArrayList<>();

        for (Car car : carList) {
            packingCarList.add(car);
            ticketList.add(car.getCardId());
        }
        return ticketList;
    }

    public Car fetchCar(PackingBoy packingBoy, String ticket) {
        for (Car car : packingCarList) {
            if (car.getCardId().equals(ticket)) {
                packingCarList.remove(car);
                return car;
            }
        }
        return null;
    }
}
