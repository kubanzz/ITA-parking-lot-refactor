package com.oocl.cultivation.story1;

import com.oocl.cultivation.story1.entity.Car;
import com.oocl.cultivation.story1.entity.Ticket;

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

    public Car fetchCar(Ticket ticket){
        for (Car carParked : packingCarList) {
            if (Ticket.generateTicket(carParked).equals(ticket)) {
                packingCarList.remove(carParked);
                this.parkingSpace ++;
                return carParked;
            }
        }
        return null;
    }

    public Ticket parkCar(Car car){
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

    public boolean isParkingLogFull(){
        return packingCarList.size() >= parkingSpace;
    }
}
