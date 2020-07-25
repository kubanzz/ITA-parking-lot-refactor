package com.oocl.cultivation.story1;

import java.util.ArrayList;
import java.util.List;

public class SuperSmallParkingBoy extends PackingBoy {


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
            setErrorMessage("the car cannot be null");
            return null;
        }

        if (isCarHaveBenParked(car)) {
            setErrorMessage("the car has benn packed");
            return null;
        }

        PackingLot packingLot = findMaxSiteRateParkingLot();
        if (packingLot != null && packingLot.getParkingSpace() > 0) {
            packingLot.getPackingCarList().add(car);
            packingLot.setParkingSpace(packingLot.getParkingSpace() - 1);
            return car.getCardId();
        }
        setErrorMessage("Not enough position.");
        return null;
    }

    @Override
    public List<String> parkCar(List<Car> carList) {
        if (carList == null) {
            setErrorMessage("the car cannot be null");
            return null;
        }

        for (Car car : carList) {
            if (isCarHaveBenParked(car)) {
                setErrorMessage("the car has benn packed");
                return null;
            }
        }

        List<String> ticketList = new ArrayList<>();
        int parkedCarNums = 0;
        for (Car car : carList) {
            PackingLot packingLot = findMaxSiteRateParkingLot();
            if (packingLot != null && packingLot.getParkingSpace() > 0) {
                int parkingSpace = packingLot.getParkingSpace();
                List<Car> packingCarList = packingLot.getPackingCarList();

                packingCarList.add(car);
                packingLot.setParkingSpace(-- parkingSpace);
                parkedCarNums ++;
                ticketList.add(car.getCardId());
            }
        }

        if (parkedCarNums != carList.size()) {
            setErrorMessage("Not enough position.");
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
        return parkingLot.getParkingSpace() / (parkingLot.getParkingSpace() + parkingLot.getPackingCarList().size());
    }

}
