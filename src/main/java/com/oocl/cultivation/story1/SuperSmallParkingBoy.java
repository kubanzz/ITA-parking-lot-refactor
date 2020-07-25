package com.oocl.cultivation.story1;

import java.util.List;

public class SuperSmallParkingBoy extends PackingBoy {


    public SuperSmallParkingBoy(List<PackingLot> packingLotList) {
        super(packingLotList);
    }

    public SuperSmallParkingBoy() {
        super();
    }

    @Override
    public String parkCar(Car car) {
        PackingLot packingLot = findMaxSiteRateParkingLot();
        if (packingLot != null && packingLot.getParkingSpace() > 0) {
            packingLot.getPackingCarList().add(car);
            return car.getCardId();
        }
        return super.parkCar(car);
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
        return parkingLot.getParkingSpace() / (parkingLot.getParkingSpace() + parkingLot.getPackingCarList().size());
    }

}
