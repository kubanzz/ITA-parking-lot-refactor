package com.oocl.cultivation.story1;

import java.util.List;

public class SmallParkingBoy extends PackingBoy{

    public SmallParkingBoy(){
        super();
    }

    public SmallParkingBoy(List<PackingLot> packingLotList) {
        super(packingLotList);
    }

    @Override
    public String parkCar(Car car) {
        PackingLot packingLot = findSurplusMaxSpaceParkingLot();
        packingLot.getPackingCarList().add(car);
        return car.getCardId();
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

    @Override
    public List<String> parkCar(List<Car> carList) {
        return super.parkCar(carList);
    }
}
