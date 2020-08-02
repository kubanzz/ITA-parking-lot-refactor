package com.oocl.cultivation.story1;

import com.oocl.cultivation.story1.enums.ParkingFetchingEnums;

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

    public List<PackingLot> getPackingLotList() {
        return this.packingLots;
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
        for (PackingLot packingLot : getPackingLotList()) {
            boolean isCarPacked = packingLot.isCarHaveBeenParked(car);
            if (isCarPacked){
                return true;
            }
        }
        return false;
    }

    public abstract String parkCar(Car car);
    public abstract List<String> parkCar(List<Car> carList);

}
