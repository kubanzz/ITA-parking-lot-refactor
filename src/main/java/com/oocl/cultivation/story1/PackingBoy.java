package com.oocl.cultivation.story1;

import com.oocl.cultivation.story1.enums.ParkingFetchingEnums;

import java.util.*;

public class PackingBoy {

    private List<PackingLot> packingLots = new ArrayList<>();
    private String errorMessage;

    public PackingBoy() {
        packingLots.add(new PackingLot());
    }

    public PackingBoy(PackingLot parkingLot) {
        this.packingLots.add(parkingLot);
    }

    public PackingBoy(List<PackingLot> parkingLotList) {
        this.packingLots = new ArrayList<>(parkingLotList);
    }

    public List<PackingLot> getPackingLotList() {
        return this.packingLots;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String parkCar(Car car) {
        if (car == null) {
            errorMessage = ParkingFetchingEnums.CAR_NULL_ERROR_MESSAGE.getMessage();
            return null;
        }

        if (isCarHaveBenParked(car)) {
            errorMessage = ParkingFetchingEnums.PARKING_CAR_HAVE_BEEN_PARKED.getMessage();
            return null;
        }

        for (PackingLot packingLot : packingLots) {
            int parkingSpace = packingLot.getParkingSpace();
            List<Car> packingCarList = packingLot.getPackingCarList();

            if (parkingSpace > 0) {
                packingCarList.add(car);
                packingLot.setParkingSpace(-- parkingSpace);

                return generateTicket(car);
            }
        }

        errorMessage = ParkingFetchingEnums.PARKING_HAVE_NO_SPACE.getMessage();
        return null;
    }

    public List<String> parkCar(List<Car> carList) {
        if (carList == null) {
            errorMessage = ParkingFetchingEnums.CAR_NULL_ERROR_MESSAGE.getMessage();
            return null;
        }

        List<String> ticketList = new ArrayList<>();
        int parkedCarNums = 0;
        for (Car car : carList) {
            String ticket = parkCar(car);
            if (ticket != null) {
                parkedCarNums ++;
                ticketList.add(ticket);
            }
        }

        if (parkedCarNums != carList.size()) {
            errorMessage = ParkingFetchingEnums.PARKING_HAVE_NO_SPACE.getMessage();
        }
        return ticketList;
    }

    boolean isCarHaveBenParked(Car car){
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
            errorMessage = ParkingFetchingEnums.FETCHING_HAVE_NO_TICKET.getMessage();
            return null;
        }

        for (PackingLot packingLot : packingLots) {
            List<Car> carList = packingLot.getPackingCarList();
            int packingSpace = packingLot.getParkingSpace();
            for (Car carParked : carList) {
                if (generateTicket(carParked).equals(ticket)) {
                    carList.remove(carParked);
                    packingLot.setParkingSpace(-- packingSpace);
                    return carParked;
                }
            }
        }

        errorMessage = ParkingFetchingEnums.FETCHING_ERROR_TICKET.getMessage();
        return null;
    }

    protected String generateTicket(Car car){
        return car.getCardId();
    }
}
