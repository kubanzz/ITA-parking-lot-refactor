package com.oocl.cultivation.story1;

import java.util.ArrayList;
import java.util.List;

public class PackingBoy {

    private List<PackingLot> packingLots = new ArrayList<>();
    private String errorMessage;

    public PackingBoy() {
        packingLots.add(new PackingLot());
    }

    public PackingBoy(PackingLot parkingLotList) {
        this.packingLots.add(parkingLotList);
    }

    public String parkCar(Car car) {
        if (car == null) {
            errorMessage = "the car cannot be null";
            return null;
        }

        if (isCarHaveBenParked(car)) {
            errorMessage = "the car has benn packed";
            return null;
        }

        for (PackingLot packingLot : packingLots) {
            int parkingSpace = packingLot.getParkingSpace();
            List<Car> packingCarList = packingLot.getPackingCarList();

            if (parkingSpace > 0) {
                packingCarList.add(car);
                packingLot.setParkingSpace(-- parkingSpace);

                return car.getCardId();
            }
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
            if (isCarHaveBenParked(car)) {
                errorMessage = "the car has benn packed";
                return null;
            }
        }

        int parkedCarNums = 0;
        for (Car car : carList) {
            for (PackingLot packingLot : packingLots) {
                int parkingSpace = packingLot.getParkingSpace();
                List<Car> packingCarList = packingLot.getPackingCarList();

                if (parkingSpace > 0) {
                    packingCarList.add(car);
                    parkedCarNums ++;

                    packingLot.setParkingSpace(-- parkingSpace);
                    ticketList.add(car.getCardId());
                    break;
                }
            }
            if (parkedCarNums != carList.size()) {
                errorMessage = "Not enough position.";
            }
        }
        return ticketList;
    }

    private boolean isCarHaveBenParked(Car car){
        for (PackingLot packingLot : packingLots) {
            List<Car> carList = packingLot.getPackingCarList();
            for (Car carParked : carList) {
                if (carParked.getCardId().equals(car.getCardId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public Car fetchCar(String ticket) {
        if (ticket == null) {
            errorMessage = "Please provide your parking ticket.";
            return null;
        }

        for (PackingLot packingLot : packingLots) {
            List<Car> carList = packingLot.getPackingCarList();
            for (Car carParked : carList) {
                if (carParked.getCardId().equals(ticket)) {
                    carList.remove(carParked);
                    return carParked;
                }
            }
        }

        errorMessage = "Unrecognized parking ticket.";
        return null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
