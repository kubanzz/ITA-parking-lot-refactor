package com.oocl.cultivation.story1;

import java.util.ArrayList;
import java.util.List;

public class PackingBoy {

    private int parkingSpace;
    private List<Car> packingCarList;
    private String errorMessage;

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
            errorMessage = "the car cannot be null";
            return null;
        }

        if (this.parkingSpace > 0) {

            if (packingCarList.contains(car)) {
                errorMessage = "the car has benn packed";
                return null;
            }
            packingCarList.add(car);
            this.parkingSpace --;

            return car.getCardId();
        }
        errorMessage = "Not enough position.";
        return null;
    }

    public List<String> parkCar(List<Car> carList) {
        if (carList == null) {
            errorMessage = "the car cannot be null";
            return null;
        }

        List<String> ticketList = new ArrayList<>();

        for (Car car : carList) {
            if (this.parkingSpace > 0){
                packingCarList.add(car);
                ticketList.add(car.getCardId());
            }else {
                errorMessage = "Not enough position.";
                break;
            }
        }
        return ticketList;
    }

    public Car fetchCar(String ticket) {
        if (ticket == null) {
            errorMessage = "Please provide your parking ticket.";
            return null;
        }

        for (Car car : packingCarList) {
            if (car.getCardId().equals(ticket)) {
                packingCarList.remove(car);
                return car;
            }
        }
        errorMessage = "Unrecognized parking ticket.";
        return null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
