package com.oocl.cultivation.story1;

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
        PackingLot packingLot = findSurplusMaxSpaceParkingLot();
        if (packingLot != null && packingLot.getParkingSpace() > 0) {
            packingLot.getPackingCarList().add(car);
            return car.getCardId();
        }
        setErrorMessage("Not enough position.");
        return null;
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
