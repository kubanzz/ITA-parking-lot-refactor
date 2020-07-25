package com.oocl.cultivation.story1;

import com.oocl.cultivation.story1.enums.ParkingFetchingEnums;

import java.util.ArrayList;
import java.util.List;

public class SmallParkingBoy extends PackingBoy{

    public SmallParkingBoy(){
        super();
    }

    public SmallParkingBoy(PackingLot packingLot) {
        super(packingLot);
    }

    public SmallParkingBoy(List<PackingLot> packingLotList) {
        super(packingLotList);
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

        PackingLot packingLot = findSurplusMaxSpaceParkingLot();
        if (packingLot != null && packingLot.getParkingSpace() > 0) {
            packingLot.getPackingCarList().add(car);
            packingLot.setParkingSpace(packingLot.getParkingSpace() - 1);
            return car.getCardId();
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

    private PackingLot findSurplusMaxSpaceParkingLot() {
        List<PackingLot> packingLotList = getPackingLotList();
        PackingLot surplusMaxSpacParkingLot = null;
        for (PackingLot packingLot : packingLotList) {
            if (surplusMaxSpacParkingLot == null || surplusMaxSpacParkingLot.getParkingSpace() < packingLot.getParkingSpace()) {
                surplusMaxSpacParkingLot = packingLot;
            }
        }

        return surplusMaxSpacParkingLot;
    }

}
