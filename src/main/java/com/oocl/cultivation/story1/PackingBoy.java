package com.oocl.cultivation.story1;

import com.oocl.cultivation.story1.enums.ParkingFetchingEnums;

import java.util.*;

public class PackingBoy extends AbstractPackingBoy{

    public PackingBoy() {
    }

    public PackingBoy(PackingLot parkingLot) {
        super(parkingLot);
    }

    public PackingBoy(List<PackingLot> parkingLotList) {
        super(parkingLotList);
    }

    public String parkCar(Car car) {
        if (car == null) {
            setErrorMessage(ParkingFetchingEnums.CAR_NULL_ERROR_MESSAGE.getMessage());
            return null;
        }

        if (isCarHaveBenParked(car)) {
            setErrorMessage(ParkingFetchingEnums.PARKING_CAR_HAVE_BEEN_PARKED.getMessage());
            return null;
        }

        for (PackingLot packingLot : getPackingLotList()) {
            String ticket = packingLot.parkCar(car);
            if (ticket != null) {
                return ticket;
            }
        }

        setErrorMessage(ParkingFetchingEnums.PARKING_HAVE_NO_SPACE.getMessage());
        return null;
    }

    public List<String> parkCar(List<Car> carList) {
        if (carList == null) {
            setErrorMessage(ParkingFetchingEnums.CAR_NULL_ERROR_MESSAGE.getMessage());
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
            setErrorMessage(ParkingFetchingEnums.PARKING_HAVE_NO_SPACE.getMessage());
        }
        return ticketList;
    }

}
