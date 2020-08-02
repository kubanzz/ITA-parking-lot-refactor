package com.oocl.cultivation.story1;

import com.oocl.cultivation.story1.enums.ParkingFetchingEnums;
import com.oocl.cultivation.story1.exceptions.CarHaveBeenParkedException;
import com.oocl.cultivation.story1.exceptions.CarIllegalException;
import com.oocl.cultivation.story1.exceptions.NotEnoughPositionException;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPackingBoy {
    private List<PackingLot> packingLots = new ArrayList<>();
    private String errorMessage;

    public AbstractPackingBoy() {
        packingLots.add(new PackingLot());
    }

    public AbstractPackingBoy(PackingLot parkingLot) {
        this.packingLots.add(parkingLot);
    }

    public AbstractPackingBoy(List<PackingLot> parkingLotList) {
        this.packingLots = new ArrayList<>(parkingLotList);
    }

    public List<PackingLot> getPackingLots() {
        return packingLots;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Car fetchCar(String ticket) {
        if (ticket == null) {
            errorMessage = ParkingFetchingEnums.FETCHING_HAVE_NO_TICKET.getMessage();
            return null;
        }

        // TODO stream
        for (PackingLot packingLot : packingLots) {
            Car car = packingLot.fetchCar(ticket);
            if (car != null) {
                return car;
            }
        }
        errorMessage = ParkingFetchingEnums.FETCHING_ERROR_TICKET.getMessage();
        return null;
    }

    protected boolean isCarHaveBenParked(Car car){
        return packingLots.stream()
                .anyMatch(packingLot -> packingLot.isCarHaveBeenParked(car));
    }

    public String parkCar(Car car){
        if (car == null) {
            throw new CarIllegalException(ParkingFetchingEnums.CAR_NULL_ERROR_MESSAGE);
        }

        if (isCarHaveBenParked(car)) {
            throw new CarHaveBeenParkedException(ParkingFetchingEnums.PARKING_CAR_HAVE_BEEN_PARKED);
        }

        if (isParkingLotsFull()) {
            throw new NotEnoughPositionException(ParkingFetchingEnums.PARKING_HAVE_NO_SPACE);
        }

        PackingLot packingLot = findRightParkingLot();
        return packingLot.parkCar(car);
    }

    public List<String> parkCar(List<Car> carList){
        if (carList == null) {
            throw new CarIllegalException(ParkingFetchingEnums.CAR_NULL_ERROR_MESSAGE);
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
            throw new NotEnoughPositionException(ParkingFetchingEnums.PARKING_HAVE_NO_SPACE);
        }
        return ticketList;
    }

    public abstract PackingLot findRightParkingLot();

    protected boolean isParkingLotsFull() {
        return packingLots.stream()
                .allMatch(PackingLot::isParkingLogFull);
    }
}
