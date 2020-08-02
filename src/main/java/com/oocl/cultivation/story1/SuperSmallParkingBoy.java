package com.oocl.cultivation.story1;

import com.oocl.cultivation.story1.enums.ParkingFetchingEnums;

import java.util.ArrayList;
import java.util.List;

public class SuperSmallParkingBoy extends AbstractPackingBoy {


    public SuperSmallParkingBoy(PackingLot packingLot) {
        super(packingLot);
    }

    public SuperSmallParkingBoy(List<PackingLot> packingLotList) {
        super(packingLotList);
    }

    public SuperSmallParkingBoy() {
        super();
    }

    @Override
    public String parkCar(Car car) {
        if (car == null) {
            setErrorMessage(ParkingFetchingEnums.CAR_NULL_ERROR_MESSAGE.getMessage());
            return null;
        }

        if (isCarHaveBenParked(car)) {
            setErrorMessage(ParkingFetchingEnums.PARKING_CAR_HAVE_BEEN_PARKED.getMessage());
            return null;
        }

        PackingLot packingLot = findMaxSiteRateParkingLot();
        if (packingLot != null && packingLot.getParkingSpace() > 0) {
            return packingLot.parkCar(car);
        }
        setErrorMessage(ParkingFetchingEnums.PARKING_HAVE_NO_SPACE.getMessage());
        return null;
    }

    @Override
    public List<String> parkCar(List<Car> carList) {
        if (carList == null) {
            setErrorMessage(ParkingFetchingEnums.CAR_NULL_ERROR_MESSAGE.getMessage());
            return null;
        }

        for (Car car : carList) {
            if (isCarHaveBenParked(car)) {
                setErrorMessage(ParkingFetchingEnums.PARKING_CAR_HAVE_BEEN_PARKED.getMessage());
                return null;
            }
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
            setErrorMessage(ParkingFetchingEnums.PARKING_HAVE_NO_SPACE.getMessage());
        }
        return ticketList;
    }

    private PackingLot findMaxSiteRateParkingLot() {
        List<PackingLot> packingLotList = getPackingLotList();
        PackingLot maxSiteRateParkingLot = null;
        double maxSiteRate = 0;

        for (PackingLot packingLot : packingLotList) {
            if (maxSiteRateParkingLot == null || maxSiteRate < calculateSiteRate(packingLot)) {
                maxSiteRateParkingLot = packingLot;
                maxSiteRate = calculateSiteRate(packingLot);
            }
        }

        return maxSiteRateParkingLot;
    }

    private double calculateSiteRate(PackingLot parkingLot) {
        if (parkingLot.getParkingSpace() == 0) {
            return 0;
        }
        return (parkingLot.getRemainingSpace() + 0.0) / parkingLot.getParkingSpace();
    }

}
