package com.oocl.cultivation.story1;

import com.oocl.cultivation.story1.enums.ParkingFetchingEnums;

import java.util.ArrayList;
import java.util.List;

public class PackingLot {
    private int parkingSpace;
    private final List<Car> packingCarList;

    public PackingLot() {
        this.parkingSpace = 10;
        this.packingCarList = new ArrayList<>();
    }

    public PackingLot(int parkingSpace) {
        this.parkingSpace = parkingSpace;
        this.packingCarList = new ArrayList<>();
    }

    public int getRemainingSpace() {
        return parkingSpace - packingCarList.size();
    }

    int getParkingSpace() {
        return parkingSpace;
    }

    public Car fetchCar(String ticket){
        for (Car carParked : packingCarList) {
            if (Ticket.generateTicket(carParked).equals(ticket)) {
                packingCarList.remove(carParked);
                this.parkingSpace ++;
                return carParked;
            }
        }
        return null;
    }

    public String parkCar(Car car){
        if (this.getRemainingSpace() > 0) {
            this.packingCarList.add(car);
            return Ticket.generateTicket(car);
        }
        return null;
    }

    public boolean isCarHaveBeenParked(Car car){
        return packingCarList.stream()
                .anyMatch(carParked -> carParked.getCardId().equals(car.getCardId()));
    }

}
